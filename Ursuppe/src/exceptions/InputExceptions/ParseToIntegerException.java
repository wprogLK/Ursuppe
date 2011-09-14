/**
 * 
 */
package exceptions.InputExceptions;

import helper.LanguagePack;
import exceptions.InputException;

/**
 * @author Lukas Keller
 * @version 1.0.0
 *
 */
public class ParseToIntegerException extends InputException
{
	public ParseToIntegerException() 
	{
		super(LanguagePack.getTranslation("inputExceptionParseToInteger"));
	}
}
