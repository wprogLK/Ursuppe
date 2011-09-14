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
public class InputEmptyException extends InputException
{
	public InputEmptyException()
	{
		super(LanguagePack.getTranslation("inputExceptionInputEmpty"));
	}
}
