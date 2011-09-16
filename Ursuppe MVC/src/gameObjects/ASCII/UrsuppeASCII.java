/**
 * 
 */
package gameObjects.ASCII;

import interfaces.IModule;
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

	@Override
	protected void run() 
	{
		this.getCurrentView().start();
	}
}
