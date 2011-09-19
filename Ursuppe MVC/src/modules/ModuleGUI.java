package modules;

import views.GUI.*;
import interfaces.IModule;
import interfaces.IView;
import models.phases.*;

public class ModuleGUI implements IModule
{

	@Override
	public IView createAboutPhaseView(AboutPhaseModel model)
	{
		return new AboutPhaseViewGUI(model);
	}
	
	@Override
	public IView createAchievementsPhaseView(AchievementsPhaseModel model)
	{
		return new AchievementsPhaseViewGUI(model);
	}
	
	@Override
	public IView createBreakMenuPhaseView(BreakMenuPhaseModel model)
	{
		return new BreakMenuPhaseViewGUI(model);
	}
	
	@Override
	public IView createCheatsPhaseView(CheatsPhaseModel model)
	{
		return new CheatsPhaseViewGUI(model);
	}
	
	@Override
	public IView createExitPhaseView(ExitPhaseModel model)
	{
		return new ExitPhaseViewGUI(model);
	}
	
	@Override
	public IView createGameEndPhaseView(GameEndPhaseModel model)
	{
		return new GameEndPhaseViewGUI(model);
	}
	
	@Override
	public IView createHelpPhaseView(HelpPhaseModel model)
	{
		return new HelpPhaseViewGUI(model);
	}
	
	@Override
	public IView createLoadGamePhaseView(LoadGamePhaseModel model)
	{
		return new LoadGamePhaseViewGUI(model);
	}
	
	@Override
	public IView createMainMenuPhaseView(MainMenuPhaseModel model)
	{
		return new MainMenuPhaseViewGUI(model);
	}
	
	@Override
	public IView createNewGamePhaseView(NewGamePhaseModel model)
	{
		return new NewGamePhaseViewGUI(model);
	}
	
	@Override
	public IView createOptionsPhaseView(OptionsPhaseModel model)
	{
		return new OptionsPhaseViewGUI(model);
	}
	
	@Override
	public IView createPhase1View(Phase1Model model)
	{
		return new Phase1ViewGUI(model);
	}
	
	@Override
	public IView createPhase2View(Phase2Model model)
	{
		return new Phase2ViewGUI(model);
	}
	
	@Override
	public IView createPhase3View(Phase3Model model)
	{
		return new Phase3ViewGUI(model);
	}
	
	@Override
	public IView createPhase4View(Phase4Model model)
	{
		return new Phase4ViewGUI(model);
	}
	
	@Override
	public IView createPhase5View(Phase5Model model)
	{
		return new Phase5ViewGUI(model);
	}
	
	@Override
	public IView createPhase6View(Phase6Model model)
	{
		return new Phase6ViewGUI(model);
	}
	
	@Override
	public IView createPreparation1PhaseView(Preparation1PhaseModel model)
	{
		return new Preparation1PhaseViewGUI(model);
	}
	
	@Override
	public IView createPreparation2PhaseView(Preparation2PhaseModel model)
	{
		return new Preparation2PhaseViewGUI(model);
	}
	
	@Override
	public IView createPreparation3PhaseView(Preparation3PhaseModel model)
	{
		return new Preparation3PhaseViewGUI(model);
	}
	
	@Override
	public IView createSaveGamePhaseView(SaveGamePhaseModel model)
	{
		return new SaveGamePhaseViewGUI(model);
	}
	
	@Override
	public IView createSplashScreenPhaseView(SplashScreenPhaseModel model)
	{
		return new SplashScreenPhaseViewGUI(model);
	}
	
	@Override
	public IView createStatisticsPhaseView(StatisticsPhaseModel model)
	{
		return new StatisticsPhaseViewGUI(model);
	}
	

}
