/**
 * 
 */
package views.ASCII.phases;

import templates.PhaseViewASCIITemplate;

import models.phases.AboutPhaseModel;
import models.phases.AchievementsPhaseModel;
import models.phases.BreakMenuPhaseModel;
import models.phases.CheatsPhaseModel;
import models.phases.ExitPhaseModel;
import models.phases.HelpPhaseModel;
import models.phases.LoadGamePhaseModel;
import models.phases.MainMenuPhaseModel;
import models.phases.SplashScreenPhaseModel;

/**
 * @author Lukas
 *
 */
public class LoadGamePhaseViewASCII extends PhaseViewASCIITemplate
{

	private LoadGamePhaseModel model;
	
	/**
	 * 
	 */
	public LoadGamePhaseViewASCII(LoadGamePhaseModel model) 
	{
		this.model=model;
	}

	@Override
	public void start()
	{
		System.out.println("START: MAIN MENU");
	}

}
