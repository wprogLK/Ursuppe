package helper;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
/**
 *wait and read the input of the useres
 * 
 *<br/> only needed for modus ASCII and for cheats
 * @author Lukas
 * @version 1.0.0.1
 */
public abstract class UserInput 
{
	private static PrintStream outStream;
	private static PrintStream errStream;
	private static InputStream inStream;
	
	public static void setOutStream(PrintStream out)
	{
		outStream=out;
	}
	
	public static void setErrorStream(PrintStream error)
	{
		errStream=error;
	}
	
	public static void setErrorStream(InputStream in)
	{
		inStream=in;
	
	}
	
	/**
	 * reads the input
	 * @return String, the input of the user
	 */
	public static String readInput(String message)
	{
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));	//TODO use inStream instead of System.in
		String line = null;
		//outStream.println(message);	//TODO
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
