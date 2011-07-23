package phases;

import java.awt.event.*;
import java.util.ArrayList;



import javax.swing.*;

import phasesGUI.PhaseAboutGUI;
import phasesGUI.PhaseWelcomeGUI;

import Components.Ameba;

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
public class PhaseAbout
{
	//BASIC THINGS OF THIS GUI:
	private boolean isActive;		//if this PhaseGui is the actual gui, the "showing gui"
	private GamePhases activePhase;	//this is IMPORTANT!
	private Ameba actualAmeba;
	private int playedPlayer=0;
	private ArrayList<Ameba> amebaOfActualPlayerOnBoard=new ArrayList<Ameba>();
	
	private PhaseAboutGUI phaseGUI;
	
	private String stringAbout;
	private Game game;
	
	private GameReadDirection readDirection;
	private GamePhases phaseBefore;
	
	/**
	 * Constructor
	 */
	public PhaseAbout(Game game) {
		//ARGUMENTES OF THE CONSTRUCTOR:
		this.game=game;
		
	
	
		
		
		init();		
		
	
		this.doUpdate();
	
	
		boolean createGUI=ReadShowGUIOrNormal.read();

		if (createGUI)
		{
			this.phaseGUI = new PhaseAboutGUI(this.game,this);
		}
			
		
	}

	private void init() {
		//CLASS VARIABLES:
		this.isActive=false;
		
		//IMPORTANT STUFF:
		this.activePhase=GamePhases.phaseEmpty;
		
		this.stringAbout="Ursuppe Game: \n \t  Version:\t \t  \t " + this.game.getVersion() +"    \n  \t Copyright of the program:   \t \t Jonas von Felten and Lukas Keller   \n  \t Original Game form:  \t  \t Doris Matthäus und Frank Nestel \n \n  \t Programming: \t \t  \t Lukas Keller and Jonas von Felten \n \t Debugging: \t \t \t Jonas von Felten and Lukas Keller \n \t Design: \t \t \t Lukas Keller and Jonas von Felten \n \t Graphics: \t \t \t Lukas Keller and Jonas von Felten \n \t Original idea of the Portal-gene: \t Radischa Iyadurai and Simon Kiener \n \n \n THANKS TO: \n - Jonas von Felten (from Lukas Keller) for supporting me and do other work when I was programming the Ursuppe \n - Dominique Rahm for correcting our smelly code and give us some hints how do programm better \n - Niko Schwarz, Dominique Rahm and Aaron Karper for fixing our git repository many times \n - Niko Schwarz for the food and drinks when we played the original Ursuppe \n - Doris Matthäus und Frank Nestel for this really interesting and cool game \n - Starbuck for their delicouse coffee \n - all other people who support us in some way in this project";

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
		
		public String getAboutString()
		{
			return this.stringAbout;
		}
		
		
		////////////
		//SETTERS://
		////////////
	
		
		public void setAboutString(String str)
		{
			this.stringAbout=str;
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
				System.out.println("\t ABOUT:");
				doPrintMessage();
				
				
				doUpdate();
			}
		}
		
		public void doPrintMessage()
		{
				System.out.println(this.stringAbout);
				String input=UserInput.readInput("To go back to the " +this.phaseBefore + " phase , type in 'b' or 'e' to stop the programm");
				
			
				checkInput(input);
		
		}
		
		public void checkInput(String input)
		{
			if (input.length()==1)
			{
				char a=input.charAt(0);
				
				switch (a)
				{
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
		this.updateComponents();
	}
	
	private void updateData()
	{
		
	}
	
	/**
	 * UPDATE the components and class variables of this phase //IMPORTANT FOR EVERY PHASE
	 */
	public void updateComponents()
	{

		
	}

	public void setBeforePhase(GamePhases phaseBefore) {
		this.phaseBefore=phaseBefore;
	}


	
	/////////////////////////////
	////////CREATE PANELS:///////
	/////////////////////////////

	
	
	
	
	
	
	

	
	
	
	/////////////////////////////////
	//SPECIAL METHODS OF THIS PHASE://
	/////////////////////////////////
	
			

	
	
	
	
	
	///////////
	//ACTIONS://
	////////////
	private void doGoBack() 
	{
		this.activePhase=this.phaseBefore;	
	}
	public void doExit()
	{
		System.exit(0);
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