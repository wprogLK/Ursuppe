/**
 * 
 */
package helper;

import java.io.File;
import java.io.PrintStream;
import java.text.DateFormat;
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
		String data=createData(e);
		
		File file = new File(path+"errorLog.txt");
	}

	private static String createData(Exception e) 
	{
		//Date:
		String strData= createData();
		
		//Exception:
		String strException=createException(e);
		
		
		
		//SystemProperties:
		String strSystemProperties=createSystemProperties();
		
		//GameProperties:
		//TODO
		return null;		//TODO
	}

	private static String createSystemProperties() 
	{
		Properties systemProp = System.getProperties();
		
		String awtToolKit= systemProp.getProperty("awt.toolkit");
		
		String javaClassPath= systemProp.getProperty("java.class.path");
		String javaClassVersion= systemProp.getProperty("java.class.version");
		
		String javaRuntimeName= systemProp.getProperty("java.runtime.name");
		String javaRuntimeVersion= systemProp.getProperty("java.runtime.version");
		
		String javaSpecificationName= systemProp.getProperty("java.specification.name");
		String javaSpecificationVendor= systemProp.getProperty("java.specification.vendor");
		String javaSpecificationVersion= systemProp.getProperty("java.specification.version");
		
		String javaVendor= systemProp.getProperty("java.vendor");
		String javaVersion= systemProp.getProperty("java.version");
		
		String javaVMSpecificationVersion= systemProp.getProperty("java.vm.specification.version");
		String javaVMVersion= systemProp.getProperty("java.vm.version");
		
		String osArch= systemProp.getProperty("os.Arch");
		String osName= systemProp.getProperty("os.name");
		
		String sunArchDataModel= systemProp.getProperty("sun.arch.data.model");
		String sunOsPatchLevel= systemProp.getProperty("sun.os.patch.level");
		
		String userCountry= systemProp.getProperty("uer.country");
		String userDir= systemProp.getProperty("user.dir");
		String userHome= systemProp.getProperty("user.home");
		String userLanguage= systemProp.getProperty("user.language");
		
		return null; //TODO
	}

	private static String createException(Exception e) 
	{
		String exceptionMessage=e.getMessage();
		String exceptionLocalizedMessage=e.getLocalizedMessage();
		String exceptionCause=e.getCause().toString();				//TODO: what is that?
		
		String strStack=createStack(e);
		
		return null;	//TODO
	}

	private static String createData() 
	{
		Date d = new Date(); 
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT ); 
		
		return df.format(d);
		
	}

	private static String createStack(Exception e) 
	{
		String str="";
		
		for(StackTraceElement item:e.getStackTrace())
		{
			str+=item.toString()+"\n";
		}
		return str;
	}
}
