/**
 * 
 */
package views.GUI.phases;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.util.Observable;

import javax.swing.JButton;

import controllers.OptionsController;
import controllers.MainMenuController;
import controllers.SplashScreenController;

import templates.PhaseViewASCIITemplate;
import templates.PhaseViewGUITemplate;

import models.phases.OptionsPhaseModel;
import models.phases.MainMenuPhaseModel;
import models.phases.SplashScreenPhaseModel;

/**
 * @author Lukas
 *
 */
public class OptionsPhaseViewGUI extends PhaseViewGUITemplate
{

	private OptionsPhaseModel model;
	
	/**
	 * 
	 */
	public OptionsPhaseViewGUI(OptionsPhaseModel model) 
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
		
		OptionsController controller=new OptionsController(model);
		
		JButton btn= new JButton("Options");
		btn.addActionListener(controller);
		btn.setActionCommand("Options");

		this.container.add(btn);
	}



}
