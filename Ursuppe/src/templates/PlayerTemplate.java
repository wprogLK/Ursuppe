package templates;

import interfaces.IPlayer;

public abstract class PlayerTemplate implements IPlayer 
{
	protected String name;
	protected int age;
	
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
}
