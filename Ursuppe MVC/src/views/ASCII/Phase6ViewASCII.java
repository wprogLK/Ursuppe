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
import models.phases.Phase1Model;
import models.phases.Phase2Model;
import models.phases.Phase3Model;
import models.phases.Phase4Model;
import models.phases.Phase5Model;
import models.phases.Phase6Model;
import models.phases.SplashScreenPhaseModel;

/**
 * @author Lukas
 *
 */
public class Phase6ViewASCII extends PhaseViewASCIITemplate
{

	private Phase6Model model;
	
	/**
	 * 
	 */
	public Phase6ViewASCII(Phase6Model model) 
	{
		this.model=model;
	}

	@Override
	public void start()
	{
		System.out.println("START: MAIN MENU");
	}

}
