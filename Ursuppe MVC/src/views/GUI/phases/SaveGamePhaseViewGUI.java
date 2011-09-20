/**
 * 
 */
package views.GUI.phases;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.util.Observable;

import javax.swing.JButton;

import controllers.SaveGameController;
import controllers.MainMenuController;
import controllers.SplashScreenController;

import templates.PhaseViewASCIITemplate;
import templates.PhaseViewGUITemplate;

import models.phases.SaveGamePhaseModel;
import models.phases.MainMenuPhaseModel;
import models.phases.SplashScreenPhaseModel;

/**
 * @author Lukas
 *
 */
public class SaveGamePhaseViewGUI extends PhaseViewGUITemplate
{

	private SaveGamePhaseModel model;
	
	/**
	 * 
	 */
	public SaveGamePhaseViewGUI(SaveGamePhaseModel model) 
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
		
		SaveGameController controller=new SaveGameController(model);
		
		JButton btn= new JButton("SaveGame");
		btn.addActionListener(controller);
		btn.setActionCommand("SaveGame");

		this.container.add(btn);
	}



}
