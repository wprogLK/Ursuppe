package main;



import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JPanel;

import annotations.OnlyForTesting;

import enums.EPhases;
import enums.EPlayingOrder;

import module.*;
import helper.ReadAndWriteFiles;
import helper.SaveAndLoad;
import helper.UserInput;
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
	private IPhase phasePreparation3;
	
	private IPhase phase1;
	private IPhase phase2;
	private IPhase phase3;
	private IPhase phase4;
	private IPhase phase5;
	private IPhase phase6;
	
	private IPhase phaseGameEnd;
	
	
	
	private IPhase currentPhase;
	
	private EPhases currentEPhase;
	
	private ArrayList<IPhase> phasesList=new ArrayList<IPhase>();
	
	private PrintStream outStream;
	private PrintStream errStream;

	//////////
	//CREATE//
	//////////
	public GameLogic(IGame game)
	{
		this.game=game;
		
		this.outStream=this.game.getOutStream();
		this.errStream=this.game.getErrorStream();
		
		boolean validStart=this.setStartPhase();
		
		this.setPriority(10);
	}

	
	
	/**
	 * creates all the phases in the ASCII mode
	 */
	public final void createASCII()
	{
		this.module=new ModuleASCII(this.outStream,this.errStream);
		
		this.createPhases();
		this.preparePhasesList();
		this.setStreams();

		 
		this.startAllPhases();
	}
	
	/**
	 * creates all the phases in the ASCII mode
	 */
	public final void createGUI()
	{
		this.module=new ModuleGUI(this.outStream,this.errStream);
		
		this.createPhases();
		this.preparePhasesList();
		this.setStreams();
		
		this.startAllPhases();
	}
	
	private void preparePhasesList() 
	{
		if(this.phaseA!=null)
			
		this.phasesList.add(this.phaseA);
		this.phasesList.add(this.phaseExit);
		
		this.phasesList.add(this.phaseSplashScreen);
		this.phasesList.add(this.phaseMainMenu);
		this.phasesList.add(this.phaseNewGame);
		this.phasesList.add(this.phaseLoadGame);
		this.phasesList.add(this.phaseOptions);
		this.phasesList.add(this.phaseHelp);
		this.phasesList.add(this.phaseCheats);
		this.phasesList.add(this.phaseAchievements);
		this.phasesList.add(this.phaseAbout);
		
		this.phasesList.add(this.phaseBreakMenu);
		this.phasesList.add(this.phaseStatistics);
		this.phasesList.add(this.phaseSaveGame);
		
		this.phasesList.add(this.phasePreparation1);
		this.phasesList.add(this.phasePreparation2);
		this.phasesList.add(this.phasePreparation3);
		
		this.phasesList.add(this.phase1);
		this.phasesList.add(this.phase2);
		this.phasesList.add(this.phase3);
		this.phasesList.add(this.phase4);
		this.phasesList.add(this.phase5);
		this.phasesList.add(this.phase6);
	}



	private void setStreams()
	{
		for (IPhase phase:this.phasesList)
		{
			if (phase!=null)						// TODO: if clause is only at the moment necessary because not all GUI Phases exist
			{
				phase.setOutStream(this.outStream);
				phase.setErrorStream(this.errStream);
			}
			
		}
		
		//For helper classes:
		ReadAndWriteFiles.setOutStream(this.outStream);
		ReadAndWriteFiles.setErrorStream(this.errStream);
		
		SaveAndLoad.setOutStream(this.outStream);
		SaveAndLoad.setErrorStream(this.errStream);
		
		UserInput.setOutStream(this.outStream);
		UserInput.setErrorStream(this.errStream);
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
		this.phasePreparation3=this.module.createPhasePreparation2();
		
		this.phase1=this.module.createPhase1();
		this.phase2=this.module.createPhase2();
		this.phase3=this.module.createPhase3();
		this.phase4=this.module.createPhase4();
		this.phase5=this.module.createPhase5();
		this.phase6=this.module.createPhase6();
	}
	
	private final void startAllPhases()
	{
		for (IPhase phase:this.phasesList)
		{
			if (phase!=null)						// TODO: if clause is only at the moment necessary because not all GUI Phases exist
			{
				phase.start();
				phase.suspend();
			}
			
		}
		
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
		for (IPhase phase:this.phasesList)
		{
			if (phase!=null)						// TODO: if clause is only at the moment necessary because not all GUI Phases exist
			{
				phase.setMainPanel(mainPanel);
			}
			
		}

	}
	
	
	public final void setGameToAllPhases()
	{
		for (IPhase phase:this.phasesList)
		{
			if (phase!=null)						// TODO: if clause is only at the moment necessary because not all GUI Phases exist
			{
				phase.setGame(this.game);
			}
			
		}
		
	}
	
	
	///////////
	//GETTERS//
	///////////
	
	public IPhase getCurrentPhase() 
	{
//For debugging:
//		this.outStream.println("CURRENT P is " + this.currentPhase);
//		this.outStream.println("CURRENT eP is " + this.currentEPhase);
//		this.outStream.println("CHANGED = " + this.changed);
		
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
		assert(!this.currentEPhase.equals(EPhases.defaultPhase));
		
		this.activatePhase();
		
		while(!this.game.isOver())
		{
			this.doSleep();
			checkPhaseChange();
			
		}
	
		this.outStream.println("END GAME LOGIC ...");
	}
	
	/////////////
	//GAMELOGIC//
	/////////////
	private boolean setStartPhase()
	{
		this.currentEPhase=this.game.getStartPhase();
		
		boolean valid=true;

		return valid;
	}
	
	private boolean checkPhaseChange()
	{
		boolean validCheck=false;
		
		IPhase phaseToCheck=this.currentEPhase.getPhase();
		EPhases changedPhase=phaseToCheck.getCurrentPhase();
		
		if (!this.currentEPhase.equals(changedPhase))
		{
			//this.outStream.println(".............................CHANGE!......................................");
			
			changedPhase.setLastPhase(this.currentEPhase);
			
			this.currentEPhase=changedPhase;
			this.activatePhase();
			return true;
		}
		else
		{
			//this.outStream.println(".............................NOT CHANGE!......................................");
			//this.outStream.println("The changed phase was: " + changedPhase);
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
			case phasePreparation3:
			{
				this.currentPhase=this.phasePreparation3;
				this.currentEPhase.setPhase(this.phasePreparation3);
				
				this.phasePreparation3.doStart();
				this.joinCurrentPhase(this.phasePreparation3);
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
				this.outStream.println("ERROR:Unkown case in activePhase: CURRENT PHASE IS " + this.currentEPhase);
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



	private void turnAllPhasesWaiting() 
	{
		for (IPhase phase:this.phasesList)
		{
			phase.turnOnWaiting();
		}
		
	}
	
	public void turnOnTestMode()
	{
		this.turnAllPhasesWaiting();
		
		for (IPhase phase:this.phasesList)
		{
			phase.turnOnTestMode();
		}
	}



	public EPlayingOrder getCurrentPlayingOrder() 
	{
		return this.getCurrentEPhase().getOrder();
		
	}



	
}
