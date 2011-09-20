/**
 * 
 */
package gameObjects.ASCII;

import annotations.OnlyForTesting;
import enums.EMode;
import enums.EPhase;
import helpers.Setting;
import helpers.UserInput;
import interfaces.IModule;
import interfaces.views.IViewASCII;
import templates.UrsuppeTemplate;

/**
 * @author Lukas
 *
 */
public class UrsuppeASCII extends UrsuppeTemplate
{
	
	/**
	 * 
	 */
	public UrsuppeASCII(IModule module, EMode mode) 
	{
		super(module, mode);
	}
	
	public UrsuppeASCII(IModule module, EPhase startEPhase, EMode mode)
	{
		super(module, startEPhase, mode);
	}
	
	@Override
	protected void initialize()
	{
		UserInput.setOutStream(Setting.asciiOut);
		UserInput.setErrorStream(Setting.asciiErr);
		
		UserInput.turnOnASCIIMode();
	}

	@Override
	public void run() 
	{
		this.run=true;
		
		IViewASCII currentASCIIView=(IViewASCII) this.getCurrentView();
		
		if(this.mode==EMode.testMode)
		{
			currentASCIIView.turnOnTestMode();
		}
		
		currentASCIIView.start();
	}
	
	@OnlyForTesting
	public IViewASCII getCurrentASCIIView()
	{
		return (IViewASCII) this.getCurrentView();
	}
}
