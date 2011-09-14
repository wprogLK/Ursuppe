 package templates;

import java.io.PrintStream;
import java.util.ArrayList;

import enums.EColor;
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
import helper.Setting;
import interfaces.IAmoeba;
import interfaces.IGame;
import interfaces.IModule;
import interfaces.IPhase;

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
public abstract class ModuleTemplate implements IModule
{
	protected PrintStream outStream;
	protected PrintStream errorStream;
	
	
	public ModuleTemplate(PrintStream out, PrintStream err)
	{
		this.outStream=out;
		this.errorStream=err;
	}
	
	protected final ArrayList<IAmoeba> createAllAmoebas(EColor color)
	{
		ArrayList<IAmoeba> amoebas=new ArrayList<IAmoeba>();
		
		for(int nr=1; nr<Setting.maxNumbersOfAmoebasPerPlayer+1; nr++)
		{
			IAmoeba amoeba=this.createAmoeba(color, nr);
			amoebas.add(amoeba);
		}
		
		return amoebas;
	}
	
	
	//////////////
	//EXCEPTIONS//
	//////////////
	
	//********************//
	//**INPUT EXCEPTIONS**//
	//********************//
	public void throwInputExceptionUnkownInstruction(String inputInstruction) throws UnknownInstructionException
	{
		throw new UnknownInstructionException(inputInstruction);
	}
	
	public void throwInputExceptionParseToString() throws ParseToStringException
	{
		
		throw new ParseToStringException();
	}
	
	public void throwInputExceptionParseToInteger() throws ParseToIntegerException
	{
		throw new ParseToIntegerException();
	}
	
	public void throwInputExceptionParseToEColor() throws ParseToEColorException
	{
		throw new ParseToEColorException();
	}
	
	public void throwInputExceptionEmptyInput() throws InputEmptyException
	{
		throw new InputEmptyException();
	}
	
	public void throwInputExceptionWrongDataFormat() throws InputWrongDataFormatException
	{
		throw new InputWrongDataFormatException();
	}
	
	public void throwInputExceptionWrongSize(String expression) throws InputWrongSizeException
	{
		throw new InputWrongSizeException(expression);
	}
	
	public void throwInputExceptionWrongToken(char tokenSign) throws InputWrongTokenException
	{
		throw new InputWrongTokenException(tokenSign);
	}
	
	public void throwInputExceptionImpossibleStartPosition() throws ImpossibleStartPositionException
	{
		throw new ImpossibleStartPositionException();
	}
	//*******************//
	//**GAME EXCEPTIONS**//
	//*******************//
	public void throwGameExceptionInvalidSquare(int x, int y) throws InvalidSquareException
	{
		throw new InvalidSquareException(x,y);
	}
	
	public void throwGameExcptionAmoebaAlreadyOnBoard() throws AmoebaAlreadyOnBoardException
	{
		throw new AmoebaAlreadyOnBoardException();
	}
	
	public void throwGameExeptionNotEnoughPlayers() throws NotEnoughPlayersException
	{
		throw new NotEnoughPlayersException();
	}
	
}
	
