/**
 * 
 */
package logics;

import templates.SquareTemplate;
import interfaces.ISoupSquare;


/**
 * @author Lukas
 *
 */
public class SoupSquareLogic extends SquareTemplate implements ISoupSquare
{

	ISoupSquare upSquare;
	ISoupSquare downSquare;
	
	ISoupSquare leftSquare;
	ISoupSquare rightSquare;
	
	/**
	 * 
	 */
	public SoupSquareLogic() 
	{
		
	}

	//////////////////////
	//NEIGHBOURN SQUARES//
	//////////////////////

	@Override
	public ISoupSquare getUpSquare() 
	{
		return this.upSquare;
	}

	@Override
	public ISoupSquare getDownSquare() 
	{
		return this.downSquare;
	}

	@Override
	public ISoupSquare getLeftSquare() 
	{
		return this.leftSquare;
	}

	@Override
	public ISoupSquare getRightSquare() 
	{
		return this.rightSquare;
	}

	
	@Override
	public boolean hasUpSquare() 
	{
		if(this.upSquare!=null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean hasDownSquare() 
	{
		if(this.downSquare!=null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean hasLeftSquare() 
	{
		if(this.leftSquare!=null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean hasRightSquare() 
	{
		if(this.rightSquare!=null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	
	@Override
	public void setUpSquare(ISoupSquare square) 
	{
		this.upSquare=square;
	}

	@Override
	public void setDownSquare(ISoupSquare square) 
	{
		this.downSquare=square;
	}

	@Override
	public void setLeftSquare(ISoupSquare square) 
	{
		this.leftSquare=square;
	}

	@Override
	public void setRightSquare(ISoupSquare square) 
	{
		this.rightSquare=square;
	}

}
