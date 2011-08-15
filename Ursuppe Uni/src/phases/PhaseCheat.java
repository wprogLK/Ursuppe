package phases;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;


import javax.swing.*;

import cheats.cheatSetPlayerName;

import enums.*;
import exceptions.ExceptionCheatInputInvalid;
import game.*;


/**
 * 
 */

/**
 * @author lukas
 *
 */
public class PhaseCheat extends JPanel implements ActionListener
{
	//BASIC THINGS OF THIS GUI:
	private boolean isActive;		//if this PhaseGui is the actual gui, the "showing gui"
	private GamePhases activePhase;	//this is IMPORTANT!
	private String asciiOutput="*ASCII OUTPUT*";
	private String textOutput="*TEXT OUTPUT*";
	
	//COMPONENTS OF THIS GUI:
	
		//PANELS:
			
		
			
			
		//LABELS:
			private JLabel labelInstruction=new JLabel();
			
			private JLabel labelEnterCheat=new JLabel();
			
			
		//TEXTFIELDS:
			private JTextField textFieldInput=new JTextField(84);
		
			private JTextArea textAreaTextOutput=new JTextArea();
			private JTextArea textAreaASCIIOutput=new JTextArea();
	
		//BUTTONS:
			private JButton buttonExit=new JButton();
			private JButton buttonCheat=new JButton();
			
		//RADIO BUTTONS:
	
	
		//BOXES
		
	
		//OTHER STUFF
			JScrollPane scrollingTextAreaOutput=new JScrollPane(textAreaTextOutput);
			JScrollPane scrollingASCIIOutput=new JScrollPane(textAreaASCIIOutput);

		
		//SPECIAL COMPONENTS:
	
		
	//VARIABLES OF THIS PHASE:
	
	
	
	private String gameTitle;
	private String phaseTitle;
	
	private Game game;
	
	private GameReadDirection readDirection;
	
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
		
		//CONFIGURE THIS PANEL:
			//here you can set the Layout and other elementary things
			this.setLayout(null);
			//this.setSize(700,700); //TODO
			
			this.setVisible(true);
			
		
		
			
			this.doUpdate();
			
		//CREATE ALL PANELS AND COMPONENTES:
			this.createPanels();
	
			
			
		
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
		this.scrollingTextAreaOutput.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.scrollingASCIIOutput.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	}
	
	/**
	 * UPDATE the components and class variables of this phase //IMPORTANT FOR EVERY PHASE
	 */
	public void updateComponents()
	{
		
		//////////////////////
		//UPDATE COMPONENTS://
		//////////////////////
		
		
				//PANELS:
				
				//LABELS:
			
				this.labelEnterCheat.setText("Enter here cheats:");
				this.labelInstruction.setText("If you know a cheat, cheat... ");
				
			
				
				//TEXTFIELDS:
				
				this.textAreaTextOutput.setText(this.textOutput);
				this.textAreaASCIIOutput.setText(this.asciiOutput);
				
				//BUTTONS:
				this.buttonExit.setText("Exit...");
				this.buttonCheat.setText("do cheat");
				
				this.buttonExit.setToolTipText("Click here to end the whole game");
				this.buttonCheat.setToolTipText("Click here to activate the cheat which you typed in");
				
	
				this.setAllBounds();
				
		
	}
	
	
	
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
		
		calc.calcPanel(this, 0, 0);
	
		/////////////
		//PANELS://
		//////////

		
		
		///////////////
		//LABELS://
		///////////
		calc.calcLabel(this.labelInstruction, 10, 10);
		calc.calcLabel(this.labelEnterCheat, 10, 30);
	
		////////////////////
		//TEXTFIELDS://
		///////////////
		calc.calcTextField(this.textFieldInput, 10,60);
		
		
		////////////////////
		//TEXTAREAS://
		///////////////

		////////////////
		//BUTTONS://
		////////////
		calc.calcButton(this.buttonExit, 10, 900);
		calc.calcButton(this.buttonCheat, 10, 80);
		
		////////////////////////
		//RADIOBUTTONS://
		///////////////////
		
		///////////////
		//OTHERS://
		///////////
		
		this.scrollingASCIIOutput.setBounds(10, 320, 1000,560);
		this.scrollingTextAreaOutput.setBounds(10,100,1000,200);
	
		
		
	
	}
	

	

	private void createPanel1()
	{	
		
		
		//...add buttons to listener
		
		this.buttonExit.addActionListener(this);
		this.buttonCheat.addActionListener(this);
	
		this.textAreaASCIIOutput.setEditable(false);
		this.textAreaASCIIOutput.setBackground(this.labelEnterCheat.getBackground());
		this.textAreaASCIIOutput.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		
		
		this.textAreaTextOutput.setEditable(false);
		this.textAreaTextOutput.setBackground(this.labelEnterCheat.getBackground());
		this.textAreaTextOutput.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	
		
	
		
		
		
		///////////////////////////////////////////////////////
		//ADD THE panelPhaseSetFirstAmeba TO THIS PANEL PART://
		///////////////////////////////////////////////////////
		//this.add(this.panelPhaseSetFirstAmeba);
		this.add(this.labelEnterCheat);
		this.add(this.labelInstruction);

		
		this.add(this.textFieldInput);
		
		this.add(this.buttonCheat);
		this.add(this.buttonExit);
	
		this.add(scrollingTextAreaOutput);
		this.add(scrollingASCIIOutput);
		

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
			
			}
			else
			{
				throw new ExceptionCheatInputInvalid("No correct cheatname or wrong number of arguments" );
			}
	    } 
	    catch (ArrayIndexOutOfBoundsException a) 
	    { 
	    	this.textOutput=this.textOutput+"\n Missing arguments \n";
	    } 
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	///////////
	//ACTIONS://
	////////////

	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO: AB HIER FREITAG WEITER MACHEN
		
		
		if (e.getSource()==this.buttonExit)
		{
			
			System.exit(0);
		}
		if (e.getSource()==this.buttonCheat)
		{
			try 
		    { 
				this.checkValidInput(this.textFieldInput.getText());
		    } 
		    catch (ExceptionCheatInputInvalid a) 
		    { 
		    	this.textOutput=this.textOutput+"\n " + a;
		    } 
			
			
		}
		else
		{
			System.out.println("A action wasn't implemented");
			System.out.println(e.getSource());
			
			
		}
		
		
		
		////////////////
		//UPDATE DATA://	//do this in every case!
		////////////////
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
	
			public boolean getButtonExitIsEnabled()
			{
				return this.buttonExit.isEnabled();
			}
		

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
