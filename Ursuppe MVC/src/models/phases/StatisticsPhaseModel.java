/**
 * 
 */
package models.phases;

import templates.PhaseModelTemplate;

import enums.EPhase;

/**
 * @author Lukas
 *
 */
public class StatisticsPhaseModel extends PhaseModelTemplate
{

	/**
	 * 
	 */
	public StatisticsPhaseModel() 
	{
		super(EPhase.phaseSplashScreen);
	}	
		
	public void goToMainMenu()
	{
		this.changePhase(EPhase.phaseStatistics);
	}

}
