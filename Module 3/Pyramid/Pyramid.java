package Pyramid;

import java.io.PrintStream;

public class Pyramid {
	
	PrintStream out;
	
	Pyramid() {
		out = new PrintStream(System.out);
	}
	
	void printer(int number, char input) {
		for (int i = 0; i <= number; i++) {
			out.print(input);
		}
	}
	
	void start() {
		char letter = 'a';
		int counter = 0;
		for (int i=0; i < 15; i++) {
			printer(counter, letter);
			counter += 2;
			letter ++;
			out.println();
		}
	}

	public static void main(String[] args) {
		new Pyramid().start();
	}
}
