/**
 * 
 */
package views.GUI.phases;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.util.Observable;

import javax.swing.JButton;

import controllers.AboutController;
import controllers.MainMenuController;
import controllers.SplashScreenController;

import templates.PhaseViewASCIITemplate;
import templates.PhaseViewGUITemplate;

import models.phases.AboutPhaseModel;
import models.phases.MainMenuPhaseModel;
import models.phases.SplashScreenPhaseModel;

/**
 * @author Lukas
 *
 */
public class AboutPhaseViewGUI extends PhaseViewGUITemplate
{

	private AboutPhaseModel model;
	
	/**
	 * 
	 */
	public AboutPhaseViewGUI(AboutPhaseModel model) 
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
		
		AboutController controller=new AboutController(model);
		
		JButton btn= new JButton("ABOUT");
		btn.addActionListener(controller);
		btn.setActionCommand("exit");

		this.container.add(btn);
	}



}
