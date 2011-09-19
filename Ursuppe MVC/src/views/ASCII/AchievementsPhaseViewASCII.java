/**
 * 
 */
package views.ASCII;

import templates.PhaseViewASCIITemplate;

import models.phases.AboutPhaseModel;
import models.phases.AchievementsPhaseModel;
import models.phases.MainMenuPhaseModel;
import models.phases.SplashScreenPhaseModel;

/**
 * @author Lukas
 *
 */
public class AchievementsPhaseViewASCII extends PhaseViewASCIITemplate
{

	private AchievementsPhaseModel model;
	
	/**
	 * 
	 */
	public AchievementsPhaseViewASCII(AchievementsPhaseModel model) 
	{
		this.model=model;
	}

	@Override
	public void start()
	{
		System.out.println("START: MAIN MENU");
	}

}
