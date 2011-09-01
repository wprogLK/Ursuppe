/**
 * 
 */
package logics;

import enums.EColor;
import interfaces.IAmoeba;

/**
 * @author Lukas
 *
 */
public class AmoebaLogic implements IAmoeba
{
	private EColor color;
	
	
	@Override
	public EColor getColor() 
	{
		System.out.println("//////////////GET COLOR OF AMOEBA////////////");
		return this.color.Default;
	}
	

}
