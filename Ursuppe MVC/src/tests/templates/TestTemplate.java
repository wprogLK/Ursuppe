
package tests.templates;

import static org.junit.Assert.assertThat;

import java.io.PrintStream;

import tests.hamcrest.CheckEPhases;
import tests.hamcrests.CheckEPhase;
import enums.EPhase;

import helpers.Setting;
import helpers.UserInput;
import interfaces.IGame;
import interfaces.IModule;




/**
 * @author Lukas Keller
 * @version 1.0.0
 *
 */
public abstract class TestTemplate extends Thread
{
	protected final PrintStream errStream= Setting.testErr;
	
	public TestTemplate()
	{
		super();
		
		UserInput.turnOnTestMode();
	}
	
	public abstract void createModule();
	
	////////////
	//WAITINGS//
	////////////
	
	protected final void waitingBetweenTwoInputs()
	{
		try 
		{
			Thread.sleep(1000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
		
//	protected final void waitingForPhaseReady(IPhase currentPhase)
//	{
//		while(!currentPhase.isWaiting())
//		{
////			System.out.println("WAIT " + currentPhase);
//			this.waitingGeneral();
//		}
//	}
	
	protected final void waitingGeneral()
	{
		try 
		{
			Thread.sleep(500);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	////////////////////
	//HAMCREST ASSERTS//
	////////////////////
	protected final void assertCurrentEPhase(IGame game, EPhase ePhase)
	{
		assertThat(game.getCurrentEPhase(),CheckEPhase.checkEPhases(ePhase));
	}
	
}






		
		
