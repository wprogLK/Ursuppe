package phasesGUI;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;


import javax.swing.*;

import phases.Phase0;
import phases.PhaseSetFirstAmeba;

import Components.Ameba;
import Components.Board;
import Components.ISquare;
import Components.Player;
import Components.SoupSquare;

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
public class PhaseSetFirstAmebaGUI extends JPanel implements ActionListener
{
	//BASIC THINGS OF THIS GUI:
	private boolean isActive;		//if this PhaseGui is the actual gui, the "showing gui"
	private GamePhases activePhase;	//this is IMPORTANT!
	private final int nrOfAmebasToSet=2;
	
	private PhaseSetFirstAmeba phase;
	
	//COMPONENTS OF THIS GUI:
	
		//PANELS:
			
			private JPanel panelPhaseSetFirstAmeba =new JPanel(); 		//Need every GUI! (its the main panel) //IMPORTANT: it contains only other panels!!!
			private JPanel panelButtons=new JPanel(new FlowLayout());
			private JPanel panelInterface=new JPanel(new GridLayout(2,1));
			private JPanel panelRadioButtons=new JPanel(new GridLayout(4,1));
			private JPanel panelSetSoupSquare=new JPanel(new GridLayout(4,4));
		
		//LABELS:
			private JLabel labelGameTitle=new JLabel();
			private JLabel labelPhaseTitle=new JLabel();
			
			private JLabel labelActualPlayer=new JLabel();
			
			private JLabel labelInstruction=new JLabel();
			
			private JLabel labelInstructionSetSoupSquare=new JLabel();
			
			private JLabel labelNumbers=new JLabel("X: ");
			private JLabel labelLetters=new JLabel("Y: ");

		//TEXTFIELDS:
			private JTextField textFieldX=new JTextField(10);
			private JTextField textFieldY=new JTextField(10);
	
		//BUTTONS:
			private JButton buttonGoBack=new JButton();
			private JButton buttonExit=new JButton();
			private JButton buttonGoToPhase1=new JButton();
			private JButton buttonSetAmeba=new JButton();
			private JButton buttonCheckPossibleSquare=new JButton();
			
		//RADIO BUTTONS:
		private JRadioButton radioButtonAmeba1=new JRadioButton();
		private JRadioButton radioButtonAmeba2=new JRadioButton();
		private JRadioButton radioButtonAmeba3=new JRadioButton();
		private JRadioButton radioButtonAmeba4=new JRadioButton();
		
		private ButtonGroup buttonGroup=new ButtonGroup();
	
		//BOXES
		
	
		//OTHER STUFF
		
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
	
	/**
	 * Constructor
	 */
	public PhaseSetFirstAmebaGUI(Game game, PhaseSetFirstAmeba phase) {
		
		this.phase=phase;
		//ARGUMENTES OF THE CONSTRUCTOR:
		this.game=game;
		this.board=game.getBoard();
		
		init(game);
		//Update/Initialize all the data for the components	//IMPORTANT!!!
			this.updateData();
			
		//CREATE ALL PANELS AND COMPONENTES:
			this.createPanels();
	
			this.setChoseAmebaVisible(true);
			this.setChoseSetSoupSquareVisible(false);
			
		
	}

	private void init(Game game) {
		//SET THE PHASE TO THE GAME:
		this.readDirection=GamePhases.phaseSetFirstAmeba.getReadDirection();
		this.game.setReadDirection(readDirection);
		this.phaseTitle="Phase 0: Set your first ambeas";
		
		//CLASS VARIABLES:
		this.isActive=false;
		this.gameTitle=game.getGameTitle();
		this.actualPlayer=game.getActualPlayer();
		
		this.playerBlue=game.getPlayer(GameColor.blue);
		this.playerRed=game.getPlayer(GameColor.red);
		this.playerYellow=game.getPlayer(GameColor.yellow);
		
		
		//IMPORTANT STUFF:
		this.activePhase=GamePhases.phaseEmpty;		
		
		//CONFIGURE THIS PANEL:
			//here you can set the Layout and other elementary things
			this.setLayout(null);
			//this.setSize(700,700); //TODO
			this.panelPhaseSetFirstAmeba.setLayout(null);
			this.setVisible(true);
			
			this.panelPhaseSetFirstAmeba.setSize(700, 700);
			this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
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
		//System.out.println("------------------ACTIVATE PHASE SET FIRST AMEBA----------------");
		this.activePhase=GamePhases.phaseEmpty;
		this.isActive=true;
		this.setVisible(true);
		updateData();
		
		addToThisOthers();
	
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
		updateData();
		updateComponents();
	}
	
	private void updateData()
	{
		this.game.skipEmptyPlayer();
		assert !game.getActualPlayer().equals(this.game.getEmptyPlayer());
		
		
		///////////////////////////
		//UPDATE CLASS VARIABLES://
		///////////////////////////
		
		//TODO:
		
		this.actualPlayer=this.game.getActualPlayer();
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
				
				updateLabels();
				
				
				
				//TEXTFIELDS:
				
				//BUTTONS:
				updateButtons();
			
				
				//RADIOBUTTONS:
				updateRadioButtons2();
				
				//BOXES:
				
				//OTHER STUFF:
				
				//SPECIAL STUFF:
				
				//SPECIAL METHODS:
				
				this.updateRadioButtons();
				this.haveAllPlayerSetTwoAmebas();
				this.setAllBounds();
		
		
	}

	private void updateRadioButtons2() {
		this.radioButtonAmeba1.setText("Ameba 1");
		this.radioButtonAmeba2.setText("Ameba 2");
		this.radioButtonAmeba3.setText("Ameba 3");
		this.radioButtonAmeba4.setText("Ameba 4");
		
		this.buttonGroup.add(this.radioButtonAmeba1);
		this.buttonGroup.add(this.radioButtonAmeba2);
		this.buttonGroup.add(this.radioButtonAmeba3);
		this.buttonGroup.add(this.radioButtonAmeba4);
		
		this.radioButtonAmeba1.setToolTipText("Chose this Ameba to set");
		this.radioButtonAmeba2.setToolTipText("Chose this Ameba to set");
		this.radioButtonAmeba3.setToolTipText("Chose this Ameba to set");
		this.radioButtonAmeba3.setToolTipText("Chose this Ameba to set");
	}

	private void updateButtons() {
		this.buttonExit.setText("Exit...");
		this.buttonGoBack.setText("Go back to the previous phase");
		this.buttonGoToPhase1.setText("Go to phase1");
		this.buttonSetAmeba.setText("Set Ameba!");
		this.buttonCheckPossibleSquare.setText("Check possible Square");
		
		this.buttonExit.setToolTipText("Click here to end the whole game");
		this.buttonGoBack.setToolTipText("Click here to go back to phase 0");
		this.buttonGoToPhase1.setToolTipText("Click here to to phase 1");
		this.buttonSetAmeba.setToolTipText("Click here to set your ameba");
		this.buttonCheckPossibleSquare.setToolTipText("Click here to check, if your prefered soup square is a valide choice!");
	}

	private void updateLabels() {
		this.labelInstruction.setText("Chose your ameba to set on the board, then click on a SoupSquare, on which is no ameba of your color");
		this.labelInstructionSetSoupSquare.setText("Chose an ameba to set. (You can set only one of your amebas one a square.)");
		this.labelGameTitle.setText(this.gameTitle);
		this.labelPhaseTitle.setText("Phase: " + this.phaseTitle);
		
		this.labelNumbers.setText("X: ");
		this.labelLetters.setText("Y: " );
		
		this.labelActualPlayer.setText("The actual player is: " + this.game.getActualPlayer().getPlayerName() + " [ " + this.game.getActualPlayer().getColor() + " ]");
	}
	
	/**
	 * it set every radioButtonAmeba# enable true or false. Depends on if a ameba of the actual player is already on the board
	 */
	private void updateRadioButtons()
	{
		ArrayList<Ameba> amebasOnBoard=this.actualPlayer.getAmebasOnBoard(); 
		
		//SET ALL RADIOBUTTONS ACTIVE:
		this.radioButtonAmeba1.setEnabled(true);
		this.radioButtonAmeba2.setEnabled(true);
		this.radioButtonAmeba3.setEnabled(true);
		this.radioButtonAmeba4.setEnabled(true);
		
		//SET RADIOBUTTON:
	
		for(int i=0; i<amebasOnBoard.size();i++)
		{
			Ameba actualAmeba=amebasOnBoard.get(i);
			
			int nr=actualAmeba.getNumber();
			
			switch(nr)
			{
				case 1:
				{
					this.radioButtonAmeba1.setEnabled(false);
					break;
				}
				case 2:
				{
					this.radioButtonAmeba2.setEnabled(false);
					break;
				}
				case 3:
				{
					this.radioButtonAmeba3.setEnabled(false);
					break;
				}
				case 4:
				{
					this.radioButtonAmeba4.setEnabled(false);
					break;
				}
				default:
				{
					System.out.println("Error (PhaseSetFirstAmeba): exception in updateRadioButtons()!");
					//TODO 
					break;
				}
			}
			
		}
		
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
		
		this.createPanelButtons();
	
		this.createPanelRadioButtons();
		this.createPanelSetSoupSquare();
		
		//IMPORTANT: createPanelPhaseSetFirstAmeba have to be called at the End of this method!!
		this.createPanelPhaseSetFirstAmeba();	
		
		this.setAllBounds();
		
		this.setChoseAmebaVisible(true);
		this.setChoseSetSoupSquareVisible(false);
		
		

	}
	
	
	
	/*
	 * Call this method after every update!! IMPORTANT
	 */
	private void setAllBounds()
	{
		Calc calc=new Calc();
		
		
		calcPanels(calc);
		//calc.calcPanel(this.panelRadioButtons, 1000, 200);
		//this.panelInterface.setBounds(rec);
		
		
		///////////////
		//LABELS://
		///////////
		calcLabels(calc);
		
		calcTextFields(calc);
		//this.labelInstruction.setBounds(rec);
		
	
		//this.labelActualPlayer.setBounds(rec);
		
		////////////////
		//BUTTONS://
		////////////
		
		calcButtons(calc);
		//this.buttonGoToPhase1.setBounds(rec);
		
		////////////////////////
		//RADIOBUTTONS://
		///////////////////
		calcRadioButtons(calc);
		
		///////////////
		//OTHERS://
		///////////
		
		calcOthers(calc);
		
	
	}

	private void calcOthers(Calc calc) {
		calc.calcBoard(this.board, 100, 100);
	}

	private void calcRadioButtons(Calc calc) {
		calc.calcRadioButton(this.radioButtonAmeba1, 1000, 200);
		calc.calcRadioButton(this.radioButtonAmeba2, 1000, 250);
		calc.calcRadioButton(this.radioButtonAmeba3, 1000, 300);
		calc.calcRadioButton(this.radioButtonAmeba4, 1000, 350);
	}

	private void calcButtons(Calc calc) {
		calc.calcButton(this.buttonExit, 100, 1000);
		//this.buttonExit.setBounds(rec);
		
		calc.calcButton(this.buttonGoBack, 450, 1000);
		//this.buttonGoBack.setBounds(rec);
		
		calc.calcButton(this.buttonGoToPhase1, 200, 1000);
		
		calc.calcButton(this.buttonSetAmeba, 1000, 300);
		calc.calcButton(this.buttonCheckPossibleSquare, 1100,300);
	}

	private void calcTextFields(Calc calc) {
		calc.calcTextField(this.textFieldY, 1120, 200);
		calc.calcTextField(this.textFieldX, 1120,250);
	}

	private void calcLabels(Calc calc) {
		calc.calcLabel(labelGameTitle, 10, 10);
		//this.labelGameTitle.setBounds(rec);
		
		calc.calcLabel(this.labelPhaseTitle, 1000, 50);
		//this.labelPhaseTitle.setBounds(rec);

		calc.calcLabel(this.labelInstruction, 1000, 100);
		calc.calcLabel(this.labelActualPlayer, 1000, 150);
		
		calc.calcLabel(this.labelInstructionSetSoupSquare, 1000, 100);
		calc.calcLabel(this.labelLetters, 1000, 200);
		calc.calcLabel(this.labelNumbers, 1000, 250);
	}

	private void calcPanels(Calc calc) {
		calc.calcPanel(this, 0, 0);
		
		//Rectangle rec;
		/////////////
		//PANELS://
		//////////
		
		//rec=calc.calcPanel(this.panelRadioButtons, 50, 50);
		//this.panelRadioButtons.setBounds(rec);
		
		//rec=calc.calcPanel(this.panelButtons, 200, 220);
		//this.panelButtons.setBounds(rec);
		
		calc.calcPanel(this.panelInterface, 900, 50);
	}
	
	private void createPanelSetSoupSquare()
	{
		//ROW 1:
		this.panelSetSoupSquare.add(this.labelInstructionSetSoupSquare);
		this.panelSetSoupSquare.add(new JLabel());
	
		//ROW 2:
		this.panelSetSoupSquare.add(this.labelLetters);
		this.panelSetSoupSquare.add(this.textFieldY);
		
		//ROW 3:
		this.panelSetSoupSquare.add(this.labelNumbers);
		this.panelSetSoupSquare.add(this.textFieldX);
		
		//ROW 4:
		this.panelSetSoupSquare.add(this.buttonSetAmeba);
		this.panelSetSoupSquare.add(new JLabel());
		
		
		
	}
	private void setChoseAmebaVisible(boolean visible)
	{
		this.labelInstruction.setVisible(visible);
		
		this.radioButtonAmeba1.setVisible(visible);
		this.radioButtonAmeba2.setVisible(visible);
		this.radioButtonAmeba3.setVisible(visible);
		this.radioButtonAmeba4.setVisible(visible);
	}
	
	private void setChoseSetSoupSquareVisible(boolean visible)
	{
		this.labelInstructionSetSoupSquare.setVisible(visible);
		
		this.labelLetters.setVisible(visible);
		this.labelNumbers.setVisible(visible);
		
		this.buttonSetAmeba.setVisible(visible);
		this.buttonSetAmeba.setEnabled(false);
		
		this.buttonCheckPossibleSquare.setVisible(visible);
		
		this.textFieldY.setVisible(visible);
		this.textFieldX.setVisible(visible);
	}
	
	
	private void createPanelInfo()
	{
		//this.panelInfo.add(this.labelGameTitle);
		//this.panelInfo.add(this.labelPhaseTitle);
		//this.panelInfo.add(this.labelInstruction);
	}
	
	private void createPanelPhaseSetFirstAmeba()
	{	
	
		
		//...add buttons to listener
		this.buttonSetAmeba.addActionListener(this);
		this.buttonCheckPossibleSquare.addActionListener(this);
		
		
	
		
		///////////////////////////////////////////////////////
		//ADD THE panelPhaseSetFirstAmeba TO THIS PANEL PART://
		///////////////////////////////////////////////////////
		//this.add(this.panelPhaseSetFirstAmeba);
		addToThisLabels();
	
		//this.add(this.panelInterface);
		addToThisOthers();
		
		
		
		addToThisButtons();
		
		addToThisTextFields();
		
		
		
		
		addToThisRadioButtons();
		
		//this.add(this.panelRadioButtons);
		
		/*
		//this.add(this.panelPhaseSetFirstAmeba);
		this.add(this.labelGameTitle);
		this.add(this.labelPhaseTitle);
		this.add(this.labelInstruction);
		//this.add(this.board);
		this.add(this.panelInterface);
		this.add(this.panelButtons);
		*/
	}

	private void addToThisRadioButtons() {
		this.add(this.panelRadioButtons);
		this.add(this.radioButtonAmeba1);
		this.add(this.radioButtonAmeba2);
		this.add(this.radioButtonAmeba3);
		this.add(this.radioButtonAmeba4);
	}

	private void addToThisTextFields() {
		this.add(this.textFieldY);
		this.add(this.textFieldX);
	}

	private void addToThisButtons() {
		this.add(this.buttonGoBack);
		this.add(this.buttonExit);
		this.add(this.buttonGoToPhase1);
		
		this.add(this.buttonCheckPossibleSquare);
		this.add(this.buttonSetAmeba);
	}

	private void addToThisOthers() {
		this.add(this.board);
	}

	private void addToThisLabels() {
		this.add(this.labelPhaseTitle);
		this.add(this.labelGameTitle);
		this.add(this.labelInstruction);
		
		this.add(this.labelInstructionSetSoupSquare);
		this.add(this.labelLetters);
		this.add(this.labelNumbers);
		

		this.add(this.labelActualPlayer);
	}
	
	
	
	
	private void createPanelButtons()
	{
		
		this.buttonGoToPhase1.setEnabled(false);
	
		//...add buttons to listener
		this.buttonGoBack.addActionListener(this);
		this.buttonExit.addActionListener(this);
		this.buttonGoToPhase1.addActionListener(this);
	
		
		
	}
	
	
	

	
	
	private void createPanelRadioButtons()
	{
		
		//...add radioButtons to listener
		this.radioButtonAmeba1.addActionListener(this);
		this.radioButtonAmeba2.addActionListener(this);
		this.radioButtonAmeba3.addActionListener(this);
		this.radioButtonAmeba4.addActionListener(this);
		
	
		//...add radioButtons to panels
		//ROW 1:
		this.panelRadioButtons.add(this.radioButtonAmeba1);
		this.panelRadioButtons.add(this.radioButtonAmeba2);
		this.panelRadioButtons.add(this.radioButtonAmeba3);
		this.panelRadioButtons.add(this.radioButtonAmeba4);
	
		
	}
	
	
	
	/////////////////////////////////
	//SPECIAL METHODS OF THIS PHASE://
	/////////////////////////////////

	private void haveAllPlayerSetTwoAmebas()
	{
		playerBlue=this.game.getPlayer(GameColor.blue);
		 playerRed=this.game.getPlayer(GameColor.red);
		Player playerYellow=this.game.getPlayer(GameColor.yellow);
		
		if(playerBlue.getNumbersOfAmebasOnBoard()==this.nrOfAmebasToSet && playerRed.getNumbersOfAmebasOnBoard()==this.nrOfAmebasToSet && playerYellow.getNumbersOfAmebasOnBoard()==this.nrOfAmebasToSet) 
		{
			this.buttonGoToPhase1.setEnabled(true);
			this.setChoseAmebaVisible(false);
			this.setChoseSetSoupSquareVisible(false);
			this.activePhase=GamePhases.phase1;
		}
		else
		{
			//System.out.println("Not every player set two players");
		}
	}
	
	private boolean isFirstAmebaOfPlayer(Player player)
	{
		/*System.out.println("numbers of AMebas on board" + player.getNumbersOfAmebasOnBoard());
		System.out.println("numbers of amebas on board player blue: " + this.game.getPlayer(GameColor.blue).getNumbersOfAmebasOnBoard());
		System.out.println("numbers of amebas on board player red: " + this.game.getPlayer(GameColor.red).getNumbersOfAmebasOnBoard());
		System.out.println("numbers of amebas on board player yellow: " + this.game.getPlayer(GameColor.yellow).getNumbersOfAmebasOnBoard());
		*/
		
		if (player.getNumbersOfAmebasOnBoard()==0)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	
	
	///////////
	//ACTIONS://
	////////////

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if(e.getSource()==this.buttonGoToPhase1)
		{
			//System.out.println("GO PHASE 1");
			this.activePhase=GamePhases.phase1;
		}
		else if (e.getSource()==this.buttonExit)
		{
			//System.out.println("ESCAPE THE GAME! (Part2)"); //TODO
			System.exit(0);
		}
		else if (e.getSource()==this.buttonGoBack)
		{
				this.activePhase=GamePhases.phase0; //Go back to phase SetNames
				//System.out.println("GO TO PHASE 0");
		}
		else if(e.getSource()==this.radioButtonAmeba1)
		{
			//System.out.println("AMEBA ONE!");
			Ameba amebaToSet=this.actualPlayer.getAmebaWithNumber(1);
			game.setActiveAmeba(amebaToSet);
			
			
			
			this.radioButtonAmeba1.setEnabled(false);
			
			this.setChoseAmebaVisible(false);
			this.setChoseSetSoupSquareVisible(true);
		}
		else if(e.getSource()==this.radioButtonAmeba2)
		{
			Ameba amebaToSet=this.actualPlayer.getAmebaWithNumber(2);
			game.setActiveAmeba(amebaToSet);
			
		
			
			this.radioButtonAmeba2.setEnabled(false);
			
			
			this.setChoseAmebaVisible(false);
			this.setChoseSetSoupSquareVisible(true);
		}
		else if(e.getSource()==this.radioButtonAmeba3)
		{
			Ameba amebaToSet=this.actualPlayer.getAmebaWithNumber(3);
			game.setActiveAmeba(amebaToSet);
			
			
			
			this.radioButtonAmeba3.setEnabled(false);
			
			
			
			this.setChoseAmebaVisible(false);
			this.setChoseSetSoupSquareVisible(true);
		}
		else if(e.getSource()==this.radioButtonAmeba4)
		{
			Ameba amebaToSet=this.actualPlayer.getAmebaWithNumber(4);
			game.setActiveAmeba(amebaToSet);
			
			
			
			this.radioButtonAmeba4.setEnabled(false);
			
			
			this.setChoseAmebaVisible(false);
			this.setChoseSetSoupSquareVisible(true);
		}
		
		else if(e.getSource()==this.buttonCheckPossibleSquare)
		{
			boolean valid= this.checkInput();
			
			if(valid)
			{
				int inputNumberR= Integer.parseInt(this.textFieldY.getText()); 	//Y
				int inputNumberC= Integer.parseInt(this.textFieldX.getText());	//X
				
				ISquare square = this.board.getSquare(inputNumberC, inputNumberR);
				
				if (!square.isSoupSquare())
				{
					JOptionPane.showMessageDialog(null, "Error: You chose a invalid square!");	
					this.buttonSetAmeba.setEnabled(false);
				}
				else if (!square.isEmpy())
				{
					JOptionPane.showMessageDialog(null, "Error: On this soupsquare is already a ameba! Please chose another square!");	
					this.buttonSetAmeba.setEnabled(false);
				}
				else
				{
					//System.out.println("Square empty:  " + square.isEmpy());
					
					
					
					ArrayList<Ameba> amebasOnSquare= square.getAmebasOfColor(game.getActualPlayer().getColor());
					if (amebasOnSquare.size()!=0)
					{
						JOptionPane.showMessageDialog(null, "Error: You chose a square, where you have already one of your amebas!");	
						this.buttonSetAmeba.setEnabled(false);
					}
					else
					{
						game.setActiveSoupSquare(square);
					}
					
				}
			}
			
			
			

		}
		else if(e.getSource()==this.buttonSetAmeba)
		{
			//TODO: CHECK THIS!!!
			int inputNumberR= Integer.parseInt(this.textFieldY.getText()); //Y
			int inputNumberC= Integer.parseInt(this.textFieldX.getText());	//X
			
			
			Ameba activeAmeba=game.getActiveAmeba();
			
			activeAmeba.setSquarePosition(inputNumberC, inputNumberR);
			
			boolean isFirstAmeba=this.isFirstAmebaOfPlayer(this.game.getActualPlayer());
			
			if (isFirstAmeba)
			{
				activeAmeba.setDamagePoints(1);	
				activeAmeba.checkIsDeath();		
				assert !activeAmeba.isDead();	
			}
			else
			{
				//System.out.println("THIS IS NOT THE FIRST AMEBA OF THE PLAYER: "+ this.actualPlayer);
				activeAmeba.setDamagePoints(0);	
				activeAmeba.checkIsDeath();		
				assert !activeAmeba.isDead();	
			}
			
			SoupSquare activeSoupSquare=game.getActiveSoupSquare();
			
			
			
			activeSoupSquare.addAmeba(activeAmeba);
			
			int x=activeSoupSquare.getX()+50;
			int y=activeSoupSquare.getY()+10;
			
			activeAmeba.setPositionOfAmeba(x, y);
			
		
			
			this.setChoseAmebaVisible(true);
			this.setChoseSetSoupSquareVisible(false);
			
			
			
			this.actualPlayer.addAmebaToOnBoard(activeAmeba);
			
			this.game.getBoard().setAnAmebaOnBoard(activeAmeba);
			
			//System.out.println("GAME: readDirection: " +this.game.getReadDirection());
			//System.out.println("GAME: BEFORE: " +this.game.getOrderPlayers());
			game.nextPlayer();
			//System.out.println("GAME: AFTER: " +this.game.getOrderPlayers());
			
		
			
		}
		else
		{
			System.out.println("A action wasn't implemented");
			//System.out.println(e.getSource());
			
			
		}
		
		
		
		////////////////
		//UPDATE DATA://	//do this in every case!
		////////////////
		this.doUpdate();
		
	
	}
	
	private boolean checkInput() {
		boolean valid=true;
		if (this.textFieldY.getText()==""  || this.textFieldX.getText()=="")
		{
			JOptionPane.showMessageDialog(null, "Error: No empty inputs allowed!");
			valid=false;
			return valid;
		}
		else
		{
			int inputNumberR= Integer.parseInt(this.textFieldY.getText());	//Y
			int inputNumberC= Integer.parseInt(this.textFieldX.getText());	//X
			
			this.buttonSetAmeba.setEnabled(true);
			
		
			
			if (game.getnumbersOfColumn()<inputNumberC || inputNumberC<1)
			{
				 JOptionPane.showMessageDialog(null, "Error: Please chose a number betweet 1 and " + game.getnumbersOfColumn() +" for Colum!");
				 this.buttonSetAmeba.setEnabled(false);
				 valid=false;
			}
			
			if (game.getnumbersOfColumn()<inputNumberR || inputNumberR<1)
			{
				 JOptionPane.showMessageDialog(null, "Error: Please chose a number betweet 1 and " + game.getnumbersOfColumn() +" for Row!");
				 this.buttonSetAmeba.setEnabled(false);
				 valid=false;
			}
			
			
			
			return valid;
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
	
		
			///////////////////
			//*RADIO BUTTONS*//
			///////////////////
			public boolean getRadioButtonAmeba1IsEnabled()
			{
				return this.radioButtonAmeba1.isEnabled();
			}
			
			public boolean getRadioButtonAmeba2IsEnabled()
			{
				return this.radioButtonAmeba2.isEnabled();
			}
			
			public boolean getRadioButtonAmeba3IsEnabled()
			{
				return this.radioButtonAmeba3.isEnabled();
			}
			public boolean getRadioButtonAmeba4IsEnabled()
			{
				return this.radioButtonAmeba4.isEnabled();
			}
			
			/////////////
			//*BUTTONS*//
			/////////////
	
			public boolean getButtonGoToPhase1IsEnabled()
			{
				return this.buttonGoToPhase1.isEnabled();
			}
			
			public boolean getButtonSetAmebaIsEnabled()
			{
				return this.buttonSetAmeba.isEnabled();
			}
			
			public boolean getButtonCheckPossibleSquareIsEnabled()
			{
				return this.buttonCheckPossibleSquare.isEnabled();
			}
	
	
			////////////////
			//*TEXTFIELDS*//
			////////////////
			
			public String getTextTextFieldY()
			{
				return this.textFieldY.getText();
			}
		
			public String getTextTextFieldX()
			{
				return this.textFieldX.getText();
			}
			
			public boolean getTextFieldXIsEnabled()
			{
				return this.textFieldX.isEnabled();
			}
			
			public boolean getTextFieldYIsEnabled()
			{
				return this.textFieldY.isEnabled();
			}
			
		
			
			
			
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
			
			public void setTextTextFieldY(String text)
			{
				this.textFieldY.setText(text);
			}
	
			public void setTextTextFieldX(String text)
			{
				this.textFieldX.setText(text);
			}
			
			
	
		/////////////////
		//FAKED EVENTS://
		/////////////////
		
			
			/////////////
			//*BUTTONS*//
			////////////
		

			public void fakeClickButtonGoToPhase1()
			{
				this.buttonGoToPhase1.isEnabled();
			}
			
			public void fakeClickButtonSetAmeba()
			{
				this.buttonSetAmeba.doClick();
			}
			
			public void fakeClickCheckPossibleSquare()
			{
				this.buttonCheckPossibleSquare.doClick();
			}
			

			///////////////////
			//*RADIO BUTTONS*//
			///////////////////
			
			public void fakeClickRadioButton1()
			{
				this.radioButtonAmeba1.doClick();
			}
			
			public void fakeClickRadioButton2()
			{
				this.radioButtonAmeba2.doClick();
			}
			
			public void fakeClickRadioButton3()
			{
				this.radioButtonAmeba3.doClick();
			}
			
			public void fakeClickRadioButton4()
			{
				this.radioButtonAmeba4.doClick();
			}
			////////////////
			//*TEXTFIELDS*//
			////////////////

			
}
