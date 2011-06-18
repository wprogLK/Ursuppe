package gameObjectsASCII;

import helper.UserInput;
import logics.PhaseALogic;

public class PhaseAASCII extends PhaseALogic
{

	
	/////////////
	//BASICS...//
	/////////////
		////////////
		//...LOGIC//
		////////////
		public void doPreAction()
		{
			System.out.println("Welcome to the techDemo of the logic of the Ursuppe! \n \n You can allways enter 'exit' to stop the programm");
		}
		
		public void doAfterAction()
		{
			System.out.println("This was the techDemo run in ASCII. Goodbye...");
			//System.exit(0);
		}

	///////////
	//ACTIONS//
	///////////
	

	////////////
	//ACTION A//
	////////////
	
	
	public void doPreActionA()
	{
		System.out.println("What is your name?");
	}
	
	public void actionAInput()
	{
		String name=UserInput.readInput("Please, enter your name: ");
		this.setInputA(name);
	}
	
	public void doAfterActionA()
	{
		System.out.println("Thank you for your name");
	}
	
	////////////
	//ACTION B//
	////////////
	
	public void doPreActionB()
	{
		System.out.println("How old are you?");
	}
	
	public void actionBInput()
	{
		String age=UserInput.readInput("Please, enter your age: ");
		this.setInputB(age);
	}
	
	public void doAfterActionB()
	{
		System.out.println("Thank you for your age");
	}

	
	
}
