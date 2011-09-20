/**
 * 
 */
package templates;

import interfaces.views.IViewASCII;

import java.io.PrintStream;
import java.util.Observable;
import helpers.Setting;
/**
 * @author Lukas
 *
 */
public abstract class GameObjectViewASCIITemplate implements IViewASCII
{
	protected PrintStream outStream=Setting.asciiOut;
	protected PrintStream errStream=Setting.asciiErr;
	/**
	 * 
	 */
	public GameObjectViewASCIITemplate() 
	{
		
	}
	
	
	@Override
	public void update(Observable arg0, Object arg1) 
	{
		System.out.println("UPDATE IN VIEW");
	}
	
	


}
