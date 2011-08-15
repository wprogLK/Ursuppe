/**
 * 
 */
package cheats;

import enums.GameColor;
import exceptions.ExceptionCheatInputInvalid;
import game.*;

/**
 * @author Lukas
 *
 */
public abstract class cheatSetPlayerName {
	private Game game;
	private GameColor color;
	/**
	 * 
	 */
	public cheatSetPlayerName() 
	{
		

	}
	public static  String cheatSetPlayerName(String strColor, String name,Game game) {
		GameColor color;
		
		if (strColor.matches(GameColor.blue.toString()))
		{
			color=GameColor.blue;
		}
		else if(strColor.matches(GameColor.red.toString()))
		{
			color=GameColor.red;
		}
		else if(strColor.matches(GameColor.yellow.toString()))
		{
			color=GameColor.yellow;
		}
		else
		{
			throw new ExceptionCheatInputInvalid("2nd argument was wrong" );
		}
		
		Player player=game.getPlayer(color);
		
		player.setName(name);
		
		return "\n Name of player [" + color + " ] is now " +player.getName() ;

		
	}
	
	

}
