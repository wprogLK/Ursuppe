/**
 * 
 */
package views.ASCII.phases;

import helpers.UserInput;
import templates.PhaseViewASCIITemplate;

import models.phases.SplashScreenPhaseModel;

/**
 * @author Lukas
 *
 */
public class SplashScreenPhaseViewASCII extends PhaseViewASCIITemplate
{

	private SplashScreenPhaseModel model;
	
	/**
	 * 
	 */
	public SplashScreenPhaseViewASCII(SplashScreenPhaseModel model) 
	{
		this.model=model;
	}

	@Override
	public void start()
	{
		System.out.println("START: SPLASH SCREEN");
		
		String output=UserInput.readInput("Please enter 'start'");
		
		this.model.goToMainMenu();
	}

}
