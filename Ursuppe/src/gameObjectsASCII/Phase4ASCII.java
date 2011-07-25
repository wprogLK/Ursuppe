package gameObjectsASCII;

import helper.UserInput;
import logics.Phase4Logic;
import logics.PhaseALogic;

public class Phase4ASCII extends Phase4Logic
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
			System.out.println(this.rb.getString("phase4Title"));		
			}
		
		@Override
		public void doAfterAction()
		{
			System.out.println("This was the techDemo run in ASCII. Goodbye...");
			this.doExit();
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
		System.out.println("What is your name?");
	}
	
	@Override
	public void actionAInput()
	{
		String name=UserInput.readInput("Please, enter your name: ");
		this.setInputA(name);
	}
	
	@Override
	public void doAfterActionA()
	{
		System.out.println("Thank you for your name");
	}
	
	////////////
	//ACTION B//
	////////////
	
	@Override
	public void doPreActionB()
	{
		System.out.println("How old are you?");
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
		System.out.println("Thank you for your age");
	}

	
	
}
