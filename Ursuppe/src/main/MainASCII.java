package main;

import helper.SaveAndLoad;
import interfaces.IGame;
import interfaces.IModule;
import enums.EPhases;
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
	private static IModule module=new ModuleASCII();
	
	
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
		 
		 game.createANewPlayer();
		 //SaveAndLoad.saveObject(game, "data/savegames/currentGame.urs");
		 game.play();
		 
		 try 
		 {
			game.join();
		 } 
		 catch (InterruptedException e) 
		 {
			e.printStackTrace();
		 }
		 System.out.println("MAIN END...");
		 
		
	}

	
	
	@OnlyForTesting
	public IGame getGame()
	{
		return game;
	}
}
