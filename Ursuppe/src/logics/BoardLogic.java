/**
 * 
 */
package logics;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import interfaces.IBoard;
import interfaces.ISquare;

/**
 * @author Lukas
 *
 */
public class BoardLogic implements IBoard
{
	Map<Point2D, String> soupBoard = new HashMap<Point2D, String>();
	
	
	public BoardLogic()
	{
		Point2D point1=new Point2D.Double(0,0);
		Point2D point2=new Point2D.Double(1,0);
		this.soupBoard.put(point1, "0,0");
		
		System.out.println(this.soupBoard.get(point1));
		
		this.soupBoard.put(point2, null);
		
		System.out.println(this.soupBoard.size());
	}
	
	
}
