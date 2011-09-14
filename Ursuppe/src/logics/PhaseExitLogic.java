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
public abstract class PhaseExitLogic extends PhaseTemplateLogic
{
	public PhaseExitLogic(IModule module) 
	{
		super(module);
	}
	
	//////////
	//BASICS//
	//////////

	//////////
	//INPUTS//
	//////////
	
		
		////////////
		//...LOGIC//
		////////////
		@Override
		public void changeActionToRun()
		{
			this.activateActionA();
		}
	//////////
	//BASICS//
	//////////
		@Override
		protected final void setCurrentPhase()
		{
			this.currentPhase=EPhases.phaseExit;
		}
		
	///////////////
	//RUN ACTIONS//
	///////////////

	
	

	////////////
	//ACTION A//
	////////////
	
	@Override
	public void setInputA(Object inputA) throws Exception
	{
		if(this.getDoRunActionA())
		{
			this.isInputNew=true;
			
			this.checkInputActionA(inputA);
		}
	}
	
	
	@Override
	public final void checkInputActionA(Object inputA) throws Exception
	{
		String inputString=this.doCastToString(inputA);
		
		if(this.inputEqualsYes(inputString))
		{
			this.outStream.println("Bye");
			System.exit(0);
			this.isInputValid=true;
		}
		else if(this.inputEqualsNo(inputString))
		{
			if(!this.lastPhase.equals(EPhases.defaultPhase))
			{
				this.currentPhase=this.lastPhase;
				
				this.doNothing=true;
				this.isInputValid=true;
			}
			else
			{
				this.isInputValid=true;
			}
			
		}
		else
		{
			this.module.throwInputExceptionUnkownInstruction(inputString);
		}
	}
	
	
	

	///////////
	//SETTERS//
	///////////
	
	
	

	
	
	///////////
	//GETTERS//
	///////////
	
	
	
}
