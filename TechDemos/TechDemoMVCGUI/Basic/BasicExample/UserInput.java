


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 *wait and read the input of the useres
 * 
 *<br/> only needed for modus ASCII and for cheats
 * @author Lukas
 * @version 1.0.0
 */
public abstract class UserInput
{
	
	

	public static String realUserInput(String message) 
	{
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in)); //TODO Maybe use another InputStream than System.in (make it more flexible)
		String line = null;
		
		System.out.println(message);	
		
		try 
		{
			line = console.readLine();
		} 
		catch (IOException e) 
		{
			//Should never happen
			e.printStackTrace();
		}
		
		return line;
	}
	
	

}
