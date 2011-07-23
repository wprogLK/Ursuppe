/**
 * 
 */
package phases;


/**
 * @author lkeller
 *
 */
public interface IPhase {
	public void doGoToWelcomePhase();
	
	public void doGoToNextPhase();
	public void doGoToPreviousPhase();
	
	public void doGoToSavePhase();
	public void doGoToLoadPhase();
	
	public void doGoToAboutPhase();
	public void doGoToSummaryPhase();
	public void doGoToCheatPhase();
	
	public void doGoToExitPhase();
	
	public void doErrorInputTryAgainPhaseSpecific();
	public void doErrorInputTryAgainAllPhases(String input);
}
