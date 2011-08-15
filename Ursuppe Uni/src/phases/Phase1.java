package phases;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;


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
public class Phase1 extends JPanel implements ActionListener
{
	//BASIC THINGS OF THIS GUI:
	private boolean isActive;		//if this PhaseGui is the actual gui, the "showing gui"
	private GamePhases activePhase;	//this is IMPORTANT!
	private Ameba actualAmeba;
	private int playedPlayer=0;
	private ArrayList<Ameba> amebaOfActualPlayerOnBoard=new ArrayList<Ameba>();
	//COMPONENTS OF THIS GUI:
	
		//PANELS:
			
			private JPanel panelPhaseSetFirstAmeba =new JPanel(); 		//Need every GUI! (its the main panel) //IMPORTANT: it contains only other panels!!!
			
			
		//LABELS:
			private JLabel labelGameTitle=new JLabel();
			private JLabel labelPhaseTitle=new JLabel();
			
			private JLabel labelActualPlayer=new JLabel();
			
			private JLabel labelInstruction=new JLabel();
			
			private JLabel labelInstructionMoveOrDrift=new JLabel();
			private JLabel labelInstructionRollDie=new JLabel();
			private JLabel labelInstructionFreeMove=new JLabel();
			private JLabel labelInstructionChoseFoodToEat=new JLabel();
			
			private JLabel labelInfoPlayerBlue=new JLabel();
			private JLabel labelInfoPlayerRed=new JLabel();
			private JLabel labelInfoPlayerYellow=new JLabel();
			
			private JLabel labelNrBlueFoodToEat=new JLabel();
			private JLabel labelNrRedFoodToEat=new JLabel();
			private JLabel labelNrYellowFoodToEat=new JLabel();

		//TEXTFIELDS:
			private JTextField textFieldNrOfBlueFoodToEat=new JTextField(10);
			private JTextField textFieldNrOfRedFoodToEat=new JTextField(10);
			private JTextField textFieldNrOfYellowFoodToEat=new JTextField(10);
	
		//BUTTONS:
			private JButton buttonGoBack=new JButton();
			private JButton buttonExit=new JButton();
			private JButton buttonGoToPhase2=new JButton();
			
		
			private JButton buttonSetFoodToEat=new JButton();
			
			private JButton buttonMove=new JButton();
			private JButton buttonDrift=new JButton();
			
			private JButton buttonRollDie=new JButton();
			
			private JButton buttonGoUp=new JButton();
			private JButton buttonGoRight=new JButton();
			private JButton buttonGoDown=new JButton();
			private JButton buttonGoLeft=new JButton();
			private JButton buttonGoMiddle=new JButton();
			
		//RADIO BUTTONS:
	
	
		//BOXES
		
	
		//OTHER STUFF
		
		
		//SPECIAL COMPONENTS:
		private Board board;
		private Die die;
		
	//VARIABLES OF THIS PHASE:
	
	private int costOfMove=1;	
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
	public Phase1(Game game) {
		//ARGUMENTES OF THE CONSTRUCTOR:
		this.game=game;
		this.board=game.getBoard();
		
		init(game);
		
			
			//this.updateData();
			
		//CREATE ALL PANELS AND COMPONENTES:
			this.createPanels();
	
			setDialogues();
			
			
		
	}

	private void setDialogues() {
		this.setDialoge1(true);
		this.setDialoge2(false);
		this.setDialoge3(false);
		this.setDialoge4(false);
	}

	private void init(Game game) {
		this.actualAmeba=game.getPlayer(GameColor.blue).getAmebasOffBoard().get(1);
		
		//SET THE PHASE TO THE GAME:
		this.readDirection=GamePhases.phase1.getReadDirection();
		this.game.setReadDirection(readDirection);
		this.phaseTitle="Phase 1: Move or drift amebas, eat and shit";
		
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
		
		//CONFIGURE THIS PANEL:
			//here you can set the Layout and other elementary things
			this.setLayout(null);
			//this.setSize(700,700); //TODO
			
			this.setVisible(true);
			
			this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		//Update/Initialize all the data for the components	//IMPORTANT!!!
			this.loadAmebasInList();
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
	
		private void setEnabledOwnTextField()
		{
			switch(this.game.getActualPlayer().getColor())
			{
			case blue:
			{
				disableBlue();
				
				break;
			}
			case red:
			{
				disableRed();
				
				break;
			}
			case yellow:
			{
				disableYellow();
				
				break;
			}
			default:
			{
				System.out.println("Error in Phase1.class: unkown case in method setEnabledOwnTextField()!");
				//TODO
				break;
			}
			}
		}

		private void disableYellow() {
			this.textFieldNrOfBlueFoodToEat.setEnabled(true);
			this.textFieldNrOfRedFoodToEat.setEnabled(true);
			this.textFieldNrOfYellowFoodToEat.setEnabled(false);
		}

		private void disableRed() {
			this.textFieldNrOfBlueFoodToEat.setEnabled(true);
			this.textFieldNrOfRedFoodToEat.setEnabled(false);
			this.textFieldNrOfYellowFoodToEat.setEnabled(true);
		}

		private void disableBlue() {
			this.textFieldNrOfBlueFoodToEat.setEnabled(false);
			this.textFieldNrOfRedFoodToEat.setEnabled(true);
			this.textFieldNrOfYellowFoodToEat.setEnabled(true);
		}
	
	
	
	
	
	///////////////////////////////////
	////////ACTIVATE/DEACTIVATE:///////
	///////////////////////////////////
	//set this GUI to the active GUI (call by GUILOGIC)
	public void activate()
	{
		this.loadAmebasInList();
		this.nextAmebaInList();
		//System.out.println("Actual Ameba: " + this.actualAmeba);
		//System.out.println("------------------ACTIVATE PHASE 1----------------");
		this.activePhase=GamePhases.phaseEmpty;
		this.isActive=true;
		this.setVisible(true);
		doUpdate();
		
		this.add(this.board);	//IMPORTANT!!
		
		setDialogues();
		
		
	
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
		//this.checkIsNextPlayerOnTurn();
		if (this.playedPlayer==this.game.getNumberOfPlayers())
		{
			//System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEeeeeeeeeee");
			this.buttonGoToPhase2.setEnabled(true);
		}
		//System.out.println("NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNn" + this.playedPlayer);
		
		this.checkFinishPhase1();
		
		this.game.skipEmptyPlayer();
		
		assert !game.getActualPlayer().equals(this.game.getEmptyPlayer());
		
		
		///////////////////////////
		//UPDATE CLASS VARIABLES://
		///////////////////////////
		
		
		
		this.actualPlayer=this.game.getActualPlayer();
		this.costOfMove=this.actualPlayer.getCostOfMoveAnAmeba();
		
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
				updateTextFields();
				
				this.setEnabledOwnTextField();
				
				//BUTTONS:
				updateButtons();
				
				
				//Dialogue1:
				updateDialogue1WriggleOrDrift();
				
				//Dialogue2:
				updateDialogue2Roll();
				
				//Dialogue3:
				
				updateDialogue3FreeMove();
				
				//Dialogue4:
				updateDialogue4Eat();
				
				
				//RADIOBUTTONS:
				
				
				//BOXES:
				
				//OTHER STUFF:
				
				//SPECIAL STUFF:
				
				//SPECIAL METHODS:
				
			
			
				this.setAllBounds();
				
				if (this.playedPlayer==this.game.getNumberOfPlayers())
				{
					//System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEeeeeeeeeee");
					this.buttonGoToPhase2.setEnabled(true);
				}
		
	}

	private void updateDialogue4Eat() {
		this.buttonSetFoodToEat.setText("Eat this food!");
		
		this.buttonSetFoodToEat.setToolTipText("Click here let your ameba moves/drift first and then eat food");
	}

	private void updateDialogue3FreeMove() {
		this.buttonGoUp.setText("Go north");
		this.buttonGoLeft.setText("Go west");
		this.buttonGoMiddle.setText("Stay here");
		this.buttonGoRight.setText("Go east");
		this.buttonGoDown.setText("Go south");
		
		this.buttonGoUp.setToolTipText("Your ameba will go up if it can");
		this.buttonGoLeft.setToolTipText("Your ameba will go left if it can");
		this.buttonGoMiddle.setToolTipText("Your ameba will stay here");
		this.buttonGoRight.setToolTipText("Your ameba will go right if it can");
		this.buttonGoDown.setToolTipText("Your ameba will go down if it can");
	}

	private void updateDialogue2Roll() {
		this.buttonRollDie.setText("Roll the die!");
		
		this.buttonRollDie.setToolTipText("Roll the die to set the direction where your ameba will move to");
	}

	private void updateDialogue1WriggleOrDrift() {
		this.buttonMove.setText("Wriggle");
		this.buttonDrift.setText("Let drift");

		this.buttonMove.setToolTipText("Click here if your ameba should wriggle");
		this.buttonDrift.setToolTipText("Click here if your ameba should move with the wind direction");
		
					//IMPLEMENTED HERE GENE STROMLINIENFORM! PART 1:
		if(this.actualPlayer.getGenes().contains(GameGene.Stromlienenform))
		{
			this.buttonMove.setEnabled(true);
			System.out.println("You have gene Stromlinienform! You haven't pay bioPoints here!");
		}
		else if(this.actualPlayer.getBioPoints()-this.costOfMove<0)
		{
			this.buttonMove.setEnabled(false);
			this.buttonMove.setToolTipText("You have not enough bioPoints! You need at least "+ this.costOfMove + " bioPoint(s)");
			//
		}
		else
		{
			this.buttonMove.setEnabled(true);
		}
	}

	private void updateButtons() {
		this.buttonExit.setText("Exit...");
		this.buttonGoBack.setText("Go back to the previous phase");
		this.buttonGoToPhase2.setText("Go to phase2");
		
		
		this.buttonExit.setToolTipText("Click here to end the whole game");
		this.buttonGoBack.setToolTipText("Click here to go back to phase 0");
		this.buttonGoToPhase2.setToolTipText("Click here to to phase 1");
	}

	private void updateTextFields() {
		this.textFieldNrOfBlueFoodToEat.setText("0");
		this.textFieldNrOfRedFoodToEat.setText("0");
		this.textFieldNrOfYellowFoodToEat.setText("0");
	}

	private void updateLabels() {
		this.labelInstructionMoveOrDrift.setText("Chose if your ameba " + this.actualAmeba.getNumber() + " should drift or move (cost " + this.costOfMove + " BioPoint(s).");
		this.labelInstructionRollDie.setText("Roll the direction where your ameba is going to move.");
		this.labelInstructionFreeMove.setText("You had luck, you rolled a 6: Chose where your ameba should move to");
		this.labelInstructionFreeMove.setText("Your ameba has to eat after it moved or drift. Please chose what kind of food your ameba shoud eat and how many of it. Note every ameba must eat 3 Foods, at least one of each color, but not its own color!");
		
		this.labelGameTitle.setText(this.gameTitle);
		this.labelPhaseTitle.setText("Phase: " + this.phaseTitle);
		
		this.labelInfoPlayerBlue.setText(this.playerBlue.toString());
		this.labelInfoPlayerRed.setText(this.playerRed.toString());
		this.labelInfoPlayerYellow.setText(this.playerYellow.toString());
		
		this.labelActualPlayer.setText("The actual player is: " + this.game.getActualPlayer().getPlayerName() + " [ " + this.game.getActualPlayer().getColor() + " ]");
		
		this.labelNrBlueFoodToEat.setText("Nr of blue food to eat: ");
		this.labelNrRedFoodToEat.setText("Nr of red food to eat: ");
		this.labelNrYellowFoodToEat.setText("Nr of yellow food to eat: ");
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
		
		setDialogues();
		
		this.buttonGoToPhase2.setEnabled(true);

	}
	
	
	
	/*
	 * Call this method after every update!! IMPORTANT
	 */
	private void setAllBounds()
	{
		Calc calc=new Calc();
		
		
		calcPanels(calc);
		
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
		calcLabels(calc);
		
		////////////////////
		//TEXTFIELDS://
		///////////////
		calcTextFields(calc);
		
		
		////////////////
		//BUTTONS://
		////////////
		calcButtosn(calc);
		
		////////////////////////
		//RADIOBUTTONS://
		///////////////////
		
		///////////////
		//OTHERS://
		///////////
		
		calcOthers(calc);
		
	
	}

	private void calcOthers(Calc calc) {
		calc.calcBoard(this.board, 100, 100);
	}

	private void calcButtosn(Calc calc) {
		calc.calcButton(this.buttonExit, 100, 1000);
		calc.calcButton(this.buttonGoBack, 450, 1000);
		calc.calcButton(this.buttonGoToPhase2, 200, 1000);
		
		//Dialogue1:
		calc.calcButton(this.buttonMove, 1000, 300); 
		calc.calcButton(this.buttonDrift, 1100, 300);
	
		
		//Dialogue2:
		calc.calcButton(this.buttonRollDie, 1000, 300);
		calc.calcDie(this.die, 1100, 400);
	

		//Dialogue3:
		
	
		calc.calcButton(this.buttonGoUp, 1100, 250);
		
		calc.calcButton(this.buttonGoLeft, 900, 300);
		calc.calcButton(this.buttonGoMiddle,1100, 300);
		calc.calcButton(this.buttonGoRight, 1300, 300);
		
		calc.calcButton(this.buttonGoDown, 1100, 350);
		
		//Dialoge4:
		calc.calcButton(this.buttonSetFoodToEat, 1100, 400);
	}

	private void calcTextFields(Calc calc) {
		calc.calcTextField(this.textFieldNrOfBlueFoodToEat, 1200, 300);
		calc.calcTextField(this.textFieldNrOfRedFoodToEat,1200,325);
		calc.calcTextField(this.textFieldNrOfYellowFoodToEat,1200,350);
	}

	private void calcLabels(Calc calc) {
		calc.calcLabel(labelGameTitle, 10, 10);
		calc.calcLabel(this.labelPhaseTitle, 1000, 50);
		
		calc.calcLabel(this.labelActualPlayer, 1000, 150);
		
		calc.calcLabel(this.labelInstructionFreeMove, 1000, 100);
		calc.calcLabel(this.labelInstructionMoveOrDrift, 1000, 100);
		calc.calcLabel(this.labelInstructionRollDie, 1000, 100);
		calc.calcLabel(this.labelInstructionChoseFoodToEat, 1000, 100);
		
		
		calc.calcLabel(this.labelInfoPlayerBlue, 10, 25);
		calc.calcLabel(this.labelInfoPlayerRed, 10, 40);
		calc.calcLabel(this.labelInfoPlayerYellow, 10, 55);
		
		calc.calcLabel(this.labelNrBlueFoodToEat, 1000, 300);
		calc.calcLabel(this.labelNrRedFoodToEat, 1000, 325);
		calc.calcLabel(this.labelNrYellowFoodToEat, 1000, 350);
	}

	private void calcPanels(Calc calc) {
		calc.calcPanel(this, 0, 0);
	}
	

	private void setDialoge1(boolean visible)
	{
		
		
		this.labelInstructionMoveOrDrift.setVisible(visible);
		this.buttonDrift.setVisible(visible);
		this.buttonMove.setVisible(visible);
		
		if (this.actualPlayer.getBioPoints()<=0)
		{
			this.buttonMove.setEnabled(false);
			this.buttonMove.setToolTipText("You have not enough BioPoints to move this ameba!");
		}
		
		
	}
	
	private void setDialoge2(boolean visible)
	{
	
		
		this.labelInstructionRollDie.setVisible(visible);
		this.buttonRollDie.setVisible(visible);
		this.die.setVisible(visible);
		
		//System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD Die "+ this.die.getX() + "  " +this.die.getY());
		this.add(this.die);
		this.die.repaint();
	
	}
	
	private void setDialoge3(boolean visible)
	{
		
		
		this.labelInstructionFreeMove.setVisible(visible);
		this.add(this.die);
		this.die.repaint();
		this.buttonGoDown.setVisible(visible);
		this.buttonGoLeft.setVisible(visible);
		this.buttonGoRight.setVisible(visible);
		this.buttonGoUp.setVisible(visible);
		this.buttonGoMiddle.setVisible(visible);
	}
	
	private void setDialoge4(boolean visible)
	{
		this.labelInstructionChoseFoodToEat.setVisible(visible);
		this.add(this.die);
		this.die.repaint();
		this.labelNrBlueFoodToEat.setVisible(visible);
		this.labelNrRedFoodToEat.setVisible(visible);
		this.labelNrYellowFoodToEat.setVisible(visible);
		
		this.buttonSetFoodToEat.setVisible(visible);
		
		this.textFieldNrOfBlueFoodToEat.setVisible(visible);
		this.textFieldNrOfRedFoodToEat.setVisible(visible);
		this.textFieldNrOfYellowFoodToEat.setVisible(visible);
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
		addToActionListenerButtons();
	

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
		addToThisLabels();
		
	
		//this.add(this.panelInterface);
		addToThisOthers();
		
		
		addToThisButtons();
		addToThisTextFields();

	}

	private void addToThisTextFields() {
		this.add(this.textFieldNrOfBlueFoodToEat);
		this.add(this.textFieldNrOfRedFoodToEat);
		this.add(this.textFieldNrOfYellowFoodToEat);
	}

	private void addToThisButtons() {
		this.add(this.buttonGoBack);
		this.add(this.buttonExit);
		this.add(this.buttonGoToPhase2);
		
		
		//Dialogue1:
		this.add(this.buttonDrift);
		this.add(this.buttonMove);
		
		//Dialogue2:
		this.add(this.buttonRollDie);
		
		//Dialogue3:
		this.add(this.buttonGoUp);
		this.add(this.buttonGoLeft);
		this.add(this.buttonGoDown);
		this.add(this.buttonGoRight);
		this.add(this.buttonGoMiddle);
		
		//Dialogue4:
		this.add(this.buttonSetFoodToEat);
	}

	private void addToThisOthers() {
		this.add(this.board);
		this.add(this.die);
		this.die.repaint();
	}

	private void addToThisLabels() {
		this.add(this.labelPhaseTitle);
		this.add(this.labelGameTitle);
		
		this.add(this.labelInfoPlayerBlue);
		this.add(this.labelInfoPlayerRed);
		this.add(this.labelInfoPlayerYellow);
		
		this.add(this.labelActualPlayer);
		
		this.add(this.labelInstructionFreeMove);
		this.add(this.labelInstructionMoveOrDrift);
		this.add(this.labelInstructionRollDie);
		this.add(this.labelInstructionChoseFoodToEat);
		
		this.add(this.labelNrBlueFoodToEat);
		this.add(this.labelNrRedFoodToEat);
		this.add(this.labelNrYellowFoodToEat);
	}

	private void addToActionListenerButtons() {
		this.buttonGoBack.addActionListener(this);
		this.buttonExit.addActionListener(this);
		this.buttonGoToPhase2.addActionListener(this);
		
		this.buttonDrift.addActionListener(this);
		this.buttonMove.addActionListener(this);
		
		this.buttonRollDie.addActionListener(this);
		
		this.buttonGoUp.addActionListener(this);
		this.buttonGoLeft.addActionListener(this);
		this.buttonGoDown.addActionListener(this);
		this.buttonGoRight.addActionListener(this);
		this.buttonGoMiddle.addActionListener(this);
		
		this.buttonSetFoodToEat.addActionListener(this);
	}
	
	
	
	
	
	
	


	
	
	
	/////////////////////////////////
	//SPECIAL METHODS OF THIS PHASE://
	/////////////////////////////////
	public boolean checkOneChooseOfFood()
	{
		ISquare squareToGoTo=this.board.getSquareMoveTo(this.actualAmeba);
		
		int blueFoodOnTargetSquare=squareToGoTo.getNrOfFood(GameColor.blue);
		int redFoodOnTargetSquare=squareToGoTo.getNrOfFood(GameColor.red);
		int yellowFoodOnTargetSquare=squareToGoTo.getNrOfFood(GameColor.yellow);
		
		int sumOfFoodsOnTargetSquare=0; 	//without the foods of the color of the actual player!
		
		sumOfFoodsOnTargetSquare = calcSumOfFoodOnTargetSquare(
				blueFoodOnTargetSquare, redFoodOnTargetSquare,
				yellowFoodOnTargetSquare, sumOfFoodsOnTargetSquare);
		
		
		boolean valid=false;
		
		if(sumOfFoodsOnTargetSquare==this.actualPlayer.getNrOfFoodsToEat())
		{
			valid=true;
		}
		
		
		if(valid )
		{
		//	System.out.println("####################AUTOMATIC CHOOSE FOOD#############################");
			
			setFoodsToEatByAmeba(blueFoodOnTargetSquare, redFoodOnTargetSquare,yellowFoodOnTargetSquare);
			
			
			
			
		
	//		System.out.println("ACTUAL AMEBA MOVE:  " + this.actualAmeba);
			this.board.moveAnAmeba(this.actualAmeba);
			
			this.amebaOfActualPlayerOnBoard.remove(0);	
		//	System.out.println("LIST AFTER DRIFT: " + this.amebaOfActualPlayerOnBoard);
			
			setDialogues();
			
			return true;
		}
		
		return false;
	}

	private void setFoodsToEatByAmeba(int blueFoodOnTargetSquare,
			int redFoodOnTargetSquare, int yellowFoodOnTargetSquare) {
		this.nextAmebaInList();
		
		switch(this.actualPlayer.getColor())
		{
			case blue:
			{
				this.actualAmeba.setNrOfEatFoodOfColor(GameColor.blue, 0);
				this.actualAmeba.setNrOfEatFoodOfColor(GameColor.red, redFoodOnTargetSquare);
				this.actualAmeba.setNrOfEatFoodOfColor(GameColor.yellow, yellowFoodOnTargetSquare);
				
				break;
			}
			case red:
			{
				this.actualAmeba.setNrOfEatFoodOfColor(GameColor.blue, blueFoodOnTargetSquare);
				this.actualAmeba.setNrOfEatFoodOfColor(GameColor.red, 0);
				this.actualAmeba.setNrOfEatFoodOfColor(GameColor.yellow, yellowFoodOnTargetSquare);
				
				break;
			}
			case yellow:
			{
				this.actualAmeba.setNrOfEatFoodOfColor(GameColor.blue, yellowFoodOnTargetSquare);
				this.actualAmeba.setNrOfEatFoodOfColor(GameColor.red, redFoodOnTargetSquare);
				this.actualAmeba.setNrOfEatFoodOfColor(GameColor.yellow, 0);
				
				break;
			}
			default:
			{
				System.out.println("Error in Phase1.class: unkown case in checkOneChooseOfFood()");
				//TODO
				break;
			}
		}
	}
	
	
	public void checkValidNrOfFoods(int blueFood, int redFood, int yellowFood)
	{
		if(!checkOneChooseOfFood());
		{
			ISquare squareToGoTo=this.board.getSquareMoveTo(this.actualAmeba);
		
			int blueFoodOnTargetSquare=squareToGoTo.getNrOfFood(GameColor.blue);
			int redFoodOnTargetSquare=squareToGoTo.getNrOfFood(GameColor.red);
			int yellowFoodOnTargetSquare=squareToGoTo.getNrOfFood(GameColor.yellow);
			
			
			
			int resultBlue=blueFoodOnTargetSquare-blueFood;
			int resultRed=redFoodOnTargetSquare-redFood;
			int resultYellow=yellowFoodOnTargetSquare-yellowFood;
			

			
			
			String errorMessage="This combination is not possible: Not enough food on the target square. Missing food: ";
			boolean valid=true;
			
			if(resultBlue<0)
			{
				valid=false;
				errorMessage=errorMessage+" Blue " + resultBlue + "  ";
			}
			
			if(resultRed<0)
			{
				valid=false;
				errorMessage=errorMessage+" Red " + resultRed + "  ";
			}
			
			if(resultYellow<0)
			{
				valid=false;
				errorMessage=errorMessage+" Yellow " + resultYellow + "  ";
			}
			
			
			int sumOfFoodsOnTargetSquare=0; 	//without the foods of the color of the actual player!
			
			sumOfFoodsOnTargetSquare = calcSumOfFoodOnTargetSquare(blueFoodOnTargetSquare, redFoodOnTargetSquare,yellowFoodOnTargetSquare, sumOfFoodsOnTargetSquare);
			//int totalSumOfFoodsOnTargetSquare=blueFoodOnTargetSquare+redFoodOnTargetSquare+yellowFoodOnTargetSquare;
			
			boolean notEnoughFoodOnSquare=false;
			boolean notEnoughFoodChoose=false;
			boolean tooMuchFoodChoose=false;
			
			//System.out.println("###############################SUM OF FOODS ON TARGET SQUARE IS:  " + sumOfFoodsOnTargetSquare);
			//System.out.println("blueFood+redFood+yellowFood: "+ blueFood+ " " +redFood + " " + yellowFood);
		
			
			
			if(blueFood+redFood+yellowFood<this.actualPlayer.getNrOfFoodsToEat())
			{
				notEnoughFoodChoose=true;
				valid=false;
			}
			
			if(blueFood+redFood+yellowFood>this.actualPlayer.getNrOfFoodsToEat())
			{
				tooMuchFoodChoose=true;
				valid=false;
			
			}
			
			
			if (sumOfFoodsOnTargetSquare<this.actualPlayer.getNrOfFoodsToEat())			//TODO(16.05.11) IMPORTANT CHECK THIS METHOD!!!!
			{
				notEnoughFoodOnSquare=true;
				valid=false;
			}
			
			//System.out.println("SUM OF FOOD ON TARGET: "+ sumOfFoodsOnTargetSquare);
			
			
			doEatOrError(blueFood, redFood, yellowFood, errorMessage, valid,notEnoughFoodOnSquare, notEnoughFoodChoose,tooMuchFoodChoose);
		}
		
	}

	private void doEatOrError(int blueFood, int redFood, int yellowFood, String errorMessage, boolean valid, boolean notEnoughFoodOnSquare,boolean notEnoughFoodChoose, boolean tooMuchFoodChoose) {
		if(valid || notEnoughFoodOnSquare)
		{
			this.nextAmebaInList();
			
			this.actualAmeba.setNrOfEatFoodOfColor(GameColor.blue, blueFood);
			this.actualAmeba.setNrOfEatFoodOfColor(GameColor.red, redFood);
			this.actualAmeba.setNrOfEatFoodOfColor(GameColor.yellow, yellowFood);
			
			//TODO AMEBA IS HUNGRY
			
			//Move ameba:
			//System.out.println("ACTUAL AMEBA MOVE:  " + this.actualAmeba);
			this.board.moveAnAmeba(this.actualAmeba);
			
			
			this.amebaOfActualPlayerOnBoard.remove(0);	
			//System.out.println("LIST AFTER DRIFT: " + this.amebaOfActualPlayerOnBoard);
			
			setDialogues();
			
		}
		else if(notEnoughFoodChoose)
		{
			JOptionPane.showMessageDialog(null, "Please, your ameba would like to eat " + this.actualPlayer.getNrOfFoodsToEat() + " foods! You had not enough!");
		}
		else if(tooMuchFoodChoose)
		{
			JOptionPane.showMessageDialog(null, "Your ameba will be happy if it can eat " + this.actualPlayer.getNrOfFoodsToEat() + " foods! You have too many!");
		}
		else
		{	
			
			JOptionPane.showMessageDialog(null, errorMessage);
		}
	}

	private int calcSumOfFoodOnTargetSquare(int blueFoodOnTargetSquare,int redFoodOnTargetSquare, int yellowFoodOnTargetSquare,int sumOfFoodsOnTargetSquare) {
		switch(this.actualPlayer.getColor())
		{
			case blue:
			{
				sumOfFoodsOnTargetSquare=redFoodOnTargetSquare+yellowFoodOnTargetSquare;
				
				
				
				break;
			}
			case red:
			{
				sumOfFoodsOnTargetSquare=blueFoodOnTargetSquare+yellowFoodOnTargetSquare;

				
				
				break;
			}
			case yellow:
			{
				sumOfFoodsOnTargetSquare=blueFoodOnTargetSquare+redFoodOnTargetSquare;

			
				
				break;
			}
			default:
			{
				System.out.println("Error in Phase1.class: unkown case in checkValidNrOfFoods(int blueFood, int redFood, int yellowFood)");
				//TODO
				break;
			}
		}
		return sumOfFoodsOnTargetSquare;
	}
	
	

	private void checkIsNextPlayerOnTurn()
	{
		if(this.amebaOfActualPlayerOnBoard.size()==0)
		{
			//System.out.println("Player on turn: "+ this.actualPlayer);
			//System.out.println("EMPTY AMEBAS LIST! NEXT PLAYER");
			
			this.game.nextPlayer();
			this.loadAmebasInList();
			
			this.buttonGoToPhase2.setEnabled(true);
			this.playedPlayer++;
		}
		else
		{
			//System.out.println("List not empty: "+ this.amebaOfActualPlayerOnBoard);
		}
		
		
	}
	
	private void checkFinishPhase1()
	{
		if (this.game.getActualPlayer().equals(this.game.getEmptyPlayer()))
		{
			this.buttonGoToPhase2.setEnabled(true);
			this.setDialoge1(false);
			this.setDialoge2(false);
			this.setDialoge3(false);
			this.setDialoge4(false);
			
			
			
			
		}
		else
		{
			this.buttonGoToPhase2.setEnabled(false);
		}
	}
	
	private void nextAmebaInList()
	{
		assert !this.amebaOfActualPlayerOnBoard.isEmpty();
		this.actualAmeba=this.amebaOfActualPlayerOnBoard.get(0);
		//this.amebaOfActualPlayerOnBoard.remove(0);				//TODO: do this after move the ameba!
	}
	
	private void loadAmebasInList()
	{
		//TODO: check this!
		this.checkFinishPhase1();
		this.game.skipEmptyPlayer();
		
		this.actualPlayer=this.game.getActualPlayer();
		//System.out.println("///////////////////////////////ACTUAL PLAYER IN LAOD AMBEAS IN LIST: "+ this.actualPlayer);
		ArrayList<Ameba> amebasOriginal=this.actualPlayer.getAmebasOnBoard();
		
		ArrayList<Ameba> amebas=new ArrayList<Ameba>();
		
		
		for (int i=0; i<amebasOriginal.size(); i++)
		{
			amebas.add(amebasOriginal.get(i));
		}
	
		
		
		//assert this.amebaOfActualPlayerOnBoard.isEmpty();
		this.amebaOfActualPlayerOnBoard=new ArrayList<Ameba>();
		
		while (!amebas.isEmpty())
		{
			Ameba ameba=amebas.get(0);
			
			for (int i=0; i<amebas.size();i++)
			{
				Ameba ameba2=amebas.get(i);
				if (ameba.getNumber()>ameba2.getNumber())
				{
					ameba=ameba2;
				}
			}
			
			this.amebaOfActualPlayerOnBoard.add(ameba);
			amebas.remove(ameba);
		}
		
		
		
	
		
		//System.out.println("Amebas on board sort: " + amebaOfActualPlayerOnBoard);
		
		
		
		
		//System.out.println("load amebas in stack OK!");
	}
	
	
	
	
	
	///////////
	//ACTIONS://
	////////////

	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO: AB HIER FREITAG WEITER MACHEN
		
		if(e.getSource()==this.buttonGoToPhase2)
		{
			//System.out.println("GO NOW TO PHASE 2!");
			
			this.activePhase=GamePhases.phase2;
		}
		else if (e.getSource()==this.buttonExit)
		{
			//System.out.println("ESCAPE THE GAME! (Part2)"); //TODO
			System.exit(0);
		}
		else if (e.getSource()==this.buttonGoBack)
		{
				this.activePhase=GamePhases.phaseSetFirstAmeba; //Go back to phase SetNames
				//System.out.println("GO TO phaseSetFirstAmeba");
		}
		else if(e.getSource()==this.buttonDrift)
		{
			showDriftDialogue();
		}
		else if(e.getSource()==this.buttonMove)
		{
			showMoveDialogue();
		}
		else if(e.getSource()==this.buttonRollDie)
		{
			int faceValue=this.die.roll();
			//System.out.println("777777777777777777777777: FACE VALUE: "+ faceValue);
			GameDirection direction=GameDirection.Free;
			
			direction=direction.getGameDirection(faceValue);
			
			if (direction==GameDirection.Free)
			{
				showFreeMoveDialogue();
				
				
			}
			else
			{
				showEatDialogue(direction);
				
			
				
			}
		}
		else if(e.getSource()==this.buttonGoUp)
		{
			this.board.setSingleWindDirection(GameDirection.North);		//TODO MAY BE DELETE THIS LINE?!
			//this.board.moveAnAmeba(this.actualAmeba);
			//this.amebaOfActualPlayerOnBoard.remove(0);
			
			showDialogue4();
			//TODO
		}
		else if(e.getSource()==this.buttonGoLeft)
		{
			this.actualAmeba.setDirection(GameDirection.West);
			//this.board.setSingleWindDirection(GameDirection.West);
			//this.board.moveAnAmeba(this.actualAmeba);
			//this.amebaOfActualPlayerOnBoard.remove(0);
			
			showDialogue4();
		}
		else if(e.getSource()==this.buttonGoDown)
		{
			this.actualAmeba.setDirection(GameDirection.South);
			//this.board.setSingleWindDirection(GameDirection.South);
			//this.board.moveAnAmeba(this.actualAmeba);
			//this.amebaOfActualPlayerOnBoard.remove(0);
			
			showDialogue4();
	
			//TODO
		}
		else if(e.getSource()==this.buttonGoRight)
		{
			this.actualAmeba.setDirection(GameDirection.East);
			//this.board.setSingleWindDirection(GameDirection.East);
			//this.board.moveAnAmeba(this.actualAmeba);
			//this.amebaOfActualPlayerOnBoard.remove(0);
			
			showDialogue4();
		}
		else if(e.getSource()==this.buttonGoMiddle)
		{
			this.actualAmeba.setDirection(GameDirection.Middle);
			//this.board.setSingleWindDirection(GameDirection.Middle);
			//this.board.moveAnAmeba(this.actualAmeba);
			//this.amebaOfActualPlayerOnBoard.remove(0);
			
			showDialogue4();
		}
		else if(e.getSource()==this.buttonSetFoodToEat)
		{

			//Reset the actual direction on board (set to direction of compass (need if an ameba wriggled):
			//GameDirection directionOfCompass= this.board.getCompassSquare().getWindDirection();
			//this.board.setGlobalWindDirection(directionOfCompass);
			GameColor color=this.actualAmeba.getColor();
			boolean check;
			
			switch(color)
			{
				case blue:
				{
					check=Integer.parseInt(this.textFieldNrOfBlueFoodToEat.getText())==0;
					break;
				}
				case red:
				{
					check=Integer.parseInt(this.textFieldNrOfRedFoodToEat.getText())==0;
	
					break;
				}
				case yellow:
				{
					check=Integer.parseInt(this.textFieldNrOfYellowFoodToEat.getText())==0;
					break;
				}
				default:
				{
					System.out.println("Error in Phase1.class: unkown case in public void actionPerformed(ActionEvent e)");
					check=false;
					//TODO:
					break;
				}
			}
			
			if(!check)
			{
				assert (check);
				JOptionPane.showMessageDialog(null, "ERROR in PHASE 1: A input is not=0!" + color + " red is= "+Integer.parseInt(this.textFieldNrOfRedFoodToEat.getText()) );
				System.exit(1);
			}
			
			int nrOfBlueFood=Integer.parseInt(this.textFieldNrOfBlueFoodToEat.getText());
			int nrOfRedFood=Integer.parseInt(this.textFieldNrOfRedFoodToEat.getText());
			int nrOfYellowFood=Integer.parseInt(this.textFieldNrOfYellowFoodToEat.getText());
			
			this.checkValidNrOfFoods(nrOfBlueFood, nrOfRedFood, nrOfYellowFood);
			
			this.checkIsNextPlayerOnTurn();
			this.nextAmebaInList();
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

	private void showDialogue4() {
		this.setDialoge1(false);
		this.setDialoge2(false);
		this.setDialoge3(false);
		this.setDialoge4(true);
	}

	private void showEatDialogue(GameDirection direction) {
		//System.out.println("Should move in :"+ direction);
		//this.board.setSingleWindDirection(direction);
		this.actualAmeba.setDirection(direction);
		
		showDialogue4();
		
		this.die.setVisible(true);
	}

	private void showFreeMoveDialogue() {
		//System.out.println("FREE MOVE!");
		
		this.setDialoge1(false);
		this.setDialoge2(false);
		this.setDialoge3(true);
		this.setDialoge4(false);
	}

	private void showMoveDialogue() {
		//IMPLEMENTED HERE GENE STROMLINIENFORM! PART 2:
		if(this.actualPlayer.getGenes().contains(GameGene.Stromlienenform))
		{
			this.buttonMove.setEnabled(true);
			System.out.println("You have gene Stromlinienform! You haven't pay bioPoints here!");
		}
		else
		{
			this.actualPlayer.subBioPoints(this.costOfMove);
		}

		
		
		this.setDialoge1(false);
		this.setDialoge2(true);
		this.setDialoge3(false);
		this.setDialoge4(false);
	}

	private void showDriftDialogue() {
		this.actualAmeba.setDirection(this.board.getCompassSquare().getWindDirection());
		
		showDialogue4();
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
	
			public boolean getButtonSetFoodToEatIsEnabled()
			{
				return this.buttonSetFoodToEat.isEnabled();
			}
			public boolean getButtonMoveIsEnabled()
			{
				return this.buttonMove.isEnabled();
			}
			public boolean getButtonDriftIsEnabled()
			{
				return this.buttonDrift.isEnabled();
			}
			public boolean getButtonGoUpIsEnabled()
			{
				return this.buttonGoUp.isEnabled();
			}
			public boolean getButtonGoRightIsEnabled()
			{
				return this.buttonGoRight.isEnabled();
			}
			public boolean getButtonGoLeftIsEnabled()
			{
				return this.buttonGoLeft.isEnabled();
			}
		
			public boolean getButtonGoMiddleIsEnabled()
			{
				return this.buttonGoMiddle.isEnabled();
			}
			public boolean getButtonRollDieIsEnabled()
			{
				return this.buttonRollDie.isEnabled();
			}
			public boolean getButtonGoDownIsEnabled()
			{
				return this.buttonGoDown.isEnabled();
			}
			public boolean getButtonGoBackIsEnabled()
			{
				return this.buttonGoBack.isEnabled();
			}
			public boolean getButtonExitIsEnabled()
			{
				return this.buttonExit.isEnabled();
			}
			public boolean getButtonGoToPhase2IsEnabled()
			{
				return this.buttonGoToPhase2.isEnabled();
			}

			////////////////
			//*TEXTFIELDS*//
			////////////////
	
			public boolean getTextFieldNrOfBlueFoodToEatIsEnabled()
			{
				return this.textFieldNrOfBlueFoodToEat.isEnabled();
			}
		
			public boolean getTextFieldNrOfYellowFoodToEatIsEnabled()
			{
				return this.textFieldNrOfYellowFoodToEat.isEnabled();
			}
		
			public boolean getTextFieldNrOfRedFoodToEatIsEnabled()
			{
				return this.textFieldNrOfRedFoodToEat.isEnabled();
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
			

			public void setTexttextFieldNrOfBlueFoodToEat(String text)
			{
				this.textFieldNrOfBlueFoodToEat.setText(text);
			}

			public void setTexttextFieldNrOfRedFoodToEat(String text)
			{
				this.textFieldNrOfRedFoodToEat.setText(text);
			}

			public void setTexttextFieldNrOfYellowFoodToEat(String text)
			{
				this.textFieldNrOfYellowFoodToEat.setText(text);
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
			public void fakeClickbuttonGoToPhase2()
			{
				this.buttonGoToPhase2.doClick();
			}
			public void fakeClickbuttonMove()
			{
				this.buttonMove.doClick();
			}
			public void fakeClickbuttonSetFoodToEat()
			{
				this.buttonSetFoodToEat.doClick();
			}

			public void fakeClickbuttonDrift()
			{
				this.buttonDrift.doClick();
			}
			public void fakeClickbuttonGoUp()
			{
				this.buttonGoUp.doClick();
			}

			public void fakeClickbuttonGoRight()
			{
				this.buttonGoRight.doClick();
			}


			public void fakeClickbuttonGoLeft()
			{
				this.buttonGoLeft.doClick();
			}


			public void fakeClickbuttonGoDown()
			{
				this.buttonGoDown.doClick();
			}


			public void fakeClickbuttonGoMiddle()
			{
				this.buttonGoMiddle.doClick();
			}


			public void fakeClickbuttonRollDie()
			{
				this.buttonRollDie.doClick();
			}
			

			///////////////////
			//*RADIO BUTTONS*//
			///////////////////
	
	
			////////////////
			//*TEXTFIELDS*//
			////////////////
			
			
			
}
