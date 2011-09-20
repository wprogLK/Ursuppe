
package tests.templates;

import static org.junit.Assert.assertThat;

import java.io.PrintStream;
import java.util.ArrayList;

import org.hamcrest.Matcher;

import annotations.OnlyForTesting;

import tests.hamcrests.CheckEPhase;
import enums.EPhase;

import helpers.ReadAndWriteFiles;
import helpers.Setting;
import helpers.UserInput;
import interfaces.IModule;
import interfaces.IUrsuppe;




/**
 * @author Lukas Keller
 * @version 1.0.0
 *
 */
public abstract class TestTemplate
{
	protected final PrintStream errStream= Setting.testErr;
	protected final PrintStream outStream= Setting.testOut;
	
	protected IModule module;
	
	private String fileName;
	protected String fileComplete;
	
	private String[] fakedUserInput;
	
	public TestTemplate()
	{
		super();
	}
	
	protected final void prepareForTesting(final String fileName, final String[] fakedUserInput)
	{
		this.fileName=fileName;
		this.fakedUserInput=fakedUserInput;
		
		this.createFakeUserInput();
		
		UserInput.turnOnTestMode();
	}
	
	
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
	
	
	private final void createFakeUserInput()
	{
		this.fileComplete=Setting.pathTestFiles+this.fileName;
		UserInput.setTestingFileName(this.fileComplete);

		ReadAndWriteFiles.writeFile(this.fakedUserInput, this.fileComplete);
	}
	

	
	////////////////////
	//HAMCREST ASSERTS//
	////////////////////
	protected final void assertCurrentEPhase(IUrsuppe ursuppe, EPhase ePhase)
	{
		assertThat(ursuppe.getCurrentEPhase(),CheckEPhase.checkEPhases(ePhase));
	}

	
		
	
	
}






		
		
