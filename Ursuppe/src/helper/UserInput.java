package helper;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class UserInput {
	public static String readInput(String instruction)
	{
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(instruction);
		String zeile = null;
		try {
			zeile = console.readLine();
		} catch (IOException e) {
			// Sollte eigentlich nie passieren
			e.printStackTrace();
		}
		
		return zeile;
	}
	

}
