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
public class NotEnoughPlayersException extends GameException
{
private static String message="Error: You can not play the game! You aren't enough players. Load or create an other one...";
	
	public NotEnoughPlayersException()
	{
		super(message);
	}
}
