
package tests;
/**
 * 
 */
import static org.junit.Assert.*;

import enums.EPhases;
import gameObjectsASCII.GameASCII;
import gameObjectsGUI.GameGUI;
import helper.Setting;
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


//TODO FIX IT: namePlayerOneShouldBeLukas assertionError

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
		
		this.outStream.println("With one player");
		game.showPlayers();
		
		return game;
	}
	
	@Given("simpleGameForTwoPlayer")
	public IGame createANewGameWithTwoPlayer(IGame game)
	{
		game.createNew();
		this.playerOne=  game.createANewPlayer();
		this.playerTwo=  game.createANewPlayer();
		
		this.outStream.println("With two players");
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
		this.waitingGeneral(); 		//Important!
		
		IPhase phaseA=game.getCurrentPhase();
		
		phaseA.waitForANewInput();
		
		phaseA.fakeInputA("Lukas");
		this.waitingBetweenTwoInputs();
		
		phaseA.fakeClickOK();
		
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






	
	