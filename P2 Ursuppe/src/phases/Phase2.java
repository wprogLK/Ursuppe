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
import phasesGUI.PhaseWelcomeGUI;

import Components.Board;
import Components.Die;
import Components.Player;

import enums.*;
import exceptions.ExceptionInvalidBioPoints;
import exceptions.ExceptionNotEnoughBioPoints;
import exceptions.ExceptionTooHighOzoneValue;
import game.*;
import helpClasses.Calc;
import helpClasses.ReadShowGUIOrNormal;
import helpClasses.UserInput;




/**
 * @author lukas
 *
 */
public class Phase2
{
	
	//BASIC THINGS OF THIS GUI:
	private boolean isActive;		//if this PhaseGui is the actual gui, the "showing gui"
	private GamePhases activePhase;	//this is IMPORTANT!
	private int nrOfPlayerPlayed=1;
	private ArrayList<GameGene> selectedGenes=new ArrayList<GameGene>();
	private int differenceToPay=0;
	private ArrayList<Integer> possiblesNrOfGenesToSell=new ArrayList<Integer>();
	
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
	
	private Phase2GUI phaseGUI;
	
	/**
	 * Constructor
	 */
	public Phase2(Game game) {
		//ARGUMENTES OF THE CONSTRUCTOR:
		this.game=game;
		this.board=game.getBoard();
		
		init(game);
	
		boolean createGUI=ReadShowGUIOrNormal.read();
		
		if (createGUI)
		{
			this.phaseGUI = new Phase2GUI(this.game,this);
		}
		
			
			//this.updateData();
			
		//CREATE ALL PANELS AND COMPONENTES:
			
	
		
			
		
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

	private void updateGame()
	{
		
		this.game.getNextOzonValue();		//Only in phase2!
		this.game.getNextWindDirection();	//Only in phase2!
		
		this.differenceToPay=this.actualPlayer.getSumOfAllGenesOzoneValue()- this.game.getOzoneValue(); //RESET
		//System.out.println("difference to pay" +this.differenceToPay);
		if (this.differenceToPay<0)
		{
			this.differenceToPay=0;
		}
	}
	
	public void activate()
	{
//		System.out.println("READDIRECTION ACTIVE PHASE2: " + this.game.getReadDirection());
//		System.out.println("ORDER OF PLAYERS ACTIVE PHASE2" + this.game.getOrderPlayers());
		
		this.actualPlayer=this.game.getActualPlayer();
//		System.out.println("the current plazer  in phase 2 is " + this.actualPlayer.getPlayerName());
		
		this.activePhase=GamePhases.phaseEmpty;
		this.isActive=true;
		
		
		if (this.phaseGUI!=null)
		{
			this.phaseGUI.activate();
			this.doUpdate();
			this.updateGame();
			
		}
		else
		{
			System.out.println("\t PHASE 2");
			
			doPrintMessage();
			this.doUpdate();
			this.updateGame();
			
			
			
			
		}
		
		//this.doUpdate();
	}
	
	public void doPrintMessage()
	{
	
		
		System.out.println("You can enter: \t e - exit \n \t w - go to Welcome phase \n \t b - go one phase back");
		
		System.out.println();
		System.out.println(this.game.getInformationAboutPlayers());
		System.out.println();
		
		
		int playerPlayed=1;
		
		//while(playerPlayed<=this.game.getNumberOfPlayers())
		for(int i=0; i<this.game.getNumberOfPlayers();i++)
		{
			System.out.println("Player " + this.actualPlayer.getPlayerName() + " is on turn now:");
			
			this.actualPlayer.resetPayedDifference();
			
			///this.isOzoneValueOfPlayerOK();
			this.updateDifference();
			if (this.differenceToPay>0)
			{
				while (this.differenceToPay>0)
				{
					String possiblities="\n ";
					
					boolean possibleWithBioPoints=false;
					boolean possibleWithGenes=false;
					
					if (this.actualPlayer.getBioPoints()>0)
					{
						possibleWithBioPoints=true;
						possiblities=possiblities+ "- 'p' with bioPoints \n";
					}
					if(this.actualPlayer.getGenes().size()>0)
					{
						possibleWithGenes=true;
						possiblities=possiblities+ "- 'g' with genes \n";
					}
					
					String input=UserInput.readInput("You have too high ozone value! You have to pay " + Math.abs(this.differenceToPay) + " points! \n How do you want to pay? \n " + possiblities) ;
					checkInputDecideToPayBioPointsOrGenes(input,possibleWithBioPoints , possibleWithGenes);
				}
				
			}
			else
			{
				if (this.actualPlayer.getGenes().size()>0)
				{
					this.showDialogeSellGenes();
				}
				else
				{
					this.showDialogueAccept();
				}
			
				
			}
			
			this.doUpdate();
			
			
			playerPlayed++;
		}
		this.doUpdate();
			
			
			
	
	}
	public void showDialogueAccept()
	{
		String input=UserInput.readInput("You must not pay and you can't sell a gene, because you haven^'t got any gene. Please press enter to skip you....");
		
	}
	public void showDialogeSellGenes()
	{
		String input=UserInput.readInput("You must not pay. Everything is ok. Do you want sell a gene? \n -'y' yes \n / 'n' no");
		
		
		if (input.charAt(0)=='y')
		{
			this.showDialoguePayGenes(false);
		}
		else if(input.charAt(0)=='n')
		{
			//do nothing
		}
		else
		{
			System.out.println("Invalid input! Try it again ...");
			this.showDialogeSellGenes();
		}
		
	}
	
	public void checkInputDecideToPayBioPointsOrGenes(String input, boolean withBioPoints, boolean withGenes)
	{
		if (input.length()==1)
		{
			char a=input.charAt(0);
			
			switch (a)
			{
				case 'p':
				{
					if (withBioPoints)
					{
						System.out.println("You want to pay with points...");
						this.showDialoguePayBioPoints();
					}
					else
					{
						System.out.println("You can't pay with bioPoints! You have got " + this.actualPlayer.getBioPoints()+" bioPoints");
					}
					break;
				}
				case 'g':
				{
					if (withGenes)
					{
						System.out.println("You want to pay with genes...");
						this.showDialoguePayGenes(true);
					}
					else
					{
						System.out.println("You can't pay with genes! You haven't any gene!");
					}
					break;
				}
				default:
				{
					System.out.println("ERROR: Unkown instruction, please try it again...");
					this.doPrintMessage();
				}
			}
		}	
	}
	
	
	public void showDialoguePayBioPoints()
	{
		int pointsToSell=-1;
		int count=0;
		while(pointsToSell<0 && pointsToSell<this.actualPlayer.getBioPoints())
		{
			if (count!=0)
			{
				System.out.println("Error: invalid input of bioPoints please try it again...");
			}
			count++;
			String input=UserInput.readInput("How many bioPoints do you sell? \n Enter a number between 0 and " + this.actualPlayer.getBioPoints());
			
			pointsToSell=Integer.parseInt(input);
		}
		
		this.doSell(pointsToSell);
		
		
	}
	
	public void showDialoguePayGenes(boolean forced)
	{
		System.out.println("You have following genes: \n");
		
		int i=printPossibleGenesToSell();
		
		
		boolean more=true;
		
		int costs=0;
		int ozone=0;
		
		this.selectedGenes.clear();
		
		while(more)
		{
			String input=UserInput.readInput("Please enter the number (0 - " + (i-1) +"  of the gene which to you want to sell: ");
			
			if (!this.possiblesNrOfGenesToSell.contains(Integer.parseInt(input)))
			{
				System.out.println("Error: This gene is not available");
				this.showDialoguePayGenes(forced);
			}
			
			System.out.println("Gene nr. " + input + " was added to the genes to sell...");
			
			int index=Integer.parseInt(input);
			
			GameGene geneToSell=this.actualPlayer.getGenes().get(index);
			this.selectedGenes.add(geneToSell);
			
			this.possiblesNrOfGenesToSell.remove(index);
			
			costs=costs+geneToSell.getPrice();
			ozone=ozone+geneToSell.getOzoneValue();
			
			//this.actualPlayer.getGenes().remove(input);
			
			if (this.possiblesNrOfGenesToSell.size()>0)
			{
				input=UserInput.readInput("Do you want to sell more genes? 'y' yes \n 'n' no ");
				
				if(input.charAt(0)=='y')
				{
						more=true;
				}
				else if(input.charAt(0)=='n')
				{
					more=false;
				}
			}
			else
			{
				System.out.println("You sold old genes!");
				more=false;
			}
			
			
		
			
		}
		
		this.printGenesToSell();
		
		this.doSell(0);
		
		System.out.println("You get " + costs +" bioPoint(s)");
		this.updateDifference();
		
		System.out.println("You have now a difference of " + this.differenceToPay);

	}
	
	private void printGenesToSell()
	{
		System.out.println("You will sell now following genes: ");
		
	
		
		for (int i=0; i<this.selectedGenes.size(); i++ )
		{
			System.out.println("- " + this.selectedGenes.get(i));
		}
		
	}
	
	private int printPossibleGenesToSell()
	{
		int i=0;
		
		this.possiblesNrOfGenesToSell.clear();
		
		for (i=0; i<this.actualPlayer.getGenes().size();i++)
		{
			if(!this.selectedGenes.contains(this.actualPlayer.getGenes().get(i)))
			{
				System.out.println("( " + i + ")" + this.actualPlayer.getGenes().get(i));
				possiblesNrOfGenesToSell.add(i);
			}
		}
		
		return i;
	}
	
	public void checkInput(String input)
	{
		if (input.length()==1)
		{
			char a=input.charAt(0);
			
			switch (a)
			{
				case 'w':
				{
					this.doGoToWelcome();
					
					break;
				}
				case 'b':
				{
					this.doGoOnePhaseBack();
					
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
					this.doPrintMessage();
				}
			}
		}
	}
	
	public void deactivate()
	{
		this.isActive=false;
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
		this.isOzoneValueOfPlayerOK();
		this.game.skipEmptyPlayer();
		
		assert !game.getActualPlayer().equals(this.game.getEmptyPlayer());
		
		
		///////////////////////////
		//UPDATE CLASS VARIABLES://
		///////////////////////////
		
		
		
		this.actualPlayer=this.game.getActualPlayer();
		this.costOfMove=this.actualPlayer.getCostOfMoveAnAmeba();
	}
	

	private void updateDifference()
	{
		this.differenceToPay=this.actualPlayer.getSumOfAllGenesOzoneValue()- this.game.getOzoneValue()-this.actualPlayer.getPayedDifference(); //RESET
		System.out.println("UPDATE DIFFERENCE: NEW DIFF= " + this.differenceToPay + " player " +this.actualPlayer.getPlayerName() + " already payed: " +this.actualPlayer.getPayedDifference());
	}

	


	
	private void isOzoneValueOfPlayerOK()
	{
		this.updateDifference();
		System.out.println("difference to pay" +this.differenceToPay);
		if (this.differenceToPay<0)
		{
			this.differenceToPay=0;
		}
		
		if (this.differenceToPay<=0  || this.actualPlayer.getSumOfAllGenesOzoneValue()<=0)
		{
			this.doNextPlayer();
			//this.labelWarning.setText("Your ozone value is ok. It is "+ this.actualPlayer.getSumOfAllGenesOzoneValue() + " You can sell some genes if you want, more than one gene can be selected at once! ");
			
			
		}
		else
		{
			throw new ExceptionTooHighOzoneValue("Your ozone value is to high! Sell genes and/or sell bioPoints. You have a sum of ozoneValue of "+ this.actualPlayer.getSumOfAllGenesOzoneValue() + " and the ozone layer on Board is "+ this.game.getOzoneValue() + " the difference which you must pay is: " + this.differenceToPay);
			
			
		}
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
	public void doGoToWelcome()
	{
		this.activePhase=GamePhases.phaseWelcome;
	}
	
	public void doGoToPhase3()
	{
		this.activePhase=GamePhases.phase3;
	}
	
	public void doExit()
	{
		System.out.println("bye...");
		System.exit(0);
	}
	
	public void doGoOnePhaseBack()
	{
		this.activePhase=GamePhases.phase1;
	}
	
	public void doSell(int nrOfBioPointsToSell)
	{
		if (this.checkInput(nrOfBioPointsToSell))
		{
			
			//System.out.println("Genes to sell: "+this.selectedGenes);
			
			for (int i=0; i<this.selectedGenes.size();i++)
			{
				GameGene gene=this.selectedGenes.get(i);
				
				gene.sellGene();
				this.actualPlayer.removeGene(gene);
				
				if (this.differenceToPay<=0) //If a player only want to sell genes from other reasons...
				{
					this.actualPlayer.addBioPoints(gene.getPrice());
					System.out.println("PLAYER WANTED TO SELL GENES (1)");
					this.actualPlayer.addPayedDifference(gene.getOzoneValue());
				}
				else
				{
					//no adding bioPoints!
					
					this.actualPlayer.addPayedDifference(gene.getPrice());
				}
			}
			
			if (this.differenceToPay<=0) //If a player only want to sell genes from other reasons...
			{
				//do nothing
				System.out.println("PLAYER WANTED TO SELL GENES (2)");
			}
			else
			{
				this.actualPlayer.addPayedDifference(nrOfBioPointsToSell);
				this.actualPlayer.subBioPoints(nrOfBioPointsToSell);
				this.differenceToPay-=nrOfBioPointsToSell;
				System.out.println("PLAYER WAS FORCED TO PAY");
				this.updateDifference();
			}
			
			//this.isOzoneValueOfPlayerOK();
		}
		else
		{
			System.out.println("check output=false!");
		}
		//this.doUpdate();
	}
	
	public void doNextPlayer()
	{
		this.differenceToPay=this.actualPlayer.getSumOfAllGenesOzoneValue()- this.game.getOzoneValue(); //RESET
		//System.out.println("difference to pay" +this.differenceToPay);
		if (this.differenceToPay<0)
		{
			this.differenceToPay=0;
		}
		
	
		if (this.nrOfPlayerPlayed==this.game.getNumberOfPlayers())
		{
			this.game.repaireOrderOfPlayers(); 
			
			System.out.println("GO TO PHASE 3");
			System.out.println("ORDER PHASE 2" + this.game.getOrderPlayers());
			System.out.println("CURRENT PLAYER P2" + this.game.getActualPlayer().getPlayerName());
			
			this.doGoToPhase3();
		}
		else
		{
			this.game.nextPlayer();
			this.actualPlayer=this.game.getActualPlayer();
			
			System.out.println(nrOfPlayerPlayed + " Player " + this.actualPlayer.getColor() + " playes now...");
			this.nrOfPlayerPlayed++;
		} 
		//this.doUpdate(); //sonst vielleicht endlosschleife
	}	
	


	private boolean checkInput(int nrOfBioPointsToSell) 
	{
		boolean valid=true;
//		if (this.textFieldNrOfBioPointsToSell.getText()==""  )		//TO this in the GUI
//		{
//			JOptionPane.showMessageDialog(null, "Error: No empty inputs allowed!");
//			valid=false;
//			return valid;
//		}
		
			
			if (nrOfBioPointsToSell>this.actualPlayer.getBioPoints())
			{
				valid =false;
				throw new ExceptionNotEnoughBioPoints("Error: you wanted to sell " + nrOfBioPointsToSell + " but you have only "+ this.actualPlayer.getBioPoints() +"!");
			}
			
			if(nrOfBioPointsToSell<0)
			{
				 valid=false;
				 throw new ExceptionInvalidBioPoints("Error: the number of bioPoint must be 0 or greater ! You wanted to sell " + nrOfBioPointsToSell);
			}

			return valid;
		
		
		
	}
	
	
//	public void valueChanged(ListSelectionEvent e) { //TODO: TO this in the gui
//		 if (e.getValueIsAdjusting() == false) {
//
//			 
//			 
//		        if (this.listGenesToSell.getSelectedIndex() == -1) {
//		      
//		            ///this.buttonSell.setEnabled(false);
//		            
//		            this.selectedGenes=new ArrayList<GameGene>() ;
//		        } 
//		        else {
//		        
//		        	  this.buttonSell.setEnabled(true);
//		        	 // this.selectedGenes.add((GameGene) listGenesToSell.getSelectedValue());
//		        	 
//				
//		        	 this.tmpSelectedGenes= this.listGenesToSell.getSelectedValues();
//		        	 
//		        	 this.selectedGenes=new ArrayList<GameGene>();	//clear the arrayList
//		        	 
//		        	 for (int i=0;i<this.tmpSelectedGenes.length;i++)
//		        	 {
//		        		 System.out.println("tmpSelectedGenes : [ " + i + " ]" + tmpSelectedGenes[i]);
//		        		 GameGene gene=(GameGene) tmpSelectedGenes[i];
//		        		 
//		        		 this.selectedGenes.add(gene);
//		        	 }
//		        	 
//		        	
//		        }
//		        
//		        System.out.println("All SelectedGenes : "+ this.selectedGenes);
//		    }
//		 
//			//this.updateData();
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
