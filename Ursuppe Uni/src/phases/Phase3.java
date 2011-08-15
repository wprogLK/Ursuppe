package phases;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import enums.*;
import game.*;

/**
 * 
 */

/**
 * @author lukas
 *
 */
public class Phase3 extends JPanel implements ActionListener, ListSelectionListener
{
	//TODO: Implement buy genes!
	
	//BASIC THINGS OF THIS GUI:
	private boolean isActive;		//if this PhaseGui is the actual gui, the "showing gui"
	private GamePhases activePhase;	//this is IMPORTANT!
	private GameGene activeGene=null;
	private GameGene selectedGene=null;
	
	
	private boolean isGeneListEmpty=true;

	
	
	//COMPONENTS OF THIS GUI:
	
		//PANELS:
			
			

			
		//LABELS:
			private JLabel labelGameTitle=new JLabel();
			private JLabel labelPhaseTitle=new JLabel();
			
			private JLabel labelActualPlayer=new JLabel();
			
			private JLabel labelInstruction=new JLabel();
			
			private JLabel labelGeneInfo=new JLabel();
			private JLabel labelGeneName=new JLabel();
			//private JLabel labelGeneDescription=new JLabel();
			private JTextArea textAreaGeneDescription=new JTextArea();
			private JLabel labelGenePrice=new JLabel();
			private JLabel labelGeneScore=new JLabel();
			private JLabel labelGeneOzoneValue=new JLabel();
			private JLabel labelGeneNrAvailable=new JLabel();
			
			private JLabel labelInfoPlayerBlue=new JLabel();
			private JLabel labelInfoPlayerRed=new JLabel();
			private JLabel labelInfoPlayerYellow=new JLabel();
			
		//JLIST:
			private JList listGenes=new JList();
			

		//TEXTFIELDS:
			
	
		//BUTTONS:
			private JButton buttonGoBack=new JButton();
			private JButton buttonExit=new JButton();
			private JButton buttonGoToPhase4=new JButton();
			
			private JButton buttonBuyGene=new JButton();
			private JButton buttonSkip=new JButton();
			
		//RADIO BUTTONS:
	
	
		//BOXES
		
	
		//OTHER STUFF
		private ArrayList<GameGene> possibleGenesToBuy=new ArrayList<GameGene>();
		
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
	public Phase3(Game game) {
		//ARGUMENTES OF THE CONSTRUCTOR:
		this.game=game;
		this.board=game.getBoard();
		
		init(game);
			
			//this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		//Update/Initialize all the data for the components	//IMPORTANT!!!
	
		
			
			//this.updateData();
			
		//CREATE ALL PANELS AND COMPONENTES:
			this.createPanels();
	
		
			
		
	}

	private void init(Game game) {
		
		//SET THE PHASE TO THE GAME:
		this.readDirection=GamePhases.phase3.getReadDirection();
		this.game.setReadDirection(readDirection);
		this.phaseTitle="Phase 3: Buy new genes";
		
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
		
		
		
		////////////
		//SETTERS://
		////////////
	
		
	
	
	
	
	
	///////////////////////////////////
	////////ACTIVATE/DEACTIVATE:///////
	///////////////////////////////////
	//set this GUI to the active GUI (call by GUILOGIC)
	public void activate()
	{
	
		//System.out.println("Actual Ameba: " + this.actualAmeba);
		//System.out.println("------------------ACTIVATE PHASE 3----------------");
		this.activePhase=GamePhases.phaseEmpty;
		this.isActive=true;
		this.setVisible(true);
		doUpdate();
		
		this.buttonGoToPhase4.setEnabled(false);
		this.add(this.board);	//IMPORTANT!!
		
	
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
		boolean noMoreGenesToBuy = updateLists();
				

		
			
				updateLabels();
			
				
			
			
				updateButtons(noMoreGenesToBuy);
				
			
			
				this.setAllBounds();
		
		
	}

	private void updateButtons(boolean noMoreGenesToBuy) {
		this.buttonExit.setText("Exit...");
		this.buttonGoBack.setText("Go back to the previous phase");
		this.buttonGoToPhase4.setText("Go to phase4");
		this.buttonBuyGene.setText("Buy gene");
		
		this.buttonBuyGene.setEnabled(false);
		
		this.buttonExit.setToolTipText("Click here to end the whole game");
		this.buttonGoBack.setToolTipText("Click here to go back to phase 1");
		this.buttonGoToPhase4.setToolTipText("Click here to to phase 4");
		this.buttonBuyGene.setToolTipText("Click here to buy gene");
		this.buttonSkip.setText("next player");
		
		if (noMoreGenesToBuy)
		{
			this.buttonBuyGene.setText("not enough money to buy genes!");
			
		}
	}

	private void updateLabels() {
		this.labelInstruction.setText("Environment and gene defects");
		
		this.labelGameTitle.setText(this.gameTitle);
		this.labelPhaseTitle.setText("Phase: " + this.phaseTitle);
		
		this.labelInfoPlayerBlue.setText(this.playerBlue.toString());
		this.labelInfoPlayerRed.setText(this.playerRed.toString());
		this.labelInfoPlayerYellow.setText(this.playerYellow.toString());
		
		this.labelActualPlayer.setText("The actual player is: " + this.game.getActualPlayer().getPlayerName() + " [ " + this.game.getActualPlayer().getColor() + " ]");
		
		this.labelGeneInfo.setText("Info about the selected gene: ");
	}

	private boolean updateLists() {
		boolean noMoreGenesToBuy=true;
		
				//LIST:
				this.listGenes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				this.listGenes.setLayoutOrientation(JList.VERTICAL);
				this.listGenes.setVisibleRowCount(-1);
				
				DefaultListModel listModel = new DefaultListModel();
				for (int i=0; i<this.game.getArrayOfGenes().size(); i++)
				{
					GameGene gene=this.game.getArrayOfGenes().get(i);
					
					
					if (gene.getPrice()<=this.actualPlayer.getBioPoints() && gene.getAvailableNrOfGenes()>0)
					{
						listModel.addElement(this.game.getArrayOfGenes().get(i));
						this.possibleGenesToBuy.add(gene);
				
						noMoreGenesToBuy=false;
						this.isGeneListEmpty=false;
				
					}
					
				}
				
				this.listGenes.setModel(listModel);
		return noMoreGenesToBuy;
	}
	
	private void updateSelectedGeneInfo()
	{
		String geneName="Name: ";
		String geneDescription="Description: ";
		String geneScore="ScoreValue: ";
		String genePrice="Price: ";
		String geneOzoneValue="OzoneValue: ";
		String geneNrAvailable="Available nr of this gene: ";
		
		
		if (this.selectedGene==null)
		{
		
			geneName.concat(" - ");
			geneDescription.concat(" - ");
			geneScore.concat(" - ");
			genePrice.concat(" - ");
			geneOzoneValue.concat(" - ");
			geneNrAvailable.concat(" - ");
		}
		else
		{
			
			geneName=geneName+this.selectedGene.getName();
			geneDescription=geneDescription+this.selectedGene.getDescription();
			geneScore=geneScore+Integer.toString(this.selectedGene.getScore());
			genePrice=genePrice+Integer.toString(this.selectedGene.getPrice());
			geneOzoneValue=geneOzoneValue+Integer.toString(this.selectedGene.getOzoneValue());
			geneNrAvailable=geneNrAvailable+Integer.toString(this.selectedGene.getAvailableNrOfGenes());
		}
		
		this.labelGeneName.setText(geneName);
		
		this.textAreaGeneDescription.setText(geneDescription);
		this.labelGenePrice.setText(genePrice);
		this.labelGeneScore.setText(geneScore);
		this.labelGeneOzoneValue.setText(geneOzoneValue);
		this.labelGeneNrAvailable.setText(geneNrAvailable);
		
	
		
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
		
		
		calcTextArea();
		
	
		calcButtons(calc);
	
		calcLists(calc);
		
		calcOthers(calc);
		
	
	}

	private void calcOthers(Calc calc) {
		calc.calcBoard(this.board, 100, 100);
	}

	private void calcLists(Calc calc) {
		calc.calcList(this.listGenes,850, 380);
	}

	private void calcButtons(Calc calc) {
		calc.calcButton(this.buttonExit, 100, 1000);
		calc.calcButton(this.buttonGoBack, 450, 1000);
		calc.calcButton(this.buttonGoToPhase4, 200, 1000);
		calc.calcButton(this.buttonBuyGene,1200,700);
		calc.calcButton(this.buttonSkip,1000,700);
	}

	private void calcTextArea() {
		this.textAreaGeneDescription.setBounds(1000, 220, 800, 80);
	}

	private void calcLabels(Calc calc) {
		calc.calcLabel(labelGameTitle, 10, 10);
		calc.calcLabel(this.labelPhaseTitle, 1000, 50);
		
		calc.calcLabel(this.labelActualPlayer, 1000, 150);
		
		calc.calcLabel(this.labelInstruction, 1000, 100);
	
		
		
		calc.calcLabel(this.labelInfoPlayerBlue, 10, 25);
		calc.calcLabel(this.labelInfoPlayerRed, 10, 40);
		calc.calcLabel(this.labelInfoPlayerYellow, 10, 55);
		
		calc.calcLabel(this.labelGeneInfo,1000,180);
		calc.calcLabel(this.labelGeneName, 1000,200);
		calc.calcLabel(this.labelGenePrice, 1000,300);
		calc.calcLabel(this.labelGeneScore, 1000,320);
		calc.calcLabel(this.labelGeneOzoneValue, 1000,340);
		calc.calcLabel(this.labelGeneNrAvailable, 1000,360);
	}

	private void calcPanels(Calc calc) {
		calc.calcPanel(this, 0, 0);
	}
	

	
	
	
	
	
	
	

	private void createPanel1()
	{	
		
	
		addToActionListenerButton();
		
		JScrollPane listScroller = new JScrollPane(listGenes);
		listScroller.setPreferredSize(new Dimension(250, 80));

		
		this.listGenes.addListSelectionListener(this);
		
	

		configTextArea();
	
		addToThisLabels();
	
		addToThisOthers();
		
		
		
		addToThisButtons();
		
		addToThisLists();
		
		
		

	}

	private void addToThisLists() {
		this.add(this.listGenes);
	}

	private void addToThisButtons() {
		this.add(this.buttonGoBack);
		this.add(this.buttonExit);
		this.add(this.buttonGoToPhase4);
		this.add(this.buttonBuyGene);
		this.add(this.buttonSkip);
	}

	private void addToThisOthers() {
		this.add(this.board);
		this.add(this.die);
	}

	private void addToThisLabels() {
		this.add(this.labelGeneName);
		this.add(this.textAreaGeneDescription);
		this.add(this.labelGenePrice);
		this.add(this.labelGeneScore);
		this.add(this.labelGeneOzoneValue);
		this.add(this.labelGeneNrAvailable);
		this.add(this.labelGeneInfo);
		
		this.add(this.labelPhaseTitle);
		this.add(this.labelGameTitle);
		
		this.add(this.labelInfoPlayerBlue);
		this.add(this.labelInfoPlayerRed);
		this.add(this.labelInfoPlayerYellow);
		
		this.add(this.labelActualPlayer);
		
		this.add(this.labelInstruction);
	}

	private void configTextArea() {
		this.textAreaGeneDescription.setLineWrap(true); //Zeilenumbruch an
		this.textAreaGeneDescription.setEditable(false);
		this.textAreaGeneDescription.setBackground(this.labelGeneInfo.getBackground());
		this.textAreaGeneDescription.setFont(this.labelGeneInfo.getFont());
	}

	private void addToActionListenerButton() {
		this.buttonGoBack.addActionListener(this);
		this.buttonExit.addActionListener(this);
		this.buttonGoToPhase4.addActionListener(this);
		this.buttonBuyGene.addActionListener(this);
		this.buttonSkip.addActionListener(this);
	}
	
	/////////////////////////////////
	//SPECIAL METHODS OF THIS PHASE://
	/////////////////////////////////

	
	
	///////////
	//ACTIONS://
	////////////

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==this.buttonGoToPhase4)
		{
			this.activePhase=GamePhases.phase4;
		}
		else if (e.getSource()==this.buttonExit)
		{
			System.exit(0);
		}
		else if (e.getSource()==this.buttonGoBack)
		{
				this.activePhase=GamePhases.phaseSetFirstAmeba; //Go back to phase SetNames
		}
		else if (e.getSource()==this.buttonBuyGene)
		{
			   
			   this.activeGene=(GameGene) listGenes.getSelectedValue();
			   activeGene.buyGene();
			   System.out.println("You buy : " + activeGene);
			
			
			   this.actualPlayer.addGene(activeGene);
			   this.selectedGene=null;
			   this.updateSelectedGeneInfo();
			   
		}
		else if(e.getSource()==this.buttonSkip)
		{
			this.game.nextPlayer();
			this.actualPlayer=this.game.getActualPlayer();
			
			this.selectedGene=null;
			this.updateSelectedGeneInfo();
			
			if (this.actualPlayer.equals(this.game.getEmptyPlayer()))
			{
				this.buttonGoToPhase4.setEnabled(true);
				this.buttonBuyGene.setEnabled(false);
				this.buttonSkip.setEnabled(false);
			}
			else
			{
				this.buttonGoToPhase4.setEnabled(false);
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
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		 if (e.getValueIsAdjusting() == false) {

		        if (this.listGenes.getSelectedIndex() == -1) {
		      
		            this.buttonBuyGene.setEnabled(false);
		            
		            this.selectedGene=null;
		        } 
		        else {
		        
		        	  this.buttonBuyGene.setEnabled(true);
		        	  this.selectedGene=(GameGene) listGenes.getSelectedValue();
		        	  this.updateSelectedGeneInfo();
		        }
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
	
		
			
			
			
			/////////////
			//*BUTTONS*//
			/////////////
			public boolean getButtonGoToPhase4IsEnabled()
			{
				return this.buttonGoToPhase4.isEnabled();
			}
			public boolean getButtonExitIsEnabled()
			{
				return this.buttonExit.isEnabled();
			}
			public boolean getButtonGoBackIsEnabled()
			{
				return this.buttonGoBack.isEnabled();
			}
			public boolean getButtonBuyGeneIsEnabled()
			{
				return this.buttonBuyGene.isEnabled();
			}
			public boolean getButtonSkipPlayerIsEnabled()
			{
				return this.buttonSkip.isEnabled();
			}
			
			
			//////////////
			//LIST://
			///////////
			
			public boolean getIsListGenesEmpty()
			{
				return this.isGeneListEmpty;
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
		
			public void fakeClickbuttonGoBack()
			{
				this.buttonGoBack.doClick();
			}
			public void fakeClickbuttonExit()
			{
				this.buttonExit.doClick();
			}
			public void fakeClickbuttonGoToPhase4()
			{
				this.buttonGoToPhase4.doClick();
			}
			public void fakeClickbuttonBuyGene()
			{
				this.buttonBuyGene.doClick();
			}
			public void fakeClickbuttonSkip()
			{
				this.buttonSkip.doClick();

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
			
			public GameGene fakeSelectItemListGene(GameGene gene)
			{
				assert (this.possibleGenesToBuy.contains(gene));
				
				int index=-1;
				
				for (int i=0; i<this.possibleGenesToBuy.size();i++)
				{
					GameGene geneInList=this.possibleGenesToBuy.get(i);
					
					if (gene.equals(geneInList))
					{
						index=i;
					}
				}
				
				
				if (index==-1)
				{
					System.out.println("Error in Phase3.class: in fakeSelectItemListGene()!");
					//TODO
				}
				
				this.listGenes.setSelectedIndex(index);
				GameGene selectGene=(GameGene) listGenes.getSelectedValue();
				
				
				
				return selectGene;
				
			}
			
			
}
