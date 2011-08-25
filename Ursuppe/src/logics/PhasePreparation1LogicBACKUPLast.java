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
public abstract class PhasePreparation1LogicBACKUPLast extends PhaseTemplateLogic
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
			System.out.println(t.toString());
		}
		
		this.setPrevAndNextOfTriples(sortedTriples);
		System.out.println("AFTER SET PRE AND NEXT WITH PLACEHOLDERS");
		for(Triple t:sortedTriples)
		{
			System.out.println(t.toString());
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
		
		//System.out.println("FIRST TRIPLE IS: " + firstTriple.getPlayer().getName());
		//System.out.println("SECOND TRIPLE IS: " + secondTriple.getPlayer().getName());
		
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

	private Triple editSameTriples(Triple firstTriple, Triple lastTriple) 
	{
		PlaceHolderTriple placeHolderTriple=new PlaceHolderTriple(firstTriple,lastTriple);
		
		return placeHolderTriple;
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
	private static class Triple
	{
		private Triple nextTriple;
		private Triple prevTriple;
		
		private IPlayer player;
		private int dieValue;
		private int compareValue;
		
		private String type="Triple";
		private static int counter=0;
		protected int number=0;
		
		
		public Triple(String type)
		{
			this.type=type;
			
			counter++;
			number=counter;
		}
		
		public Triple(IPlayer player, int value)
		{
			counter++;
			number=counter;
			
			this.player=player;
			this.dieValue=value;
			this.compareValue=value;
		}
		
		public String getType()
		{
			return this.type;
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
			
			String prev="";
			String next="";

			if(this.prevTriple==null)
			{
				prev="null";
			}
			else if(this.prevTriple instanceof PlaceHolderTriple)
			{
				prev=this.prevTriple.toString();
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
			else if(this.nextTriple instanceof PlaceHolderTriple)
			{
				next=this.nextTriple.toString();
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

			return "Pair (Nr " + this.number +" )[ " + this.player.getName() + " ] [ die value: " + this.dieValue +" ] [ compare value: " + this.compareValue +" ] [ PREV: " + prev + " ] [ NEXT: " + next + " ]" ;

			
			
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
		
	}
	
	private class PlaceHolderTriple extends Triple
	{
		private Triple firstTriple;
		private Triple lastTriple;
		
		public PlaceHolderTriple(Triple firstTriple, Triple lastTriple)
		{
			super("Placeholder");
			
			this.firstTriple=firstTriple;
			this.lastTriple=lastTriple;
			
			this.edit();
		}
		
		private void edit()
		{
			this.firstTriple.setPrev(this);
			this.lastTriple.setNext(this);
		}
		
		public Triple getFirstTriple()
		{
			return this.firstTriple;
		}
		
		public Triple getLastTriple()
		{
			return this.lastTriple;
		}
		
		public int size()
		{
			int count=0;
			
			Triple nextTriple=this.firstTriple;
			while(!nextTriple.equals(this) && nextTriple!=null)
			{
				nextTriple=nextTriple.getNext();
				
				count++;
			}
			
			
			return count;
		}
		
		@Override
		public String toString()
		{
			String prev="";
			
		
			if(this.getPrev()!=null)
			{
				if(this.getPrev().getType().equals("Placeholder"))//.getClass()==  this.getClass())	//TODO CHECK THIS
				{
					prev=this.getPrev().toString();				//it's a placeHolderTriple
				}	
				else
				{
					prev=this.getPrev().getPlayer().getName();	//it's a normal triple
				}
			}
			else
			{
				prev="NULL";
			}
			
			
			String next="";
			
			
			if(this.getNext()!=null)
			{
				if(this.getNext().getType().equals("Placeholder"))//.getClass(==  this.getClass())	//TODO CHECK THIS
				{
					next=this.getNext().toString();					//it's a placeHolderTriple
				}
				else
				{
					next=this.getNext().getPlayer().getName();			//it's a normal triple
				}
			}
			else
			{
				next="NULL";
			}
			
			return "PLACEHOLDER (NR " + this.number+" ): [FIRST TRIPLE " + this.firstTriple.getPlayer().getName() +" ] [ LAST TRIPLE " + this.lastTriple.getPlayer().getName()+" ] [ SIZE " + this.size() +" ] [ PREV " + prev + " ] [ NEXT " + next +" ]";
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
