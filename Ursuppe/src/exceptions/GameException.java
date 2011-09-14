/**
 * 
 */
package exceptions;

/**
 * @author Lukas Keller
 * @version 1.0.0
 */
public abstract class GameException extends Exception
{
	
	
	public GameException(String message)
	{
		super(message);
	}
}
