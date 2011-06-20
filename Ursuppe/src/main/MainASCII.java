package main;

import helper.SaveAndLoad;
import interfaces.IGame;
import interfaces.IModule;
import enums.EPhases;
import gameObjectsASCII.GameASCII;

import module.*;

import annotations.OnlyForTesting;

import com.google.inject.Guice;
import com.google.inject.Injector;
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
		 //Injector injector_test = Guice.createInjector(new ModuleASCII());
		 //game =injector_test.getInstance(GameASCII.class);
	
		 game=module.createGame();
		
		 game.setStartPhase(EPhases.phaseA);
		 
		 game.createNew();
//		 try 
//		 {
//			game.join();
//		 } 
//		 catch (InterruptedException e) 
//		 {
//			e.printStackTrace();
//		 }
		 game.createANewPlayer();
		 SaveAndLoad.saveObject(game, "currentGame.urs");
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
