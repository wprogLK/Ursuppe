/**
 * 
 */
package exceptions.InputExceptions;

import exceptions.InputException;
import helper.LanguagePack;
import interfaces.IInputException;

/**
 * @author Lukas Keller
 * @version 1.0.0
 *
 */
public class ParseToStringException extends InputException
{
	public ParseToStringException() 
	{
		super(LanguagePack.getTranslation("inputExceptionParseToString"));
	}
}
