package main;

import helper.Setting;
import interfaces.IGame;
import interfaces.IModule;
import interfaces.IPlayer;

import enums.EPhases;
import gameObjectsGUI.GameGUI;

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
public class MainGUI {
	
	
	private static IGame game;
	private static IModule module =new ModuleGUI(Setting.asciiOut, Setting.asciiErr);
	
	
	/**
	 * runs the game with the support of Google Guice.
	 * <p>
	 * creates and starts the game ({@link GameGUI}) in GUI mode 
	 * </p>
	 * 
	 * 
	 * @param args
	 */
	public static void main(String[] args) 
	{
		game=module.createGame();
		
		 game.setStartPhase(EPhases.phaseA);
		
		 game.createNew();
		 
		 game.createANewPlayer();
		 
		 game.play();
		 
		 IPlayer playerOne=game.getPlayer(0);
		 
		 System.out.println("Player One: \n \t - Name: " + playerOne.getName() +" \n \t - Age: " + playerOne.getAge()); //TODO delete
	}

	
	@OnlyForTesting
	public IGame getGame()
	{
		return game;
	}
}
