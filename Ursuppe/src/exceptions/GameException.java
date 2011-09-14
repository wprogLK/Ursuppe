/**
 * 
 */
package exceptions;

import helper.LanguageSetup;

/**
 * @author Lukas Keller
 * @version 1.0.0
 */
public abstract class GameException extends Exception
{
	static protected LanguageSetup languageSetup;
	
	public GameException(String message)
	{
		super(message);
	}
}
