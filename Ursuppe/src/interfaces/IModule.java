 package interfaces;

import java.io.PrintStream;
import java.util.Date;

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
	
	public IPhase createPhaseSplashScreen();
	public IPhase createPhaseMainMenu();
	public IPhase createPhaseNewGame();
	public IPhase createPhaseLoadGame();
	public IPhase createPhaseSaveGame();
	public IPhase createPhaseOptions();
	public IPhase createPhaseHelp();
	public IPhase createPhaseCheats();
	public IPhase createPhaseAchievements();
	public IPhase createPhaseExit();
	public IPhase createPhaseAbout();
	public IPhase createPhaseStatistics();
	
	public IPhase createPhaseBreakMenu();
	
	public IPhase createPhasePreparation1();
	public IPhase createPhasePreparation2();
	public IPhase createPhase1();
	public IPhase createPhase2();
	public IPhase createPhase3();
	public IPhase createPhase4();
	public IPhase createPhase5();
	public IPhase createPhase6();
	public IPhase createPhaseGameEnd();
	
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
	public IPlayer createAPlayer(String name, Date birthday, int age, EColor color);
	
}
