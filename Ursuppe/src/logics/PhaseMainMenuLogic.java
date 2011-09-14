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
public abstract class PhaseMainMenuLogic extends PhaseTemplateLogic
{
	//////////
	//BASICS//
	//////////

	//////////
	//INPUTS//
	//////////
													//ACTION A: Chose action
	
		public PhaseMainMenuLogic(IModule module) 
		{
			super(module);
		}

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
		this.currentPhase=EPhases.phaseMainMenu;
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
		//This time don't check the basic instructions because they're not needed here
		
		this.isInputNew=true;
	
		this.checkInputActionA(inputA);
	}
	
	
	@Override
	public final void checkInputActionA(Object inputA) throws Exception
	{
		String inputString=this.doCastToString(inputA);
		this.checkSelectedEntry(inputString);
	}
	


	private void checkSelectedEntry(String input) throws Exception
	{
		if(input.equals("1"))			//New Game
		{
			this.currentPhase=EPhases.phaseNewGame;
			this.isInputValid=true;
		}
		else if(input.equals("2"))		//Load Game	
		{
			this.currentPhase=EPhases.phaseLoadGame;
			this.isInputValid=true;
		}
		else if(input.equals("3"))		//Options
		{
			this.currentPhase=EPhases.phaseOptions;
			this.isInputValid=true;
		}
		else if(input.equals("4"))		//Help
		{
			this.currentPhase=EPhases.phaseHelp;
			this.isInputValid=true;
		}
		else if(input.equals("5"))		//Cheats
		{
			this.currentPhase=EPhases.phaseCheats;
			this.isInputValid=true;
		}
		else if(input.equals("6"))		//Achievements
		{
			this.currentPhase=EPhases.phaseAchievements;
			this.isInputValid=true;
		}
		else if(input.equals("7"))		//Statistics
		{
			this.currentPhase=EPhases.phaseStatistics;
			this.isInputValid=true;
		}
		else if(input.equals("8"))		//About
		{
			this.currentPhase=EPhases.phaseAbout;
			this.isInputValid=true;
		}
		else if(input.equals("9"))		//Exit
		{
			this.currentPhase=EPhases.phaseExit;
			this.isInputValid=true;
		}
		else
		{
			this.module.throwInputExceptionUnkownInstruction(input);
		}
	}
	

	///////////
	//SETTERS//
	///////////
	
	
	

	
	
	///////////
	//GETTERS//
	///////////
	
	
}
