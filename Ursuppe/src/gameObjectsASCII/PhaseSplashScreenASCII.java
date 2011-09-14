package gameObjectsASCII;

import exceptions.InputException;
import interfaces.IModule;
import helper.LanguagePack;
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
			this.outStream.print(LanguagePack.getTranslation("splashScreenASCIIWelcome"));
			this.outStream.println(LanguagePack.getTranslation("phaseSplashScreenTitle"));
			this.outStream.print(LanguagePack.getTranslation("phaseSplashScreenAuthor"));
			this.outStream.println(" " + LanguagePack.getTranslation("phaseSplashScreenCopyright"));
			this.outStream.println(LanguagePack.getTranslation("phaseSplashScreenBasedOn"));

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
		String instruction=UserInput.readInput(LanguagePack.getTranslation("splashScreenASCIIStartInstruction"));
		
		try
		{
			this.setInputA(instruction);
		} 
		catch (Exception e)
		{
			//TODO
			System.out.println(e.getMessage());
		}
	}
	
}
