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
import models.phases.Phase5Model;
import models.phases.Preparation1PhaseModel;
import models.phases.Preparation2PhaseModel;
import models.phases.SaveGamePhaseModel;
import models.phases.SplashScreenPhaseModel;
import models.phases.StatisticsPhaseModel;

/**
 * @author Lukas
 *
 */
public class StatisticsPhaseViewASCII extends PhaseViewASCIITemplate
{

	private  StatisticsPhaseModel model;
	
	/**
	 * 
	 */
	public StatisticsPhaseViewASCII(StatisticsPhaseModel model) 
	{
		this.model=model;
	}

	@Override
	public void start()
	{
		System.out.println("START: MAIN MENU");
	}

}
