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
public class SplashScreenPhaseModel extends PhaseModelTemplate
{

	/**
	 * 
	 */
	public SplashScreenPhaseModel() 
	{
		super(EPhase.phaseSplashScreen);
	}	
		
	public void goToMainMenu()
	{
		this.changePhase(EPhase.phaseMainMenu);
	}

}
