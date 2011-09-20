/**
 * 
 */
package views.GUI.phases;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.util.Observable;

import javax.swing.JButton;

import controllers.MainMenuController;
import controllers.SplashScreenController;

import templates.PhaseViewASCIITemplate;
import templates.PhaseViewGUITemplate;

import models.phases.MainMenuPhaseModel;
import models.phases.SplashScreenPhaseModel;

/**
 * @author Lukas
 *
 */
public class MainMenuPhaseViewGUI extends PhaseViewGUITemplate
{

	private MainMenuPhaseModel model;
	
	/**
	 * 
	 */
	public MainMenuPhaseViewGUI(MainMenuPhaseModel model) 
	{
		this.model=model;
		
		this.create();
	}

	@Override
	public void update(Observable o, Object arg) 
	{
		// TODO Auto-generated method stub
	}

	
	protected void create()
	{
		this.container=new Container();
		
		this.container.setLayout(new FlowLayout());
		
		MainMenuController controller=new MainMenuController(model);
		
		JButton btn= new JButton("Exit");
		btn.addActionListener(controller);
		btn.setActionCommand("exit");

		this.container.add(btn);
	}



}
