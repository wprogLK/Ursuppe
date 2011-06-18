package helper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class SaveAndLoad {
	
	public static void saveObject(Object obj, String filename)
	{
	      
		try
	    {
	      FileOutputStream file = new FileOutputStream( filename );
	      ObjectOutputStream o = new ObjectOutputStream( file );
	   
	      System.out.println("Save object "+ obj.toString());
	      o.writeObject(obj);
	      o.close();
	    }
	    catch ( IOException e ) { System.err.println( e + " Error in saveObject " + filename); }

		
	}
	
	public static Object loadObject(String filename)
	{
		Object obj=null;
		
			try
		    {
		      FileInputStream file = new FileInputStream( filename );
		      ObjectInputStream o = new ObjectInputStream( file );
		      obj =o.readObject();
		      o.close();
		    }
		    catch ( IOException e ) { System.err.println( e ); }
		    catch ( ClassNotFoundException e ) { System.err.println( e + " Error in loadObject"); }

		
		
		
		return obj;
	}
}
