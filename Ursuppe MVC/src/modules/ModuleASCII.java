package modules;

import views.ASCII.MainMenuPhaseViewASCII;
import views.ASCII.SplashScreenPhaseViewASCII;
import interfaces.IModule;
import interfaces.IView;
import models.phases.*;

public class ModuleASCII implements IModule
{

	@Override
	public IView createSplashScreenPhaseView(SplashScreenPhaseModel model)
	{
		return new SplashScreenPhaseViewASCII(model);
	}
	
	@Override
	public IView createMainMenuPhaseView(MainMenuPhaseModel model)
	{
		return new MainMenuPhaseViewASCII(model);
	}

}
