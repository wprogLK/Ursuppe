 package module;

import gameObjectsASCII.*;
import interfaces.IGame;
import interfaces.IModule;
import interfaces.IPhase;
import interfaces.IPlayer;

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
 */
public class ModuleASCII implements IModule
{
	public IPhase createPhaseA()
	{
		return new PhaseAASCII();
	}
	public IPhase createPhaseExit()
	{
		return new PhaseExitASCII();
	}
	
	
	
	
	
	public IGame createGame()
	{
		return new GameASCII();
	}
	
	public IPlayer createPlayer()
	{
		return new PlayerASCII();
	}
}
