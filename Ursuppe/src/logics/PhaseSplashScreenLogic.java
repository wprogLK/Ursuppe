package logics;

import enums.EPhases;
import templates.PhaseTemplateLogic;
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

	//////////
	//INPUTS//
	//////////

		////////////
		//...LOGIC//
		////////////
		@Override
		public final void setActionsToRun()
		{
			this.activateActionA();
		}
		
		////////////
		//ACTION A//
		////////////
		
		@Override
		public  boolean setInputA(Object inputA)
		{
			this.isInputNew=true;
			boolean valid=this.checkInputActionA(inputA);
			this.isInputValid=valid;
			return valid;
		}
		
		
		@Override
		public final boolean checkInputActionA(Object inputA)
		{
			String inputString="";
			
			if(!this.tryCastToString(inputA))
			{
				return false;
			}
			else
			{
				inputString=this.doCastToString(inputA);
			}
			
			if(this.inputEqualsStart(inputString))
			{
				this.currentPhase=EPhases.phaseMainMenu;
				
				return true;
			}
			else
			{
				return false;
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
