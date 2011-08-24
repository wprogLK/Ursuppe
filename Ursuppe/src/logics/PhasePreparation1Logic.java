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
public abstract class PhasePreparation1Logic extends PhaseTemplateLogic
{
	//////////
	//BASICS//
	//////////
//	private ArrayList<Pair> pairs=new ArrayList<Pair>();
//	private ArrayList<IPlayer> orderToPlay=new ArrayList<IPlayer>();
//	private ArrayList<Pair> pairsSorted=new ArrayList<Pair>();
//	
//	private ArrayList<IPlayer> originalOrder=new ArrayList<IPlayer>();
//	
//	private ArrayList<Integer> rolledValues=new ArrayList<Integer>();
//	private ArrayList<Pair> sameValueRolledPairs=new ArrayList<Pair>();
//	
//	private ArrayList<Integer> sameRolledValue=new ArrayList<Integer>();
//	
//	private boolean isSameValueRolled=false;
	
	private ArrayList<Triple> triples=new ArrayList<Triple>();
	private ArrayList<IPlayer> playerOrderToPlay=new ArrayList<IPlayer>();
	private ArrayList<Triple> triplesOrderToPlay=new ArrayList<Triple>();
	private ArrayList<Triple> triplesToPlayAgain=new ArrayList<Triple>();
	
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
		
		}
		
		
		
		//TODO TEST THIS & REFACTOR!!
		
//		if(this.isSameValueRolled)
//		{
//			
//		}
//		else if(!this.game.nextPlayer())	//If round is NOT finished...
//		{
//			this.turnOnRestart();
//			System.out.println("CURRENT PLAYER IS: " + this.game.getCurrentPlayer().getName());
//			System.out.println("RESTART ON");
//		}
//		else	//if round is finished...
//		{
//			this.checkSameValuesRolled();
//			
//			if(!this.isSameValueRolled) //No same values
//			{
//				this.turnOffRestart();
//				System.out.println("CURRENT PLAYER IS: " + this.game.getCurrentPlayer().getName());
//				System.out.println("RESTART OFF");
//					
//				System.out.println(this.pairs.size());
//					
//				this.orderToPlaySetup();
//			}
//			else	//same values
//			{
//				this.turnOnRestart();
//				
//				ArrayList<IPlayer> rollAgainPlayers=this.getPlayersWithSameRolledValue();
//				
//				this.originalOrder=this.game.overrideAllNormalPlayers(rollAgainPlayers);
//				
//				System.out.println("CURRENT PLAYER IS: " + this.game.getCurrentPlayer().getName());
//				System.out.println("RESTART ON: SAME VALUE ROLLED!");
//			}
//			
//		}
	
	}
	
	private void printTripleOrderToPlay() 
	{
		System.out.println("triples READY to play:");
		
		for(Triple triple:this.triplesOrderToPlay)
		{
			System.out.println(triple.toString());
		}
		
		System.out.println("triples NOT READY to play:");
		
		for(Triple triple:this.triplesToPlayAgain)
		{
			System.out.println(triple.toString());
		}
			
	}

//	private ArrayList<IPlayer> getPlayersWithSameRolledValue() 
//	{
//		
//		//TODO: TEST THIS!
//		ArrayList<IPlayer> playersWithSameRolledValue=new ArrayList<IPlayer>();
//		
//		for (Pair pair:this.pairsSorted)
//		{
//			int playersRolledValue=pair.getDieValue();
//			
//			if(this.sameRolledValue.contains(playersRolledValue))
//			{
//				System.out.println("Übereinstimmung gefunden: "+ pair);
//				playersWithSameRolledValue.add(pair.getPlayer());
//				
//				this.pairsSorted.remove(pair);
//			}
//		}
//		
//		return playersWithSameRolledValue;
//	}

//	private void checkSameValuesRolled() 
//	{
//		this.isSameValueRolled=this.sameValueRolledPairs.isEmpty();
//	}

	
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
				
				if(comparingTriple.getComapareValue()>currentTriple.getComapareValue())
				{
					System.out.println("Triple " + comparingTriple +" is better than " + currentTriple);
					currentTriple=comparingTriple;
				}
				else if(comparingTriple.getComapareValue()==currentTriple.getComapareValue())
				{
					if(!comparingTriple.equals(currentTriple))
					{
						System.out.println("Triple " + comparingTriple +" is equal to " + currentTriple +"!");
						this.triplesToPlayAgain.add(currentTriple);
						this.triplesToPlayAgain.add(comparingTriple);
						
						this.rolledSameValue=true;
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
	
//	private void orderToPlaySetup() 
//	{
//		
//		while(!this.pairs.isEmpty())
//		{
//			System.out.println("SIZE OF PAIRS ARRAYLIST: " + this.pairs.size());
//			
//			Pair currentPair=this.pairs.get(0);
//			
//			for(Pair comparingPair:this.pairs)
//			{
//				System.out.println("COMPARE...");
//				if(comparingPair.getDieValue()>currentPair.getDieValue())
//				{
//					System.out.println("Pair " + comparingPair +" is better than " + currentPair);
//					currentPair=comparingPair;
//				}
//			}
//			
//			System.out.println("Beste Player is: " + currentPair.toString());
//			
//			this.orderToPlay.add(currentPair.getPlayer());
//			this.pairs.remove(currentPair);
//			this.pairsSorted.add(currentPair);
//		}
//		
//		printOrderToPlay();
//	}
//	
	//TODO Delete this:
//	private void printOrderToPlay()
//	{
//		System.out.println("Order to play:");
//
//		for(Pair cPair:this.pairsSorted)
//		{
//			System.out.println(cPair.toString());
//		}
//	}
		
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
	
	
	///////////////
	//INNER CLASS//
	///////////////
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
	
}
