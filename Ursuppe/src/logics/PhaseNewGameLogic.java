package logics;

import enums.EPhases;
import templates.PhaseTemplateLogic;
import interfaces.IPhase;
import interfaces.IPlayer;

/**
 * 
 * @author Lukas Keller
 * @version 1.0.0
 * 
 * @see IPhase
 */
public abstract class PhaseNewGameLogic extends PhaseTemplateLogic
{
	//////////
	//BASICS//
	//////////

	//////////
	//INPUTS//
	//////////
	
										//ACTION A: Do you want to add a player or go back or play?
										//ACTION B: Load an exist player or create a new one?
										//ACTION C: Load (AI and human)
										//ACTION D: Create a new one: Part 1: Name
										//ACTION E: Create a new one: Part 2: age/birthday
										//ACTION F: Create a new one: Part 3: favorite color

		////////////
		//...LOGIC//
		////////////
		@Override
		public final void setActionsToRun()
		{
			this.activateActionA();
		}
		
	//////////
	//BASICS//
	//////////
	@Override
	protected void setCurrentPhase()
	{
		this.currentPhase=EPhases.phaseNewGame;
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
	
	
	private boolean tryUnderstandInputA(String inputA)
	{
		if (inputA.equals(this.rb.getString("instructionPhaseNewGameAddPlayer")))	//add new player
		{
			this.activateActionB();
			return true;
		}
		else if (inputA.equals(this.rb.getString("instructionPhaseNewGamePlay")))	//play
		{
			this.currentPhase=EPhases.phasePreparation1;
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
	
	
	////////////
	//ACTION C//
	////////////
	@Override
	public  boolean setInputC(Object inputC)
	{
		String inputString="";
		boolean validBasic = false;
		this.isInputNew=true;
		
		if(!this.tryCastToString(inputC))
		{
			return false;
		}
		else
		{
			inputString=this.doCastToString(inputC);
		}
		
	
		validBasic=this.checkBasicInputs(inputString);
		
		if(validBasic)
		{
			this.isInputValid=true;
			return true;
		}
		else
		{
			boolean valid=this.checkInputActionC(inputString);
			this.isInputValid=valid;
			return valid;
		}
		
	}
	
	
	@Override
	public final boolean checkInputActionC(Object inputC)
	{
		if(!this.tryCastToInteger(inputC))
		{
			return false;
		}
		else
		{
			this.game.getPlayer(1).setAge(this.doCastToInteger(inputC)); 
			return true;
		}
	}
	
	////////////
	//ACTION D//
	////////////
	@Override
	public  boolean setInputD(Object inputD)
	{
		String inputString="";
		boolean validBasic = false;
		this.isInputNew=true;
		
		if(!this.tryCastToString(inputD))
		{
			return false;
		}
		else
		{
			inputString=this.doCastToString(inputD);
		}
		
	
		validBasic=this.checkBasicInputs(inputString);
		
		if(validBasic)
		{
			this.isInputValid=true;
			return true;
		}
		else
		{
			boolean valid=this.checkInputActionD(inputString);
			this.isInputValid=valid;
			return valid;
		}
		
	}
	
	
	@Override
	public final boolean checkInputActionD(Object inputD)
	{
		if(!this.tryCastToInteger(inputD))
		{
			return false;
		}
		else
		{
			this.game.getPlayer(1).setAge(this.doCastToInteger(inputD)); 
			return true;
		}
	}
	
	
	////////////
	//ACTION E//
	////////////
	@Override
	public  boolean setInputE(Object inputE)
	{
		String inputString="";
		boolean validBasic = false;
		this.isInputNew=true;
		
		if(!this.tryCastToString(inputE))
		{
			return false;
		}
		else
		{
			inputString=this.doCastToString(inputE);
		}
		
	
		validBasic=this.checkBasicInputs(inputString);
		
		if(validBasic)
		{
			this.isInputValid=true;
			return true;
		}
		else
		{
			boolean valid=this.checkInputActionE(inputString);
			this.isInputValid=valid;
			return valid;
		}
		
	}
	
	
	@Override
	public final boolean checkInputActionE(Object inputE)
	{
		if(!this.tryCastToInteger(inputE))
		{
			return false;
		}
		else
		{
			this.game.getPlayer(1).setAge(this.doCastToInteger(inputE)); 
			return true;
		}
	}
	
	////////////
	//ACTION F//
	////////////
	@Override
	public  boolean setInputF(Object inputF)
	{
		String inputString="";
		boolean validBasic = false;
		this.isInputNew=true;
		
		if(!this.tryCastToString(inputF))
		{
			return false;
		}
		else
		{
			inputString=this.doCastToString(inputF);
		}
		
	
		validBasic=this.checkBasicInputs(inputString);
		
		if(validBasic)
		{
			this.isInputValid=true;
			return true;
		}
		else
		{
			boolean valid=this.checkInputActionE(inputString);
			this.isInputValid=valid;
			return valid;
		}
		
	}
	
	
	@Override
	public final boolean checkInputActionF(Object inputF)
	{
		if(!this.tryCastToInteger(inputF))
		{
			return false;
		}
		else
		{
			this.game.getPlayer(1).setAge(this.doCastToInteger(inputF)); 
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
