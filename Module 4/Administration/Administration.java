package Administration;

import java.io.PrintStream;
import java.util.Scanner;

import ui.UIAuxiliaryMethods;

public class Administration {
	
	public final static int MAX_SIMILARITY = 20;
	public final static int MIN_SIMILARITY = 0;
	
	PrintStream out;
	
	Administration(){
		out = new PrintStream(System.out);
	}
	
	double calculateResult(String grade) {
		Scanner gradeScanner = new Scanner(grade);
		double sum = 0;
		double divider = 0;
		
		while(gradeScanner.hasNext()) {
			sum += gradeScanner.nextDouble();
			divider ++;
		}
		return(sum / divider);
	}
	
	void printResult(String grade, String studentName) {
		double result = calculateResult(grade);
		double avgGrade = (double) Math.round(result * 2) / 2;
		
		if (avgGrade >= 5.5 && avgGrade < 6.0) {
			out.printf("%s has an average of 6-", studentName);
		} else {
			if (avgGrade % 1 == 0) {
				out.printf("%s has an average of %.0f", studentName, avgGrade);
			} else {
				out.printf("%s has an average of %.1f", studentName, avgGrade);
			}
		}
		out.println();
	}
	
	String printGraph(int number) {
		if (number == MIN_SIMILARITY) {
			return("_");
		} else if (number < MAX_SIMILARITY) {
			return("-");
		} else {
			return("^");
		}
	}
	
	void getGraph(String num) {
		Scanner score = new Scanner(num);
		score.useDelimiter("=");
		
		while(score.hasNext()) {
			String sign = printGraph(score.nextInt());
			out.print(sign);
		}
		out.println();
	}
	
	void printNames(String inputName) {
		Scanner name = new Scanner(inputName);
		name.useDelimiter(",");
		
		while(name.hasNext()) {
			out.println(name.next());
		}
	}
	
	void printStudentAvg(String studentLine) {
		Scanner studentScanner = new Scanner(studentLine);
		studentScanner.useDelimiter("_");
		
		String name = studentScanner.next();
		String grades = studentScanner.next();
		
		printResult(grades, name);
	}
	
	void printSimilarity(String sim) {
		Scanner simScan = new Scanner(sim);
		simScan.useDelimiter(";");
		
		String simScore = simScan.next();
		getGraph(simScore);
		
		if(simScan.hasNext()) {
			String simName = simScan.next();
			printNames(simName);
		} else {
			out.print("No matches found\n");
		}
		out.println();
	}
	
	void start() {
		Scanner fileScanner = UIAuxiliaryMethods.askUserForInput().getScanner();
		
		while(fileScanner.hasNext()) {
			String student = fileScanner.nextLine();
			printStudentAvg(student);

			String score = fileScanner.nextLine();
			printSimilarity(score);
		}
	}

	public static void main(String[] args) {
		new Administration().start();
	}
}
