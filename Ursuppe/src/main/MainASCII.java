package main;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.util.Date;

import helper.ErrorLogger;
import helper.SaveAndLoad;
import helper.Setting;
import interfaces.IBoard;
import interfaces.IGame;
import interfaces.IModule;
import interfaces.IPlayer;
import interfaces.ISquare;
import enums.EColor;
import enums.EPhases;
import enums.EToken;
import gameObjectsASCII.GameASCII;

import logics.BoardLogic;
import logics.SoupSquareLogic;
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
		 IBoard b=module.createBoard();
		
		 
		 System.out.println("BOARD \n" + b.toString());
		 
		 game=module.createGame();
		 
		 game.setStartPhase(EPhases.phasePreparation1);
		 
		 game.createNew();

		 IPlayer player1=game.createANewPlayer();						//TODO delete
		 IPlayer player2=game.createANewPlayer();						//TODO delete
		 IPlayer player3=game.createANewPlayer();						//TODO delete
		 IPlayer player4=game.createANewPlayer();						//TODO delete
		 IPlayer player5=game.createANewPlayer();						//TODO delete
		 IPlayer player6=game.createANewPlayer();						//TODO delete
		 IPlayer player7=game.createANewPlayer();						//TODO delete
		 
		 player1.setName("P1");
		 player2.setName("P2");
		 player3.setName("P3");
		 player4.setName("P4");
		 player5.setName("P5");
		 player6.setName("P6");
		 player7.setName("P7");
		// SaveAndLoad.saveHumanPlayer(player, "test3Player",EToken.HU);	//TODO delete

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
