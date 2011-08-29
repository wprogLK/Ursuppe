package interfaces;

import java.awt.geom.Point2D;

public interface ISquare 
{
	public ISquare getUpSquare();
	public ISquare getDownSquare();
	
	public ISquare getLeftSquare();
	public ISquare getRightSquare();
	
	
	public boolean hasUpSquare();
	public boolean hasDownSquare();
	
	public boolean hasLeftSquare();
	public boolean hasRightSquare();
	
	
	public void setUpSquare(ISquare square);
	public void setDownSquare(ISquare square);
	
	public void setLeftSquare(ISquare square);
	public void setRightSquare(ISquare square);
	
	
	public Point2D getPosition();
	public void setPosition(Point2D position);
	
}
