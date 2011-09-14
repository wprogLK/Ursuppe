/**
 * 
 */
package exceptions.GameExceptions;

import helper.LanguagePack;
import exceptions.GameException;

/**
 * @author Lukas Keller
 * @version 1.0.0
 *
 */
public class InvalidSquareException extends GameException
{
	public InvalidSquareException(int x, int y)
	{
		super(String.format(LanguagePack.getTranslation("gameExceptionInvalidSquare"),x,y));
	}
}
