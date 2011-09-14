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
public class ImpossibleStartPositionException extends InputException
{
	public ImpossibleStartPositionException()
	{
		super(LanguagePack.getTranslation("inputExceptionImpossibleStartPosition"));
	}
}
