package logics;

import java.util.ArrayList;
import java.util.Arrays;

import enums.EPhases;
import templates.PhaseTemplateLogic;
import interfaces.IPhase;
import interfaces.IPlayer;

/**
 * 
 * @author Lukas Keller
 * @version 1.0.0
 * 
 * @see IPhase
 */
public abstract class PhasePreparation1Logic_BACKUP extends PhaseTemplateLogic
{
	//////////
	//BASICS//
	//////////
	
	private ArrayList<Triple> triples=new ArrayList<Triple>();
	private ArrayList<IPlayer> playerOrderToPlay=new ArrayList<IPlayer>();
	private ArrayList<Triple> triplesOrderToPlay=new ArrayList<Triple>();
	private ArrayList<Triple> triplesToPlayAgain=new ArrayList<Triple>();
	
	private ArrayList<IPlayer> originalPlayersOrder=new ArrayList<IPlayer>();
	
	private ArrayList<ArrayListPlayAgain<IPlayer>> listPlayAgain=new ArrayList<ArrayListPlayAgain<IPlayer>>();
	
	private boolean rolledSameValue=false;
	//////////
	//INPUTS//
	//////////

		////////////
		//...LOGIC//
		////////////
		@Override
		public final void changeActionToRun()
		{
			this.activateActionA();
		}
		
	//////////
	//BASICS//
	//////////
	@Override
	protected void setCurrentPhase()
	{
		this.currentPhase=EPhases.phasePreparation1;
	}
	
	@Override
	public final void doLogicPreAction()
	{
	}
	
	@Override
	public final void doLogicAfterAction()
	{
		if(!this.game.nextPlayer())	//if round is NOT over...
		{
			this.turnOnRestart();
			System.out.println("CURRENT PLAYER IS: " + this.game.getCurrentPlayer().getName());
			System.out.println("RESTART ON");
		}
		else						//if round is over...
		{
			this.turnOffRestart();
			this.triplesOrderToPlay=  this.sortTriples(this.triples);
			System.out.println("RESTART OFF");
			
			this.printTripleOrderToPlay();
			
			this.preparePlayerToPlayAgain();
		
		}
		
	
	
	}
	
	private void preparePlayerToPlayAgain() 
	{
		if(!this.triplesToPlayAgain.isEmpty())
		{
			ArrayList<IPlayer> playersToPlayAgain=new ArrayList<IPlayer>();
			
			for(Triple triple:this.triplesToPlayAgain)
			{
				
			}
		}
	}

	private void printTripleOrderToPlay() 
	{
		System.out.println("triples READY to play:");
		
		for(Triple triple:this.triplesOrderToPlay)
		{
			System.out.println(triple.toString());
		}
		
		System.out.println("triples NOT READY to play:");
		
		System.out.println("listPlayAgain is empty? " + this.listPlayAgain.isEmpty());
		
		for(ArrayListPlayAgain<IPlayer> currentList:this.listPlayAgain)
		{
			System.out.println("DIE VALUE " + currentList.getDieValue() + "  COMPARE VALUE " + currentList.getCompareValue());
			
			for(IPlayer player:currentList)
			{
				System.out.println("Player: " + player.getName());
			}
		}
		
			
	}

	
	private ArrayList<Triple> sortTriples(ArrayList<Triple> unsortedTriples)
	{
		ArrayList<Triple> sortedTriples=new ArrayList<Triple>();
		
		while(!unsortedTriples.isEmpty())
		{
			System.out.println("SIZE OF UNSORTED TRIPLES IS: " + unsortedTriples.size());
			
			Triple currentTriple=unsortedTriples.get(0);
			
			for(Triple comparingTriple:unsortedTriples)
			{
				System.out.println("COMPARE...");
				
				//checking if there is a better triple than the current one...
				
				if(comparingTriple.getComapareValue()>currentTriple.getComapareValue())
				{
					System.out.println("Triple " + comparingTriple +" is better than " + currentTriple);
					currentTriple=comparingTriple;
				}
				
				//checking if there is a triple with the same value...
				else if(comparingTriple.getComapareValue()==currentTriple.getComapareValue())
				{
					if(!comparingTriple.equals(currentTriple))
					{
						System.out.println("Triple " + comparingTriple +" is equal to " + currentTriple +"!");

						//check if listPlayAgain is not empty
						ArrayListPlayAgain<IPlayer> arrayListRollAgain;
						
						if(this.listPlayAgain.isEmpty())	//if empty, create a new ArrayListPlayAgain
						{
							System.out.println("listPlayAgain WAS EMPTY => create a new item");
							arrayListRollAgain=new ArrayListPlayAgain<IPlayer>(currentTriple.getDieValue(),currentTriple.getComapareValue());
							
							this.listPlayAgain.add(arrayListRollAgain);
							
							//add players:
							System.out.println("ADD PLAYERS (A): TO THE ITEM:");
							IPlayer currentPlayer=currentTriple.getPlayer();
							IPlayer comparePlayer=comparingTriple.getPlayer();
							
							if(!arrayListRollAgain.contains(currentPlayer))
							{
								arrayListRollAgain.add(currentPlayer);
							}
							if(!arrayListRollAgain.contains(comparePlayer))
							{
								arrayListRollAgain.add(comparePlayer);
							}
						}
						
						else								//check if a arrayList in listPlayAgain already exists with the values
						{
							System.out.println("listPlayAgain WAS NOT EMPTY");
							
							//check if a same ArrayListPlayAgain with the correct values already exists
							boolean alreadyExist=false;
							
							for(ArrayListPlayAgain<IPlayer> arrayListPlayAgainToCheck:this.listPlayAgain)
							{
								boolean sameCompareValue=arrayListPlayAgainToCheck.getCompareValue()==currentTriple.getComapareValue();
								boolean sameDieValue=arrayListPlayAgainToCheck.getDieValue()==currentTriple.getDieValue();
								
								if(sameCompareValue && sameDieValue)
								{
									System.out.println("found a item with the same values...");
									
									alreadyExist=true;
									arrayListRollAgain=arrayListPlayAgainToCheck;
									
									//add players:
									System.out.println("ADD PLAYERS (B): TO THE ITEM:");
									IPlayer currentPlayer=currentTriple.getPlayer();
									IPlayer comparePlayer=comparingTriple.getPlayer();
									
									if(!arrayListRollAgain.contains(currentPlayer))
									{
										arrayListRollAgain.add(currentPlayer);
									}
									if(!arrayListRollAgain.contains(comparePlayer))
									{
										arrayListRollAgain.add(comparePlayer);
									}
								}
								
							}
							
							
							//if a same ArrayListPlayAgain not exist...
							
							if(!alreadyExist)
							{
								System.out.println("found NOT a item with the same values...create a new one");
								arrayListRollAgain=new ArrayListPlayAgain<IPlayer>(currentTriple.getDieValue(),currentTriple.getComapareValue());
								
								this.listPlayAgain.add(arrayListRollAgain);
								
								
								//add players:
								System.out.println("ADD PLAYERS (C): TO THE ITEM:");
								IPlayer currentPlayer=currentTriple.getPlayer();
								IPlayer comparePlayer=comparingTriple.getPlayer();
								
								if(!arrayListRollAgain.contains(currentPlayer))
								{
									arrayListRollAgain.add(currentPlayer);
								}
								if(!arrayListRollAgain.contains(comparePlayer))
								{
									arrayListRollAgain.add(comparePlayer);
								}
							}
							
						}
						
						
						//TODO MORE! (skip tail and head, override players) 
					}
				}
			}
			
			System.out.println("Beste Player is: " + currentTriple.toString());
			
			unsortedTriples.remove(currentTriple);
			sortedTriples.add(currentTriple);
		
		}
		
		
		
		return sortedTriples;
	}
	
		
	///////////////
	//RUN ACTIONS//
	///////////////

	

	////////////
	//ACTION A//
	////////////
	
	

	@Override
	public  boolean setInputA(Object inputA)
	{
		boolean validBasic = false;
		this.isInputNew=true;
		
		validBasic=this.checkBasicInputs(inputA);
		if(validBasic)
		{
			return true;
		}
		else
		{
			boolean valid=this.checkInputActionA(inputA);
			this.isInputValid=valid;
			return valid;
		}
	}
	
	
	@Override
	public final boolean checkInputActionA(Object inputA)
	{
		String inputString="";
		
		if(!this.tryCastToString(inputA))
		{
			return false;
		}
		else
		{
			inputString=this.doCastToString(inputA);
		}
		
		
		if (inputString.equals(this.rb.getString("instructionPhasePreparation1Roll")))
		{
			int value=this.game.rollDie();
			
			System.out.println("YOU ROLLED " + value);
			
			Triple triple=new Triple(this.game.getCurrentPlayer(),value);
			this.triples.add(triple);
			return true;
		}
		else
		{
			return false;
		}
	}
	


	////////////
	//ACTION B//
	////////////
	@Override
	public  boolean setInputB(Object inputB)
	{
		String inputString="";
		boolean validBasic = false;
		this.isInputNew=true;
		
		if(!this.tryCastToString(inputB))
		{
			return false;
		}
		else
		{
			inputString=this.doCastToString(inputB);
		}
		
	
		validBasic=this.checkBasicInputs(inputString);
		
		if(validBasic)
		{
			this.isInputValid=true;
			return true;
		}
		else
		{
			boolean valid=this.checkInputActionB(inputString);
			this.isInputValid=valid;
			return valid;
		}
		
	}
	
	
	@Override
	public final boolean checkInputActionB(Object inputB)
	{
		if(!this.tryCastToInteger(inputB))
		{
			return false;
		}
		else
		{
			this.game.getPlayer(1).setAge(this.doCastToInteger(inputB)); 
			return true;
		}
	}
	
	

	///////////
	//SETTERS//
	///////////
	
	
	

	
	
	///////////
	//GETTERS//
	///////////
	
	
	/////////////////
	//INNER CLASSES//
	/////////////////
	private class Triple
	{
		private IPlayer player;
		private int dieValue;
		private int compareValue;
		
		public Triple(IPlayer player, int value)
		{
			this.player=player;
			this.dieValue=value;
			this.compareValue=value;
		}
		
		public int getDieValue()
		{
			return this.dieValue;
		}
		
		public int getComapareValue()
		{
			return this.compareValue;
		}
		
		public IPlayer getPlayer()
		{
			return player;
		}
		
		public String toString()
		{
			return "Pair [ " + this.player.getName() + " ] [ die value: " + this.dieValue +" ] [ compare value: " + this.compareValue +" ]";
		}
	}
	
	private class ArrayListPlayAgain<T> extends ArrayList<T>
	{
		private int dieValue;
		private int compareValue;
		
		public ArrayListPlayAgain(int dieValue, int compareValue)
		{
			this.compareValue=compareValue;
			this.dieValue=dieValue;
		}
		
		public int getDieValue()
		{
			return dieValue;
		}
		
		public int getCompareValue()
		{
			return compareValue;
		}
		
	}
	
}
