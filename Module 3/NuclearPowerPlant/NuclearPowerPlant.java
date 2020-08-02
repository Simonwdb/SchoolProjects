package NuclearPowerPlant;

import java.io.PrintStream;

public class NuclearPowerPlant {
	
	PrintStream out;
	
	NuclearPowerPlant(){
		out = new PrintStream(System.out);
	}
	
	void makeWarning(){
		for (int i = 0; i < 3; i++){
			out.println("NUCLEAR CORE UNSTABLE!!!");
			out.println("Quarantine is in effect.");
			out.println("Surrounding hamlets will be evacuated.");
			out.println("Anti-radiationsuits and iodine pills are mandatory");
			out.println();
		}
	}

	public static void main(String[] args) {
		new NuclearPowerPlant().makeWarning();

	}

}