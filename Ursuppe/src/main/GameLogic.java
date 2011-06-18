package main;



import java.io.Serializable;

import javax.swing.JPanel;

import annotations.OnlyForTesting;

import com.google.inject.Guice;
import com.google.inject.Injector;

import enums.EPhases;

import module.*;
import gameObjectsASCII.*;
import gameObjectsGUI.*;
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
	
	private IPhase phaseA;
	private IPhase phaseExit;
	
	
	private IPhase currentPhase;
	
	private EPhases currentEPhase;

	private Injector injector;
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
		
		//this.phaseA=.createPhaseA();
		//(this.phaseExit=ModuleASCII.createPhaseExit();
//		this.injector = Guice.createInjector(new ModuleASCII());
//		
//		this.phaseA =injector.getInstance(PhaseAASCII.class);
//		this.phaseExit =injector.getInstance(PhaseExitASCII.class);
		 
		this.startAllPhases();
	}
	
	/**
	 * creates all the phases in the ASCII mode
	 */
	public final void createGUI()
	{
		
		this.module=new ModuleGUI();
		
		this.createPhases();
		
//		 this.injector = Guice.createInjector(new ModuleGUI());
//		 
//		 this.phaseA =injector.getInstance(PhaseAGUI.class);
//		 this.phaseExit =injector.getInstance(PhaseExitGUI.class);
		
		 this.startAllPhases();
		 
	}
	
	private void createPhases()
	{
		this.phaseA=this.module.createPhaseA();
		this.phaseExit=this.module.createPhaseExit();
	}
	
	private final void startAllPhases()
	{
		this.phaseA.start();
		this.phaseA.suspend();
		
		this.phaseExit.start();
		this.phaseExit.suspend();
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
	//THREAD//
	//////////
	
	//@Override
	/**
	 * starts the {@code GameLogic}
	 */
	public final void run()
	{
		assert(this.setStartPhase());
		
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
		System.out.println("CURRENT PHASE IN setStartPhase is: " + this.currentEPhase);
		
		boolean valid=true;
		switch(this.currentEPhase)
		{
			case phaseA:
			{
				break;
			}
			case phaseExit:
			{
				//this.currentPhase.setPhase(this.phaseExit);
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
		
		//System.out.println("currentPhase= " +this.currentEPhase + "  changedPhase = " + changedPhase);
		
		if (!this.currentEPhase.equals(changedPhase))
		{
		//	System.out.println("CHANGE!");
			
			changedPhase.setLastPhase(this.currentEPhase);
			
			this.currentEPhase=changedPhase;
			this.activatePhase();
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	/**
	 * starts the currentPhase
	 */
	private void activatePhase()
	{

		System.out.println("CURRENT PHASE " + this.currentEPhase);
		
		switch(this.currentEPhase)
		{
			case phaseA:
			{
				this.currentPhase=this.phaseA;
				this.currentEPhase.setPhase(this.phaseA);
				
				this.phaseA.doStart();
				//this.phaseA.start();
				this.joinCurrentPhase(this.phaseA);
				break;
			}
			case phaseExit:
			{
				this.currentPhase=this.phaseExit;
				this.currentEPhase.setPhase(this.phaseExit);
				
			///	System.out.println("exit PHASE starts now...");

				this.phaseExit.doStart();
				//this.phaseExit.start();
				this.joinCurrentPhase(this.phaseExit);
			//	System.out.println("exit PHASE end now...");
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
			
			//System.out.println("PHASE [ " + phase + " ] running: "+ phase.getIsRunning());
			this.doSleep();
		}
		
//		try 
//		{
//			phase.join();
//		} 
//		catch (InterruptedException e) 
//		{
//			e.printStackTrace();
//		}
	}
	
	
	/**
	 * sleeps 50 milliseconds
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
