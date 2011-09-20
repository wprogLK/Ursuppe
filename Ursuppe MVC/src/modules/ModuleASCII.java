package modules;

import views.ASCII.*;
import views.ASCII.phases.AboutPhaseViewASCII;
import views.ASCII.phases.AchievementsPhaseViewASCII;
import views.ASCII.phases.BreakMenuPhaseViewASCII;
import views.ASCII.phases.CheatsPhaseViewASCII;
import views.ASCII.phases.ExitPhaseViewASCII;
import views.ASCII.phases.GameEndPhaseViewASCII;
import views.ASCII.phases.HelpPhaseViewASCII;
import views.ASCII.phases.LoadGamePhaseViewASCII;
import views.ASCII.phases.MainMenuPhaseViewASCII;
import views.ASCII.phases.NewGamePhaseViewASCII;
import views.ASCII.phases.OptionsPhaseViewASCII;
import views.ASCII.phases.Phase1ViewASCII;
import views.ASCII.phases.Phase2ViewASCII;
import views.ASCII.phases.Phase3ViewASCII;
import views.ASCII.phases.Phase4ViewASCII;
import views.ASCII.phases.Phase5ViewASCII;
import views.ASCII.phases.Phase6ViewASCII;
import views.ASCII.phases.Preparation1PhaseViewASCII;
import views.ASCII.phases.Preparation2PhaseViewASCII;
import views.ASCII.phases.Preparation3PhaseViewASCII;
import views.ASCII.phases.SaveGamePhaseViewASCII;
import views.ASCII.phases.SplashScreenPhaseViewASCII;
import views.ASCII.phases.StatisticsPhaseViewASCII;
import interfaces.IModule;
import interfaces.views.IView;
import models.AmoebaModel;
import models.BoardModel;
import models.CompassSquareModel;
import models.DieModel;
import models.GameModel;
import models.PlayerModel;
import models.SoupSquareModel;
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
	
	/////////////////////
	//CREATE BOAD STUFF//
	/////////////////////
	@Override
	public IView createAmoebaView(AmoebaModel model)
	{
		return new AmoebaViewASCII(model);
	}
	@Override
	public IView createPlayerView(PlayerModel model)
	{
		return new PlayerViewASCII(model);
	}
	
	@Override
	public IView createBoardView(BoardModel model)
	{
		return new BoardViewASCII(model);
	}
	@Override
	public IView createSoupSquareView(SoupSquareModel model)
	{
		return new SoupSquareViewASCII(model);
	}
	@Override
	public IView createCompassSquareView(CompassSquareModel model)
	{
		return new CompassSquareViewASCII(model);
	}
	
	@Override
	public IView createDieView(DieModel model)
	{
		return new DieViewASCII(model);
	}
	
	////////////////////////
	//CREATE OTHER OBJECTS//
	////////////////////////
	
	
	

}
