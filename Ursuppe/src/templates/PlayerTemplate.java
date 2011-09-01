package templates;

import java.io.Serializable;
import java.util.Date;

import enums.EColor;
import enums.EPlayer;
import interfaces.IPlayer;

public abstract class PlayerTemplate implements IPlayer, Serializable
{
	protected String name;
	protected int age;
	protected Date birthday;
	protected EColor color;
	protected EPlayer eType;
	protected int score;
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
		this.score=-1;
	}
	
	/**
	 * is the concrete constructor
	 * @param name
	 * @param age
	 * @param color
	 */
	public PlayerTemplate(String name, Date birthday,int age, EColor color)
	{
		this.setName(name);
		this.setAge(age);
		this.setColor(color);
		this.setBirthday(birthday);
		this.score=-1;
	}
	
	
	
	

	///////////
	//SETTERS//
	///////////
	@Override
	public void setType(EPlayer eType)
	{
		this.eType=eType;
	}
	
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
	
	@Override
	public void setColor(EColor color)
	{
		this.color=color;
	}
	
	@Override
	public void setBirthday(Date birthday) 
	{
		this.birthday=birthday;
	}
	
	@Override
	public void setScore(int value)
	{
		this.score=value;
	}

	///////////
	//GETTERS//
	///////////
	@Override
	public int getScore()
	{
		return this.score;
	}
	
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
	
	@Override
	public EColor getColor()
	{
		return this.color;
	}
	
	@Override
	public EPlayer getType()
	{
		return this.eType;
	}
}
