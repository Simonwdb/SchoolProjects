package recursion;

import java.io.PrintStream;
import java.util.Scanner;

import ui.LabyrinthUserInterface;
import ui.UIAuxiliaryMethods;
import ui.UserInterfaceFactory;

public class Recursion {
	
	final static int MAX_W = 32;
	final static int MAX_H = 24;
	final static int WAITING_TIME = 50;
	final static int DIRECTION = 4;
	final static int EAST = 0;
	final static int SOUTH = 1;
	final static int WEST = 2;
	
	CoordinateRow map;
	CoordinateRow path;
	Coordinate endCoord;
	
	LabyrinthUserInterface ui;
	PrintStream out;
	
	Recursion() {
		out = new PrintStream(System.out);
		map = createMap();
		endCoord = new Coordinate(map.coordArray[1].x, map.coordArray[1].y);
		path = new CoordinateRow();
		ui = UserInterfaceFactory.getLabyrinthUI(MAX_W, MAX_H);
	}
	
	CoordinateRow createArray(String input) {
		Scanner coordScanner = new Scanner(input);
		CoordinateRow arrayCoords = new CoordinateRow();
		
		while(coordScanner.hasNext()) {
			int x = coordScanner.nextInt();
			int y = coordScanner.nextInt();
			Coordinate singleCoord = new Coordinate(x, y);
			arrayCoords.addToBack(singleCoord);
		}
		return arrayCoords;
	}
	
	CoordinateRow createMap() {
		Scanner fileScanner = UIAuxiliaryMethods.askUserForInput().getScanner();
		fileScanner.useDelimiter("=");
		CoordinateRow gameMap = new CoordinateRow();
		
		while(fileScanner.hasNext()) {
			CoordinateRow tempArray = createArray(fileScanner.next());
			gameMap.addArrayToBack(tempArray);
		}
		return gameMap;
	}
	
	void placeWall() {
		for (int i = 2; i < map.indexNumber; i++) {
			ui.place(map.coordArray[i].x, map.coordArray[i].y, ui.WALL);
		}
		ui.place(endCoord.x, endCoord.y, ui.PATH);
	}
	
	void startUpGame() {
		Coordinate startingCoord = new Coordinate(map.coordArray[0].x, map.coordArray[0].y);
		path.addToBack(startingCoord);
		ui.place(startingCoord.x, startingCoord.y, ui.PATH);
	}
	
	Coordinate newStep(int direction) {
		int x = path.coordArray[path.indexNumber-1].x;
		int y = path.coordArray[path.indexNumber-1].y;
		Coordinate newCoord = new Coordinate(x, y);
		if (direction == EAST) {
			newCoord.x = newCoord.x + 1;
		} else if (direction == SOUTH) {					
			newCoord.y = newCoord.y + 1;
		} else if (direction == WEST) {
			newCoord.x = newCoord.x - 1;
		} else {
			newCoord.y = newCoord.y - 1;
		}
		return newCoord;
	}
	
	void findPath () {
		int x = path.coordArray[path.indexNumber - 1].x;
		int y = path.coordArray[path.indexNumber - 1].y;
		Coordinate newCoord = new Coordinate(x, y);
		
		if (newCoord.checkSingleCollision(endCoord)) {
			return;
		}

		for (int i = 0; i < DIRECTION; i++) {
			Coordinate newpiece = newStep(i);
			if (!map.checkCollision(newpiece) && !path.checkCollision(newpiece)) {
				path.addToBack(newpiece);
				ui.place(newpiece.x, newpiece.y, ui.PATH);
				ui.showChanges();
				ui.wait(WAITING_TIME);
				findPath();
			}
		}
		path.remove();
		ui.place(path.coordArray[path.indexNumber].x, path.coordArray[path.indexNumber].y, ui.EMPTY);
		ui.showChanges();
		ui.wait(WAITING_TIME);
	}
	
	void start() {
		startUpGame();
		placeWall();
		findPath();
	}

	public static void main(String[] args) {
		new Recursion().start();
	}
}
