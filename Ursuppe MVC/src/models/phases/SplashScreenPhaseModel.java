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
		
	
	@Override
	public EPhase getCurrentEPhase() 
	{
		return this.currentEPhase;
	}
	
	public void goToMainMenu()
	{
		this.changePhase(EPhase.phaseMainMenu);
	}

}
