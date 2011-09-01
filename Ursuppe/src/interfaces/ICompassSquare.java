package interfaces;

import java.util.ArrayList;

import enums.EDirections;

public interface ICompassSquare 
{
	public void setPosition(int x, int y);
	public int getPosX();
	public int getPosY();
	
	public int getCurrentOzoneLayerValue();
	public EDirections getCurrentDirection();
	
	public void nextOzoneLayerValue();
	public void nextDirection();
	
	public void setFakedOzoneLayers(ArrayList<Integer> ozoneLayers);
	public void setFakedDirections(ArrayList<EDirections> directions);
	
	public String getLineNumber(int nr);
}
