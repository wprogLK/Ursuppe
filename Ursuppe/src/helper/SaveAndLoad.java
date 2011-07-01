package helper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*
 * TODO: instead of save & load any object use an empty interface called IUrsuppeObject and save & load this!
 */

/**
 * is the hole save & load system for the game.
 * 
 *<br/> works with streams and every object must be seralizable!
 * @author Lukas
 * @version 1.0.0.1
 */
public abstract class SaveAndLoad {
	
	public static void saveObject(Object obj, String filename)
	{
	      
		try
	    {
	      FileOutputStream file = new FileOutputStream( filename );
	      ObjectOutputStream o = new ObjectOutputStream( file );
	   
	      o.writeObject(obj);
	      o.close();
	    }
	    catch ( IOException e ) 
	    { 
	    	System.err.println( e + " Error in saveObject " + filename); 
	    }

		
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
		catch ( IOException e ) 
		{
			System.err.println( e ); 
		}
		catch ( ClassNotFoundException e ) 
		{ 
			System.err.println( e + " Error in loadObject"); 
		}

		return obj;
	}
}
