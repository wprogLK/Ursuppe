
package tests;
/**
 * 
 */
import static org.junit.Assert.*;

import enums.EPhases;
import interfaces.IGame;
import interfaces.IPhase;
import interfaces.IPlayer;

import org.junit.Test;
import ch.unibe.jexample.JExample;
import ch.unibe.jexample.Given;


import org.junit.runner.RunWith;

import templates.ASCIITestTemplate;


@RunWith(JExample.class)
public class SimpleGameTestASCII extends ASCIITestTemplate//Thread{
{
	//private IGame game;
	
	
	
	private IPlayer playerOne;
	private IPlayer playerTwo;
	
	/**
	 * 
	 */
	@Test
	public IGame simpleGameWithOnePlayer() 
	{
		this.setPriority(10);
		
		IGame game;
		
		game=module.createGame();
		
		game.setStartPhase(EPhases.phaseA);
		
		return game;
	}
	
	@Test
	public IGame simpleGameWithTwoPlayers() 
	{
		this.setPriority(10);
		
		IGame game;
		
		game=module.createGame();
		
		game.setStartPhase(EPhases.phaseA);
		
		return game;
	}
	

	
	@Given("simpleGameWithOnePlayer")
	public IGame createANewGameWithOnePlayer(IGame game)
	{
		game.createNew();
		this.playerOne=  game.createANewPlayer();
		
		System.out.println("With one player");
		game.showPlayers();
		
		return game;
	}
	
	@Given("simpleGameWithTwoPlayers")
	public IGame createANewGameWithTwoPlayer(IGame game)
	{
		game.createNew();
		this.playerOne=  game.createANewPlayer();
		this.playerTwo=  game.createANewPlayer();
		
		System.out.println("With two player");
		game.showPlayers();
		
		return game;
	}
	
	@Given("createANewGameWithOnePlayer")
	public IGame playGameWithOnePlayer(IGame game)
	{
		game.play();
		return game;
	}
	
	@Given("createANewGameWithTwoPlayer")
	public IGame playGameWithTwoPlayer(IGame game)
	{
		game.play();
		return game;
	}
	
	@Given("playGameWithTwoPlayer")
	public IGame setNamePlayerOne(IGame game)
	{
		IPhase phaseA= game.getGameLogic().getPhaseA();
		phaseA.waitForANewInput();
		assertTrue(phaseA.setInputA("Lukas"));
		System.out.println("Lukas");				//Not necessary, but it looks like a real userInput
		
		return game;
	}
	
	@Given("setNamePlayerOne")
	public IGame namePlayerOneShouldBeLukas(IGame game)
	{
		assertTrue(this.playerOne.getName().equals("Lukas"));
		return game;
	}
	
	@Given("namePlayerOneShouldBeLukas")
	public IGame namePlayerTwoShouldBeSubjectName(IGame game)
	{
		assertTrue(this.playerTwo.getName().equals("[SubjectName]"));

		return game;
	}
	
	
	
}






	
	