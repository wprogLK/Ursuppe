/**
 * 
 */
package views.GUI.phases;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.util.Observable;

import javax.swing.JButton;

import controllers.ExitController;
import controllers.MainMenuController;
import controllers.SplashScreenController;

import templates.PhaseViewASCIITemplate;
import templates.PhaseViewGUITemplate;

import models.phases.ExitPhaseModel;
import models.phases.MainMenuPhaseModel;
import models.phases.SplashScreenPhaseModel;

/**
 * @author Lukas
 *
 */
public class ExitPhaseViewGUI extends PhaseViewGUITemplate
{

	private ExitPhaseModel model;
	
	/**
	 * 
	 */
	public ExitPhaseViewGUI(ExitPhaseModel model) 
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
		
		ExitController controller=new ExitController(model);
		
		JButton btn= new JButton("Exit");
		btn.addActionListener(controller);
		btn.setActionCommand("exit");

		this.container.add(btn);
	}



}
