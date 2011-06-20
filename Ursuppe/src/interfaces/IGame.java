package interfaces;

import javax.swing.JPanel;

import enums.EColor;
import enums.EPhases;

import annotations.OnlyForTesting;

import gameObjectsGUI.*;
import gameObjectsASCII.*;
import templates.*;
import main.*;

/**
 * is for the different game modes.
 * 
 * 
 * @author Lukas Keller
 * @version 1.0.0
 * 
 * @see GameTemplate
 * @see GameASCII
 * @see GameGUI
 *
 */
public interface IGame 
{
	//////////
	//THREAD//
	//////////
	public void join() throws InterruptedException;
	
	/**
	 * starts the game.
	 * 
	 * <p>
	 * 	calls the method {@link GameTemplate#playExtras()} and starts the game with calling the method {@link GameLogic#start()}
	 * </p>
	 * 
	 */
	
	/**
	 * updates the game and the current phase
	 */
	public void update();
	
	public void play();
	
	/**
	 * create a new game and its  {@link GameLogic}
	 * 
	 * 
	 * <p>
	 * can do some extra things which are implemented in a concrete phase in its method.
	 * can be override by a concrete phase 
	 * </p>
	 * 
	 * @see GameASCII
	 * @see GameGUI
	 */
	public void createNew();
	
	/**
	 * returns if the game is over or not.
	 * @return
	 */
	public boolean isOver();
	
	/**
	 * sets that the game is over;
	 */
	public void setGameOver();
	/**
	 * returns the <code>mainPanel</code>. Only needed for GUI
	 * 
	 * @return 
	 * <ul>
	 * 	<li> if the game is running in <u>ASCII</u> mode: returns <code> null </code>
	 * 	<li> if the game is running in <u>GUI</u> mode: returns the JPanel <code> mainPanel </code>
	 * </ul>
	 */
	public JPanel getMainPanel();
	
	///////////
	//PLAYERS//
	///////////
	
	/**
	 * adds a player the the game if the game doesn't already contain the player
	 * @param player
	 */
	public  void addPlayer(IPlayer player);

	/**
	 * removes a player from the game if the game contains the player
	 * @param player
	 */
	public void removePlayer(IPlayer player);

	/**
	 * returns the<sup>{@code index-th} </sup>player
	 * @param index
	 * @return
	 */
	public IPlayer getPlayer(int index);
	
	/**
	 * Creates a new default player with the color {@code default} and name {@code [SubjectName]}
	 * @return the new default player
	 */
	public  IPlayer createANewPlayer();
	
	/**
	 * Creates a new player
	 * @return the new player
	 */
	public IPlayer createANewPlayer(String name, int age, EColor color);
	///////////
	//SETTERS//
	///////////
	/**
	 * sets the startPhase which will show at the begin of the game.
	 * 
	 * @param startPhase
	 */
	public void setStartPhase(EPhases startPhase);
	
	///////////
	//GETTERS//
	///////////
	/**
	 * gets the startPhase which will show at the begin of the game.
	 * 
	 * @param startPhase
	 */
	public EPhases getStartPhase();
	
	/**
	 * gets the number of real players.
	 * @return
	 */
	public int getNumbersOfPlayers();
	////////////////////
	//ONLY FOR TESTING//
	////////////////////
	/**
	 * returns the {@link GameLogic}
	 * 
	 * @return the GameLogic of the game
	 */
	@OnlyForTesting
	public GameLogic  getGameLogic();
	
	
	@OnlyForTesting
	public void showPlayers();
}
