/**
 * 
 */
package exceptions.GameExceptions;

import exceptions.GameException;

/**
 * @author Lukas Keller
 * @version 1.0.0
 *
 */
public class InvalidSquareException extends GameException
{
private static String message="Error: The square %s | %s doesn't exist! Please chose a valid square!";
	
	public InvalidSquareException(int x, int y)
	{
		super(String.format(message, x,y));
	}
}
