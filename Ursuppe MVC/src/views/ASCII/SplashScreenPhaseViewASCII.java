/**
 * 
 */
package views.ASCII;

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
		this.model.goToMainMenu();
	}

}
