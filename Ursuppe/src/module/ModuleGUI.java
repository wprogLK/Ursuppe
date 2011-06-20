package module;

import enums.EColor;
import gameObjectsASCII.PhaseAASCII;
import gameObjectsASCII.PlayerASCII;
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
	@Override
	public IPhase createPhaseA() 
	{
		return new PhaseAGUI();
	}
	@Override
	public IPhase createPhaseExit()
	{
		return new PhaseExitGUI();
	}
	
	@Override
	public IGame createGame()
	{
		return new GameGUI();
	}

	@Override
	public IPlayer createAPlayer() 
	{
		return new PlayerGUI();
	}
	
	@Override
	public IPlayer createAPlayer(String name, int age, EColor color)
	{
		return new PlayerGUI(name,age,color);
	}
	
	
	
}
