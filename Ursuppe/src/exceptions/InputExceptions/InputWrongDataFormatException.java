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
public class InputWrongDataFormatException extends InputException
{
	private static String message="Error: Your input hasn't the correct data format! Try it again...";
	
	public InputWrongDataFormatException()
	{
		super(message);
	}
}
