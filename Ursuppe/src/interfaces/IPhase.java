package interfaces;

import java.io.PrintStream;

import javax.swing.JPanel;

import enums.EPhases;

import annotations.OnlyForTesting;

import logics.*;
import main.GameLogic;
import gameObjectsASCII.*;
import gameObjectsGUI.*;
/**
 * is for all phases independently of the mode
 * 
 * @author Lukas Keller
 * @Version 1.0.0
 *
 * @see PhaseALogic
 * @see PhaseAGUI
 * @see PhaseAASCII
 */
public interface IPhase
{
	
	//////////
	//BASICS//
	//////////
	/**
	 * sets the game
	 */
	public void setGame(IGame game);
	
	/**
	 * returns if the Phase is run through the whole logic or is still running
	 * @return
	 */
	public  boolean getIsRunning();
	
	public EPhases getCurrentPhase();
	public EPhases getLastPhase();
	public EPhases getNextPhase();
	
	public void setLastPhase(EPhases lastPhase);
	public void setNextPhase(EPhases nextPhase);
	
	public void setOutStream(PrintStream out);
	public void setErrorStream(PrintStream err);
	
	public void turnOnWaiting();	//At the moment only used for testing
	public void turnOffWaiting();	//At the moment only used for testing
	
	//////////
	//THREAD//
	//////////
	/**
	 * <u>Only (!) </u> for the first time to start the thread. Called by in the GameLogic.
	 */
	public void start();
	public Thread.State getState();
	public void join() throws InterruptedException;
	public void waitForANewInput();
	
	public void resume();
	public void suspend();
	
		//NEW OWN METHODS FOR THREADS
		/**
		 * to activate/run/reRun/restart the phase call this method!
		 */
		public void runPhase();
		public void doStart();
	////////////////
	//ONLY FOR GUI//
	////////////////
	
	/**
	 * sets the {@code mainPanel} to the phase.
	 * 
	 * <p>
	 * 	is only used in GUI mode!
	 * </p>
	 */
	public void setMainPanel(JPanel mainPanel);
	
	////////////////////
	//ONLY FOR TESTING//
	////////////////////
	
	///////////
	//GENERAL//
	///////////
		
		
		public void turnOnTestMode();
		//////////////
		//SET INPUTS//
		//////////////
	
		@OnlyForTesting
		public boolean setInputA(Object inputA);
		
		@OnlyForTesting
		public boolean setInputB(Object inputB);
		
		@OnlyForTesting
		public boolean setInputC(Object inputC);
		
		@OnlyForTesting
		public void setAllInput(String message);
		
	///////
	//GUI//
	///////
		
		/////////////////
		//FAKING INPUTS//
		/////////////////
		
		@OnlyForTesting
		public  void fakeInputA(String inputA);
		
		//////////////////
		//FAKING BUTTONS//
		//////////////////
		
		//*PHASE A*//
		
		@OnlyForTesting
		public  void fakeClickOK();
		
		@OnlyForTesting
		public void fakeClickExit();
	
		//*PHASE EXIT*//
		
		@OnlyForTesting
		public void fakeClickNo();
		
		@OnlyForTesting
		public void fakeClickYes();

		

		
}
