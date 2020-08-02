package Pirate;

import java.io.PrintStream;
import java.util.Scanner;

import ui.UIAuxiliaryMethods;

public class test {
	
	PrintStream out;
	
	test() {
		out = new PrintStream(System.out);
	}
	
	Coordinate createC(String input) {
		Scanner cScanner = new Scanner(input);
		cScanner.useDelimiter(",");
		
		int x = cScanner.nextInt() + 1;
		int y = cScanner.nextInt();
		
		Coordinate c = new Coordinate(x, y);
		return c;
	}
	
	
	CoordinateRow createArray(String input) {
		Scanner rowScanner = new Scanner(input);
		CoordinateRow opslag = new CoordinateRow();
		
		while (rowScanner.hasNext()) {
			Coordinate sC = createC(rowScanner.next());
			opslag.addToBack(sC);
		}
		
		return opslag;
	}
	
	void printArray(CoordinateRow input) {
		for (int i = 0; i < input.indexNumber; i++) {
			out.printf("%d,%d\n", input.coordArray[i].x, input.coordArray[i].y);
		}
	}
	
	
	void start() {
		Scanner file = UIAuxiliaryMethods.askUserForInput().getScanner();
		file.useDelimiter("=");
		
		CoordinateRow finalA = new CoordinateRow();
		
		
		while(file.hasNext()) {
			CoordinateRow tA = createArray(file.next());
			
			boolean front = true;
			
			if(front) {
				finalA.addArrayInFront(tA);
			} else {
				finalA.addArrayToBack(tA);
			}
			
			front =! front;
		}
		
		printArray(finalA);
	}
	
	
	
	
	public static void main(String[] args) {
		new test().start();
		

	}

}
