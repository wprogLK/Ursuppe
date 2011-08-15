package phases;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;


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
public class Phase4 extends JPanel implements ActionListener
{
	//BASIC THINGS OF THIS GUI:
	private boolean isActive;		//if this PhaseGui is the actual gui, the "showing gui"
	private GamePhases activePhase;	//this is IMPORTANT!
	private int playedPlayer=0;
	private Ameba amebaToSet;
	//COMPONENTS OF THIS GUI:
	
		//PANELS:
			
			private JPanel panelPhaseSetFirstAmeba =new JPanel(); 		//Need every GUI! (its the main panel) //IMPORTANT: it contains only other panels!!!
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
			
			private JLabel labelInfoPlayerBlue=new JLabel();
			private JLabel labelInfoPlayerRed=new JLabel();
			private JLabel labelInfoPlayerYellow=new JLabel();

		//TEXTFIELDS:
			private JTextField textFieldX=new JTextField(10);
			private JTextField textFieldY=new JTextField(10);
	
		//BUTTONS:
			private JButton buttonGoBack=new JButton();
			private JButton buttonExit=new JButton();
			private JButton buttonGoToPhase5=new JButton();
			private JButton buttonSetAmeba=new JButton();
			private JButton buttonCheckPossibleSquare=new JButton();
			private JButton buttonSkipPlayer=new JButton();
			
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
	private int costToSetAnAmeba=6;
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
	public Phase4(Game game) {
		//ARGUMENTES OF THE CONSTRUCTOR:
		this.game=game;
		this.board=game.getBoard();
		
		init(game);
		//Update/Initialize all the data for the components	//IMPORTANT!!!
			this.doUpdate();
			
		//CREATE ALL PANELS AND COMPONENTES:
			this.createPanels();
	
			this.setChoseAmebaVisible(true);
			this.setChoseSetSoupSquareVisible(false);
			
		
	}

	private void init(Game game) {
		//SET THE PHASE TO THE GAME:
		this.readDirection=GamePhases.phase4.getReadDirection();
		this.game.setReadDirection(readDirection);
		this.phaseTitle="Phase 4:Cell division";
		
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
			this.setLayout(null);
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
	public void activate()
	{
		this.activePhase=GamePhases.phaseEmpty;
		this.isActive=true;
		this.setVisible(true);
		
		this.addBioPointsOnActualPlayer(10);
		this.costToSetAnAmeba=this.actualPlayer.getCostOfSetAmeba();
		
		updateData();
		
		addToThisOthers();
		this.setChoseAmebaVisible(true);
	
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
		this.game.skipEmptyPlayer();
		assert !game.getActualPlayer().equals(this.game.getEmptyPlayer());
		
		
		this.actualPlayer=this.game.getActualPlayer();
		this.costToSetAnAmeba=this.actualPlayer.getCostOfSetAmeba();
	}
	
	/**
	 * UPDATE the components and class variables of this phase //IMPORTANT FOR EVERY PHASE
	 */
	public void updateComponents()
	{
		
		
		
		if(this.playedPlayer>=this.game.getNumberOfPlayers())
		{
			this.buttonGoToPhase5.setEnabled(true);
			this.buttonSkipPlayer.setEnabled(false);
			
			this.setChoseAmebaVisible(false);
			this.setChoseSetSoupSquareVisible(false);
		}
		
		///////////////////////////
		//UPDATE CLASS VARIABLES://
		///////////////////////////
		
	
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
				if(0>this.actualPlayer.getBioPoints()-this.costToSetAnAmeba)
				{
					this.buttonSkipPlayer.setText("Not enought bioPoints - No more set amebas");
					
					this.setChoseAmebaVisible(false);
					this.setChoseSetSoupSquareVisible(false);
				}
				
				
				this.setAllBounds();
		
				if(this.playedPlayer>=this.game.getNumberOfPlayers())
				{
					this.buttonGoToPhase5.setEnabled(true);
					this.buttonSkipPlayer.setEnabled(false);
					
					this.setChoseAmebaVisible(false);
					this.setChoseSetSoupSquareVisible(false);
				}
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
		this.buttonGoToPhase5.setText("Go to phase5");
		this.buttonSetAmeba.setText("Set Ameba!");
		this.buttonCheckPossibleSquare.setText("Check possible Square");
		this.buttonSkipPlayer.setText("No more set amebas");
		
		this.buttonExit.setToolTipText("Click here to end the whole game");
		this.buttonGoBack.setToolTipText("Click here to go back to phase 3");
		this.buttonGoToPhase5.setToolTipText("Click here to to phase 5");
		this.buttonSetAmeba.setToolTipText("Click here to set your ameba");
		this.buttonCheckPossibleSquare.setToolTipText("Click here to check, if your prefered soup square is a valide choice!");
		this.buttonSkipPlayer.setToolTipText("No more set amebas");
	}

	private void updateLabels() {
		this.labelInstruction.setText("Chose your ameba to set on the board, on which is no ameba of your color");
		this.labelInstructionSetSoupSquare.setText("Chose an ameba to set. (You can set only one of your amebas on  a square.)");
		this.labelGameTitle.setText(this.gameTitle);
		this.labelPhaseTitle.setText("Phase: " + this.phaseTitle);
		
		this.labelNumbers.setText("X: ");
		this.labelLetters.setText("Y: " );
		
		this.labelActualPlayer.setText("The actual player is: " + this.game.getActualPlayer().getPlayerName() + " [ " + this.game.getActualPlayer().getColor() + " ]");
		
		this.labelInfoPlayerBlue.setText(this.playerBlue.toString());
		this.labelInfoPlayerRed.setText(this.playerRed.toString());
		this.labelInfoPlayerYellow.setText(this.playerYellow.toString());
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

		calcLabels(calc);
		
		calcTextFields(calc);
		
		calcButtons(calc);
		
		calcRadioButtons(calc);
	
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
		
		calc.calcButton(this.buttonGoBack, 450, 1000);
		
		calc.calcButton(this.buttonGoToPhase5, 200, 1000);
		
		calc.calcButton(this.buttonSetAmeba, 1000, 300);
		calc.calcButton(this.buttonCheckPossibleSquare, 1100,300);
		
		calc.calcButton(this.buttonSkipPlayer, 1000, 450);
	}

	private void calcTextFields(Calc calc) {
		calc.calcTextField(this.textFieldY, 1120, 200);
		calc.calcTextField(this.textFieldX, 1120,250);
	}

	private void calcLabels(Calc calc) {
		calc.calcLabel(labelGameTitle, 10, 10);
		
		
		calc.calcLabel(this.labelPhaseTitle, 1000, 50);
		
		calc.calcLabel(this.labelInstruction, 1000, 100);
		calc.calcLabel(this.labelActualPlayer, 1000, 150);
		
		calc.calcLabel(this.labelInstructionSetSoupSquare, 1000, 100);
		calc.calcLabel(this.labelLetters, 1000, 200);
		calc.calcLabel(this.labelNumbers, 1000, 250);
		

		calc.calcLabel(this.labelInfoPlayerBlue, 10, 25);
		calc.calcLabel(this.labelInfoPlayerRed, 10, 40);
		calc.calcLabel(this.labelInfoPlayerYellow, 10, 55);
	}

	private void calcPanels(Calc calc) {
		calc.calcPanel(this, 0, 0);
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
	
	

	
	private void createPanelPhaseSetFirstAmeba()
	{	
		
		
		addToActionnListenerButtons();
	
		addToThisLabels();
		
		
	
		addToThisOthers();
		
		
		
		addToThisButtons();
		addToThisRadioButtons();
		
	}

	private void addToThisRadioButtons() {
		this.add(this.panelRadioButtons);
		this.add(this.radioButtonAmeba1);
		this.add(this.radioButtonAmeba2);
		this.add(this.radioButtonAmeba3);
		this.add(this.radioButtonAmeba4);
	}

	private void addToThisOthers() {
		this.add(this.board);
	}

	private void addToThisButtons() {
		this.add(this.buttonGoBack);
		this.add(this.buttonExit);
		this.add(this.buttonGoToPhase5);
		this.add(this.buttonSkipPlayer);
		
		this.add(this.buttonCheckPossibleSquare);
		this.add(this.buttonSetAmeba);
		
		this.add(this.textFieldY);
		this.add(this.textFieldX);
	}

	private void addToActionnListenerButtons() {
		this.buttonSetAmeba.addActionListener(this);
		this.buttonCheckPossibleSquare.addActionListener(this);
		this.buttonSkipPlayer.addActionListener(this);
	}

	private void addToThisLabels() {
		this.add(this.labelPhaseTitle);
		this.add(this.labelGameTitle);
		this.add(this.labelInstruction);
		
		this.add(this.labelInfoPlayerBlue);
		this.add(this.labelInfoPlayerRed);
		this.add(this.labelInfoPlayerYellow);
		
		
		this.add(this.labelInstructionSetSoupSquare);
		this.add(this.labelLetters);
		this.add(this.labelNumbers);
		
		this.add(this.labelActualPlayer);
	}
	
	
	
	
	private void createPanelButtons()
	{

		this.buttonGoToPhase5.setEnabled(false);
	
		addToActionListenerButtons2();
	
	}

	private void addToActionListenerButtons2() {
		this.buttonGoBack.addActionListener(this);
		this.buttonExit.addActionListener(this);
		this.buttonGoToPhase5.addActionListener(this);
	}
	
	
	
	
	
	
	private void createPanelRadioButtons()
	{
	
		addToActionListenerRadioButtons();
		
		
		addRadioButtonsToPanelRadioButtons();
		
	
		
		
	}

	private void addRadioButtonsToPanelRadioButtons() {
		this.panelRadioButtons.add(this.radioButtonAmeba1);
		this.panelRadioButtons.add(this.radioButtonAmeba2);
		this.panelRadioButtons.add(this.radioButtonAmeba3);
		this.panelRadioButtons.add(this.radioButtonAmeba4);
	}

	private void addToActionListenerRadioButtons() {
		this.radioButtonAmeba1.addActionListener(this);
		this.radioButtonAmeba2.addActionListener(this);
		this.radioButtonAmeba3.addActionListener(this);
		this.radioButtonAmeba4.addActionListener(this);
	}
	
	
	
	/////////////////////////////////
	//SPECIAL METHODS OF THIS PHASE://
	/////////////////////////////////


	
	
	private void addBioPointsOnActualPlayer(int points)
	{
		this.actualPlayer=this.game.getActualPlayer();
		
		this.actualPlayer.addBioPoints(points);
		
		this.updateData();
	}
	
	
	
	///////////
	//ACTIONS://
	////////////

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==this.buttonGoToPhase5)
		{
			this.activePhase=GamePhases.phase5;
		}
		else if (e.getSource()==this.buttonExit)
		{
			System.exit(0);
		}
		else if (e.getSource()==this.buttonGoBack)
		{
				this.activePhase=GamePhases.phase0; //Go back to phase SetNames
		}
		else if(e.getSource()==this.radioButtonAmeba1)
		{
			this.amebaToSet=this.actualPlayer.getAmebaWithNumber(1);
		
			game.setActiveAmeba(this.amebaToSet);
			
			
			
			this.radioButtonAmeba1.setEnabled(false);
			
			this.setChoseAmebaVisible(false);
			this.setChoseSetSoupSquareVisible(true);
		}
		else if(e.getSource()==this.radioButtonAmeba2)
		{
			this.amebaToSet=this.actualPlayer.getAmebaWithNumber(2);
			game.setActiveAmeba(this.amebaToSet);

			this.radioButtonAmeba2.setEnabled(false);
			
			this.setChoseAmebaVisible(false);
			this.setChoseSetSoupSquareVisible(true);
		}
		else if(e.getSource()==this.radioButtonAmeba3)
		{
			this.amebaToSet=this.actualPlayer.getAmebaWithNumber(3);
			game.setActiveAmeba(this.amebaToSet);
			
			this.radioButtonAmeba3.setEnabled(false);

			this.setChoseAmebaVisible(false);
			this.setChoseSetSoupSquareVisible(true);
		}
		else if(e.getSource()==this.radioButtonAmeba4)
		{
			this.amebaToSet=this.actualPlayer.getAmebaWithNumber(4);
			game.setActiveAmeba(this.amebaToSet);
			
			this.radioButtonAmeba4.setEnabled(false);
			
			this.setChoseAmebaVisible(false);
			this.setChoseSetSoupSquareVisible(true);
		}
		
		else if(e.getSource()==this.buttonCheckPossibleSquare)
		{
			boolean valid= this.checkInput();
			
			if(valid)
			{
				int inputNumberR= Integer.parseInt(this.textFieldY.getText()); //Y
				int inputNumberC= Integer.parseInt(this.textFieldX.getText());	//X
				
				ISquare square = this.board.getSquare(inputNumberC, inputNumberR);
				
				if (!square.isSoupSquare())
				{
					JOptionPane.showMessageDialog(null, "Error: You chose a invalid square!");	
					this.buttonSetAmeba.setEnabled(false);
				}
				else
				{
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
		else if(e.getSource()==this.buttonSkipPlayer)
		{
			this.game.nextPlayer();
			this.actualPlayer=this.game.getActualPlayer();
			this.addBioPointsOnActualPlayer(10);
			
			this.costToSetAnAmeba=this.actualPlayer.getCostOfSetAmeba();
			
			this.playedPlayer++;
			
			this.setChoseAmebaVisible(true);
			this.setChoseSetSoupSquareVisible(false);
		}
		else if(e.getSource()==this.buttonSetAmeba)
		{
		
			this.costToSetAnAmeba=this.actualPlayer.getCostOfSetAmeba();
			this.actualPlayer.subBioPoints(this.costToSetAnAmeba);
			
			int inputNumberR= Integer.parseInt(this.textFieldY.getText()); //Y
			int inputNumberC= Integer.parseInt(this.textFieldX.getText());	//X
			
			Ameba activeAmeba=game.getActiveAmeba();
			
			activeAmeba.setSquarePosition(inputNumberC, inputNumberR);
			
			activeAmeba.setDamagePoints(0);
			
			
			SoupSquare activeSoupSquare=game.getActiveSoupSquare();
			
			activeSoupSquare.addAmeba(activeAmeba);
			
			int x=activeSoupSquare.getX()+50;
			int y=activeSoupSquare.getY()+10;
			
			activeAmeba.setPositionOfAmeba(x, y);

			this.setChoseAmebaVisible(true);
			this.setChoseSetSoupSquareVisible(false);
			
			this.actualPlayer.addAmebaToOnBoard(activeAmeba);
			
			this.game.getBoard().setAnAmebaOnBoard(activeAmeba);
			
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
			
			ISquare squareUp=this.board.getSquare(inputNumberC, inputNumberR-1);
			ISquare squareDown=this.board.getSquare(inputNumberC, inputNumberR+1);
			
			ISquare squareLeft=this.board.getSquare(inputNumberC-1, inputNumberR);
			ISquare squareRight=this.board.getSquare(inputNumberC+1, inputNumberR);
			
			ArrayList<Ameba> up=squareUp.getAmebasOfColor(this.game.getActualPlayer().getColor());
			ArrayList<Ameba> down=squareDown.getAmebasOfColor(this.game.getActualPlayer().getColor());
			ArrayList<Ameba> left=squareLeft.getAmebasOfColor(this.game.getActualPlayer().getColor());
			ArrayList<Ameba> right=squareRight.getAmebasOfColor(this.game.getActualPlayer().getColor());
			
			if (up==null)
			{
				up=new ArrayList<Ameba>();
			}
			
			if (down==null)
			{
				down=new ArrayList<Ameba>();
			}
			
			if (left==null)
			{
				left=new ArrayList<Ameba>();
			}
			
			if (right==null)
			{
				up=new ArrayList<Ameba>();
			}
			
			
			if(up.isEmpty() && down.isEmpty() && left.isEmpty() && right.isEmpty())
			{
				valid=false;
				
				JOptionPane.showMessageDialog(null, "Error: set ameba there is not possible!");
			}
			else
			{
				///Possible to set
				valid=true;
				this.buttonSetAmeba.setEnabled(true);
			}
			
			
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
	
			public Ameba getAmebaToSet()
			{
				return this.amebaToSet;
			}

			////////////
			//*LABELS*//
			////////////
			
			/////////////
			//*BUTTONS*//
			/////////////
			public boolean getButtonGoBackIsEnabled()
			{
				return this.buttonGoBack.isEnabled();
			}
			public boolean getButtonExitIsEnabled()
			{
				return this.buttonExit.isEnabled();
			}
			public boolean getButtonGoToPhase5IsEnabled()
			{
				return this.buttonGoToPhase5.isEnabled();
			}
			public boolean getButtonSetAmebaIsEnabled()
			{
				return this.buttonSetAmeba.isEnabled();
			}
			public boolean getButtonCheckPossibleSquareIsEnabled()
			{
				return this.buttonCheckPossibleSquare.isEnabled();
			}
			public boolean getButtonSkipPlayerIsEnabled()
			{
				return this.buttonSkipPlayer.isEnabled();
			}
	
			///////////////////
			//*RADIO BUTTONS*//
			///////////////////

			public boolean getRadioButtonAmeba1IsEnabled()
			{
				return this.radioButtonAmeba1.isEnabled();
			}
			public boolean getRadioButtonAmeba3IsEnabled()
			{
				return this.radioButtonAmeba3.isEnabled();
			}
			public boolean getRadioButtonAmeba4IsEnabled()
			{
				return this.radioButtonAmeba4.isEnabled();
			}
			public boolean getRadioButtonAmeba2IsEnabled()
			{
				return this.radioButtonAmeba2.isEnabled();
			}

	

			////////////////
			//*TEXTFIELDS*//
			////////////////
			public boolean getTextFieldtextFieldXIsEnabled()
			{
				return this.textFieldX.isEnabled();
			}
			public boolean geTextFieldtextFieldYIsEnbled()
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
			public void setTexttextFieldX(String text)
			{
				this.textFieldX.setText(text);
				
			}
			public void setTexttextFieldY(String text)
			{
				this.textFieldY.setText(text);
				
			}
					
	
	
	
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
			public void fakeClickbuttonGoBack()
			{
				this.buttonGoBack.doClick();
			}
			public void fakeClickbuttonGoToPhase5()
			{
				this.buttonGoToPhase5.doClick();
			}
			public void fakeClickbuttonSetAmeba()
			{
				this.buttonSetAmeba.doClick();
			}
			public void fakeClickbuttonCheckPossibleSquare()
			{
				this.buttonCheckPossibleSquare.doClick();
			}
			public void fakeClickbuttonSkipPlayer()
			{
				this.buttonSkipPlayer.doClick();
			}
		
			

			///////////////////
			//*RADIO BUTTONS*//
			///////////////////
			public void fakeClickradioButtonAmeba1()
			{
				this.radioButtonAmeba1.doClick();
			}
			public void fakeClickradioButtonAmeba2()
			{
				this.radioButtonAmeba2.doClick();
			}
			public void fakeClickradioButtonAmeba3()
			{
				this.radioButtonAmeba3.doClick();

			}
			public void fakeClickradioButtonAmeba4()
			{
				this.radioButtonAmeba4.doClick();
				
			}


	
			////////////////
			//*TEXTFIELDS*//
			////////////////

			
}
