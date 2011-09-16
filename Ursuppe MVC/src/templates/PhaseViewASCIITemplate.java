/**
 * 
 */
package templates;

import interfaces.IViewASCII;

import java.util.Observable;

/**
 * @author Lukas
 *
 */
public abstract class PhaseViewASCIITemplate implements IViewASCII
{
	
	/**
	 * 
	 */
	public PhaseViewASCIITemplate() 
	{
		
	}
	
	
	@Override
	public void update(Observable arg0, Object arg1) 
	{
		System.out.println("UPDATE IN VIEW");
	}
	
	


}
