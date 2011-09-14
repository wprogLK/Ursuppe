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
public class UnknownInstructionException extends InputException
{
	public UnknownInstructionException(String inputInstruction)
	{
		super(String.format(LanguagePack.getTranslation("inputExceptionUnknownInstruction"), inputInstruction));
	}
}
