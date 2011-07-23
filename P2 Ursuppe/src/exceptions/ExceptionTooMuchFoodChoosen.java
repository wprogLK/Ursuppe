package exceptions;

public class ExceptionTooMuchFoodChoosen extends RuntimeException
{
	public ExceptionTooMuchFoodChoosen()
	{
		
	}
	
	 public ExceptionTooMuchFoodChoosen(String s) 
	 { 
	    super( s ); 
	 } 
}
