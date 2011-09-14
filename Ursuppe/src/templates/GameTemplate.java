package templates;

import annotations.*;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JPanel;


import com.google.inject.Injector;

import enums.EColor;
import enums.EPhases;
import enums.EPlayer;
import enums.EPlayingOrder;

import main.GameLogic;

import helper.LanguageSetup;
import helper.Setting;
import interfaces.IBoard;
import interfaces.IDie;
import interfaces.IGame;
import interfaces.IModule;
import interfaces.IPhase;
import interfaces.IPlayer;
import gameObjectsGUI.*;
import gameObjectsASCII.*;
/**
 * is the abstract template for every game. 
 * 
 * @author Lukas Keller
 * @version 1.0.0
 *
 * @see GameASCII
 * @see GameGUI
 */
public abstract class GameTemplate extends LanguageSetup implements IGame{

	protected GameLogic gameLogic;
	protected IModule module;
	
	protected IDie die;
	protected IBoard board;
	
	protected ArrayList<IPlayer> players=new ArrayList<IPlayer>();
	protected boolean isOver=false;
	private EPhases startPhase;
	
	protected PrintStream outStream;
	protected PrintStream errorStream;
	
	protected IPlayer currentPlayer;
	
	protected boolean testModeOn=false;
	
	protected int roundNumber;
	
	private ArrayList<EColor> usedColors=new ArrayList<EColor>();
	
	public GameTemplate(PrintStream out, PrintStream error)
	{
		this.outStream=out;
		this.errorStream=error;
		
		this.roundNumber=1;
	}
	
	/**
	 * sets the {@code mainPanel} which contains all components.
	 * 
	 * <p>
	 * 	only used if {@code game} is running in the GUI mode.
	 * </p>
	 * @param mainPanel
	 */
	protected final void setMainPanelToAllPhases(JPanel mainPanel)
	{
		this.gameLogic.setMainPanelToAllPhases(mainPanel);
	}
	
	//////////
	//CREATE//
	//////////
	protected final void setupPlayers()
	{
		IPlayer playerTail=this.createANewPlayer();
		IPlayer playerHead=this.createANewPlayer();
		
		playerHead.setName("Head");
		playerTail.setName("Tail");
		playerHead.setType(EPlayer.HeadPlayer);
		playerTail.setType(EPlayer.TailPlayer);
		
		
	}
	
	
	@Override
	public final IPlayer createANewPlayer()
	{
		 IPlayer newPlayer = this.module.createAPlayer();
		 
		 this.addPlayer(newPlayer);
		 
		 return newPlayer;
	}
	
	@Override
	public final IPlayer createANewPlayer(String name, Date birthday, int age, EColor color)
	{
		 IPlayer newPlayer = this.module.createAPlayer(name, birthday,age, color);
		
		 this.addPlayer(newPlayer);
		
		 
		 return newPlayer;
	}
	
	//////////
	//BASICS//
	//////////
	
	@Override
	public ArrayList<IPlayer> overrideAllNormalPlayers(ArrayList<IPlayer> players)
	{
		ArrayList<IPlayer> outPlayers=new ArrayList<IPlayer>();
		
		//CREATE OUTPUT
		for(IPlayer player:this.players)
		{
			boolean isPlayerHead=(player.getType()==EPlayer.HeadPlayer);
			boolean isPlayerTail=(player.getType()==EPlayer.TailPlayer);
			
			if(!(isPlayerHead || isPlayerTail)) //if player isn't head or tail
			{
				outPlayers.add(player);
			}
		}
		
		//REMOVE
		for(IPlayer player:outPlayers)
		{
			this.players.remove(player);
		}
		
		//"CREATE" INPUT
		for(IPlayer player:players)
		{
			int secondLastIndex=this.players.size()-2;
			this.players.add(secondLastIndex, player);
		}
		
		return outPlayers;
	}
	
	@Override
	public final void skipTailOrHeadPlayer()
	{	
		this.getCurrentPlayer();
		
		if(this.currentPlayer==null)
		{
			//System.out.println("IN GAME TEMPLATE: ERROR IN SKIP TAIL OR HEAD PLAYER: MESSAGE:: CURRENT PLAYER IS NULL"); 
//			if(!this.testModeOn)				//TODO Uncomment that and fix it!
//			{
//				throw new NullPointerException();
//			}
		}
		else
		{
	
			
			
			boolean isHead=this.currentPlayer.getType()==EPlayer.HeadPlayer;
			boolean isTail=this.currentPlayer.getType()==EPlayer.TailPlayer;
			
			boolean validNextPlayer;
			
			if(isHead || isTail)
			{
				validNextPlayer=false;
			}
			else
			{
				validNextPlayer=true;
			}
			
			
			while (!validNextPlayer)
			{
				validNextPlayer=!this.nextPlayer();
			}
		}
	}
	
	@Override 
	public final int rollDie()
	{
		return this.die.roll();
	}
	
	@Override
	public final IDie getDie()
	{
		return this.die;
	}
	
	@Override
	public void update()
	{
	
	}
	
	@Override
	public final void play()
	{
		Setting.usedColors=this.usedColors;
		
		this.start();
	}
	
	public final void run()
	{
		this.playExtras();
		this.gameLogic.start();
		try 
		{
			this.gameLogic.join();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * can do some extra things which are implemented in a concrete game in its method (see {@link GameASCII#playExtras()  or {@link GameGUI#playExtras()})
	 * can be override by a concrete game 
	 * 
	 * @see GameASCII#playExtras()
	 * @see GameGUI#playExtras()
	 */
	public void playExtras()
	{
		
	}
	
	@Override
	public final void addPlayer(IPlayer player)
	{
		assert(!this.players.contains(player));
		
		if(this.players.size()-1<0)
		{
			this.players.add(player);
		}
		else
		{
			this.players.add(this.players.size()-1, player);
			
			if(player.getColor()!=EColor.Default)
			{
				
				this.usedColors.add(player.getColor());
			}
		}
	}
	
	@Override
	public final void removePlayer(IPlayer player)
	{
		assert(!this.players.isEmpty());
		assert(this.players.contains(player));
		
		this.players.remove(player);
	}
	
	@Override
	public IPlayer getCurrentPlayer()
	{
		this.setCurrentPlayer();
		
		return this.currentPlayer;
	}
	
	
	@Override
	public boolean nextPlayer()
	{
		EPlayingOrder order =this.gameLogic.getCurrentPlayingOrder();
		
		int indexToInsert = 0;

		switch(order)
		{
			case Descending: //Absteigend
			{
				indexToInsert=this.players.size()-1;

				
				break;
			}
			
			case Ascending: //Aufsteigend
			{
				indexToInsert=0;
			
				break;
			}
			
			default:
			{
				//TODO
			}
			
		}
		
		this.players.remove(this.currentPlayer);
		this.players.add(indexToInsert, this.currentPlayer);
		
		this.getCurrentPlayer();
		
		return isCurrentPlayerHeadOrTail();
	}
	
	/**
	 * set the correct current player (depends on the playing order of the current ePhase)
	 */
	private void setCurrentPlayer() 
	{
		EPlayingOrder order=this.gameLogic.getCurrentEPhase().getOrder();
		
		switch(order)
		{
			case Descending:
			{
				this.currentPlayer=this.players.get(0);
				break;
			}
			case Ascending:
			{
				this.currentPlayer=this.players.get(this.players.size()-1);
				break;
			}
		}
	}
	
	/////////
	//BOARD//
	/////////
	
	@Override
	@OnlyForASCII
	public void showBoard()
	{
		//do nothing
	}

	///////////
	//GETTERS//
	///////////
	@Override
	public final int getCurrentRoundNumber()
	{
		return this.roundNumber;
	}
	
	public PrintStream getErrorStream()
	{
		return this.errorStream;
	}
	
	public PrintStream getOutStream()
	{
		return this.outStream;
	}
	
	@Override
	public int getNumbersOfPlayers()
	{
		int nrOfPlayers=this.players.size()-2;
		
		assert nrOfPlayers>=0;
		
		return nrOfPlayers;
	}
	
	
	@Override
	public JPanel getMainPanel() 
	{
		return null;
	}
	
	@Override
	public final IPlayer getPlayer(int index)
	{
		assert(this.players.size()-1>=index && index>=0);
		
		return this.players.get(index);
	}
	
	@Override
	public final boolean isOver()
	{
		return this.isOver;
	}
	
	public final EPhases getStartPhase()
	{
		return this.startPhase;
	}
	
	@Override
	public final IPhase getCurrentPhase()
	{
		return this.gameLogic.getCurrentPhase();
	}
	
	@Override
	public EPhases getCurrentEPhase()
	{
		return this.gameLogic.getCurrentEPhase();
	}
	
	///////////
	//SETTERS//
	///////////
	public final void turnOnTestMode()
	{
		this.testModeOn=true;
		
		this.turnOnTestModeEveryWhere();
		
		this.gameLogic.turnOnTestMode();
	}

	public final void turnOnCurrentPhaseWaiting()
	{
		this.gameLogic.getCurrentPhase().turnOnWaiting();
	}
	
	public final void turnOffCurrentPhaseWaiting()
	{
		this.gameLogic.getCurrentPhase().turnOffWaiting();
	}
	
	@Override
	public final void setGameOver()
	{
		this.isOver=true;
	}
	
	public final void setStartPhase(EPhases startPhase)
	{
		this.startPhase=startPhase;
	}

	///////////////////
	//PRIVATE METHODS//
	///////////////////
	private void turnOnTestModeEveryWhere() 
	{
		this.die.turnOnTestMode();
		//TODO Every other object turn on here!
		
	}
	
	private boolean isCurrentPlayerHeadOrTail() 
	{
		if(this.currentPlayer==null)
		{
			System.out.println("Current player was NULL");
			return true;
		}
		
		else if (this.currentPlayer.getType()==EPlayer.HeadPlayer)
		{
			return true;
		}
		else if(this.currentPlayer.getType()==EPlayer.TailPlayer)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	////////////////////
	//ONLY FOR TESTING//
	////////////////////
	@Override
	@OnlyForTesting
	public final GameLogic  getGameLogic()
	{
		return this.gameLogic;
	}
	
	@Override
	@OnlyForTesting
	public void showPlayers()
	{
		this.outStream.println("-------PLAYERS HEAD:-----");
		
		for(int i=0; i<this.players.size();i++)
		{
			this.outStream.println("Player " + this.players.get(i).getName());
		}
		
		this.outStream.println("-------PLAYERS TAIL:-----");
	}
	
	public ArrayList<IPlayer> getPlayOrder()
	{
		return this.players;
	}
	
	@Override
	@OnlyForTesting
	public IBoard getBoard()
	{
		return this.board;
	}

}
