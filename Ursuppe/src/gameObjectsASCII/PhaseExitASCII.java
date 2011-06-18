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
		public void doPreAction()
		{
			System.out.println("You want to leave the game?");
		}

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
	
	
	public void doPreActionA()
	{
		System.out.println("Are you sure to leave the game?");
	}
	
	public void actionAInput()
	{
		String name=UserInput.readInput("y/n");
		this.setInputA(name);
	}
	
	
}
