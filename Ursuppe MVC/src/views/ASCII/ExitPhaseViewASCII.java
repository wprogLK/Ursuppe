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
import models.phases.MainMenuPhaseModel;
import models.phases.SplashScreenPhaseModel;

/**
 * @author Lukas
 *
 */
public class ExitPhaseViewASCII extends PhaseViewASCIITemplate
{

	private ExitPhaseModel model;
	
	/**
	 * 
	 */
	public ExitPhaseViewASCII(ExitPhaseModel model) 
	{
		this.model=model;
	}

	@Override
	public void start()
	{
		System.out.println("START: MAIN MENU");
	}

}
