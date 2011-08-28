
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
 * In this test in the FIRST ROUND player 2 and 3 are going to roll the same value.
 * In the SECOND ROUND player 2 and 3 are are going to roll different values.
 */
@RunWith(JExample.class)
public class PhasePreparation1LogicTest2 extends ASCIITestTemplate
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
	public IGame test3PlayersInValidOrder() 
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
		
		this.playingOrder.add(this.player3);
		this.playingOrder.add(this.player2);
		this.playingOrder.add(this.player1);
		
		game.turnOnTestMode();
		
		game.play();
		
		this.waitingGeneral(); 
		
		game.turnOnCurrentPhaseWaiting();
		
		return game;
		
	}
	

	private void prepareTest3PlayersValidOrder() 
	{
		ArrayList<String> instructions=this.createInstructionsForTest3PlayersValidOrder();
		
		String fileName="PhasePreparation1LogicTest_test3PlayersInvalidOrder.ins";
		
		this.fileNameWithPathSimpleGameTest=Setting.pathTestFiles+fileName;
		UserInput.setTestingFileName(this.fileNameWithPathSimpleGameTest);

		ReadAndWriteFiles.writeFile(instructions, this.fileNameWithPathSimpleGameTest);
	}


	private ArrayList<String> createInstructionsForTest3PlayersValidOrder() 
	{
		ArrayList<String> instructions=new ArrayList<String>();
		
		//First Round
		instructions.add("roll");	//P1
		instructions.add("roll");	//P2
		instructions.add("roll");	//P3
		
		//Second Round
		instructions.add("roll");	//P2
		instructions.add("roll");	//P3
		
		return instructions;
	}
	
	@Given("test3PlayersInValidOrder")
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
	public IGame currentPlayerShouldBePlayer1_FirstRound(IGame game)
	{
		IPhase currentPhase=game.getCurrentPhase();
		
		assertTrue(currentPhase.getCurrentAction()==EActions.ActionDoAllPreAction);
		
		assertTrue(game.getCurrentPlayer()==this.player1);
		
		game.getDie().setFakeValue(2);
		
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
	
	@Given("currentPlayerShouldBePlayer1_FirstRound")
	public IGame currentPlayerShouldBePlayer2_FirstRound(IGame game)
	{
		IPhase currentPhase=game.getCurrentPhase();
//		System.out.println("ACTION plaYER 2" + currentPhase.getCurrentAction());
		
		assertTrue(currentPhase.getCurrentAction()==EActions.ActionDoAllPreAction);
		
		assertTrue(game.getCurrentPlayer()==this.player2);
		
		game.getDie().setFakeValue(5);
		
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
	
	@Given("currentPlayerShouldBePlayer2_FirstRound")
	public IGame currentPlayerShouldBePlayer3_FirstRound(IGame game)
	{
		IPhase currentPhase=game.getCurrentPhase();
		
		assertTrue(currentPhase.getCurrentAction()==EActions.ActionDoAllPreAction);
		
		assertTrue(game.getCurrentPlayer()==this.player3);
		
		game.getDie().setFakeValue(5);
		
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
	
	
	@Given("currentPlayerShouldBePlayer3_FirstRound")
	public IGame phaseShouldBeStillPhasePreparation1(IGame game)
	{
		assertTrue(game.getCurrentEPhase()==EPhases.phasePreparation1);		
	
		return game;
	}
	
	@Given("phaseShouldBeStillPhasePreparation1")
	public IGame currentPlayerShouldBePlayer2_SecondRound(IGame game)
	{
		IPhase currentPhase=game.getCurrentPhase();
		
		assertTrue(currentPhase.getCurrentAction()==EActions.ActionDoAllPreAction);
		
		assertTrue(game.getCurrentPlayer()==this.player2);
		
		game.getDie().setFakeValue(3);
		
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
	
	@Given("currentPlayerShouldBePlayer2_SecondRound")
	public IGame currentPlayerShouldBePlayer3_SecondRound(IGame game)
	{
		IPhase currentPhase=game.getCurrentPhase();
		
		assertTrue(currentPhase.getCurrentAction()==EActions.ActionDoAllPreAction);
		
		assertTrue(game.getCurrentPlayer()==this.player3);
		
		game.getDie().setFakeValue(6);
		
		game.turnOffCurrentPhaseWaiting();
		this.waitingForPhaseReady(game.getCurrentPhase());
		assertTrue(currentPhase.getCurrentAction()==EActions.ActionAfterRunning);
		
		game.turnOffCurrentPhaseWaiting();
		this.waitingForPhaseReady(game.getCurrentPhase());
		
		
		return game;
	}
	
	@Given("currentPlayerShouldBePlayer3_SecondRound")
	public IGame checkOrderToPlay(IGame game)
	{		
		ArrayList<IPlayer> gameList=game.getPlayOrder();
		
		game.skipTailOrHeadPlayer();
		
		for(int i=0; i<this.playingOrder.size(); i++)
		{
			assertTrue(this.playingOrder.get(i)==game.getPlayOrder().get(i));
		}
		
		return game;
	}
	
	@Given("checkOrderToPlay")
	public IGame phaseShouldBePhasePreparation2(IGame game)
	{
		game.showPlayers();
		
		assertTrue(game.getCurrentEPhase()==EPhases.phasePreparation2);		
	
		return game;
	}
	
	

	
	
	
}






	
	