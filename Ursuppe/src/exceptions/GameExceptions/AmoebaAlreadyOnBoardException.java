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
public class AmoebaAlreadyOnBoardException extends GameException
{
	public AmoebaAlreadyOnBoardException() 
	{
		super(LanguagePack.getTranslation("gameExceptionAmoebaAlreadyOnBoard"));

	}
}
