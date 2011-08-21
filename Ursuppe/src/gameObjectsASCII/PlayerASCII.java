package gameObjectsASCII;

import java.util.Date;

import enums.EColor;
import templates.PlayerTemplate;

public class PlayerASCII extends PlayerTemplate
{
	
	/**
	 * creates a default player
	 * 
	 * <br/> for head and tail player
	 * @see PlayerTemplate
	 */
	public PlayerASCII()
	{
		super();
	}
	
	/**
	 * creates a concrete player with name, age and color
	 * @param name
	 * @param age
	 * @param color
	 */
	public PlayerASCII(String name, Date birthday, int age, EColor color)
	{
		super(name,birthday, age,color);
	}

	@Override
	public String getType() {
		return "TYPE ASCII";
	}
	
}
