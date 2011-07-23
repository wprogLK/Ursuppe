package phasesGUI;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;


import javax.swing.*;

import phases.PhaseWelcome;

import Components.Ameba;
import GUIComponents.UrsuppeGUI;

import enums.*;
import game.*;
import helpClasses.Calc;



/**
 * 
 */

/**
 * @author lukas
 *
 */
public class PhaseWelcomeGUI extends JPanel implements ActionListener
{
	//BASIC THINGS OF THIS GUI:
	private boolean isActive;		//if this PhaseGui is the actual gui, the "showing gui"
	private GamePhases activePhase;	//this is IMPORTANT!
	private Ameba actualAmeba;
	private int playedPlayer=0;
	private ArrayList<Ameba> amebaOfActualPlayerOnBoard=new ArrayList<Ameba>();
	
	private PhaseWelcome phase;
	
	private UrsuppeGUI ursuppe;
	//COMPONENTS OF THIS GUI:
	
		//PANELS:
			
			
			
		//LABELS:
			private JLabel labelWelcome=new JLabel();
			

	
	
		//BUTTONS:
			private JButton buttonStartNewGame=new JButton();
			private JButton buttonLoadGame=new JButton();
			private JButton buttonAbout=new JButton();
			private JButton buttonExit=new JButton();
			private JButton buttonHelp=new JButton();
			
			
		//RADIO BUTTONS:
	
	
		//BOXES
		
	
		//OTHER STUFF
		
		
		//SPECIAL COMPONENTS:
		
		
	//VARIABLES OF THIS PHASE:
	

	private Game game;
	
	private GameReadDirection readDirection;
	
	/**
	 * Constructor
	 */
	public PhaseWelcomeGUI(Game game,PhaseWelcome phase) {
		
		//TODO: LOAD URSUPPEGUI
		//this.ursuppe=ursuppeGui;
		
		this.phase=phase;
		
		//ARGUMENTES OF THE CONSTRUCTOR:
		this.game=game;
		
		init();
			
			
		//Update/Initialize all the data for the components	//IMPORTANT!!!
			
			
			//this.updateData();
			
		//CREATE ALL PANELS AND COMPONENTES:
			this.createPanels();
	
		
			
		
	}



	private void init() {
		//SET THE PHASE TO THE GAME:
		this.readDirection=GamePhases.phaseWelcome.getReadDirection();
		this.game.setReadDirection(readDirection);
		
		
		//CLASS VARIABLES:
		this.isActive=false;
		
		//IMPORTANT STUFF:
		this.activePhase=GamePhases.phaseEmpty;		
		
		//CONFIGURE THIS PANEL:
			//here you can set the Layout and other elementary things
			this.setLayout(null);
			//this.setSize(700,700); //TODO
			
			this.setVisible(true);
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
		this.setVisible(true);
		doUpdate();
		
		
		
	
	}
	
	public void deactivate()
	{
		this.isActive=false;
		this.setVisible(false);
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
		//this.checkIsNextPlayerOnTurn();
	
		
		//////////////////////
		//UPDATE COMPONENTS://
		//////////////////////
		
		
				//PANELS:
				
				//LABELS:
			
		
				updateLabels();
				//TEXTFIELDS:

				
				//BUTTONS:
				updateButtons();
				
				
				//RADIOBUTTONS:
				
				
				//BOXES:
				
				//OTHER STUFF:
				
				//SPECIAL STUFF:
				
				//SPECIAL METHODS:
				
			
			
				this.setAllBounds();
				
		
	}



	private void updateButtons() {
		this.buttonExit.setText("Exit...");
		this.buttonStartNewGame.setText("Start a new game");
		this.buttonLoadGame.setText("Load a old game");
		this.buttonAbout.setText("About this game and us");
		this.buttonHelp.setText("Help?!");
		
		
		this.buttonExit.setToolTipText("Click here to end the whole game");
		this.buttonStartNewGame.setToolTipText("Click here to start a new game");
		this.buttonLoadGame.setToolTipText("Click here to load a old game");
		this.buttonAbout.setToolTipText("Click here to see infommation about the game and us");
		this.buttonHelp.setToolTipText("Click here to look the manual of the game");
	}



	private void updateLabels() {
		this.labelWelcome.setText("Welcome to Ursuppe! What do you want to do? ");
		
		
	
	}
	
	/**
	 * it set every radioButtonAmeba# enable true or false. Depends on if a ameba of the actual player is already on the board
	 */

	
	/////////////////////////////
	////////CREATE PANELS:///////
	/////////////////////////////
	
	/**
	 *
	 * this method will be called by the constructor
	 */
	private void createPanels()
	{
		

		
		//IMPORTANT: createPanelPhaseSetFirstAmeba have to be called at the End of this method!!
		this.createPanel1();	
		
		this.setAllBounds();
	

	}
	
	
	
	/*
	 * Call this method after every update!! IMPORTANT
	 */
	private void setAllBounds()
	{
		Calc calc=new Calc();
		
		
		setBoundsPanel(calc);
		
		//Rectangle rec;
		/////////////
		//PANELS://
		//////////
		
		//rec=calc.calcPanel(this.panelRadioButtons, 50, 50);
		//this.panelRadioButtons.setBounds(rec);
		
		//rec=calc.calcPanel(this.panelButtons, 200, 220);
		//this.panelButtons.setBounds(rec);
		
		
		//calc.calcPanel(this.panelRadioButtons, 1000, 200);
		//this.panelInterface.setBounds(rec);
		
		
		///////////////
		//LABELS://
		///////////
		setBoundsLabel(calc);
		
		////////////////////
		//TEXTFIELDS://
		///////////////

		
		
		////////////////
		//BUTTONS://
		////////////
		
		
		setBoundsButtons(calc);
		
		////////////////////////
		//RADIOBUTTONS://
		///////////////////
		
		///////////////
		//OTHERS://
		///////////
			
	
	}



	private void setBoundsButtons(Calc calc) {
		calc.calcButton(this.buttonStartNewGame,80,120);
		calc.calcButton(this.buttonLoadGame,80,150);
		calc.calcButton(this.buttonAbout,80,180);
		calc.calcButton(this.buttonHelp,80,210);
		calc.calcButton(this.buttonExit,80,270);
	}



	private void setBoundsLabel(Calc calc) {
		calc.calcLabel(this.labelWelcome, 50, 100);
	}



	private void setBoundsPanel(Calc calc) {
		calc.calcPanel(this, 0, 0);
	}
	


	
	

	private void createPanel1()
	{	
		////////////////
		//CREATE PART://
		////////////////
		
		//...Create panels
		
		
		//...Create labels
		
		
		//...Create textFields
		
		//...Create buttons
		
		//...Create radioButtons
		
		
		//...Create boxes
		
		
		//...Create other Stuff
		
		
		/////////////////////////
		//ADD TO LISTENER PART://
		/////////////////////////
		
		//...add panels to listener
		
		
		//...add labels to listener
		
		
		//...add textFields to listener
		
		
		//...add buttons to listener
		
		addActionListenerButtons();

		//...add radioButtons to listener
		
		
		//...add boxes to listener
		
		
		//...add other Stuff to listener
		
		
		///////////////////////
		//ADD TO PANELS PART://
		///////////////////////
		
		//...add panels to panels
		
		
		//...add labels to panels
		
		//...add textFields to panels
		
		
		//...add buttons to panels
		
		//...add radioButtons to panels
		
		
		//...add boxes to panels
		
		
		//...add other Stuff to panels
		
		
		////////////////////////////////////////////////////////////////////////
		//ADD PANELS TO THE THIS (INSTEAD OF TO panelPhaseSetFirstAmeba) PART://
		////////////////////////////////////////////////////////////////////////
		
		
		///////////////////////////////////////////////////////
		//ADD THE panelPhaseSetFirstAmeba TO THIS PANEL PART://
		///////////////////////////////////////////////////////
		//this.add(this.panelPhaseSetFirstAmeba);
		
		addToThisLabel();
		addToThisButtons();
	
		//this.add(this.panelInterface);
	
	}



	private void addToThisButtons() {
		this.add(this.buttonAbout);
		this.add(this.buttonExit);
		this.add(this.buttonHelp);
		this.add(this.buttonLoadGame);
		this.add(this.buttonStartNewGame);
	}



	private void addToThisLabel() {
		this.add(this.labelWelcome);
	}



	private void addActionListenerButtons() {
		this.buttonExit.addActionListener(this);
		this.buttonAbout.addActionListener(this);
		this.buttonHelp.addActionListener(this);
		this.buttonLoadGame.addActionListener(this);
		this.buttonStartNewGame.addActionListener(this);
	}
	
	
	
	
	

	
	
	
	/////////////////////////////////
	//SPECIAL METHODS OF THIS PHASE://
	/////////////////////////////////
	
			

	
	
	
	
	
	///////////
	//ACTIONS://
	////////////

	@Override
	public void actionPerformed(ActionEvent e) {
	
		
		if (e.getSource()==this.buttonExit)
		{
			//System.out.println("ESCAPE THE GAME! (Part2)"); //TODO
			System.exit(0);
		}
		else if (e.getSource()==this.buttonAbout)
		{
			this.ursuppe.fakeShowAbout();
			//TODO
		}
		else if (e.getSource()==this.buttonHelp)
		{
			//TODO
		}
		else if (e.getSource()==this.buttonLoadGame)
		{
			//TODO
		}
		else if (e.getSource()==this.buttonStartNewGame)
		{
			this.activePhase=GamePhases.phaseSetNames;
			//TODO
		}
		
		else
		{
			System.out.println("A action wasn't implemented");
			System.out.println(e.getSource());
			
			
		}
		
		
		
		////////////////
		//UPDATE DATA://	//do this in every case!
		////////////////
		this.updateData();
		
	
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
	
			public Ameba getActiveAmeba()
			{
				return this.actualAmeba;
			}

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
		
			public void fakeClickbuttonExit()
			{
				this.buttonExit.doClick();
			}
			
			

			///////////////////
			//*RADIO BUTTONS*//
			///////////////////
	
	
			////////////////
			//*TEXTFIELDS*//
			////////////////
			
			
			
}
