package helper;

import java.io.PrintStream;
import java.util.ArrayList;

import enums.EColor;

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
	
	public static PrintStream testOut=System.out;//new PrintStream(new NullOutputStream());//System.out;
	public static PrintStream testErr=new PrintStream(new NullOutputStream());//System.err;
	
	public static ArrayList<EColor> usedColors=new ArrayList<EColor>();
	
	
	
}
