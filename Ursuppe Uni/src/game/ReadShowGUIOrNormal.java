package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class ReadShowGUIOrNormal {
	public static boolean read()
	{
		 File inFile  = new File("data/gui.ini");  // File to read from.

		  Scanner freader;

		  try 
		  {
			  freader = new Scanner(inFile);
				
			  String output="";
		      String line = null;
		      while (freader.hasNextLine()) {
		            line = freader.nextLine();
		            
		            if ( !line.isEmpty())
		            {
		            	  if (line.charAt(0)!='#')
		      		      {
		      	            	output=output +"\n " + line;
		      		      }
		            }
		          
		        }
		        

		        freader.close();  // Close to unlock.
		        
		        if (Integer.parseInt(line) ==1)
		        {
		        	return true;
		        }
		        else
		        {
		        	return false;
		        }

		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		return true;
		
	}
}
