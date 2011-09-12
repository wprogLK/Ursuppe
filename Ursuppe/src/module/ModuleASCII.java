 package module;

import java.io.PrintStream;
import java.util.Date;

import templates.ModuleTemplate;

import enums.EColor;
import exceptions.InputException;
import gameObjectsASCII.*;
import helper.UserInput;
import interfaces.IAmoeba;
import interfaces.IBoard;
import interfaces.ICompassSquare;
import interfaces.IDie;
import interfaces.IGame;
import interfaces.IInputException;
import interfaces.IModule;
import interfaces.IPhase;
import interfaces.IPlayer;
import interfaces.ISoupSquare;

/**
 * used if {@code game} is running in ASCII mode.
 * 
 * @author Lukas Keller
 * @version 1.0.1
 *
 * @see IPhase
 * @see PhaseAASCII
 * @see IGame
 * @see GameASCII
 */
public class ModuleASCII extends ModuleTemplate
{
	public ModuleASCII(PrintStream out, PrintStream err)
	{
		super(out,err);
		
		UserInput.turnOnASCIIMode();
	}
	
	/////////////////
	//CREATE PHASES//
	/////////////////
	public IPhase createPhaseA()
	{
		return new PhaseAASCII(this);
	}
	
	public IPhase createPhaseAbout()
	{
		return new PhaseAboutASCII(this);
	}
	public IPhase createPhaseSplashScreen()
	{
		return new PhaseSplashScreenASCII(this);
	}
	public IPhase createPhaseMainMenu()
	{
		return new PhaseMainMenuASCII(this);
	}
	public IPhase createPhaseNewGame()
	{
		return new PhaseNewGameASCII(this);
	}
	public IPhase createPhaseLoadGame()
	{
		return new PhaseLoadGameASCII(this);
	}
	public IPhase createPhaseOptions()
	{
		return new PhaseOptionsASCII(this);
	}
	public IPhase createPhaseHelp()
	{
		return new PhaseHelpASCII(this);
	}
	public IPhase createPhaseCheats()
	{
		return new PhaseCheatsASCII(this);
	}
	public IPhase createPhaseAchievements()
	{
		return new PhaseAchievementsASCII(this);
	}
	public IPhase createPhaseExit()
	{
		return new PhaseExitASCII(this);
	}
	
	public IPhase createPhasePreparation1()
	{
		return new PhasePreparation1ASCII(this);
	}
	public IPhase createPhasePreparation2()
	{
		return new PhasePreparation2ASCII(this);
	}
	public IPhase createPhasePreparation3()
	{
		return new PhasePreparation3ASCII(this);
	}
	
	public IPhase createPhase1()
	{
		return new Phase1ASCII(this);
	}
	public IPhase createPhase2()
	{
		return new Phase2ASCII(this);
	}
	public IPhase createPhase3()
	{
		return new Phase3ASCII(this);
	}
	public IPhase createPhase4()
	{
		return new Phase4ASCII(this);
	}
	public IPhase createPhase5()
	{
		return new Phase5ASCII(this);
	}
	public IPhase createPhase6()
	{
		return new Phase6ASCII(this);
	}
	
	public IPhase createPhaseGameEnd()
	{
		return new PhaseGameEndASCII(this);
	}
	public IPhase createPhaseStatistics()
	{
		return new PhaseStatisticsASCII(this);
	}
	public IPhase createPhaseSaveGame()
	{
		return new PhaseSaveGameASCII(this);
	}
	public IPhase createPhaseBreakMenu()
	{
		return new PhaseBreakMenuASCII(this);
	}
	
	
	//////////////////
	//CREATE OBJECTS//
	//////////////////
	
	@Override
	public IGame createGame()
	{
		return new GameASCII(this.outStream,this.errorStream);
	}
	
	@Override
	public IPlayer createAPlayer()
	{
		IPlayer player= new PlayerASCII();
		
		EColor colorPlayer=player.getColor();
		
		player.setAmoeba(this.createAllAmoebas(colorPlayer));
		
		return player;
	}
	
	@Override
	public IPlayer createAPlayer(String name, Date birthday, int age, EColor color)
	{
		IPlayer player=new PlayerASCII(name,birthday,age,color);
		
		EColor colorPlayer=player.getColor();
		
		player.setAmoeba(this.createAllAmoebas(colorPlayer));
		
		return player;
	}
	
	@Override
	public IDie createDie()
	{
		return new DieASCII();
	}


	@Override
	public IAmoeba createAmoeba() 
	{
		return new AmoebaASCII();
	}


	@Override
	public IAmoeba createAmoeba(EColor color, int number) 
	{
		return new AmoebaASCII(color,number);
	}


	@Override
	public IBoard createBoard() 
	{
		return new BoardASCII(this.outStream,this.errorStream);
	}


	@Override
	public ISoupSquare createSoupSquare() 
	{
		return new SoupSquareASCII();
	}


	@Override
	public ICompassSquare createCompassSquare() 
	{
		return new CompassSquareASCII();
	}
	
	//////////////
	//EXCEPTIONS//
	//////////////
	public InputException createInputException(String message)
	{
		return new InputExceptionASCII(message);
	}
	
	public InputException createInputExceptionUnkownInstruction(String inputInstruction)
	{
		String str="Sorry, but I don't know the instruction " + inputInstruction + " Try it again ...";
		
		return new InputExceptionASCII(str);
	}
	
	public InputException createInputExceptionParseToString()
	{
		return new InputExceptionASCII("Error: the input can not parse to a string! Try it again ...");
	}
	
	public InputException createInputExceptionParseToInteger()
	{
		return new InputExceptionASCII("Error: the input can not parse to an integer! Try it again ...");
	}
	
	public InputException createInputExceptionParseToEColor()
	{
		return new InputExceptionASCII("Error: Your input wasn't a valid color! Try it again ...");
	}
}
