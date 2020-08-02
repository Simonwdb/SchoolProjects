package snake;

public class CoordinateRow {
	
	final static int MAX_ELEMENTS = 500;
	int indexNumber;
	Coordinate[] coordArray;
	
	CoordinateRow() {
		coordArray = new Coordinate[MAX_ELEMENTS];
		indexNumber = 0;
	}
	
	void shiftElementUp() {
		for (int i = indexNumber - 1; i > 0; i--) {
			coordArray[i] = coordArray[i - 1]; 
		}
	}
	
	void addToBack(Coordinate coord) {
		coordArray[indexNumber] = coord;
		indexNumber ++;
	}
	
	void addArrayToBack(CoordinateRow array) {
		for (int i = 0; i < array.indexNumber; i++) {
			addToBack(array.coordArray[i]);
		}
	}
	
	void addInFront(Coordinate coord) {
		indexNumber ++;
		for (int i = indexNumber; i > 0; i--) {
			coordArray[i] = coordArray[i - 1];
		}
		coordArray[0] = coord;
	}
	
	void addArrayInFront(CoordinateRow array) {
		for(int i = array.indexNumber - 1; i >= 0; i--) {
			addInFront(array.coordArray[i]);
		}
	}
	
	boolean checkWallCollision(Coordinate headSnake, CoordinateRow wall) {
		for (int i = 0; i < wall.indexNumber; i++) {
			if (headSnake.x == wall.coordArray[i].x && headSnake.y == wall.coordArray[i].y) {
				return true;
			}
		}
		return false;
	}
	
	boolean checkSnakeBodyCollision(Coordinate headSnake, CoordinateRow snakeBody) {
		for (int i = 1; i < snakeBody.indexNumber - 1; i++) {
			if (headSnake.x == snakeBody.coordArray[i].x && headSnake.y == snakeBody.coordArray[i].y) {
				return true;
			}
		}
		return false;
	}
	
	boolean checkSingleCollision(Coordinate first, Coordinate second) {
		if(first.x == second.x && first.y == second.y) {
			return true;
		}
		return false;
	}
}
