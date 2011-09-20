/**
 * 
 */
package templates;

import interfaces.views.IViewGUI;

import java.awt.Container;
import java.io.PrintStream;
import java.util.Observable;

import javax.swing.JFrame;

import helpers.Setting;
/**
 * @author Lukas
 *
 */
public abstract class GameObjectViewGUITemplate extends Observable implements IViewGUI
{
	protected Container container;
	protected JFrame window;
	
	protected PrintStream outStream=Setting.guiOut;
	protected PrintStream errStream=Setting.guiErr;
	/**
	 * 
	 */
	public GameObjectViewGUITemplate() 
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
	

//	@Override
//	public void start()
//	{
//		//do nothing
//	}
	
	@Override
	public void stop()
	{
		
	}
	


}