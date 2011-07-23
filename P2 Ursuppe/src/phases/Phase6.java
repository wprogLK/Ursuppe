package phases;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;


import javax.swing.*;

import phasesGUI.Phase5GUI;
import phasesGUI.Phase6GUI;

import Components.Board;
import Components.Die;
import Components.Player;

import enums.*;
import game.*;
import helpClasses.Calc;
import helpClasses.ReadShowGUIOrNormal;
import helpClasses.UserInput;



/**
 * 
 */

/**
 * @author lukas
 *
 */
public class Phase6
{
	//BASIC THINGS OF THIS GUI:
	private boolean isActive;		//if this PhaseGui is the actual gui, the "showing gui"
	private GamePhases activePhase;	//this is IMPORTANT!
	
	
		
		//SPECIAL COMPONENTS:
		private Board board;

		
	//VARIABLES OF THIS PHASE:
	
	private Player playerBlue;
	private Player playerRed;
	private Player playerYellow;
	
	private Player actualPlayer;
	
	private String gameTitle;
	private String phaseTitle;
	
	private Game game;
	
	private GameReadDirection readDirection;
	private Phase6GUI phaseGUI;
	
	/**
	 * Constructor
	 */
	public Phase6(Game game) {
		//ARGUMENTES OF THE CONSTRUCTOR:
		this.game=game;
		this.board=game.getBoard();
		
		init(game);
	
		boolean createGUI=ReadShowGUIOrNormal.read();
		
		if (createGUI)
		{
			this.phaseGUI = new Phase6GUI(this.game,this);
		}
			
			//this.updateData();
			
		//CREATE ALL PANELS AND COMPONENTES:
	
	
		
			
		
	}

	private void init(Game game) {
		
		//SET THE PHASE TO THE GAME:
		this.readDirection=GamePhases.phase6.getReadDirection();
		this.game.setReadDirection(readDirection);
		this.phaseTitle="Phase 6: score";
		
		//CLASS VARIABLES:
		this.isActive=false;
		this.gameTitle=game.getGameTitle();
		this.actualPlayer=game.getActualPlayer();
		
		getPlayerColor(game);
		
		
		//IMPORTANT STUFF:
		this.activePhase=GamePhases.phaseEmpty;		
		
		
	}

	private void getPlayerColor(Game game) {
		this.playerBlue=game.getPlayer(GameColor.blue);
		this.playerRed=game.getPlayer(GameColor.red);
		this.playerYellow=game.getPlayer(GameColor.yellow);
	}
	
	//////////////////////
	//GETTERS & SETTERS://
	//////////////////////
	
		////////////
		//GETTERS://
		////////////
		
		//to communicate with the GUILOGIC:
		public GamePhases getActivePhase()
		{
			return this.activePhase;
		}
		
		
		
		////////////
		//SETTERS://
		////////////
	
		
	
	
	
	
	
	///////////////////////////////////
	////////ACTIVATE/DEACTIVATE:///////
	///////////////////////////////////
	public void activate()
	{
		this.activePhase=GamePhases.phaseEmpty;
		this.isActive=true;
		
		//this.buttonGoToPhase1.setEnabled(false);
		if (this.phaseGUI!=null)
		{
			this.phaseGUI.activate();
			doUpdate();
			calcAllScores();
			
			calcNewScoreLadder();
			
			System.out.println("SCORE AFTER: BLUE "+ this.playerBlue.getScore());
			System.out.println("SCORE AFTER: RED "+ this.playerRed.getScore());
			System.out.println("SCORE AFTER: YELLOW "+ this.playerYellow.getScore());
		
			this.doCheckWinner();
		}
		else
		{
			System.out.println("\t Phase 6: Calculate score");
			//this.setVisible(true);
			doUpdate();
			
			//this.add(this.board);	//IMPORTANT!!
			
			System.out.println("SCORE BEFORE: BLUE "+ this.playerBlue.getScore());
			System.out.println("SCORE BEFORE: RED "+ this.playerRed.getScore());
			System.out.println("SCORE BEFORE: YELLOW "+ this.playerYellow.getScore());
			
			System.out.println("ORDER OF PLAYER BEFORE CALCULATING NEW SCORE " + this.game.getOrderPlayers());
			
			doPrintMessage();
				
			
			calcAllScores();
			
			calcNewScoreLadder();
			
			System.out.println("ORDER OF PLAYER AFTER CALCULATING NEW SCORE " + this.game.getOrderPlayers());
			System.out.println("AFTER calculating:");
			this.doPrintBoard();
			
			System.out.println("SCORE AFTER: BLUE "+ this.playerBlue.getScore());
			System.out.println("SCORE AFTER: RED "+ this.playerRed.getScore());
			System.out.println("SCORE AFTER: YELLOW "+ this.playerYellow.getScore());
		
			this.doCheckWinner();
		}
	
	}

	public void doPrintMessage()
	{
			System.out.println("You can enter: \t e - exit \n \t w - go to Welcome phase \n \t b - go one phase back");
			
			System.out.println("BEFORE calculating:");
			this.doPrintBoard();
				
			String input=UserInput.readInput("Press 'c' for calculating the new score");
					
			checkInput(input);
			this.doUpdate();
				
		

			
			
	
	}
	
	private void checkInput(String input) {
		if (input.length()==1)
		{
			char a=input.charAt(0);
			
			switch (a)
			{
				case 'w':
				{
					this.doGoToWelcomePhase();   
					
					break;
				}
				case 'c':
				{
					//do it   
					
					break;
				}
				case 'b':
				{
					this.doGoOnePhaseBack();
					
					break;
				}
				case 'e':
				{
					this.doExit();
					
					break;
				}
				default:
				{
					System.out.println("ERROR: Unkown instruction, please try it again...");
					this.doPrintMessage();
				}
			}
		}
		
	}

	private void doPrintBoard() {
		System.out.println(this.game.getInformationAboutPlayers());
		
		System.out.println();
		System.out.println();
		
		System.out.println(this.game.getBoardAsString());
		
	}

	private void calcNewScoreLadder() {
		this.actualPlayer=this.game.getActualPlayer();
		ArrayList<Player> players=new ArrayList<Player>();
		
		for (int i=0; i<this.game.getNumberOfPlayers();i++)
		{
			players.add(this.game.getOrderPlayers().get(i));
		}
		
		
		int count=0;
		while(count<this.game.getNumberOfPlayers())
		{
			if (!this.actualPlayer.equals(this.game.getEmptyPlayer()))
			{
				
				Player player=null;
				
				for (int i=0; i<players.size();i++)
				{
					player=players.get(i);
					
					if(!player.equals(this.game.getEmptyPlayer()) && !player.equals(this.actualPlayer) && player.getScore()==this.actualPlayer.getScore())
					{
						this.actualPlayer.addScore(1);
					}
				}
			}
			else
			{
				//"skip" empty player and do nothing
			}
			
			
			this.game.nextPlayer();
			this.actualPlayer=this.game.getActualPlayer();
			count++;
		}
		
	
	
		this.doUpdate();
		
		//this.buttonGoToPhase1.setEnabled(true);
	}

	private void calcAllScores() {
		this.calcScore(this.playerBlue);
		this.calcScore(this.playerRed);
		this.calcScore(this.playerYellow);
	}
	
	public void deactivate()
	{
		this.isActive=false;
		//this.setVisible(false);
	}
	
	//////////////////////
	////////UPDATE:///////
	//////////////////////
	public void doUpdate()
	{
		this.updateData();
		
	}
	
	private void updateData()
	{
	
		
		this.game.skipEmptyPlayer();
		
		assert !game.getActualPlayer().equals(this.game.getEmptyPlayer());
		
		
		///////////////////////////
		//UPDATE CLASS VARIABLES://
		///////////////////////////
		
		
		
		this.actualPlayer=this.game.getActualPlayer();
	}

	
	
	
	
	
	
	
	
	


	
	
	
	/////////////////////////////////
	//SPECIAL METHODS OF THIS PHASE://
	/////////////////////////////////
	
		
	public void calcScore(Player player)
	{
		int scoreToAdd=0;
		
		int numberOfAmebasOnBoard=player.getAmebasOnBoard().size();
		int scoreOfGenes=player.getScoreOfGenes();
		
		//Calc numberOfAmebasOnBoard into Score
		scoreToAdd = calcScoreOfAmebas(scoreToAdd, numberOfAmebasOnBoard);
		
		//Calc scoreOfGenes into Score
		scoreToAdd = calcScoreGenes(scoreToAdd, scoreOfGenes);
		
		player.addScore(scoreToAdd);
		
		
		
		
	}

	private int calcScoreGenes(int scoreToAdd, int scoreOfGenes) {
		if(scoreOfGenes>=6)
		{
			scoreToAdd=scoreToAdd+4;
		}
		else if(scoreOfGenes==5)
		{
			scoreToAdd=scoreToAdd+3;
		}
		else if(scoreOfGenes==4)
		{
			scoreToAdd=scoreToAdd+2;
		}
		else if(scoreOfGenes==3)
		{
			scoreToAdd=scoreToAdd+1;
		}
		else
		{
			scoreToAdd=scoreToAdd+0;
		}
		return scoreToAdd;
	}

	private int calcScoreOfAmebas(int scoreToAdd, int numberOfAmebasOnBoard) {
		if(numberOfAmebasOnBoard==7)
		{
			scoreToAdd=scoreToAdd+6;
		}
		else if(numberOfAmebasOnBoard==6)
		{
			scoreToAdd=scoreToAdd+5;
		}
		else if(numberOfAmebasOnBoard==5)
		{
			scoreToAdd=scoreToAdd+4;
		}
		else if(numberOfAmebasOnBoard==4)
		{
			scoreToAdd=scoreToAdd+2;
		}
		else if(numberOfAmebasOnBoard==3)
		{
			scoreToAdd=scoreToAdd+1;
		}
		else
		{
			scoreToAdd=scoreToAdd+0;
		}
		return scoreToAdd;
	}
	
	private void doCheckWinner()
	{
		boolean winnerExists=false;
		
		assert (!this.actualPlayer.equals(this.game.getEmptyPlayer()));
		System.out.println("READ DIRECTION FOR CHECKING WINNER: " + this.game.getReadDirection());
		System.out.println("ORDER OF PLAYER FOR CHECKING WINNER: " + this.game.getOrderPlayers());
		while(!this.actualPlayer.equals(this.game.getEmptyPlayer()) || !winnerExists)
		{
			if (this.actualPlayer.getIsOnADarkSquare())
			{
				winnerExists=true;
				this.actualPlayer.setIsWinner();
				System.out.println("Player " +this.actualPlayer.getPlayerName() + " won the game!");
				this.game.setIsOver();
			}
		}
	}
	
	
	
	

	
	
	
	
	
	
	
	
	///////////
	//ACTIONS://
	////////////
	public void doGoToPhase1()
	{
		this.game.addPlayedRound();
		this.activePhase=GamePhases.phase1;
	}
	
	public void doExit()
	{
		System.out.println("Bye...");
		System.exit(0);
	}
	
	public void doGoOnePhaseBack()
	{
		this.activePhase=GamePhases.phase5;
	}
	
	public void doGoToWelcomePhase()
	{
		this.activePhase=GamePhases.phaseWelcome;
	}
	
	public void doGoToResultPhase()
	{
//		this.activePhase=GamePhases.phaseResult;	//TODO
	}
	
	
	
		
		
	
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
