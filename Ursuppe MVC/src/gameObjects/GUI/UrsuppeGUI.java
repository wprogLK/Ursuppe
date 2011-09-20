package gameObjects.GUI;

import java.awt.Container;

import javax.swing.JFrame;

import enums.EPhase;

import helpers.Setting;
import helpers.UserInput;
import interfaces.IModule;
import interfaces.views.IViewGUI;
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
	
	public UrsuppeGUI(IModule module, EPhase startEPhase)
	{
		super(module, startEPhase);
	}

	@Override
	protected void initialize() 
	{
		UserInput.setOutStream(Setting.guiOut);
		UserInput.setErrorStream(Setting.guiErr);
		
		UserInput.turnOnGUIMode();
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
	public void run() 
	{
		this.run=true;
		
		this.window.getContentPane().removeAll();
		
		System.out.println("DO RUN ");
		IViewGUI view=(IViewGUI) this.getCurrentView();
	
		this.container=view.getContainer();
		this.window.getContentPane().add(this.container);
		
		this.window.setVisible(true);	//needed to refresh the window
	}
}
