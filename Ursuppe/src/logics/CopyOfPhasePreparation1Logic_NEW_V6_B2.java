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
public abstract class CopyOfPhasePreparation1Logic_NEW_V6_B2 extends PhaseTemplateLogic
{
	private static enum EItem {Item, Placeholder};
	
	
	private Stack<PlaceholderItem> stackPlaceholders=new Stack<PlaceholderItem>();
	private ArrayList<Item> roundItems=new ArrayList<Item>();
	private ArrayList<Item> orderItems=new ArrayList<Item>();
	
	private int roundNumber=1;
	
	//////////
	//BASICS//
	//////////
	
	
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
		boolean validPlayer=!this.nextPlayer();
		
		if(validPlayer)
		{
			System.out.println("--- RESTART ON: NEXT PLAYER --");
			this.turnOnRestart();
		}
		else
		{
			boolean validOrder=isOrderValid(this.roundNumber);
			
			if(validOrder)
			{
				System.out.println("--- RESTART OFF: END --");
				this.turnOffRestart();
				
				this.currentPhase=EPhases.phasePreparation2;
			}
			else
			{
				System.out.println("--- RESTART ON: INVALID ORDER --");
				this.roundNumber++;
				this.turnOnRestart();
			}
		}
		
//		System.out.println("END OF LOGIC AFTer ACTION");
		
	
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
			
			//System.out.println("YOU ROLLED " + value);
			

			Item item=new Item(this.game.getCurrentPlayer(),value);
			this.roundItems.add(item);
			
			return true;
			
//			if(this.roundNumber!=0)
//			{
//				//Triple triple=this.currentPlaceHolderTriple.firstTriple;						
//				Triple triple=this.currentTripleOfPlaceholder;							//TODO CHANGED HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//				
//				assert(triple.getPlayer()==this.game.getCurrentPlayer());
//				
//				triple.setValue(value);
//				
//				if(!triple.getType().equals("Placeholder"))
//				{
//					System.out.println("''''''''''''''''''''''''''''''''''PLACEHOLDER NOCH NICHT FERTIG ABGEARBEITET!''''''''''''''''''''''''''''''''''''''''''''");
//					//this.currentPlaceHolderTriple.setFirstTriple(triple.getNext());
//					this.currentTripleOfPlaceholder=this.currentPlaceHolderTriple.getNext();
//				}
//				else
//				{
//					System.out.println("''''''''''''''''''''''''''''''''''PLACEHOLDER ERFOLRGREICH ABGEARBEITET!''''''''''''''''''''''''''''''''''''''''''''");
//				}
//				this.thisRoundTriples.add(triple);
//			}
//			else
//			{
//				Triple triple=new Triple(this.game.getCurrentPlayer(),value);
//				this.thisRoundTriples.add(triple);
//			}
//			
//			return true;
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
	
	private boolean isOrderValid(int roundNumber) 
	{
		boolean validOrder=false;
		
		if(roundNumber==1)	//is first round?
		{
			System.out.println("IT IS ROUND ONE");
			
			this.editOrder();
			
			validOrder=isOrderValid(roundNumber+1);
			
			this.saveBasicOrder();
		}
		else		//not first round
		{
			System.out.println("IT IS NOT ROUND ONE");
			
			if(this.stackPlaceholders.isEmpty())
			{
				validOrder=true;
			}
			else
			{
				this.prepareNextRound();
			}
		}
		
		return validOrder;
	}
	
	private void saveBasicOrder() 
	{
		assert (this.roundNumber==1);
		assert (this.orderItems.isEmpty());
		
//		this.orderItems=this.roundItems;
		
		for(Item currentItem:this.roundItems)
		{
			this.orderItems.add(currentItem);
		}
		
		this.printOutOrder(this.orderItems, "BASIC ORDER");
	}
	
	private void printOutOrder(ArrayList<Item> items,String message)
	{
		System.out.println("*****************************************************************************************************");
		System.out.println("************************" + message + "************************");
		System.out.println("*****************************************************************************************************");
		
		for(Item item:items)
		{
			System.out.println("\t " + item.toString());
		}
		
		System.out.println("*****************************************************************************************************");
		
		
	}

	private void prepareNextRound() 
	{
		
		
	}

	private void editOrder() 
	{
		System.out.println("---- EDIT ORDER START ----");
		this.printOutOrder(this.roundItems, "UNSORTED ORDER");
		
		this.sortRoundOrder();
		this.printOutOrder(this.roundItems, "SORTED ORDER");
		
		this.setPrevAndNextRoundOrder();
		
		this.checkSameValueRoundOrder();
		
		System.out.println("---- EDIT ORDER END ----");
	}
	
	private void sortRoundOrder() 
	{
		System.out.println("----- SORT ROUND ORDER -----");
		
		ArrayList<Item> sortedRoundOrder=new ArrayList<Item>();
		
		while(!this.roundItems.isEmpty())
		{
			Item currentItem=this.roundItems.get(0);
			
			for(Item compareItem:this.roundItems)
			{
				if(currentItem!=compareItem)
				{
					if(compareItem.getValue()>currentItem.getValue())
					{
						System.out.println("---------- COMPARE ITEM IS BETTER THAN CURRENT ----------");
						
						currentItem=compareItem;
					}
				}
			}
			
			this.roundItems.remove(currentItem);
			sortedRoundOrder.add(currentItem);
			
		}
		
		this.roundItems=sortedRoundOrder;
	}


	private void setPrevAndNextRoundOrder() 
	{
		System.out.println("----- SET PREV AND NEXT ROUND ORDER -----");
		
	}
	
	private void checkSameValueRoundOrder() 
	{
		System.out.println("----- CHECK SAME VALUE ROUND ORDER -----");
		
	}


	

	/////////////////
	//INNER CLASSES//
	/////////////////
	private class Item
	{
		Item nextItem;
		Item prevItem;
		
		IPlayer player;
		
		int value;
		
		EItem typ;
		
		public Item()
		{
		
		}
		
		public Item(IPlayer player, int rolledValue)
		{
			this.value=rolledValue;
			this.player=player;
			
			this.typ=EItem.Item;
		}
		
		
		public void setPlayer(IPlayer player)
		{
			this.player=player;
		}
		
		public void setValue(int rolledValue)
		{
			this.value=rolledValue;
		}
		
		public void setNext(Item nextItem)
		{
			this.nextItem=nextItem;
		}
		
		public void setPrev(Item prevItem)
		{
			this.prevItem=prevItem;
		}
		
		public Item getNext()
		{
			return this.nextItem;
		}
		
		public Item getPrev()
		{
			return this.prevItem;
		}
		
		public boolean hasPrev()
		{
			boolean valid=false;
			
			if(this.prevItem!=null)
			{
				valid=true;
			}
			
			return valid;
		}
		
		public boolean hasNext()
		{
			boolean valid=false;
			
			if(this.nextItem!=null)
			{
				valid=true;
			}
			
			return valid;
		}
		
		public IPlayer getPlayer()
		{
			return this.player;
		}
		
		public EItem getTyp()
		{
			return this.typ;
		}
		
		public int getValue()
		{
			return value;
		}
		
		public String toString()
		{
			String str="";
			
			String prev="PrevItem: " + this.prevItem;
			String next="NextItem: " + this.nextItem;
			
			String player="Player: " + this.player.getName();
			
			String value="Value: " + this.value;
			
			String typ="Typ: " + this.typ;
			
			str="[ " + prev + "  " + next + "  " + player + "  " + value + "  " + typ + "]";
			
			return str;
		}
	}
	
	private class PlaceholderItem extends Item
	{
		ArrayList<Item> items=new ArrayList<Item>();
		
		public PlaceholderItem()
		{
			super();
			
			this.typ=EItem.Placeholder;
		}
		
		public PlaceholderItem(ArrayList<Item> items)
		{
			super();
			
			this.typ=EItem.Placeholder;
			
			this.items=items;
		}
		
		public int size()
		{
			return this.items.size();
		}
		
		public boolean isEmpty()
		{
			return this.items.isEmpty();
		}
		
		public Item remove(int index)
		{
			return this.items.remove(index);
		}
		
		public void remove(Item item)
		{
			this.items.remove(item);
		}
		
		public void addItem(Item item)
		{
			this.items.add(item);
		}
		
		public void addItem(int index, Item item)
		{
			this.items.add(index, item);
		}
		
		public String toString()
		{
			String str="";
			
			String prev="PrevItem: " + this.prevItem;
			String next="NextItem: " + this.nextItem;
			
			String items="Items:";
				
			for(Item item : this.items)
			{
				items+="  " + item.toString() + "; ";
			}
			
			String value="Value: " + this.value;
			
			String typ="Typ: " + this.typ;
			
			str="[ " + prev + "  " + next + "  " + items + "  " + value + "  " + typ + "]";
			
			return str;
		}
	}

}


