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
 * 
 * @author Lukas Keller
 * @version 1.0.0
 * 
 * @see IPhase
 */
public abstract class PhasePreparation1Logic extends PhaseTemplateLogic
{
	private static enum EItem {Item, Placeholder};
	
	private PlaceholderItem roundPlaceholder;
//	private Queue<PlaceholderItem> queuePlaceholders=new LinkedList<PlaceholderItem>();
	
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
//			System.out.println("-_-_-_-_-_-_-_-_-_-_-_- EDIT ROUND BEFOR CHECK  -_-_-_-_-_-_-_-_-_-_-_-");
//			this.printOutOrder(this.roundItems, "-_-_-_-_-_-_-_-_-_-_-_- ROUND ITEMS MANUAL EDIT AFTER -_-_-_-_-_-_-_-_-_-_-_-");
			
			boolean validOrder=isOrderValid(this.roundNumber);
//			this.printOutOrder(this.roundItems, "-_-_-_-_-_-_-_-_-_-_-_- ROUND ITEMS NOT MAnuaL EDIT AFTER -_-_-_-_-_-_-_-_-_-_-_-");
			//this.roundItems.clear();
			
			if(validOrder)
			{
				System.out.println("--- RESTART OFF: END --");
				this.turnOffRestart();
				
				this.removePlaceholders();
				this.rebuildOrder();
				
				this.printOutOrder(this.orderItems, "-_-_-_-_-_-_-_-_-_-_-_- ORDER ITEMS AT THE END -_-_-_-_-_-_-_-_-_-_-_-");
				
				this.overwriteOrderToPlay();
				
				this.waiting=true;
				
				this.currentPhase=EPhases.phasePreparation2;
			}
			else
			{
				System.out.println("--- RESTART ON: INVALID ORDER --");
				this.roundNumber++;
				this.turnOnRestart();
				
				this.prepareForAReRoll();
			}
			
			
		}
		
//		System.out.println("END OF LOGIC AFTer ACTION");
		
	
	}
	
	


	private void overwriteOrderToPlay() 
	{
		ArrayList<IPlayer> newOrder=new ArrayList<IPlayer>();
		
		for(Item item:this.orderItems)
		{
			newOrder.add(item.getPlayer());
		}
		
		this.game.overrideAllNormalPlayers(newOrder);
		
		System.out.println(",,,,,,,,,,,,,,,,,,,,,,ORDER TO PLAY ,,,,,,,,,,,,,,,,,,,,,,,,");
		
		this.game.showPlayers();
	}

	private void rebuildOrder() 
	{
//	System.out.println("*************----- SORT ORDER ITEMS-----********************");
		
		ArrayList<Item> sortedRoundOrder=new ArrayList<Item>();
		
		while(!this.orderItems.isEmpty())
		{
			Item currentItem=this.orderItems.get(0);
			
			for(Item compareItem:this.orderItems)
			{
				if(currentItem!=compareItem)
				{
					if(compareItem.getValue()>currentItem.getValue())
					{
//						System.out.println("---------- COMPARE ITEM IS BETTER THAN CURRENT ----------");
						
						currentItem=compareItem;
					}
				}
			}
//			System.out.println("DELETE");
			this.orderItems.remove(currentItem);
			sortedRoundOrder.add(currentItem);
			
		}
		
		this.orderItems=sortedRoundOrder;
		
	}

	private void removePlaceholders() 
	{
		
		ArrayList<Item> finalOrder=new ArrayList<Item>();
		
		for(Item currentItem:this.orderItems)
		{
			if(currentItem.getTyp()==EItem.Placeholder)
			{
				PlaceholderItem phI=(PlaceholderItem) currentItem;
				
				for(Item i:phI.getItemList())
				{
					finalOrder.add(i);
				}
				
				
			}
			else
			{
//				System.out.println("=======================================ITEM NORMAL "+currentItem +" =========================" );
				if(!finalOrder.contains(currentItem))
				{
					finalOrder.add(currentItem);
				}
				
			}
		}
		this.printOutOrder(finalOrder, "ITEMS AT END REPLACEING PLACEHOLDER");
		this.orderItems=finalOrder;
		
//		OLD ALGORITHM:
//
//		ArrayList<Item> finalOrder=new ArrayList<Item>();
//		
//		for(Item currentItem:this.orderItems)
//		{
//			if(currentItem.getTyp()==EItem.Placeholder)
//			{
//				PlaceholderItem phI=(PlaceholderItem) currentItem;
//				
//				Item prevBeforeItem= phI.getPrev();
//				Item nextAfterItem=phI.getNext();
//				
//				System.out.println("PLACEHOLDER: " + phI);
//				
//				System.out.println("preBeforeItem is" + prevBeforeItem);
//				System.out.println("nextAfterItem is" + nextAfterItem);
//				
//				if(prevBeforeItem!=null)								//TODO TEST THIS!
//				{
//					for(Item i:phI.getItemList())
//					{
//						System.out.println("=======================================preBefor normal add " + i+ "=========================" );
//						finalOrder.add(i);
//					}
//				}
//				else if(!phI.getItemList().isEmpty())		//placeholder is/was head of the arrayList		//TODO TEST THIS!
//				{
//					Item head=phI.getItemList().remove(0);//.get(0);
//					
//					System.out.println("=======================================First Item is head: " + head+ "=========================" );
//					
//					finalOrder.add(head);
//					
//
//				}
//				else
//				{
//					System.out.println("!!!!!!!!!!!!!!!!ERROR!!!!!!!!!!!!!! IN HEAD: list is empty" );
//				}
//				
//				if(nextAfterItem!=null)										//TODO TEST THIS!
//				{
//					for(Item i:phI.getItemList())
//					{
//						System.out.println("=======================================nextAfter normal add " + i+ "=========================" );
//						if(!finalOrder.contains(i))
//						{
//							finalOrder.add(i);
//						}
//						
//					}
//				}
//				if(!phI.getItemList().isEmpty())	//placeholder is/was tail of the arrayList		//TODO TEST THIS!
//				{
//					System.out.println("SIZE: " + phI.getItemList().size());
//					
//					
//					Item tail=phI.getItemList().remove(phI.getItemList().size()-1);
//					
//					System.out.println("=======================================Last Item is tail: " + tail+ "=========================" );
//					
//					if(!finalOrder.contains(tail))
//					{
//							finalOrder.add(tail);
//					}
//				
//				}
//				else
//				{
//					System.out.println("!!!!!!!!!!!!!!!!ERROR!!!!!!!!!!!!!! IN TAIL: list is empty" );
//				}
//				
//				this.printOutOrder(phI.getItemList(), "ITEMS AT END OF IN PLACEHOLDER");
//			}
//			else
//			{
//				System.out.println("=======================================ITEM NORMAL "+currentItem +" =========================" );
//				if(!finalOrder.contains(currentItem))
//				{
//					finalOrder.add(currentItem);
//				}
//				
//			}
//		}
//		
//		this.orderItems=finalOrder;
	}

	private void prepareForAReRoll() 
	{
		//this.roundPlaceholder=this.queuePlaceholders.poll();
		this.roundPlaceholder=this.stackPlaceholders.pop();
		
		ArrayList<IPlayer> newPlayers=this.roundPlaceholder.getPlayerList();
//		ArrayList<IPlayer> newPlayers= this.stackPlaceholders.firstElement().getPlayerList();
//		ArrayList<IPlayer> newPlayers=this.queuePlaceholders.peek().getPlayerList();
		
		this.game.overrideAllNormalPlayers(newPlayers);
		
		System.out.println("----------------------------------------------------------------------------------------------------------------------------");
//		System.out.println("size of queue before: " + this.queuePlaceholders.size());
		

		
		
		System.out.println("----------------------------------------------------------------------------------------------------------------------------");
//		System.out.println("size of queue after: " + this.queuePlaceholders.size());
	
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
			//value=10^this.roundNumber*value;
			value=(int) Math.pow(10, this.roundNumber)*value;
			
			//System.out.println("YOU ROLLED " + value);
			
			if(this.roundNumber==1)
			{
//				System.out.println("------- ROLL DIE: ROUND ONE -------");
				
				Item item=new Item(this.game.getCurrentPlayer(),value);
				item.setDieValue(dieValue);
				this.roundItems.add(item);
			}
			else
			{
				System.out.println("/////////////////////////ROUND NR: " + this.roundNumber + "\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
//				System.out.println("------- ROLL DIE: NOT ROUND ONE -------");
				this.printOutOrder(this.roundItems, "ROUND ITEMS:");
				
				Item refreshItem=this.getItemWithPlayer(this.game.getCurrentPlayer());
				System.out.println("CURRENT PLAYER is " + this.game.getCurrentPlayer().getName());
				assert(refreshItem!=null);
				
//				this.printOutOrder(this.roundItems, "((((((((((((((((((ROUND ITEMS BEFORE CHANGE VALUE))))))))))))))))))))))))");
				refreshItem.setDieValue(dieValue);
				refreshItem.setValue(value);
//				this.printOutOrder(this.roundItems, "((((((((((((((((((ROUND ITEMS AFTER CHANGE VALUE))))))))))))))))))))))))");
			}
			
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

	private Item getItemWithPlayer(IPlayer currentPlayer) 
	{
		
//		System.out.println("/////////////////////////////////////////////////////////////////////////////////////////////");
		System.out.println("CURRENT PLAYER IS " + currentPlayer.getName());
		for(Item currentItem :this.roundItems)
		{
			
			if(currentItem.getPlayer().equals(currentPlayer))
			{
				System.out.println("CURRENT ITEM IS " + currentItem);
				return currentItem;
			}
		}
//		System.out.println("/////////////////////////////////////////////////////////////////////////////////////////////");
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
	
	private boolean isOrderValid(int roundNumber) 
	{
		boolean validOrder=false;
		
		if(roundNumber==1)	//is first round?
		{
			System.out.println("IT IS ROUND ONE");
			
			this.editRoundOrder();
			
			validOrder=isOrderValid(roundNumber+1);
			
			this.saveBasicOrder();
		}
		else		//not first round
		{
			System.out.println("IT IS NOT ROUND ONE");
			
			boolean isStackPlaceholdersEmpty=this.stackPlaceholders.isEmpty();
			
//			boolean isStackPlaceholdersEmpty=this.queuePlaceholders.isEmpty();
//			boolean isRoundItemsEmpty=this.roundItems.isEmpty();
			
			if(isStackPlaceholdersEmpty)
			{
//				System.out.println("::::::::::::::::::::::::::::: OPTION A::::::::::::::::::::::::::");
//				System.out.println("IS ROUND ITEMS EMPTY: " + this.roundItems.isEmpty());
				validOrder=true;
			}
			else
			{
//				System.out.println("::::::::::::::::::::::::::::: OPTION B::::::::::::::::::::::::::");
//				System.out.println("IS ROUND ITEMS EMPTY: " + this.roundItems.isEmpty());
				this.prepareNextRound();
			}
			
//			this.printOutOrder(roundItems, ":::::::::::::::::::::::::::::::::::::::::: roundItems before::::::::::::::::::::::::::::::::::::");
//			this.printOutOrder(orderItems, ":::::::::::::::::::::::::::::::::::::::::: orderItems before::::::::::::::::::::::::::::::::::::");
			
			
			//this.sortRoundOrder();
			
//			this.printOutOrder(roundItems, ":::::::::::::::::::::::::::::::::::::::::: roundItems after sort::::::::::::::::::::::::::::::::::::");
//			this.printOutOrder(orderItems, ":::::::::::::::::::::::::::::::::::::::::: orderItems after sort::::::::::::::::::::::::::::::::::::");
			//this.editRoundOrder();	
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

	private void editRoundOrder() 
	{
//		System.out.println("---- EDIT ORDER START ----");
		this.printOutOrder(this.roundItems, "UNSORTED ORDER");
		
		this.sortRoundOrder();
//		this.printOutOrder(this.roundItems, "SORTED ORDER");
		
		this.setPrevAndNextRoundOrder();
//		this.printOutOrder(this.roundItems, "SET PRE AND NEXT ORDER");
		
		this.checkSameValueRoundOrder();
//		this.printOutOrder(this.roundItems, "ORDER WITH PLACEHOLDERITEM");
		
//		System.out.println("---- EDIT ORDER END ----");
	}
	
	private void sortRoundOrder() 
	{
//		System.out.println("----- SORT ROUND ORDER -----");
		
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
//						System.out.println("---------- COMPARE ITEM IS BETTER THAN CURRENT ----------");
						
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
//		System.out.println("----- SET PREV AND NEXT ROUND ORDER -----");
		
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

	private void checkSameValueRoundOrder() 
	{
//		System.out.println("----- CHECK SAME VALUE ROUND ORDER -----");
		
		//ArrayList<Item> sortedItemsCopy=(ArrayList<Item>) this.roundItems.clone();
		ArrayList<Item> newSortedItems=new ArrayList<Item>();
		
		while(!this.roundItems.isEmpty())
		{
			Item currentItem=this.roundItems.remove(0);
			
			ArrayList<Item> itemsToRemove=new ArrayList<Item>();
			
			int counter=0;
			boolean compare=true;
			
			
			
			while(compare && !this.roundItems.isEmpty())
			{
				Item compareTriple=this.roundItems.get(counter);
				
				if(compareTriple.getValue()==currentItem.getValue())
				{
					//found a triple with the same value!
					
					itemsToRemove.add(currentItem);
					itemsToRemove.add(compareTriple);
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
				Item firstTriple=itemsToRemove.get(0);
				
				for(Item t:itemsToRemove)
				{
					System.out.println(t);
				}
				
				Item lastTriple=itemsToRemove.get(itemsToRemove.size()-1);
				
				//Item placeHolderTriple=editSameTriples(firstTriple,lastTriple);
				PlaceholderItem placeholderItem=new PlaceholderItem(itemsToRemove);
				
				
				//Set pre and next before and after placeholder:
				Item preBeforeItem=itemsToRemove.get(0).getPrev();
				Item nextAfterItem=itemsToRemove.get(itemsToRemove.size()-1).getNext();
				
//				System.out.println("..............................................................................................");
//				System.out.println("preBeforeItem is " + preBeforeItem);
//				System.out.println("nextAfterItem is " + nextAfterItem);
//				System.out.println("..............................................................................................");
				
				placeholderItem.setPrev(preBeforeItem);		//TODO f
				placeholderItem.setNext(nextAfterItem);
				
				this.stackPlaceholders.push(placeholderItem);			
//				this.queuePlaceholders.offer(placeholderItem);
				
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
				else
				{
//					System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
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


