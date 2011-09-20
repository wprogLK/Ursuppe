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
import controllers.Phase4Controller;
import controllers.SplashScreenController;

import templates.PhaseViewASCIITemplate;
import templates.PhaseViewGUITemplate;

import models.phases.MainMenuPhaseModel;
import models.phases.Phase4Model;
import models.phases.SplashScreenPhaseModel;

/**
 * @author Lukas
 *
 */
public class Phase4ViewGUI extends PhaseViewGUITemplate
{

	private Phase4Model model;
	
	/**
	 * 
	 */
	public Phase4ViewGUI(Phase4Model model) 
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
		
		Phase4Controller controller=new Phase4Controller(model);
		
		JButton btn= new JButton("");
		btn.addActionListener(controller);
		btn.setActionCommand("");

		this.container.add(btn);
	}



}
