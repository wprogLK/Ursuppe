package phasesGUI;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import phases.Phase2;

import Components.Board;
import Components.Die;
import Components.Player;

import enums.*;
import game.*;
import helpClasses.Calc;




/**
 * @author lukas
 *
 */
public class Phase2GUI extends JPanel implements ActionListener, ListSelectionListener
{
	
	//BASIC THINGS OF THIS GUI:
	private boolean isActive;		//if this PhaseGui is the actual gui, the "showing gui"
	private GamePhases activePhase;	//this is IMPORTANT!
	private int nrOfPlayerPlayed=1;
	private ArrayList<GameGene> selectedGenes=new ArrayList<GameGene>();
	private int differenceToPay=0;
	
	private Phase2 phase;
	
	//COMPONENTS OF THIS GUI:
	
		//PANELS:
			
			

			
		//LABELS:
			private JLabel labelGameTitle=new JLabel();
			private JLabel labelPhaseTitle=new JLabel();
			
			private JLabel labelActualPlayer=new JLabel();
			
			private JLabel labelInstruction=new JLabel();
			
			private JLabel labelWarning=new JLabel();

			
			private JLabel labelInfoPlayerBlue=new JLabel();
			private JLabel labelInfoPlayerRed=new JLabel();
			private JLabel labelInfoPlayerYellow=new JLabel();
			
		

		//TEXTFIELDS:
			private JTextField textFieldNrOfBioPointsToSell=new JTextField(10);
			
	
		//LIST:
		
			private JList listGenesToSell=new JList();
			
		//BUTTONS:
			private JButton buttonGoBack=new JButton();
			private JButton buttonExit=new JButton();
			private JButton buttonGoToPhase3=new JButton();
		
			
			private JButton buttonNextPlayer=new JButton();
			private JButton buttonSell=new JButton();
		
			
		//RADIO BUTTONS:
	
	
		//BOXES
		
	
		//OTHER STUFF
			private ArrayList<GameGene> possibleGenesToSell=new ArrayList<GameGene>();
		
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
	private Object[] tmpSelectedGenes;
	
	/**
	 * Constructor
	 */
	public Phase2GUI(Game game, Phase2 phase) {
		//ARGUMENTES OF THE CONSTRUCTOR:
		this.phase=phase;
		
		this.game=game;
		this.board=game.getBoard();
		
		init(game);
	
		
			
			//this.updateData();
			
		//CREATE ALL PANELS AND COMPONENTES:
			this.createPanels();
	
		
			
		
	}

	private void init(Game game) {
		
		//SET THE PHASE TO THE GAME:
		this.readDirection=GamePhases.phase2.getReadDirection();
		this.game.setReadDirection(readDirection);
		this.phaseTitle="Phase 2: Enviroment and gene defects";
		
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
		this.game.getNextOzonValue();		//Only in phase2!
		this.game.getNextWindDirection();	//Only in phase2!
		
		
		this.differenceToPay=this.actualPlayer.getSumOfAllGenesOzoneValue()- this.game.getOzoneValue(); //RESET
		System.out.println("difference to pay" +this.differenceToPay);
		if (this.differenceToPay<0)
		{
			this.differenceToPay=0;
		}
	
		
		//System.out.println("Actual Ameba: " + this.actualAmeba);
		//System.out.println("------------------ACTIVATE PHASE 2----------------");
		this.activePhase=GamePhases.phaseEmpty;
		this.isActive=true;
		this.setVisible(true);
		doUpdate();
		
		this.add(this.board);	//IMPORTANT!!
		this.buttonGoToPhase3.setEnabled(false);
	
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
		this.isOzoneValueOfPlayerOK();
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
//		this.differenceToPay=this.actualPlayer.getSumOfAllGenesOzoneValue()- this.game.getOzoneValue(); //RESET
//		System.out.println("difference to pay" +this.differenceToPay);
//		if (this.differenceToPay<0)
//		{
//			this.differenceToPay=0;
//		}
		
		//this.checkIsNextPlayerOnTurn();
		
		//this.buttonGoToPhase3.setEnabled(false);
		
		
		
		//////////////////////
		//UPDATE COMPONENTS://
		//////////////////////
		
			
			
				//PANELS:
				
				//LABELS:
			
				updatesLabels();
				
				
				//LIST:
				updateLists();
			
				
				//TEXTFIELDS:
			
				updateTextFields();
				
				//BUTTONS:
				updateButtons();
				
				
				//Dialogue1:
				
				
				
				
				//Dialogue2:
				
				
				//Dialogue3:
				
			
				
				//Dialogue4:
			
				
				
				//RADIOBUTTONS:
				
				
				//BOXES:
				
				//OTHER STUFF:
				
				//SPECIAL STUFF:
				
				//SPECIAL METHODS:
				
			
			
				this.setAllBounds();
		
		
	}

	private void updateButtons() {
		this.buttonExit.setText("Exit...");
		this.buttonGoBack.setText("Go back to the previous phase");
		this.buttonGoToPhase3.setText("Go to phase3");
		this.buttonNextPlayer.setText("Next player");
		this.buttonSell.setText("Sell!");
		
		this.buttonExit.setToolTipText("Click here to end the whole game");
		this.buttonGoBack.setToolTipText("Click here to go back to phase 1");
		this.buttonGoToPhase3.setToolTipText("Click here to to phase 3");
		this.buttonNextPlayer.setToolTipText("Click here to activate the next player");
		this.buttonSell.setToolTipText("Sell " + this.selectedGenes + " genes and " + this.textFieldNrOfBioPointsToSell.getText() + " BioPoint(s)");
	}

	private void updateTextFields() {
		this.textFieldNrOfBioPointsToSell.setText(""+0);
	}

	private void updateLists() {
		this.listGenesToSell.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION );
		this.listGenesToSell.setLayoutOrientation(JList.VERTICAL);
		this.listGenesToSell.setVisibleRowCount(-1);
		
		
		
		boolean noMoreGenesToSell=true;
		DefaultListModel listModel = new DefaultListModel();
		
		for (int i=0; i<this.actualPlayer.getGenes().size(); i++)
		{
			GameGene gene=this.actualPlayer.getGenes().get(i);
			//System.out.println("Gene check to add: "+ gene);
			
			
			
				listModel.addElement(this.actualPlayer.getGenes().get(i));
				this.possibleGenesToSell.add(gene);
				//System.out.println("Gene add to list: "+ gene);
				noMoreGenesToSell=false;
			
		
		
			
		}
		
		this.listGenesToSell.setModel(listModel);
	}

	private void updatesLabels() {
		this.labelInstruction.setText("Environment and gene defects");
		
		this.labelGameTitle.setText(this.gameTitle);
		this.labelPhaseTitle.setText("Phase: " + this.phaseTitle);
		
		this.labelInfoPlayerBlue.setText(this.playerBlue.toString());
		this.labelInfoPlayerRed.setText(this.playerRed.toString());
		this.labelInfoPlayerYellow.setText(this.playerYellow.toString());
		
		this.labelActualPlayer.setText("The actual player is: " + this.game.getActualPlayer().getPlayerName() + " [ " + this.game.getActualPlayer().getColor() + " ]");
	}
	
	private void isOzoneValueOfPlayerOK()
	{
		this.differenceToPay=this.actualPlayer.getSumOfAllGenesOzoneValue()- this.game.getOzoneValue(); //RESET
		System.out.println("difference to pay" +this.differenceToPay);
		if (this.differenceToPay<0)
		{
			this.differenceToPay=0;
		}
		
		if (this.differenceToPay<=0  || this.actualPlayer.getSumOfAllGenesOzoneValue()<=0)
		{
			this.buttonNextPlayer.setEnabled(true);
			this.labelWarning.setText("Your ozone value is ok. It is "+ this.actualPlayer.getSumOfAllGenesOzoneValue() + " You can sell some genes if you want, more than one gene can be selected at once! ");
			
		}
		else
		{
			this.buttonNextPlayer.setEnabled(false);
			this.labelWarning.setText("Your ozone value is to high! Sell genes and/or sell bioPoints. You have a sum of ozoneValue of "+ this.actualPlayer.getSumOfAllGenesOzoneValue() + " and the ozone layer on Board is "+ this.game.getOzoneValue() + " the difference which you must pay is: " + this.differenceToPay );
		}
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
		
		//////////////////////
		//LISTS://///
		/////////////
		calcLists(calc);
		
		////////////////////
		//TEXTFIELDS://
		///////////////
		
		calcTextFields(calc);
		
		
		////////////////
		//BUTTONS://
		////////////
		calcButtons(calc);
		
		
		
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

	private void calcButtons(Calc calc) {
		calc.calcButton(this.buttonExit, 100, 1000);
		calc.calcButton(this.buttonGoBack, 450, 1000);
		calc.calcButton(this.buttonGoToPhase3, 200, 1000);
		
		calc.calcButton(this.buttonNextPlayer, 1000, 700);
		calc.calcButton(this.buttonSell, 1200, 700);
	}

	private void calcTextFields(Calc calc) {
		calc.calcTextField(this.textFieldNrOfBioPointsToSell, 1100, 600);
	}

	private void calcLists(Calc calc) {
		calc.calcList(this.listGenesToSell,1000, 280);
	}

	private void calcLabels(Calc calc) {
		calc.calcLabel(labelGameTitle, 10, 10);
		calc.calcLabel(this.labelPhaseTitle, 1000, 50);
		
		calc.calcLabel(this.labelActualPlayer, 1000, 150);
		
		calc.calcLabel(this.labelInstruction, 1000, 100);
	
		
		
		calc.calcLabel(this.labelInfoPlayerBlue, 10, 25);
		calc.calcLabel(this.labelInfoPlayerRed, 10, 40);
		calc.calcLabel(this.labelInfoPlayerYellow, 10, 55);
		
		calc.calcLabel(this.labelWarning, 900, 250);
	}

	private void calcPanels(Calc calc) {
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
		addToActionListenerButtons();
		
		JScrollPane listScroller = new JScrollPane(this.listGenesToSell);
		listScroller.setPreferredSize(new Dimension(250, 80));

		
		addToActionListenerLists();
	

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
		addToThisTextFields();
		addToThisLists();

	
		//this.add(this.panelInterface);
		addToThisOthers();
		
		
		addToThisButtons();
		
		
		

	}

	private void addToThisButtons() {
		this.add(this.buttonNextPlayer);
		this.add(this.buttonSell);
		this.add(this.buttonGoBack);
		this.add(this.buttonExit);
		this.add(this.buttonGoToPhase3);
	}

	private void addToThisOthers() {
		this.add(this.board);
		this.add(this.die);
	}

	private void addToThisLists() {
		this.add(this.listGenesToSell);
	}

	private void addToThisTextFields() {
		this.add(this.textFieldNrOfBioPointsToSell);
	}

	private void addToThisLabels() {
		this.add(this.labelPhaseTitle);
		this.add(this.labelGameTitle);
		
		this.add(this.labelInfoPlayerBlue);
		this.add(this.labelInfoPlayerRed);
		this.add(this.labelInfoPlayerYellow);
		
		this.add(this.labelActualPlayer);
		
		this.add(this.labelInstruction);
	
	
		this.add(this.labelWarning);
	}

	private void addToActionListenerLists() {
		this.listGenesToSell.addListSelectionListener(this);
	}

	private void addToActionListenerButtons() {
		this.buttonGoBack.addActionListener(this);
		this.buttonExit.addActionListener(this);
		this.buttonGoToPhase3.addActionListener(this);
		
		this.buttonNextPlayer.addActionListener(this);
		this.buttonSell.addActionListener(this);
	}
	
	
	
	
	


	
	
	
	/////////////////////////////////
	//SPECIAL METHODS OF THIS PHASE://
	/////////////////////////////////
	
		
		
	
	
	
	
	

	
	
	
	
	
	
	
	
	///////////
	//ACTIONS://
	////////////

	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO: AB HIER FREITAG WEITER MACHEN
		
		if(e.getSource()==this.buttonGoToPhase3)
		{
			//System.out.println("GO PHASE 3");
			this.activePhase=GamePhases.phase3;
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
		else if(e.getSource()==this.buttonSell)
		{
			if (this.checkInput())
			{
				int nrOfBioPointsToSell= Integer.parseInt(this.textFieldNrOfBioPointsToSell.getText());	
				
				
				
				System.out.println("Genes to sell: "+this.selectedGenes);
				
				for (int i=0; i<this.selectedGenes.size();i++)
				{
					GameGene gene=this.selectedGenes.get(i);
					
					gene.sellGene();
					this.actualPlayer.removeGene(gene);
					
					if (this.actualPlayer.getSumOfAllGenesOzoneValue()<=this.differenceToPay) //If a player only want to sell genes from other reasons...
					{
						this.actualPlayer.addBioPoints(gene.getPrice());
					}
					else
					{
						//no adding bioPoints!
					}
				}
				
				if (this.actualPlayer.getSumOfAllGenesOzoneValue()<=this.differenceToPay) //If a player only want to sell genes from other reasons...
				{
					//do nothing
				}
				else
				{
					this.actualPlayer.subBioPoints(nrOfBioPointsToSell);
					this.differenceToPay-=nrOfBioPointsToSell;
				}
				
				this.isOzoneValueOfPlayerOK();
			}
			else
			{
				System.out.println("check output=false!");
			}
			
			
		}
		else if(e.getSource()==this.buttonNextPlayer)
		{
			this.differenceToPay=this.actualPlayer.getSumOfAllGenesOzoneValue()- this.game.getOzoneValue(); //RESET
			System.out.println("difference to pay" +this.differenceToPay);
			if (this.differenceToPay<0)
			{
				this.differenceToPay=0;
			}
			
			this.game.nextPlayer();
			this.actualPlayer=this.game.getActualPlayer();
			
			if (this.nrOfPlayerPlayed==this.game.getNumberOfPlayers())
			{
				this.buttonGoToPhase3.setEnabled(true);
				this.buttonSell.setEnabled(true);
				this.buttonNextPlayer.setEnabled(false);
			}
			else
			{
				System.out.println("next player is "+ this.nrOfPlayerPlayed);
				this.buttonGoToPhase3.setEnabled(false);
				this.nrOfPlayerPlayed++;
			}
		}
		else if(e.getSource()==this.textFieldNrOfBioPointsToSell)
		{
			this.buttonSell.setEnabled(true);
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
	
	private boolean checkInput() {
		boolean valid=true;
		if (this.textFieldNrOfBioPointsToSell.getText()==""  )
		{
			JOptionPane.showMessageDialog(null, "Error: No empty inputs allowed!");
			valid=false;
			return valid;
		}
		else
		{
			int nrOfBioPointsToSell= Integer.parseInt(this.textFieldNrOfBioPointsToSell.getText());	
			
			if (nrOfBioPointsToSell>this.actualPlayer.getBioPoints())
			{
				JOptionPane.showMessageDialog(null, "Error: you wanted to sell " + nrOfBioPointsToSell + " but you have only "+ this.actualPlayer.getBioPoints() +"!");
				 valid=false;
			}
			
			if(nrOfBioPointsToSell<0)
			{
				JOptionPane.showMessageDialog(null, "Error: the number of bioPoint must be 0 or greater ! You wanted to sell " + nrOfBioPointsToSell);
				 valid=false;
			}

			return valid;
		}
		
		
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		 if (e.getValueIsAdjusting() == false) {

			 
			 
		        if (this.listGenesToSell.getSelectedIndex() == -1) {
		      
		            ///this.buttonSell.setEnabled(false);
		            
		            this.selectedGenes=new ArrayList<GameGene>() ;
		        } 
		        else {
		        
		        	  this.buttonSell.setEnabled(true);
		        	 // this.selectedGenes.add((GameGene) listGenesToSell.getSelectedValue());
		        	 
				
		        	 this.tmpSelectedGenes= this.listGenesToSell.getSelectedValues();
		        	 
		        	 this.selectedGenes=new ArrayList<GameGene>();	//clear the arrayList
		        	 
		        	 for (int i=0;i<this.tmpSelectedGenes.length;i++)
		        	 {
		        		 System.out.println("tmpSelectedGenes : [ " + i + " ]" + tmpSelectedGenes[i]);
		        		 GameGene gene=(GameGene) tmpSelectedGenes[i];
		        		 
		        		 this.selectedGenes.add(gene);
		        	 }
		        	 
		        	
		        }
		        
		        System.out.println("All SelectedGenes : "+ this.selectedGenes);
		    }
		 
			//this.updateData();

		
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
	
				public boolean getIsListGenesEmpty()
				{
					return this.possibleGenesToSell.isEmpty();
				}
		

			////////////
			//*LABELS*//
			////////////
	
		
			
			
			
			/////////////
			//*BUTTONS*//
			/////////////
	
			public boolean getButtonGoToPhase3IsEnabled()
			{
				return this.buttonGoToPhase3.isEnabled();
			}
		
			public boolean getButtonGoBackIsEnabled()
			{
				return this.buttonGoBack.isEnabled();
			}
			public boolean getButtonExitIsEnabled()
			{
				return this.buttonExit.isEnabled();
			}
			
			public boolean getButtonSellIsEnabled()
			{
				return this.buttonSell.isEnabled();
			}
			public boolean getButtonNextPlayertIsEnabled()
			{
				return this.buttonNextPlayer.isEnabled();
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
			
			public void setTextTextFieldNrOfBioPointsToSell(int value)
			{
				this.textFieldNrOfBioPointsToSell.setText(Integer.toString(value));
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

			public void fakeClickbuttonGoToPhase3()
			{
				this.buttonGoToPhase3.doClick();
			}
		
			public void fakeClickButtonNextPlayer()
			{
				this.buttonNextPlayer.doClick();
			}
			
			public void fakeClickButtonSell()
			{
				this.buttonSell.doClick();
			}
			

			///////////////////
			//*RADIO BUTTONS*//
			///////////////////
	
	
			////////////////
			//*TEXTFIELDS*//
			////////////////
			
			//////////////
			//LIST://
			///////////
			
			public void fakeSelectItemListGene(GameGene[] genes)
		{
				
				  this.buttonSell.setEnabled(true);
	        	 this.selectedGenes=new ArrayList<GameGene>();	//clear the arrayList
	        	 
	        	 tmpSelectedGenes=genes;
	        	 for (int i=0;i<this.tmpSelectedGenes.length;i++)
	        	 {
	        		 System.out.println("tmpSelectedGenes : [ " + i + " ]" + tmpSelectedGenes[i]);
	        		 GameGene gene=(GameGene) tmpSelectedGenes[i];
	        		 
	        		 this.selectedGenes.add(gene);
	        	 }
	        	 
	        	 System.out.println("faked selected genes are: "+ this.selectedGenes);
				
				//OLD 2:
//				
//				int[] indexSelected=new int[genes.length];
//				
//				for (int i=0; i<this.possibleGenesToSell.size();i++)
//				{
//					GameGene possibleGene=this.possibleGenesToSell.get(i);
//					
//					for (int j=0; j<genes.length;j++)
//					{
//						GameGene geneToFake=genes[j];
//						
//						if (geneToFake==possibleGene)
//						{
//							indexSelected[j]=j;
//						}
//					}
//				}
//				
//				System.out.println("indexSelected :" );
//					for (int i=0; i<indexSelected.length;i++)
//					{
//						System.out.println("i= " + i + "  SelectedINDEX: " +indexSelected[i]);
//					}
//				
				
				
				//OLD 1
				
				//this.listGenesToSell.setSelectedIndices(arg0)
				
				
//				assert (this.possibleGenesToSell.contains(gene));
//				
//				int index=-1;
//				
//				for (int i=0; i<this.possibleGenesToSell.size();i++)
//				{
//					GameGene geneInList=this.possibleGenesToSell.get(i);
//					
//					if (gene.equals(geneInList))
//					{
//						index=i;
//					}
//				}
//				
//				
//				if (index==-1)
//				{
//					System.out.println("Error in Phase3.class: in fakeSelectItemListGene()!");
//					//TODO
//				}
//				
//				this.listGenesToSell.setSelectedIndex(index);
//				GameGene selectGene=(GameGene) listGenesToSell.getSelectedValue();
				
				
				
			//	return selectGene;
				
			}
}
