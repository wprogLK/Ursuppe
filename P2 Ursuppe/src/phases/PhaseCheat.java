package phases;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;


import javax.swing.*;

import phasesGUI.Phase0GUI;
import phasesGUI.PhaseCheatGUI;


import cheats.cheatSetPlayerName;

import enums.*;
import exceptions.ExceptionCheatInputInvalid;
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
public class PhaseCheat
{
	//BASIC THINGS OF THIS GUI:
	private boolean isActive;		//if this PhaseGui is the actual gui, the "showing gui"
	private GamePhases activePhase;	//this is IMPORTANT!
	private GamePhases beforePhase;
	private String asciiOutput="*ASCII OUTPUT*";
	private String textOutput="*TEXT OUTPUT*";
	
	private PhaseCheatGUI phaseGUI;

		
		//SPECIAL COMPONENTS:
	
		
	//VARIABLES OF THIS PHASE:
	
	
	
	private String gameTitle;
	private String phaseTitle;
	
	private Game game;
	
	private GameReadDirection readDirection;
	private String input;
	
	/**
	 * Constructor
	 */
	public PhaseCheat(Game game) {
		//ARGUMENTES OF THE CONSTRUCTOR:
		this.game=game;

		//SET THE PHASE TO THE GAME:
	
	
		this.phaseTitle="CHEATS";
		
		//CLASS VARIABLES:
		this.isActive=false;
		this.gameTitle=game.getGameTitle();
	
		//IMPORTANT STUFF:
		this.activePhase=GamePhases.phaseEmpty;		
		
		boolean createGUI=ReadShowGUIOrNormal.read();

		if (createGUI)
		{
			this.phaseGUI = new PhaseCheatGUI(this.game,this);
		}
		
		
			
			this.doUpdate();
			
		//CREATE ALL PANELS AND COMPONENTES:
			
	
			
			
		
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
			System.out.println("\t CHEAT");
			doPrintMessage();
			
			
			doUpdate();
		}
	}
	
	
	public void setBeforePhase(GamePhases beforePhase)
	{
		this.beforePhase=beforePhase;
	}
	
	private void doPrintMessage() {
		System.out.println("You can enter: \t e - exit \n \t w - go to Welcome phase \n \t b - go one phase back");


		this.input=UserInput.readInput("Enter a valid cheat: ");
		this.doCheat();	
		
		System.out.println("TEXT OUTPUT: " + this.textOutput);
		System.out.println("ASCII OUTPUT: " + this.asciiOutput);
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
		//this.scrollingTextAreaOutput.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		//this.scrollingASCIIOutput.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	}
	
	
	
	
	
	/////////////////////////////////
	//SPECIAL METHODS OF THIS PHASE://
	/////////////////////////////////
	private void checkValidInput(String input)
	{
		try 
	    { 
			String[] cheatInstruction;
			cheatInstruction=input.split(" ");
			
			String mainInstruction=cheatInstruction[0]; //The mainInstruction contains the name of the cheat
			
			int numberOfArguments=cheatInstruction.length;
			
			if (input.length()==1)		// it's a non-cheat instruction
			{
				char a=input.charAt(0);
				
				switch (a)
				{
					case 'w':
					{
						this.doGoToWelcome();   
						
						break;
					}
					case 'b':
					{
						this.doGoBack();
						
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
			else		//It's a cheat
			{
				if (mainInstruction.matches(GameCheats.myNameIs.toString()) && numberOfArguments==3)
				{
					String output="";
					
					output=cheatSetPlayerName.cheatSetPlayerName(cheatInstruction[1], cheatInstruction[2], this.game);
					
					this.textOutput=this.textOutput+output;
				}
				else if (mainInstruction.matches(GameCheats.readCheatBook.toString()) && numberOfArguments==1)
				{
					for (GameCheats cheat : GameCheats.values()) {
						this.textOutput=this.textOutput+cheat.getInfo();
					}
				
				}		//IMPLEMENT HERE MORE CHEATS
				else
				{
					throw new ExceptionCheatInputInvalid("No correct cheatname or wrong number of arguments" );
				}
			}
	    } 
	    catch (ArrayIndexOutOfBoundsException a) 
	    { 
	    	this.textOutput=this.textOutput+"\n Missing arguments \n";
	    } 
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	private void doGoToWelcome() {
		this.activePhase=GamePhases.phaseWelcome;
		
	}

	private void doGoBack() {
		this.activePhase=this.beforePhase;
	}

	///////////
	//ACTIONS://
	////////////
	public void doExit()
	{
		System.out.println("Bye...");
		System.exit(0);
	}
	
	public void doCheat()
	{
		try 
	    { 
			this.checkValidInput(this.input);
	    } 
	    catch (ExceptionCheatInputInvalid a) 
	    { 
	    	this.textOutput=this.textOutput+"\n " + a;
	    } 
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
	
//			public boolean getTextFieldNrOfBlueFoodToEatIsEnabled()
//			{
//				return this.textFieldNrOfBlueFoodToEat.isEnabled();
//			}
		
			
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
			

//			public void setTexttextFieldNrOfBlueFoodToEat(String text)
//			{
//				this.textFieldNrOfBlueFoodToEat.setText(text);
//			}

		
	
	
	
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
