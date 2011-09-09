
package templates;

import static org.junit.Assert.assertThat;

import java.io.PrintStream;

import module.ModuleASCII;
import module.ModuleGUI;
import tests.hamcrest.CheckEPhases;
import enums.EPhases;
import helper.Setting;
import helper.UserInput;
import interfaces.IGame;
import interfaces.IModule;
import interfaces.IPhase;




/**
 * @author Lukas Keller
 * @version 1.0.0
 *
 */
public abstract class TestTemplate extends Thread
{
	protected final PrintStream outStream= Setting.testOut;
	protected final PrintStream errStream= Setting.testErr;
	
	protected  IModule module;
	
	
	public TestTemplate()
	{
		super();
		
		this.createModule();
		
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
		
	protected final void waitingForPhaseReady(IPhase currentPhase)
	{
		while(!currentPhase.isWaiting())
		{
//			System.out.println("WAIT " + currentPhase);
			this.waitingGeneral();
		}
	}
	
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
	protected final void assertCurrentEPhase(IGame game, EPhases ePhase)
	{
		assertThat(game.getCurrentEPhase(),CheckEPhases.checkEPhases(ePhase));
	}
	
}






		
		
