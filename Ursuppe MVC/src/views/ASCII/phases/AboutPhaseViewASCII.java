/**
 * 
 */
package views.ASCII.phases;

import templates.PhaseViewASCIITemplate;

import models.phases.AboutPhaseModel;
import models.phases.MainMenuPhaseModel;
import models.phases.SplashScreenPhaseModel;

/**
 * @author Lukas
 *
 */
public class AboutPhaseViewASCII extends PhaseViewASCIITemplate
{

	private AboutPhaseModel model;
	
	/**
	 * 
	 */
	public AboutPhaseViewASCII(AboutPhaseModel model) 
	{
		this.model=model;
	}

	@Override
	public void start()
	{
		System.out.println("START: MAIN MENU");
	}

}
