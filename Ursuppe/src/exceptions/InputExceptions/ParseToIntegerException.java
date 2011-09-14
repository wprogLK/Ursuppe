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
public class ParseToIntegerException extends InputException
{
	private static String message="Error: The input can not parse to an integer! Try it again...";
	
	public ParseToIntegerException() 
	{
		super(message);
	}
}
