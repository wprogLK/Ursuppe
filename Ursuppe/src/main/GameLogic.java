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
	
	private IPhase phasePreparation1;
	private IPhase phasePreparation2;
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
		
		this.phaseSplashScreen=this.module.createPhaseSplashScreen();
		this.phaseMainMenu=this.module.createPhaseMainMenu();
		this.phaseNewGame=this.module.createPhaseNewGame();
		this.phaseLoadGame=this.module.createPhaseLoadGame();
		this.phaseOptions=this.module.createPhaseOptions();
		this.phaseHelp=this.module.createPhaseHelp();
		this.phaseCheats=this.module.createPhaseCheats();
		this.phaseAchievements=this.module.createPhaseAchievements();
		this.phaseAbout=this.module.createPhaseAbout();
		
		this.phaseBreakMenu=this.module.createPhaseBreakMenu();
		this.phaseStatistics=this.module.createPhaseStatistics();
		this.phaseSaveGame=this.module.createPhaseSaveGame();
		
		this.phasePreparation1=this.module.createPhasePreparation1();
		this.phasePreparation2=this.module.createPhasePreparation2();
		this.phase1=this.module.createPhase1();
		this.phase2=this.module.createPhase2();
		this.phase3=this.module.createPhase3();
		this.phase4=this.module.createPhase4();
		this.phase5=this.module.createPhase5();
		this.phase6=this.module.createPhase6();
	}
	
	private final void startAllPhases()
	{
		this.phaseA.start();
		this.phaseA.suspend();
		
	
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
		
		
		this.phasePreparation1.start();
		this.phasePreparation1.suspend();
		
		this.phasePreparation2.start();
		this.phasePreparation2.suspend();
		
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
		
		this.phaseSplashScreen.setMainPanel(mainPanel);
		this.phaseMainMenu.setMainPanel(mainPanel);
		this.phaseNewGame.setMainPanel(mainPanel);
		this.phaseLoadGame.setMainPanel(mainPanel);
		this.phaseOptions.setMainPanel(mainPanel);
		this.phaseHelp.setMainPanel(mainPanel);
		this.phaseCheats.setMainPanel(mainPanel);
		this.phaseAchievements.setMainPanel(mainPanel);
		this.phaseAbout.setMainPanel(mainPanel);
		this.phaseStatistics.setMainPanel(mainPanel);
		this.phaseSaveGame.setMainPanel(mainPanel);
		this.phaseBreakMenu.setMainPanel(mainPanel);
		
		this.phase1.setMainPanel(mainPanel);
		this.phase2.setMainPanel(mainPanel);
		this.phase3.setMainPanel(mainPanel);
		this.phase4.setMainPanel(mainPanel);
		this.phase5.setMainPanel(mainPanel);
		this.phase6.setMainPanel(mainPanel);
	}
	
	
	public final void setGameToAllPhases()
	{
		this.phaseA.setGame(this.game);
		this.phaseExit.setGame(this.game);
		
		this.phaseSplashScreen.setGame(this.game);
		this.phaseMainMenu.setGame(this.game);
		this.phaseNewGame.setGame(this.game);
		this.phaseLoadGame.setGame(this.game);
		this.phaseOptions.setGame(this.game);
		this.phaseHelp.setGame(this.game);
		this.phaseCheats.setGame(this.game);
		this.phaseAchievements.setGame(this.game);
		this.phaseAbout.setGame(this.game);
		this.phaseStatistics.setGame(this.game);
		this.phaseSaveGame.setGame(this.game);
		this.phaseBreakMenu.setGame(this.game);
		
		this.phase1.setGame(this.game);
		this.phase2.setGame(this.game);
		this.phase3.setGame(this.game);
		this.phase4.setGame(this.game);
		this.phase5.setGame(this.game);
		this.phase6.setGame(this.game);
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
//		switch(this.currentEPhase)
//		{
//			case phaseA:
//			{
//				break;
//			}
//			case phaseExit:
//			{
//				break;
//			}
//			default:
//			{
//				System.out.println("ERROR:Unkown case in setStartPhase: CURRENT PHASE IS " + this.currentEPhase);
//				valid=false;
//			}
//		}
		return valid;
	}
	
	private boolean checkPhaseChange()
	{
		boolean validCheck=false;
		
		IPhase phaseToCheck=this.currentEPhase.getPhase();
		EPhases changedPhase=phaseToCheck.getCurrentPhase();
		
		if (!this.currentEPhase.equals(changedPhase))
		{
			//System.out.println(".............................CHANGE!......................................");
			
			changedPhase.setLastPhase(this.currentEPhase);
			
			this.currentEPhase=changedPhase;
			this.activatePhase();
			return true;
		}
		else
		{
			//System.out.println(".............................NOT CHANGE!......................................");
			//System.out.println("The changed phase was: " + changedPhase);
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
			case phaseSplashScreen:
			{
				this.currentPhase=this.phaseSplashScreen;
				this.currentEPhase.setPhase(this.phaseSplashScreen);
				
				this.phaseSplashScreen.doStart();
				this.joinCurrentPhase(this.phaseSplashScreen);
				break;
			}
			case phaseMainMenu:
			{
				this.currentPhase=this.phaseMainMenu;
				this.currentEPhase.setPhase(this.phaseMainMenu);
				
				this.phaseMainMenu.doStart();
				this.joinCurrentPhase(this.phaseMainMenu);
				break;
			}
			case phaseNewGame:
			{
				this.currentPhase=this.phaseNewGame;
				this.currentEPhase.setPhase(this.phaseNewGame);
				
				this.phaseNewGame.doStart();
				this.joinCurrentPhase(this.phaseNewGame);
				break;
			}
			case phaseLoadGame:
			{
				this.currentPhase=this.phaseLoadGame;
				this.currentEPhase.setPhase(this.phaseLoadGame);
				
				this.phaseLoadGame.doStart();
				this.joinCurrentPhase(this.phaseLoadGame);
				break;
			}
			case phaseSaveGame:
			{
				this.currentPhase=this.phaseSaveGame;
				this.currentEPhase.setPhase(this.phaseSaveGame);
				
				this.phaseSaveGame.doStart();
				this.joinCurrentPhase(this.phaseSaveGame);
				break;
			}
			case phaseOptions:
			{
				this.currentPhase=this.phaseOptions;
				this.currentEPhase.setPhase(this.phaseOptions);
				
				this.phaseOptions.doStart();
				this.joinCurrentPhase(this.phaseOptions);
				break;
			}
			case phaseHelp:
			{
				this.currentPhase=this.phaseHelp;
				this.currentEPhase.setPhase(this.phaseHelp);
				
				this.phaseHelp.doStart();
				this.joinCurrentPhase(this.phaseHelp);
				break;
			}
			case phaseCheats:
			{
				this.currentPhase=this.phaseCheats;
				this.currentEPhase.setPhase(this.phaseCheats);
				
				this.phaseCheats.doStart();
				this.joinCurrentPhase(this.phaseCheats);
				break;
			}
			case phaseAchievements:
			{
				this.currentPhase=this.phaseAchievements;
				this.currentEPhase.setPhase(this.phaseAchievements);
				
				this.phaseAchievements.doStart();
				this.joinCurrentPhase(this.phaseAchievements);
				break;
			}
			case phaseAbout:
			{
				this.currentPhase=this.phaseAbout;
				this.currentEPhase.setPhase(this.phaseAbout);
				
				this.phaseAbout.doStart();
				this.joinCurrentPhase(this.phaseAbout);
				break;
			}
			case phasePreparation1:
			{
				this.currentPhase=this.phasePreparation1;
				this.currentEPhase.setPhase(this.phasePreparation1);
				
				this.phasePreparation1.doStart();
				this.joinCurrentPhase(this.phasePreparation1);
				break;
			}
			case phasePreparation2:
			{
				this.currentPhase=this.phasePreparation2;
				this.currentEPhase.setPhase(this.phasePreparation2);
				
				this.phasePreparation2.doStart();
				this.joinCurrentPhase(this.phasePreparation2);
				break;
			}
			case phase1:
			{
				this.currentPhase=this.phase1;
				this.currentEPhase.setPhase(this.phase1);
				
				this.phase1.doStart();
				this.joinCurrentPhase(this.phase1);
				break;
			}
			case phase2:
			{
				this.currentPhase=this.phase2;
				this.currentEPhase.setPhase(this.phase2);
				
				this.phase2.doStart();
				this.joinCurrentPhase(this.phase2);
				break;
			}
			case phase3:
			{
				this.currentPhase=this.phase3;
				this.currentEPhase.setPhase(this.phase3);
				
				this.phase3.doStart();
				this.joinCurrentPhase(this.phase3);
				break;
			}
			case phase4:
			{
				this.currentPhase=this.phase4;
				this.currentEPhase.setPhase(this.phase4);
				
				this.phase4.doStart();
				this.joinCurrentPhase(this.phase4);
				break;
			}
			case phase5:
			{
				this.currentPhase=this.phase5;
				this.currentEPhase.setPhase(this.phase5);
				
				this.phase5.doStart();
				this.joinCurrentPhase(this.phase5);
				break;
			}
			case phase6:
			{
				this.currentPhase=this.phase6;
				this.currentEPhase.setPhase(this.phase6);
				
				this.phase6.doStart();
				this.joinCurrentPhase(this.phase6);
				break;
			}
			case phaseStatistics:
			{
				this.currentPhase=this.phaseStatistics;
				this.currentEPhase.setPhase(this.phaseStatistics);
				
				this.phaseStatistics.doStart();
				this.joinCurrentPhase(this.phaseStatistics);
				break;
			}
			case phaseBreakMenu:
			{
				this.currentPhase=this.phaseBreakMenu;
				this.currentEPhase.setPhase(this.phaseBreakMenu);
				
				this.phaseBreakMenu.doStart();
				this.joinCurrentPhase(this.phaseBreakMenu);
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
