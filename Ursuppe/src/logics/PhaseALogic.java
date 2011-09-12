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
public abstract class PhaseALogic extends PhaseTemplateLogic
{
	//////////
	//BASICS//
	//////////

	//////////
	//INPUTS//
	//////////
	
	public PhaseALogic(IModule module) 
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
			this.activateActionB();
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
	public  void setInputA(Object inputA) throws InputException
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
	public final void checkInputActionA(Object inputA) throws InputException
	{
		String inputString="";
		
		if(!this.tryCastToString(inputA))
		{
			throw this.module.createInputException("Please enter a string");
		}
		else
		{
			inputString=this.doCastToString(inputA);
		}
		
		
		if (!inputString.equals(""))
		{
			this.game.getPlayer(1).setName(this.doCastToString(inputA)); 
			
			this.isInputValid=true; //TODO: This is new!
		}
		else
		{
			throw this.module.createInputException("An empty name is not allowed");
		}
	}
	


	////////////
	//ACTION B//
	////////////
	@Override
	public  void setInputB(Object inputB) throws InputException
	{
		if(this.getDoRunActionB())
		{
			String inputString="";
			boolean validBasic = false;
			this.isInputNew=true;
			
			if(!this.tryCastToString(inputB))
			{
				throw this.module.createInputException("Please enter some valid input");
			}
			else
			{
				inputString=this.doCastToString(inputB);
			}
			
		
			validBasic=this.checkBasicInputs(inputString);
			
			if(!validBasic)
			{
				this.checkInputActionB(inputString);
			}
		}
		
		
	}
	
	
	@Override
	public final void checkInputActionB(Object inputB) throws InputException
	{
		if(!this.tryCastToInteger(inputB))
		{
			throw this.module.createInputException("Please enter a number");
		}
		else
		{
			this.game.getPlayer(1).setAge(this.doCastToInteger(inputB)); 
			this.isInputValid=true; //TODO: This is new!
		}
	}
	
	

	///////////
	//SETTERS//
	///////////
	
	
	

	
	
	///////////
	//GETTERS//
	///////////
	
	
}
