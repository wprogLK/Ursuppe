/**
 * 
 */
package helper;

import java.io.File;
import java.io.PrintStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

/**
 * @author Lukas
 *
 */
public abstract class ErrorLogger 
{
	private static String filename="errorLogs.txt";
	private static String path=Setting.pathErrorLogs;
	
	private static PrintStream outStream;
	private static PrintStream errStream;
	
	public static void setOutStream(PrintStream out)
	{
		outStream=out;
	}
	
	public static void setErrorStream(PrintStream error)
	{
		errStream=error;
	}
	
	public static void logAError(Exception e)
	{
		ArrayList<String> data=createData(e);
		
		File file = new File(path+"errorLog.txt");

		ReadAndWriteFiles.addDataErrorLog(data);
	}

	private static ArrayList<String> createData(Exception e) 
	{
		ArrayList<String> arrAll=new ArrayList<String>();
		
		//Date:
		String arrData= createData();
		arrAll.add("DATE: ");
		arrAll.add(arrData);
		
			
		
		//Exception:
		ArrayList<String> arrException=createException(e);
		for(String str:arrException)
		{
			arrAll.add(str);
		}
		
		
		//SystemProperties:
		ArrayList<String> arrSystemProp=createSystemProperties();
		for(String str:arrSystemProp)
		{
			arrAll.add(str);
		}
		
		
		//GameProperties:
		//TODO
		return arrAll;
	}

	private static ArrayList<String> createSystemProperties() 
	{
		Properties systemProp = System.getProperties();
		ArrayList<String> list=new ArrayList<String>();
		
		list.add("SYSTEM PROPERTIES: \n");
		
		list.add("Awt tool kit: "+ systemProp.getProperty("awt.toolkit")); 
		
		list.add("\n");
		
		list.add("Java class path: " + systemProp.getProperty("java.class.path")); 
		list.add("Java class version: " + systemProp.getProperty("java.class.version")); 

		list.add("\n");
		
		list.add("Java runtime name: " + systemProp.getProperty("java.runtime.name")); 
		list.add("Java runtime version: " + systemProp.getProperty("java.runtime.version")); 

		list.add("\n");
		
		list.add("Java specification name: " + systemProp.getProperty("java.specification.name")); 
		list.add("Java specification vendor: " + systemProp.getProperty("java.specification.vendor")); 
		list.add("Java specification version: " + systemProp.getProperty("java.specification.version")); 

		list.add("\n");
		
		list.add("Java vendor: " + systemProp.getProperty("java.vendor")); 
		list.add("Java version: " + systemProp.getProperty("java.version")); 

		list.add("\n");
		
		list.add("Java VM specification version: " + systemProp.getProperty("java.vm.specification.version")); 
		list.add("Java VM version: " + systemProp.getProperty("java.vm.version")); 

		list.add("\n");
		
		list.add("OS arch: " + systemProp.getProperty("os.arch")); 
		list.add("OS name: " + systemProp.getProperty("os.name")); 

		list.add("\n");
		
		list.add("Sun arch data model: " + systemProp.getProperty("sun.arch.data.model")); 
		list.add("Sun os patch level: " + systemProp.getProperty("sun.os.patch.level")); 

		list.add("\n");
		
		list.add("User country: " + systemProp.getProperty("user.country")); 
		list.add("User dir: " + systemProp.getProperty("user.dir")); 
		list.add("User home: " + systemProp.getProperty("user.home")); 
		list.add("User language: "+ systemProp.getProperty("user.language"));		
		
		
		
		return list;
	}

	private static ArrayList<String> createException(Exception e) 
	{
		ArrayList<String> list=new ArrayList<String>();
		list.add("EXCEPTION: \n");
		
		String exceptionMessage="Message: " + e.getMessage();
		list.add(exceptionMessage);
		String exceptionLocalizedMessage="Localized message: " + e.getLocalizedMessage();
		list.add(exceptionLocalizedMessage);
//		String exceptionCause="Cause: " + e.getCause().toString();				//TODO: what is that?
//		list.add(exceptionCause);
		ArrayList<String> arrStack= createStack(e);
		for(String str:arrStack)
		{
			list.add(str);
		}
		
		
		return list;
	}

	private static String createData() 
	{
		Date d = new Date(); 
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT ); 
		
		return df.format(d) +"\n";
	}

	private static ArrayList<String> createStack(Exception e) 
	{
		ArrayList<String> arrStack=new ArrayList<String>();
		arrStack.add("Stack: \n\t");
		
		
		for(StackTraceElement item:e.getStackTrace())
		{
			arrStack.add(item.toString() + "\n\t");
		}
		return arrStack;
	}
}
