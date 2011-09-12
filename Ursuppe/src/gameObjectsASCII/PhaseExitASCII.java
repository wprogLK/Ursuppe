package gameObjectsASCII;

import exceptions.InputException;
import interfaces.IModule;
import helper.UserInput;
import logics.PhaseExitLogic;

public class PhaseExitASCII extends PhaseExitLogic
{

	
	public PhaseExitASCII(IModule module) 
	{
		super(module);
	}

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
		String answer=UserInput.readInput("y/n");
		
		try 
		{
			this.setInputA(answer);
		} 
		catch (InputException e) 
		{
			//TODO
			System.out.println(e.getMessage());
		}
			
	}
	
	
}
