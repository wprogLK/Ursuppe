package logics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


import annotations.OnlyForTesting;

import enums.EPhases;
import templates.PhaseTemplateLogic;
import interfaces.IPhase;
import interfaces.IPlayer;

/**
 * This preparation phase is about rolling the dice to get a order to set the players on the board
 * @author Lukas Keller
 * @version 1.0.0
 * 
 * @see IPhase
 */
public abstract class PhasePreparation1Logic extends PhaseTemplateLogic
{
	private static enum EItem {Item, Placeholder};
	
	private PlaceholderItem roundPlaceholder;
	
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
																		//Action A: roll the die
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
	/**
	 * checks if there is another valid player or if not then checks if it is a valid order to play
	 */
	public final void doLogicAfterAction()
	{
		boolean validPlayer=!this.nextPlayer();
		
		if(validPlayer)
		{
			//Restart on because there is a valid player
			this.turnOnRestart();
		}
		else
		{
			boolean validOrder=isOrderValid(this.roundNumber);
			
			if(validOrder)
			{
				//Restart off because a valid order was found
				this.turnOffRestart();
				
				this.removePlaceholders();
				this.rebuildOrder();
				
				this.overwriteOrderToPlay();
				
				this.waiting=true;
				
				this.currentPhase=EPhases.phasePreparation2;
			}
			else
			{
				//Restart on because of an invalid order
				this.roundNumber++;
				this.turnOnRestart();
				
				this.prepareForAReRoll();
			}
		}
	}
	
	/**
	 * Overwrites the current order to play with the new order
	 */
	private void overwriteOrderToPlay() 
	{
		ArrayList<IPlayer> newOrder=new ArrayList<IPlayer>();
		
		for(Item item:this.orderItems)
		{
			newOrder.add(item.getPlayer());
		}
		
		this.game.overrideAllNormalPlayers(newOrder);
	}

	/**
	 * sorts the order items
	 */
	private void rebuildOrder() 
	{
		ArrayList<Item> sortedRoundOrder=new ArrayList<Item>();
		
		while(!this.orderItems.isEmpty())
		{
			Item currentItem=this.orderItems.get(0);
			
			for(Item compareItem:this.orderItems)
			{
				if(currentItem!=compareItem)
				{
					if(compareItem.getValue()>currentItem.getValue())
					{															//Compare value is higher than the current value
						currentItem=compareItem;
					}
				}
			}

			this.orderItems.remove(currentItem);								//Delete the currentItem
			sortedRoundOrder.add(currentItem);
			
		}
		
		this.orderItems=sortedRoundOrder;
	}

	/**
	 * removes the placeholder if there exist and create an array only with items.
	 */
	private void removePlaceholders() 
	{
		ArrayList<Item> finalOrder=new ArrayList<Item>();
		
		for(Item currentItem:this.orderItems)
		{
			if(currentItem.getTyp()==EItem.Placeholder)						//PlaceholderItem
			{
				PlaceholderItem phI=(PlaceholderItem) currentItem;
				
				for(Item i:phI.getItemList())
				{
					finalOrder.add(i);
				}
			}
			else
			{																//Normal item
				if(!finalOrder.contains(currentItem))
				{
					finalOrder.add(currentItem);
				}
			}
		}
		
		this.orderItems=finalOrder;
	}

	private void prepareForAReRoll() 
	{
		this.roundPlaceholder=this.stackPlaceholders.pop();
		
		ArrayList<IPlayer> newPlayers=this.roundPlaceholder.getPlayerList();
		
		this.game.overrideAllNormalPlayers(newPlayers);
		
		this.roundItems=this.roundPlaceholder.getItemList();
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
			int dieValue=value;
			value=(int) Math.pow(10, this.roundNumber)*value;
			
			if(this.roundNumber==1)			//First round
			{
				Item item=new Item(this.game.getCurrentPlayer(),value);
				item.setDieValue(dieValue);
				this.roundItems.add(item);
			}
			else
			{								//not first round
				Item refreshItem=this.getItemWithPlayer(this.game.getCurrentPlayer());

				assert(refreshItem!=null);
				
				refreshItem.setDieValue(dieValue);
				refreshItem.setValue(value);
			}
			
			return true;
		}
		else
		{
			return false;
		}
	}

	private Item getItemWithPlayer(IPlayer currentPlayer) 
	{
		for(Item currentItem :this.roundItems)
		{
			
			if(currentItem.getPlayer().equals(currentPlayer))
			{
				return currentItem;
			}
		}
		return null;
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
	
	/**
	 * checks if the current order is a valid one.
	 * @param the current round number
	 * @return true if it is a valid order
	 */
	private boolean isOrderValid(int roundNumber) 
	{
		boolean validOrder=false;
		
		if(roundNumber==1)	//is first round?
		{
			this.editRoundOrder();
			
			validOrder=isOrderValid(roundNumber+1);
			
			this.saveBasicOrder();
		}
		else		//not first round
		{
			boolean isStackPlaceholdersEmpty=this.stackPlaceholders.isEmpty();
			
			if(isStackPlaceholdersEmpty)
			{
				validOrder=true;
			}
			else
			{
				//do nothing
			}
			
		}
		
		return validOrder;
	}
	
	/**
	 * is called only in the first round and will save the basic order 
	 */
	private void saveBasicOrder() 
	{
		assert (this.roundNumber==1);
		assert (this.orderItems.isEmpty());
		
		for(Item currentItem:this.roundItems)
		{
			this.orderItems.add(currentItem);
		}
	}

	/**
	 * edit the complete roundOrder
	 */
	private void editRoundOrder() 
	{
		this.sortRoundOrder();
		
		this.setPrevAndNextRoundOrder();
		
		this.checkSameValueRoundOrder();
	}
	
	/**
	 * sorts the current round order after die values
	 */
	private void sortRoundOrder() 
	{
		ArrayList<Item> sortedRoundOrder=new ArrayList<Item>();
		
		while(!this.roundItems.isEmpty())
		{
			Item currentItem=this.roundItems.get(0);
			
			for(Item compareItem:this.roundItems)
			{
				if(currentItem!=compareItem)
				{
					if(compareItem.getValue()>currentItem.getValue())
					{													//compare value is better than the current one
						currentItem=compareItem;
					}
				}
			}
			
			this.roundItems.remove(currentItem);
			sortedRoundOrder.add(currentItem);
		}
		
		this.roundItems=sortedRoundOrder;
	}

	/**
	 * set the prev and next of the sorted round order
	 */
	private void setPrevAndNextRoundOrder() 
	{
		for(int index=0;index<this.roundItems.size();index++)
		{
			if(index==0) 								//First Item
			{
				Item currentItem=this.roundItems.get(index);
				Item nextItem=this.roundItems.get(index+1);
				this.linkItems(null,currentItem,nextItem);
			}
			else if(index==this.roundItems.size()-2)	//Last Item
			{
				Item prevItem=this.roundItems.get(index-1);
				Item currentItem=this.roundItems.get(index);
				Item nextItem=this.roundItems.get(index+1);
				
				this.linkItems(prevItem,currentItem,nextItem);
			}
			else										//Every other Item
			{
				Item prevItem=this.roundItems.get(index-1);
				Item currentItem=this.roundItems.get(index);
				
				this.linkItems(prevItem,currentItem,null);
			}
		}
	}
	
	private void linkItems(Item prevItem, Item currentItem, Item nextItem) 
	{
		currentItem.setPrev(prevItem);
		currentItem.setNext(nextItem);
	}
	
	/**
	 * checks if more than one player rolled the same value.
	 * if its true these players will be saved in a placeholderItem and the normal items will be replace with it.
	 */
	private void checkSameValueRoundOrder() 
	{
		ArrayList<Item> newSortedItems=new ArrayList<Item>();
		
		while(!this.roundItems.isEmpty())
		{
			Item currentItem=this.roundItems.remove(0);
			
			ArrayList<Item> itemsToRemove=new ArrayList<Item>();
			
			int counter=0;
			boolean compare=true;
			
			while(compare && !this.roundItems.isEmpty())
			{
				Item compareItem=this.roundItems.get(counter);
				
				if(compareItem.getValue()==currentItem.getValue())
				{
					//found items with the same value!
					
					itemsToRemove.add(currentItem);
					itemsToRemove.add(compareItem);
				}
				else
				{
					compare=false;
				}
				
				counter++;
				
				if(counter>=this.roundItems.size())
				{
					compare=false;
				}
			}
			
			if(!itemsToRemove.isEmpty())
			{
				PlaceholderItem placeholderItem=new PlaceholderItem(itemsToRemove);
				
				//Set pre and next before and after placeholder:
				Item preBeforeItem=itemsToRemove.get(0).getPrev();
				Item nextAfterItem=itemsToRemove.get(itemsToRemove.size()-1).getNext();
				
				placeholderItem.setPrev(preBeforeItem);	
				placeholderItem.setNext(nextAfterItem);
				
				this.stackPlaceholders.push(placeholderItem);			
				
				newSortedItems.add(placeholderItem);
				
				for(Item triple:itemsToRemove)
				{
					this.roundItems.remove(triple);
				}
			}
			else
			{
				newSortedItems.add(currentItem);
			}
		}

		this.roundItems=newSortedItems;
		
	}


	

	/////////////////
	//INNER CLASSES//
	/////////////////
	/**
	 * Item is a inner class of PhasePreparation1Logic class.
	 * 
	 * <br> The item contains a next and a prev item, a player and the value of the rolled value.
	 * 
	 * @author Lukas Keller
	 * @version 1.0.0
	 */
	private class Item
	{
		Item nextItem;
		Item prevItem;
		
		IPlayer player;
		
		int value;
		int dieValue;
		
		EItem typ;
		
		String name=null;
		
		public Item()
		{
		
		}
		
		public Item(IPlayer player, int rolledValue)
		{
			this.value=rolledValue;
			this.player=player;
			
			this.name=this.player.getName();
			
			this.typ=EItem.Item;
		}
		
		public void setDieValue(int dieValue)
		{
			this.dieValue=dieValue;
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
			
			
			String prev="PrevItem: ";
			if(this.hasPrev())
			{
				prev+=this.prevItem.getName();
			}
			else
			{
				prev+="NULL";
			}
			
			String next="NextItem: ";
			if(this.hasNext())
			{
				next+=this.nextItem.getName();
			}
			else
			{
				next+="NULL";
			}
			
			String dieValue="Die value: " +this.dieValue;
			
			String player="Player: " + this.player.getName();
			
			String value="Value: " + this.value;
			
			String typ="Typ: " + this.typ;
			
			str="[ " + prev + "  " + next + "  " + player + "  " + value + "  " + dieValue + "  " + typ + "]";
			
			return str;
		}
		
		public String getName()
		{
			return this.name;
		}
	}
	
	/**
	 * PlaceholderItem is a inner class of PhasePreparation1Logic class.
	 * 
	 * <br> it can contains more than one item. It is used for different items with the same rolled value.
	 * 
	 * @author Lukas Keller
	 * @version 1.0.0
	 */
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
			
			this.removeDoublePlayers(items);
			
			
		}
		
		private void removeDoublePlayers(ArrayList<Item> items) 
		{
			ArrayList<Item> itemsToSave=new ArrayList<Item>();
			
			for(Item item:items)
			{
				if(!itemsToSave.contains(item))
				{
					itemsToSave.add(item);
				}
			}
			
			
			this.items=itemsToSave;
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
		
		public ArrayList<IPlayer> getPlayerList()
		{
			ArrayList<IPlayer> players=new ArrayList<IPlayer>();
			
			for(Item item:this.items)
			{
				IPlayer player=item.getPlayer();
				players.add(player);
			}
			
			return players;
		}
		
		public ArrayList<Item> getItemList()
		{
			return this.items;
		}
		
		public String toString()
		{
			String str="";
			
			
			String prev="PrevItem: ";
			if(this.hasPrev())
			{
				prev+=this.prevItem.getName();
			}
			else
			{
				prev+="NULL";
			}
			
			
			String next="NextItem: ";
			if(this.hasNext())
			{
				next+=this.nextItem.getName();
			}
			else
			{
				next+="NULL";
			}
			
			
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


