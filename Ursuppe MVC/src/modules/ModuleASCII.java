package modules;

import views.ASCII.*;
import interfaces.IModule;
import interfaces.IView;
import models.phases.*;

public class ModuleASCII implements IModule
{

	@Override
	public IView createAboutPhaseView(AboutPhaseModel model)
	{
		return new AboutPhaseViewASCII(model);
	}
	
	@Override
	public IView createAchievementsPhaseView(AchievementsPhaseModel model)
	{
		return new AchievementsPhaseViewASCII(model);
	}
	
	@Override
	public IView createBreakMenuPhaseView(BreakMenuPhaseModel model)
	{
		return new BreakMenuPhaseViewASCII(model);
	}
	
	@Override
	public IView createCheatsPhaseView(CheatsPhaseModel model)
	{
		return new CheatsPhaseViewASCII(model);
	}
	
	@Override
	public IView createExitPhaseView(ExitPhaseModel model)
	{
		return new ExitPhaseViewASCII(model);
	}
	
	@Override
	public IView createGameEndPhaseView(GameEndPhaseModel model)
	{
		return new GameEndPhaseViewASCII(model);
	}
	
	@Override
	public IView createHelpPhaseView(HelpPhaseModel model)
	{
		return new HelpPhaseViewASCII(model);
	}
	
	@Override
	public IView createLoadGamePhaseView(LoadGamePhaseModel model)
	{
		return new LoadGamePhaseViewASCII(model);
	}
	
	@Override
	public IView createMainMenuPhaseView(MainMenuPhaseModel model)
	{
		return new MainMenuPhaseViewASCII(model);
	}
	
	@Override
	public IView createNewGamePhaseView(NewGamePhaseModel model)
	{
		return new NewGamePhaseViewASCII(model);
	}
	
	@Override
	public IView createOptionsPhaseView(OptionsPhaseModel model)
	{
		return new OptionsPhaseViewASCII(model);
	}
	
	@Override
	public IView createPhase1View(Phase1Model model)
	{
		return new Phase1ViewASCII(model);
	}
	
	@Override
	public IView createPhase2View(Phase2Model model)
	{
		return new Phase2ViewASCII(model);
	}
	
	@Override
	public IView createPhase3View(Phase3Model model)
	{
		return new Phase3ViewASCII(model);
	}
	
	@Override
	public IView createPhase4View(Phase4Model model)
	{
		return new Phase4ViewASCII(model);
	}
	
	@Override
	public IView createPhase5View(Phase5Model model)
	{
		return new Phase5ViewASCII(model);
	}
	
	@Override
	public IView createPhase6View(Phase6Model model)
	{
		return new Phase6ViewASCII(model);
	}
	
	@Override
	public IView createPreparation1PhaseView(Preparation1PhaseModel model)
	{
		return new Preparation1PhaseViewASCII(model);
	}
	
	@Override
	public IView createPreparation2PhaseView(Preparation2PhaseModel model)
	{
		return new Preparation2PhaseViewASCII(model);
	}
	
	@Override
	public IView createPreparation3PhaseView(Preparation3PhaseModel model)
	{
		return new Preparation3PhaseViewASCII(model);
	}
	
	@Override
	public IView createSaveGamePhaseView(SaveGamePhaseModel model)
	{
		return new SaveGamePhaseViewASCII(model);
	}
	
	@Override
	public IView createSplashScreenPhaseView(SplashScreenPhaseModel model)
	{
		return new SplashScreenPhaseViewASCII(model);
	}
	
	@Override
	public IView createStatisticsPhaseView(StatisticsPhaseModel model)
	{
		return new StatisticsPhaseViewASCII(model);
	}
	
	

}
