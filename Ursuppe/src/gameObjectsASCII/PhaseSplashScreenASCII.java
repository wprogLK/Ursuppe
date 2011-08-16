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
			this.outStream.print(this.rb.getString("splashScreenASCIIWelcome"));
			this.outStream.println(this.rb.getString("phaseSplashScreenTitle"));
			this.outStream.print(this.rb.getString("phaseSplashScreenAuthor"));
			this.outStream.println(" " + this.rb.getString("phaseSplashScreenCopyright"));
			this.outStream.println(this.rb.getString("phaseSplashScreenBasedOn"));

			this.outStream.println("");
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
