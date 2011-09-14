/**
 * 
 */
package exceptions.InputExceptions;

import exceptions.InputException;

/**
 * @author Lukas Keller
 * @version 1.0.0
 *
 */
public class ParseToEColorException extends InputException
{
	private static String message="Error: The input can not parse to a color!";

	public ParseToEColorException() 
	{
		super(message);
	}
}
