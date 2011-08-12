package main;

import helper.SaveAndLoad;
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
		 
		 IPlayer player=game.createANewPlayer();
		 SaveAndLoad.savePlayer(player, "testPlayer",EToken.HU);
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
