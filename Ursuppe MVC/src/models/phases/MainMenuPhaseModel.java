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
public class MainMenuPhaseModel extends PhaseModelTemplate
{
	
	/**
	 * 
	 */
	public MainMenuPhaseModel() 
	{
		super(EPhase.phaseMainMenu);
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


	public void doExit() 
	{
		System.out.println("EXIT... Bye...");
		System.exit(0);
		
	}

}
