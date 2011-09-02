/**
 * 
 */
package logics;

import java.util.ArrayList;

import enums.EColor;
import templates.SquareTemplate;
import interfaces.IAmoeba;
import interfaces.IFood;
import interfaces.ISoupSquare;


/**
 * @author Lukas Keller
 * @version 1.0.0
 *
 */
public class SoupSquareLogic extends SquareTemplate implements ISoupSquare
{
	protected ArrayList<IAmoeba> amoebas=new ArrayList<IAmoeba>();
	protected ArrayList<IFood> foods=new ArrayList<IFood>();
	
	protected ISoupSquare upSquare;
	protected ISoupSquare downSquare;
	
	protected ISoupSquare leftSquare;
	protected ISoupSquare rightSquare;
	
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

	//////////
	//AMOEBA//
	//////////
	
	@Override
	public void addAmoeba(IAmoeba amoeba) 
	{
		assert(!this.amoebas.contains(amoeba));
		
		this.amoebas.add(amoeba);
	}

	@Override
	public void removeAmoeba(IAmoeba amoeba) 
	{
		assert(this.amoebas.contains(amoeba));
		
		this.amoebas.remove(amoeba);
	}
	
	@Override
	public boolean isSquareEmpty()
	{
		return this.amoebas.isEmpty();
	}
	
	@Override 
	public int getAmoebaNumberWithColor(EColor color)
	{
		int counter=0;
		
		for(IAmoeba amoeba:this.amoebas)
		{
			if(amoeba.getColor()==color)
			{
				counter++;
			}
		}
		
		return counter;
	}
	
	////////
	//FOOD//
	////////

	@Override
	public void addFood(IFood food) 
	{
		assert(!this.foods.contains(food));
		
		this.foods.add(food);
	}

	@Override
	public void removeFood(IFood food) 
	{
		assert(this.foods.contains(food));
		
		this.foods.remove(food);
	}

	@Override
	public void removeFood(EColor color, int number) 
	{
//		boolean foodWithColorExist=true;		//TODO
//		
//		while()
//		
	}
	
	@Override
	public boolean hasEnoughFoodOfColor(EColor color, int numberToCount)
	{
		boolean count=true;
		
		int coloredFoodCounter=0;
		
		for(int foodCounter=0; (foodCounter<this.foods.size()) && (count); foodCounter++)
		{
			IFood food=this.foods.get(foodCounter);
			
			if(food.getColor()==color)
			{
				coloredFoodCounter++;
				
				if(foodCounter>=foodCounter)
				{
					return true;
				}
			}
		}
		return false;
	}
	
	@Override 
	public int getFoodNumberWithColor(EColor color)
	{
		int counter=0;
		
		for(IFood food:this.foods)
		{
			if(food.getColor()==color)
			{
				counter++;
			}
		}
		
		return counter;
	}
	
	//////////////
	//TO STRING//
	/////////////
	public String getLineNumber(int nr)
	{
		return "Soup square";
	}
	

}
