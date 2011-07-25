package gameObjectsASCII;

import helper.UserInput;
import logics.PhaseALogic;
import logics.PhaseSplashScreenLogic;

public class PhaseSplashScreenASCII extends PhaseSplashScreenLogic
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
			System.out.print(this.rb.getString("splashScreenASCIIWelcome"));
			System.out.println(this.rb.getString("phaseSplashScreenTitle"));
			System.out.print(this.rb.getString("phaseSplashScreenAuthor"));
			System.out.println(" " + this.rb.getString("phaseSplashScreenCopyright"));
			System.out.println(this.rb.getString("phaseSplashScreenBasedOn"));

			System.out.println("");
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
		String instruction=UserInput.readInput(this.rb.getString("splashScreenASCIIStartInstruction"));
		this.setInputA(instruction);
	}
	
}
