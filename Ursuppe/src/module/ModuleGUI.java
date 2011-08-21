package module;

import java.io.PrintStream;
import java.util.Date;

import enums.EColor;
import gameObjectsASCII.PhaseAASCII;
import gameObjectsASCII.PlayerASCII;
import gameObjectsGUI.*;
import helper.UserInput;
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
	private PrintStream outStream;
	private PrintStream errorStream;
	
	
	public ModuleGUI(PrintStream out, PrintStream err)
	{
		this.outStream=out;
		this.errorStream=err;
		
		UserInput.turnOnGUIMode();
	}
	
	
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
		return new GameGUI(this.outStream, this.errorStream);
	}

	@Override
	public IPlayer createAPlayer() 
	{
		return new PlayerGUI();
	}
	
	@Override
	public IPlayer createAPlayer(String name, Date birthday, int age, EColor color)
	{
		return new PlayerGUI(name,birthday,age,color);
	}


	@Override
	public IPhase createPhaseSplashScreen() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public IPhase createPhaseMainMenu() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public IPhase createPhaseNewGame() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public IPhase createPhaseLoadGame() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public IPhase createPhaseSaveGame() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public IPhase createPhaseOptions() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public IPhase createPhaseHelp() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public IPhase createPhaseCheats() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public IPhase createPhaseAchievements() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public IPhase createPhaseAbout() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public IPhase createPhaseStatistics() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public IPhase createPhaseBreakMenu() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public IPhase createPhasePreparation1() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public IPhase createPhasePreparation2() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public IPhase createPhase1() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public IPhase createPhase2() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public IPhase createPhase3() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public IPhase createPhase4() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public IPhase createPhase5() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public IPhase createPhase6() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public IPhase createPhaseGameEnd() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
