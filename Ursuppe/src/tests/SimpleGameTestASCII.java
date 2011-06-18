
package tests;
/**
 * 
 */
import static org.junit.Assert.*;

import enums.EPhases;
import gameObjectsASCII.GameASCII;
import interfaces.IGame;
import interfaces.IPhase;
import interfaces.IPlayer;

import module.ModuleASCII;


import org.junit.Test;
import ch.unibe.jexample.JExample;
import ch.unibe.jexample.Given;


import org.junit.runner.RunWith;

import templates.ASCIITestTemplate;

import com.google.inject.Guice;
import com.google.inject.Injector;




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
	public IGame simpleGame1() 
	{
		this.setPriority(10);
		
		IGame game;
		
		game=module.createGame();
		
//		Injector injector_test = Guice.createInjector(new ModuleASCII());
//		game =injector_test.getInstance(GameASCII.class);
		
		game.setStartPhase(EPhases.phaseA);
		
		return game;
		 
		//IPlayer playerOne=game.getPlayer(0);
	}
	
	
	

	
	@Given("simpleGame1")
	public IGame createANewGameWithOnePlayer(IGame game)
	{
		game.createNew();
		this.playerOne=  game.createANewPlayer();
		return game;
	}
	
	@Given("simpleGame1")
	public IGame createANewGameWithTwoPlayer(IGame game)
	{
		game.createNew();
		this.playerOne=  game.createANewPlayer();
		this.playerTwo=  game.createANewPlayer();
		return game;
	}
	
	@Given("createANewGameWithOnePlayer")
	public IGame playGameWithOnePlayer(IGame game)
	{
		//game.play();
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
	public IGame namePlayerTwoShouldBeNull(IGame game)
	{
		assertTrue(this.playerTwo.getName()==null);

		return game;
	}
	
	
	
}






	
	