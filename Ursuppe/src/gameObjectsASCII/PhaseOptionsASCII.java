package gameObjectsASCII;

import interfaces.IModule;
import helper.LanguagePack;
import helper.UserInput;
import logics.PhaseALogic;
import logics.PhaseOptionsLogic;

public class PhaseOptionsASCII extends PhaseOptionsLogic
{

	
	/////////////
	//BASICS...//
	/////////////
		////////////
		//...LOGIC//
		////////////
	
		public PhaseOptionsASCII(IModule module) 
		{
			super(module);
		}

		@Override
		public void doPreAction()
		{
			this.outStream.println(LanguagePack.getTranslation("phaseOptionsTitle"));
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
	
//	@Override
//	public void doPreActionA()
//	{
//		this.outStream.println("What is your name?");
//	}
//	
//	@Override
//	public void actionAInput()
//	{
//		String name=UserInput.readInput("Please, enter your name: ");
//		this.setInputA(name);
//	}
//	
//	@Override
//	public void doAfterActionA()
//	{
//		this.outStream.println("Thank you for your name");
//	}
	
	////////////
	//ACTION B//
	////////////
//	
//	@Override
//	public void doPreActionB()
//	{
//		this.outStream.println("How old are you?");
//	}
//	
//	@Override
//	public void actionBInput()
//	{
//		String age=UserInput.readInput("Please, enter your age: ");
//		this.setInputB(age);
//	}
//	
//	@Override
//	public void doAfterActionB()
//	{
//		this.outStream.println("Thank you for your age");
//	}

	
	
}
