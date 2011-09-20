/**
 * 
 */
package views.GUI.phases;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.util.Observable;

import javax.swing.JButton;

import controllers.NewGameController;
import controllers.MainMenuController;
import controllers.SplashScreenController;

import templates.PhaseViewASCIITemplate;
import templates.PhaseViewGUITemplate;

import models.phases.NewGamePhaseModel;
import models.phases.MainMenuPhaseModel;
import models.phases.SplashScreenPhaseModel;

/**
 * @author Lukas
 *
 */
public class NewGamePhaseViewGUI extends PhaseViewGUITemplate
{

	private NewGamePhaseModel model;
	
	/**
	 * 
	 */
	public NewGamePhaseViewGUI(NewGamePhaseModel model) 
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
		
		NewGameController controller=new NewGameController(model);
		
		JButton btn= new JButton("NewGame");
		btn.addActionListener(controller);
		btn.setActionCommand("NewGame");

		this.container.add(btn);
	}



}
