/**
 * 
 */
package views.GUI.phases;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.util.Observable;

import javax.swing.JButton;

import controllers.LoadGameController;
import controllers.MainMenuController;
import controllers.SplashScreenController;

import templates.PhaseViewASCIITemplate;
import templates.PhaseViewGUITemplate;

import models.phases.LoadGamePhaseModel;
import models.phases.MainMenuPhaseModel;
import models.phases.SplashScreenPhaseModel;

/**
 * @author Lukas
 *
 */
public class LoadGamePhaseViewGUI extends PhaseViewGUITemplate
{

	private LoadGamePhaseModel model;
	
	/**
	 * 
	 */
	public LoadGamePhaseViewGUI(LoadGamePhaseModel model) 
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
		
		LoadGameController controller=new LoadGameController(model);
		
		JButton btn= new JButton("LoadGame");
		btn.addActionListener(controller);
		btn.setActionCommand("LoadGame");

		this.container.add(btn);
	}



}
