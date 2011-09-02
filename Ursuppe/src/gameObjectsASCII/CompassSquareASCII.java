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
		String line="";
		
		switch(nr)
		{
			case 1:
			{
				line=this.getLineOne();
				break;
			}
			case 2:
			{
				line=this.getLineTwo();
				break;
			}
			case 3:
			{
				line=this.getLineThree();
				break;
			}
			default:
			{
				//TODO Exception
				line="";
				break;
			}
		}
		
		return this.normalSquare(line);
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
