/**
 * 
 */
package views.GUI.phases;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.util.Observable;

import javax.swing.JButton;

import controllers.HelpController;
import controllers.MainMenuController;
import controllers.SplashScreenController;

import templates.PhaseViewASCIITemplate;
import templates.PhaseViewGUITemplate;

import models.phases.HelpPhaseModel;
import models.phases.MainMenuPhaseModel;
import models.phases.SplashScreenPhaseModel;

/**
 * @author Lukas
 *
 */
public class HelpPhaseViewGUI extends PhaseViewGUITemplate
{

	private HelpPhaseModel model;
	
	/**
	 * 
	 */
	public HelpPhaseViewGUI(HelpPhaseModel model) 
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
		
		HelpController controller=new HelpController(model);
		
		JButton btn= new JButton("Help");
		btn.addActionListener(controller);
		btn.setActionCommand("Help");

		this.container.add(btn);
	}



}
