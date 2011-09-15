import java.util.Observable;


public class ModelPhaseB extends Observable implements IModel
{
	private String color;
	
	private IDemo demo;
	private boolean run=false;
	
	public ModelPhaseB(IDemo demo)
	{
		this.demo=demo;
	}
	
	public void setColor(String color)
	{
		setChanged();
		notifyObservers();
		
		this.color=color;
	}
	
	public String getColor()
	{
		return this.color;
	}
	
	public void setEPhase(EPhase phase)
	{
		this.demo.setCurrentPhase(phase);
	}

	public void stop()
	{
		this.run=false;
	}
	

	
	
}
