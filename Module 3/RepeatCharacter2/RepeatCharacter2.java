package RepeatCharacter2;

import java.io.PrintStream;
import java.util.Scanner;

public class RepeatCharacter2 {
	
	PrintStream out;
	
	RepeatCharacter2() {
		out = new PrintStream(System.out);
	}
	
	void readIntNumber(int number, char text) {
		for (int i = 0; i < number; i++) {
			out.print(text);
		}

	}
	
	int getNumber(Scanner input) {
		out.print("Fill in a number: ");
		int number = input.nextInt();
		
		return number;
	}
	
	void start(){
		Scanner in = new Scanner(System.in);
		int amount1 = getNumber(in);
		readIntNumber(amount1, '!');
		out.println();
		int amount2 = getNumber(in);
		readIntNumber(amount2, ',');
	}

	public static void main(String[] args) {
		new RepeatCharacter2().start();

	}

}
