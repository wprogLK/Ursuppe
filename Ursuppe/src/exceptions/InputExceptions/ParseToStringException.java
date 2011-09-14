/**
 * 
 */
package exceptions.InputExceptions;

import exceptions.InputException;
import interfaces.IInputException;

/**
 * @author Lukas Keller
 * @version 1.0.0
 *
 */
public class ParseToStringException extends InputException
{
	private static String message="Error: The input can not cast to a string! Try it again...";

	public ParseToStringException() 
	{
		super(message);
	}
}
