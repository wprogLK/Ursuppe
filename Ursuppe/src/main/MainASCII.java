package main;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;

import helper.Setting;
import interfaces.IAmoeba;
import interfaces.IBoard;
import interfaces.IGame;
import interfaces.IModule;
import interfaces.IPlayer;
import enums.EColor;
import enums.EPhases;
import gameObjectsASCII.GameASCII;

import module.*;

import annotations.OnlyForTesting;

/**
 * runs the whole Ursuppe game in the ASCII Mode
 * 
 * @author Lukas Keller
 * @version 1.0.0
 *
 */
public class MainASCII {
	
	
	private static IGame game;
	
	private static PrintStream outStream=Setting.asciiOut;
	private static PrintStream errorStream=Setting.asciiErr;
	
	private static IModule module=new ModuleASCII(outStream, errorStream);
	
	
	/**
	 * runs the game
	 * <p>
	 * creates and starts the game ({@link GameASCII}) in ASCII mode 
	 * </p>
	 * 
	 * @param args
	 */
	public static void main(String[] args) 
	{
		 game=module.createGame();
		 
		 game.setStartPhase(EPhases.phaseNewGame);
		 
		 game.createNew();
		 
		 game.play();
		 
		 try 
		 {
			game.join();
		 } 
		 catch (InterruptedException e) 
		 {
			e.printStackTrace();
		 }
	}
	
}
