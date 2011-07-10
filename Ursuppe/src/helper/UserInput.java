package helper;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 *wait and read the input of the useres
 * 
 *<br/> only needed for modus ASCII and for cheats
 * @author Lukas
 * @version 1.0.0.1
 */
public abstract class UserInput 
{
	/**
	 * reads the input
	 * @return String, the input of the user
	 */
	public static String readInput(String message)
	{
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
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
