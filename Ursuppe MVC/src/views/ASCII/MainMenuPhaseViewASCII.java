/**
 * 
 */
package views.ASCII;

import templates.PhaseViewASCIITemplate;

import models.phases.MainMenuPhaseModel;
import models.phases.SplashScreenPhaseModel;

/**
 * @author Lukas
 *
 */
public class MainMenuPhaseViewASCII extends PhaseViewASCIITemplate
{

	private MainMenuPhaseModel model;
	
	/**
	 * 
	 */
	public MainMenuPhaseViewASCII(MainMenuPhaseModel model) 
	{
		this.model=model;
	}

	@Override
	public void start()
	{
		System.out.println("START: MAIN MENU");
	}

}
