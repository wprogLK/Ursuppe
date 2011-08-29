/**
 * 
 */
package templates;

import java.awt.geom.Point2D;

import interfaces.ISquare;

/**
 * @author Lukas
 *
 */
public class SquareTemplate implements ISquare
{
	ISquare upSquare;
	ISquare downSquare;
	
	ISquare leftSquare;
	ISquare rightSquare;
	
	
	Point2D position;
	
	/**
	 * 
	 */
	public SquareTemplate() 
	{
		
	}

	//////////////////////
	//NEIGHBOURN SQUARES//
	//////////////////////

	@Override
	public ISquare getUpSquare() 
	{
		return this.upSquare;
	}

	@Override
	public ISquare getDownSquare() 
	{
		return this.downSquare;
	}

	@Override
	public ISquare getLeftSquare() 
	{
		return this.leftSquare;
	}

	@Override
	public ISquare getRightSquare() 
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
	public void setUpSquare(ISquare square) 
	{
		this.upSquare=square;
	}

	@Override
	public void setDownSquare(ISquare square) 
	{
		this.downSquare=square;
	}

	@Override
	public void setLeftSquare(ISquare square) 
	{
		this.leftSquare=square;
	}

	@Override
	public void setRightSquare(ISquare square) 
	{
		this.rightSquare=square;
	}

	//////////////////////
	///////POSITION///////
	//////////////////////

	
	@Override
	public Point2D getPosition() 
	{
		return this.position;
	}

	@Override
	public void setPosition(Point2D position) 
	{
		this.position=position;
	}

}
