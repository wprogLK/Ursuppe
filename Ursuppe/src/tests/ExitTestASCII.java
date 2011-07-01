
package tests;
/**
 * 
 */
import static org.junit.Assert.*;

import enums.EPhases;
import gameObjectsASCII.PhaseExitASCII;
import interfaces.IGame;
import interfaces.IPhase;
import interfaces.IPlayer;

import org.junit.Test;
import ch.unibe.jexample.JExample;
import ch.unibe.jexample.Given;


import org.junit.runner.RunWith;

import templates.ASCIITestTemplate;


@RunWith(JExample.class)
public class ExitTestASCII extends ASCIITestTemplate//Thread{
{	
	private IPlayer playerOne;
	private IPlayer playerTwo;
	
	/**
	 * 
	 */
	@Test
	public IGame simpleGame() 
	{
		this.setPriority(10);
		
		IGame game;
		
		game=module.createGame();
		
		System.out.println("before setStartPhase");
		
		game.setStartPhase(EPhases.phaseA);

		return game;
	}
	
	@Given("simpleGame")
	public IGame createANewGame(IGame game)
	{
		game.createNew();
		this.playerOne=  game.createANewPlayer();
		
		

		return game;
	}
	
	
	@Given("createANewGame")
	public IGame playGame(IGame game)
	{
		game.play();
		
		return game;
	}
	
	@Given("playGame")
	public IGame phaseShouldBeA(IGame game)
	{
		this.waitingGeneral(); 		//Important!
		assertTrue(game.getCurrentEPhase()==EPhases.phaseA);
		
		return game;
	}
	
	@Given("phaseShouldBeA")
	public IGame phaseShouldBeExit(IGame game)
	{
		this.waitingGeneral(); 		//Important!
		
		IPhase phaseA=game.getCurrentPhase();
		phaseA.waitForANewInput();
		
		//phaseA.setInputA("exit");
		phaseA.setAllInput("Exit");
		System.out.println("Exit");				//Not necessary, but it looks like a real userInput

		this.waitingGeneral(); 		//Important!
		System.out.println("NEW PHASE: " +game.getCurrentPhase());
		
		System.out.println("NEW INDIRECT ePHASE: " +game.getCurrentPhase().getCurrentPhase());
		
		System.out.println("NEW ePHASE: " +game.getCurrentEPhase());  //TODO!!!
		
		System.out.println("isRunning: " + game.getCurrentPhase().getIsRunning());
		
		assertTrue(game.getCurrentEPhase()==EPhases.phaseExit);
		
		return game;
	}
	
}






	
	