package Pirate;

import java.io.PrintStream;
import java.util.Scanner;
import ui.UIAuxiliaryMethods;

public class Pirate {
	
	final static int X_SHIFTED = 1;
	
	PrintStream out;
	
	Pirate() {
		out = new PrintStream(System.out);
	}
	
	Coordinate createCoord(String input) {
		Scanner coordScanner = new Scanner(input);
		coordScanner.useDelimiter(",");
		Coordinate coord = new Coordinate(coordScanner.nextInt() + X_SHIFTED, coordScanner.nextInt());
		return coord;
	}
	
	CoordinateRow createRow(String input) {
		Scanner rowScanner = new Scanner(input);
		CoordinateRow arrayOfCoords = new CoordinateRow();
		while (rowScanner.hasNext()) {
			Coordinate singleCoord = createCoord(rowScanner.next());
			arrayOfCoords.addToBack(singleCoord);
		}
		return arrayOfCoords;
	}
	
	void printArray(CoordinateRow input) {
		for (int i = 0; i < input.indexNumber; i++) {
			out.printf("%d,%d\n", input.coordArray[i].x, input.coordArray[i].y);
		}
	}
	
	void start() {
		Scanner input = UIAuxiliaryMethods.askUserForInput().getScanner();
		input.useDelimiter("=");
		CoordinateRow finalArray = new CoordinateRow();
		int ticker = 0;
		
		while (input.hasNext()) {
			ticker ++;
			CoordinateRow tempArray = createRow(input.next());
			if (ticker % 2 == 0) {
				finalArray.addArrayToBack(tempArray);
			} else {
				finalArray.addArrayInFront(tempArray);
			}
		}
		printArray(finalArray);
	}

	public static void main(String[] args) {
		new Pirate().start();
	}
}
