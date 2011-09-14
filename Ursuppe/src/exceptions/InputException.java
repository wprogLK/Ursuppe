/**
 * 
 */
package exceptions;


/**
 * @author Lukas Keller
 * @version 1.0.0
 */
public abstract class InputException extends Exception
{
	public InputException(String message)
	{
		super(message);
	}
}
