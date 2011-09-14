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
public class ImpossibleStartPositionException extends InputException
{
	private static String message="Error: Sorry but your chosen startposition doesn't exist or is already occupied by another player. Try it again...";
	
	public ImpossibleStartPositionException()
	{
		super(message);
	}
}
