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
			System.out.println("You want to leave the game?");
		}

		@Override
		public void doAfterAction()
		{
			System.out.println("Ok... See  you soon... Bye...");
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
		System.out.println("Are you sure to leave the game?");
	}
	
	@Override
	public void actionAInput()
	{
		String name=UserInput.readInput("y/n");
		this.setInputA(name);
	}
	
	
}
