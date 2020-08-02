package Replay1;

import java.io.*;
import java.util.Scanner;

import ui.OthelloReplayUserInterface;
import ui.UIAuxiliaryMethods;
import ui.UserInterfaceFactory;

public class Replay1 {
	
	public static final int NUM_CHAR_A = 97;
	
	OthelloReplayUserInterface ui;
	
	Replay1(){
		ui = UserInterfaceFactory.getOthelloReplayUI();
	}
	
	int returnColour(String colour) {
		if (colour.equals("white")) {
			return(ui.WHITE);
		}
		return(ui.BLACK);
	}
	
	boolean canMove(String move) {
		if (move.equals("move")) {
			return(true);
		}
		return(false);
	}
	
	void makeTurn(int column, int row, int colour, int waitingTime) {
		ui.place(column, row, colour);
		ui.showChanges();
		ui.wait(waitingTime);
	}
	
	void startOthello() {
		makeTurn(3, 3, ui.WHITE, 0);
		makeTurn(4, 4, ui.WHITE, 0);
		makeTurn(3, 4, ui.BLACK, 0);
		makeTurn(4, 3, ui.BLACK, 0);
	}
	
	void playGame(String scanner) {
		Scanner gameScanner = new Scanner(scanner);
		int colour = returnColour(gameScanner.next());
		int waitingTime = gameScanner.nextInt();
		String move = gameScanner.next();
		
		if (canMove(move)) {
			String letter = gameScanner.next();
			int column = letter.charAt(0) - NUM_CHAR_A;
			int row = gameScanner.nextInt() - 1;
			
			makeTurn(column, row, colour, waitingTime);
		}
	}
	
	void start() throws FileNotFoundException {
		// System.out.println("dejwdnqwq ");
		File file = new File("/Users/Simon/eclipse-workspace-2/ItP/Module 3/Replay1Input.txt");
		Scanner game = new Scanner(file);
		
		// Scanner file = UIAuxiliaryMethods.askUserForInput().getScanner();
		
		startOthello();
		
		while(game.hasNextLine()) {
			String fileLine = game.nextLine();
			playGame(fileLine);
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		new Replay1().start();
	}
}
