package logics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;



import annotations.OnlyForTesting;

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
public abstract class PhasePreparation1LogicBACKUP_V4 extends PhaseTemplateLogic
{
	//////////
	//BASICS//
	//////////
	private Stack<ValueSet> rollStack=new Stack<ValueSet>();
	
	private ArrayList<Triple> orderToPlay=new ArrayList<Triple>();
	
	private ArrayList<Triple> thisRoundTriples =new ArrayList<Triple>();
	
	private boolean validOrderToPlay=false;
	
	private ArrayList<ValueSet<IPlayer>> listPlayAgain=new ArrayList<ValueSet<IPlayer>>();
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
		boolean invalidNextPlayer=this.game.nextPlayer();
		
		if(!invalidNextPlayer)
		{
			System.out.println("::::turn on restart: because there is another player");
			
			this.turnOnRestart();
		}
		else
		{
			this.editOrderToPlay();
			
			if(!this.validOrderToPlay)
			{
//				this.startReRoll();
			}
			else
			{
				System.out.println("::::turn off restart: because order to play is valid");
				this.turnOffRestart();
			}
		
		}

	
	}
	
 
	
	

	///////////////
	//RUN ACTIONS//
	///////////////

	

	////////////
	//ACTION A//
	////////////
	
	



	

	private void editOrderToPlay() 
	{
		ArrayList<Triple> sortedTriples=this.sort(this.thisRoundTriples);
		
		for(Triple t:sortedTriples)
		{
			System.out.println(t.toString());
		}
		
		System.out.println("ListPlayAgain:");
		System.out.println("Size: " + this.listPlayAgain.size());
		
		for(Triple t:sortedTriples)
		{
			System.out.println(t.toString());
		}
		
		this.setPrevAndNextOfTriples(sortedTriples);
		
		System.out.println("AFTER SET PRE AND NEXT");
		for(Triple t:sortedTriples)
		{
			System.out.println(t.toString());
		}
		
		sortedTriples=this.checkForSameValues(sortedTriples);
		
		System.out.println("AFTER PLACEHOLDERS");
		for(Triple t:sortedTriples)
		{
			if(t.isPlaceHolder)
			{
				String str="";
				
				String prev="";
				String prevIn="";
				String next="";
				String nextIn="";
				
				if(t.getPrev()==null)
				{
					prev="prev NULL";
				}
				else
				{
					prev="Prev "+t.getPrev().getPlayer().getName();
				}
				
				if(t.getPrevIn().getPlayer()==null)
				{
					prevIn="prevIn NULL";
				}
				else
				{
					prevIn="PrevIn" + t.getPrevIn().getPlayer().getName();
				}
				
				if(t.getNext()==null)
				{
					next="next NULL";
				}
				else
				{
					next="Next " +t.getNext().getPlayer().getName();
				}
				
				if(t.getNextIn()==null)
				{
					nextIn="nextIn NULL";
				}
				else
				{
					nextIn="NextIn "+t.getNextIn().getPlayer().getName();
				}
				
				System.out.println("placeHolder: ["+prev +"] [  " + prevIn + "] [ " + next+ " ] [ " + nextIn+"]");
			}
			else
			{
				System.out.println(t.toString());
			}
			
		}
	}

	private ArrayList<Triple> checkForSameValues(ArrayList<Triple> sortedTriples) 
	{
		ArrayList<Triple> sortedTriplesCopy=(ArrayList<Triple>) sortedTriples.clone();
		ArrayList<Triple> newSortedTriples=new ArrayList<Triple>();
		
		while(!sortedTriplesCopy.isEmpty())
		{
			Triple currentTriple=sortedTriplesCopy.remove(0);
			
			ArrayList<Triple> triplesToRemove=new ArrayList<Triple>();
			
			int counter=0;
			boolean compare=true;
			
			while(compare && !sortedTriplesCopy.isEmpty())
			{
				Triple compareTriple=sortedTriplesCopy.get(counter);
				
				if(compareTriple.getComapareValue()==currentTriple.getComapareValue())
				{
					System.out.println("FOUND TRIPLES WITH SAME VALUE: COMPARE" + compareTriple.toString() +  "  AND  CURRENT " + currentTriple.toString());
					
					triplesToRemove.add(currentTriple);
					triplesToRemove.add(compareTriple);
					
				}
				else
				{
					compare=false;
				}
				
				
				counter++;
				
				if(counter>=sortedTriplesCopy.size())
				{
					compare=false;
				}
			}
			
			if(!triplesToRemove.isEmpty())
			{
				Triple firstTriple=triplesToRemove.get(0);
				
				System.out.println(":::::::::::::::::::::TRIPLES TO REMOVE:::::::::::::::::::::: ");
				
				for(Triple t:triplesToRemove)
				{
					System.out.println(t);
				}
				
				Triple lastTriple=triplesToRemove.get(triplesToRemove.size()-1);
				
				Triple placeHolderTriple=editSameTriples(firstTriple,lastTriple);//new Triple(firstTriple,lastTriple);	//TODO CHECK THIS!
				
				newSortedTriples.add(placeHolderTriple);
				
				for(Triple triple:triplesToRemove)
				{
					sortedTriplesCopy.remove(triple);
				}
				
			}
			else
			{
				newSortedTriples.add(currentTriple);
			}
			
		}
		
		
		
		return newSortedTriples;
	}

	private void setPrevAndNextOfTriples(ArrayList<Triple> sortedTriples) 
	{
		int index=0;
		
		//First one
		Triple firstTriple=sortedTriples.get(0);
		Triple secondTriple=sortedTriples.get(1);
		
		System.out.println("FIRST TRIPLE IS: " + firstTriple.getPlayer().getName());
		System.out.println("SECOND TRIPLE IS: " + secondTriple.getPlayer().getName());
		
		firstTriple.setPrev(null);
		firstTriple.setNext(secondTriple);
		
		//Middles
		for(index=1;index<sortedTriples.size()-1;index++)
		{
			Triple prevTriple=sortedTriples.get(index-1);
			Triple currentTriple=sortedTriples.get(index);
			Triple nextTriple=sortedTriples.get(index+1);
			
			currentTriple.setPrev(prevTriple);
			currentTriple.setNext(nextTriple);
			
		}
		
		//Last one
		
		Triple secondLastTriple=sortedTriples.get(index-1);
		Triple lastTriple=sortedTriples.get(index);
		
		lastTriple.setPrev(secondLastTriple);
		lastTriple.setNext(null);
		
	}

	private ArrayList<Triple> sort(ArrayList<Triple> unsortedTriples) 
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
			
			}
			
			System.out.println("Beste Player is: " + currentTriple.toString());
			
			
				unsortedTriples.remove(currentTriple);
				sortedTriples.add(currentTriple);
		
			
		
		}
		
		
		
		return sortedTriples;
	
		
	}



	private boolean existValueSet(Triple currentTriple, Triple comparingTriple) {
		ValueSet<IPlayer> arrayListRollAgain;
		boolean alreadyExist=false;
		
		for(ValueSet<IPlayer> arrayListPlayAgainToCheck:this.listPlayAgain)
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
		return alreadyExist;
	}
	
	private boolean existValueSet(Triple triple)
	{
		ValueSet<IPlayer> arrayListRollAgain;
		boolean alreadyExist=false;
		
		for(ValueSet<IPlayer> arrayListPlayAgainToCheck:this.listPlayAgain)
		{
			boolean sameCompareValue=arrayListPlayAgainToCheck.getCompareValue()==triple.getComapareValue();
			boolean sameDieValue=arrayListPlayAgainToCheck.getDieValue()==triple.getDieValue();
			
			if(sameCompareValue && sameDieValue)
			{
				System.out.println("found a item with the same values...");
				
				alreadyExist=true;
				arrayListRollAgain=arrayListPlayAgainToCheck;
				
				//add players:
				System.out.println("ADD PLAYERS (B): TO THE ITEM:");
				IPlayer currentPlayer=triple.getPlayer();
				IPlayer comparePlayer=triple.getPlayer();
				
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
		return alreadyExist;
	}

	private Triple editSameTriples(Triple comparingTriple, Triple currentTriple) 
	{
		
		//TODO: CHECK THIS!
		
		Triple placeHolder=new Triple(comparingTriple,currentTriple);
		
		placeHolder.setPrev(comparingTriple.getPrev());
		placeHolder.setNext(currentTriple.getNext());
		
		System.out.println("FIRST: " + comparingTriple.getPlayer().getName());
		System.out.println("LAST: " + currentTriple.getPlayer().getName());
		
		System.out.println("FIRST Next: " + comparingTriple.getNext());
		System.out.println("FIRST Prev: " + comparingTriple.getPrev());
		
		System.out.println("LAST Next: " + currentTriple.getNext());
		System.out.println("LAST Prev: " + currentTriple.getPrev());
		
		//currentTriple.setPrev(placeHolder);
		//currentTriple.setNext(comparingTriple);
		
		//comparingTriple.setPrev(currentTriple);
		//comparingTriple.setNext(null);
		
		return placeHolder;
	}

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
			this.thisRoundTriples.add(triple);
			
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
		private Triple nextTriple;
		private Triple prevTriple;
		
		private Triple nextInTriple;
		private Triple prevInTriple;
		
		private IPlayer player;
		private int dieValue;
		private int compareValue;
		
		private boolean isPlaceHolder=false;
		
		public Triple(Triple first, Triple last)
		{
			this.isPlaceHolder=true;
			
			this.setNextIn(last);
			this.setPrevIn(first);
		}
		
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
			String str="";
			
			if(this.isPlaceHolder)
			{
				
				str="Pair (PLACEHOLDER) :";
				
				str+="PREV IN: " + this.prevInTriple +"  NEXT IN: "+ this.nextInTriple;
				
				return str;
			}
			else
			{
				String prev="";
				String next="";
				
				if(this.prevTriple==null)
				{
					prev="null";
				}
				else
				{
					if(this.prevTriple.getPlayer()!=null)
					{
						prev=this.prevTriple.getPlayer().getName();
					}
					else
					{
						prev="No name";
					}
					
				}
				
				if(this.nextTriple==null)
				{
					next="null";
				}
				else
				{
					if(this.nextTriple.getPlayer()!=null)
					{
						next=this.nextTriple.getPlayer().getName();
					}
					else
					{
						prev="No name";
					}
					
					
				}
				
				return "Pair [ " + this.player.getName() + " ] [ die value: " + this.dieValue +" ] [ compare value: " + this.compareValue +" ] [ is placeHolder " + this.isPlaceHolder + " ] [ PREV: " + prev + " ] [ NEXT: " + next + " ]" ;

			}
			
		}
		
		public void setNext(Triple nextTriple)
		{
			this.nextTriple=nextTriple;
		}
		

		public void setPrev(Triple prevTriple)
		{
			this.prevTriple=prevTriple;
		}
		
		public Triple getNext()
		{
			return this.nextTriple;
		}
		
		public Triple getPrev()
		{
			return this.prevTriple;
		}
		
		public void setNextIn(Triple nextTriple)
		{
			this.nextInTriple=nextTriple;
		}
		

		public void setPrevIn(Triple prevTriple)
		{
			this.prevInTriple=prevTriple;
		}
		
		public Triple getNextIn()
		{
			return this.nextInTriple;
		}
		
		public Triple getPrevIn()
		{
			return this.prevInTriple;
		}
		
		public boolean isPlaceHolder()
		{
			return this.isPlaceHolder;
		}
	}
	
	private class ValueSet<T> extends ArrayList<T>
	{
		private int roundNumber=0;
		private int dieValue=0;
		private int compareValue;
		
		public ValueSet(int roundNumber, int dieValue)
		{
			this.dieValue=dieValue;
			this.compareValue=dieValue;
			this.roundNumber=roundNumber;
		}
		
		public int getRoundNumber()
		{
			return this.roundNumber;
		}
		
		public int getDieValue()
		{
			return this.dieValue;
		}
		
		public int getCompareValue()
		{
			return compareValue;
		}
		
		
		

	}
	
	

}
