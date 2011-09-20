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
import models.phases.NewGamePhaseModel;
import models.phases.MainMenuPhaseModel;
import models.phases.OptionsPhaseModel;
import models.phases.Phase1Model;
import models.phases.Phase2Model;
import models.phases.Phase3Model;
import models.phases.Phase4Model;
import models.phases.SplashScreenPhaseModel;

/**
 * @author Lukas
 *
 */
public class Phase4ViewASCII extends PhaseViewASCIITemplate
{

	private Phase4Model model;
	
	/**
	 * 
	 */
	public Phase4ViewASCII(Phase4Model model) 
	{
		this.model=model;
	}

	@Override
	public void start()
	{
		System.out.println("START: MAIN MENU");
	}

}
