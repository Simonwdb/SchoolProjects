package Pirate;

public class CoordinateRow {
	
	final static int MAX_ELEMENTS = 100;
	int indexNumber;
	Coordinate[] coordArray;
	
	CoordinateRow(){
		coordArray = new Coordinate[MAX_ELEMENTS];
		indexNumber = 0;
	}
	
	void addToBack(Coordinate input) {
		coordArray[indexNumber] = input;
		indexNumber ++;
	}
	
	void addArrayToBack(CoordinateRow input) {
		for (int i = 0; i < input.indexNumber; i++) {
			addToBack(input.coordArray[i]);
		}
	}
	
	void addInFront(Coordinate input) {
		for (int i = 0; i < indexNumber; i++) {
			coordArray[indexNumber - i] = coordArray[indexNumber - i - 1];
		}
		indexNumber ++;
		coordArray[0] = input;
	}
	
	void addArrayInFront(CoordinateRow input) {
		for (int i = 1; i <= input.indexNumber; i++) {
			addInFront(input.coordArray[input.indexNumber - i]);
		}
	}
}
