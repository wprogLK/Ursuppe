package helpers;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import annotations.OnlyForTesting;

import enums.EMode;
/**
 *wait and read the input of the useres
 * 
 *<br/> only needed for modus ASCII and for cheats
 * @author Lukas
 * @version 1.0.0
 */
public abstract class UserInput extends Thread
{
	private static PrintStream outStream;
	private static PrintStream errStream;
	
	private static EMode runningMode;
	private static String testFileName;
	
	private static boolean newInput=false;
	private static String input;
	
	/**
	 * set the new input
	 * used for gui
	 */
	public static void setNewInput(String in)
	{
		input=in;
		newInput=true;
	}
	
	public static void setOutStream(PrintStream out)
	{
		outStream=out;
	}
	
	public static void setErrorStream(PrintStream error)
	{
		errStream=error;
	}
	
	public static void turnOnTestMode()
	{
		runningMode=EMode.testMode;
	}
	
	public static void turnOnGUIMode()
	{
		runningMode=EMode.guiMode;
	}
	
	public static void turnOnASCIIMode()
	{
		runningMode=EMode.asciiMode;
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
		switch(runningMode)
		{
			case asciiMode:
			{
				return realUserInput(message);
			}
			case testMode:
			{
				return fakeUserInput(message);
			}
			case guiMode:
			{
				return null;	//TODO
			}
			default:
			{
				return null;
				//TODO: throw an exception
			}
		}
	}

	private static String fakeUserInput(String message) 
	{	
		System.out.println("fakeUserInput");
		
		String output=ReadAndWriteFiles.readOneLineOfTestFile(testFileName);
		
		return output;
	}

	private static String realUserInput(String message) 
	{
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
