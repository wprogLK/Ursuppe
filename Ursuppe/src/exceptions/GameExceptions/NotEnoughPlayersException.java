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
public class NotEnoughPlayersException extends GameException
{
	public NotEnoughPlayersException()
	{
		super(LanguagePack.getTranslation("gameExceptionNotEnoughPlayers"));
	}
}
