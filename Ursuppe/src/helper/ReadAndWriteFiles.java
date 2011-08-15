package helper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is for read and write text files.
 * @author Lukas Keller
 * @version 1.0.0
 *
 */
public abstract class ReadAndWriteFiles 
{
	
	
	
	/**
	 * read the file overviewHumanPlayers.urs
	 * @return ArrayList with all human player names
	 */
	public static ArrayList<String> readSaveHumanPlayers()
	{
		return readFile(Setting.pathSavePlayers+"/overviewHumanPlayers.urs");
	}
	
	public static ArrayList<String> readSaveHumanPlayersWithComments()
	{
		return readFileWithComments(Setting.pathSavePlayers+"/overviewHumanPlayers.urs");
	}

	/**
	 * read the file overviewArtificalIntelligencePlayers.urs
	 * @return ArrayList with all AI player names
	 */
	public static ArrayList<String> readSaveAIPlayers()
	{
		return readFile(Setting.pathSavePlayers+"/overviewArtificalIntelligencePlayers.urs");
	}
	
	public static ArrayList<String> readSaveGames()
	{
		return readFile(Setting.pathSaveGames+"/overviewSaveGames.urs");
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
	
	private static  ArrayList readFileWithComments(String fileName)
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
						arLiTMP.add(line);
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

	private static  void writeFile(ArrayList<String> input, String fileName)
	{
		String data="";
		
		System.out.println("WRITE FILE fileName" + fileName + " \n Data: " + data);
		
		File file  = new File(Setting.pathSavePlayers+"/overviewHumanPlayers.urs");
		
		BufferedWriter writer;
		try 
		{
			writer = new BufferedWriter(new FileWriter(file));
			
			for (String strLine:input)
			{
				writer.write(strLine);
				writer.newLine();
			}
			
			
			writer.close();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
				
	}

	public static void addDataHumanPlayer(String filename) 
	{
		
		ArrayList<String> data=readSaveHumanPlayersWithComments();
	
		data.add(filename);
		System.out.println("ADD DATA" + data);
		writeFile(data, filename);		//TODO: Check
	}
	

}
