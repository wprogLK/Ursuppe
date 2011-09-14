 package interfaces;

import java.io.PrintStream;
import java.util.Date;

import enums.EColor;
import exceptions.InputException;
import exceptions.GameExceptions.AmoebaAlreadyOnBoardException;
import exceptions.GameExceptions.InvalidSquareException;
import exceptions.GameExceptions.NotEnoughPlayersException;
import exceptions.InputExceptions.ImpossibleStartPositionException;
import exceptions.InputExceptions.InputEmptyException;
import exceptions.InputExceptions.InputWrongDataFormatException;
import exceptions.InputExceptions.InputWrongSizeException;
import exceptions.InputExceptions.InputWrongTokenException;
import exceptions.InputExceptions.ParseToEColorException;
import exceptions.InputExceptions.ParseToIntegerException;
import exceptions.InputExceptions.ParseToStringException;
import exceptions.InputExceptions.UnknownInstructionException;
import gameObjectsASCII.*;
import interfaces.IGame;
import interfaces.IPhase;
import interfaces.IPlayer;
import module.*;

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
 * 
 * @see ModuleASCII
 * @see ModuleGUI
 */
public  interface IModule
{
	
	public IPhase createPhaseA();
	
	public IPhase createPhaseSplashScreen();
	public IPhase createPhaseMainMenu();
	public IPhase createPhaseNewGame();
	public IPhase createPhaseLoadGame();
	public IPhase createPhaseSaveGame();
	public IPhase createPhaseOptions();
	public IPhase createPhaseHelp();
	public IPhase createPhaseCheats();
	public IPhase createPhaseAchievements();
	public IPhase createPhaseExit();
	public IPhase createPhaseAbout();
	public IPhase createPhaseStatistics();
	
	public IPhase createPhaseBreakMenu();
	
	public IPhase createPhasePreparation1();
	public IPhase createPhasePreparation2();
	public IPhase createPhasePreparation3();
	
	public IPhase createPhase1();
	public IPhase createPhase2();
	public IPhase createPhase3();
	public IPhase createPhase4();
	public IPhase createPhase5();
	public IPhase createPhase6();
	public IPhase createPhaseGameEnd();
	
	public  IGame createGame();
	
	/**
	 * Creates a new default player with the color {@code default} and name {@code [SubjectName]}
	 * @return
	 */
	public  IPlayer createAPlayer();
	
	/**
	 * Creates a new concrete player
	 * @param name
	 * @param age
	 * @param color
	 * @return
	 */
	public IPlayer createAPlayer(String name, Date birthday, int age, EColor color);
	
	public IDie createDie();
	
	/**
	 * Creates default amoeba
	 * @return
	 */
	public IAmoeba createAmoeba();
	
	/**
	 * Creates a specific amoeba
	 * @param color
	 * @param number
	 * @return
	 */
	public IAmoeba createAmoeba(EColor color, int number);
	
	/**
	 * Creates a default board
	 * @return
	 */
	public IBoard createBoard(IGame game);
	
	/**
	 * Creates a default soupSquare
	 * @return
	 */
	public ISoupSquare createSoupSquare();
	
	/**
	 * Creates a default compassSquare
	 */
	public ICompassSquare createCompassSquare();
	
	
	//////////////
	//EXCEPTIONS//
	//////////////
	
	//********************//
	//**INPUT EXCEPTIONS**//
	//********************//
	public void throwInputExceptionUnkownInstruction(String inputInstruction) throws UnknownInstructionException;
	
	public void throwInputExceptionParseToString() throws ParseToStringException;
	public void throwInputExceptionParseToInteger() throws ParseToIntegerException;
	public void throwInputExceptionParseToEColor() throws ParseToEColorException;
	
	public void throwInputExceptionEmptyInput() throws InputEmptyException;
	
	public void throwInputExceptionWrongDataFormat() throws InputWrongDataFormatException;
	public void throwInputExceptionWrongSize(String expression) throws InputWrongSizeException;
	public void throwInputExceptionWrongToken(char tokenSign) throws InputWrongTokenException;
	
	public void throwInputExceptionImpossibleStartPosition() throws ImpossibleStartPositionException;
	
	//*******************//
	//**GAME EXCEPTIONS**//
	//*******************//
	public void throwGameExceptionInvalidSquare(int x, int y) throws InvalidSquareException;
	public void throwGameExcptionAmoebaAlreadyOnBoard() throws AmoebaAlreadyOnBoardException;
	
	public void throwGameExeptionNotEnoughPlayers() throws NotEnoughPlayersException;
	
	
}
