package logics;

import enums.EPhases;
import templates.PhaseTemplateLogic;
import helper.ReadAndWriteFiles;
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
		
		boolean valid=this.tryUnderstandInputA(inputString);
		
		return valid;
	}
	
	/**
	 * try to understand/interpret the input and do the right thing
	 * 
	 * @param inputA
	 * @return - true if correct <br/> - false if incorrect
	 */
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
		String inputString="";
		
		if(!this.tryCastToString(inputB))
		{
			return false;
		}
		else
		{
			inputString=this.doCastToString(inputB);
		}
		
		boolean valid=this.tryUnderstandInputB(inputString);
		
		return valid;
	}
	
	/**
	 * try to understand/interpret the input and do the right thing
	 * 
	 * @param inputA
	 * @return - true if correct <br/> - false if incorrect
	 */
	private boolean tryUnderstandInputB(String inputB)
	{
		if (inputB.equals(this.rb.getString("instructionPhaseNewGameLoadPlayer")))	//load player
		{
			this.activateActionC();
			return true;
		}
		else if (inputB.equals(this.rb.getString("instructionPhaseNewGameCreatePlayer")))	//create new player
		{
			activateActionD();
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	////////////
	//ACTION C//
	////////////
	@Override
	public  boolean setInputC(Object inputC)
	{
		ReadAndWriteFiles.readSavePlayers();
		//TODO: prepare ArrayList to show
		
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
			boolean valid=this.checkInputActionC(inputC);
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
			int intInput=this.doCastToInteger(inputC);
			boolean valid=this.tryUnderstandInputC(intInput);
			
			return valid;
		}
	}
	
	
	/**
	 * try to understand/interpret the input and do the right thing
	 * 
	 * @param inputC
	 * @return - true if correct <br/> - false if incorrect
	 */
	private boolean tryUnderstandInputC(int inputC)
	{
//		if (inputB.equals(this.rb.getString("instructionPhaseNewGameLoadPlayer")))	//load player
//		{
//			this.activateActionC();
//			return true;
//		}
//		else if (inputB.equals(this.rb.getString("instructionPhaseNewGameCreatePlayer")))	//create new player
//		{
//			activateActionD();
//			return true;
//		}
//		else
//		{
//			return false;
//		}
		
		/*
		 * TODO:
		 * (0) means back
		 * every other number stands for a player (human or AI)
		 */
		
		return true;
		
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
