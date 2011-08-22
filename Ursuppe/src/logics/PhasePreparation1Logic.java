package logics;

import java.util.ArrayList;

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
public abstract class PhasePreparation1Logic extends PhaseTemplateLogic
{
	//////////
	//BASICS//
	//////////

	//////////
	//INPUTS//
	//////////
	
	protected ArrayList<Integer> rolledValue=new ArrayList<Integer>();

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
		this.currentPhase=EPhases.phasePreparation1;
	}
	
	@Override
	public void doPreAction()
	{

	}
	
	@Override
	public final void doAfterAction()
	{
		if(!this.game.nextPlayer())
		{
			this.turnOnRestart();
			System.out.println("CURRENT PLAYER IS: " + this.game.getCurrentPlayer().getName());
			System.out.println("RESTART ON");
		}
		else
		{
			this.turnOffRestart();
			System.out.println("CURRENT PLAYER IS: " + this.game.getCurrentPlayer().getName());
			System.out.println("RESTART OFF");
		}
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
		boolean validBasic = false;
		this.isInputNew=true;
		
		validBasic=this.checkBasicInputs(inputA);
		if(validBasic)
		{
			return true;
		}
		else
		{
			boolean valid=this.checkInputActionA(inputA);
			this.isInputValid=valid;
			return valid;
		}
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
		
		
		if (!inputString.equals(""))
		{
			this.game.getPlayer(1).setName(this.doCastToString(inputA)); 
			return true;
		}
		else
		{
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
			this.game.getPlayer(1).setAge(this.doCastToInteger(inputB)); 
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
