package exceptions;

import game.Game;

public class ExceptionRowOutOfRange extends RuntimeException
{

	
	 public ExceptionRowOutOfRange(Game game) 
	 { 
	    super("Error: Please chose a number betweet 1 and " + game.getnumbersOfColumn() +" for Row (y)!"); 
	 } 
}
