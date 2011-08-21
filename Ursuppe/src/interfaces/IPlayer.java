package interfaces;

import java.util.Date;

import enums.EColor;

public interface IPlayer {
	
	///////////
	//SETTERS//
	///////////
	
	public void setName(String name);
	public void setAge(int age);
	public void setColor(EColor color);
	public void setBirthday(Date birthday);
	
	///////////
	//GETTERS//
	///////////
	
	public String getName();
	public int getAge();
	
	public String getType();
}
