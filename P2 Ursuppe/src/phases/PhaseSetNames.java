package phases;
import game.Game;
import helpClasses.ReadShowGUIOrNormal;
import helpClasses.UserInput;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import phasesGUI.PhaseAboutGUI;
import phasesGUI.PhaseSetNamesGUI;

import Components.Player;

import enums.GameColor;
import enums.GamePhases;


/**
 * 
 */

/**
 * @author lukas
 *
 */
public class PhaseSetNames 
{
	//BASIC THINGS OF THIS GUI:
	private boolean isActive;		//if this PhaseGui is the actual gui, the "showing gui"
	private GamePhases activePhase;		//this is IMPORTANT!
	private Game game;
	
	private PhaseSetNamesGUI phaseGUI;
		
	//VARIABLES OF THIS PHASE:
	private String namePlayerBlue;
	private String namePlayerRed;
	private String namePlayerYellow;
	
	private String gameTitle;
	private String phaseTitle;
	
	
	
	/**
	 * Constructor
	 */
	public PhaseSetNames(Game game) {
		//ARGUMENTES OF THE CONSTRUCTOR:
		this.game=game;
		this.gameTitle=game.getGameTitle();
		
		init();
			
		boolean createGUI=ReadShowGUIOrNormal.read();

		if (createGUI)
		{
			this.phaseGUI = new PhaseSetNamesGUI(this.game,this);
		}
	
	}

	private void init() {
		//CLASS VARIABLES:
		this.isActive=false;
		
		//IMPORTANT STUFF:
		this.activePhase=GamePhases.phaseEmpty;		
		
	}

	//////////////////////
	//GETTERS & SETTERS://
	//////////////////////
	
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
			System.out.println("\t Phase Set Names:");
			doPrintMessage();
			
			
			doUpdate();
		}
	}
	
	public void doPrintMessage()
	{
			this.namePlayerBlue=UserInput.readInput("name Player blue: ");
			this.namePlayerRed=UserInput.readInput("name Player red: ");
			this.namePlayerYellow=UserInput.readInput("name Player yellow: ");
			
			String input=UserInput.readInput("Type in 'o' for ok , type in 'e' for exit or type in 'b' to go back to the welcome phase");
			
			checkInput(input);
	
	}
	
	public void checkInput(String input)
	{
		if (input.length()==1)
		{
			char a=input.charAt(0);
			
			switch (a)
			{
				case 'o':
				{
					this.doOK();
					
					break;
				}
				case 'b':
				{
					this.doGoBackToWelcome();
					
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
	
	}
	
	private void updateData()
	{
		
	}
	
	

	
	/**
	 * update dates in the game IMPORTANT (also for every other phase!!!) !!
	 */
	private void updateGame()
	{
	
		
	
		
		setPlayerName();
		
		//System.out.println("PHASE SET NAME: UPDATE GAMES: name player blue: " + playerBlue.getPlayerName());
		
		
		
	}

	private void setPlayerName() {
		Player playerBlue=this.game.getPlayer(GameColor.blue);
		Player playerRed=this.game.getPlayer(GameColor.red);
		Player playerYellow=this.game.getPlayer(GameColor.yellow);
		
		playerBlue.setPlayerName(this.namePlayerBlue);
		playerRed.setPlayerName(this.namePlayerRed);
		playerYellow.setPlayerName(this.namePlayerYellow);
	}


	
	/////////////////////////////
	////////CREATE PANELS:///////
	/////////////////////////////
	
	/**
	 * call createPanelGUI1(), createPanelButtons(), createPanelLabels()
	 * this method will be called by the constructor
	 */
	
	////////////////////////////
	//ACTIONS://
	////////////
	
	public void doOK()
	{
		this.updateGame();
		this.activePhase=GamePhases.phase0;
		
	}
	
	public void doExit()
	{
		System.out.println("bye ...");
		
		System.exit(0);
	}
	
	public void doGoBackToWelcome()
	{
		this.activePhase=GamePhases.phaseWelcome;
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
