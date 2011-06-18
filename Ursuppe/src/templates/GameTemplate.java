package templates;

import annotations.*;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JPanel;


import com.google.inject.Injector;

import enums.EPhases;

import main.GameLogic;

import interfaces.IGame;
import interfaces.IModule;
import interfaces.IPlayer;
import gameObjectsGUI.*;
import gameObjectsASCII.*;
/**
 * is the abstract template for every game. 
 * 
 * @author Lukas Keller
 * @version 1.0.0
 *
 * @see IGame
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
	@Override
	public final IPlayer createANewPlayer()
	{
		System.out.println("------------MODULE IN GAME TEMPLATE: " + this.module + " ------------------");
		
		IPlayer newPlayer = this.module.createPlayer();
		
		 //IPlayer newPlayer =this.injector.getInstance(IPlayer.class);
		 
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
		this.players.add(player);
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

}
