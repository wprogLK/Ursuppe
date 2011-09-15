import java.util.Observable;

public class Model extends Observable{
	
	private int curValue;
	private String name;
	
	public Model()
	{
		curValue=0;
		setChanged();
		notifyObservers();
	}
	
	public void incValue()
	{
		setChanged();
		notifyObservers();
		
		curValue++;
	}
	
	public int getValue()
	{
		return curValue;
	}
	
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name) throws Exception
	{
		if(this.name!=name)
		{
			if(name.equals(""))
			{
				System.out.println("THROW ERROR!....");
				throw new InputEmptyException();
			}
			else
			{
				this.name=name;
				
				setChanged();
				notifyObservers();
			}
		}
	}

	
	
	
}
