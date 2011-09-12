package gameObjectsASCII;

import exceptions.InputException;
import interfaces.IModule;
import helper.UserInput;
import logics.PhaseALogic;
import logics.PhaseSplashScreenLogic;
/**
 * the splashScreen phase for ASCII game
 * @author Lukas Keller
 * @version 1.0.0
 */
public class PhaseSplashScreenASCII extends PhaseSplashScreenLogic
{

	
	/////////////
	//BASICS...//
	/////////////
		////////////
		//...LOGIC//
		////////////
	
		public PhaseSplashScreenASCII(IModule module) 
		{
			super(module);
		}

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
	public void actionAInput()
	{
		String instruction=UserInput.readInput(this.rb.getString("splashScreenASCIIStartInstruction"));
		
		try
		{
			this.setInputA(instruction);
		} 
		catch (InputException e) 
		{
			//TODO
			System.out.println(e.getMessage());
		}
	}
	
}
