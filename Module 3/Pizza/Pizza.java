package Pizza;

import java.io.PrintStream;

public class Pizza {
	
	final static int DIFF_INGR_MARIO = 10;
	final static int INGR_PIZZA_MARIO = 3;
	final static int DIFF_INGR_LUIGI = 9;
	final static int ING_PIZZA_LUIGI = 4;
	
	PrintStream out;
	
	Pizza() {
		out = new PrintStream(System.out);
	}
	
	int faculteit(int num) {
		int counter;
		for (counter = num -1 ; counter > 1; counter--) {
			num *= counter;
		}
		return num;
	}
	
	double calculate(double n, double k, double j) {
		double result = n / (k * j);
		return(result);
	}
	
	void determineWinner(int mario, int luigi) {
		if (mario < luigi) {
			out.printf("The winner of the bet is Luigi with: %d", luigi);
		} else {
			out.printf("The winner of the bet is Mario with: %d", mario);
		}
	}
	
	void start() {
		int nMario = faculteit(DIFF_INGR_MARIO);
		int kMario = faculteit(INGR_PIZZA_MARIO);
		int jMario = faculteit(DIFF_INGR_MARIO - INGR_PIZZA_MARIO);
		
		int nLuigi = faculteit(DIFF_INGR_LUIGI);
		int kLuigi = faculteit(ING_PIZZA_LUIGI);
		int jLuigi = faculteit(DIFF_INGR_LUIGI - ING_PIZZA_LUIGI);
		
		int resultMario = (int) calculate(nMario, kMario, jMario);
		int resultLuigi = (int) calculate(nLuigi, kLuigi, jLuigi);
		
		determineWinner(resultMario, resultLuigi);
	}
	

	public static void main(String[] args) {
		new Pizza().start();

	}

}
