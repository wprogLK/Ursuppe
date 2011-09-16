package templates;

/**
 * 
 */

import java.util.Observable;
import java.util.Observer;

import enums.EPhase;


import models.GameModel;
import models.phases.MainMenuPhaseModel;
import models.phases.SplashScreenPhaseModel;
import interfaces.IGame;
import interfaces.IModel;
import interfaces.IModule;
import interfaces.IUrsuppe;
import interfaces.IView;

/**
 * @author Lukas
 *
 */
public abstract class UrsuppeTemplate implements IUrsuppe
{
	
	//////////
	//BASICS//
	//////////
	
	private IGame game;				//If a model use it implement like: new XModel(game). If a view use it do it with a getGame in model!
	private IModule module;
	
	private EPhase currentEPhase;
	private IModel currentModel;
	private IView currentView;
	/////////
	//VIEWS//
	/////////
	
	
	//////////
	//MODELS//
	//////////
	private SplashScreenPhaseModel phaseSplashScreenModel;
	private MainMenuPhaseModel phaseMainMenuScreenModel;
	
	/**
	 * 
	 */
	public UrsuppeTemplate(IModule module) 
	{
		this.module=module;
		
		this.initialize();
		
		this.start();
		
		this.run();
	}
	
	//////////
	//BASICS//
	//////////
	
	protected void initialize()
	{
		//do nothing
	}
	
	private void start()
	{
		createPhaseSplashScreen();
	}
	
	protected abstract void run();
	
	////////
	//GAME//
	////////
	
	public void newGame()
	{
		this.game=new GameModel();
	}

	
	//////////
	//UPDATE//
	//////////
	
	@Override
	public void update(Observable o, Object arg) 
	{
		if(!this.currentEPhase.equals(this.currentModel.getCurrentEPhase()))
		{
			this.changePhase(this.currentModel.getCurrentEPhase());
		}
	}

	private void changePhase(EPhase ePhase)
	{
		this.currentModel.deleteObservers();
		
		switch (ePhase)
		{
			case phaseSplashScreen:
			{
				createPhaseSplashScreen();
				
				break;
			}
			case phaseMainMenu:
			{
				createPhaseMainMenu();
			}
		}
		
		this.run();
	}
	

	private void createPhaseSplashScreen()
	{
		this.currentEPhase=EPhase.phaseSplashScreen;
		
		this.phaseSplashScreenModel=new SplashScreenPhaseModel();
		this.currentModel=this.phaseSplashScreenModel;
		
		this.currentView=this.module.createSplashScreenPhaseView(this.phaseSplashScreenModel);
		
		this.phaseSplashScreenModel.addObserver(currentView);
		this.phaseSplashScreenModel.addObserver(this);
	}
	
	
	private void createPhaseMainMenu()
	{
		this.currentEPhase=EPhase.phaseMainMenu;
		
		this.phaseMainMenuScreenModel=new MainMenuPhaseModel();
		this.currentModel=this.phaseMainMenuScreenModel;
		
		this.currentView=this.module.createMainMenuPhaseView(this.phaseMainMenuScreenModel);
		
		this.phaseMainMenuScreenModel.addObserver(currentView);
		this.phaseMainMenuScreenModel.addObserver(this);
	}
	
	/////////////
	//RUN LOGIC//
	/////////////
	
	
	/////////////////////
	//SETTERS & GETTERS//
	/////////////////////
	
	public void setGame(IGame game)
	{
		this.game=game;
	}
	
	public IView getCurrentView()
	{
		return this.currentView;
	}
	
	
	
	
	
}
