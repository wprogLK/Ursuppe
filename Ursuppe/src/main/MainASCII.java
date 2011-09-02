package main;

import java.io.PrintStream;
import java.util.Date;

import helper.Setting;
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
	
		// IBoard b=module.createBoard();

		 game=module.createGame();
		 
		 game.setStartPhase(EPhases.phasePreparation1);
		 
		 game.createNew();
//		 String name, Date birthday, int age, EColor color
		 
		 IPlayer player1=game.createANewPlayer("Player 1", null,0,EColor.Blue);						//TODO delete
		 IPlayer player2=game.createANewPlayer("Player 2", null,0,EColor.Red);						//TODO delete
//		 IPlayer player3=game.createANewPlayer("Player 3", null,0,EColor.Yellow);						//TODO delete
//		 IPlayer player4=game.createANewPlayer("Player 4", null,0,EColor.Gray);						//TODO delete
//		 IPlayer player5=game.createANewPlayer("Player 4", null,0,EColor.Green);						//TODO delete
//		 IPlayer player6=game.createANewPlayer("Player 4", null,0,EColor.Pink);						//TODO delete
//		 IPlayer player7=game.createANewPlayer("Player 4", null,0,EColor.Black);						//TODO delete
//		 IPlayer player3=game.createANewPlayer();						//TODO delete
//		 IPlayer player4=game.createANewPlayer();						//TODO delete
//		 IPlayer player5=game.createANewPlayer();						//TODO delete
//		 IPlayer player6=game.createANewPlayer();						//TODO delete
//		 IPlayer player7=game.createANewPlayer();						//TODO delete
		 
//		 player1.setName("P1");
//		 player2.setName("P2");
//		 player3.setName("P3");
//		 player4.setName("P4");
//		 player5.setName("P5");
//		 player6.setName("P6");
//		 player7.setName("P7");
		// SaveAndLoad.saveHumanPlayer(player, "test3Player",EToken.HU);	//TODO delete

		 game.play();
		// System.out.println("BOARD \n" + b.toString());
		 
		// b.testAddAmeba();
		 
		// System.out.println("BOARD \n" + b.toString());
		 
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
