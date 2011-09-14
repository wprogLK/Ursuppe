/**
 * 
 */
package exceptions;

import helper.LanguageSetup;

/**
 * @author Lukas Keller
 * @version 1.0.0
 */
public abstract class InputException extends Exception
{
	static protected LanguageSetup languageSetup;
	
	public InputException(String message)
	{
		super(message);
	}
}
