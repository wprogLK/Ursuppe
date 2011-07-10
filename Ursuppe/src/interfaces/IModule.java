 package interfaces;

import enums.EColor;
import gameObjectsASCII.*;
import interfaces.IGame;
import interfaces.IPhase;
import interfaces.IPlayer;
import module.*;

/**
 * used if {@code game} is running in ASCII mode.
 * 
 * @author Lukas Keller
 * @version 1.0.1
 *
 * @see IPhase
 * @see PhaseAASCII
 * @see IGame
 * @see GameASCII
 * 
 * @see ModuleASCII
 * @see ModuleGUI
 */
public  interface IModule
{
	
	public IPhase createPhaseA();
	public IPhase createPhaseExit();
	
	
	public  IGame createGame();
	
	/**
	 * Creates a new default player with the color {@code default} and name {@code [SubjectName]}
	 * @return
	 */
	public  IPlayer createAPlayer();
	
	/**
	 * Creates a new concrete player
	 * @param name
	 * @param age
	 * @param color
	 * @return
	 */
	public IPlayer createAPlayer(String name, int age, EColor color);
	
}
