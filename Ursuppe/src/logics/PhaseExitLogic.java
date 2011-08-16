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
public abstract class PhaseExitLogic extends PhaseTemplateLogic
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
		public void setActionsToRun()
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
		
		if(this.inputEqualsYes(inputString))
		{
			this.outStream.println("Bye");
			System.exit(0);
			return true;
		}
		else if(this.inputEqualsNo(inputString))
		{
			if(!this.lastPhase.equals(EPhases.defaultPhase))
			{
				this.currentPhase=this.lastPhase;
				
				this.doNothing=true;
				return true;
			}
			else
			{
				return true;
			}
			
		}
		else
		{
			this.outStream.println("The input wasn't yes/no. It was " + inputString);
			return false;
		}
	}
	


	////////////
	//ACTION B//
	////////////
	@Override
	public  boolean setInputB(Object inputB)
	{
		String inputString="";
		boolean validBasic = false;
		this.isInputNew=true;
		
		if(!this.tryCastToString(inputB))
		{
			return false;
		}
		else
		{
			inputString=this.doCastToString(inputB);
		}
		
	
		validBasic=this.checkBasicInputs(inputString);
		
		if(validBasic)
		{
			this.isInputValid=true;
			return true;
		}
		else
		{
			boolean valid=this.checkInputActionB(inputString);
			this.isInputValid=valid;
			return valid;
		}
		
	}
	
	
	@Override
	public final boolean checkInputActionB(Object inputB)
	{
		if(!this.tryCastToInteger(inputB))
		{
			return false;
		}
		else
		{
			this.game.getPlayer(0).setAge(this.doCastToInteger(inputB)); 
			return true;
		}
	}
	
	

	///////////
	//SETTERS//
	///////////
	
	
	

	
	
	///////////
	//GETTERS//
	///////////
	
	
	
}
