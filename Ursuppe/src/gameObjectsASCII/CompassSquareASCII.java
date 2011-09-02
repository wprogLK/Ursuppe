/**
 * 
 */
package gameObjectsASCII;

import logics.CompassSquareLogic;

/**
 * This is the compassSquare for the ASCII game.
 * @author Lukas Keller
 * @version 1.0.0
 */
public class CompassSquareASCII extends CompassSquareLogic{

	
	/**
	 * 
	 */
	public CompassSquareASCII() 
	{
		// TODO 
	}
	
	public String toString()
	{
		//TODO
		return "COMPASS";
	}
	
	@Override
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
		//TODO
		return "C 1";
	}
	
	private String getLineTwo()
	{
		//TODO
		return "C 2";
	}
	
	private String getLineThree()
	{
		//TODO
		return "C 3";
	}

}
