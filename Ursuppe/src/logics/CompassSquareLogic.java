package logics;

import java.util.ArrayList;
import java.util.Random;

import enums.EDirections;

import templates.SquareTemplate;
import interfaces.ICompassSquare;
import interfaces.ISquare;

/**
 * @author Lukas
 *
 */
public class CompassSquareLogic extends SquareTemplate implements ICompassSquare
{
	private int x;
	private int y;

	private int numbersOfItems=10;		//number of items on the arrayList of ozoneLayers and directions	//TODO later set this with the game
	private int minOzoneValue=4;		//TODO later set this with the game
	private int maxOzoneValue=12;		//TODO later set this with the game
	
	
	protected ArrayList<Integer> ozoneLayers=new ArrayList<Integer>();
	protected ArrayList<EDirections> directions=new ArrayList<EDirections>();
	
	public CompassSquareLogic() 
	{

	}

	@Override
	public void setPosition(int x, int y) {
		this.x=x;
		this.y=y;
		
	}

	@Override
	public int getPosX() {
		
		return this.x;
	}

	@Override
	public int getPosY() 
	{
		return this.y;
	}

	@Override
	public int getCurrentOzoneLayerValue() 
	{
		return this.ozoneLayers.get(0);
	}

	@Override
	public EDirections getCurrentDirection() 
	{
		return this.directions.get(0);
	}

	@Override
	public void nextOzoneLayerValue() 
	{
		this.ozoneLayers.remove(0);
		
		if(this.ozoneLayers.isEmpty())
		{
			this.createRandomOzoneLayers();
			//TODO to implement: if it was the last item => game over
		}
		
		
	}

	private void createRandomOzoneLayers() 
	{
		Random rnd=new Random();
		
		for(int i=0; i<this.numbersOfItems; i++)
		{
			int newItem=rnd.nextInt(this.maxOzoneValue-this.minOzoneValue)+this.minOzoneValue;
			
			assert(newItem>=this.minOzoneValue);
			assert(newItem<=this.maxOzoneValue);
			
			this.ozoneLayers.add(newItem);
		}
		
	}

	@Override
	public void nextDirection() 
	{
		this.directions.remove(0);
		
		if(this.directions.isEmpty())
		{
			this.createRandomDirections();
			//TODO to implement: if it was the last item => game over
		}
	}

	private void createRandomDirections() 
	{
		Random rnd=new Random();
		
		for(int i=0; i<this.numbersOfItems; i++)
		{
			//TODO CHECK this if the min is 1 and max 5
			int value=rnd.nextInt(5)+1;
			
			assert(value>=1);
			assert(value<=5);
			
			EDirections direction=EDirections.getDirection(value);
			
			this.directions.add(direction);
		}
		
	}

	@Override
	public void setFakedOzoneLayers(ArrayList<Integer> ozoneLayers) 
	{
		this.ozoneLayers=ozoneLayers;
		
	}

	@Override
	public void setFakedDirections(ArrayList<EDirections> directions) 
	{
		this.directions=directions;
		
	}
	
	
	//////////////
	//TO STRING//
	/////////////
	public String getLineNumber(int nr)
	{
		return "compass square";
	}
	

}
