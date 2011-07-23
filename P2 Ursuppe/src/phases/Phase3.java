package phases;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import phasesGUI.Phase2GUI;
import phasesGUI.Phase3GUI;

import Components.Board;
import Components.Die;
import Components.LadderSquare;
import Components.Player;

import enums.*;
import exceptions.ExceptionInvalidInput;
import game.*;
import helpClasses.Calc;
import helpClasses.ReadShowGUIOrNormal;
import helpClasses.UserInput;

/**
 * 
 */

/**
 * @author lukas
 *
 */
public class Phase3
{

	
	//BASIC THINGS OF THIS GUI:
	private boolean isActive;		//if this PhaseGui is the actual gui, the "showing gui"
	private GamePhases activePhase;	//this is IMPORTANT!
	private GameGene activeGene=null;
	private GameGene selectedGene=null;
	
	private ArrayList<Integer> possibleNrOfGenesToBuy=new ArrayList<Integer>();
	
	private boolean isGeneListEmpty=true;

	
	
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
	
	private Phase3GUI phaseGUI;
	
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
		boolean createGUI=ReadShowGUIOrNormal.read();
		
		if (createGUI)
		{
			this.phaseGUI = new Phase3GUI(this.game,this);
		}
		
			
			//this.updateData();
			
		//CREATE ALL PANELS AND COMPONENTES:
			
	
		
			
		
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
		
		public void deactivate()
		{
			this.isActive=false;
		}
		
		public void activate()
		{
			this.activePhase=GamePhases.phaseEmpty;
			this.isActive=true;
			
			if (this.phaseGUI!=null)
			{
				this.phaseGUI.activate();
				doUpdate();
			}
			else
			{
				System.out.println("\t PHASE 3: Buy genes");
				doPrintMessage();
				
				
				doUpdate();
			}
		}
		
		public void doPrintMessage()
		{
				System.out.println("You can enter: \t e - exit \n \t w - go to Welcome phase \n \t b - go one phase back");
					
				System.out.println();
				System.out.println(this.game.getInformationAboutPlayers());
				System.out.println();
				
				System.out.println("ORDER PHASE 3" + this.game.getOrderPlayers());
				System.out.println("CURRENT PLAYER P3" + this.game.getActualPlayer().getPlayerName());
				
				int playerPlayed=1;
				
				while(playerPlayed<=this.game.getNumberOfPlayers())
				{
					this.actualPlayer=this.game.getActualPlayer();
					
					this.showMainDialogue();
					
					
					this.game.nextPlayer();
					this.actualPlayer=this.game.getActualPlayer();
					this.doUpdate();
					
					playerPlayed++;
					
				}
				this.doGoToPhase4();
				//this.doUpdate();
		}

		private void showMainDialogue() 
		{
			System.out.println("Player " + this.actualPlayer.getPlayerName() + " is on turn now:");
			
			boolean noMoreGenesToBuy = this.checkNoMoreGenesToBuy();
			
			if (!noMoreGenesToBuy)
			{
				String input=UserInput.readInput("You can buy some genes. Do you want? \n -'y' yes \n - 'n' no");
				this.checkInput(input);
			}
			else
			{
				String input2=UserInput.readInput("You can't buy some genes. You haven't got enough bioPoints or all genes are already sold. Please press <enter> to skip yourself...");
				//this.doASCIISkipPlayer();
			}
			
		}

		public void checkInput(String input)
		{
			if (input.length()==1)
			{
				char a=input.charAt(0);
				
				switch (a)
				{
					case 'b':
					{
						this.doGoOnePhaseBack();
						
						break;
					}
					case 'w':
					{
						this.doGoToWelcome();
						
						break;
					}
					case 'n':
					{
						//this.doASCIISkipPlayer();
						
						break;
					}
					case 'y':
					{
						boolean noMoreGenesToBuy=this.checkNoMoreGenesToBuy();
						if (!noMoreGenesToBuy)
						{
							this.showDialogueBuyGene();
							//this.doASCIISkipPlayer();
						}
						else
						{
							System.out.println("Error: You typed in 'y', but this option isn't a valid option for you because you haven't got enough bioPoints or all genes are already bought");
							//this.doASCIISkipPlayer();
						}
						break;
					}
					case 'e':
					{
						System.out.println("Bye...");
						this.doExit();
						
						break;
					}
					default:
					{
								System.out.println("ERROR: Unkown instruction, please try it again...");
					}
				}
			}
			else
			{
				throw new ExceptionInvalidInput();
			}
			
		}
		
	

	private void showDialogueBuyGene() 
	{
		System.out.println("You want to buy some genes: \n You can buy following genes: ");
		this.possibleNrOfGenesToBuy.clear();
		
		System.out.println("( number )  \t \t name \t \t \t [ price ]");
		
		for (int i=0; i<this.possibleGenesToBuy.size();i++)
		{
			System.out.println("( "+ i +" ) : \t \t" +this.possibleGenesToBuy.get(i).getName() +" \t \t \t [ " + this.possibleGenesToBuy.get(i).getPrice() + " ]");
			this.possibleNrOfGenesToBuy.add(i);
		}
		
		String input=UserInput.readInput("Enter the number of the gene in which you are interested or type in 'back' to skip: ");
		
		try
		{
			this.checkGeneInput(input);
		}
		catch(ExceptionInvalidInput e)
		{
			System.out.println(e);
			this.showDialogueBuyGene();
		}
		
		
			
	}
	
	private void checkGeneInput(String input) {
		if (input.matches("back"))
		{
			System.out.println("You aren't interesting in buying new genes?! - OK...");
			
		}
		else
		{

			int index=Integer.parseInt(input);
			
			if (this.possibleNrOfGenesToBuy.contains(index))
			{
				String input2=UserInput.readInput("Type in 'b' for buying the gene or 'i' for see more information about the gene ");
				try
				{
					this.activeGene=this.possibleGenesToBuy.get(index);
					this.selectedGene=this.possibleGenesToBuy.get(index);
					
					checkInputBuyOrInfo(input2);
				}
				catch(ExceptionInvalidInput e)
				{
					System.out.println(e);
					this.checkGeneInput(input);
				}
				
			}
			else
			{
				throw new ExceptionInvalidInput();
			}
		}
		
	}
	
	private void askToBuyMoreGenes()
	{
		String input=UserInput.readInput("Do you want buy one more gene? \n -'y' yes \n -'n' no");
		
		
		if (input.length()==1)
		{
			if (input.charAt(0)=='y')
			{
				this.showMainDialogue();
			}
			else if (input.charAt(0)=='n')
			{
				//do nothing
			}
		}
	}

	private void checkInputBuyOrInfo(String input2) {
		if (input2.length()==1)
		{
			if (input2.charAt(0)=='b')
			{
				
				this.doBuyGene();
				this.askToBuyMoreGenes();
			}
			else if (input2.charAt(0)=='i')
			{
				this.updateASCIISelectedGeneInfo();
				
				String input3=UserInput.readInput("Do you want buy this gene now? \n -'y' yes \n -'n' no");
				if (input3.length()==1)
				{
					if (input3.charAt(0)=='y')
					{
						this.doBuyGene();
						this.askToBuyMoreGenes();
					}
					else
					{
						//do nothing
					}
				}
				
			}
			else
			{
				throw new ExceptionInvalidInput();
			}
		}
		else
		{
			throw new ExceptionInvalidInput();
		}
	}

	

	//////////////////////
	////////UPDATE:///////
	//////////////////////
	public void doUpdate()
	{
		this.updateData();
	}
	
	private void updateData()
	{
		this.game.skipEmptyPlayer();
		
		assert !game.getActualPlayer().equals(this.game.getEmptyPlayer());
		
		
		///////////////////////////
		//UPDATE CLASS VARIABLES://
		///////////////////////////
		
		
		
		this.actualPlayer=this.game.getActualPlayer();
		
		
		boolean noMoreGenesToBuy = this.checkNoMoreGenesToBuy();
	}
	
	



	

	private boolean checkNoMoreGenesToBuy() {
		boolean noMoreGenesToBuy=true;
		
				this.possibleGenesToBuy.clear();
				this.possibleNrOfGenesToBuy.clear();
				
				for (int i=0; i<this.game.getArrayOfGenes().size(); i++)
				{
					GameGene gene=this.game.getArrayOfGenes().get(i);
					
					
					if (gene.getPrice()<=this.actualPlayer.getBioPoints() && gene.getAvailableNrOfGenes()>0)
					{
						this.possibleGenesToBuy.add(gene);
						
						
						
						noMoreGenesToBuy=false;
						this.isGeneListEmpty=false;
				
					}
					
				}
				
		return noMoreGenesToBuy;
	}
	
	private void updateASCIISelectedGeneInfo()
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
		
		System.out.println("Gene information about gene " + geneName + ":");
		System.out.println("\t Description : \t" + geneDescription);
		System.out.println("\t Value : \t" + geneScore);
		System.out.println("\t Price : \t" + genePrice);
		System.out.println("\t OzoneValue : \t" + geneOzoneValue);
		System.out.println("\t Number of available genes : \t" + geneNrAvailable);
	}
	
	private void updateGUISelectedGeneInfo()
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
		
		//TODO GUI
	}

	
	/////////////////////////////
	////////CREATE PANELS:///////
	/////////////////////////////
	

	
	
	
	
	
	
	
	
	
	
	

	
	
	/////////////////////////////////
	//SPECIAL METHODS OF THIS PHASE://
	/////////////////////////////////

	
	
	///////////
	//ACTIONS://
	////////////
	
	public void doGoToPhase4()
	{
		System.out.println("GO TO PHASE 4");
		this.activePhase=GamePhases.phase4;
	}
	
	public void doGoToWelcome()
	{
		this.activePhase=GamePhases.phaseWelcome;
	}
	
	public void doExit()
	{
		System.out.println("bye...");
		System.exit(0);
	}
	
	public void doGoOnePhaseBack()
	{
		this.activePhase=GamePhases.phase2;
	}
	
	public void doBuyGene()
	{

			
		   this.activeGene.buyGene();
		   System.out.println("You bought : " + activeGene);
		
		
		   this.actualPlayer.addGene(activeGene);
		   this.selectedGene=null;
		 
		   
			this.doUpdate();
	}
		
	public void doASCIISkipPlayer()
	{
		this.game.nextPlayer();
		this.actualPlayer=this.game.getActualPlayer();
		
		this.selectedGene=null;
		//this.updateSelectedGeneInfo();
		
		if (this.actualPlayer.equals(this.game.getEmptyPlayer()))
		{
			this.doGoToPhase4();
//			this.buttonGoToPhase4.setEnabled(true);
//			this.buttonBuyGene.setEnabled(false);
//			this.buttonSkip.setEnabled(false);
		}
		else
		{
			//this.buttonGoToPhase4.setEnabled(false);
		}
		
		this.doUpdate();
	}
	
		
		////////////////
		//UPDATE DATA://	//do this in every case!
		////////////////

		
	
	
	
//	@Override
//	public void valueChanged(ListSelectionEvent e) {
//		 if (e.getValueIsAdjusting() == false) {
//
//		        if (this.listGenes.getSelectedIndex() == -1) {
//		      
//		            this.buttonBuyGene.setEnabled(false);
//		            
//		            this.selectedGene=null;
//		        } 
//		        else {
//		        
//		        	  this.buttonBuyGene.setEnabled(true);
//		        	  this.selectedGene=(GameGene) listGenes.getSelectedValue();
//		        	  this.updateSelectedGeneInfo();
//		        }
//		    }
//		 
//
//		
//	}
		
		
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
			
			
			//////////////
			//LIST://
			///////////
			

	
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
			
			
			//////////////
			//LIST://
			///////////
			
		
			
			
}
