package recursion;

import ui.LabyrinthUserInterface;

public class CoordinateRow {
	
	final static int MAX_ELEMENTS = 1000;
	int indexNumber;
	Coordinate[] coordArray;
	
	CoordinateRow() {
		coordArray = new Coordinate[MAX_ELEMENTS];
		indexNumber = 0;
	}

	void remove() {
		indexNumber = indexNumber - 1;
		
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
	
	boolean checkCollision(Coordinate headSnake) {
		for (int i = 2; i < indexNumber; i++) {
			if (headSnake.x == coordArray[i].x && headSnake.y == coordArray[i].y) {
				return true;
			}
		}
		return false;
	}
}
