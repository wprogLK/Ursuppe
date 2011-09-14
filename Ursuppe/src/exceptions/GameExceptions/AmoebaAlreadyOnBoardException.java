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
public class AmoebaAlreadyOnBoardException extends GameException
{
	private static String message="Error: Amoeba can not add to the board. The choosen amoeba exist already on the board!";
	
	public AmoebaAlreadyOnBoardException() 
	{
		super(message);

	}
}
