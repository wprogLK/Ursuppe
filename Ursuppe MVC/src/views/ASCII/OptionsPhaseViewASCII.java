/**
 * 
 */
package views.ASCII;

import templates.PhaseViewASCIITemplate;

import models.phases.AboutPhaseModel;
import models.phases.AchievementsPhaseModel;
import models.phases.BreakMenuPhaseModel;
import models.phases.CheatsPhaseModel;
import models.phases.ExitPhaseModel;
import models.phases.HelpPhaseModel;
import models.phases.NewGamePhaseModel;
import models.phases.MainMenuPhaseModel;
import models.phases.OptionsPhaseModel;
import models.phases.SplashScreenPhaseModel;

/**
 * @author Lukas
 *
 */
public class OptionsPhaseViewASCII extends PhaseViewASCIITemplate
{

	private OptionsPhaseModel model;
	
	/**
	 * 
	 */
	public OptionsPhaseViewASCII(OptionsPhaseModel model) 
	{
		this.model=model;
	}

	@Override
	public void start()
	{
		System.out.println("START: MAIN MENU");
	}

}
