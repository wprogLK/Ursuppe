
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
import interfaces.IGame;
import interfaces.IPhase;
import interfaces.IPlayer;

import org.junit.Test;
import ch.unibe.jexample.JExample;
import ch.unibe.jexample.Given;


import org.junit.runner.RunWith;

import templates.ASCIITestTemplate;


@RunWith(JExample.class)
public class SimpleGameTest2ASCII extends ASCIITestTemplate//Thread{
{	
	private IPlayer playerOne;
	private IPlayer playerTwo;
	
	private String fileNameWithPathSimpleGameTest;
	
	
	/**
	 * 
	 */
	@Test
	public IGame simpleGameTest() 
	{
		this.prepareSimpleGameTest();
		
		this.setPriority(10);
		
		IGame game;
		
		game=module.createGame();
		
		game.setStartPhase(EPhases.phaseSplashScreen);
		
		game.createNew();
		
		game.turnOnTestMode();
		
		game.play();
		
		this.waitingGeneral(); 
		
		game.turnOnCurrentPhaseWaiting();
	
		
		return game;
		
	}
	

	private void prepareSimpleGameTest() 
	{
		ArrayList<String> instructions=this.createInstructionsForSimpleGameTest();
		
		String fileName="simpleGameTest2ASCII_simpleGameTest.ins";
		
		this.fileNameWithPathSimpleGameTest=Setting.pathTestFiles+fileName;
		UserInput.setTestingFileName(this.fileNameWithPathSimpleGameTest);

		ReadAndWriteFiles.writeFile(instructions, this.fileNameWithPathSimpleGameTest);
		
	}


	private ArrayList<String> createInstructionsForSimpleGameTest() 
	{
		ArrayList<String> instructions=new ArrayList<String>();
		
		instructions.add("Start");
		instructions.add("1");
		instructions.add("add");
		
		return instructions;
	}


	@Given("simpleGameTest")
	public IGame phaseShouldBePhaseSplashScreen(IGame game)
	{
		assertTrue(game.getCurrentEPhase()==EPhases.phaseSplashScreen);		//TODO AB HIER
		
		game.turnOffCurrentPhaseWaiting();
		this.waitingGeneral();
		
		assertTrue(game.getCurrentEPhase()==EPhases.phaseMainMenu);
		
		game.turnOffCurrentPhaseWaiting();
		this.waitingGeneral();
		
		assertTrue(game.getCurrentEPhase()==EPhases.phaseNewGame);	
		
		return game;
	}
	

	
	
	
}






	
	