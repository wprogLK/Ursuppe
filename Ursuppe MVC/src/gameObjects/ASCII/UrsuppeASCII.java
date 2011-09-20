/**
 * 
 */
package gameObjects.ASCII;

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
	public UrsuppeASCII(IModule module) 
	{
		super(module);
	}
	
	public UrsuppeASCII(IModule module, EPhase startEPhase)
	{
		super(module, startEPhase);
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
		currentASCIIView.start();
	}
}
