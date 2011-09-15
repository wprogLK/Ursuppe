import java.util.Observable;


public class PhaseBViewASCII implements IViewASCII
{
	private ModelPhaseB model;
	private boolean run=false;
	
	public PhaseBViewASCII(ModelPhaseB model)
	{
		this.model=model;	
	}
	
	@Override
	public void update(Observable o, Object arg) 
	{
		//empty
	}

	@Override
	public void run() 
	{
		this.run=true;
		
		askColor();
		
		this.run=false;
	}

	private void askColor() {
		while(true)
		{
			String color=UserInput.realUserInput("What is your favourit color?");
			
			this.model.setColor(color);
			break;
		}
	}
	
	public void stop()
	{
		this.run=false;
	}


}
