import java.util.Observable;


public class PhaseAViewASCII implements IViewASCII
{
	private ModelPhaseA model;
	private boolean run=false;
	
	public PhaseAViewASCII(ModelPhaseA model)
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
	
		askName();
		//this.model.setEPhase(EPhase.PhaseB);	
		askAge();
		
		this.run=false;
		this.model.setEPhase(EPhase.PhaseB);
		
	}

	private void askAge() 
	{
		while(this.run)
		{
			String name=UserInput.realUserInput("How old are you?");
			
			this.model.setName(name);
			break;
		}
	}

	private void askName() 
	{
		while(this.run)
		{
			String name=UserInput.realUserInput("What is your name?");
			
			this.model.setName(name);
			break;
		}
	}
	
	public void stop()
	{
		this.run=false;
	}

}
