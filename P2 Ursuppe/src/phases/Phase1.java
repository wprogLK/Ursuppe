package phases;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;


import javax.swing.*;

import phasesGUI.Phase1GUI;
import phasesGUI.PhaseSetFirstAmebaGUI;

import Components.Ameba;
import Components.Board;
import Components.Die;
import Components.ISquare;
import Components.Player;

import enums.*;
import exceptions.ExceptionErrorFood;
import exceptions.ExceptionNotEnoughFoodOnTargetSquare;
import exceptions.ExceptionTooMuchFoodChoosen;
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
public class Phase1
{
	//BASIC THINGS OF THIS GUI:
	private boolean isActive;		//if this PhaseGui is the actual gui, the "showing gui"
	private GamePhases activePhase;	//this is IMPORTANT!
	private Ameba actualAmeba;
	private int playedPlayer=0;
	private ArrayList<Ameba> amebaOfActualPlayerOnBoard=new ArrayList<Ameba>();
	
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
	
	
	private Phase1GUI phaseGUI;
	/**
	 * Constructor
	 */
	public Phase1(Game game) {
		//ARGUMENTES OF THE CONSTRUCTOR:
		this.game=game;
		this.board=game.getBoard();
		
		init(game);
		
		

		boolean createGUI=ReadShowGUIOrNormal.read();

		if (createGUI)
		{
			this.phaseGUI = new Phase1GUI(this.game,this);
		}
		
			
			//this.updateData();
			
		//CREATE ALL PANELS AND COMPONENTES:
			
			
			
		
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
	
//		private void setEnabledOwnTextField()
//		{
//			switch(this.game.getActualPlayer().getColor())
//			{
//			case blue:
//			{
//				disableBlue();
//				
//				break;
//			}
//			case red:
//			{
//				disableRed();
//				
//				break;
//			}
//			case yellow:
//			{
//				disableYellow();
//				
//				break;
//			}
//			default:
//			{
//				System.out.println("Error in Phase1.class: unkown case in method setEnabledOwnTextField()!");
//				//TODO
//				break;
//			}
//			}
//		}

	
	
	
	
	
	
	///////////////////////////////////
	////////ACTIVATE/DEACTIVATE:///////
	///////////////////////////////////
	//set this GUI to the active GUI (call by GUILOGIC)
		public void activate()
		{
			this.activePhase=GamePhases.phaseEmpty;
			this.isActive=true;
			
			this.loadAmebasInList();
			this.nextAmebaInList();
			
			if (this.phaseGUI!=null)
			{
				this.phaseGUI.activate();
				doUpdate();
			}
			else
			{
				System.out.println("\t Phase 1: Drift/Wriggle and Eat/Shit");
				System.out.println("READDIRECTION: " + this.game.getReadDirection());
				System.out.println("ORDER OF PLAYERS" + this.game.getOrderPlayers());
				doPrintMessage();
				
				
				doUpdate();
			}
		}
		
		public void doPrintMessage()
		{
				System.out.println("You can enter: \t e - exit \n \t w - go to Welcome phase \n \t b - go one phase back");
				
				
				
				while(this.playedPlayer<this.game.getNumberOfPlayers())
				{
					checkIsNextPlayerOnTurn();
					System.out.println("Player " + this.actualPlayer.getPlayerName() + " is on turn now: ");
					
					int count=1;
					
					while(count<=this.actualPlayer.getAmebasOnBoard().size())
					{
						System.out.println("The current ambea " + this.actualAmeba.getColor() +" is ameba nr. " + this.actualAmeba.getNumber() + ": ");
						String str="Enter 'd' to let drift ";
						
						if (this.actualPlayer.getBioPoints()-this.costOfMove>=0)
						{
							str=str+" or 'w' to try wriggle (costs " + this.costOfMove + " bioPoint(s) )";
						}
						else
						{
							str=str + " you can't wriggle because you haven't enough bioPoints";
						}
						
						String input=UserInput.readInput(str);
						
						checkInput(input);
						count++;
					}
					
					System.out.println("READDIRECTION 2: " + this.game.getReadDirection());
					System.out.println("ORDER OF PLAYERS 2" + this.game.getOrderPlayers());
				}
				
				this.checkFinishPhase1();
				//this.doUpdate();
				//this.doGoToPhase2();
				
				
				
		
		}
		
		public void checkInput(String input)
		{
			if (input.length()==1)
			{
				char a=input.charAt(0);
				
				switch (a)
				{
					case 'd':
					{
						System.out.println("DRIFT....");
						this.dialogueSetFoodToEat();
						
						break;
					}
					case 'w':
					{
						System.out.println("WRIGGLE....");
						input=" ";
						
						while(input.charAt(0)!='r')
						{
							if (input.charAt(0)!=' ')
							{
								System.out.println("Invalid input... try again...");
								
								input=UserInput.readInput("Roll the die with 'r'");
							}
							input=UserInput.readInput("Roll the die with 'r'");
							
						}
						
						GameDirection direction= this.doRollDie();
						
						
						
						if(direction==GameDirection.Free)
						{
							System.out.println("You had luck and rolled a 6! You can chose now your direction:");
							
				
							input=UserInput.readInput("\n - 'n' North \n - 'e' East \n - 's' South \n - 'w' West \n - 'm' Middle \n What do you want: ");

							if (input.length()>1)
							{
								System.out.println("Your input was too long! Try it again...");
							}
							else
							{
								switch(input.charAt(0))
								{
								case 'n':
								{
									this.doGoUp();
									break;
								}
								case 'e':
								{
									this.doGoRight();
									break;
								}
								case 's':
								{
									this.doGoDown();
									break;
								}
								case 'w':
								{
									this.doGoLeft();
									break;
								}
								case 'm':
								{
									this.doGoMiddle();
									break;
								}
								default:
								{
									System.out.println("Error: Invalid input! Try it again...");
								}
								}
								this.dialogueSetFoodToEat();

							}
						}
						else
						{
							System.out.println("You rolled a " + direction.getDieValue() +"! Your current ameba drift now in : "+ direction);
							this.actualAmeba.setDirection(direction);
							this.dialogueSetFoodToEat();
							
						}
						
						
						break;
					}
					case 'b':
					{
						this.doGoToWelcomePhase();
						
						break;
					}
					case 'e':
					{
						System.out.println("bye...");
						System.exit(0);
						
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
		
		
		private void dialogueSetFoodToEat() {
			System.out.println("Set now how many food you want to eat on the target square (you have to at totally 3 foods): ");
			ISquare squareToGoTo=this.board.getSquareMoveTo(this.actualAmeba);
			
			
			
			if (squareToGoTo.isSoupSquare())
			{
				int nrOfFoodBlueOnTarget=squareToGoTo.getNrOfFood(GameColor.blue);
				int nrOfFoodRedOnTarget=squareToGoTo.getNrOfFood(GameColor.red);
				int nrOfFoodYellowOnTarget=squareToGoTo.getNrOfFood(GameColor.yellow);
				System.out.println("On the targetSquare are: \n " + nrOfFoodBlueOnTarget + " blue food \n " + nrOfFoodRedOnTarget + " red food \n " + nrOfFoodYellowOnTarget + " yellow food");
			}
			else
			{
				System.out.println("You will stay on the current square, because in this direction is no other square!");
				
				Point2D point=this.actualAmeba.getSquarePosition();
				ISquare squareOnIt=this.game.getBoard().getSquare((int) point.getX(), (int) point.getX());
				
				int nrOfFoodBlueOnTarget=squareOnIt.getNrOfFood(GameColor.blue);
				int nrOfFoodRedOnTarget=squareOnIt.getNrOfFood(GameColor.red);
				int nrOfFoodYellowOnTarget=squareOnIt.getNrOfFood(GameColor.yellow);
				System.out.println("On the targetSquare are: \n " + nrOfFoodBlueOnTarget + " blue food \n " + nrOfFoodRedOnTarget + " red food \n " + nrOfFoodYellowOnTarget + " yellow food");
				
				
			}
			
			int foodNrBlue=0;
			int foodNrRed=0;
			int foodNrYellow=0;
			
			switch(this.actualPlayer.getColor())
			{
				case blue:
				{
					foodNrRed=startSetFood("red");
					foodNrYellow=startSetFood("yellow");
					break;
				}
				case red:
				{
					foodNrBlue=startSetFood("blue");
					foodNrYellow=startSetFood("yellow");
					break;
				}
				case yellow:
				{
					foodNrBlue=startSetFood("blue");
					foodNrRed=startSetFood("red");
					break;
				}
				default:
				{
					System.out.println("Error: Unkown case in dialogueSetFoodToEat in phase1");
					//TODO
				}
			}
			
			try
			{
				this.doSetFoodToEat(foodNrBlue,foodNrRed,foodNrYellow);
			}
			catch(ExceptionTooMuchFoodChoosen e)
			{
				System.out.println(e);
				this.dialogueSetFoodToEat();
			}
			
			System.out.println("BOARD AFTER EATING:");
			this.doPrintBoard();
		}
		
		private int startSetFood(String color)
		{
			String input=UserInput.readInput("Please enter a number between 0 and 2 for eating "+color+" food: ");
			return Integer.parseInt(input);
			
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
//		this.updateComponents();
	}
	
	private void updateData()
	{
		//this.checkIsNextPlayerOnTurn();
		if (this.playedPlayer<=this.game.getNumberOfPlayers())
		{
			//System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEeeeeeeeeee");
			
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
	
	

	

private void checkFinishPhase1() {
		if (this.game.getActualPlayer().equals(this.game.getEmptyPlayer()))
		{
			System.out.println("GO TO PHASE 2 (CHECK FINISH PHASE1)");
			this.doGoToPhase2();
		}
		
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
		
		sumOfFoodsOnTargetSquare = calcSumOfFoodOnTargetSquare(blueFoodOnTargetSquare, redFoodOnTargetSquare,yellowFoodOnTargetSquare, sumOfFoodsOnTargetSquare);
		
		
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
			//this.nextAmebaInList();
			
			this.actualAmeba.setNrOfEatFoodOfColor(GameColor.blue, blueFood);
			this.actualAmeba.setNrOfEatFoodOfColor(GameColor.red, redFood);
			this.actualAmeba.setNrOfEatFoodOfColor(GameColor.yellow, yellowFood);
			
			//TODO AMEBA IS HUNGRY
			
			//Move ameba:
			System.out.println("ACTUAL AMEBA MOVE:  " + this.actualAmeba);
			this.board.moveAnAmeba(this.actualAmeba);
			
			
			this.amebaOfActualPlayerOnBoard.remove(0);	
			//System.out.println("LIST AFTER DRIFT: " + this.amebaOfActualPlayerOnBoard);

		}
		else if(notEnoughFoodChoose)
		{
			throw new ExceptionNotEnoughFoodOnTargetSquare();
			//JOptionPane.showMessageDialog(null, "Please, your ameba would like to eat " + this.actualPlayer.getNrOfFoodsToEat() + " foods! You had not enough!");
		}
		else if(tooMuchFoodChoose)
		{
			throw new ExceptionTooMuchFoodChoosen();
			//JOptionPane.showMessageDialog(null, "Your ameba will be happy if it can eat " + this.actualPlayer.getNrOfFoodsToEat() + " foods! You have too many!");
		}
		else
		{	
			throw new ExceptionErrorFood(errorMessage);
			//JOptionPane.showMessageDialog(null, errorMessage);
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
			this.actualPlayer=this.game.getActualPlayer();
			this.costOfMove=this.actualPlayer.getCostOfMoveAnAmeba();
			
			this.loadAmebasInList();
			
			//this.buttonGoToPhase2.setEnabled(true);
			this.playedPlayer++;
		}
		
		
		
	}
	
	
	
	private void nextAmebaInList()
	{
		System.out.println("Size of amebaLIST: " +this.amebaOfActualPlayerOnBoard.size() + " of Player " + this.actualPlayer.getPlayerName());
		
		assert !this.amebaOfActualPlayerOnBoard.isEmpty();		//BUG! Sometimes size==0
		this.actualAmeba=this.amebaOfActualPlayerOnBoard.get(0); //is needed otherwise "wrong" ameba try to eat
		//this.amebaOfActualPlayerOnBoard.remove(0);				//TODO: do this after move the ameba!
	}
	
	private void loadAmebasInList()
	{
		
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
	
	}
	
	
	
	
	///////////
	//ACTIONS://
	////////////
	public void doGoToWelcomePhase()
	{
		this.activePhase=GamePhases.phaseWelcome;
	}
	
	public void doGoToPhase2()
	{
		this.activePhase=GamePhases.phase2;
	}
	
	public void doExit()
	{
		System.out.println("Bye...");
		System.exit(0);
	}
	
	public void doGoBack()
	{
		this.activePhase=GamePhases.phaseSetFirstAmeba;
	}
	
	
	public void doASCIIShowDrift()
	{
		//TODO
		
		this.doUpdate();
	}
	
	public void doASCIIMove()
	{
		//TODO
		
		this.doUpdate();
	}
	
	/**
	 * 
	 * @return gameDirection
	 */
	public GameDirection doRollDie()
	{
		this.actualPlayer.subBioPoints(this.costOfMove);
		int faceValue=this.die.roll();
		GameDirection direction=GameDirection.Free;
		
		//direction=direction.getGameDirection(faceValue);
		
		return direction;
			
		
	}

	public void doGoUp()
	{
		this.actualAmeba.setDirection(GameDirection.North);
		//this.board.setSingleWindDirection(GameDirection.North);		//TODO MAY BE DELETE THIS LINE?!
		//showDialogue4();
		
		this.doUpdate();
	}
	
	public void doGoLeft()
	{
		this.actualAmeba.setDirection(GameDirection.West);

		//showDialogue4();
		
		this.doUpdate();
	}
	
	public void doGoDown()
	{
		this.actualAmeba.setDirection(GameDirection.South);

		//showDialogue4();
		
		this.doUpdate();
	}
	
	public void doGoRight()
	{
		this.actualAmeba.setDirection(GameDirection.East);
			
		//showDialogue4();
		
		this.doUpdate();
	}
	
	public void doGoMiddle()
	{
		this.actualAmeba.setDirection(GameDirection.Middle);
		//showDialogue4();
		
		this.doUpdate();
	}

	private void doPrintBoard()
	{
		System.out.println(this.game.getInformationAboutPlayers());
		
		System.out.println();
		System.out.println();
		
		System.out.println(this.game.getBoardAsString());
	}
	
	public void doSetFoodToEat(int blueFoodToEat, int redFoodToEat, int yellowFoodToEat)
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
				check=blueFoodToEat==0;
				break;
			}
			case red:
			{
				check=redFoodToEat==0;

				break;
			}
			case yellow:
			{
				check=yellowFoodToEat==0;
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
			System.out.println("ERROR in PHASE 1: A input is not=0!" + color);
			System.exit(1);
		}
		
	
	
		this.checkValidNrOfFoods(blueFoodToEat, redFoodToEat, yellowFoodToEat);
		
		this.checkIsNextPlayerOnTurn();
		this.nextAmebaInList();
		
		
		this.doUpdate();

	}
		
			
		
		
	
	

	private void showEatDialogue(GameDirection direction) {
		//System.out.println("Should move in :"+ direction);
		//this.board.setSingleWindDirection(direction);
		this.actualAmeba.setDirection(direction);
		
		//showDialogue4();
		
		this.die.setVisible(true);
	}

	private void showFreeMoveDialogue() {
		//System.out.println("FREE MOVE!");
		
//		this.setDialoge1(false);
//		this.setDialoge2(false);
//		this.setDialoge3(true);
//		this.setDialoge4(false);
	}

	private void showMoveDialogue() {
		//IMPLEMENTED HERE GENE STROMLINIENFORM! PART 2:
		if(this.actualPlayer.getGenes().contains(GameGene.Stromlienenform))
		{
			//this.buttonMove.setEnabled(true);
			System.out.println("You have gene Stromlinienform! You haven't pay bioPoints here!");
		}
		else
		{
			this.actualPlayer.subBioPoints(this.costOfMove);
		}

		
		
//		this.setDialoge1(false);
//		this.setDialoge2(true);
//		this.setDialoge3(false);
//		this.setDialoge4(false);
	}

	private void showDriftDialogue() {
		this.actualAmeba.setDirection(this.board.getCompassSquare().getWindDirection());
		
//		showDialogue4();
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
