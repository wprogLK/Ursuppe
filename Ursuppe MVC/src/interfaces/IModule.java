package interfaces;

import models.phases.MainMenuPhaseModel;
import models.phases.SplashScreenPhaseModel;

public interface IModule 
{
	public IView createSplashScreenPhaseView(SplashScreenPhaseModel model);
	public IView createMainMenuPhaseView(MainMenuPhaseModel model);
}
