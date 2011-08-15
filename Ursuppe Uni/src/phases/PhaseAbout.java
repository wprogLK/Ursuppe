package phases;

import java.awt.event.*;
import java.util.ArrayList;



import javax.swing.*;

import enums.*;
import game.*;

/**
 * 
 */

/**
 * @author lukas
 *
 */
public class PhaseAbout extends JPanel implements ActionListener
{
	//BASIC THINGS OF THIS GUI:
	private boolean isActive;		//if this PhaseGui is the actual gui, the "showing gui"
	private GamePhases activePhase;	//this is IMPORTANT!
	private Ameba actualAmeba;
	private int playedPlayer=0;
	private ArrayList<Ameba> amebaOfActualPlayerOnBoard=new ArrayList<Ameba>();
	//COMPONENTS OF THIS GUI:
	
		//PANELS:
			
			
			
		//LABELS:
			private JLabel labelTitle=new JLabel();
			

	
	
		//BUTTONS:
			private JButton buttonCloseWindow=new JButton();
		
			
			
		//RADIO BUTTONS:
	
	
		//BOXES
		
	
		//OTHER STUFF
		private JTextArea textAreaInfo=new JTextArea(50,300);
		
		//SPECIAL COMPONENTS:
		
		
	//VARIABLES OF THIS PHASE:
	

	private Game game;
	
	private GameReadDirection readDirection;
	
	/**
	 * Constructor
	 */
	public PhaseAbout(Game game) {
		//ARGUMENTES OF THE CONSTRUCTOR:
		this.game=game;
		
	
	
		
		
		//CLASS VARIABLES:
		this.isActive=false;
		
		//IMPORTANT STUFF:
		this.activePhase=GamePhases.phaseEmpty;		
		
		//CONFIGURE THIS PANEL:
			//here you can set the Layout and other elementary things
			this.setLayout(null);
			//this.setSize(700,700); //TODO
			
			this.setVisible(true);
		
			//this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		//Update/Initialize all the data for the components	//IMPORTANT!!!
			
			
			//this.updateData();
			
		//CREATE ALL PANELS AND COMPONENTES:
			this.doUpdate();
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
		
	}
	
	/**
	 * UPDATE the components and class variables of this phase //IMPORTANT FOR EVERY PHASE
	 */
	public void updateComponents()
	{
		//this.checkIsNextPlayerOnTurn();
	
		System.out.println("UPDATE COMP. in About");
		//////////////////////
		//UPDATE COMPONENTS://
		//////////////////////
		
		
				//PANELS:
				
				//LABELS:
			
		
				this.labelTitle.setText("About this game: ");
				
				
			
				//TEXTFIELDS:
				String string="Ursuppe Game: \n \t  Version:\t \t  \t " + this.game.getVersion() +"    \n  \t Copyright of the program:   \t \t Jonas von Felten and Lukas Keller   \n  \t Original Game form:  \t  \t Doris Matthäus und Frank Nestel \n \n  \t Programming: \t \t  \t Lukas Keller and Jonas von Felten \n \t Debugging: \t \t \t Jonas von Felten and Lukas Keller \n \t Design: \t \t \t Lukas Keller and Jonas von Felten \n \t Graphics: \t \t \t Lukas Keller and Jonas von Felten \n \t Original idea of the Portal-gene: \t Radischa Iyadurai and Simon Kiener \n \n \n THANKS TO: \n - Jonas von Felten (from Lukas Keller) for supporting me and do other work when I was programming the Ursuppe \n - Dominique Rahm for correcting our smelly code and give us some hints how do programm better \n - Niko Schwarz, Dominique Rahm and Aaron Karper for fixing our git repository many times \n - Niko Schwarz for the food and drinks when we played the original Ursuppe \n - Doris Matthäus und Frank Nestel for this really interesting and cool game \n - Starbuck for their delicouse coffee \n - all other people who support us in some way in this project";
				this.textAreaInfo.setText(string);
				
				this.textAreaInfo.setLineWrap(true);
				this.textAreaInfo.setAutoscrolls(true);
				this.textAreaInfo.setEditable(false);
				this.textAreaInfo.setBackground(this.labelTitle.getBackground());
				//BUTTONS:
				this.buttonCloseWindow.setText("Close");
			
				
				this.buttonCloseWindow.setToolTipText("Click here to close this window");
				
				
				
				//RADIOBUTTONS:
				
				
				//BOXES:
				
				//OTHER STUFF:
				
				//SPECIAL STUFF:
				
				//SPECIAL METHODS:
				
			
			
				this.setAllBounds();
				
		
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
		
		
		calc.calcPanel(this, 0, 0);
		
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
		calc.calcLabel(this.labelTitle, 100, 10);
		
		////////////////////
		//TEXTAreas://
		///////////////
		
		this.textAreaInfo.setBounds(30, 30, 2000, 2000);
		
		
		////////////////
		//BUTTONS://
		////////////
		
		
		calc.calcButton(this.buttonCloseWindow,0,400);

		
		////////////////////////
		//RADIOBUTTONS://
		///////////////////
		
		///////////////
		//OTHERS://
		///////////
			
	
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
		
		this.buttonCloseWindow.addActionListener(this);
		
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
		
		this.add(this.labelTitle);
		this.add(this.buttonCloseWindow);
		this.add(this.textAreaInfo);
		
	
		//this.add(this.panelInterface);
	
	}
	
	
	
	
	

	
	
	
	/////////////////////////////////
	//SPECIAL METHODS OF THIS PHASE://
	/////////////////////////////////
	
			

	
	
	
	
	
	///////////
	//ACTIONS://
	////////////

	@Override
	public void actionPerformed(ActionEvent e) {
	
		
		if (e.getSource()==this.buttonCloseWindow)
		{
			//System.out.println("ESCAPE THE GAME! (Part2)"); //TODO
			System.exit(0);
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
		
			

			///////////////////
			//*RADIO BUTTONS*//
			///////////////////
	
	
			////////////////
			//*TEXTFIELDS*//
			////////////////
			
			
			
}