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
public class Phase6 extends JPanel implements ActionListener
{
	//BASIC THINGS OF THIS GUI:
	private boolean isActive;		//if this PhaseGui is the actual gui, the "showing gui"
	private GamePhases activePhase;	//this is IMPORTANT!
	
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
			private JButton buttonGoToPhase1=new JButton();
		
			
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
	
	/**
	 * Constructor
	 */
	public Phase6(Game game) {
		//ARGUMENTES OF THE CONSTRUCTOR:
		this.game=game;
		this.board=game.getBoard();
		
		init(game);
	
		
			
			//this.updateData();
			
		//CREATE ALL PANELS AND COMPONENTES:
			this.createPanels();
	
		
			
		
	}

	private void init(Game game) {
		
		//SET THE PHASE TO THE GAME:
		this.readDirection=GamePhases.phase6.getReadDirection();
		this.game.setReadDirection(readDirection);
		this.phaseTitle="Phase 6: score";
		
		//CLASS VARIABLES:
		this.isActive=false;
		this.gameTitle=game.getGameTitle();
		this.actualPlayer=game.getActualPlayer();
		
		getPlayerColor(game);
		
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
	}

	private void getPlayerColor(Game game) {
		this.playerBlue=game.getPlayer(GameColor.blue);
		this.playerRed=game.getPlayer(GameColor.red);
		this.playerYellow=game.getPlayer(GameColor.yellow);
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
		this.buttonGoToPhase1.setEnabled(false);
	
		this.activePhase=GamePhases.phaseEmpty;
		this.isActive=true;
		this.setVisible(true);
		doUpdate();
		
		this.add(this.board);	//IMPORTANT!!
		
		System.out.println("SCORE BEFORE: BLUE "+ this.playerBlue.getScore());
		System.out.println("SCORE BEFORE: RED "+ this.playerRed.getScore());
		System.out.println("SCORE BEFORE: YELLOW "+ this.playerYellow.getScore());
		
		
		calcAllScores();
		
		calcNewScoreLadder();
	
	
		//TODO: check if somebody won
	}

	private void calcNewScoreLadder() {
		this.actualPlayer=this.game.getActualPlayer();
		ArrayList<Player> players=new ArrayList<Player>();
		
		for (int i=0; i<this.game.getNumberOfPlayers();i++)
		{
			players.add(this.game.getOrderPlayers().get(i));
		}
		
		
		int count=0;
		while(count<this.game.getNumberOfPlayers())
		{
			if (!this.actualPlayer.equals(this.game.getEmptyPlayer()))
			{
				
				Player player=null;
				
				for (int i=0; i<players.size();i++)
				{
					player=players.get(i);
					
					if(!player.equals(this.game.getEmptyPlayer()) && !player.equals(this.actualPlayer) && player.getScore()==this.actualPlayer.getScore())
					{
						this.actualPlayer.addScore(1);
					}
				}
			}
			else
			{
				//"skip" empty player and do nothing
			}
			
			
			this.game.nextPlayer();
			this.actualPlayer=this.game.getActualPlayer();
			count++;
		}
		
	
	
		this.doUpdate();
		
		this.buttonGoToPhase1.setEnabled(true);
	}

	private void calcAllScores() {
		this.calcScore(this.playerBlue);
		this.calcScore(this.playerRed);
		this.calcScore(this.playerYellow);
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
		updateLabels();
			
		updateButtons();

		this.setAllBounds();
		
		
	}

	private void updateButtons() {
		this.buttonExit.setText("Exit...");
		this.buttonGoBack.setText("Go back to the previous phase");
		this.buttonGoToPhase1.setText("Go to phase 1");
		
		
		this.buttonExit.setToolTipText("Click here to end the whole game");
		this.buttonGoBack.setToolTipText("Click here to go back to phase 5");
		this.buttonGoToPhase1.setToolTipText("Click here to to phase 1");
	}

	private void updateLabels() {
		this.labelInstruction.setText("Score");
		
		this.labelGameTitle.setText(this.gameTitle);
		this.labelPhaseTitle.setText("Phase: " + this.phaseTitle);
		
		this.labelInfoPlayerBlue.setText(this.playerBlue.toString());
		this.labelInfoPlayerRed.setText(this.playerRed.toString());
		this.labelInfoPlayerYellow.setText(this.playerYellow.toString());
		
		this.labelActualPlayer.setText("The actual player is: " + this.game.getActualPlayer().getPlayerName() + " [ " + this.game.getActualPlayer().getColor() + " ]");
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
		
		
		calcPanels(calc);
		
		
		calcLabels(calc);
		
		
		
		calcButtons(calc);
		
		
		
		
		
		calc.calcBoard(this.board, 100, 100);
		
	
	}

	private void calcButtons(Calc calc) {
		calc.calcButton(this.buttonExit, 100, 1000);
		calc.calcButton(this.buttonGoBack, 450, 1000);
		calc.calcButton(this.buttonGoToPhase1, 200, 1000);
	}

	private void calcPanels(Calc calc) {
		calc.calcPanel(this, 0, 0);
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
		this.add(this.buttonGoToPhase1);
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
		this.buttonGoToPhase1.addActionListener(this);
	}
	
	
	
	
	
	
	


	
	
	
	/////////////////////////////////
	//SPECIAL METHODS OF THIS PHASE://
	/////////////////////////////////
	
		
	public void calcScore(Player player)
	{
		int scoreToAdd=0;
		
		int numberOfAmebasOnBoard=player.getAmebasOnBoard().size();
		int scoreOfGenes=player.getScoreOfGenes();
		
		//Calc numberOfAmebasOnBoard into Score
		scoreToAdd = calcScoreOfAmebas(scoreToAdd, numberOfAmebasOnBoard);
		
		//Calc scoreOfGenes into Score
		scoreToAdd = calcScoreGenes(scoreToAdd, scoreOfGenes);
		
		player.addScore(scoreToAdd);
		
		
		
		
	}

	private int calcScoreGenes(int scoreToAdd, int scoreOfGenes) {
		if(scoreOfGenes>=6)
		{
			scoreToAdd=scoreToAdd+4;
		}
		else if(scoreOfGenes==5)
		{
			scoreToAdd=scoreToAdd+3;
		}
		else if(scoreOfGenes==4)
		{
			scoreToAdd=scoreToAdd+2;
		}
		else if(scoreOfGenes==3)
		{
			scoreToAdd=scoreToAdd+1;
		}
		else
		{
			scoreToAdd=scoreToAdd+0;
		}
		return scoreToAdd;
	}

	private int calcScoreOfAmebas(int scoreToAdd, int numberOfAmebasOnBoard) {
		if(numberOfAmebasOnBoard==7)
		{
			scoreToAdd=scoreToAdd+6;
		}
		else if(numberOfAmebasOnBoard==6)
		{
			scoreToAdd=scoreToAdd+5;
		}
		else if(numberOfAmebasOnBoard==5)
		{
			scoreToAdd=scoreToAdd+4;
		}
		else if(numberOfAmebasOnBoard==4)
		{
			scoreToAdd=scoreToAdd+2;
		}
		else if(numberOfAmebasOnBoard==3)
		{
			scoreToAdd=scoreToAdd+1;
		}
		else
		{
			scoreToAdd=scoreToAdd+0;
		}
		return scoreToAdd;
	}
	
	
	
	
	

	
	
	
	
	
	
	
	
	///////////
	//ACTIONS://
	////////////

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==this.buttonGoToPhase1)
		{
			this.activePhase=GamePhases.phase1;
			
			this.game.addPlayedRound();
		}
		else if (e.getSource()==this.buttonExit)
		{
			System.exit(0);
		}
		else if (e.getSource()==this.buttonGoBack)
		{
				this.activePhase=GamePhases.phaseSetFirstAmeba; //Go back to phase SetNames
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
			public boolean getButtonGoBackIsEnabled()
			{
				return this.buttonGoBack.isEnabled();
			}
			public boolean getButtonGoToPhase1IsEnabled()
			{
				return this.buttonGoToPhase1.isEnabled();
			}
			public boolean getButtonExitIsEnabled()
			{
				return this.buttonExit.isEnabled();
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
			public void fakeClickbuttonExit()
			{
				this.buttonExit.doClick();
			}

			public void fakeClickbuttonGoBack()
			{
				this.buttonGoBack.doClick();
			}

			public void fakeClickbuttonGoToPhase1()
			{
				this.buttonGoToPhase1.doClick();
			}
		
			

			///////////////////
			//*RADIO BUTTONS*//
			///////////////////
	
	
			////////////////
			//*TEXTFIELDS*//
			////////////////
			
			
}
