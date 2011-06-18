 package interfaces;

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
	
	public  IPlayer createPlayer();
	
}
