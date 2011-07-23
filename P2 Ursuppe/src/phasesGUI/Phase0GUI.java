package phasesGUI;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;


import javax.swing.*;

import phases.Phase0;
import phases.PhaseSetNames;

import Components.Board;
import Components.Die;
import Components.LadderSquare;
import Components.Player;

import enums.*;
import game.*;



/**
 * 
 */

/**
 * @author lukas
 *
 */
public class Phase0GUI extends JPanel implements ActionListener 
{
	//BASIC THINGS OF THIS GUI:
	private boolean isActive;		//if this PhaseGui is the actual gui, the "showing gui"
	private GamePhases activePhase;	//this is IMPORTANT!
	
	private Phase0 phase;
	
	private GamePart activePart;
	//COMPONENTS OF THIS GUI:
	
		//PANELS:
			
			//For part 1:
			private JPanel panelPhase0Part1 =new JPanel(new GridLayout(5,2)); 		//Need every GUI! (its the main panel) //IMPORTANT: it contains only other panels!!!
			private JPanel panelButtonsPart1=new JPanel(new GridLayout(1,2));
			private JPanel panelDie=new JPanel(new FlowLayout());
			private JPanel panelLabelPlayerNamePart1=new JPanel(new GridLayout(3,1));
		
			//For part 2:
			private JPanel panelPhase0Part2=new JPanel(new GridLayout(5,2)); 		//Need every GUI! (its the main panel) //IMPORTANT: it contains only other panels!!!
			private JPanel panelButtonsPart2=new JPanel(new GridLayout(1,3)); 	
			private JPanel panelRadioButtons=new JPanel(new GridLayout(1,3));
			private JPanel panelLabelPlayerNamePart2=new JPanel(new GridLayout(3,1));
			
		
			
		
		//LABELS:
			//For part 1:
			private JLabel labelGameTitlePart1=new JLabel();
			private JLabel labelPhaseTitlePart1=new JLabel();
			
			private JLabel labelActualPlayerPart1=new JLabel();
			
			private JLabel labelInstructionPart1=new JLabel();
			
			private JLabel labelInfoPlayerBluePart1=new JLabel();
			private JLabel labelInfoPlayerRedPart1=new JLabel();
			private JLabel labelInfoPlayerYellowPart1=new JLabel();
		
			//For part 2:
			private JLabel labelGameTitlePart2=new JLabel();
			private JLabel labelPhaseTitlePart2=new JLabel();
			
			private JLabel labelActualPlayerPart2=new JLabel();
			
			private JLabel labelInstructionPart2=new JLabel();
			
			private JLabel labelInfoPlayerBluePart2=new JLabel();
			private JLabel labelInfoPlayerRedPart2=new JLabel();
			private JLabel labelInfoPlayerYellowPart2=new JLabel();
	
		//TEXTFIELDS:
		
	
		//BUTTONS:
			//For part 1:
			private JButton buttonGoBackPart1=new JButton();
			private JButton buttonRollDiePart1=new JButton();
			private JButton buttonExitPart1=new JButton();
			private JButton buttonLetsSetPlayers=new JButton();
			
			//For part2:
			private JButton buttonLetsPlayPart2=new JButton();
			private JButton buttonExitPart2=new JButton();
			private JButton buttonGoBackPart2=new JButton();
			
	
		//RADIO BUTTONS:
		private JRadioButton radioButtonStartPos1=new JRadioButton();
		private JRadioButton radioButtonStartPos2=new JRadioButton();
		private JRadioButton radioButtonStartPos3=new JRadioButton();
		
		private ButtonGroup buttonGroup=new ButtonGroup();
	
		//BOXES
		
	
		//OTHER STUFF
		
		//SPECIAL COMPONENTS:
		private Die die;
		
	//VARIABLES OF THIS PHASE:
	private String namePlayerBlue;
	private String namePlayerRed;
	private String namePlayerYellow;
	
	private int faceValueBlue;
	private int faceValueRed;
	private int faceValueYellow;
	
	private int posBlue;
	private int posRed;
	private int posYellow;
	
	private Player playerBlue;
	private Player playerRed;
	private Player playerYellow;
	
	private Player actualPlayer;
	
	private ArrayList<Player> tmpOrderOfPlayers;
	private ArrayList<Player> newOrderOfPlayers;
	
	private String gameTitle;
	private String phaseTitle;
	
	private Game game;
	
	private GameReadDirection readDirection;
	
	/**
	 * Constructor
	 */
	public Phase0GUI(Game game, Phase0 phase) {
		//ARGUMENTES OF THE CONSTRUCTOR:
		this.phase=phase;
		this.game=game;
		
		init(game);
			
		//CREATE ALL PANELS AND COMPONENTES:
			this.createPanels();
		
			//this.setPanelPhase0Part1(); //Activate panel part1	//This will be done by the PhaseLogic
			
		//Update/Initialize all the data for the components	//IMPORTANT!!!
			this.doUpdate();
			
	}

	private void init(Game game) {
		//SET THE PHASE TO THE GAME:
		this.readDirection=GamePhases.phase0.getReadDirection();
		this.game.setReadDirection(readDirection);
		this.phaseTitle="Phase 0";
		
		//CLASS VARIABLES:
		this.isActive=false;
		this.gameTitle=game.getGameTitle();
		this.actualPlayer=game.getActualPlayer();
		
		this.playerBlue=game.getPlayer(GameColor.blue);
		this.playerRed=game.getPlayer(GameColor.red);
		this.playerYellow=game.getPlayer(GameColor.yellow);
		
		this.namePlayerBlue=this.playerBlue.getPlayerName();
		this.namePlayerRed=this.playerRed.getPlayerName();
		this.namePlayerYellow=this.playerYellow.getPlayerName();
		
		this.faceValueBlue=0;
		this.faceValueRed=0;
		this.faceValueYellow=0;
		
		this.posBlue=0;
		this.posRed=0;
		this.posYellow=0;
		
		
		//IMPORTANT STUFF:
		this.activePhase=GamePhases.phaseEmpty;		
		
		//CONFIGURE THIS PANEL:
			//here you can set the Layout and other elementary things
			this.setLayout(new FlowLayout());
			this.setVisible(true);
	}
	
	//////////////////////
	//GETTERS & SETTERS://
	//////////////////////
	
		////////////
		//GETTERS://
		////////////
		//to communicate with the GUILOGIC:
		public String getNamePlayerBlue()
		{
			return this.namePlayerBlue;
		}
		
		//to communicate with the GUILOGIC:
		public String getNamePlayerRed()
		{
			return this.namePlayerRed;
		}
		
		//to communicate with the GUILOGIC:
		public String getNamePlayerYellow()
		{
			return this.namePlayerYellow;
		}
		
		//to communicate with the GUILOGIC:
		public GamePhases getActivePhase()
		{
			return this.activePhase;
		}
		
		////////////
		//SETTERS://
		////////////
		private void setActivePart(GamePart activePart)
		{
			this.activePart=activePart;
		}
	
	
	
	
	
	
	///////////////////////////////////
	////////ACTIVATE/DEACTIVATE:///////
	///////////////////////////////////
	//set this GUI to the active GUI (call by GUILOGIC)
	public void activate()
	{
		this.activePhase=GamePhases.phaseEmpty;
		this.isActive=true;
		this.setVisible(true);
		doUpdate();;
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
		this.actualPlayer=this.game.getActualPlayer();
	}
	
	/**
	 * UPDATE the components and class variables of this phase //IMPORTANT FOR EVERY PHASE
	 */
	public void updateComponents()
	{
	

		///////////////////////////
		//UPDATE CLASS VARIABLES://
		///////////////////////////
		
		//TODO:

		//////////////////////
		//UPDATE COMPONENTS://
		//////////////////////
		
				///////////
				//PART 1://
				///////////
		
				//PANELS:
				
				updateLabels();
				
				//TEXTFIELDS:
				
				//BUTTONS:
				updateButtons();
				
				//RADIOBUTTONS:
				
				//BOXES:
				
				//OTHER STUFF:
				
				//SPECIAL STUFF:
				
				///////////
				//PART 2://
				///////////
				
				//PANELS:
				
				//LABELS:
				
				
				//TEXTFIELDS:
				
				//BUTTONS:
				
				
				//RADIOBUTTONS:
				updateRadioButtons();
				
				//BOXES:
				
				//OTHER STUFF:
				
				//SPECIAL STUFF:
				
		
	
		
		
	}

	private void updateRadioButtons() {
		this.radioButtonStartPos1.setText("Position 1");
		this.radioButtonStartPos2.setText("Position 2");
		this.radioButtonStartPos3.setText("Position 3");
		
		this.radioButtonStartPos1.setToolTipText("Chose this to set your stone on ladderSquare number 1");
		this.radioButtonStartPos2.setToolTipText("Chose this to set your stone on ladderSquare number 2");
		this.radioButtonStartPos3.setToolTipText("Chose this to set your stone on ladderSquare number 3");
	}

	private void updateButtons() {
		
		//PART 1
		this.buttonExitPart1.setText("Exit...");
		this.buttonGoBackPart1.setText("Go back to the previous phase");
		this.buttonRollDiePart1.setText("Roll the die!");
		this.buttonLetsSetPlayers.setText("Lets set the players!");
		
		this.buttonExitPart1.setToolTipText("Click here to end the whole game");
		this.buttonGoBackPart1.setToolTipText("Click here to go back to phase setNames");
		this.buttonRollDiePart1.setToolTipText("Click here to roll the die");
		this.buttonLetsSetPlayers.setToolTipText("Click here to start part 2 of this phase");
		
		//PART 2:
		this.buttonExitPart2.setText("Exit...");
		this.buttonGoBackPart2.setText("Go back to part1 of this phase");
		this.buttonLetsPlayPart2.setText("Lets play!");
		
		this.buttonExitPart2.setToolTipText("Click here to end the whole game");
		this.buttonGoBackPart2.setToolTipText("Click here to go back to part1 of this phase");
		this.buttonLetsPlayPart2.setToolTipText("Click here to start the game and go to phase 1");
	}

	private void updateLabels() {
		//LABELS:
		//PART 1:
		this.labelInfoPlayerBluePart1.setText("Player " + this.playerBlue.getPlayerName() + " [ " +this.playerBlue.getColor()+" ] has rolled " +this.playerBlue.getRolledFaceValue());
		this.labelInfoPlayerRedPart1.setText("Player " + this.playerRed.getPlayerName() + " [ " +this.playerRed.getColor()+" ] has rolled " +this.playerRed.getRolledFaceValue());
		this.labelInfoPlayerYellowPart1.setText("Player " + this.playerYellow.getPlayerName() + " [ " +this.playerYellow.getColor()+" ] has rolled " +this.playerYellow.getRolledFaceValue());
		
		this.labelInstructionPart1.setText("Set the order of setting the stone on the ladder:  \n The actual player roll the die (click on the button 'roll die').");
		
		this.labelGameTitlePart1.setText(this.gameTitle);
		this.labelPhaseTitlePart1.setText("Phase: " + this.phaseTitle);
		
		this.labelActualPlayerPart1.setText("The actual player is: " + this.game.getActualPlayer().getPlayerName() + " [ " + this.game.getActualPlayer().getColor() + " ]");
		
		//PART 2:
		this.labelInfoPlayerBluePart2.setText("Player " + this.playerBlue.getPlayerName() + " [ " +this.playerBlue.getColor()+" ] has position " +this.playerBlue.getScore());
		this.labelInfoPlayerRedPart2.setText("Player " + this.playerRed.getPlayerName() + " [ " +this.playerRed.getColor()+" ] has position " +this.playerRed.getScore());
		this.labelInfoPlayerYellowPart2.setText("Player " + this.playerYellow.getPlayerName() + " [ " +this.playerYellow.getColor()+" ] has position " +this.playerYellow.getScore());
		
		this.labelInstructionPart2.setText("Set your stone on the ladder:  \n The actual player can chose now a startposition.");
		
		this.labelGameTitlePart2.setText(this.gameTitle);
		this.labelPhaseTitlePart2.setText("Phase: " + this.phaseTitle);
		
		this.labelActualPlayerPart2.setText("The actual player is: " + this.actualPlayer.getPlayerName() + " [ " + this.actualPlayer.getColor() + " ]");
	}
	
	/////////////////////////////
	////////CREATE PANELS:///////
	/////////////////////////////
	
	/**
	 * call createPanelGUI1(), createPanelButtons(), createPanelLabels()
	 * this method will be called by the constructor
	 */
	private void createPanels()
	{
		this.createPanelButtonsPart1();
		this.createPanelButtonsPart2();
		this.createPanelDie();
		this.createPanelLabelPlayerNamePart1();
		this.createPanelLabelPlayerNamePart2();
		this.createPanelRadioButtons();
		
		//IMPORTANT: createPanelPhase0() have to be called at the End of this method!!
		this.createPanelPhase0Part2();	//IMPORTANT MAYBE DELETE THIS
		this.createPanelPhase0Part1();	//IMPORTANT MAYBE DELETE THIS
	
		
		

	}
	
	private void createPanelPhase0Part1()
	{	
		
		
		/////////////////////////////////////
		//ADD PANELS TO THE PANELGUI1 PART://
		/////////////////////////////////////
		
		//ROW 1:
		this.panelPhase0Part1.add(this.labelGameTitlePart1);
		this.panelPhase0Part1.add(this.labelPhaseTitlePart1);
		
		//ROW 2:
		this.panelPhase0Part1.add(new JLabel(""));  			// for empty cell
		this.panelPhase0Part1.add(new JLabel(""));  			// for empty cell
		
		//ROW 3:
		this.panelPhase0Part1.add(this.panelLabelPlayerNamePart1);
		this.panelPhase0Part1.add(this.labelInstructionPart1);
	
		
		//ROW 4:
		this.panelPhase0Part1.add(this.labelActualPlayerPart1);
		this.panelPhase0Part1.add(this.panelDie);
		
		//ROW 5:
		this.panelPhase0Part1.add(new JLabel(""));  			// for empty cell
		this.panelPhase0Part1.add(this.panelButtonsPart1);
		
		
	}
	
	private void createPanelPhase0Part2()
	{	
		
		
		//ROW 1:
		this.panelPhase0Part2.add(this.labelGameTitlePart2);
		this.panelPhase0Part2.add(this.labelPhaseTitlePart2);
		
		//ROW 2:
		this.panelPhase0Part2.add(new JLabel(""));  			// for empty cell
		this.panelPhase0Part2.add(new JLabel(""));  			// for empty cell
		
		//ROW 3:
		this.panelPhase0Part2.add(this.panelLabelPlayerNamePart2);
		this.panelPhase0Part2.add(this.labelInstructionPart2);
		
		
		//ROW 4:
		this.panelPhase0Part2.add(this.labelActualPlayerPart2);
		this.panelPhase0Part2.add(this.panelRadioButtons);
		
		//ROW 5:
		this.panelPhase0Part2.add(new JLabel(""));  			// for empty cell
		this.panelPhase0Part2.add(this.panelButtonsPart2);
		
		
	}
	
	/**
	 * to switch to panelPhase0Part1 
	 */
	public void setPanelPhase0Part1()
	{	
		this.setActivePart(GamePart.part1);
		
		this.radioButtonStartPos1.setEnabled(false);
		this.radioButtonStartPos2.setEnabled(false);
		this.radioButtonStartPos3.setEnabled(false);
		
		this.panelPhase0Part2.setVisible(false);
		this.panelPhase0Part1.setVisible(true);
		
		this.removeAll(); //TODO: is this necessary?
		
		
		
		////////////////////////////////////////////////
		//ADD THE panelPhase0Part1 TO THIS PANEL PART://
		////////////////////////////////////////////////
		this.add(this.panelPhase0Part1);
		
	}
	
	/**
	 * to switch to panelPhase0Part2 
	 * called by after the die was rolled 3times //TODO
	 * @param panelPhase0
	 */
	public void setPanelPhase0Part2()
	{
		this.setActivePart(GamePart.part2);
		
		this.radioButtonStartPos1.setEnabled(true);
		this.radioButtonStartPos2.setEnabled(true);
		this.radioButtonStartPos3.setEnabled(true);
		
		this.panelPhase0Part1.setVisible(false);
		this.panelPhase0Part2.setVisible(true);
		
		this.removeAll(); //TODO: is this necessary?
		
		////////////////////////////////////////////////
		//ADD THE panelPhase0Part2 TO THIS PANEL PART://
		////////////////////////////////////////////////
		this.add(this.panelPhase0Part2);
	}
	
	private void createPanelButtonsPart1()
	{
		
		
		//...add buttons to listener
		this.buttonGoBackPart1.addActionListener(this);
		this.buttonExitPart1.addActionListener(this);
		
	
		
		///////////////////////
		//ADD TO PANELS PART://
		///////////////////////
		
	
		
		//ROW 1:
		this.panelButtonsPart1.add(this.buttonGoBackPart1);
		this.panelButtonsPart1.add(this.buttonExitPart1);
		
		//...add radioButtons to panels
		
		
		//...add boxes to panels
		
		
		//...add other Stuff to panels
		
		
	}
	
	private void createPanelButtonsPart2()
	{
		
		
		this.buttonLetsPlayPart2.setEnabled(false); //This button shoudn't be active now
		
		
		
		
		/////////////////////////
		//ADD TO LISTENER PART://
		/////////////////////////
		
	
		
		//...add buttons to listener
		this.buttonExitPart2.addActionListener(this);		
		this.buttonGoBackPart2.addActionListener(this);
		this.buttonLetsPlayPart2.addActionListener(this);
		
	
		
		
		///////////////////////
		//ADD TO PANELS PART://
		///////////////////////
		
		//...add buttons to panels
		//ROW 1:
		this.panelButtonsPart2.add(this.buttonGoBackPart2); 
		this.panelButtonsPart2.add(this.buttonExitPart2);	
		this.panelButtonsPart2.add(this.buttonLetsPlayPart2);
		
	
		
		
	}
	
	private void createPanelLabelPlayerNamePart1()
	{
		
		
		///////////////////////
		//ADD TO PANELS PART://
		///////////////////////
		
	
		//...add buttons to panels
			//ROW 1:
			this.panelLabelPlayerNamePart1.add(this.labelInfoPlayerBluePart1);
			//ROW 2:
			this.panelLabelPlayerNamePart1.add(this.labelInfoPlayerRedPart1);
			//ROW 3:
			this.panelLabelPlayerNamePart1.add(this.labelInfoPlayerYellowPart1);
		
		
		
	}
	
	private void createPanelLabelPlayerNamePart2()
	{
	
		
		
		///////////////////////
		//ADD TO PANELS PART://
		///////////////////////
		
		
		
		
		//...add buttons to panels
			//ROW 1:
			this.panelLabelPlayerNamePart2.add(this.labelInfoPlayerBluePart2);
			//ROW 2:
			this.panelLabelPlayerNamePart2.add(this.labelInfoPlayerRedPart2);
			//ROW 3:
			this.panelLabelPlayerNamePart2.add(this.labelInfoPlayerYellowPart2);
		
		
		
		
	}
	
	private void createPanelRadioButtons()
	{
		
		
		//...Create radioButtons
		
		this.radioButtonStartPos1.setActionCommand("1");
		
		
		this.radioButtonStartPos2.setActionCommand("2");
		

		this.radioButtonStartPos3.setActionCommand("3");
		
		
		
			//Add to buttonGroup
		this.buttonGroup.add(this.radioButtonStartPos1);
		this.buttonGroup.add(this.radioButtonStartPos2);
		this.buttonGroup.add(this.radioButtonStartPos3);
		

		
		
		/////////////////////////
		//ADD TO LISTENER PART://
		/////////////////////////
	

		
		//...add radioButtons to listener
		this.radioButtonStartPos1.addActionListener(this);
		this.radioButtonStartPos2.addActionListener(this);
		this.radioButtonStartPos3.addActionListener(this);
		
	
		
		///////////////////////
		//ADD TO PANELS PART://
		///////////////////////
		
	
		
		
		//...add radioButtons to panels
		//ROW 1:
		this.panelRadioButtons.add(this.radioButtonStartPos1);
		this.panelRadioButtons.add(this.radioButtonStartPos2);
		this.panelRadioButtons.add(this.radioButtonStartPos3);
	
	}
	
	private void createPanelDie()
	{
		
		
		//...Create other Stuff
		this.die=game.getDie();
		
		/////////////////////////
		//ADD TO LISTENER PART://
		/////////////////////////
	
		
		
		//...add buttons to listener
		this.buttonRollDiePart1.addActionListener(this);
		this.buttonLetsSetPlayers.addActionListener(this);
		

		///////////////////////
		//ADD TO PANELS PART://
		///////////////////////
		
	
		
		
		//...add buttons to panels
		this.panelDie.add(this.buttonRollDiePart1);
	
	
		
		
		//...add other Stuff to panels
		this.panelDie.add(this.die);
		
	}
	
	/////////////////////////////////
	//SPECIAL METHODS OF THIS PHASE://
	/////////////////////////////////
	private void isNextPlayerEmptyForRadioButtons()
	{
		this.actualPlayer=this.game.getActualPlayer();
		
		if (this.actualPlayer==this.game.getEmptyPlayer())
		{
			this.buttonLetsPlayPart2.setEnabled(true);
			this.createOrderToPlay();
			
			this.game.skipEmptyPlayer();
			
		}
	}
	
	
	private void setDiePanelNew()
	{
		this.panelDie.removeAll();
		this.panelDie.add(this.buttonLetsSetPlayers);
	}
	
	public void createOrderToPlay()
	{		
		ArrayList<Player> orderToPlay=new ArrayList<Player>();
		
		orderToPlay.add(this.game.getEmptyPlayer()); //HEAD
		
		for (int i=1; i<=this.game.getNumberOfPlayers();i++) //FIXED int i=0
		{
			LadderSquare ladderSquare=this.game.getBoard().getLadderSquare(i);
			Player playerOnSquare=ladderSquare.getPlayer();
			//System.out.println("LADDERSQUARE " + i + " : " + ladderSquare.getPlayer());
			orderToPlay.add(playerOnSquare);
		}
		
		orderToPlay.add(this.game.getEmptyPlayer()); //TAIL
		
		this.game.setOrderPlayers(orderToPlay);		//IMPORTANT DON'T FORGET THIS!!!!!!!
		
		
		//System.out.println("Order to play:" + orderToPlay);
		//System.out.println("Order to play size:" + orderToPlay.size());
	}
	public void createOrderToSetPlayers()
	{
		//System.out.println("CREATE ORDER TO SET PLAYERS() IN PHASE0:");
		int numberOfPlayers=this.game.getNumberOfPlayers();
		
		long[] faceValueArray=new long[numberOfPlayers];
		
		//System.out.println("actual order:" + this.game.getOrderPlayers());
		
		
		//Create the array to sort:
		for(int i=0; i<numberOfPlayers;i++)
		{
			this.actualPlayer=this.game.getActualPlayer();
			faceValueArray[i]=(long) actualPlayer.getRolledFaceValue()*-1;		//*-1 because otherwise the order would be the wrong way after sorting
			
			this.game.nextPlayer();
			this.actualPlayer=this.game.getActualPlayer();
		}
			

		
		//sort the array
		Arrays.sort(faceValueArray);
		
	
		
		//create the correct ArrayList:
		
		ArrayList<Player> orderToSetPlayers=new ArrayList<Player>();
		
		orderToSetPlayers.add(this.game.getEmptyPlayer());		//HEAD
		
		//FOR DEBUGGING:
		//System.out.println("Player blue: faceValue: " + this.playerBlue.getRolledFaceValue());
		//System.out.println("Player red: faceValue: " + this.playerRed.getRolledFaceValue());
		//System.out.println("Player Yellow: faceValue: " + this.playerRed.getRolledFaceValue());
		
		for (int i=0; i<faceValueArray.length; i++)
		{	
			int faceValue= (int) faceValueArray[i]*-1;		//*-1 to get the original value
			
			if(this.playerBlue.getRolledFaceValue()==faceValue)
			{
				//System.out.println("Add player Blue");
				orderToSetPlayers.add(this.playerBlue);
			}
			else if(this.playerRed.getRolledFaceValue()==faceValue)
			{
				//System.out.println("Add player Red");
				orderToSetPlayers.add(this.playerRed);
			}
			else if(this.playerYellow.getRolledFaceValue()==faceValue)
			{
				//System.out.println("Add player Yellow");
				orderToSetPlayers.add(this.playerYellow);
			}
			else
			{
				//System.out.println("faceValue is: " + faceValue); //For debugging
				
				System.out.println("Error (Phase0.class): exception in createOrderToSetPlayers()!");
				//TODO: Exception!
			}
		}
		
		orderToSetPlayers.add(this.game.getEmptyPlayer());		//TAIL
		
		//System.out.println("The order to set players is:  " + orderToSetPlayers );
		

		this.game.setOrderPlayers(orderToSetPlayers); //Overwrite
	
	
		this.game.skipEmptyPlayer();
		
		//System.out.println("The ACTUAL PLAYER WHO STARTS IS: " + this.game.getActualPlayer());
		
		
		
		/*OLD
		Player momentanPlayer=this.game.getActualPlayer();
		this.game.getOrderPlayers().remove(momentanPlayer); //remove actualPlayer
		
		Player nextPlayer=this.game.getActualPlayer();
		
		while (nextPlayer!=this.game.getEmptyPlayer())
		{
			momentanPlayer=this.game.getActualPlayer();
			this.game.getOrderPlayers().remove(momentanPlayer); //remove actualPlayer
			
			nextPlayer=this.game.getActualPlayer();

			while (nextPlayer!=this.game.getEmptyPlayer())
			{
				//TODO
			}
			
		}*/
	}
	
	private boolean hasNextPlayer()
	{
		return game.getEmptyPlayer()!=game.getActualPlayer();
	}
	
	/**
	 * Checks if more than one player have the same value, set the new order of players who had the same value
	 * @return: if more than one player have the same faceValue, it will return true, else false
	 */
	private boolean hasMorePlayerSameValue()
	{
		this.tmpOrderOfPlayers=this.game.getOrderPlayers();	//Create a backup of the full order of players
		//System.out.println("The  BACKUP (START) ArrayList is: " + tmpOrderOfPlayers);
		
		boolean hasMorePlayerSameValue=false;
		ArrayList<Player> playersSameValue=new ArrayList<Player>();
		
		playersSameValue.add(this.game.getEmptyPlayer());		//insert the head
		
		int faceValueBlue=this.playerBlue.getRolledFaceValue();
		int faceValueRed=this.playerRed.getRolledFaceValue();
		int faceValueYellow=this.playerYellow.getRolledFaceValue();
		
		if (faceValueBlue==faceValueRed)
		{
			hasMorePlayerSameValue=true;
			
			//System.out.println("BLUE==RED");
			
			if (!playersSameValue.contains(playerBlue))
			{
				playersSameValue.add(playerBlue);
			}
			
			if (!playersSameValue.contains(playerRed))
			{
				playersSameValue.add(playerRed);
			}
		}
		

		if (faceValueBlue==faceValueYellow)
		{
			//System.out.println("BLUE==YELLOW");
			
			hasMorePlayerSameValue=true;
			
			if (!playersSameValue.contains(playerBlue))
			{
				playersSameValue.add(playerBlue);
			}
			
			if (!playersSameValue.contains(playerYellow))
			{
				playersSameValue.add(playerYellow);
			}
		}
		
		
		if (faceValueRed==faceValueYellow)
		{
			//System.out.println("YELLOW==RED");
			hasMorePlayerSameValue=true;
			

			if (!playersSameValue.contains(playerRed))
			{
				playersSameValue.add(playerRed);
			}
			
			if (!playersSameValue.contains(playerYellow))
			{
				playersSameValue.add(playerYellow);
			}
		}
				
			
		
		/* OLD:
		for (int i=0; i<this.game.getOrderPlayers().size()-2;i++)		//-2 because the first and the last element in the arrayList are emptyPlayers
		{
			Player playerToCompare=game.getOrderPlayers().get(i);
			
			for (int j=0; j<this.game.getOrderPlayers().size()-2;j++)		//-2 because the first and the last element in the arrayList are emptyPlayers
			{
				Player player=game.getOrderPlayers().get(j);
				
				if (playerToCompare.getRolledFaceValue()==player.getRolledFaceValue() && !playerToCompare.equals(player))
				{
					System.out.println("Found players with same value ( " + player.getRolledFaceValue() + " )");
					
					hasMorePlayerSameValue=true;
					
					if (!playersSameValue.contains(playerToCompare))
					{
						playersSameValue.add(playerToCompare);
						System.out.println("Add player " + playerToCompare.getPlayerName());
					}
					else
					{
						System.out.println("The player " + playerToCompare.getPlayerName() + " is already in the ArrayList! No add needed");
					}
				}
			}
		}*/
		
		playersSameValue.add(this.game.getEmptyPlayer());		//insert the tail
		
		this.game.setOrderPlayers(playersSameValue); //Overwrite 
		
	//	System.out.println("The  ArrayList is: " + playersSameValue);

	
		
		return hasMorePlayerSameValue;
	}
	
	
	////////////
	//ACTIONS://
	////////////

	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO: AB HIER FREITAG WEITER MACHEN
		
		if(e.getSource()==this.buttonLetsPlayPart2)
		{
			//System.out.println("GO PHASE 1");
			this.activePhase=GamePhases.phaseSetFirstAmeba;
		}
		else if (e.getSource()==this.buttonExitPart1)
		{
			//System.out.println("ESCAPE THE GAME! (Part2)"); //TODO
			System.exit(0);
		}
		else if (e.getSource()==this.buttonExitPart1)
		{
			//System.out.println("ESCAPE THE GAME! (Part1)"); //TODO
			System.exit(0);
		}
		else if (e.getSource()==this.buttonRollDiePart1)
		{
			
			int faceValue=this.die.roll();
			//System.out.println("DIE ROLLED: "+ faceValue);
			
			Player actualPlayer=this.game.getActualPlayer();
			
			actualPlayer.setRolledFaceValue(faceValue);
			
			this.game.nextPlayer();
			
			if(!this.hasNextPlayer())		//If there is no next player
			{
				this.game.nextPlayer(); //skip the empty player and  begin so a new turn
				
				
				boolean hasMorePlayerSameValue=this.hasMorePlayerSameValue();
				
				//System.out.println("hasMorePlayerSameValue: " + hasMorePlayerSameValue);
				
				if (!hasMorePlayerSameValue)
				{
					//Overwrite the tmpOrderOfPlayers: (NEW)
					this.tmpOrderOfPlayers.clear();
					
					this.tmpOrderOfPlayers.add(this.game.getEmptyPlayer()); //HEAD
					this.tmpOrderOfPlayers.add(this.playerBlue);
					this.tmpOrderOfPlayers.add(this.playerRed);
					this.tmpOrderOfPlayers.add(this.playerYellow);
					this.tmpOrderOfPlayers.add(this.game.getEmptyPlayer()); //TAIL
					
					
					//System.out.println("The orderOfPlayer DEF: is : " + this.tmpOrderOfPlayers);
					this.game.setOrderPlayers(this.tmpOrderOfPlayers);	//Overwrite the old, uncompleted list with the original list
				
					
					this.setDiePanelNew();
					
					
					
				}
				else
				{
					//System.out.println("More than one player have the same value! The players with the same value are:" + this.game.getOrderPlayers());
				}
			
			}
			
			
		}
		else if(e.getSource()==this.buttonLetsSetPlayers)
		{
			//System.out.println("////////////// do REAL click on button lets set players////////////////");
			this.newOrderOfPlayers=new ArrayList<Player>();
			this.createOrderToSetPlayers();
			
			
			this.setPanelPhase0Part2();
		}
		
		else if (e.getSource()==this.buttonGoBackPart1)
		{
				this.activePhase=GamePhases.phaseSetNames; //Go back to phase SetNames
				//System.out.println("GO TO PHASE SETNAMES");
		}
		else if (e.getSource()==this.buttonGoBackPart2)
		{
				this.setActivePart(GamePart.part1); //Go back to part1 of this phase
				this.setPanelPhase0Part1();
				
				//TODO: RESET THINGS! (show rollDieButton, setRolledFaceValue=0 of each player) (MAYBE)
		}
		else if(e.getSource()==this.radioButtonStartPos1)
		{
			//System.out.println("-----------------CLICKED RADION BUTTON 1-----------------");
			this.actualPlayer=this.game.getActualPlayer();
			
			this.actualPlayer.setScore(1);
			
			
			Board board=this.game.getBoard();
			LadderSquare ladderSquare=board.getLadderSquare(1);
			ladderSquare.setPlayer(actualPlayer);
			
			
			this.radioButtonStartPos1.setEnabled(false);
			
			this.game.nextPlayer();
			
			this.isNextPlayerEmptyForRadioButtons();
		}
		else if(e.getSource()==this.radioButtonStartPos2)
		{
			//System.out.println("-----------------CLICKED RADION BUTTON 2-----------------");
			this.actualPlayer=this.game.getActualPlayer();
			
			this.actualPlayer.setScore(2);
			
			Board board=this.game.getBoard();
			LadderSquare ladderSquare=board.getLadderSquare(2);
			ladderSquare.setPlayer(actualPlayer);
			
			this.radioButtonStartPos2.setEnabled(false);
			
			this.game.nextPlayer();
			
			this.isNextPlayerEmptyForRadioButtons();
		}
		else if(e.getSource()==this.radioButtonStartPos3)
		{
			//System.out.println("-----------------CLICKED RADION BUTTON 3-----------------");
			this.actualPlayer=this.game.getActualPlayer();
			
			this.actualPlayer.setScore(3);
			
			Board board=this.game.getBoard();
			LadderSquare ladderSquare=board.getLadderSquare(3);
			ladderSquare.setPlayer(actualPlayer);
			
			this.radioButtonStartPos3.setEnabled(false);
			
			this.game.nextPlayer();
			
			this.isNextPlayerEmptyForRadioButtons();
		}
		
		else
		{
			System.out.println("A action wasn't handelt!");
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
		
			public boolean buttonExitPart1IsEnabled()
			{
				return this.buttonExitPart1.isEnabled();
			}
			
			public boolean buttonExitPart2IsEnabled()
			{
				return this.buttonExitPart2.isEnabled();
			}
			
			public boolean buttonRollDiePart1IsEnabled()
			{
				return this.buttonRollDiePart1.isEnabled();
			}
			
			public boolean buttonLetsSetPlayersIsEnabled()
			{
				return this.buttonLetsSetPlayers.isEnabled();
			}
			
			public boolean buttonLetsPlayPart2IsEnabled()
			{
				return this.buttonLetsPlayPart2.isEnabled();
			}
			
			public boolean buttonGoBackPart2IsEnabled()
			{
				return this.buttonGoBackPart2.isEnabled();
			}
			
	
			////////////////
			//*TEXTFIELDS*//
			////////////////
	
			///////////////////
			//*RADIO BUTTONS*//
			///////////////////
			
			public boolean radioButtonStartPos1IsEnabled()
			{
				return this.radioButtonStartPos1.isEnabled();
			}
			
			public boolean radioButtonStartPos2IsEnabled()
			{
				return this.radioButtonStartPos2.isEnabled();
			}
			
			public boolean radioButtonStartPos3IsEnabled()
			{
				return this.radioButtonStartPos3.isEnabled();
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
			
			
	
	
	
		/////////////////
		//FAKED EVENTS://
		/////////////////
		
			
			/////////////
			//*BUTTONS*//
			////////////
		
			public void fakeClickButtonExitPart1()
			{
				this.buttonExitPart1.doClick();
			}
			
			public void fakeClickButtonExitPart2()
			{
				this.buttonExitPart2.doClick();
			}
			
			public void fakeClickButtonRollDiePart1()
			{
				this.buttonRollDiePart1.doClick();
			}
			
			public void fakeClickButtonLetsSetPlayers()
			{
				this.buttonLetsSetPlayers.doClick();
			}
			
			public void fakeClickButtonLetsPlayPart2()
			{
				this.buttonLetsPlayPart2.doClick();
			}
			
			public void fakeClickButtonGoBackPart2()
			{
				this.buttonGoBackPart2.doClick();
			}
			

			///////////////////
			//*RADIO BUTTONS*//
			///////////////////
	
			public void fakeClickradioButtonStartPos1()
			{
				this.radioButtonStartPos1.doClick();
			}
			
			public void fakeClickradioButtonStartPos2()
			{
				this.radioButtonStartPos2.doClick();
			}
			
			public void fakeClickradioButtonStartPos3()
			{
				this.radioButtonStartPos3.doClick();
			}
			
			////////////////
			//*TEXTFIELDS*//
			////////////////
			
}
