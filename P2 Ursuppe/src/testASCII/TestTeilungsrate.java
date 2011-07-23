
package testASCII;
/**
 * 
 */
import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JPanel;
import javax.swing.Timer;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import Components.Ameba;
import Components.Die;
import Components.ISquare;
import Components.LadderSquare;
import Components.Player;
import GUIComponents.UrsuppeGUI;
import Hamcrests.checkBoolean;
import Hamcrests.checkColor;
import Hamcrests.checkDirection;
import Hamcrests.checkInteger;
import Hamcrests.checkPhase;
import Hamcrests.checkPlayer;
import Hamcrests.checkString;
import ch.unibe.jexample.JExample;
import ch.unibe.jexample.Given;


import org.junit.runner.RunWith;

import phases.*;
import enums.*;
import game.*;

/**
 *  this tests a specific test scenario
 * @author lukas
 *
 */
@RunWith(JExample.class)
public class TestTeilungsrate implements ActionListener {

	private GamePhases activePhase;
	private GamePhases changePhase;
	private PhaseLogic phaseLogic;
	
	private  Game game;
	
	private Die die;
	
	private Player playerBlue;
	private Player playerRed;
	private Player playerYellow;
	
	private Timer sleepTimer;
	private final int sleep_interval=1000; //1(?) seconds sleep	//for between change phases
	private final int nap_interval=800;		//0.8 (?) second nap //for in phases
	
	private boolean timerIsOn=false;
	
	
	private boolean isNapPossible=true;			//@ USER: you can switch this!
	private final boolean seeEndBoard=false;	//@ USER: you can switch this!
	
	/**
	 * 
	 */
	@Test
	public UrsuppeGUI scriptedGame1() 
	{
		
		this.createSleeper();
		
		UrsuppeGUI ursuppe=new UrsuppeGUI(true,true);
		//ursuppe.run();
		
	
		
		this.phaseLogic=ursuppe.getPhaseLogic();
		this.activePhase=this.phaseLogic.getActivePhase();
		this.changePhase=this.phaseLogic.getChangePhase();
		
		this.game=ursuppe.getGame();
		this.die=this.game.getDie();
		
		/////////////////
		//WINDDIRECTION//
		/////////////////
		
		Stack<GameDirection> stackWind=new Stack<GameDirection>();
		
		stackWind.add(GameDirection.East);
		stackWind.add(GameDirection.East);
		stackWind.add(GameDirection.Middle);
		stackWind.add(GameDirection.West);
		stackWind.add(GameDirection.South);
		stackWind.add(GameDirection.North);
		
		assertThat(stackWind.size(),checkInteger.checkInteger(6));
		
		this.game.setStackOfWindDirection(stackWind);
		assertThat(stackWind.size(),checkInteger.checkInteger(5));
		assertThat(this.game.getActualeDirection(),checkDirection.checkDirection(GameDirection.North));
		
		/////////
		//OZONE//
		/////////
		
		Stack<Integer> stackOzone=new Stack<Integer>();
		
		stackOzone.add(10);
		stackOzone.add(7);
		stackOzone.add(12);
		stackOzone.add(6);
		stackOzone.add(9);
		stackOzone.add(10);
		
		
		assertThat(stackOzone.size(),checkInteger.checkInteger(6));
		
		this.game.setStackOfOzone(stackOzone);
		assertThat(stackOzone.size(),checkInteger.checkInteger(5));
		assertThat(this.game.getOzoneValue(),checkInteger.checkInteger(10));
		
		
		this.playerBlue=this.game.getPlayer(GameColor.blue);
		this.playerRed=this.game.getPlayer(GameColor.red);
		this.playerYellow=this.game.getPlayer(GameColor.yellow);

		assertFalse(this.die.isInTestMode());
		
		this.die.testModeOn();
		
		assertTrue(this.die.isInTestMode());
	
		
		
		//TEST LADDERSQUARES:
		for (int i=1; i<=this.game.getBoard().getLadder().size();i++)
		{
			LadderSquare ladderSquare=this.game.getBoard().getLadderSquare(i);
			
			assertThat(ladderSquare.getNumber(), checkInteger.checkInteger(i));
		}
		
		
		
		return ursuppe;	
	}
	
	
	@Given("testScriptedGame1")
	public UrsuppeGUI setNewPlayerData(UrsuppeGUI ursuppe)
	{
		
		////////////////////
		//BLUE//
		//////////
		this.playerBlue.addGene(GameGene.Teilungsrate);
		
		int price=GameGene.Teilungsrate.getPrice();
		assertThat(this.game.getBioPointsAtStart()-price,checkInteger.checkInteger(this.playerBlue.getBioPoints()));
		assertThat(GameGene.Teilungsrate.getAvailableNrOfGenes(),checkInteger.checkInteger(0));
		
		this.playerBlue.setBioPoints(this.game.getBioPointsAtStart()); 
		
		assertThat(this.playerBlue.getSumOfAllGenesOzoneValue(),checkInteger.checkInteger(5));
		
		
		
		
		
		this.goSleep();
		return ursuppe;
	}
	
	///////////////////////
	//TEST: START PHASES://
	///////////////////////
	
	@Given("setNewPlayerData")
	public UrsuppeGUI startPhaseSetNames(UrsuppeGUI ursuppe)
	{
		assertThat(this.phaseLogic.getActivePhase(),checkPhase.checkPhase(GamePhases.phaseSetNames));							//Important! Each phase test must check this!
		//assertThat(square42.getAmebasOfColor(colorOfAmeba1).size(),checkInteger.checkInteger(0));
		
		this.goSleep();
		
		return ursuppe;
	}
	
	@Given("testDataPhaseSetNames")
	public UrsuppeGUI startPhase0(UrsuppeGUI ursuppe)				
	{
		ursuppe.fakeTimer();
		
		assertThat(this.phaseLogic.getActivePhase(),checkPhase.checkPhase(GamePhases.phase0));							//Important! Each phase test must check this!
		
		this.goSleep();
		
		return ursuppe;
	}
	
	@Given("testDataPhase0")
	public UrsuppeGUI startPhaseSetFirstAmeba(UrsuppeGUI ursuppe)
	{
		ursuppe.fakeTimer();
		
		assertThat(this.phaseLogic.getActivePhase(),checkPhase.checkPhase(GamePhases.phaseSetFirstAmeba));				//Important! Each phase test must check this!
		
		this.goSleep();
		
		return ursuppe;
	}
	
	@Given("testDataPhaseSetFirstAmeba")
	public UrsuppeGUI startPhase1(UrsuppeGUI ursuppe)
	{
		ursuppe.fakeTimer();
		
		assertThat(this.phaseLogic.getActivePhase(),checkPhase.checkPhase(GamePhases.phase1));							//Important! Each phase test must check this!
		
		this.goSleep();
		
		return ursuppe;
	}
	
	@Given("testDataPhase1")
	public UrsuppeGUI startPhase2(UrsuppeGUI ursuppe)
	{
		ursuppe.fakeTimer();
		
		assertThat(this.phaseLogic.getActivePhase(),checkPhase.checkPhase(GamePhases.phase2));							//Important! Each phase test must check this!
		
		this.goSleep();
		
		return ursuppe;
	}
	
	@Given("testDataPhase2")
	public UrsuppeGUI startPhase3(UrsuppeGUI ursuppe)
	{
		ursuppe.fakeTimer();
		
		assertThat(this.phaseLogic.getActivePhase(),checkPhase.checkPhase(GamePhases.phase3));							//Important! Each phase test must check this!
		
		this.goSleep();
		
		return ursuppe;
	}
	
	@Given("testDataPhase3")
	public UrsuppeGUI startPhase4(UrsuppeGUI ursuppe)
	{
		ursuppe.fakeTimer();
		
		assertThat(this.phaseLogic.getActivePhase(),checkPhase.checkPhase(GamePhases.phase4));								//Important! Each phase test must check this!
		
		this.goSleep();
		
		return ursuppe;
	}
	

	

	
	
	///////////////////////////
	//TEST: DATA OF A PHASE://
	//////////////////////////
	
	@Given("scriptedGame1")
	public UrsuppeGUI testScriptedGame1(UrsuppeGUI ursuppe)
	{	
		assertThat(this.playerBlue.getColor(),checkColor.checkColor(GameColor.blue));
		assertThat(this.playerRed.getColor(),checkColor.checkColor(GameColor.red));
		assertThat(this.playerYellow.getColor(),checkColor.checkColor(GameColor.yellow));
		
		assertThat(this.playerBlue.getBioPoints(),checkInteger.checkInteger(this.game.getBioPointsAtStart()));
		assertThat(this.playerRed.getBioPoints(),checkInteger.checkInteger(this.game.getBioPointsAtStart()));
		assertThat(this.playerYellow.getBioPoints(),checkInteger.checkInteger(this.game.getBioPointsAtStart()));
		
		assertThat(this.playerBlue.getAmebasOffBoard().size(),checkInteger.checkInteger(4));
		assertThat(this.playerRed.getAmebasOffBoard().size(),checkInteger.checkInteger(4));
		assertThat(this.playerYellow.getAmebasOffBoard().size(),checkInteger.checkInteger(4));
		
		assertThat(this.playerBlue.getScore(),checkInteger.checkInteger(0));	
		assertThat(this.playerRed.getScore(),checkInteger.checkInteger(0));		
		assertThat(this.playerYellow.getScore(),checkInteger.checkInteger(0));	
		
		assertThat(this.playerBlue.getNumberOfGenes(),checkInteger.checkInteger(0));
		assertThat(this.playerRed.getNumberOfGenes(),checkInteger.checkInteger(0));
		assertThat(this.playerYellow.getNumberOfGenes(),checkInteger.checkInteger(0));
		
		assertThat(this.playerBlue.getSumOfAllGenesOzoneValue(),checkInteger.checkInteger(0));
		assertThat(this.playerRed.getSumOfAllGenesOzoneValue(),checkInteger.checkInteger(0));
		assertThat(this.playerYellow.getSumOfAllGenesOzoneValue(),checkInteger.checkInteger(0));
		
		assertThat(this.playerBlue.getCostOfMoveAnAmeba(),checkInteger.checkInteger(1));
		assertThat(this.playerRed.getCostOfMoveAnAmeba(),checkInteger.checkInteger(1));
		assertThat(this.playerYellow.getCostOfMoveAnAmeba(),checkInteger.checkInteger(1));
		
		assertThat(this.playerBlue.getCostOfSetAmeba(),checkInteger.checkInteger(6));
		assertThat(this.playerRed.getCostOfSetAmeba(),checkInteger.checkInteger(6));
		assertThat(this.playerYellow.getCostOfSetAmeba(),checkInteger.checkInteger(6));
		
		assertThat(this.playerBlue.getAmebasOnBoard().size(),checkInteger.checkInteger(0));
		assertThat(this.playerRed.getAmebasOnBoard().size(),checkInteger.checkInteger(0));
		assertThat(this.playerYellow.getAmebasOnBoard().size(),checkInteger.checkInteger(0));
		
		assertFalse(this.game.getIsOver());
		return ursuppe;
	}
	
	@Given("startPhaseSetNames")
	public UrsuppeGUI testDataPhaseSetNames(UrsuppeGUI ursuppe)
	{
		PhaseSetNames phase=(PhaseSetNames) this.getObjectPhase(this.phaseLogic.getActivePhase());	//Important! Each phase test should have this line!

		phase.fakeWriteTextFieldNamePlayerBlue("Jonas");
		this.goHavingANap(); //@for see only
		phase.fakeWriteTextFieldNamePlayerRed("Lukas");
		this.goHavingANap(); //@for see only
		phase.fakeWriteTextFieldNamePlayerYellow("Max Muster");
		this.goHavingANap(); //@for see only
		phase.fakeClickButtonOK();
		
		
		assertThat(this.playerBlue.getPlayerName(),checkString.checkString("Jonas"));
		assertThat(this.playerRed.getPlayerName(),checkString.checkString("Lukas"));
		assertThat(this.playerYellow.getPlayerName(),checkString.checkString("Max Muster"));
		
		return ursuppe;
	}
	
	@Given("startPhase0")
	public UrsuppeGUI testDataPhase0(UrsuppeGUI ursuppe)
	{
		
		Phase0 phase=(Phase0) this.getObjectPhase(this.phaseLogic.getActivePhase());	//Important! Each phase test should have this line!
		
		assertFalse(phase.buttonLetsPlayPart2IsEnabled());
		assertTrue(phase.buttonRollDiePart1IsEnabled());
		
		assertFalse(phase.radioButtonStartPos1IsEnabled());
		assertFalse(phase.radioButtonStartPos2IsEnabled());
		assertFalse(phase.radioButtonStartPos3IsEnabled());
		
		//ROLL DIE:
		
		assertThat(this.game.getActualPlayer(),checkPlayer.checkPlayer(playerBlue));
		
		die.setExpectedValue(6);
		this.goHavingANap(); //@for see only
		phase.fakeClickButtonRollDiePart1();
		
		assertThat(this.playerBlue.getRolledFaceValue(),checkInteger.checkInteger(6));
		assertThat(this.playerRed.getRolledFaceValue(),checkInteger.checkInteger(0));
		assertThat(this.playerYellow.getRolledFaceValue(),checkInteger.checkInteger(0));
		
		assertThat(this.game.getActualPlayer(),checkPlayer.checkPlayer(playerRed));
		
		
		die.setExpectedValue(1);
		this.goHavingANap(); //@for see only
		phase.fakeClickButtonRollDiePart1();
		
		assertThat(this.playerBlue.getRolledFaceValue(),checkInteger.checkInteger(6));
		assertThat(this.playerRed.getRolledFaceValue(),checkInteger.checkInteger(1));
		assertThat(this.playerYellow.getRolledFaceValue(),checkInteger.checkInteger(0));
		
		
		assertThat(this.game.getActualPlayer(),checkPlayer.checkPlayer(playerYellow));
		
		die.setExpectedValue(4);
		this.goHavingANap(); //@for see only
		phase.fakeClickButtonRollDiePart1();
		
		assertThat(this.playerBlue.getRolledFaceValue(),checkInteger.checkInteger(6));
		assertThat(this.playerRed.getRolledFaceValue(),checkInteger.checkInteger(1));
		assertThat(this.playerYellow.getRolledFaceValue(),checkInteger.checkInteger(4));
		
		
		this.goHavingANap(); //@for see only
		phase.fakeClickButtonLetsSetPlayers();	//Show dialogue part 2
		this.goHavingANap(); //@for see only
		
		//SET POSITION:
		
		assertTrue(phase.radioButtonStartPos1IsEnabled());
		assertTrue(phase.radioButtonStartPos2IsEnabled());
		assertTrue(phase.radioButtonStartPos3IsEnabled());
		
		assertFalse(phase.buttonLetsPlayPart2IsEnabled());
		
		assertThat(this.game.getActualPlayer(),checkPlayer.checkPlayer(playerBlue));
		
		phase.fakeClickradioButtonStartPos2();
		this.goHavingANap(); //@for see only
		
		assertThat(this.playerBlue.getScore(),checkInteger.checkInteger(2));
		assertThat(this.playerRed.getScore(),checkInteger.checkInteger(0));
		assertThat(this.playerYellow.getScore(),checkInteger.checkInteger(0));
		
		
		assertTrue(phase.radioButtonStartPos1IsEnabled());
		assertFalse(phase.radioButtonStartPos2IsEnabled());
		assertTrue(phase.radioButtonStartPos3IsEnabled());
		
		assertFalse(phase.buttonLetsPlayPart2IsEnabled());
		
		
		
		assertThat(this.game.getActualPlayer(),checkPlayer.checkPlayer(playerYellow)); 
		
		phase.fakeClickradioButtonStartPos1();
		this.goHavingANap(); //@for see only
		
		assertThat(this.playerBlue.getScore(),checkInteger.checkInteger(2));
		assertThat(this.playerRed.getScore(),checkInteger.checkInteger(0));
		assertThat(this.playerYellow.getScore(),checkInteger.checkInteger(1));
		
		
		assertFalse(phase.radioButtonStartPos1IsEnabled());
		assertFalse(phase.radioButtonStartPos2IsEnabled());
		assertTrue(phase.radioButtonStartPos3IsEnabled());
		
		assertFalse(phase.buttonLetsPlayPart2IsEnabled());
		
		
		
		assertThat(this.game.getActualPlayer(),checkPlayer.checkPlayer(playerRed)); 
		
		phase.fakeClickradioButtonStartPos3();
		this.goHavingANap(); //@for see only
		
		assertThat(this.playerBlue.getScore(),checkInteger.checkInteger(2));
		assertThat(this.playerRed.getScore(),checkInteger.checkInteger(3));
		assertThat(this.playerYellow.getScore(),checkInteger.checkInteger(1));
		
		assertFalse(phase.radioButtonStartPos1IsEnabled());
		assertFalse(phase.radioButtonStartPos2IsEnabled());
		assertFalse(phase.radioButtonStartPos3IsEnabled());
		
		assertTrue(phase.buttonLetsPlayPart2IsEnabled());
		
		
		assertThat(this.playerBlue.getScore(),checkInteger.checkInteger(2));
		assertThat(this.playerRed.getScore(),checkInteger.checkInteger(3));
		assertThat(this.playerYellow.getScore(),checkInteger.checkInteger(1));
		
		//System.out.println("ORDER 1:  " +this.game.getOrderOfPlayersColor());
		phase.fakeClickButtonLetsPlayPart2();
		this.goHavingANap(); //@for see only
		
		////////////
		//*YELLOW*//
		////////////
		ISquare ladderSquare1=this.game.getBoard().getLadderSquare(1);
		assertTrue(ladderSquare1.getPlayer().equals(this.playerYellow));
		
		//////////
		//*BLUE*//
		//////////
		ISquare ladderSquare2=this.game.getBoard().getLadderSquare(2);
		assertTrue(ladderSquare2.getPlayer().equals(this.playerBlue));
		
		/////////
		//*RED*//
		/////////
		ISquare ladderSquare3=this.game.getBoard().getLadderSquare(3);
		assertTrue(ladderSquare3.getPlayer().equals(this.playerRed));
		
		return ursuppe;
	}
	
	@Given("startPhaseSetFirstAmeba")
	public UrsuppeGUI testDataPhaseSetFirstAmeba(UrsuppeGUI ursuppe)
	{
		PhaseSetFirstAmeba phase=(PhaseSetFirstAmeba) this.getObjectPhase(this.phaseLogic.getActivePhase());	//Important! Each phase test should have this line!
		
		//System.out.println("ORDER OF PLAYERS IN SCRIPTED GAME: "+ this.game.getOrderOfPlayersColor());
		assertTrue(this.game.getOrderOfPlayersColor().equals(this.getControlOrder()));		//Check order!
		
		/////////////////////////
		////*SET FIRST AMEBA*////
		/////////////////////////
		
		///////////
		//YELLOW://
		///////////
		
		
		setAmebaPhaseSetFirstAmebaCheck(phase,this.playerYellow, 4,  0, true,  true,  true,  true,  1,  2 ,  2,  1);
		this.goHavingANap(); //@for see only
	
		
		/////////
		//BLUE://
		/////////
		
		
		
		this.setAmebaPhaseSetFirstAmebaCheck(phase, this.playerBlue, 4, 0, true, true, true, true, 3, 4 , 2, 1);
		this.goHavingANap(); //@for see only
		
		////////
		//RED://
		///////
		
		this.setAmebaPhaseSetFirstAmebaCheck(phase, this.playerRed, 4, 0, true, true, true, true, 3, 2 , 4, 1);
		this.goHavingANap(); //@for see only
		
		/////////////////////////
		////*SET SECOND AMEBA*////
		/////////////////////////

		///////////
		//YELLOW://
		///////////
		this.setAmebaPhaseSetFirstAmebaCheck(phase, this.playerYellow, 3, 1, false, true, true, true, 3, 4 , 3, 0);
		this.goHavingANap(); //@for see only
		
		/////////
		//BLUE://
		/////////
		this.setAmebaPhaseSetFirstAmebaCheck(phase, this.playerBlue, 3, 1, true, true, false, true, 4, 5 , 4, 0);
		this.goHavingANap(); //@for see only
		
		////////
		//RED://
		///////
		this.setAmebaPhaseSetFirstAmebaCheck(phase, this.playerRed, 3, 1, true, true, false, true, 2, 3 , 1, 0);
		this.goHavingANap(); //@for see only
		
		/////////THIS WAS THE LAST AMEBA!
		assertTrue(phase.getButtonGoToPhase1IsEnabled());
		phase.fakeClickButtonGoToPhase1();
		
		return ursuppe;
	}

	
	@Given("startPhase1")
	public UrsuppeGUI testDataPhase1(UrsuppeGUI ursuppe)		//Move/Drift and Eat
	{
		Phase1 phase=(Phase1) this.getObjectPhase(this.phaseLogic.getActivePhase());				//Important! Each phase test should have this line!
		Ameba activeAmeba;
		assertFalse(phase.getButtonGoToPhase2IsEnabled());
		
		assertTrue(this.game.getReadDirection()==GameReadDirection.up);
		
		//CHECK WINDDIRECTION:
		assertThat(this.game.getActualeDirection(),checkDirection.checkDirection(GameDirection.North));
		
		//////////
		//YELLOW//
		//////////
			
		assertThat(this.game.getActualPlayer(),checkPlayer.checkPlayer(this.playerYellow));
		assertThat(this.playerYellow.getNrOfFoodsToEat(),checkInteger.checkInteger(3));
		
			////////////
			//*AMEBA 1//
			////////////
			
			
			this.moveDriftAndEatTest(phase, this.playerYellow,true, false, 0, GameDirection.North, 2, 1, 0, 1,  1,  1, 2, 2, true,  false,  2,  2,  2,  2,  1,  0,  1, 4 );
			this.goHavingANap(); //@for see only

			
			////////////
			//*AMEBA 3//
			////////////
			this.moveDriftAndEatTest(phase, this.playerYellow,true, false, 0, GameDirection.North, 1, 2, 0, 3,  0,  0, 4, 3, true,  false,  2,  2,  2,  4,  2,  1,  0, 4 );
			this.goHavingANap(); //@for see only
				
			assertFalse(phase.getButtonGoToPhase2IsEnabled());
				
		////////
		//BLUE//
		////////
			
			
			
				assertThat(this.game.getActualPlayer(),checkPlayer.checkPlayer(this.playerBlue));
				assertThat(this.playerBlue.getNrOfFoodsToEat(),checkInteger.checkInteger(3));

			////////////
			//*AMEBA 3//
			////////////
				this.moveDriftAndEatTest(phase, this.playerBlue,true, false, 0, GameDirection.North, 0, 1, 2, 3,  1,  1, 4, 2, false,  false,  1,  0,  4,  4,  1,  4,  1, 0 );
				this.goHavingANap(); //@for see only
				
			////////////
			//*AMEBA 4//
			////////////
				
				this.moveDriftAndEatTest(phase,this.playerBlue,false,true,5, GameDirection.Middle,0,1,2,4,0,0,5,4,false,true,4,1,0,5,4,4,1,0);
				this.goHavingANap(); //@for see only
				
				assertFalse(phase.getButtonGoToPhase2IsEnabled());
		////////
		//RED//
		///////
					
				assertThat(this.game.getActualPlayer(),checkPlayer.checkPlayer(this.playerRed));
				assertThat(this.playerRed.getNrOfFoodsToEat(),checkInteger.checkInteger(3));
				
			////////////
			//*AMEBA 2//
			////////////
					this.moveDriftAndEatTest(phase, this.playerRed, false,true,4,GameDirection.South,1,0,2,2,0,0,3,1,true,false,2,2,2,3,2,1,4,0);
					this.goHavingANap(); //@for see only
					
			////////////
			//*AMEBA 3//
			////////////
					this.moveDriftAndEatTest(phase,this.playerRed,true,false,0,GameDirection.North,2,0,1,3,1,1,2,4,true,false,2,2,2,2,3,0,4,1);
					this.goHavingANap(); //@for see only
					
			assertTrue(phase.getButtonGoToPhase2IsEnabled());
			
			phase.fakeClickbuttonGoToPhase2();
		return ursuppe;
	}
	
	@Given("startPhase2")	//Environment and GeneDefects
	public UrsuppeGUI testDataPhase2(UrsuppeGUI ursuppe)
	{
		Phase2 phase=(Phase2) this.getObjectPhase(this.phaseLogic.getActivePhase());				//Important! Each phase test should have this line!
		
		assertTrue(this.game.getReadDirection()==GameReadDirection.down);
		////////////
		//RED//
		///////
		assertThat(this.game.getActualPlayer(),checkPlayer.checkPlayer(this.playerRed));
		
		assertTrue(phase.getButtonNextPlayertIsEnabled());
		phase.fakeClickButtonNextPlayer();
		this.goHavingANap(); //@for see only
		
		
		///////////
		//BLUE//
		///////
		assertThat(this.game.getActualPlayer(),checkPlayer.checkPlayer(this.playerBlue));
		assertTrue(phase.getButtonNextPlayertIsEnabled());
		phase.fakeClickButtonNextPlayer();
		this.goHavingANap(); //@for see only
		
		
		//////////////
		//YELLOW//
		//////////
		assertThat(this.game.getActualPlayer(),checkPlayer.checkPlayer(this.playerYellow));
		assertTrue(phase.getButtonNextPlayertIsEnabled());
		phase.fakeClickButtonNextPlayer();
		this.goHavingANap(); //@for see only
		
		
		assertTrue(phase.getButtonGoToPhase3IsEnabled());
		phase.fakeClickbuttonGoToPhase3();
		
		return ursuppe;
	}
	
	@Given("startPhase3") //Buy/Sell genes
	public UrsuppeGUI testDataPhase3(UrsuppeGUI ursuppe)
	{
		Phase3 phase=(Phase3) this.getObjectPhase(this.phaseLogic.getActivePhase());				//Important! Each phase test should have this line!
		
		
	
		
		
		////////////
		//RED//
		///////
		assertThat(this.game.getActualPlayer(),checkPlayer.checkPlayer(this.playerRed));
		
		phase.fakeClickbuttonSkip();
		this.goHavingANap(); //@for see only
		
		
		///////////
		//BLUE//
		///////
		assertThat(this.game.getActualPlayer(),checkPlayer.checkPlayer(this.playerBlue));
		phase.fakeClickbuttonSkip();
		this.goHavingANap(); //@for see only
		
		
		//////////////
		//YELLOW//
		//////////
		assertThat(this.game.getActualPlayer(),checkPlayer.checkPlayer(this.playerYellow));
		phase.fakeClickbuttonSkip();
		this.goHavingANap(); //@for see only
		
		assertTrue(phase.getButtonGoToPhase4IsEnabled());
		phase.fakeClickbuttonGoToPhase4();
		return ursuppe;
	}
	
	@Given("startPhase4") //Cell division
	public UrsuppeGUI testDataPhase4(UrsuppeGUI ursuppe)
	{
		Phase4 phase=(Phase4) this.getObjectPhase(this.phaseLogic.getActivePhase());				//Important! Each phase test should have this line!
		
		
		
		assertTrue(this.game.getReadDirection()==GameReadDirection.down);
		
		
		////////
		//RED://
		////////
		assertThat(this.game.getActualPlayer(),checkPlayer.checkPlayer(this.playerRed));
		
		assertTrue(phase.getRadioButtonAmeba1IsEnabled());
		assertFalse(phase.getRadioButtonAmeba2IsEnabled());
		assertFalse(phase.getRadioButtonAmeba3IsEnabled());
		assertTrue(phase.getRadioButtonAmeba4IsEnabled());
		
		assertFalse(phase.getButtonSetAmebaIsEnabled());
		
			///////////////////
			//SET AMEBA NR 4://
			///////////////////
			
			assertThat(this.playerRed.getBioPoints(), checkInteger.checkInteger(this.game.getBioPointsAtStart()-1+10));
			
			phase.fakeClickradioButtonAmeba4();
			this.goHavingANap(); //@for see only
			
			
			phase.setTexttextFieldX("3");
			phase.setTexttextFieldY("1");
			
			assertFalse(phase.getButtonSetAmebaIsEnabled());
			
			phase.fakeClickbuttonCheckPossibleSquare();
			this.goHavingANap(); //@for see only
			
			assertTrue(phase.getButtonSetAmebaIsEnabled());
			phase.fakeClickbuttonSetAmeba();
			this.goHavingANap(); //@for see only
			
			Ameba amebaToSet=phase.getAmebaToSet();
			
			assertThat(amebaToSet.getNumber(), checkInteger.checkInteger(4));
			assertThat(amebaToSet.getDamagePoints(), checkInteger.checkInteger(0));
			
			ISquare square=this.game.getBoard().getSquare(3, 1);
			assertTrue(square.getAmebasOfColor(GameColor.red).contains(amebaToSet));
			
			assertThat(this.playerRed.getBioPoints(), checkInteger.checkInteger(this.game.getBioPointsAtStart()-1-6+10));
		
			assertThat(this.playerRed.getAmebasOnBoard().size(),checkInteger.checkInteger(3));
		phase.fakeClickbuttonSkipPlayer();
		this.goHavingANap(); //@for see only
		
		/////////
		//BLUE://
		/////////
			assertThat(this.game.getActualPlayer(),checkPlayer.checkPlayer(this.playerBlue));
			
			assertTrue(phase.getRadioButtonAmeba1IsEnabled());
			assertTrue(phase.getRadioButtonAmeba2IsEnabled());
			assertFalse(phase.getRadioButtonAmeba3IsEnabled());
			assertFalse(phase.getRadioButtonAmeba4IsEnabled());
			
			assertFalse(phase.getButtonSetAmebaIsEnabled());
			///////////////////
			//SET AMEBA NR 1://
			///////////////////
			
			assertThat(this.playerBlue.getBioPoints(), checkInteger.checkInteger(this.game.getBioPointsAtStart()-1+10));
			
			phase.fakeClickradioButtonAmeba1();
			this.goHavingANap(); //@for see only
			
			
			phase.setTexttextFieldX("5");
			phase.setTexttextFieldY("1");
			
			assertFalse(phase.getButtonSetAmebaIsEnabled());
			
			phase.fakeClickbuttonCheckPossibleSquare();
			this.goHavingANap(); //@for see only
			
			assertTrue(phase.getButtonSetAmebaIsEnabled());
			phase.fakeClickbuttonSetAmeba();
			this.goHavingANap(); //@for see only
			
			amebaToSet=phase.getAmebaToSet();
			
			assertThat(amebaToSet.getNumber(), checkInteger.checkInteger(1));
			assertThat(amebaToSet.getDamagePoints(), checkInteger.checkInteger(0));
			
			square=this.game.getBoard().getSquare(5, 1);
			assertTrue(square.getAmebasOfColor(GameColor.blue).contains(amebaToSet));
			
			assertThat(this.playerBlue.getBioPoints(), checkInteger.checkInteger(this.game.getBioPointsAtStart()-1-4+10));// TODO ERROR FIX THIS!
		
			assertThat(this.playerBlue.getAmebasOnBoard().size(),checkInteger.checkInteger(3));
			
			
		
	
	
		
		phase.fakeClickbuttonSkipPlayer();
		this.goHavingANap(); //@for see only
		
		///////////
		//YELLOW://
		///////////
		assertThat(this.game.getActualPlayer(),checkPlayer.checkPlayer(this.playerYellow));
		
		assertFalse(phase.getRadioButtonAmeba1IsEnabled());
		assertTrue(phase.getRadioButtonAmeba2IsEnabled());
		assertFalse(phase.getRadioButtonAmeba3IsEnabled());
		assertTrue(phase.getRadioButtonAmeba4IsEnabled());
		
		assertFalse(phase.getButtonSetAmebaIsEnabled());
	
		
		assertThat(this.playerYellow.getBioPoints(), checkInteger.checkInteger(this.game.getBioPointsAtStart()+10));
		assertThat(this.playerYellow.getAmebasOnBoard().size(),checkInteger.checkInteger(2));
		
		phase.fakeClickbuttonSkipPlayer();
		this.goHavingANap(); //@for see only
		
		
		assertTrue(phase.getButtonGoToPhase5IsEnabled());
		phase.fakeClickbuttonGoToPhase5();
		
		if(this.seeEndBoard)
		{
			//this while loop is only for see the final gui
			while(true)
			{
				//System.out.println("the game is waiting...");
			}
		}
		else
		{
			return ursuppe;
		}
	}
	
		
		
		
		
	
		
	
	
	
	///////////////////
	//HELPER METHODS://
	///////////////////
	private ArrayList<GameColor> getControlOrder()
	{
		ArrayList<GameColor> colors=new ArrayList<GameColor>();
		
		colors.add(GameColor.empty);
		colors.add(GameColor.yellow);
		colors.add(GameColor.blue);
		colors.add(GameColor.red);
		colors.add(GameColor.empty);
		
		return colors;
	}
	
	private ArrayList<GameColor> getReverseControlOrder()
	{
		ArrayList<GameColor> colors=new ArrayList<GameColor>();
		
		colors.add(GameColor.empty);
		colors.add(GameColor.red);
		colors.add(GameColor.blue);
		colors.add(GameColor.yellow);
		colors.add(GameColor.empty);
		
		return colors;
	}

	
	private JPanel getObjectPhase(GamePhases phase)
	{
		return this.phaseLogic.getPhase(phase);
	}
	
	
	
	/////////////////
	//TEST METHODS://
	/////////////////
		////////////////////////
		//*PhaseSetFirstAmeba*//
		////////////////////////
	
		/**
		 * 
		 * @param phase
		 * @param currentPlayer
		 * @param nrOfAmebasOffBoardBefore
		 * @param nrOfAmebasOnBoardBefore
		 * @param radioButton1IsEnabled
		 * @param radioButton2IsEnabled
		 * @param radioButton3IsEnabled
		 * @param radioButton4IsEnabled
		 * @param choseAmebaNr
		 * @param xCoordinate
		 * @param yCoordinate
		 * @param damagePointsOfAmeba
		 */
		public void setAmebaPhaseSetFirstAmebaCheck(PhaseSetFirstAmeba phase, Player currentPlayer, int nrOfAmebasOffBoardBefore, int nrOfAmebasOnBoardBefore, boolean radioButton1IsEnabled, boolean radioButton2IsEnabled, boolean radioButton3IsEnabled, boolean radioButton4IsEnabled, int choseAmebaNr, int xCoordinate , int yCoordinate, int damagePointsOfAmeba)
		{
			assertThat(this.game.getActualPlayer(),checkPlayer.checkPlayer(currentPlayer)); 
			assertThat(currentPlayer.getNumbersOfAmebasOffBoard(),checkInteger.checkInteger(nrOfAmebasOffBoardBefore));
			assertThat(currentPlayer.getNumbersOfAmebasOnBoard(),checkInteger.checkInteger(nrOfAmebasOnBoardBefore));
			
			
			assertThat(phase.getRadioButtonAmeba1IsEnabled(), checkBoolean.checkBoolean(radioButton1IsEnabled));
			assertThat(phase.getRadioButtonAmeba2IsEnabled(), checkBoolean.checkBoolean(radioButton2IsEnabled));
			assertThat(phase.getRadioButtonAmeba3IsEnabled(), checkBoolean.checkBoolean(radioButton3IsEnabled));
			assertThat(phase.getRadioButtonAmeba4IsEnabled(), checkBoolean.checkBoolean(radioButton4IsEnabled));
			
			assertFalse(phase.getButtonGoToPhase1IsEnabled());
			assertFalse(phase.getButtonSetAmebaIsEnabled());
			assertTrue(phase.getButtonCheckPossibleSquareIsEnabled());
			
			//Chose ameba nr #:
			switch (choseAmebaNr)
			{
				case 1: 
				{
					phase.fakeClickRadioButton1();
					break;
				}
				case 2:
				{
					phase.fakeClickRadioButton2();
					break;
				}
				case 3:
				{
					phase.fakeClickRadioButton3();
					break;
				}
				case 4:
				{
					phase.fakeClickRadioButton4();
					break;
				}
				default:
				{
					System.out.println("Error in ScriptedGame1.class: unkown case in setAmebaPhaseSetFirstAmebaCheck()!");
					//TODO
					assertTrue(false);
					break;
				}
			}
		
			
			assertFalse(phase.getButtonGoToPhase1IsEnabled());
			assertFalse(phase.getButtonSetAmebaIsEnabled());
			assertTrue(phase.getButtonCheckPossibleSquareIsEnabled());
			
			//Set coordinate:
			
			phase.setTextTextFieldX(Integer.toString(xCoordinate));
			phase.setTextTextFieldY(Integer.toString(yCoordinate));
			
			phase.fakeClickCheckPossibleSquare();
			
			assertFalse(phase.getButtonGoToPhase1IsEnabled());
			assertTrue(phase.getButtonSetAmebaIsEnabled());
			assertTrue(phase.getButtonCheckPossibleSquareIsEnabled());
			
			//Set ameba:
			phase.fakeClickButtonSetAmeba();
			
			//Check square:
			assertFalse(this.game.getBoard().getSquare(xCoordinate, yCoordinate).isEmpy());
			
			//Check ameba
			Ameba newSetAmeba=this.game.getActiveAmeba();
			
			assertThat(newSetAmeba.getColor(),checkColor.checkColor(currentPlayer.getColor()));
			assertThat(newSetAmeba.getNumber(),checkInteger.checkInteger(choseAmebaNr));
			assertThat(newSetAmeba.getDamagePoints(),checkInteger.checkInteger(damagePointsOfAmeba));
			
			assertThat(this.playerYellow.getNumbersOfAmebasOffBoard(),checkInteger.checkInteger(nrOfAmebasOffBoardBefore-1));
			assertThat(this.playerYellow.getNumbersOfAmebasOnBoard(),checkInteger.checkInteger(nrOfAmebasOnBoardBefore+1));
		}
		
		/////////////
		//*Phase 1*//
		/////////////
		
		
		public void moveDriftAndEatTest(Phase1 phase, Player currentPlayer, boolean doClickDrift, boolean doClickMove, int expectedValueOfDie, GameDirection currentDirection, int nrOfEatFoodBlue, int nrOfEatFoodRed, int nrOfEatFoodYellow, int nrOfCurrentAmeba, int nrOfDamagePointsBefore, int nrOfDamagePointsAfter, int xCoordinateLeft, int yCoordinateLeft, boolean isLeftSquareEmtpy, boolean isLeftSquareContainsCurrentAmeba, int nrBlueFoodOnLeftSquare, int nrRedFoodOnLeftSquare, int nrYellowFoodOnLeftSquare, int xCoordinateTarget, int yCoordinateTarget, int nrBlueFoodOnTargetSquare, int nrRedFoodOnTargetSquare, int nrYellowFoodOnTargetSquare )
		{
	
			
			
		
				//CHECK AMEBA:
				Ameba activeAmeba=phase.getActiveAmeba();
				assertThat(activeAmeba.getColor(),checkColor.checkColor(currentPlayer.getColor()));
				assertThat(activeAmeba.getNumber(),checkInteger.checkInteger(nrOfCurrentAmeba));
				assertThat(activeAmeba.getDamagePoints(),checkInteger.checkInteger(nrOfDamagePointsBefore));
				
				//decide what to do:
				if(doClickDrift)
				{
					phase.fakeClickbuttonDrift();
				}
				else if (doClickMove)
				{
					phase.fakeClickbuttonMove();
					this.die.setExpectedValue(expectedValueOfDie);
					phase.fakeClickbuttonRollDie();
					assertThat(currentPlayer.getBioPoints(), checkInteger.checkInteger(this.game.getBioPointsAtStart()-1));
					
				}
			
				
				//CHECK TEXTFIELDS
				switch (activeAmeba.getColor())
				{
				case blue:
				{
					assertFalse(phase.getTextFieldNrOfBlueFoodToEatIsEnabled());
					assertTrue(phase.getTextFieldNrOfRedFoodToEatIsEnabled());
					assertTrue(phase.getTextFieldNrOfYellowFoodToEatIsEnabled());
					break;
				}
				case red:
				{
					assertTrue(phase.getTextFieldNrOfBlueFoodToEatIsEnabled());
					assertFalse(phase.getTextFieldNrOfRedFoodToEatIsEnabled());
					assertTrue(phase.getTextFieldNrOfYellowFoodToEatIsEnabled());
					break;
				}
				case yellow:
				{
					assertTrue(phase.getTextFieldNrOfBlueFoodToEatIsEnabled());
					assertTrue(phase.getTextFieldNrOfRedFoodToEatIsEnabled());
					assertFalse(phase.getTextFieldNrOfYellowFoodToEatIsEnabled());
					break;
				}
				default:
				{
					System.out.println("Error in ScriptedGame1.class: unknown error in moveDriftAndEatTest()!");
					//TODO
					assertTrue(false);
					break;
				}
				}
				
				//SET NUMBER OF FOOD TO EAT
				phase.setTexttextFieldNrOfBlueFoodToEat(Integer.toString(nrOfEatFoodBlue));
				phase.setTexttextFieldNrOfRedFoodToEat(Integer.toString(nrOfEatFoodRed));
				phase.setTexttextFieldNrOfYellowFoodToEat(Integer.toString(nrOfEatFoodYellow));
				
				//DO MOVE/DRIFT AND EAT
				
				phase.fakeClickbuttonSetFoodToEat();
				
				//CHECK SQUARES:
				
					//*LEFT SQUARE*//
					ISquare leftSquare=this.game.getBoard().getSquare(xCoordinateLeft, yCoordinateLeft);
					
					assertTrue(leftSquare.isEmpy()==isLeftSquareEmtpy);
					assertTrue(leftSquare.getAmebasOfColor(activeAmeba.getColor()).contains(activeAmeba)==isLeftSquareContainsCurrentAmeba);
				
					assertThat(leftSquare.getNrOfFood(GameColor.blue),checkInteger.checkInteger(nrBlueFoodOnLeftSquare));
					assertThat(leftSquare.getNrOfFood(GameColor.red),checkInteger.checkInteger(nrRedFoodOnLeftSquare));
					assertThat(leftSquare.getNrOfFood(GameColor.yellow),checkInteger.checkInteger(nrYellowFoodOnLeftSquare));
				
					//*TARGET SQUARE*//
					ISquare targetSquare=this.game.getBoard().getSquare(xCoordinateTarget, yCoordinateTarget);
					
					assertFalse(targetSquare.isEmpy());
					assertTrue(targetSquare.getAmebasOfColor(activeAmeba.getColor()).contains(activeAmeba));
					
					assertThat(targetSquare.getNrOfFood(GameColor.blue),checkInteger.checkInteger(nrBlueFoodOnTargetSquare));
					assertThat(targetSquare.getNrOfFood(GameColor.red),checkInteger.checkInteger(nrRedFoodOnTargetSquare));
					assertThat(targetSquare.getNrOfFood(GameColor.yellow),checkInteger.checkInteger(nrYellowFoodOnTargetSquare));
				
				//CHECK AMEBA:
				assertThat(activeAmeba.getDamagePoints(), checkInteger.checkInteger(nrOfDamagePointsAfter));
				assertThat(activeAmeba.getDirection(),checkDirection.checkDirection(currentDirection));
		}
		
		
		public void createSleeper()
		{
			this.sleepTimer=new Timer(this.sleep_interval,this);
		}
		
		public void goSleep(){
			this.sleepTimer=new Timer(this.sleep_interval,this);
			this.sleepTimer.start();
			this.timerIsOn=true;
			
			while(this.timerIsOn)
			{
				//System.out.println("Good night!");
				System.out.print("");
			}
		}
		
		public void goHavingANap(){
			if (this.isNapPossible)
			{
				this.sleepTimer=new Timer(this.nap_interval,this);
				this.sleepTimer.start();
				this.timerIsOn=true;
				
				while(this.timerIsOn)
				{
					//System.out.println("Good and have a nap!");
					System.out.print("");
				}
			}
			
		}
		
		
		
		
		
		public void actionPerformed(ActionEvent e) //TODO maybe it will call by animation_timer=new Timer(animation_interval, this) in the constructor //Check that!
		{
			if (e.getSource()==this.sleepTimer)
			{ 
				this.sleepTimer.stop();
				this.timerIsOn=false;
				
				
			}
			
		}
		
	
}






	
	