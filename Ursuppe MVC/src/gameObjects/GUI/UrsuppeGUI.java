package gameObjects.GUI;

import java.awt.Container;

import javax.swing.JFrame;

import interfaces.IModule;
import interfaces.IView;
import interfaces.IViewGUI;
import templates.UrsuppeTemplate;

public class UrsuppeGUI extends UrsuppeTemplate
{
	private JFrame window;
	private Container container;
	
	/**
	 * 
	 */
	public UrsuppeGUI(IModule module) 
	{
		super(module);
	}

	@Override
	protected void initialize() 
	{
		createWindow();
	}
	
	private void createWindow() 
	{
		this.window=new JFrame("Ursuppe GUI");
	
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.window.setSize(200, 200);
		this.window.setVisible(true);
	}

	@Override
	protected void run() 
	{
		this.window.getContentPane().removeAll();
		
		System.out.println("DO RUN ");
		IViewGUI view=(IViewGUI) this.getCurrentView();
	
		this.container=view.getContainer();
		this.window.getContentPane().add(this.container);
		
		this.window.setVisible(true);	//needed to refresh the window
	}
}
