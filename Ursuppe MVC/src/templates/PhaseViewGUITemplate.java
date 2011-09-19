/**
 * 
 */
package templates;

import helpers.Setting;
import interfaces.IViewGUI;

import java.awt.Container;
import java.io.PrintStream;
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
	
	protected PrintStream outStream=Setting.guiOut;
	protected PrintStream errStream=Setting.guiErr;
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
