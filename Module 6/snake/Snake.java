package snake;

import java.io.PrintStream;
import java.util.Scanner;

import ui.Event;
import ui.SnakeUserInterface;
import ui.UIAuxiliaryMethods;
import ui.UserInterfaceFactory;

public class Snake {
	
	final static int MAX_WIDTH = 32;			
	final static int MAX_HEIGHT = 24;
	final static int START_MAP = 2;
	
	static double FPS = 10.0;
	static int score = -1;
	
	boolean isAppleEaten = false;
	boolean isApplePlaced = false;
	boolean isAppleOnObject = false;
	boolean isPlayingGame = true;
	
	String direction = null;

	Coordinate apple;
	CoordinateRow map;
	CoordinateRow snake;

	PrintStream out;
	SnakeUserInterface ui;
	
	Snake() {
		map = createMap();
		out = new PrintStream(System.out);
		ui = UserInterfaceFactory.getSnakeUI(MAX_WIDTH, MAX_HEIGHT);
		snake = new CoordinateRow();
	}
	
	CoordinateRow createArray(String input) {
		Scanner coordScanner = new Scanner(input);
		CoordinateRow arrayOfCoords = new CoordinateRow();

		while(coordScanner.hasNext()) {
			int x = coordScanner.nextInt();
			int y = coordScanner.nextInt();
			Coordinate singleCoord = new Coordinate(x, y);
			arrayOfCoords.addToBack(singleCoord);
		}
		return arrayOfCoords;
	}
	
	CoordinateRow createMap() {
		Scanner fileScanner = UIAuxiliaryMethods.askUserForInput().getScanner();
		fileScanner.useDelimiter("=");
		CoordinateRow gameMap = new CoordinateRow();
		CoordinateRow firstCoord = createArray(fileScanner.next());
		gameMap.addArrayInFront(firstCoord);
		direction = fileScanner.next();
		
		while(fileScanner.hasNext()) {
			CoordinateRow tempArray = createArray(fileScanner.next());
			gameMap.addArrayToBack(tempArray);
		}
		return gameMap;
	}
	
	int directionRight() {
		int dir;

		if (snake.coordArray[0].x < MAX_WIDTH - 1) {
			dir = snake.coordArray[0].x + 1;
		} else {
			dir =  0;
		}
		return dir;
	}
	
	int directionLeft() {
		int dir;

		if (snake.coordArray[0].x <= 0) {
			dir = MAX_WIDTH - 1;
		} else {
			dir = snake.coordArray[0].x - 1;
		}
		return dir;
	}
	
	int directionUp() {
		int dir;

		if (snake.coordArray[0].y > 0) {
			dir = snake.coordArray[0].y - 1;
		} else {
			dir = MAX_HEIGHT - 1;
		}
		return dir;
	}
	
	int directionDown() {
		int dir;

		if (snake.coordArray[0].y < MAX_HEIGHT - 1) {
			dir = snake.coordArray[0].y + 1;
		} else {
			dir = 0;
		}
		return dir;
	}
	
	Coordinate checkDirection(Coordinate snakeDirection) {
		Coordinate head = snakeDirection;

		if (direction.equals("R")) {
			head.x = directionRight();
		} else if (direction.equals("L")) {
			head.x = directionLeft();
		} else if (direction.equals("U")) {
			head.y = directionUp();
		} else {
			head.y = directionDown();
		}
		return head;
	}
	
	Coordinate createHeadSnake() {
		int x = snake.coordArray[0].x;
		int y = snake.coordArray[0].y;
		Coordinate head = new Coordinate(x, y);
		head = checkDirection(head);
		return head;
	}
	
	void createApple() {
		int x = UIAuxiliaryMethods.getRandom(0, MAX_WIDTH);
		int y = UIAuxiliaryMethods.getRandom(0, MAX_HEIGHT);
		apple = new Coordinate(x, y);
	}
	
	void placeApple() {
		createApple();

		if(map.checkWallCollision(apple, map) | snake.checkSnakeBodyCollision(apple, snake)) {
			createApple();
			isApplePlaced = false;
		} else {
			ui.place(apple.x, apple.y, ui.FOOD);
			isApplePlaced = true;
			score ++;
		}
	}
	
	void checkApple() {
		if(!isApplePlaced) {
			placeApple();
		}
	}
	
	void moveSnake() {
		Coordinate newHead = createHeadSnake();

		if (!isAppleEaten) {
			snake.shiftElementUp();
			snake.coordArray[0] = newHead;
		} else {
			snake.addInFront(newHead);
			isAppleEaten = false;
		}
	}
	
	void checkSnakeWithWall() {
		if (map.checkWallCollision(snake.coordArray[0], map)) {
			isPlayingGame = false;
		}
	}
	
	void checkSnakeWithBody() {
		if (snake.checkSnakeBodyCollision(snake.coordArray[0], snake)) {
			isPlayingGame = false;
		}
	}
	
	void checkSnakeWithApple() {
		if (snake.checkSingleCollision(snake.coordArray[0], apple)) {
			isAppleEaten = true;
			isApplePlaced = false;
		}
	}
	
	void placeWall() {
		for (int i = START_MAP; i < map.indexNumber; i++) {
			ui.place(map.coordArray[i].x, map.coordArray[i].y, ui.WALL);
		}
	}
	
	void placeSnake() {
		for (int i = 0; i < snake.indexNumber; i++) {
			ui.place(snake.coordArray[i].x, snake.coordArray[i].y, ui.SNAKE);
			ui.place(snake.coordArray[snake.indexNumber - 1].x, snake.coordArray[snake.indexNumber - 1].y, ui.EMPTY);
		}
	}
	
	void processArrow(String arrowDirection) {
		if (arrowDirection.equals("R") && !direction.equals("L")) {
			direction = "R";
		} else if (arrowDirection.equals("L") && !direction.equals("R")) {
			direction = "L";
		} else if (arrowDirection.equals("U") && !direction.equals("D")) {
			direction = "U";
		} else if (arrowDirection.equals("D") && !direction.equals("U")) {
			direction = "D";
		}
	}
	
	void processEvent(Event event) {
		if (event.name.equals("arrow")) {
			processArrow(event.data);
		} else if (event.name.equals("alarm")) {
			processAlarm();
		}
	}
	
	void showscore() {
		ui.clearStatusBar();
		ui.printf("Score is: %d", score);
	}
	
	void processAlarm() {
		moveSnake();
		placeWall();
		placeSnake();
		checkApple();
//		checkSnakeWithBody();
		checkSnakeWithApple();
//		checkSnakeWithWall();
		showscore();
	}
	
	void startUpGame() {
		Coordinate headSnake = new Coordinate(map.coordArray[0].x,map.coordArray[0].y);  	
		Coordinate secondPart = new Coordinate(map.coordArray[1].x,map.coordArray[1].y);
		snake.addToBack(headSnake);
		snake.addToBack(secondPart);
		ui.setFramesPerSecond(FPS);
	}
	
	void gameOver() {
		String message;
		
		if (map.checkWallCollision(snake.coordArray[0], map)) {
			message = "Game over, snake hit the wall.";
		} else {
			message = "Game over, snake hit its body.";
		}
		UIAuxiliaryMethods.showMessage(message);
	}
	
	void start() {
		startUpGame();

		while (isPlayingGame) {
			Event event = ui.getEvent();
			processEvent(event);
			ui.showChanges();
		}
		gameOver();
	}
	
	public static void main(String[] args) {
		new Snake().start();
	}
}
