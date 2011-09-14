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
public abstract class PhaseGameEndLogic extends PhaseTemplateLogic
{
	//////////
	//BASICS//
	//////////

	//////////
	//INPUTS//
	//////////
	
	public PhaseGameEndLogic(IModule module) 
	{
		super(module);
	}


	private String name; 	//ACTION A
	private int age;		//ACTION B

		////////////
		//...LOGIC//
		////////////
		@Override
		public final void changeActionToRun()
		{
			this.activateActionA();
		}
		
	//////////
	//BASICS//
	//////////
	@Override
	protected void setCurrentPhase()
	{
		this.currentPhase=EPhases.phaseA;
	}
	
	
		
	///////////////
	//RUN ACTIONS//
	///////////////

	

	////////////
	//ACTION A//
	////////////
	
	@Override
	public  void setInputA(Object inputA) throws Exception
	{
		if(this.getDoRunActionA())
		{
			boolean validBasic = false;
			this.isInputNew=true;
			
			validBasic=this.checkBasicInputs(inputA);
			
			if(!validBasic)
			{
				this.checkInputActionA(inputA);
			}
		}
	}
	
	
	@Override
	public final void checkInputActionA(Object inputA)throws Exception
	{
		String inputString=this.doCastToString(inputA);
		
		if (!inputString.equals(""))
		{
			this.game.getPlayer(1).setName(this.doCastToString(inputA)); 
			this.isInputValid=true;
		}
		else
		{
			this.module.throwInputExceptionEmptyInput();
		}
	}
	
	
	

	///////////
	//SETTERS//
	///////////
	
	
	

	
	
	///////////
	//GETTERS//
	///////////
	
	
	///////////////////
	//PRIVATE METHODS//
	///////////////////
	
	
}
