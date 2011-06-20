
package tests;
/**
 * 
 */
import static org.junit.Assert.*;

import enums.EPhases;
import gameObjectsASCII.GameASCII;
import gameObjectsGUI.GameGUI;
import interfaces.IGame;
import interfaces.IPhase;
import interfaces.IPlayer;

import module.ModuleASCII;
import module.ModuleGUI;

import org.junit.Test;
import ch.unibe.jexample.JExample;
import ch.unibe.jexample.Given;


import org.junit.runner.RunWith;

import templates.GUITestTemplate;

import com.google.inject.Guice;
import com.google.inject.Injector;




@RunWith(JExample.class)
public class SimpleGameTestGUI extends GUITestTemplate{
	
	
	//private IGame game;
	
	private IPlayer playerOne;
	private IPlayer playerTwo;
	
	/**
	 * 
	 */
	@Test
	public IGame simpleGameForOnePlayer() 
	{
		this.setPriority(10);
		
		IGame game;
		
		game=module.createGame();
		
//		Injector injector_test = Guice.createInjector(new ModuleGUI());
//		game =injector_test.getInstance(GameGUI.class);
		
		game.setStartPhase(EPhases.phaseA);
		
		return game;
	}
	
	@Test
	public IGame simpleGameForTwoPlayer() 
	{
		this.setPriority(10);
		
		IGame game;
		
		game=module.createGame();
		
		game.setStartPhase(EPhases.phaseA);
		
		return game;
	}
	

	@Given("simpleGameForOnePlayer")
	public IGame createANewGameWithOnePlayer(IGame game)
	{
		game.createNew();
		this.playerOne=  game.createANewPlayer();
		return game;
	}
	
	@Given("simpleGameForTwoPlayer")
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
		
		phaseA.fakeInputA("Lukas");
		this.waitingBetweenTwoInputs();
		
		phaseA.fakeClickOK();
		
		return game;
	}
	
	@Given("setNamePlayerOne")
	public IGame namePlayerOneShouldBeLukas(IGame game)
	{
		System.out.println("NAME PLAYER ONE: " + this.playerOne.getName());
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






	
	