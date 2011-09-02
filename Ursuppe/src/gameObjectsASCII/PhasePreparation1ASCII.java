package gameObjectsASCII;

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
	
		@Override
		public void doPreActionFirstRun()
		{
			this.outStream.println(this.rb.getString("phasePreparation1Title"));
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
		String instruction=UserInput.readInput(this.rb.getString("preparation1Instruction"));
		this.setInputA(instruction);
	}
	
	@Override
	public void doAfterActionA()
	{
		int rolledValue=this.game.getDie().getValue();
		
		this.outStream.println(this.game.getCurrentPlayer().getName() + " " + this.rb.getString("preparation1Rolled") + " " + rolledValue);
	}

}
