package modules;

import views.GUI.*;
import views.GUI.phases.AboutPhaseViewGUI;
import views.GUI.phases.AchievementsPhaseViewGUI;
import views.GUI.phases.BreakMenuPhaseViewGUI;
import views.GUI.phases.CheatsPhaseViewGUI;
import views.GUI.phases.ExitPhaseViewGUI;
import views.GUI.phases.GameEndPhaseViewGUI;
import views.GUI.phases.HelpPhaseViewGUI;
import views.GUI.phases.LoadGamePhaseViewGUI;
import views.GUI.phases.MainMenuPhaseViewGUI;
import views.GUI.phases.NewGamePhaseViewGUI;
import views.GUI.phases.OptionsPhaseViewGUI;
import views.GUI.phases.Phase1ViewGUI;
import views.GUI.phases.Phase2ViewGUI;
import views.GUI.phases.Phase3ViewGUI;
import views.GUI.phases.Phase4ViewGUI;
import views.GUI.phases.Phase5ViewGUI;
import views.GUI.phases.Phase6ViewGUI;
import views.GUI.phases.Preparation1PhaseViewGUI;
import views.GUI.phases.Preparation2PhaseViewGUI;
import views.GUI.phases.Preparation3PhaseViewGUI;
import views.GUI.phases.SaveGamePhaseViewGUI;
import views.GUI.phases.SplashScreenPhaseViewGUI;
import views.GUI.phases.StatisticsPhaseViewGUI;
import interfaces.IModule;
import interfaces.views.IView;
import models.AmoebaModel;
import models.BoardModel;
import models.CompassSquareModel;
import models.DieModel;
import models.PlayerModel;
import models.SoupSquareModel;
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
	
	/////////////////////
	//CREATE BOAD STUFF//
	/////////////////////
	@Override
	public IView createAmoebaView(AmoebaModel model)
	{
		return new AmoebaViewGUI(model);
	}
	@Override
	public IView createPlayerView(PlayerModel model)
	{
		return new PlayerViewGUI(model);
	}
	
	@Override
	public IView createBoardView(BoardModel model)
	{
		return new BoardViewGUI(model);
	}
	@Override
	public IView createSoupSquareView(SoupSquareModel model)
	{
		return new SoupSquareViewGUI(model);
	}
	@Override
	public IView createCompassSquareView(CompassSquareModel model)
	{
		return new CompassSquareViewGUI(model);
	}
	
	@Override
	public IView createDieView(DieModel model)
	{
		return new DieViewGUI(model);
	}
	

}
