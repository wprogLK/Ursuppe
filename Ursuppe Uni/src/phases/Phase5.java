package phases;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;


import javax.swing.*;

import enums.*;
import game.*;

//TODO EVERYTHING!!!!

/**
 * 
 */

/**
 * @author lukas
 *
 */
public class Phase5 extends JPanel implements ActionListener
{
	//BASIC THINGS OF THIS GUI:
	private boolean isActive;		//if this PhaseGui is the actual gui, the "showing gui"
	private GamePhases activePhase;	//this is IMPORTANT!
	private Ameba actualAmeba;
	private Ameba deathAmeba;
	private ArrayList<Ameba> amebasToDie=new ArrayList<Ameba>();
	private int nrOfFoodAfterDie=2;
	//COMPONENTS OF THIS GUI:
	
		//PANELS:
			
		//LABELS:
			private JLabel labelGameTitle=new JLabel();
			private JLabel labelPhaseTitle=new JLabel();
			
			private JLabel labelActualPlayer=new JLabel();
			
			private JLabel labelInstruction=new JLabel();
		
			private JLabel labelInfoPlayerBlue=new JLabel();
			private JLabel labelInfoPlayerRed=new JLabel();
			private JLabel labelInfoPlayerYellow=new JLabel();

		//TEXTFIELDS:
			
	
		//BUTTONS:
			private JButton buttonGoBack=new JButton();
			private JButton buttonExit=new JButton();
			private JButton buttonGoToPhase6=new JButton();
		
			
		//RADIO BUTTONS:
	
	
		//BOXES
		
	
		//OTHER STUFF
		
		
		//SPECIAL COMPONENTS:
		private Board board;
		private Die die;
		
	//VARIABLES OF THIS PHASE:
	
	private Player playerBlue;
	private Player playerRed;
	private Player playerYellow;
	
	private Player actualPlayer;
	
	private String gameTitle;
	private String phaseTitle;
	
	private Game game;
	
	private GameReadDirection readDirection;
	
	
	//TIMER
	private int deathIntervall=2000; //Milliseconds between checks (2000ms=2sec)
	private Timer deathTimer;
	
	/**
	 * Constructor
	 */
	public Phase5(Game game) {
		//ARGUMENTES OF THE CONSTRUCTOR:
		this.game=game;
		this.board=game.getBoard();
		
		this.actualAmeba=game.getPlayer(GameColor.blue).getAmebasOffBoard().get(1);
		
		init(game);
			
	
		
			
			//this.updateData();
			
		//CREATE ALL PANELS AND COMPONENTES:
			this.createPanels();
	
		//TIMER:
			this.deathTimer=new Timer(this.deathIntervall,this);
			
			
		
	}

	private void init(Game game) {
		//SET THE PHASE TO THE GAME:
		this.readDirection=GamePhases.phase5.getReadDirection();
		this.game.setReadDirection(readDirection);
		this.phaseTitle="Phase 5: death";
		
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
		
		public int getNrOfFoodAfterDie()
		{
			return this.nrOfFoodAfterDie;
		}
		
		
		
		////////////
		//SETTERS://
		////////////
		
		public void setNrOfFoodAfterDie(int nr)
		{
			this.nrOfFoodAfterDie=nr;
		}
		
	
	
	
	
	
	///////////////////////////////////
	////////ACTIVATE/DEACTIVATE:///////
	///////////////////////////////////
	//set this GUI to the active GUI (call by GUILOGIC)
	public void activate()
	{
		this.deathTimer.start();

		this.activePhase=GamePhases.phaseEmpty;
		this.isActive=true;
		this.setVisible(true);
		doUpdate();
		
		this.add(this.board);	//IMPORTANT!!
		
		this.loadAmebasToDie();
		this.buttonGoToPhase6.setEnabled(false);
		
	
	}
	
	public void deactivate()
	{
		this.deathTimer.stop();
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
		
		
		///////////////////////////
		//UPDATE CLASS VARIABLES://
		///////////////////////////
		
		
		
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
	
			
				updateLabels();
				
				updateButtons();
				
				
			
				
			
			
				this.setAllBounds();
		
		
	}

	private void updateButtons() {
		this.buttonExit.setText("Exit...");
		this.buttonGoBack.setText("Go back to the previous phase");
		this.buttonGoToPhase6.setText("Go to phase6");
		
		
		this.buttonExit.setToolTipText("Click here to end the whole game");
		this.buttonGoBack.setToolTipText("Click here to go back to phase 1");
		this.buttonGoToPhase6.setToolTipText("Click here to to phase 4");
	}

	private void updateLabels() {
		this.labelInstruction.setText("Environment and gene defects");
		
		this.labelGameTitle.setText(this.gameTitle);
		this.labelPhaseTitle.setText("Phase: " + this.phaseTitle);
		
		this.labelInfoPlayerBlue.setText(this.playerBlue.toString());
		this.labelInfoPlayerRed.setText(this.playerRed.toString());
		this.labelInfoPlayerYellow.setText(this.playerYellow.toString());
		
		this.labelActualPlayer.setText("The actual player is: " + this.game.getActualPlayer().getPlayerName() + " [ " + this.game.getActualPlayer().getColor() + " ]");
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
		
		this.createPanel1();	
		
		this.setAllBounds();

	}
	
	
	
	/*
	 * Call this method after every update!! IMPORTANT
	 */
	private void setAllBounds()
	{
		Calc calc=new Calc();
		
		
		calcPanels(calc);
		
	
		calcLabels(calc);
		
		

		calcButtons(calc);
		

		
		calcOthers(calc);
		
	
	}

	private void calcOthers(Calc calc) {
		calc.calcBoard(this.board, 100, 100);
	}

	private void calcButtons(Calc calc) {
		calc.calcButton(this.buttonExit, 100, 1000);
		calc.calcButton(this.buttonGoBack, 450, 1000);
		calc.calcButton(this.buttonGoToPhase6, 200, 1000);
	}

	private void calcLabels(Calc calc) {
		calc.calcLabel(labelGameTitle, 10, 10);
		calc.calcLabel(this.labelPhaseTitle, 1000, 50);
		
		calc.calcLabel(this.labelActualPlayer, 1000, 150);
		
		calc.calcLabel(this.labelInstruction, 1000, 100);
	
		
		
		calc.calcLabel(this.labelInfoPlayerBlue, 10, 25);
		calc.calcLabel(this.labelInfoPlayerRed, 10, 40);
		calc.calcLabel(this.labelInfoPlayerYellow, 10, 55);
	}

	private void calcPanels(Calc calc) {
		calc.calcPanel(this, 0, 0);
	}
	
	private void createPanel1()
	{	
		
	
		addToActionListenerButtons();
		
		
	

		addToThisLabels();
	
	
		
	
		
		addToThisOthers();
		
		
		
		addToThisButtons();
		
		
		

	}

	private void addToThisButtons() {
		this.add(this.buttonGoBack);
		this.add(this.buttonExit);
		this.add(this.buttonGoToPhase6);
	}

	private void addToThisOthers() {
		this.add(this.board);
		this.add(this.die);
	}

	private void addToThisLabels() {
		this.add(this.labelPhaseTitle);
		this.add(this.labelGameTitle);
		
		this.add(this.labelInfoPlayerBlue);
		this.add(this.labelInfoPlayerRed);
		this.add(this.labelInfoPlayerYellow);
		
		this.add(this.labelActualPlayer);
		
		this.add(this.labelInstruction);
	}

	private void addToActionListenerButtons() {
		this.buttonGoBack.addActionListener(this);
		this.buttonExit.addActionListener(this);
		this.buttonGoToPhase6.addActionListener(this);
	}
	
	/////////////////////////////////
	//SPECIAL METHODS OF THIS PHASE://
	/////////////////////////////////
	
		
		
	private void loadAmebasToDie()
	{
		assert this.amebasToDie.isEmpty();
		
		int totalNumberOfAmebas=this.actualPlayer.getNumbersOfAmebasOnBoard()+this.actualPlayer.getNumbersOfAmebasOffBoard();
		
		for(int i=1; i<=totalNumberOfAmebas;i++)
		{
			this.actualAmeba=this.actualPlayer.getAmebaWithNumber(i);
			this.actualAmeba.checkIsDeath();
			
			assert this.actualAmeba.getNumber()==i;
			
			if (this.actualPlayer.getAmebasOnBoard().contains(this.actualAmeba) && this.actualAmeba.isDead())
			{
				this.amebasToDie.add(this.actualAmeba);
			}
			
		}
	}
	
	
	///////////
	//ACTIONS://
	////////////

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==this.buttonGoToPhase6)
		{
			this.activePhase=GamePhases.phase6;
		}
		else if (e.getSource()==this.buttonExit)
		{
			System.exit(0);
		}
		else if (e.getSource()==this.buttonGoBack)
		{
				this.activePhase=GamePhases.phaseSetFirstAmeba; //Go back to phase SetNames
		}
		else if(e.getSource()==this.deathTimer)
		{
			if (this.amebasToDie.isEmpty())
			{
				this.game.nextPlayer();
				this.actualPlayer=this.game.getActualPlayer();
				
				this.loadAmebasToDie();
			}
			
			if (this.amebasToDie.isEmpty())
			{
				this.buttonGoToPhase6.setEnabled(true);
				this.deathTimer.stop();
			}
			else
			{
				
				this.buttonGoToPhase6.setEnabled(false);
				
				//AMEBA DIE
				
				this.deathAmeba=this.amebasToDie.get(0);
				
				this.actualPlayer.addAmebaToOffBoard(this.deathAmeba);
				
				this.amebasToDie.remove(this.deathAmeba);
				Point2D squarePos=this.deathAmeba.getSquarePosition();
				
				ISquare aSquare=this.game.getBoard().getSquare((int) squarePos.getX(), (int) squarePos.getY());
				SoupSquare soupSquare=(SoupSquare) aSquare;
				
				soupSquare.removeAmeba(this.deathAmeba);
				
				soupSquare.createFoodOfColor(GameColor.blue, this.nrOfFoodAfterDie);
				soupSquare.createFoodOfColor(GameColor.red, this.nrOfFoodAfterDie);
				soupSquare.createFoodOfColor(GameColor.yellow, this.nrOfFoodAfterDie);
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
			public Ameba getCurrentAmeba()
			{
				return this.actualAmeba;
			}
			
			public Ameba getDeathAmeba()
			{
				return this.deathAmeba;
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
			public boolean getButtonGoToPhase6IsEnabled()
			{
				return this.buttonGoToPhase6.isEnabled();
			}
	
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
			public void fakeClickbuttonGoToPhase6()
			{
				this.buttonGoToPhase6.doClick();
			}
			public void fakeClickbuttonGoBack()
			{
				this.buttonGoBack.doClick();
			}
		
			

			///////////////////
			//*RADIO BUTTONS*//
			///////////////////
	
	
			////////////////
			//*TEXTFIELDS*//
			////////////////
			
			
}
