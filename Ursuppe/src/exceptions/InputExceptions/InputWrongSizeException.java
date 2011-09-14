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
public class InputWrongSizeException extends InputException
{
	private static String message="Error: Your input hasn't the correct size! It was too %s. Try it again...";
	
	public InputWrongSizeException(String expression)
	{
		super(String.format(message, expression));
	}
}
