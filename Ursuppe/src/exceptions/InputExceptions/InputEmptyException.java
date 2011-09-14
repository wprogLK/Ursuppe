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
public class InputEmptyException extends InputException
{
	private static String message="Error: Your input was empty but empty input is not valid! Try it again...";
	
	public InputEmptyException()
	{
		super(message);
	}
}
