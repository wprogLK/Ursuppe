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
public class UnknownInstructionException extends InputException
{
	private static String message="Error: Sorry but I don't know what you want to do with the input %s . Try it again...";
	
	public UnknownInstructionException(String inputInstruction)
	{
		super(String.format(message, inputInstruction));
	}
}
