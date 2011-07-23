package main;



import java.io.Serializable;

import javax.swing.JPanel;

import annotations.OnlyForTesting;

import enums.EPhases;

import module.*;
import interfaces.*;

/**
 * creates, manages and run the phases of the game
 * 
 * @author Lukas Keller
 * @version 1.0.0
 *
 */
public class GameLogic extends Thread implements Serializable
{
	private IGame game;
	private IModule module;
	
	private boolean changed=false;
	private IPhase phaseA;
	
	
	private IPhase phaseSplashScreen;
	private IPhase phaseMainMenu;
	private IPhase phaseNewGame;
	private IPhase phaseLoadGame;
	private IPhase phaseOptions;
	private IPhase phaseHelp;
	private IPhase phaseCheats;
	private IPhase phaseAchievements;
	private IPhase phaseAbout;
	private IPhase phaseExit;
	private IPhase phaseStatistics;
	private IPhase phaseSaveGame;
	private IPhase phaseBreakMenu;
	
	private IPhase phase1;
	private IPhase phase2;
	private IPhase phase3;
	private IPhase phase4;
	private IPhase phase5;
	private IPhase phase6;
	
	private IPhase phaseGameEnd;
	
	
	
	private IPhase currentPhase;
	
	private EPhases currentEPhase;

	//////////
	//CREATE//
	//////////
	public GameLogic(IGame game)
	{
		this.game=game;
		this.setPriority(7);
	}

	
	
	/**
	 * creates all the phases in the ASCII mode
	 */
	public final void createASCII()
	{
		this.module=new ModuleASCII();
		
		this.createPhases();
		 
		this.startAllPhases();
	}
	
	/**
	 * creates all the phases in the ASCII mode
	 */
	public final void createGUI()
	{
		this.module=new ModuleGUI();
		
		this.createPhases();
		
		this.startAllPhases();
	}
	
	private void createPhases()
	{
		this.phaseA=this.module.createPhaseA();
		this.phaseExit=this.module.createPhaseExit();
		
		//TODO: TOMORROW
		HERE
	}
	
	private final void startAllPhases()
	{
		this.phaseA.start();
		this.phaseA.suspend();
		
		this.phaseExit.start();
		this.phaseExit.suspend();
		
	
		this.phaseSplashScreen.start();
		this.phaseSplashScreen.suspend();
		
		this.phaseMainMenu.start();
		this.phaseMainMenu.suspend();
		
		this.phaseNewGame.start();
		this.phaseNewGame.suspend();
		
		this.phaseSaveGame.start();
		this.phaseSaveGame.suspend();
		
		this.phaseLoadGame.start();
		this.phaseLoadGame.suspend();
		
		this.phaseOptions.start();
		this.phaseOptions.suspend();
		
		this.phaseHelp.start();
		this.phaseHelp.suspend();
		
		this.phaseCheats.start();
		this.phaseCheats.suspend();
		
		this.phaseAchievements.start();
		this.phaseAchievements.suspend();
		
		this.phaseExit.start();
		this.phaseExit.suspend();
		
		this.phaseAbout.start();
		this.phaseAbout.suspend();
		
		this.phaseBreakMenu.start();
		this.phaseBreakMenu.suspend();
		
		this.phaseStatistics.start();
		this.phaseStatistics.suspend();
		
		
		
		this.phase1.start();
		this.phase1.suspend();
		
		this.phase2.start();
		this.phase2.suspend();
		
		this.phase3.start();
		this.phase3.suspend();
		
		this.phase4.start();
		this.phase4.suspend();
		
		this.phase5.start();
		this.phase5.suspend();
		
		this.phase6.start();
		this.phase6.suspend();
		
	}
	
	
	///////////
	//SETTERS//
	///////////
	/**
	 * sets to all phases the {@code mainPanel}.
	 * 
	 * <p>
	 * 	only used if the game is running in the GUI mode
	 * </p>
	 * @param mainPanel
	 * 			
	 */
	public final void  setMainPanelToAllPhases(JPanel mainPanel)
	{
		this.phaseA.setMainPanel(mainPanel);
		this.phaseExit.setMainPanel(mainPanel);
	}
	
	
	public final void setGameToAllPhases()
	{
		this.phaseA.setGame(this.game);
		this.phaseExit.setGame(this.game);
	}
	
	
	///////////
	//GETTERS//
	///////////
	
	public IPhase getCurrentPhase() 
	{
//For debugging:
//		System.out.println("CURRENT P is " + this.currentPhase);
//		System.out.println("CURRENT eP is " + this.currentEPhase);
//		System.out.println("CHANGED = " + this.changed);
		
		this.changed=false;
		
		return this.currentPhase;
	}
	
	public EPhases getCurrentEPhase()
	{
		return this.currentEPhase;
	}
	
	///////////
	//THREAD//
	//////////
	
	//@Override
	/**
	 * starts the {@code GameLogic}
	 */
	public final void run()
	{
		boolean validStart=this.setStartPhase();
		
		assert(validStart);
		
		assert(!this.currentEPhase.equals(EPhases.defaultPhase));
		
		this.activatePhase();
		
		while(!this.game.isOver())
		{
			this.doSleep();
			checkPhaseChange();
			
		}
	
		System.out.println("END GAME LOGIC ...");
	}
	
	/////////////
	//GAMELOGIC//
	/////////////
	private boolean setStartPhase()
	{
		this.currentEPhase=this.game.getStartPhase();
		
		boolean valid=true;
		switch(this.currentEPhase)
		{
			case phaseA:
			{
				break;
			}
			case phaseExit:
			{
				break;
			}
			default:
			{
				System.out.println("ERROR:Unkown case in setStartPhase: CURRENT PHASE IS " + this.currentEPhase);
				valid=false;
			}
		}
		return valid;
	}
	
	private boolean checkPhaseChange()
	{
		boolean validCheck=false;
		
		IPhase phaseToCheck=this.currentEPhase.getPhase();
		EPhases changedPhase=phaseToCheck.getCurrentPhase();
		
		if (!this.currentEPhase.equals(changedPhase))
		{
			System.out.println(".............................CHANGE!......................................");
			
			changedPhase.setLastPhase(this.currentEPhase);
			
			this.currentEPhase=changedPhase;
			this.activatePhase();
			return true;
		}
		else
		{
			System.out.println(".............................NOT CHANGE!......................................");
			System.out.println("The changed phase was: " + changedPhase);
			return false;
		}
		
	}
	
	/**
	 * starts the currentPhase
	 */
	private void activatePhase()
	{

		this.changed=true;
		
		switch(this.currentEPhase)
		{
			case phaseA:
			{
				this.currentPhase=this.phaseA;
				this.currentEPhase.setPhase(this.phaseA);
				
				this.phaseA.doStart();
				this.joinCurrentPhase(this.phaseA);
				break;
			}
			case phaseExit:
			{
				this.currentPhase=this.phaseExit;
				this.currentEPhase.setPhase(this.phaseExit);
				
				this.phaseExit.doStart();
				this.joinCurrentPhase(this.phaseExit);
				break;
			}
			default:
			{
				System.out.println("ERROR:Unkown case in activePhase: CURRENT PHASE IS " + this.currentEPhase);
			}
		}
	}
	
	/**
	 * Joins the phase
	 * @param phase
	 */
	private void joinCurrentPhase(IPhase phase)
	{
		while(phase.getIsRunning())
		{
			this.doSleep();
		}
	}
	
	
	/**
	 * sleeps 1 milliseconds
	 * 
	 * <p>
	 * used for in each while loop for check other things and listen to other objects
	 * </p>
	 * 
	 */
	public final void doSleep()
	{
		try 
		{
			Thread.sleep(1);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
	////////////////////
	//ONLY FOR TESTING//
	////////////////////
	
	@OnlyForTesting
	public IPhase  getPhaseA()
	{
		return this.phaseA;
	}



	
}
