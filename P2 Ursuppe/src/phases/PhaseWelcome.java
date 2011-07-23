package phases;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;


import javax.swing.*;

import phasesGUI.PhaseWelcomeGUI;

import Components.Ameba;
import GUIComponents.UrsuppeGUI;

import enums.*;
import game.*;
import helpClasses.Calc;
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
public class PhaseWelcome implements IPhase
{
	//BASIC THINGS OF THIS GUI:
	private boolean isActive;		//if this PhaseGui is the actual gui, the "showing gui"
	private GamePhases activePhase;	//this is IMPORTANT!
	
	private PhaseWelcomeGUI phaseGUI;
	
	

	private Game game;
	
	private GameReadDirection readDirection;
	
	/**
	 * Constructor
	 */
	public PhaseWelcome(Game game) {
		
		
		//ARGUMENTES OF THE CONSTRUCTOR:
		this.game=game;
		
		init();
			

		boolean createGUI=ReadShowGUIOrNormal.read();
		
		if (createGUI)
		{
			this.phaseGUI = new PhaseWelcomeGUI(this.game,this);
		}
		
		
	}



	private void init() {
		//SET THE PHASE TO THE GAME:
		this.readDirection=GamePhases.phaseWelcome.getReadDirection();
		this.game.setReadDirection(readDirection);
		
		
		//CLASS VARIABLES:
		this.isActive=false;
		
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
		
		
		
		////////////
		//SETTERS://
		////////////
	
		
	
	
	
	
	
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
			System.out.println("\t PHASE WELCOME");
			doPrintMessage();
			
			
			doUpdate();
		}
	}
	
	public void doPrintMessage()
	{
		System.out.println("Welcome to Ursuppe! \n Please enter what you want to do: \n \t - <n> new Game \n");
		System.out.println(InterpretAndRunBasicUserInput.possibleStandardInstructions());
		
		String input=UserInput.readInput("What do you want?");

		
			
			InterpretAndRunBasicUserInput.readAndInterpretInput(input, this);
			
			//checkInput(input);
			
			
			
	
	}
	
	public void checkInput(String input)
	{
		if (input.length()==1)
		{
			char a=input.charAt(0);
			
			switch (a)
			{
				case 'n':
				{
					System.out.println("start new game...");
					this.doStartNewGame();
					
					break;
				}
				default:
				{
					this.doErrorInputTryAgainPhaseSpecific();
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
	
	/////////////////////////////////
	//SPECIAL METHODS OF THIS PHASE://
	/////////////////////////////////
	

	///////////
	//ACTIONS://
	////////////

	public void doExit()
	{
		System.exit(0);
		this.doUpdate();
	}
	
	public void doAbout()
	{
		this.activePhase=GamePhases.phaseAbout;
		this.doUpdate();
	}
	
	public void doHelp()
	{
		//TODO
		this.doUpdate();
	}
	
	public void doLoadGame()
	{
		//TODO
		this.doUpdate();
	}
	
	public void doStartNewGame()
	{
		this.activePhase=GamePhases.phaseSetNames;
		this.doUpdate();
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
	public void doGoToWelcomePhase() {
		//do nothing, you are already here
		
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
