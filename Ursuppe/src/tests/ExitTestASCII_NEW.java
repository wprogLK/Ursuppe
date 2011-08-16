
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

import templates.ASCIITestTemplate;
import templates.GUITestTemplate;

import com.google.inject.Guice;
import com.google.inject.Injector;




@RunWith(JExample.class)
public class ExitTestASCII_NEW extends ASCIITestTemplate{
	
	/**
	 * 
	 */
	@Test
	public IGame simpleGame() 
	{
		this.setPriority(10);
		
		IGame game;
		
		game=module.createGame();
		
		game.setStartPhase(EPhases.phaseA);
		
		return game;
	}
	

	@Given("simpleGame")
	public IGame createANewGame(IGame game)
	{
		game.createNew();
		//this.playerOne=  game.createANewPlayer();
		
		
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
		//TODO
		return game;
	}
	
	
	@Given("phaseShouldBeA")
	public IGame phaseShouldBeExit(IGame game)
	{
		this.waitingGeneral(); 		//Important!
		
		IPhase phaseA=game.getCurrentPhase();
		
		phaseA.waitForANewInput();
		
		
		phaseA.waitForANewInput();
		assertTrue(phaseA.setInputA("exit"));
		assertTrue(phaseA.setInputB("exit"));
		//assertTrue(phaseA.setInputC("exit"));
//		phaseA.fakeClickExit();
		
		this.waitingGeneral();
		
		this.outStream.println("CURRENT ePhase: " + game.getCurrentEPhase());
		this.outStream.println("CURRENT Phase: " + game.getCurrentPhase());
//		
//		IPhase phaseA=game.getCurrentPhase();
//		
//		phaseA.waitForANewInput();
//		
//		phaseA.fakeInputA("Lukas");
//		this.waitingBetweenTwoInputs();
//		
//		phaseA.fakeClickOK();
		return game;
	}
	
//	@Given("playGameWithTwoPlayer")
//	public IGame setNamePlayerOne(IGame game)
//	{
//		this.waitingGeneral(); 		//Important!
//		
//		IPhase phaseA=game.getCurrentPhase();
//		
//		phaseA.waitForANewInput();
//		
//		phaseA.fakeInputA("Lukas");
//		this.waitingBetweenTwoInputs();
//		
//		phaseA.fakeClickOK();
//		
//		return game;
//	}
//	
//	@Given("setNamePlayerOne")
//	public IGame namePlayerOneShouldBeLukas(IGame game)
//	{
//		assertTrue(this.playerOne.getName().equals("Lukas"));
//		return game;
//	}
//	
//	@Given("namePlayerOneShouldBeLukas")
//	public IGame namePlayerTwoShouldBeSubjectName(IGame game)
//	{
//		assertTrue(this.playerTwo.getName().equals("[SubjectName]"));
//
//		return game;
//	}
	
	
	
}






	
	