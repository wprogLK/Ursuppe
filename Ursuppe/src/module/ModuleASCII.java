 package module;

import java.io.PrintStream;
import java.util.Date;

import enums.EColor;
import gameObjectsASCII.*;
import helper.UserInput;
import interfaces.IAmoeba;
import interfaces.IBoard;
import interfaces.ICompassSquare;
import interfaces.IDie;
import interfaces.IGame;
import interfaces.IModule;
import interfaces.IPhase;
import interfaces.IPlayer;
import interfaces.ISoupSquare;

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
	private PrintStream outStream;
	private PrintStream errorStream;
	
	
	public ModuleASCII(PrintStream out, PrintStream err)
	{
		this.outStream=out;
		this.errorStream=err;
		
		UserInput.turnOnASCIIMode();
	}
	
	
	public IPhase createPhaseA()
	{
		return new PhaseAASCII();
	}
	
	public IPhase createPhaseAbout()
	{
		return new PhaseAboutASCII();
	}
	public IPhase createPhaseSplashScreen()
	{
		return new PhaseSplashScreenASCII();
	}
	public IPhase createPhaseMainMenu()
	{
		return new PhaseMainMenuASCII();
	}
	public IPhase createPhaseNewGame()
	{
		return new PhaseNewGameASCII();
	}
	public IPhase createPhaseLoadGame()
	{
		return new PhaseLoadGameASCII();
	}
	public IPhase createPhaseOptions()
	{
		return new PhaseOptionsASCII();
	}
	public IPhase createPhaseHelp()
	{
		return new PhaseHelpASCII();
	}
	public IPhase createPhaseCheats()
	{
		return new PhaseCheatsASCII();
	}
	public IPhase createPhaseAchievements()
	{
		return new PhaseAchievementsASCII();
	}
	public IPhase createPhaseExit()
	{
		return new PhaseExitASCII();
	}
	
	public IPhase createPhasePreparation1()
	{
		return new PhasePreparation1ASCII();
	}
	public IPhase createPhasePreparation2()
	{
		return new PhasePreparation2ASCII();
	}
	public IPhase createPhase1()
	{
		return new Phase1ASCII();
	}
	public IPhase createPhase2()
	{
		return new Phase2ASCII();
	}
	public IPhase createPhase3()
	{
		return new Phase3ASCII();
	}
	public IPhase createPhase4()
	{
		return new Phase4ASCII();
	}
	public IPhase createPhase5()
	{
		return new Phase5ASCII();
	}
	public IPhase createPhase6()
	{
		return new Phase6ASCII();
	}
	
	public IPhase createPhaseGameEnd()
	{
		return new PhaseGameEndASCII();
	}
	public IPhase createPhaseStatistics()
	{
		return new PhaseStatisticsASCII();
	}
	public IPhase createPhaseSaveGame()
	{
		return new PhaseSaveGameASCII();
	}
	public IPhase createPhaseBreakMenu()
	{
		return new PhaseBreakMenuASCII();
	}
	
	
	
	
	@Override
	public IGame createGame()
	{
		return new GameASCII(this.outStream,this.errorStream);
	}
	
	@Override
	public IPlayer createAPlayer()
	{
		return new PlayerASCII();
	}
	
	@Override
	public IPlayer createAPlayer(String name, Date birthday, int age, EColor color)
	{
		return new PlayerASCII(name,birthday,age,color);
	}
	
	@Override
	public IDie createDie()
	{
		return new DieASCII();
	}


	@Override
	public IAmoeba createAmoeba() 
	{
		//TODO
		return null;
	}


	@Override
	public IAmoeba createAmoeba(EColor color, int number) 
	{
		//TODO
		return null;
	}


	@Override
	public IBoard createBoard() 
	{
		//TODO
		return null;
	}


	@Override
	public ISoupSquare createSoupSquare() 
	{
		//TODO
		return null;
	}


	@Override
	public ICompassSquare createCompassSquare() 
	{
		//TODO
		return null;
	}
	
}
