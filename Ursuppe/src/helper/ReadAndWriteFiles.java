package helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is for read and write files.
 * @author Lukas Keller
 * @version 1.0.0
 *
 */
public abstract class ReadAndWriteFiles 
{
	private static String pathSavePlayers="data/saveplayers";
	private static String pathSaveGames="data/savegames";
	
	
	/**
	 * read the file overviewPlayers.urs
	 */
	public static ArrayList<String> readSavePlayers()
	{
		return readFile(pathSavePlayers+"/overviewPlayers.urs");
	}
	
	public static ArrayList<String> readSaveGames()
	{
		return readFile(pathSavePlayers+"/overviewSaveGames.urs");
	}
	
	
	private static  ArrayList readFile(String fileName)
	{
		try
		{
			ArrayList<String> arLiTMP=new ArrayList<String>();
			
			File file  = new File(fileName);  // File to read from.
			
			Scanner fileReader = new Scanner(file);
			
			String line=null;
			
			while (fileReader.hasNextLine()) 
			{
				line = fileReader.nextLine();
	
				if ( !line.isEmpty())
				{
					if (line.charAt(0)!='#')
					{
						arLiTMP.add(line);
					}
				}
				
			}
		
			fileReader.close(); 
			
			return arLiTMP;
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			return new ArrayList();
		}
		
		
		
	}
	

}
