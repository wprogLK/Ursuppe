package module;

import gameObjectsASCII.PhaseAASCII;
import gameObjectsGUI.*;
import interfaces.IGame;
import interfaces.IModule;
import interfaces.IPhase;
import interfaces.IPlayer;

import com.google.inject.AbstractModule;
/**
 * used if {@code game} is running in GUI mode.
 * 
 * @author Lukas Keller
 * @version 1.0.1
 *
 * @see IPhase
 * @see PhaseAGUI
 * @see IGame
 * @see GameGUI
 */
public class ModuleGUI implements IModule
{
	public IPhase createPhaseA() 
	{
		return new PhaseAGUI();
	}
	public IPhase createPhaseExit()
	{
		return new PhaseExitGUI();
	}
	
	
	
	
	
	
	public IGame createGame()
	{
		return new GameGUI();
	}

	public IPlayer createPlayer() 
	{
		return new PlayerGUI();
	}

}
