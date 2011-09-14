package gameObjectsASCII;

import exceptions.InputException;
import interfaces.IModule;
import helper.LanguagePack;
import helper.UserInput;
import logics.PhaseALogic;
import logics.PhasePreparation1Logic;
/**
 * the first preparation phase for ASCII game
 * @author Lukas Keller
 * @version 1.0.0
 */
public class PhasePreparation1ASCII extends PhasePreparation1Logic
{
	/////////////
	//BASICS...//
	/////////////
		////////////
		//...LOGIC//
		////////////
	
		public PhasePreparation1ASCII(IModule module) 
		{
			super(module);
		}



		@Override
		public void doPreActionFirstRun()
		{
			this.outStream.println(LanguagePack.getTranslation("phasePreparation1Title"));
		}
		
		

	///////////
	//ACTIONS//
	///////////
	

	////////////
	//ACTION A//
	////////////
	
	@Override
	public void actionAInput()
	{
		String instruction=UserInput.readInput(LanguagePack.getTranslation("preparation1Instruction"));
		
		
		try 
		{
			this.setInputA(instruction);
		} 
		catch (Exception e)
		{
			//TODO
			System.out.println(e.getMessage());
		}
		
	}
	
	@Override
	public void doAfterActionA()
	{
		int rolledValue=this.game.getDie().getValue();
		
		this.outStream.println(this.game.getCurrentPlayer().getName() + " " + LanguagePack.getTranslation("preparation1Rolled") + " " + rolledValue);
	}

}
