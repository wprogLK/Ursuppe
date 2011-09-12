/**
 * 
 */
package exceptions;

import interfaces.IInputException;

/**
 * @author Lukas
 *
 */
public class InputException extends Exception
{
	String message;
	
	public InputException(String message)
	{
		super(message);
	}
}
