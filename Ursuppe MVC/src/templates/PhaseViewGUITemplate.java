/**
 * 
 */
package templates;

import interfaces.IViewGUI;

import java.awt.Container;
import java.util.Observable;

import javax.swing.JFrame;

/**
 * @author Lukas
 *
 */
public abstract class PhaseViewGUITemplate extends Observable implements IViewGUI
{
	protected Container container;
	protected JFrame window;
	/**
	 * 
	 */
	public PhaseViewGUITemplate() 
	{
		
	}

	@Override
	public Container getContainer()
	{
		return this.container;
	}
	
	@Override
	public JFrame getJFrame()
	{
		return this.window;
	}
	

	@Override
	public void start()
	{
		//do nothing
	}
	
	


}
