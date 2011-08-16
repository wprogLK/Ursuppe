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
public abstract class PhaseMainMenuLogic extends PhaseTemplateLogic
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
	public  boolean setInputA(Object inputA)
	{
		//This time don't check the basic instructions because they're not needed here
		
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
		
		return this.checkSelectedEntry(inputString);
		
	}
	


	private boolean checkSelectedEntry(String input)
	{
		boolean valid=true;
		
		if(input.equals("1"))			//New Game
		{
			this.currentPhase=EPhases.phaseNewGame;
		}
		else if(input.equals("2"))		//Load Game	
		{
			this.currentPhase=EPhases.phaseLoadGame;
		}
		else if(input.equals("3"))		//Options
		{
			this.currentPhase=EPhases.phaseOptions;
		}
		else if(input.equals("4"))		//Help
		{
			this.currentPhase=EPhases.phaseHelp;
		}
		else if(input.equals("5"))		//Cheats
		{
			this.currentPhase=EPhases.phaseCheats;
		}
		else if(input.equals("6"))		//Achievements
		{
			this.currentPhase=EPhases.phaseAchievements;
		}
		else if(input.equals("7"))		//Statistics
		{
			this.currentPhase=EPhases.phaseStatistics;
		}
		else if(input.equals("8"))		//About
		{
			this.currentPhase=EPhases.phaseAbout;
		}
		else if(input.equals("9"))		//Exit
		{
			this.currentPhase=EPhases.phaseExit;
		}
		else
		{
			valid=false;
			this.outStream.println("Input was: " + input);	//TODO delete this
		}
		
		return valid;
		
	}
	

	///////////
	//SETTERS//
	///////////
	
	
	

	
	
	///////////
	//GETTERS//
	///////////
	
	
}
