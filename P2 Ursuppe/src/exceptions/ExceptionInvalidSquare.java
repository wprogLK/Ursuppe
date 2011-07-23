package exceptions;

public class ExceptionInvalidSquare extends RuntimeException
{

	
	 public ExceptionInvalidSquare() 
	 { 
	    super("Error: You chose an invalid square! Try it again...."); 
	 } 
	 public ExceptionInvalidSquare(String str) 
	 { 
	    super("Error: You have to set an ameba on a neighbour square where already an ameba of your color is. Try it again..."); 
	 } 
}
