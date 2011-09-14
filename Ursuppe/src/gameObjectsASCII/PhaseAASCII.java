package gameObjectsASCII;

import exceptions.InputException;
import interfaces.IModule;
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
	
		public PhaseAASCII(IModule module) 
		{
			super(module);
		}

		@Override
		public void doPreAction()
		{
			this.outStream.println("Welcome to the techDemo of the logic of the Ursuppe! \n \n You can allways enter 'exit' to stop the programm");
		}
		
		@Override
		public void doAfterAction()
		{
			this.outStream.println("This was the techDemo run in ASCII. Goodbye...");
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
		this.outStream.println("What is your name?");
	}
	
	@Override
	public void actionAInput()
	{
		String name=UserInput.readInput("Please, enter your name: ");
		
		try 
		{
			this.setInputA(name);
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
		this.outStream.println("Thank you for your name");
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
		
		try 
		{
			this.setInputB(age);
		} 
		catch (Exception e)
		{
			//TODO
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void doAfterActionB()
	{
		this.outStream.println("Thank you for your age");
	}

	
	
}
