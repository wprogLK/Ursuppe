
package tests;
/**
 * 
 */
import static org.junit.Assert.*;

import java.util.ArrayList;

import enums.EPhases;
import helper.ReadAndWriteFiles;
import helper.Setting;
import helper.UserInput;
import interfaces.IBoard;
import interfaces.IGame;
import interfaces.IPhase;
import interfaces.IPlayer;

import org.junit.Test;
import ch.unibe.jexample.JExample;
import ch.unibe.jexample.Given;


import org.junit.runner.RunWith;

import templates.ASCIITestTemplate;
import tests.hamcrest.CheckEPhases;

@RunWith(JExample.class)
public class SquaresOnBoardTest extends ASCIITestTemplate
{	
	private IPlayer playerOne;
	private IPlayer playerTwo;
	
	private String fileNameWithPathSimpleGameTest;
	
	
	/**
	 * 
	 */
	@Test
	public IBoard simpleSquaresTest() 
	{
		this.setPriority(10);
		
		IGame game;
		
		game=module.createGame();
		
		game.setStartPhase(EPhases.phaseSplashScreen);
		
		game.createNew();
		
		game.turnOnTestMode();
		
		game.play();
		
		this.waitingGeneral(); 
		
		game.turnOnCurrentPhaseWaiting();
	
		
		return game.getBoard();
		
	}
	

	@Given("simpleSquaresTest")
	public IBoard testLine1(IBoard board)
	{
		assertFalse(board.testExistSoupSquare(1, 1));
		assertTrue(board.testExistSoupSquare(2, 1));
		assertTrue(board.testExistSoupSquare(3, 1));
		assertTrue(board.testExistSoupSquare(4, 1));
		assertTrue(board.testExistSoupSquare(5, 1));
		
		return board;
	}
	
	
	@Given("testLine1")
	public IBoard testLine2(IBoard board)
	{
		assertTrue(board.testExistSoupSquare(1, 2));
		assertTrue(board.testExistSoupSquare(2, 2));
		assertTrue(board.testExistSoupSquare(3, 2));
		assertTrue(board.testExistSoupSquare(4, 2));
		assertTrue(board.testExistSoupSquare(5, 2));
		
		return board;
	}
	
	@Given("testLine2")
	public IBoard testLine3(IBoard board)
	{
		assertTrue(board.testExistSoupSquare(1, 3));
		assertTrue(board.testExistSoupSquare(2, 3));
		assertFalse(board.testExistSoupSquare(3, 3));
		assertTrue(board.testExistSoupSquare(4, 3));
		assertTrue(board.testExistSoupSquare(5, 3));
		
		return board;
	}
	
	@Given("testLine3")
	public IBoard testLine4(IBoard board)
	{
		assertTrue(board.testExistSoupSquare(1, 4));
		assertTrue(board.testExistSoupSquare(2, 4));
		assertTrue(board.testExistSoupSquare(3, 4));
		assertTrue(board.testExistSoupSquare(4, 4));
		assertTrue(board.testExistSoupSquare(5, 4));
		
		return board;
	}
	
	@Given("testLine4")
	public IBoard testLine5(IBoard board)
	{
		assertFalse(board.testExistSoupSquare(1, 5));
		assertFalse(board.testExistSoupSquare(2, 5));
		assertFalse(board.testExistSoupSquare(3, 5));
		assertTrue(board.testExistSoupSquare(4, 5));
		assertFalse(board.testExistSoupSquare(5, 5));
		
		return board;
	}

	

	
	
	
}






	
	