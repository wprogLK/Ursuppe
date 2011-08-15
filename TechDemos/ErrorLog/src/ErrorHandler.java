import java.util.Iterator;
import java.util.Properties;
import java.util.TreeSet;



public class ErrorHandler 
{
	public ErrorHandler()
	{
		 
		 
		try 
		{
			throw new IllegalArgumentException("PhysicalExam.scaleBloodPressure: age is negative");
		} 
		catch (IllegalArgumentException e) 
		{
			System.out.println("MESSAGE: " +e.getMessage());
			System.out.println("Localized Message: " +e.getLocalizedMessage());
			System.out.println("Cause: " +e.getCause());
			
			for(StackTraceElement item:e.getStackTrace())
			{
				System.out.println("Stack item: " +item);
			}
			
			//System.out.println("Version " + version)	//TODO
			//System.out.println("OS Infos: " + osInfos) //TODO
			
			 Properties pr = System.getProperties();
		        TreeSet propKeys = new TreeSet(pr.keySet());  // TreeSet sorts keys
		        for (Iterator it = propKeys.iterator(); it.hasNext(); ) {
		            String key = (String)it.next();
		           // m_propertiesTA.append("" + key + "=" + pr.get(key) + "\n");
		            System.out.println(key+" = " + pr.get(key));
		        }
		        
		        System.out.println("GET KEY: " + pr.getProperty("os.name"));
			
		}
		
	}
}
