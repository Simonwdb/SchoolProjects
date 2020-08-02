package practice;

import java.io.PrintStream;

public class RecursionPractice {
	
	PrintStream out;
	
	RecursionPractice() {
		out = new PrintStream(System.out);
	}
	
	int fact(int n) {
		if (n <= 1) {
			return 1;
		} else {
			return n * fact(n - 1);
		}
	}
	
	void printFun(int test) {
		if (test < 1) {
			return;
		} else {
			out.printf("%d ", test);
			printFun(test - 1);
			out.printf("%d ", test);
			return;
		}
	}

	public static void main(String[] args) {
		int test = 10;
		new RecursionPractice().printFun(test);
	}
}
