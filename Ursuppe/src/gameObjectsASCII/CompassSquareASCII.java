/**
 * 
 */
package gameObjectsASCII;

import logics.CompassSquareLogic;

/**
 * @author Lukas
 *
 */
public class CompassSquareASCII extends CompassSquareLogic{

	
	/**
	 * 
	 */
	public CompassSquareASCII() 
	{
	
		// TODO Auto-generated constructor stub
	}
	
	public String toString()
	{
		return "COMPASS";
	}
	
	public String getLineNumber(int nr)
	{
		switch(nr)
		{
			case 1:
			{
				return this.getLineOne();
			}
			case 2:
			{
				return this.getLineTwo();
			}
			case 3:
			{
				return this.getLineThree();
			}
			default:
			{
				//TODO Exception
				return "";
			}
		}
	}
	
	
	///////////////////
	//PRIVATE METHODS//
	///////////////////
	
	private String getLineOne()
	{
		return "C 1";
	}
	
	private String getLineTwo()
	{
		return "C 2";
	}
	
	private String getLineThree()
	{
		return "C 3";
	}

}
