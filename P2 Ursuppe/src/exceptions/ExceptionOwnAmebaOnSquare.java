package exceptions;

public class ExceptionOwnAmebaOnSquare extends RuntimeException
{

	
	 public ExceptionOwnAmebaOnSquare() 
	 { 
	    super("Error: You chose a square, where you have already one of your amebas! Please chose another square..."); 
	 } 
}
