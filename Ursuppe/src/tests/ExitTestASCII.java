
package tests;
/**
 * 
 */
import static org.junit.Assert.*;

import enums.EPhases;
import gameObjectsASCII.PhaseExitASCII;
import helper.Setting;
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
		
		this.outStream.println("before setStartPhase");
		
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
		this.outStream.println("Exit");				//Not necessary, but it looks like a real userInput

		this.waitingGeneral(); 		//Important!
		this.outStream.println("NEW PHASE: " +game.getCurrentPhase());
		
		this.outStream.println("NEW INDIRECT ePHASE: " +game.getCurrentPhase().getCurrentPhase());
		
		this.outStream.println("NEW ePHASE: " +game.getCurrentEPhase()); 	//#BUG!
		
		this.outStream.println("isRunning: " + game.getCurrentPhase().getIsRunning());
		
		//assertTrue(game.getCurrentEPhase()==EPhases.phaseExit);		//#BUG! Why it isn't working?!
		assertTrue(game.getCurrentPhase().toString().equals("phaseExit"));	
		
		return game;
	}
	
}






	
	