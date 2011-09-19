package interfaces;

import models.phases.*;

public interface IModule 
{
	public IView createAboutPhaseView(AboutPhaseModel model);
	
	public IView createAchievementsPhaseView(AchievementsPhaseModel model);
	
	public IView createBreakMenuPhaseView(BreakMenuPhaseModel model);
	
	public IView createCheatsPhaseView(CheatsPhaseModel model);
	
	public IView createExitPhaseView(ExitPhaseModel model);
	
	public IView createGameEndPhaseView(GameEndPhaseModel model);
	
	public IView createHelpPhaseView(HelpPhaseModel model);
	
	public IView createLoadGamePhaseView(LoadGamePhaseModel model);
	
	public IView createMainMenuPhaseView(MainMenuPhaseModel model);
	
	public IView createNewGamePhaseView(NewGamePhaseModel model);
	
	public IView createOptionsPhaseView(OptionsPhaseModel model);
	
	public IView createPhase1View(Phase1Model model);
	
	public IView createPhase2View(Phase2Model model);
	
	public IView createPhase3View(Phase3Model model);
	
	public IView createPhase4View(Phase4Model model);
	
	public IView createPhase5View(Phase5Model model);
	
	public IView createPhase6View(Phase6Model model);
	
	public IView createPreparation1PhaseView(Preparation1PhaseModel model);
	
	public IView createPreparation2PhaseView(Preparation2PhaseModel model);
	
	public IView createPreparation3PhaseView(Preparation3PhaseModel model);
	
	public IView createSaveGamePhaseView(SaveGamePhaseModel model);
	
	public IView createSplashScreenPhaseView(SplashScreenPhaseModel model);
	
	public IView createStatisticsPhaseView(StatisticsPhaseModel model);
	
}
