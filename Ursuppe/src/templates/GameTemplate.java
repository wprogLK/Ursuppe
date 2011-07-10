package templates;

import annotations.*;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JPanel;


import com.google.inject.Injector;

import enums.EColor;
import enums.EPhases;

import main.GameLogic;

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
public abstract class GameTemplate extends Thread implements IGame{

	protected GameLogic gameLogic;
	protected IModule module;
	
	
	protected ArrayList<IPlayer> players=new ArrayList<IPlayer>();
	protected boolean isOver=false;
	private EPhases startPhase;
	
	
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
		
	}
	
	
	@Override
	public final IPlayer createANewPlayer()
	{
		 IPlayer newPlayer = this.module.createAPlayer();
		 
		 this.addPlayer(newPlayer);
		 
		 return newPlayer;
	}
	
	@Override
	public final IPlayer createANewPlayer(String name, int age, EColor color)
	{
		 IPlayer newPlayer = this.module.createAPlayer(name, age, color);
		
		 this.addPlayer(newPlayer);
		 
		 return newPlayer;
	}
	
	//////////
	//BASICS//
	//////////
	@Override
	public void update()
	{
	
	}
	
	@Override
	public final void play()
	{
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
		}
	}
	
	@Override
	public final void removePlayer(IPlayer player)
	{
		assert(!this.players.isEmpty());
		assert(this.players.contains(player));
		
		this.players.remove(player);
	}
	
	///////////
	//GETTERS//
	///////////
	
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

	@Override
	public final void setGameOver()
	{
		this.isOver=true;
	}
	
	public final void setStartPhase(EPhases startPhase)
	{
		this.startPhase=startPhase;
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
		System.out.println("-------PLAYERS HEAD:-----");
		
		for(int i=0; i<this.players.size();i++)
		{
			System.out.println("Player " + this.players.get(i).getName());
		}
		
		System.out.println("-------PLAYERS TAIL:-----");
	}

}
