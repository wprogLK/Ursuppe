package exceptions;

public class ExceptionNotEnoughFoodOnTargetSquare extends RuntimeException
{
	public ExceptionNotEnoughFoodOnTargetSquare()
	{
		
	}
	
	 public ExceptionNotEnoughFoodOnTargetSquare(String s) 
	 { 
	    super( s ); 
	 } 
}
