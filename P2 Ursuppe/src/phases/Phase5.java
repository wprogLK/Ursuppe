package phases;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;


import javax.swing.*;

import phasesGUI.Phase2GUI;
import phasesGUI.Phase5GUI;

import Components.Ameba;
import Components.Board;
import Components.Die;
import Components.ISquare;
import Components.Player;
import Components.SoupSquare;

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
public class Phase5 
{
	//BASIC THINGS OF THIS GUI:
	private boolean isActive;		//if this PhaseGui is the actual gui, the "showing gui"
	private GamePhases activePhase;	//this is IMPORTANT!
	private Ameba actualAmeba;
	private Ameba deathAmeba;
	private ArrayList<Ameba> amebasToDie=new ArrayList<Ameba>();
	private int nrOfFoodAfterDie=2;
	
	private Phase5GUI phaseGUI;
		
		
		//SPECIAL COMPONENTS:
		private Board board;
		private Die die;
		
	//VARIABLES OF THIS PHASE:
	
	private Player playerBlue;
	private Player playerRed;
	private Player playerYellow;
	
	private Player actualPlayer;
	
	private String gameTitle;
	private String phaseTitle;
	
	private Game game;
	
	private GameReadDirection readDirection;
	
	
	//TIMER
	private int deathIntervall=2000; //Milliseconds between checks (2000ms=2sec)
	private Timer deathTimer;
	
	/**
	 * Constructor
	 */
	public Phase5(Game game) {
		//ARGUMENTES OF THE CONSTRUCTOR:
		this.game=game;
		this.board=game.getBoard();
		
		this.actualAmeba=game.getPlayer(GameColor.blue).getAmebasOffBoard().get(1);
		
		init(game);
			
		boolean createGUI=ReadShowGUIOrNormal.read();
		
		if (createGUI)
		{
			this.phaseGUI = new Phase5GUI(this.game,this);
		}
		
		
			
			//this.updateData();
		
		//TIMER:
			//this.deathTimer=new Timer(this.deathIntervall,this);
			
			
		
	}

	private void init(Game game) {
		//SET THE PHASE TO THE GAME:
		this.readDirection=GamePhases.phase5.getReadDirection();
		this.game.setReadDirection(readDirection);
		this.phaseTitle="Phase 5: death";
		
		//CLASS VARIABLES:
		this.isActive=false;
		this.gameTitle=game.getGameTitle();
		this.actualPlayer=game.getActualPlayer();
		
		this.playerBlue=game.getPlayer(GameColor.blue);
		this.playerRed=game.getPlayer(GameColor.red);
		this.playerYellow=game.getPlayer(GameColor.yellow);
		
		this.die=this.game.getDie();
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
		public GamePhases getActivePhase()
		{
			return this.activePhase;
		}
		
		public int getNrOfFoodAfterDie()
		{
			return this.nrOfFoodAfterDie;
		}
		
		
		
		////////////
		//SETTERS://
		////////////
		
		public void setNrOfFoodAfterDie(int nr)
		{
			this.nrOfFoodAfterDie=nr;
		}
		
	
	
	
	
	
	///////////////////////////////////
	////////ACTIVATE/DEACTIVATE:///////
	///////////////////////////////////

	public void deactivate()
	{
		this.isActive=false;
			
	}
	
	public void activate()
	{
		//this.deathTimer.start();

		this.activePhase=GamePhases.phaseEmpty;
		this.isActive=true;
		//this.setVisible(true);
		//doUpdate();
		
		//this.add(this.board);	//IMPORTANT!!
		
		
		//this.buttonGoToPhase6.setEnabled(false);
			
		if (this.phaseGUI!=null)
		{
			this.phaseGUI.activate();
			doUpdate();
		}
		else
		{
			System.out.println("\t Phase 5: Death");
			doPrintMessage();
			
			
			doUpdate();
		}
	
		
	}
	
	public void doPrintMessage()
	{
			System.out.println("You can enter: \t e - exit \n \t w - go to Welcome phase \n \t b - go one phase back");
			
			
			
			for (int i=0;i<this.game.getNumberOfPlayers();i++)
			{
				//this.doPrintBoard();
				System.out.println("Player " + this.actualPlayer.getPlayerName() + " is on turn now: ");
				
				this.loadAmebasToDie();
				
				
					
				
				System.out.println("You have following amebas on board before checking: " + this.actualPlayer.getAmebasOnBoard());
				String input=UserInput.readInput("Press 'd' for checking if an ameba of you will die");
					
				
				checkInput(input);
				this.doUpdate();
				
			}
			System.out.println("GO TO PHASE 6 FROM DO PRINT MESSAGE...");
			this.doGoToPhase6();
			//this.doUpdate();
			//this.doGoToPhase2();
			
			
			
	
	}
	
	private void doPrintBoard() {
		System.out.println(this.game.getInformationAboutPlayers());
		
		System.out.println();
		System.out.println();
		
		System.out.println(this.game.getBoardAsString());
		
	}

	public void checkInput(String input)
	{
		if (input.length()==1)
		{
			char a=input.charAt(0);
			
			switch (a)
			{
				case 'w':
				{
					this.doGoToWelcome();   
					
					break;
				}
				case 'd':
				{
					this.doDeathCheck();   
					
					break;
				}
				case 'b':
				{
					this.doGoOnePhaseBack();
					
					break;
				}
				case 'e':
				{
					System.out.println("bye...");
					System.exit(0);
					
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
	
	
	

	
	/////////////////////////////
	////////CREATE PANELS:///////
	/////////////////////////////
	
	
	/////////////////////////////////
	//SPECIAL METHODS OF THIS PHASE://
	/////////////////////////////////
	
		
		
	private void loadAmebasToDie()
	{
		assert this.amebasToDie.isEmpty();
		
		int totalNumberOfAmebas=this.actualPlayer.getNumbersOfAmebasOnBoard()+this.actualPlayer.getNumbersOfAmebasOffBoard();
		
		for(int i=1; i<=totalNumberOfAmebas;i++)
		{
			this.actualAmeba=this.actualPlayer.getAmebaWithNumber(i);
			this.actualAmeba.checkIsDeath();
			
			assert this.actualAmeba.getNumber()==i;
			
			if (this.actualPlayer.getAmebasOnBoard().contains(this.actualAmeba) && this.actualAmeba.isDead() && !this.amebasToDie.contains(this.actualAmeba) )
			{
				this.amebasToDie.add(this.actualAmeba);
			}
			
		}
	}
	
	
	///////////
	//ACTIONS://
	////////////

	public void doGoToPhase6()
	{
		System.out.println("GO TO PHASE 6   ");
		this.activePhase=GamePhases.phase6;
	}
	
	public void doExit()
	{
		System.out.println("Bye...");
		
		System.exit(0);
	}
	
	public void doGoToWelcome()
	{
		this.activePhase=GamePhases.phaseWelcome;
	}
	
	public void doGoOnePhaseBack()
	{
		this.activePhase=GamePhases.phase4;
	}
	
	public void doDeathCheck()
	{
		//if (this.amebasToDie.isEmpty())
		//{
			
		//}
		
		if (this.amebasToDie.isEmpty())
		{
			//this.buttonGoToPhase6.setEnabled(true);
			System.out.println("GO TO PHASE 6 FROM DEATH CHECK...");
			this.doGoToPhase6();
		}
		else
		{
			
			//this.buttonGoToPhase6.setEnabled(false);
			
			//AMEBA DIE
			while(!this.amebasToDie.isEmpty())
			{
				System.out.println("AMEBAS TO DIE: " + this.amebasToDie);
				this.deathAmeba=this.amebasToDie.get(0);
				
				this.actualPlayer.addAmebaToOffBoard(this.deathAmeba);
				
				System.out.println(this.actualPlayer.getPlayerName()+ " your ameba " + this.deathAmeba.getNumber() + " is dead now...");
				
				this.amebasToDie.remove(this.deathAmeba);
				Point2D squarePos=this.deathAmeba.getSquarePosition();
				
				ISquare aSquare=this.game.getBoard().getSquare((int) squarePos.getX(), (int) squarePos.getY());
				SoupSquare soupSquare=(SoupSquare) aSquare;
				
				soupSquare.removeAmeba(this.deathAmeba);
				
				soupSquare.createFoodOfColor(GameColor.blue, this.nrOfFoodAfterDie);
				soupSquare.createFoodOfColor(GameColor.red, this.nrOfFoodAfterDie);
				soupSquare.createFoodOfColor(GameColor.yellow, this.nrOfFoodAfterDie);
			}
			System.out.println("You have following amebas on board after checking: " + this.actualPlayer.getAmebasOnBoard());
			
			this.doPrintBoard();
			
			this.game.nextPlayer();
			this.actualPlayer=this.game.getActualPlayer();
			
			this.loadAmebasToDie();
		}
		
		this.doUpdate();
		
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
