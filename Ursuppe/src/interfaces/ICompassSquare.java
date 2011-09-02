package interfaces;

import java.util.ArrayList;

import enums.EDirections;

public interface ICompassSquare 
{
	/**
	 * set the position x,y of the compassSquare
	 * @param x
	 * @param y
	 */
	public void setPosition(int x, int y);
	
	/**
	 * get the x position of the compassSquare
	 * @return
	 */
	public int getPosX();
	
	/**
	 * get the y position of the compassSquare
	 * @return
	 */
	public int getPosY();
	
	/**
	 * get the current ozone layer value;
	 * @return
	 */
	public int getCurrentOzoneLayerValue();
	
	/**
	 * get the current direction
	 * @return
	 */
	public EDirections getCurrentDirection();
	
	/**
	 * go to the next ozone layer value
	 */
	public void nextOzoneLayerValue();
	
	/**
	 * go to the next direction
	 */
	public void nextDirection();
	
	/**
	 * sets a skriped ozone layer value.
	 * <br> used for testing and skripted games
	 * @param ozoneLayers
	 */
	public void setFakedOzoneLayers(ArrayList<Integer> ozoneLayers);
	/**
	 * sets a skriped directions.
	 * <br> used for testing and skripted games
	 * @param ozoneLayers
	 */
	public void setFakedDirections(ArrayList<EDirections> directions);
	
	/**
	 * Only used for ASCII game!!
	 * @param nr
	 * @return
	 */
	public String getLineNumber(int nr);
}
