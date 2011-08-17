package main;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Date;

import helper.SaveAndLoad;
import helper.Setting;
import interfaces.IGame;
import interfaces.IModule;
import interfaces.IPlayer;
import enums.EPhases;
import enums.EToken;
import gameObjectsASCII.GameASCII;

import module.*;

import annotations.OnlyForTesting;

/**
 * runs the whole Ursuppe game
 * 
 * @author Lukas Keller
 * @version 1.0.0
 *
 */
public class MainASCII {
	
	
	private static IGame game;
	
	private static PrintStream outStream=Setting.asciiOut;
	private static PrintStream errorStream=Setting.asciiOut;
	
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
		 
		 game.setStartPhase(EPhases.phaseSplashScreen);
		 
		 game.createNew();
		 Date now = new Date();	//TODO delete
		 
		
		 IPlayer player=game.createANewPlayer();						//TODO delete
		 SaveAndLoad.saveHumanPlayer(player, "test3Player",EToken.HU);	//TODO delete
		
		 game.play();
		 
		 try 
		 {
			game.join();
		 } 
		 catch (InterruptedException e) 
		 {
			e.printStackTrace();
		 }
		 System.out.println("MAIN END...");	//TODO delete 
		 
		
	}

	
	
	@OnlyForTesting
	public IGame getGame()
	{
		return game;
	}
}
