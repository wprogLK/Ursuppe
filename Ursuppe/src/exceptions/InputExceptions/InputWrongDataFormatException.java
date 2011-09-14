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
public class InputWrongDataFormatException extends InputException
{
	public InputWrongDataFormatException()
	{
		super(LanguagePack.getTranslation("inputExceptionInputWrongDataFormate"));
	}
}
