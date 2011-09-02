
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

/*
 * In this test case every player is going to roll another value. 
 */

@RunWith(JExample.class)
public class PhasePreparation2LogicTest1 extends ASCIITestTemplate
{	
	private IPlayer player1;
	private IPlayer player2;
	private IPlayer player3;
	
	private String fileNameWithPathSimpleGameTest;
	
	private ArrayList<IPlayer> playingOrder=new ArrayList<IPlayer>();
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
		
		game.setStartPhase(EPhases.phasePreparation2);
		
		game.createNew();
		
		this.player1=game.createANewPlayer();					
		this.player2=game.createANewPlayer();						
		this.player3=game.createANewPlayer();					
		
		this.player1.setName("P1");
		this.player2.setName("P2");
		this.player3.setName("P3");
		
		this.playingOrder.add(this.player3);
		this.playingOrder.add(this.player1);
		this.playingOrder.add(this.player2);
		
		game.turnOnTestMode();
		
		game.play();
		
		this.waitingGeneral(); 
		
		game.turnOnCurrentPhaseWaiting();
		
		return game;
		
	}
	

	private void prepareTest3PlayersValidOrder() 
	{
		ArrayList<String> instructions=this.createInstructionsForTest3PlayersValidOrder();
		
		String fileName="PhasePreparation2LogicTest_test3PlayersValidOrder.ins";
		
		this.fileNameWithPathSimpleGameTest=Setting.pathTestFiles+fileName;
		UserInput.setTestingFileName(this.fileNameWithPathSimpleGameTest);

		ReadAndWriteFiles.writeFile(instructions, this.fileNameWithPathSimpleGameTest);
	}


	private ArrayList<String> createInstructionsForTest3PlayersValidOrder() 
	{
		ArrayList<String> instructions=new ArrayList<String>();
		
		instructions.add("2");
		instructions.add("3");
		instructions.add("1");
		
		return instructions;
	}
	
	@Given("test3PlayersValidOrder")
	public IGame phaseShouldBePhasePreparation2(IGame game)
	{
		IPhase currentPhase=game.getCurrentPhase();
		
		assertTrue(currentPhase.getCurrentAction()==EActions.ActionBeforRunning);
		
		assertTrue(game.getCurrentEPhase()==EPhases.phasePreparation2);
		
		
		game.turnOffCurrentPhaseWaiting();
		this.waitingForPhaseReady(currentPhase);
		
		assertTrue(currentPhase.getCurrentAction()==EActions.ActionDoAllPreAction);
		
		return game;
	}
	
	@Given("phaseShouldBePhasePreparation2")
	public IGame currentPlayerShouldBePlayer1(IGame game)
	{
		IPhase currentPhase=game.getCurrentPhase();
		
		assertTrue(currentPhase.getCurrentAction()==EActions.ActionDoAllPreAction);
		
		assertTrue(game.getCurrentPlayer()==this.player1);
		
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
		
		assertTrue(currentPhase.getCurrentAction()==EActions.ActionDoAllPreAction);
		
		assertTrue(game.getCurrentPlayer()==this.player2);
		
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
		
		game.turnOffCurrentPhaseWaiting();
		this.waitingForPhaseReady(game.getCurrentPhase());
		
		game.turnOffCurrentPhaseWaiting();
		this.waitingForPhaseReady(game.getCurrentPhase());
		
		return game;
	}
	
	
	@Given("currentPlayerShouldBePlayer3")
	public IGame checkOrderToPlay(IGame game)
	{		
		game.skipTailOrHeadPlayer();
		
		for(int i=0; i<this.playingOrder.size(); i++)
		{
			assertTrue(this.playingOrder.get(i)==game.getPlayOrder().get(i));
		}
		
		return game;
	}
	
	
	@Given("checkOrderToPlay")
	public IGame phaseShouldBePhasePreparation3(IGame game)
	{
		game.turnOffCurrentPhaseWaiting();
		this.waitingGeneral();
		
		assertTrue(game.getCurrentEPhase()==EPhases.phasePreparation3);		
	
		return game;
	}
	


	
	
	
}






	
	