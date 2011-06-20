 package module;

import enums.EColor;
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
	
	
	
	
	@Override
	public IGame createGame()
	{
		return new GameASCII();
	}
	
	@Override
	public IPlayer createAPlayer()
	{
		return new PlayerASCII();
	}
	
	@Override
	public IPlayer createAPlayer(String name, int age, EColor color)
	{
		return new PlayerASCII(name,age,color);
	}
	
}
