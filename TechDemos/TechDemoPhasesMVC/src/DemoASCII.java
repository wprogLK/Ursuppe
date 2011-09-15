
public class DemoASCII implements IDemo
{
	ModelPhaseA modelPhaseA;
	ModelPhaseB modelPhaseB;
	
	IViewASCII viewPhaseA;
	IViewASCII viewPhaseB;
	
	public DemoASCII()
	{
		this.modelPhaseA=new ModelPhaseA(this);
		this.modelPhaseB=new ModelPhaseB(this);
		
		this.viewPhaseA=new PhaseAViewASCII(modelPhaseA);
		this.viewPhaseB=new PhaseBViewASCII(modelPhaseB);
		
		this.modelPhaseA.addObserver(viewPhaseA);
		this.modelPhaseB.addObserver(viewPhaseB);
	}
	
	@Override
	public void setCurrentPhase(EPhase phase) 
	{
		switch(phase)
		{
			case PhaseA:
			{
				this.stopViews();
				
				this.viewPhaseA.run();
				break;
			}
			case PhaseB:
			{
				this.stopViews();
				
				this.viewPhaseB.run();
				break;
			}
		}
		
	}

	@Override
	public void start(EPhase phase) 
	{
		this.setCurrentPhase(phase);
		
	}
	
	private void stopViews()
	{
		this.viewPhaseA.stop();
		this.viewPhaseB.stop();
	}

}
