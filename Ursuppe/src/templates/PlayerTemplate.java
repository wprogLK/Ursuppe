package templates;

import java.io.Serializable;

import enums.EColor;
import interfaces.IPlayer;

public abstract class PlayerTemplate implements IPlayer, Serializable
{
	protected String name;
	protected int age;
	protected EColor color;
	
	/**
	 * is the default constructor
	 * 
	 * </br> name will be {@code [SubjectName]}
	 * </br> age will be {@code 0}
	 * </br> color will be {@link EColor#Default}
	 */
	public PlayerTemplate()
	{
		this.setName("[SubjectName]");
		this.setAge(0);
		this.setColor(EColor.Default);
	}
	
	/**
	 * is the concrete constructor
	 * @param name
	 * @param age
	 * @param color
	 */
	public PlayerTemplate(String name, int age, EColor color)
	{
		this.setName(name);
		this.setAge(age);
		this.setColor(color);
	}
	
	
	
	///////////
	//SETTERS//
	///////////
	@Override
	public void setName(String name) 
	{
		this.name=name;
	}

	@Override
	public void setAge(int age) 
	{
		this.age=age;
	}
	
	public void setColor(EColor color)
	{
		this.color=color;
	}

	///////////
	//GETTERS//
	///////////
	@Override
	public String getName() 
	{
		return this.name;
	}

	@Override
	public int getAge() 
	{
		return this.age;
	}
	
	public EColor getColor()
	{
		return this.color;
	}
}
