package phases;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;


import javax.swing.*;

import phasesGUI.Phase0GUI;
import phasesGUI.PhaseSetFirstAmebaGUI;

import Components.Ameba;
import Components.Board;
import Components.ISquare;
import Components.Player;
import Components.SoupSquare;

import enums.*;
import exceptions.ExceptionColumnOutOfRange;
import exceptions.ExceptionInvalidInput;
import exceptions.ExceptionInvalidSquare;
import exceptions.ExceptionNotEmptySquare;
import exceptions.ExceptionOwnAmebaOnSquare;
import exceptions.ExceptionRowOutOfRange;
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
public class PhaseSetFirstAmeba
{
	//BASIC THINGS OF THIS GUI:
	private boolean isActive;		//if this PhaseGui is the actual gui, the "showing gui"
	private GamePhases activePhase;	//this is IMPORTANT!
	private final int nrOfAmebasToSet=1;
	

	private PhaseSetFirstAmebaGUI phaseGUI;
	
	
		
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
	public PhaseSetFirstAmeba(Game game) {
		//ARGUMENTES OF THE CONSTRUCTOR:
		this.game=game;
		this.board=game.getBoard();
		
		init(game);
		

		boolean createGUI=ReadShowGUIOrNormal.read();

		if (createGUI)
		{
			this.phaseGUI = new PhaseSetFirstAmebaGUI(this.game,this);
		}
		
		//Update/Initialize all the data for the components	//IMPORTANT!!!
			this.doUpdate();
		
		
			
		
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
			this.activePhase=GamePhases.phaseEmpty;
			this.isActive=true;
			this.actualPlayer=this.game.getActualPlayer();
			System.out.println("READ DIRECTION : "+ this.game.getReadDirection());
			
			if (this.phaseGUI!=null)
			{
				this.phaseGUI.activate();
				doUpdate();
			}
			else
			{
				System.out.println("\t PHASE Set first ameba:");
				System.out.println("You can enter: \n \t e - exit \n \t w - go to Welcome phase \n \t b - go one phase back");
				doPrintMessage();
				
				
				//doUpdate();
			}
		}
		
		public void doPrintMessage()
		{
				for (int i=0; i<this.game.getNumberOfPlayers()*nrOfAmebasToSet;i++)
				{
					System.out.println("ORDER: " + this.game.getOrderPlayers());
					
					if (i==0)
					{
						System.out.println("FIRST ROUND");
						System.out.println();
					}
					else if(i==3)
					{
						System.out.println("SECOND ROUND");
						System.out.println();
					}
					
					System.out.println("Player " + this.actualPlayer.getPlayerName() + " is on turn now.");
					
					System.out.println();
					
					this.doPrintBoard();
					
					String input=UserInput.readInput("Type in 'a' for chosing an ameba: ");
					checkInput(input);
				}
				
				
				this.doGoToPhase1();
				
			
		
		}
		
		private void doPrintBoard()
		{
			System.out.print(this.game.getBoardAsString());
		}
		
		private String doGetPossibleAmebasToSet() {
			ArrayList<Ameba> amebasOffBoard=this.actualPlayer.getAmebasOffBoard();

			String nrOfPossibleAmebas="";
			
			for (int i=0; i<amebasOffBoard.size(); i++)
			{
				nrOfPossibleAmebas=nrOfPossibleAmebas+amebasOffBoard.get(i).getNumber();
				
				if(i<amebasOffBoard.size()-1)
				{
					nrOfPossibleAmebas=nrOfPossibleAmebas+" or ";
				}
			}
			
			
			return nrOfPossibleAmebas;
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
						this.doGoBackToPreviousPhase();
						
						break;
					}
					case 'w':
					{
						this.doGoToWelcomePhase();
						
						break;
					}
					case 'e':
					{
						System.out.println("Bye...");
						this.doExit();
						
						break;
					}
					case 'a':
					{
						String possibleAmebas=this.doGetPossibleAmebasToSet();
						
						String inputAmeba=UserInput.readInput("Typ in a number ( " + possibleAmebas + " ) of an ambeba to set on board: ");
						
						checkInputForAmeba(inputAmeba);
						break;
					}
					default:
					{
						System.out.println("ERROR: Unkown instruction, please try it again...");
					}
				}
			}
		}
		
		private void checkInputForAmeba(String inputAmeba) {
			this.doChoseAmebaWithNr(Integer.parseInt(inputAmeba));
			
			boolean valid=false;
			
			int posX=-1;
			int posY=-1;
			
			while (!valid)
			{
				String inputX=UserInput.readInput("Typ in a number between 1 and 5 for the x-coordiante of the square: ");
				posX=Integer.parseInt(inputX);
				
				String inputY=UserInput.readInput("Typ in a number between 1 and 5 for the y-coordiante of the square: ");
				posY=Integer.parseInt(inputY);
				
				valid=this.doASCIICheckPossibleSquare(posX, posY); 
				
				
			}
			Ameba activeAmeba=game.getActiveAmeba();
			
			System.out.println("Your ameba with nr " +activeAmeba.getNumber() + " and damagePoints "+ activeAmeba.getDamagePoints() + "  is now on board on square [ " + posX + " | " + posY +" ]"  );
			
			this.doPrintBoard();
			
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
		updateData();
		//updateComponents();
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
	
	


	
	
	/////////////////////////////////
	//SPECIAL METHODS OF THIS PHASE://
	/////////////////////////////////


	private boolean isFirstAmebaOfPlayer(Player player)
	{
		
		
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

	public void doGoToWelcomePhase()
	{
		this.activePhase=GamePhases.phaseWelcome;
	}
	
	public void doGoToPhase1()
	{
		this.activePhase=GamePhases.phase1;
	}
	
	public void doExit()
	{
		System.out.println("Bye...");
		System.exit(0);
	}
	
	public void doGoBackToPreviousPhase()
	{
		this.activePhase=GamePhases.phase0;
	}
	
	public void doChoseAmebaWithNr(int nr)
	{
		Ameba amebaToSet=this.actualPlayer.getAmebaWithNumber(nr);
		game.setActiveAmeba(amebaToSet);
		
		
		//this.radioButtonAmeba[nr].setEnabled(false);
		
		//this.setChoseAmebaVisible(false);
		//this.setChoseSetSoupSquareVisible(true);
	}
	/**
	 * its special for ASCII. it call doCheckPossibleSquare(x,y) and catch possible Exceptions and handle them
	 */
	private boolean doASCIICheckPossibleSquare(int x, int y)
	{
		try {
			this.doCheckPossibleSquare(x, y);
			return true;
		} catch (Exception e) {
		    System.out.println(e); //TODO: CORRECT LIKE THIS?
		    return false;
		}
		
	}
	
	/**
	 * Call this method in ASCII with method doASCIICheckPossibleSquare. Call this method in GUI with method doGUICheckPossibleSquare! 
	 * @param x
	 * @param y
	 */
	public void doCheckPossibleSquare(int x, int y)
	{
			 
			
			if(this.checkInputForSquare(x,y)==true)
			{
				int inputNumberR= y; 	//Y
				int inputNumberC= x;	//X
				
				ISquare square = this.board.getSquare(inputNumberC, inputNumberR);
				
				if (!square.isSoupSquare())		
				{
					//this.buttonSetAmeba.setEnabled(false);
					throw new ExceptionInvalidSquare();
				}
				else if (!square.isEmpy())
				{
				
					throw new ExceptionNotEmptySquare();
					//this.buttonSetAmeba.setEnabled(false);
				}
	
					
					
					
					ArrayList<Ameba> amebasOnSquare= square.getAmebasOfColor(game.getActualPlayer().getColor());
					if (amebasOnSquare.size()!=0)		
					{
						throw new ExceptionOwnAmebaOnSquare();
						//this.buttonSetAmeba.setEnabled(false);
					}
					
						game.setActiveSoupSquare(square);
						
						this.doSetAmeba(x, y);
			}
			else
			{
				throw new ExceptionInvalidInput();
			}
			
			
			

		}



public void doSetAmeba(int posX, int posY)
{
//			int inputNumberR= Integer.parseInt(this.textFieldY.getText()); //Y	//TODO: DO THIS IN GUI OF THIS PHASE!
//			int inputNumberC= Integer.parseInt(this.textFieldX.getText());	//X
//			
			int inputNumberR= posY; 
			int inputNumberC= posX;		
	
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
				activeAmeba.setDamagePoints(0);	
				activeAmeba.checkIsDeath();		
				assert !activeAmeba.isDead();	
			}
			
			SoupSquare activeSoupSquare=game.getActiveSoupSquare();
			
			
			
			activeSoupSquare.addAmeba(activeAmeba);
			
			int x=activeSoupSquare.getX()+50;
			int y=activeSoupSquare.getY()+10;
			
			activeAmeba.setPositionOfAmeba(x, y);
			
		
			
			//this.setChoseAmebaVisible(true);
			//this.setChoseSetSoupSquareVisible(false);
			
			System.out.println("Ameba added successful to the other amebas on board... PLAYER " +this.actualPlayer.getPlayerName());
			
			this.actualPlayer.addAmebaToOnBoard(activeAmeba);
			
			this.game.getBoard().setAnAmebaOnBoard(activeAmeba);
		
			game.nextPlayer();		
		
		
		
		
		
		////////////////
		//UPDATE DATA://	//do this in every case!
		////////////////
		this.doUpdate();
		
	
	}

	private boolean checkInputForSquare(int x, int y) {
		
//		if (this.textFieldY.getText()==""  || this.textFieldX.getText()=="")		//TODO: DO THIS IN THE GUI OF THIS PHASE
//		{
//			JOptionPane.showMessageDialog(null, "Error: No empty inputs allowed!");
//			valid=false;
//			return valid;
//		}
		
			
			if (game.getnumbersOfColumn()<x || x<1)
			{
				
				throw new ExceptionColumnOutOfRange(this.game);
			}
			
			if (game.getnumbersOfColumn()<y || y<1)
			{
			
				 throw new ExceptionRowOutOfRange(this.game);
			}
			
			return true;
			
			
		
		
		
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

			
}
