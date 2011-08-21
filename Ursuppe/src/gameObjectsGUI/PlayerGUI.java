package gameObjectsGUI;

import java.util.Date;

import enums.EColor;
import templates.PlayerTemplate;

public class PlayerGUI  extends PlayerTemplate
{

	/**
	 * creates a default player
	 * @see PlayerTemplate
	 */
	public PlayerGUI()
	{
		super();
	}
	
	/**
	 * creates a concrete player with name, age and color
	 * @param name
	 * @param age
	 * @param color
	 */
	public PlayerGUI(String name,Date birthday, int age, EColor color)
	{
		super(name,birthday,age,color);
	}
	
	
	@Override
	public String getType() {
		return "TYPE GUI";
	}
}
