package practice;

import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Random;

import Pirate.Coordinate;

public class Practice {
	
	final static int ELEMENTS = 10;
	int[] test ;
	int indexNum ;
	
	PrintStream out;
	
	Practice() {
		test = new int[ELEMENTS];
		indexNum = 0;
		out = new PrintStream(System.out);
	}
	
	void add(int input) {
		test[indexNum] = input;
		indexNum ++;
	}
	
	void autoAdd() {
		for (int i = 0; i < ELEMENTS; i++) {
			add(i);
		}
	}
	
	void printArrayLowToHigh() {
		for (int i = 0; i < indexNum; i++) {
			out.println(test[i]);
		}
	}
	
	void printArrayHighToLow() {
		for (int i = indexNum - 1; i >= 0; i--) {
			out.println(test[i]);
		}
	}
	
	/*
  	void addInFront(Coordinate input) {
		for (int i = 0; i < indexNumber; i++) {
			coordArray[indexNumber - i] = coordArray[indexNumber - i - 1];
		}
		indexNumber ++;
		coordArray[0] = input;
	}
	*/	
	
	void start() {
		autoAdd();
		out.println(indexNum);
//		printArrayLowToHigh();
		printArrayHighToLow();
	}
	
	
	void test() {
		int test = new Random().nextInt(10);
		out.printf("%d", test);
	}

	public static void main(String[] args) {
		new Practice().test();

	}
}
/*
*/