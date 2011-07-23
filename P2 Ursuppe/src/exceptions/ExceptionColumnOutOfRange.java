package exceptions;

import game.Game;

public class ExceptionColumnOutOfRange extends RuntimeException
{
	 public ExceptionColumnOutOfRange(Game game) 
	 { 
	    super("Error: Please chose a number betweet 1 and " + game.getnumbersOfColumn() +" for Colum (x)!"); 
	 } 
}
