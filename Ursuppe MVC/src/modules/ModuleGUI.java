package modules;

import views.ASCII.MainMenuPhaseViewASCII;
import views.GUI.MainMenuPhaseViewGUI;
import views.GUI.SplashScreenPhaseViewGUI;
import interfaces.IModule;
import interfaces.IView;
import models.phases.*;

public class ModuleGUI implements IModule
{

	@Override
	public IView createSplashScreenPhaseView(SplashScreenPhaseModel model)
	{
		return new SplashScreenPhaseViewGUI(model);
	}
	
	@Override
	public IView createMainMenuPhaseView(MainMenuPhaseModel model)
	{
		return new MainMenuPhaseViewGUI(model);
	}

}
