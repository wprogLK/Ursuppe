package interfaces;

import java.util.Date;

import enums.EColor;
import enums.EPlayer;

public interface IPlayer {
	
	///////////
	//SETTERS//
	///////////
	
	public void setName(String name);
	public void setAge(int age);
	public void setColor(EColor color);
	public void setBirthday(Date birthday);
	public void setType(EPlayer eType);
	///////////
	//GETTERS//
	///////////
	
	public String getName();
	public int getAge();

	public EColor getColor();
	
	public EPlayer getType();
	
}
