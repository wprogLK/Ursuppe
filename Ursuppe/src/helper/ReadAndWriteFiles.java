package helper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
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
	
	
	/**
	 * read the file overviewHumanPlayers.urs
	 * @return ArrayList with all human player names
	 */
	public static ArrayList<String> readSaveHumanPlayers()
	{
		return readFile(Setting.pathSavePlayers+"overviewHumanPlayers.urs");
	}
	
	public static ArrayList<String> readSaveHumanPlayersWithComments()
	{
		return readFileWithComments(Setting.pathSavePlayers+"overviewHumanPlayers.urs");
	}

	/**
	 * read the file overviewArtificalIntelligencePlayers.urs
	 * @return ArrayList with all AI player names
	 */
	public static ArrayList<String> readSaveAIPlayers()
	{
		return readFile(Setting.pathSavePlayers+"overviewArtificalIntelligencePlayers.urs");
	}
	
	public static ArrayList<String> readSaveGames()
	{
		return readFile(Setting.pathSaveGames+"overviewSaveGames.urs");
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
	
	private static  ArrayList readFileWithComments(String fileNameWithPath)
	{
		try
		{
			ArrayList<String> arLiTMP=new ArrayList<String>();
			
			File file  = new File(fileNameWithPath);  // File to read from.
			
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

	public static  void writeFile(ArrayList<String> input, String fileNameWithPath)
	{
		String data="";
		
		File file =new File(fileNameWithPath);
		
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
			e.printStackTrace();
		}
				
	}
	

	public static void addDataHumanPlayer(String filename) 
	{
		ArrayList<String> data=readSaveHumanPlayersWithComments();
	
		data.add(filename);
		
		writeFile(data, Setting.pathSavePlayers+"overviewHumanPlayers.urs");
	}

	public static String readOneLineOfTestFile(String testFileNameWithPath) 
	{
		ArrayList<String> data=readFile(testFileNameWithPath);
		
		deleteFirstLineOfAFile(testFileNameWithPath);
		
		return data.get(0);
	}
	
	public static void deleteFirstLineOfAFile(String fileNameWithPath)
	{
		ArrayList<String> data=readFile(fileNameWithPath);
		
		data.remove(0);
		
		writeFile(data,fileNameWithPath);
	}

	public static void addDataErrorLog(ArrayList<String> newData) 
	{
		ArrayList<String> data=readErrorLogWithComments();
		
		for(String str:newData)
		{
			data.add(str);
		}
		
		writeFile(data, Setting.pathErrorLogs + Setting.fileNameErrorLogs);
	}

	private static ArrayList<String> readErrorLogWithComments() 
	{
		return readFileWithComments(Setting.pathErrorLogs + Setting.fileNameErrorLogs);
	}
	

}
