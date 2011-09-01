package interfaces;

import enums.EColor;

public interface ISoupSquare
{
	public void setUpSquare(ISoupSquare square);
	public void setDownSquare(ISoupSquare square);
	
	public void setLeftSquare(ISoupSquare square);
	public void setRightSquare(ISoupSquare square);
	
	public ISoupSquare getUpSquare();
	public ISoupSquare getDownSquare();
	
	public ISoupSquare getLeftSquare();
	public ISoupSquare getRightSquare();
	
	
	public boolean hasUpSquare();
	public boolean hasDownSquare();
	
	public boolean hasLeftSquare();
	public boolean hasRightSquare();
	
	
	public void addAmoeba(IAmoeba amoeba);
	public void removeAmoeba(IAmoeba amoeba);
	
	public void addFood(IFood food);
	public void removeFood(IFood food);
	
	public void removeFood(EColor color, int number);
	
	public boolean hasEnoughFoodOfColor(EColor color, int numberToCount);
	
	public boolean isSquareEmpty();
	
	
	public int getAmoebaNumberWithColor(EColor color);
	public int getFoodNumberWithColor(EColor color);
	
	/////////////////////
	//TO STRING METHODS//
	/////////////////////
	public String getLineNumber(int nr);
	

	
}
