package templates;

/**
 * 
 */

import java.util.Observable;

import annotations.OnlyForTesting;

import enums.EPhase;


import models.*;
import models.phases.*;
import interfaces.IModule;
import interfaces.IUrsuppe;
import interfaces.models.IGameModel;
import interfaces.models.IPhaseModel;
import interfaces.views.IView;

/**
 * @author Lukas
 *
 */
public abstract class UrsuppeTemplate implements IUrsuppe
{
	
	//////////
	//BASICS//
	//////////
	
	private IGameModel game;				//If a model use it implement like: new XModel(game). If a view use it do it with a getGame in model!
	private IModule module;
	
	private EPhase currentEPhase;
	private IPhaseModel currentPhaseModel;
	private IView currentView;
	
	protected boolean run=false;
	//////////
	//MODELS//
	//////////
	
	private AboutPhaseModel phaseAboutModel;
	private AchievementsPhaseModel phaseAchievementsModel;
	private BreakMenuPhaseModel phaseBreakMenuModel;
	private CheatsPhaseModel phaseCheatsModel;
	private ExitPhaseModel phaseExitModel;
	private GameEndPhaseModel phaseGameEndModel;
	private HelpPhaseModel phaseHelpModel;
	private LoadGamePhaseModel phaseLoadGameModel;
	private MainMenuPhaseModel phaseMainMenuScreenModel;
	private NewGamePhaseModel phaseNewGameModel;
	private OptionsPhaseModel phaseOptionsModel;
	
	private Phase1Model phase1Model;
	private Phase2Model phase2Model;
	private Phase3Model phase3Model;
	private Phase4Model phase4Model;
	private Phase5Model phase5Model;
	private Phase6Model phase6Model;
	
	private Preparation1PhaseModel phasePreparation1Model;
	private Preparation2PhaseModel phasePreparation2Model;
	private Preparation3PhaseModel phasePreparation3Model;
	
	private SaveGamePhaseModel phaseSaveGameModel;
	private SplashScreenPhaseModel phaseSplashScreenModel;
	private StatisticsPhaseModel phaseStatisticsModel;
	
	
	
	
	/**
	 * default constructor
	 */
	public UrsuppeTemplate(IModule module) 
	{
		this.module=module;
		
		this.initialize();
		
		this.changePhase(EPhase.phaseSplashScreen);
		
//		this.run();
	}
	
	public UrsuppeTemplate(IModule module, EPhase startEPhase) 
	{
		this.module=module;
		
		this.initialize();
		this.changePhase(startEPhase);
		
//		this.run();
	}
	
	//////////
	//BASICS//
	//////////
	
	protected void initialize()
	{
		//do nothing
	}
	
//	private void start()
//	{
//		createPhaseSplashScreen();
//		this.currentEPhase=EPhase.phaseSplashScreen;
//		
//		setupObserver();
//	}
	
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
		if(!this.currentEPhase.equals(this.currentPhaseModel.getCurrentEPhase()))
		{
			this.changePhase(this.currentPhaseModel.getCurrentEPhase());
		}
	}

	private void changePhase(EPhase ePhase)
	{	
		if(this.currentPhaseModel!=null)
		{
			this.currentPhaseModel.deleteObservers();
		}
		
		
		boolean valid=true;
		
		switch (ePhase)
		{
			case phaseAbout:
			{
				createPhaseAbout();
				break;
			}
			case phaseAchievements:
			{
				createPhaseAchievements();
				break;
			}
			case phaseBreakMenu:
			{
				createPhaseBreakMenu();
				break;
			}
			case phaseCheats:
			{
				createPhaseCheats();
				break;
			}
			case phaseExit:
			{
				createPhaseExit();
				break;
			}
			case phaseGameEnd:
			{
				createPhaseGameEnd();
				break;
			}
			case phaseHelp:
			{
				createPhaseHelp();
				break;
			}
			case phaseLoadGame:
			{
				createPhaseLoadGame();
				break;
			}
			case phaseMainMenu:
			{
				createPhaseMainMenu();
				break;
			}
			case phaseNewGame:
			{
				createPhaseNewGame();
				break;
			}
			case phaseOptions:
			{
				createPhaseOptions();
				break;
			}
			case phase1:
			{
				createPhase1();
				break;
			}
			case phase2:
			{
				createPhase2();
				break;
			}
			case phase3:
			{
				createPhase3();
				break;
			}
			case phase4:
			{
				createPhase4();
				break;
			}
			case phase5:
			{
				createPhase5();
				break;
			}
			case phase6:
			{
				createPhase6();
				break;
			}
			case phasePreparation1:
			{
				createPhasePreparation1();
				break;
			}
			case phasePreparation2:
			{
				createPhasePreparation2();
				break;
			}
			case phasePreparation3:
			{
				createPhasePreparation3();
				break;
			}
			case phaseSaveGame:
			{
				createPhaseSaveGame();
				break;
			}
			case phaseSplashScreen:
			{
				createPhaseSplashScreen();
				break;
			}
			case phaseStatistics:
			{
				createPhaseStatistics();
				break;
			}
			default:
			{
				//TODO
				valid=false;
				break;
			}
			
		}
		
		if(valid)
		{
			this.currentEPhase=ePhase;
			
			setupObserver();
		}
		
		if(this.run)
		{
			this.run();
		}
	}
	

	private void setupObserver() 
	{
		System.out.println("SETUP OBSERVER");
		
		this.currentPhaseModel.addObserver(this.currentView);
		this.currentPhaseModel.addObserver(this);
	}

	private void createPhaseAbout() 
	{
		this.phaseAboutModel=new AboutPhaseModel();
		this.currentPhaseModel=this.phaseAboutModel;
		
		this.currentView=this.module.createAboutPhaseView(this.phaseAboutModel);
	}

	private void createPhaseAchievements() 
	{
		this.phaseAchievementsModel=new AchievementsPhaseModel();
		this.currentPhaseModel=this.phaseAchievementsModel;
		
		this.currentView=this.module.createAchievementsPhaseView(this.phaseAchievementsModel);
	}

	private void createPhaseBreakMenu() 
	{
		this.phaseBreakMenuModel=new BreakMenuPhaseModel();
		this.currentPhaseModel=this.phaseBreakMenuModel;
		
		this.currentView=this.module.createBreakMenuPhaseView(this.phaseBreakMenuModel);
	}

	private void createPhaseCheats() 
	{
		this.phaseCheatsModel=new CheatsPhaseModel();
		this.currentPhaseModel=this.phaseCheatsModel;
		
		this.currentView=this.module.createCheatsPhaseView(this.phaseCheatsModel);
	}

	private void createPhaseExit() 
	{
		this.phaseExitModel=new ExitPhaseModel();
		this.currentPhaseModel=this.phaseExitModel;
		
		this.currentView=this.module.createExitPhaseView(this.phaseExitModel);
	}

	private void createPhaseGameEnd() 
	{
		this.phaseGameEndModel=new GameEndPhaseModel();
		this.currentPhaseModel=this.phaseGameEndModel;
		
		this.currentView=this.module.createGameEndPhaseView(this.phaseGameEndModel);
	}

	private void createPhaseHelp() 
	{
		this.phaseHelpModel=new HelpPhaseModel();
		this.currentPhaseModel=this.phaseHelpModel;
		
		this.currentView=this.module.createHelpPhaseView(this.phaseHelpModel);
	}

	private void createPhaseLoadGame() 
	{
		this.phaseLoadGameModel=new LoadGamePhaseModel();
		this.currentPhaseModel=this.phaseLoadGameModel;
		
		this.currentView=this.module.createLoadGamePhaseView(this.phaseLoadGameModel);
	}

	private void createPhaseMainMenu()
	{
		this.phaseMainMenuScreenModel=new MainMenuPhaseModel();
		this.currentPhaseModel=this.phaseMainMenuScreenModel;
		
		this.currentView=this.module.createMainMenuPhaseView(this.phaseMainMenuScreenModel);
	}
	
	private void createPhaseNewGame() 
	{
		this.phaseNewGameModel=new NewGamePhaseModel();
		this.currentPhaseModel=this.phaseNewGameModel;
		
		this.currentView=this.module.createNewGamePhaseView(this.phaseNewGameModel);
		}

	private void createPhaseOptions() 
	{
		this.phaseOptionsModel=new OptionsPhaseModel();
		this.currentPhaseModel=this.phaseOptionsModel;
		
		this.currentView=this.module.createOptionsPhaseView(this.phaseOptionsModel);
	}

	private void createPhase1() 
	{
		this.phase1Model=new Phase1Model();
		this.currentPhaseModel=this.phase1Model;
		
		this.currentView=this.module.createPhase1View(this.phase1Model);
	}

	private void createPhase2() 
	{
		this.phase2Model=new Phase2Model();
		this.currentPhaseModel=this.phase2Model;
		
		this.currentView=this.module.createPhase2View(this.phase2Model);
	}

	private void createPhase3() 
	{
		this.phase3Model=new Phase3Model();
		this.currentPhaseModel=this.phase3Model;
		
		this.currentView=this.module.createPhase3View(this.phase3Model);
	}

	private void createPhase4() 
	{
		this.phase4Model=new Phase4Model();
		this.currentPhaseModel=this.phase4Model;
		
		this.currentView=this.module.createPhase4View(this.phase4Model);
	}

	private void createPhase5() 
	{
		this.phase5Model=new Phase5Model();
		this.currentPhaseModel=this.phase5Model;
		
		this.currentView=this.module.createPhase5View(this.phase5Model);
	}

	private void createPhase6() 
	{
		this.phase6Model=new Phase6Model();
		this.currentPhaseModel=this.phase6Model;
		
		this.currentView=this.module.createPhase6View(this.phase6Model);
	}

	private void createPhasePreparation1() 
	{
		this.phasePreparation1Model=new Preparation1PhaseModel();
		this.currentPhaseModel=this.phasePreparation1Model;
		
		this.currentView=this.module.createPreparation1PhaseView(this.phasePreparation1Model);
	}

	private void createPhasePreparation2() 
	{
		this.phasePreparation2Model=new Preparation2PhaseModel();
		this.currentPhaseModel=this.phasePreparation2Model;
		
		this.currentView=this.module.createPreparation2PhaseView(this.phasePreparation2Model);
	}

	private void createPhasePreparation3() 
	{
		this.phasePreparation3Model=new Preparation3PhaseModel();
		this.currentPhaseModel=this.phasePreparation3Model;
		
		this.currentView=this.module.createPreparation3PhaseView(this.phasePreparation3Model);
	}

	private void createPhaseSaveGame() 
	{
		this.phaseSaveGameModel=new SaveGamePhaseModel();
		this.currentPhaseModel=this.phaseSaveGameModel;
		
		this.currentView=this.module.createSaveGamePhaseView(this.phaseSaveGameModel);
	}
	
	private void createPhaseSplashScreen()
	{
		this.phaseSplashScreenModel=new SplashScreenPhaseModel();
		this.currentPhaseModel=this.phaseSplashScreenModel;
		
		this.currentView=this.module.createSplashScreenPhaseView(this.phaseSplashScreenModel);
	}
	
	private void createPhaseStatistics() 
	{
		this.phaseStatisticsModel=new StatisticsPhaseModel();
		this.currentPhaseModel=this.phaseStatisticsModel;
		
		this.currentView=this.module.createStatisticsPhaseView(this.phaseStatisticsModel);
	}
	
	
	
	/////////////
	//RUN LOGIC//
	/////////////
	
	
	/////////////////////
	//SETTERS & GETTERS//
	/////////////////////
	
	public void setGame(IGameModel game)
	{
		this.game=game;
	}
	
	public IView getCurrentView()
	{
		return this.currentView;
	}
	
	
	////////////////////
	//ONLY FOR TESTING//
	////////////////////
	@OnlyForTesting
	public EPhase getCurrentEPhase() 
	{
		return this.currentEPhase;
	}
	
	@OnlyForTesting
	public void turnOnTestMode()
	{
		//TODO
	}
	
	
	
	
}
