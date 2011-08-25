
package tests;
/**
 * 
 */
import static org.junit.Assert.*;

import java.util.ArrayList;

import enums.EActions;
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
public class PhasePreparation1LogicTest1 extends ASCIITestTemplate
{	
	private IPlayer player1;
	private IPlayer player2;
	private IPlayer player3;
	
	private String fileNameWithPathSimpleGameTest;
	
	
	/**
	 * 
	 */
	@Test
	public IGame test3PlayersValidOrder() 
	{
		this.prepareTest3PlayersValidOrder();
		
		this.setPriority(10);
		
		IGame game;
		
		game=module.createGame();
		
		game.setStartPhase(EPhases.phasePreparation1);
		
		game.createNew();
		
		this.player1=game.createANewPlayer();					
		this.player2=game.createANewPlayer();						
		this.player3=game.createANewPlayer();					
		
		this.player1.setName("P1");
		this.player2.setName("P2");
		this.player3.setName("P3");
		
		game.turnOnTestMode();
		
		game.play();
		
		this.waitingGeneral(); 
		
		game.turnOnCurrentPhaseWaiting();
		
		return game;
		
	}
	

	private void prepareTest3PlayersValidOrder() 
	{
		ArrayList<String> instructions=this.createInstructionsForTest3PlayersValidOrder();
		
		String fileName="PhasePreparation1LogicTest_test3PlayersValidOrder.ins";
		
		this.fileNameWithPathSimpleGameTest=Setting.pathTestFiles+fileName;
		UserInput.setTestingFileName(this.fileNameWithPathSimpleGameTest);

		ReadAndWriteFiles.writeFile(instructions, this.fileNameWithPathSimpleGameTest);
	}


	private ArrayList<String> createInstructionsForTest3PlayersValidOrder() 
	{
		ArrayList<String> instructions=new ArrayList<String>();
		
		instructions.add("roll");
		instructions.add("roll");
		instructions.add("roll");
		
		return instructions;
	}
	
	@Given("test3PlayersValidOrder")
	public IGame phaseShouldBePhasePreparation1(IGame game)
	{
		IPhase currentPhase=game.getCurrentPhase();
		
		//System.out.println("CURRENT ACTION 1: " +currentPhase.getCurrentAction());
		assertTrue(currentPhase.getCurrentAction()==EActions.ActionBeforRunning);
		
		assertTrue(game.getCurrentEPhase()==EPhases.phasePreparation1);
		
		
		game.turnOffCurrentPhaseWaiting();
		this.waitingForPhaseReady(currentPhase);
		
		assertTrue(currentPhase.getCurrentAction()==EActions.ActionDoAllPreAction);
		
		return game;
	}
	
	@Given("phaseShouldBePhasePreparation1")
	public IGame currentPlayerShouldBePlayer1(IGame game)
	{
		IPhase currentPhase=game.getCurrentPhase();
		
		assertTrue(currentPhase.getCurrentAction()==EActions.ActionDoAllPreAction);
		
		assertTrue(game.getCurrentPlayer()==this.player1);
		
		game.getDie().setFakeValue(4);
		
		game.turnOffCurrentPhaseWaiting();
		this.waitingForPhaseReady(game.getCurrentPhase());
		
		assertTrue(currentPhase.getCurrentAction()==EActions.ActionAfterRunning);
		
		
		game.turnOffCurrentPhaseWaiting();
		this.waitingForPhaseReady(game.getCurrentPhase());
		
		assertTrue(currentPhase.getCurrentAction()==EActions.ActionBeforRunning);
		
		game.turnOffCurrentPhaseWaiting();
		this.waitingForPhaseReady(game.getCurrentPhase());
		
		
		
		return game;
	}
	
	@Given("currentPlayerShouldBePlayer1")
	public IGame currentPlayerShouldBePlayer2(IGame game)
	{
		IPhase currentPhase=game.getCurrentPhase();
		//System.out.println("ACTION plaYER 2" + currentPhase.getCurrentAction());
		
		assertTrue(currentPhase.getCurrentAction()==EActions.ActionDoAllPreAction);
		
		assertTrue(game.getCurrentPlayer()==this.player2);
		
		game.getDie().setFakeValue(1);
		
		game.turnOffCurrentPhaseWaiting();
		this.waitingForPhaseReady(game.getCurrentPhase());
		
		
		game.turnOffCurrentPhaseWaiting();
		this.waitingForPhaseReady(game.getCurrentPhase());
		
		assertTrue(currentPhase.getCurrentAction()==EActions.ActionBeforRunning);
		
		game.turnOffCurrentPhaseWaiting();
		this.waitingForPhaseReady(game.getCurrentPhase());
		
		return game;
	}
	
	@Given("currentPlayerShouldBePlayer2")
	public IGame currentPlayerShouldBePlayer3(IGame game)
	{
		IPhase currentPhase=game.getCurrentPhase();
		
		assertTrue(currentPhase.getCurrentAction()==EActions.ActionDoAllPreAction);
		
		assertTrue(game.getCurrentPlayer()==this.player3);
		
		game.getDie().setFakeValue(2);
		
		game.turnOffCurrentPhaseWaiting();
		this.waitingForPhaseReady(game.getCurrentPhase());
		
		return game;
	}
	
	
	@Given("currentPlayerShouldBePlayer3")
	public IGame phaseShouldBePhasePreparation2(IGame game)
	{
		game.turnOffCurrentPhaseWaiting();
		this.waitingGeneral();
		
		assertTrue(game.getCurrentEPhase()==EPhases.phasePreparation2);		
	
		return game;
	}
	

	
	
	
}






	
	