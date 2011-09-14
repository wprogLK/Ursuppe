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
public class InputWrongSizeException extends InputException
{
	public InputWrongSizeException(String expression)
	{
		super(LanguagePack.getTranslation("inputExceptionInputWrongSize"));
	}
}
