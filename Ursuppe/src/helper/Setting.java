package helper;

import java.io.PrintStream;
import java.util.ArrayList;

import enums.EColor;

/**
 * 
 * @author Lukas Keller
 * @version 1.0.0
 */
public abstract class Setting 
{
	public static String pathSavePlayers="data/saveplayers/";
	public static String pathSaveGames="data/savegames/";
	public static String pathErrorLogs="data/errorLogs/";
	public static String pathTestFiles="data/testing/";
	
	public static String fileNameErrorLogs="errorLog.urs";
	
	
	public static PrintStream asciiOut=System.out;
	public static PrintStream asciiErr=System.err;
	
	
	public static PrintStream guiOut=System.out;
	public static PrintStream guiErr=System.err;
	
	public static PrintStream testOut=System.out;	//TODO: change back to: new PrintStream(new NullOutputStream())
	public static PrintStream testErr=new PrintStream(new NullOutputStream());//TODO: change back to(?) :System.err;
	
	public static ArrayList<EColor> usedColors=new ArrayList<EColor>();
	
	
	public static int maxNumbersOfAmoebasPerPlayer=7;
	
	public static int numberOfFirstAmoebasOnBoardPerPlayer=2;	//Numbers of amoebas which each player can set on the board in the first round
	
}
