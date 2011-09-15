import java.util.Observable;


public class ModelPhaseA extends Observable implements IModel
{
	private String name;
	private int age;
	
	private IDemo demo;
	
	public ModelPhaseA(IDemo demo)
	{
		this.demo=demo;
	}
	
	public void setName(String name)
	{
		setChanged();
		notifyObservers();
		
		this.name=name;
	}
	
	public void setAge(int age)
	{
		setChanged();
		notifyObservers();
		
		this.age=age;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int getAge()
	{
		return this.age;
	}
	
	public void setEPhase(EPhase phase)
	{
		this.demo.setCurrentPhase(phase);
	}
	
	

	
}
