package phases;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;


import javax.swing.*;

import phasesGUI.Phase0GUI;
import phasesGUI.PhaseAboutGUI;
import phasesGUI.PhaseSetNamesGUI;

import Components.Board;
import Components.Die;
import Components.LadderSquare;
import Components.Player;

import enums.*;
import game.*;
import helpClasses.InterpretAndRunBasicUserInput;
import helpClasses.ReadShowGUIOrNormal;
import helpClasses.UserInput;



/**
 * 
 */

/**
 * @author lukas
 *
 */
public class Phase0 implements IPhase
{
	//BASIC THINGS OF THIS GUI:
	private boolean isActive;		//if this PhaseGui is the actual gui, the "showing gui"
	private GamePhases activePhase;	//this is IMPORTANT!
	
	private GamePart activePart;
	
		//BOXES
		
	
		//OTHER STUFF
		
		//SPECIAL COMPONENTS:
		private Die die;
		
	//VARIABLES OF THIS PHASE:
		
		
	private String namePlayerBlue;
	private String namePlayerRed;
	private String namePlayerYellow;
	

	
	private Player playerBlue;
	private Player playerRed;
	private Player playerYellow;
	
	private Player actualPlayer;
	
	private ArrayList<Player> tmpOrderOfPlayers;
	private ArrayList<Player> newOrderOfPlayers;
	
	
	private Game game;
	
	private GameReadDirection readDirection;
	
	private Phase0GUI phaseGUI;
	/**
	 * Constructor
	 */
	public Phase0(Game game) {
		//ARGUMENTES OF THE CONSTRUCTOR:
		this.game=game;
		
		init(game);
		
		boolean createGUI=ReadShowGUIOrNormal.read();

		if (createGUI)
		{
			this.phaseGUI = new Phase0GUI(this.game,this);
		}
	
		//Update/Initialize all the data for the components	//IMPORTANT!!!
			this.doUpdate();
			
	}

	private void init(Game game) {
		//SET THE PHASE TO THE GAME:
		this.readDirection=GamePhases.phase0.getReadDirection();
		this.game.setReadDirection(readDirection);
		
		
		this.die=this.game.getDie();
		
		//CLASS VARIABLES:
		this.isActive=false;
		
		this.actualPlayer=game.getActualPlayer();
		
		this.playerBlue=game.getPlayer(GameColor.blue);
		this.playerRed=game.getPlayer(GameColor.red);
		this.playerYellow=game.getPlayer(GameColor.yellow);
		
		this.namePlayerBlue=this.playerBlue.getPlayerName();
		this.namePlayerRed=this.playerRed.getPlayerName();
		this.namePlayerYellow=this.playerYellow.getPlayerName();
		
	
		
		//IMPORTANT STUFF:
		this.activePhase=GamePhases.phaseEmpty;		
		
		
	}
	
	//////////////////////
	//GETTERS & SETTERS://
	//////////////////////
	
		////////////
		//GETTERS://
		////////////
		//to communicate with the GUILOGIC:
		public String getNamePlayerBlue()
		{
			return this.namePlayerBlue;
		}
		
		//to communicate with the GUILOGIC:
		public String getNamePlayerRed()
		{
			return this.namePlayerRed;
		}
		
		//to communicate with the GUILOGIC:
		public String getNamePlayerYellow()
		{
			return this.namePlayerYellow;
		}
		
		//to communicate with the GUILOGIC:
		public GamePhases getActivePhase()
		{
			return this.activePhase;
		}
		
		////////////
		//SETTERS://
		////////////
		private void setActivePart(GamePart activePart)
		{
			this.activePart=activePart;
		}
	
	
	
	
	
	
	///////////////////////////////////
	////////ACTIVATE/DEACTIVATE:///////
	///////////////////////////////////
	//set this GUI to the active GUI (call by GUILOGIC)
		public void activate()
		{
			this.activePhase=GamePhases.phaseEmpty;
			this.isActive=true;
			
			if (this.phaseGUI!=null)
			{
				this.phaseGUI.activate();
				doUpdate();
			}
			else
			{
				System.out.println("\t PHASE 0");
				doPrintMessage();
				
				
				doUpdate();
			}
		}
		
		public void doPrintMessage()
		{
			System.out.println(InterpretAndRunBasicUserInput.possibleStandardInstructions());
			
				//for (int i=0; i<this.game.getNumberOfPlayers();i++)
				while (!this.actualPlayer.equals(this.game.getEmptyPlayer()))
				{	
					Player tmpPlayer=this.actualPlayer;
					String input=UserInput.readInput("Player "+ tmpPlayer.getPlayerName()+  " please roll the die: enter 'r' ");
					InterpretAndRunBasicUserInput.readAndInterpretInput(input, this);
					System.out.println("You rolled a "+ tmpPlayer.getRolledFaceValue());
				}
				
				System.out.println();
				
				while(this.hasMorePlayerSameValue())
				{
					this.game.skipEmptyPlayer();
					this.game.getOrderPlayers();
					this.actualPlayer=this.game.getActualPlayer();
					System.out.println("More than one player had the same value roll! Roll again...");
					System.out.println(	this.game.getOrderPlayers());
					//for (int i=0; i<this.game.getNumberOfPlayers();i++)
					while (!this.actualPlayer.equals(this.game.getEmptyPlayer()))
					{	
						Player tmpPlayer=this.actualPlayer;
						String input=UserInput.readInput("Player "+ tmpPlayer.getPlayerName()+  " please roll again the die: enter 'r' ");
						InterpretAndRunBasicUserInput.readAndInterpretInput(input, this);
						System.out.println("You rolled a "+ tmpPlayer.getRolledFaceValue());
						this.actualPlayer=this.game.getActualPlayer();
					}
				}
				
				this.doNoMorePlayerHaveSameValue();
				

				this.doLetsSetPlayers();
				
				Player tmpPlayer;
				
				System.out.println();
				
				printOutSummaryOrderToSetPositions();
				
				//for (int i=0; i<this.game.getNumberOfPlayers();i++)
				while (!this.actualPlayer.equals(this.game.getEmptyPlayer()))
				{	
					boolean check=false;
					while (!check)
					{
						String possibleStartSquare=this.getPossibleStartSquares();
						
						tmpPlayer=this.actualPlayer;
						String inputPos=UserInput.readInput("Player " + tmpPlayer.getPlayerName() + " please enter " + possibleStartSquare +" for your startPosition on the ladder.");
						check=checkInputPos(inputPos);
					}
					tmpPlayer=this.actualPlayer;
				
				}
				
				this.doCreateOrderToPlay();
				
				System.out.println();
				
				this.printOutSummaryOrderToPlay();
				
				this.doLetsPlay();
		
		}

		private String getPossibleStartSquares() {
			String possibleStartSquare="";
			
			LadderSquare ladderSquare1=this.game.getBoard().getLadderSquare(1);
			LadderSquare ladderSquare2=this.game.getBoard().getLadderSquare(2);
			LadderSquare ladderSquare3=this.game.getBoard().getLadderSquare(3);
			
			String ladder1="";
			String ladder2="";
			String ladder3="";
			
			int numberOfValidPossiblesSquares=0;
			
			if (ladderSquare1.isEmpy())
			{
				ladder1="1";
				numberOfValidPossiblesSquares++;
			}
			
			if (ladderSquare2.isEmpy())
			{
				ladder2="2";
				numberOfValidPossiblesSquares++;
			}
			
			if (ladderSquare3.isEmpy())
			{
				ladder3="3";
				numberOfValidPossiblesSquares++;
			}
			
			
			if (!ladder1.isEmpty())
			{
				if (numberOfValidPossiblesSquares>1)
				{
					possibleStartSquare=possibleStartSquare+ladder1 + " or ";
				}
				else
				{
					possibleStartSquare=possibleStartSquare+ladder1;
				}
				
			}
			
			if (!ladder2.isEmpty())
			{
				
				if (numberOfValidPossiblesSquares>1)
				{
					possibleStartSquare=possibleStartSquare+ladder2 + " or ";
				}
				else
				{
					possibleStartSquare=possibleStartSquare+ladder2;
				}
				
			}
			
			if (!ladder3.isEmpty())
			{
				possibleStartSquare=possibleStartSquare+ladder3;
			}
			
			return possibleStartSquare;
		}

		private void printOutSummaryOrderToSetPositions() {
			Player tmpPlayer;
			System.out.println("The order to set the position of the player is: ");
			
			tmpPlayer=this.game.getOrderPlayers().get(1);
			System.out.println("\t - 1.) " + tmpPlayer.getPlayerName() + " rolled a  " + tmpPlayer.getRolledFaceValue());
			tmpPlayer=this.game.getOrderPlayers().get(2);
			System.out.println("\t - 2.) " + tmpPlayer.getPlayerName() + " rolled a  " + tmpPlayer.getRolledFaceValue());
			tmpPlayer=this.game.getOrderPlayers().get(3);
			System.out.println("\t - 3.) " + tmpPlayer.getPlayerName() + " rolled a  " + tmpPlayer.getRolledFaceValue());
		}
		
		private void printOutSummaryOrderToPlay() {
			Player tmpPlayer;
			System.out.println("The order on the ladder is: ");
			
			tmpPlayer=this.game.getOrderPlayers().get(1);
			System.out.println("\t - Square 1: " + tmpPlayer.getPlayerName());
			tmpPlayer=this.game.getOrderPlayers().get(2);
			System.out.println("\t - Square 2: " + tmpPlayer.getPlayerName());
			tmpPlayer=this.game.getOrderPlayers().get(3);
			System.out.println("\t - Square 3: " + tmpPlayer.getPlayerName());
		}

		public void checkInput(String input)
		{
			if (input.length()==1)
			{
				char a=input.charAt(0);
				
				switch (a)
				{
					case 'r':
					{
						this.doRoll();
						
						break;
					}
					default:
					{
						this.doErrorInputTryAgainPhaseSpecific();
					}
				}
			}
			
		}
		
		public Boolean checkInputPos(String posStr)
		{
			int pos=0;
			
			try
			{
				pos=Integer.parseInt(posStr);
			}
			catch(NumberFormatException e)
			{
				System.out.println(e +" please type in a valid number!");
			}
			LadderSquare ladderSquare=this.game.getBoard().getLadderSquare(pos);
			
			if (pos<4 && pos>0)
			{
				if (ladderSquare.isEmpy())
				{
					this.doSetPlayerStartPosition(pos);
					System.out.println("You are now on ladderSquare nr. "+ pos);
					return true;
				}
				else
				{
					System.out.println("Error: this pos is not empty! Please chose another startSquare on ladder. Try it again...");
					return false;
				}
			}
			else
			{
				System.out.println("Error: invalid pos! Position must between 1 and 3! Try it again...");
			}
			
			return true;
		}
	
	public void deactivate()
	{
		this.isActive=false;
	}
	
	//////////////////////
	////////UPDATE:///////
	//////////////////////
	public void doUpdate()
	{
		this.updateData();
		//this.updateComponents();
	}
	
	private void updateData()
	{
		this.actualPlayer=this.game.getActualPlayer();
	}
	
	

	
	
	/////////////////////////////////
	//SPECIAL METHODS OF THIS PHASE://
	/////////////////////////////////
	private void isNextPlayerEmptyForRadioButtons()
	{
		this.actualPlayer=this.game.getActualPlayer();
		
//		if (this.actualPlayer==this.game.getEmptyPlayer())
//		{
//			
//			//this.buttonLetsPlayPart2.setEnabled(true);
//			this.createOrderToPlay();
//			
//			this.game.skipEmptyPlayer();
//			
//		}
	}
	
	
	
	
	public void createOrderToPlay()
	{		
		ArrayList<Player> orderToPlay=new ArrayList<Player>();
		
		orderToPlay.add(this.game.getEmptyPlayer()); //HEAD
		
		for (int i=1; i<=this.game.getNumberOfPlayers();i++) //FIXED int i=0
		{
			LadderSquare ladderSquare=this.game.getBoard().getLadderSquare(i);
			Player playerOnSquare=ladderSquare.getPlayer();
			
			orderToPlay.add(playerOnSquare);
		}
		
		orderToPlay.add(this.game.getEmptyPlayer()); //TAIL
		
		this.game.setOrderPlayers(orderToPlay);		//IMPORTANT DON'T FORGET THIS!!!!!!!
	
	}
	public void createOrderToSetPlayers()
	{

		int numberOfPlayers=this.game.getNumberOfPlayers();
		
		long[] faceValueArray=new long[numberOfPlayers];
	
		
		//Create the array to sort:
		for(int i=0; i<this.game.getNumberOfPlayers();i++)
		{
			this.actualPlayer=this.game.getActualPlayer();
			faceValueArray[i]=(long) actualPlayer.getRolledFaceValue()*-1;		//*-1 because otherwise the order would be the wrong way after sorting
			
			this.game.nextPlayer();
			this.actualPlayer=this.game.getActualPlayer();
		}
			
		
		
		//sort the array
		Arrays.sort(faceValueArray);
		
	
		
		//create the correct ArrayList:
		
		ArrayList<Player> orderToSetPlayers=new ArrayList<Player>();
		
		orderToSetPlayers.add(this.game.getEmptyPlayer());		//HEAD
		
		
		for (int i=0; i<faceValueArray.length; i++)
		{	
			int faceValue= (int) faceValueArray[i]*-1;		//*-1 to get the original value
			
			if(this.playerBlue.getRolledFaceValue()==faceValue)
			{
				orderToSetPlayers.add(this.playerBlue);
			}
			else if(this.playerRed.getRolledFaceValue()==faceValue)
			{
				orderToSetPlayers.add(this.playerRed);
			}
			else if(this.playerYellow.getRolledFaceValue()==faceValue)
			{
				orderToSetPlayers.add(this.playerYellow);
			}
			else
			{
				
				System.out.println("Error (Phase0.class): exception in createOrderToSetPlayers()!");
				//TODO: Exception!
			}
		}
		
		orderToSetPlayers.add(this.game.getEmptyPlayer());		//TAIL
		
		

		this.game.setOrderPlayers(orderToSetPlayers); //Overwrite
		
		

		
		this.game.skipEmptyPlayer();

	}
	
	private boolean hasNextPlayer()
	{
		return game.getEmptyPlayer()!=game.getActualPlayer();
	}
	
	/**
	 * Checks if more than one player have the same value, set the new order of players who had the same value
	 * @return: if more than one player have the same faceValue, it will return true, else false
	 */
	public boolean hasMorePlayerSameValue()
	{
		this.tmpOrderOfPlayers=this.game.getOrderPlayers();	//Create a backup of the full order of players
		
		boolean hasMorePlayerSameValue=false;
		ArrayList<Player> playersSameValue=new ArrayList<Player>();
		
		playersSameValue.add(this.game.getEmptyPlayer());		//insert the head
		
		int faceValueBlue=this.playerBlue.getRolledFaceValue();
		int faceValueRed=this.playerRed.getRolledFaceValue();
		int faceValueYellow=this.playerYellow.getRolledFaceValue();
		
		if (faceValueBlue==faceValueRed)
		{
			hasMorePlayerSameValue=true;
			
			
			if (!playersSameValue.contains(playerBlue))
			{
				playersSameValue.add(playerBlue);
			}
			
			if (!playersSameValue.contains(playerRed))
			{
				playersSameValue.add(playerRed);
			}
		}
		

		if (faceValueBlue==faceValueYellow)
		{
			
			hasMorePlayerSameValue=true;
			
			if (!playersSameValue.contains(playerBlue))
			{
				playersSameValue.add(playerBlue);
			}
			
			if (!playersSameValue.contains(playerYellow))
			{
				playersSameValue.add(playerYellow);
			}
		}
		
		
		if (faceValueRed==faceValueYellow)
		{
			hasMorePlayerSameValue=true;
			

			if (!playersSameValue.contains(playerRed))
			{
				playersSameValue.add(playerRed);
			}
			
			if (!playersSameValue.contains(playerYellow))
			{
				playersSameValue.add(playerYellow);
			}
		}
				
			
		
		
		
		playersSameValue.add(this.game.getEmptyPlayer());		//insert the tail
		
		this.game.setOrderPlayers(playersSameValue); //Overwrite 
		

	
		
		return hasMorePlayerSameValue;
	}
	
	
	////////////
	//ACTIONS://
	////////////
	public void doCreateOrderToPlay()
	{
		this.isNextPlayerEmptyForRadioButtons();
	}
	
	public void doLetsPlay()
	{
		this.activePhase=GamePhases.phaseSetFirstAmeba;
		this.doUpdate();
	}
	
	
	
	public void doRoll()
	{

		int faceValue=this.die.roll();
		
		Player actualPlayer=this.game.getActualPlayer();
		
		actualPlayer.setRolledFaceValue(faceValue);
		
		this.game.nextPlayer();
		
//		if(!this.hasNextPlayer())		//If there is no next player
//		{
//			this.game.nextPlayer(); //skip the empty player and  begin so a new turn
//			
//			
//					
//		}
		this.doUpdate();
	}
	

	
	public void doLetsSetPlayers()
	{
		this.newOrderOfPlayers=new ArrayList<Player>();
		this.createOrderToSetPlayers();
		
		//this.setPanelPhase0Part2();
		this.doUpdate();
	}
	
	public void doGoBackToPart1()
	{
		this.setActivePart(GamePart.part1); //Go back to part1 of this phase
		//this.setPanelPhase0Part1();
		this.doUpdate();
	}
	
	public void doSetPlayerStartPosition(int pos)
	{
		this.actualPlayer=this.game.getActualPlayer();
		
		this.actualPlayer.setScore(pos);
		
		
		Board board=this.game.getBoard();
		LadderSquare ladderSquare=board.getLadderSquare(pos);
		ladderSquare.setPlayer(actualPlayer);
		
		
		//this.radioButtonStartPos[pos].setEnabled(false);
		
		this.game.nextPlayer();
		
		this.isNextPlayerEmptyForRadioButtons();
		
		this.doUpdate();
	}
	
	
	
	
	public void doNoMorePlayerHaveSameValue() {
		//Overwrite the tmpOrderOfPlayers: (NEW)
		this.tmpOrderOfPlayers.clear();
		
		this.tmpOrderOfPlayers.add(this.game.getEmptyPlayer()); //HEAD
		this.tmpOrderOfPlayers.add(this.playerBlue);
		this.tmpOrderOfPlayers.add(this.playerRed);
		this.tmpOrderOfPlayers.add(this.playerYellow);
		this.tmpOrderOfPlayers.add(this.game.getEmptyPlayer()); //TAIL
		
		
		this.game.setOrderPlayers(this.tmpOrderOfPlayers);	//Overwrite the old, uncompleted list with the original list
	
		
	}

	@Override
	public void doGoToAboutPhase() {
		this.activePhase=GamePhases.phaseAbout;
	}



	@Override
	public void doGoToCheatPhase() {
		this.activePhase=GamePhases.phaseCheat;
	}



	@Override
	public void doGoToExitPhase() {
		//TODO
		System.out.println("Bye...");
		System.exit(0);
	}



	@Override
	public void doGoToLoadPhase() {
		//TODO
		System.out.println("Go to load...");
		
	}



	@Override
	public void doGoToNextPhase() {
	 //do nothing
		
	}



	@Override
	public void doGoToPreviousPhase() {
		//do nothing
		
	}



	@Override
	public void doGoToSavePhase() {
		//TODO
		System.out.println("Go to save...");
		
	}



	@Override
	public void doGoToSummaryPhase() {
		//TODO
		System.out.println("Go to summary...");
		
	}



	@Override
	public void doGoToWelcomePhase()
	{
		this.activePhase=GamePhases.phaseWelcome;
	}



	@Override
	public void doErrorInputTryAgainAllPhases(String input) 
	{
		this.checkInput(input);
		
	}



	@Override
	public void doErrorInputTryAgainPhaseSpecific() {
		System.out.println("ERROR: Unkown instruction, please try it again...");
		this.doPrintMessage();
		
	}

		
		
		
		////////////////
		//UPDATE DATA://	//do this in every case!
		////////////////
		
		
	
	
	
	/////////////////////
	//ONLY FOR TESTING://
	/////////////////////
	
	
		//////////////////////
		//GETTERS & SETTERS://
		//////////////////////
			
			////////////
			//GETTERS://
			////////////
		
		

			////////////
			//*LABELS*//
			////////////
	
		
			
			
			
			/////////////
			//*BUTTONS*//
			/////////////
		
			
	
			////////////////
			//*TEXTFIELDS*//
			////////////////
	
			///////////////////
			//*RADIO BUTTONS*//
			///////////////////
		
			////////////
			//SETTERS://
			////////////
		
		
		
			////////////
			//*LABELS*//
			////////////
		
		
			
			
			
			/////////////
			//*BUTTONS*//
			/////////////
		
		
			////////////////
			//*TEXTFIELDS*//
			////////////////
			
			
	
	
	
		/////////////////
		//FAKED EVENTS://
		/////////////////
		
			
			/////////////
			//*BUTTONS*//
			////////////
		
		

			///////////////////
			//*RADIO BUTTONS*//
			///////////////////
	
		
			////////////////
			//*TEXTFIELDS*//
			////////////////
			
}
