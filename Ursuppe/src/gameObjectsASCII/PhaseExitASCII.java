package gameObjectsASCII;

import helper.UserInput;
import logics.PhaseExitLogic;

public class PhaseExitASCII extends PhaseExitLogic
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
			this.outStream.println(this.rb.getString("phaseExitTitle"));
			this.outStream.println("You want to leave the game?");
		}

		@Override
		public void doAfterAction()
		{
			this.outStream.println("Ok... See  you soon... Bye...");
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
		this.outStream.println("Are you sure to leave the game?");
	}
	
	@Override
	public void actionAInput()
	{
		String name=UserInput.readInput("y/n");
		this.setInputA(name);
	}
	
	
}
