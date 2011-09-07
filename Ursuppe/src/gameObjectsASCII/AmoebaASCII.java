/**
 * 
 */
package gameObjectsASCII;

import enums.EColor;
import logics.AmoebaLogic;

/**
 * This is the amoeba for the ASCII game.
 * @author Lukas Keller
 * @Version 1.0.0
 */
public class AmoebaASCII extends AmoebaLogic{

	/**
	 * 
	 */
	public AmoebaASCII() 
	{
		super();
	}
	
	public AmoebaASCII(EColor color, int number)
	{
		super(color,number);
	}
	
	public String toString()
	{
		return "NR: " + this.number + " DP: " + this.damagePoints;
	}

}
