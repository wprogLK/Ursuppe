package helper;


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
 * @version 1.0.0.1
 */
public abstract class UserInput extends Thread
{
	private static PrintStream outStream;
	private static PrintStream errStream;
	
	private static Boolean testingModeOn=false;
	private static String testFileName;
	
	private static Boolean stop=true;
	
	public static void turnStopOn()
	{
		stop=true;
	}
	
	public static void turnStopOff()
	{
		stop=false;
	}
	
	public static void setOutStream(PrintStream out)
	{
		outStream=out;
	}
	
	public static void setErrorStream(PrintStream error)
	{
		errStream=error;
	}
	
	public static void turnOnTestingMode()
	{
		testingModeOn=true;
	}
	
	public static void turnOffTestingMode()
	{
		testingModeOn=false;
	}
	
	public static void setTestingFileName(String fileName)
	{
		testFileName=fileName;
	}
	
	/**
	 * reads the input
	 * @return String, the input of the user
	 */
	public static String readInput(String message)
	{
		if (!testingModeOn)	//normal mode
		{
			return realUserInput(message);
		}
		else 				//testing mode
		{
			return fakeUserInput(message);
		}
	}

	private static String fakeUserInput(String message) 
	{	
		/*
		 * TODO:
		 * (1) implement a start and stop mechanism
		 * (2) stop when file is empty
		 * 
		 * 
		 */
//		while(stop)
//		{
//			doSleep();
//		}
		
		return ReadAndWriteFiles.readOneLineOfTestFile(testFileName);
	}

	private static String realUserInput(String message) 
	{
		//System.out.println("::::::::TESTING MODE OFF:::::::::");  //TODO delete
		
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in)); //TODO Maybe use another InputStream than System.in (make it more flexible)
		String line = null;
		
		outStream.println(message);	
		
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
	
	/**
	 * sleeps 100 milliseconds
	 * 
	 * <p>
	 * used for in each while loop for check other things and listen to other objects
	 * </p>
	 * 
	 */
	public final static void doSleep()
	{
		try 
		{
			Thread.sleep(100);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	

}
