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
public class InputWrongTokenException extends InputException
{
	
	
	private static String message="Error: A token %s doesn't exist. Try it again...";
	
	public InputWrongTokenException(char token)
	{
		super(String.format(message, token));
	}
}
