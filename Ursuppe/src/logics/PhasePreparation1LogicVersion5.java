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
public abstract class PhasePreparation1LogicVersion5 extends PhaseTemplateLogic
{
	//////////
	//BASICS//
	//////////
	//private Stack<ArrayList<Triple>> rollStack=new Stack<ArrayList<Triple>>();
	private Stack<PlaceHolderTriple> rollStack=new Stack<PlaceHolderTriple>();
	private Stack<ArrayList<IPlayer>> stackRollPlayers=new Stack<ArrayList<IPlayer>>();
	
	private ArrayList<Triple> orderToPlay=new ArrayList<Triple>();
	
	private ArrayList<Triple> thisRoundTriples =new ArrayList<Triple>();
	
	private boolean validOrderToPlay=false;
	
	private int roundNumber=0;
	
	private PlaceHolderTriple currentPlaceHolderTriple=null;
	
	private ArrayList<Triple> basicOrderToPlay;
	
	private Triple currentTripleOfPlaceholder=null;
	
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
//		System.out.println("+++++++++++THIS IS ROUND NUMBER " + this.roundNumber + "+++++++++++++++++");
	}
	
	@Override
	public final void doLogicAfterAction()
	{
		boolean invalidNextPlayer=this.nextPlayer();//this.game.nextPlayer();
		
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
				
				this.startReRoll();
				
			}
			else
			{
				System.out.println("::::turn off restart: because order to play is valid");
				
				System.out.println("*********************THE ORDER TO PLAY (BEFORE RECONSTRUCT) IS: ********************" );
				
				this.showBasicOrdnerToPlay();
//				for(Triple t:this.basicOrderToPlay)
//				{
//					System.out.println(t.toString());
//				}
				
				this.basicOrderToPlay=this.reconstructWholeOrder(this.basicOrderToPlay);
				
				System.out.println("*********************THE ORDER TO PLAY (AFTER RECONSTRUCT) IS: ********************" );
				
				this.showBasicOrdnerToPlay();
//				for(Triple t:this.basicOrderToPlay)
//				{
//					System.out.println(t.toString());
//				}
				
				this.turnOffRestart();
				
				this.waiting=true;
				
				//this.waitBeforePhaseChange();	//Important for every phase change!
				
				
				this.currentPhase=EPhases.phasePreparation2;	//TODO do it with nextPhase!
			}
			
			this.roundNumber++;
		}
		
//		System.out.println("END OF LOGIC AFTer ACTION");
		
		
	}
	
	

	private void showBasicOrdnerToPlay() 
	{
		Triple currentTriple=this.basicOrderToPlay.get(0);
		int counter=0;
		
		while(currentTriple!=null)
		{
			System.out.println(currentTriple);
			
			currentTriple=currentTriple.nextTriple;
			counter++;
		}
		
//		System.out.println("COUNTER AT THE END WAS " + counter);
		
	}

	private ArrayList<Triple>  reconstructWholeOrder(ArrayList<Triple> inputOrder) 
	{
		this.showNewPlayerOrder(inputOrder);		//ONLY FOR DEBUGGIN
		this.setPrevAndNextOfPlaceholder(inputOrder);
		
		ArrayList<Triple> output=this.createNewOrder(inputOrder);
//		System.out.println("SIZE OF INPUT ORDER AFTER " + inputOrder.size());
//		
		
		
	
		return output;
	}

	private void showNewPlayerOrder(ArrayList<Triple> inputList) 
	{
		ArrayList<Triple> output=new ArrayList<Triple>();
		
		Triple currentTriple=inputList.get(0);
		
		if(currentTriple.getType().equals("Placeholder"))
		{
			currentTriple=currentTriple.getNext();
			
			boolean valid=output.contains(currentTriple) ;
			
			while(!valid && currentTriple!=null)
			{
				output.add(currentTriple);
	
				currentTriple=currentTriple.getNext();
				
				valid=output.contains(currentTriple);
			}
		}
		else
		{
			boolean valid=output.contains(currentTriple);
			
			while(!valid && currentTriple!=null)
			{
				output.add(currentTriple);
	
				currentTriple=currentTriple.getNext();
				
				valid=output.contains(currentTriple);
			}
		}
		
		
		System.out.println("같같같같같같같같같같같같같같같같같같같같같같같같같같같같같같같OUTPUT같같같같같같같같같같같같같같같같같같같같같같같같같같같같같같같같같");
		for(Triple t:output)
		{
			System.out.println(t);
		}
		System.out.println("같같같같같같같같같같같같같같같같같같같같같같같같같같같같같같같END같같같같같같같같같같같같같같같같같같같같같같같같같같같같같같같같같");
	}

	private ArrayList<Triple>  createNewOrder(ArrayList<Triple> inputOrder) 
	{
		ArrayList<Triple> outputOrder=new ArrayList<Triple>();
		
		Triple currentTriple=inputOrder.get(0);
		while(currentTriple!=null)
		{
			
			
			if(currentTriple.getType().equals("Placeholder"))
			{
				PlaceHolderTriple pTriple=(PlaceHolderTriple) currentTriple;
				
				currentTriple=pTriple.getFirstTriple();
			}
			else
			{
				currentTriple=currentTriple.nextTriple;
			}
			
			outputOrder.add(currentTriple);
			
		}
		
//		System.out.println("CREATE NEW ORDER END...");
		
		this.orderToPlay.clear();
		
		return outputOrder;
	}

	private void setPrevAndNextOfPlaceholder(ArrayList<Triple> inputOrder) {
		boolean containsPlaceholderTriples=false;
		
		System.out.println("SIZE OF INPUT ORDER BEFORE " + inputOrder.size());
		
		for(Triple triple:inputOrder)
		{
			if(triple.getType().equals("Placeholder"))
			{
				
				
				PlaceHolderTriple pTriple=(PlaceHolderTriple) triple;
				
				Triple firstTriple=pTriple.getFirstTriple();
				Triple lastTriple=pTriple.getLastTriple();
//				
				Triple prevTriple=pTriple.getPrev();
				Triple nextTriple=pTriple.getNext();
				
				
					firstTriple.setPrev(prevTriple);
					System.out.println("00000000000000000000000000000000000 prevTriple is " + prevTriple);
//					if(prevTriple!=null)
//					{
//						prevTriple.setNext(firstTriple);//NEW
//					}
					
					lastTriple.setNext(nextTriple);
					System.out.println("00000000000000000000000000000000000 nextTriple is " + nextTriple);
//					if(nextTriple!=null)
//					{
//						nextTriple.setPrev(lastTriple); //NEW
//					}
					
				
			}
		}
		
		
//		boolean containsPlaceholderTriples=false;
//		
//		System.out.println("SIZE OF INPUT ORDER BEFORE " + inputOrder.size());
//		
//		for(Triple triple:inputOrder)
//		{
//			if(triple.getType().equals("Placeholder"))
//			{
//				
//				
//				PlaceHolderTriple pTriple=(PlaceHolderTriple) triple;
//				
//				Triple firstTriple=pTriple.getFirstTriple();
//				Triple lastTriple=pTriple.getLastTriple();
////				
//				Triple prevTriple=pTriple.getPrev();
//				Triple nextTriple=pTriple.getNext();
//				
//				
//					firstTriple.setPrev(prevTriple);
//					
//					if(prevTriple!=null)
//					{
//						prevTriple.setNext(firstTriple);//NEW
//					}
//					
//					lastTriple.setNext(nextTriple);
//					
//					if(nextTriple!=null)
//					{
//						nextTriple.setPrev(lastTriple); //NEW
//					}
//					
//				
//			}
//		}
	}

	private void startReRoll() 
	{
		System.out.println("//////////////////////ON THE ROLL STACK ARE:");
		
		for(PlaceHolderTriple pT:this.rollStack)
		{
			System.out.println(pT);
		}
		
		
		this.overridePlayers();
		
		this.turnOnRestart();
		
		System.out.println("::::turn on restart: because roll again");
		
	}

	private void overridePlayers() 
	{
		ArrayList<IPlayer> currentPlayers=this.stackRollPlayers.pop();
		this.currentPlaceHolderTriple=this.rollStack.pop();
		this.currentTripleOfPlaceholder=this.currentPlaceHolderTriple.getFirstTriple();
		
		this.game.overrideAllNormalPlayers(currentPlayers);
		
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
			
			if(this.roundNumber!=0)
			{
				//Triple triple=this.currentPlaceHolderTriple.firstTriple;						
				Triple triple=this.currentTripleOfPlaceholder;							//TODO CHANGED HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				
				assert(triple.getPlayer()==this.game.getCurrentPlayer());
				
				triple.setValue(value);
				
				if(!triple.getType().equals("Placeholder"))
				{
					System.out.println("''''''''''''''''''''''''''''''''''PLACEHOLDER NOCH NICHT FERTIG ABGEARBEITET!''''''''''''''''''''''''''''''''''''''''''''");
					//this.currentPlaceHolderTriple.setFirstTriple(triple.getNext());
					this.currentTripleOfPlaceholder=this.currentPlaceHolderTriple.getNext();
				}
				else
				{
					System.out.println("''''''''''''''''''''''''''''''''''PLACEHOLDER ERFOLRGREICH ABGEARBEITET!''''''''''''''''''''''''''''''''''''''''''''");
				}
				this.thisRoundTriples.add(triple);
			}
			else
			{
				Triple triple=new Triple(this.game.getCurrentPlayer(),value);
				this.thisRoundTriples.add(triple);
			}
			
			return true;
		}
		else
		{
			return false;
		}
	}

	///////////
	//SETTERS//
	///////////
	
	
	

	
	
	///////////
	//GETTERS//
	///////////
	
	
	///////////////////////////////////
	//SPECIAL METHODES FOR THIS LOGIC//
	///////////////////////////////////

	private void editOrderToPlay() 
	{
		ArrayList<Triple> sortedTriples=this.sort(this.thisRoundTriples);
		
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
		
		this.editStack(sortedTriples);
		
		System.out.println("AFTER EDIT STACK");
		for(PlaceHolderTriple pT:this.rollStack)
		{
			System.out.println("-----------------------------------------------------------------");
			
			
		
			System.out.println(pT);
		
			
			System.out.println("-----------------------------------------------------------------");
		}
		
		this.checkReRoll();
		
		if(this.roundNumber==0)
		{
			System.out.println("└└└└└└└└└└└└└└└└└└ CREATE BASIC ORDER TO PLAY └└└└└└└└└└└└�");
			
			this.basicOrderToPlay=sortedTriples;
			
//			Triple headTriple=new Triple("Head");
//			Triple firstTriple=this.basicOrderToPlay.get(0);
//			
//			headTriple.setNext(firstTriple);
//			firstTriple.setPrev(headTriple);
//			
//			this.basicOrderToPlay.add(0, headTriple);
		}
	}

	private void checkReRoll()
	{
		if(this.stackRollPlayers.isEmpty())
		{
			this.validOrderToPlay=true;
		}
		else
		{
			System.out.println("NOT FOUND VALID ORDER TO PLAY!");
		}
	}

	private void editStack(ArrayList<Triple> sortedTriples) 
	{
		for(Triple triple:sortedTriples)
		{
			if(triple.getType().equals("Placeholder"))
			{
				PlaceHolderTriple pTriple=(PlaceHolderTriple) triple;
				
				ArrayList<IPlayer> players= pTriple.getPlayers();
				
//				System.out.println("placeHolderTriple with number " + pTriple.getNumber() + " has following players: ");
				
				for(IPlayer player:players)
				{
					System.out.println(player.getName());
				}
				
				this.stackRollPlayers.push(players);
				
				ArrayList<Triple> triples=pTriple.getTriples();
				
				this.rollStack.push(pTriple);
				
				
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
					//found a triple with the same value!
					
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
				
//				for(Triple t:triplesToRemove)
//				{
//					System.out.println(t);
//				}
				
				Triple lastTriple=triplesToRemove.get(triplesToRemove.size()-1);
				
				PlaceHolderTriple placeHolderTriple=editSameTriples(firstTriple,lastTriple);
				
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
		Triple secondTriple=null;
		
		if(sortedTriples.size()>1)
		{
			secondTriple=sortedTriples.get(1);
		}
		
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
			Triple currentTriple=unsortedTriples.get(0);
			
			for(Triple comparingTriple:unsortedTriples)
			{
				//checking if there is a better triple than the current one...
			
				if(comparingTriple.getComapareValue()>currentTriple.getComapareValue())
				{
					//found a better triple than currentTriple
					
					currentTriple=comparingTriple;
				}
			
			}
			
			unsortedTriples.remove(currentTriple);
			sortedTriples.add(currentTriple);
		
		}
	
		return sortedTriples;
	}

	private PlaceHolderTriple editSameTriples(Triple firstTriple, Triple lastTriple) 
	{
		PlaceHolderTriple placeHolderTriple=new PlaceHolderTriple(firstTriple,lastTriple);
		
		return placeHolderTriple;
	}

	
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
		
		public void setValue(int value) 
		{
			this.compareValue=value;
			this.dieValue=value;
			
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
					next="No name";
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
		
		public int getNumber()
		{
			return this.number;
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
		
		public void setFirstTriple(Triple next) 
		{
			this.firstTriple=next;
			
		}

		public ArrayList<IPlayer> getPlayers()
		{
			ArrayList<IPlayer> players=new ArrayList<IPlayer>();
			
			Triple nextTriple=this.firstTriple;
			IPlayer currentPlayer;
			
			while(!nextTriple.equals(this) && nextTriple!=null)
			{
				currentPlayer=nextTriple.getPlayer();
				
				players.add(currentPlayer);	
				
				nextTriple=nextTriple.getNext();
			}
			
			assert (players.size()==this.size());
			
			return players;
		}
		
		public ArrayList<Triple> getTriples()
		{
			ArrayList<Triple> triples=new ArrayList<Triple>();
			
			Triple nextTriple=this.firstTriple;
			
			while(!nextTriple.equals(this) && nextTriple!=null)
			{
				triples.add(nextTriple);	
				
				nextTriple=nextTriple.getNext();
			}
			
			
			return triples;
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
			boolean valid=true;
			
			while( nextTriple!=null && valid && !nextTriple.getType().equals("Placeholder"))//!nextTriple.equals(this) ) //(nextTriple!=this.firstTriple && count>0))
			{
				nextTriple=nextTriple.getNext();
				
				count++;
				
				if(nextTriple==this.firstTriple)
				{
					valid=false;
				}
			}
			
			return count;
		}
		
		@Override
		public String toString()
		{
			String prev="";
			
			if(this.getPrev()!=null ) //TODO: CHANGED HERE!
			{
				if (this.getPrev().getPlayer()!=null)
				{
					if(this.getPrev().getType().equals("Placeholder"))
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
			}
			else
			{
				prev="NULL";
			}
			
			
			String next="";
			
			
			if(this.getNext()!=null)
			{
				if(this.getNext().getType().equals("Placeholder"))
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
			System.out.println("FIRST TRIPLE" + this.firstTriple.getType());
			System.out.println("LAST TRIPLE" + this.lastTriple.getType());
			
			return "PLACEHOLDER (NR " + this.number+" ): [FIRST TRIPLE " + this.firstTriple.getPlayer().getName() +" ] [ LAST TRIPLE " + this.lastTriple.getPlayer().getName()+" ] [ SIZE " + this.size() +" ] [ PREV " + prev + " ] [ NEXT " + next +" ]";
		}
	}

}
