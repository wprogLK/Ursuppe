package logics;

import enums.EPhases;
import exceptions.InputException;
import templates.PhaseTemplateLogic;
import interfaces.IModule;
import interfaces.IPhase;

/**
 * 
 * @author Lukas Keller
 * @version 1.0.0
 * 
 * @see IPhase
 */
public abstract class PhaseSplashScreenLogic extends PhaseTemplateLogic
{
	//////////
	//BASICS//
	//////////

	public PhaseSplashScreenLogic(IModule module) 
	{
		super(module);
	}

		//////////
	//INPUTS//
	//////////
													//ACTION A: start
		////////////
		//...LOGIC//
		////////////
		@Override
		public final void changeActionToRun()
		{
			this.activateActionA();
		}
		
		////////////
		//ACTION A//
		////////////
		
		@Override
		public  void setInputA(Object inputA) throws InputException
		{
			if(this.getDoRunActionA())
			{
				this.isInputNew=true;
				this.checkInputActionA(inputA);
			}
		}
		
		
		@Override
		public final void checkInputActionA(Object inputA) throws InputException
		{
			String inputString="";
			
			if(!this.tryCastToString(inputA))
			{
				throw this.module.createInputExceptionParseToString();
			}
			else
			{
				inputString=this.doCastToString(inputA);
			}
			
			if(this.inputEqualsStart(inputString))
			{
				this.currentPhase=EPhases.phaseMainMenu;
				
				this.isInputValid=true;
			}
			else
			{
				throw this.module.createInputExceptionUnkownInstruction(inputString);
			}
		}
		
	//////////
	//BASICS//
	//////////
	@Override
	protected void setCurrentPhase()
	{
		this.currentPhase=EPhases.phaseSplashScreen;
	}
	
	///////////////////
	//PRIVATE METHODS//
	///////////////////
	/**
	 * checks if an inputString is equals "start" or "Start"
	 * @return 
	 * 		- true
	 * 		<br/>
	 * 		or
	 * 		<br/> 
	 * 		- false
	 */
	private final boolean inputEqualsStart(String inputString)
	{
		if(inputString.toLowerCase().equals(this.rb.getString("instructionStart")))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
}
