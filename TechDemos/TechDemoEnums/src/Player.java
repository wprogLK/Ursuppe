
public class Player
{
	private String name;
	private EColor color;
	
	public Player(String name, EColor color) 
	{
		this.name=name;
		this.color=color;
	}

	public EColor getColor() 
	{
		return this.color;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String toString()
	{
		return " Name: " + this.name + "   Color: " +this.color;
	}
	
	public void setName(String name)
	{
		this.name=name;
	}

}
