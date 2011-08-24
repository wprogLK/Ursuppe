package gameObjectsASCII;

import helper.UserInput;
import logics.PhaseALogic;
import logics.PhasePreparation1Logic;

public class PhasePreparation1ASCII extends PhasePreparation1Logic
{

	
	/////////////
	//BASICS...//
	/////////////
		////////////
		//...LOGIC//
		////////////
	
		@Override
		public void doPreAction()
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
	public void doPreActionA()
	{
		
		
	}
	
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
	
	////////////
	//ACTION B//
	////////////
	
	@Override
	public void doPreActionB()
	{
		this.outStream.println("How old are you?");
	}
	
	@Override
	public void actionBInput()
	{
		String age=UserInput.readInput("Please, enter your age: ");
		this.setInputB(age);
	}
	
	@Override
	public void doAfterActionB()
	{
		this.outStream.println("Thank you for your age");
	}

	
	
}
