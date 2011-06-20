package gameObjectsASCII;

import enums.EColor;
import templates.PlayerTemplate;

public class PlayerASCII extends PlayerTemplate
{
	
	/**
	 * creates a default player
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
	public PlayerASCII(String name, int age, EColor color)
	{
		super(name,age,color);
	}

	@Override
	public String getType() {
		return "TYPE ASCII";
	}
	
}
