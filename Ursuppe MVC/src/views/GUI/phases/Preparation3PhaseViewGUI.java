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
import controllers.Phase6Controller;
import controllers.Preparation3Controller;
import controllers.SplashScreenController;

import templates.PhaseViewASCIITemplate;
import templates.PhaseViewGUITemplate;

import models.phases.MainMenuPhaseModel;
import models.phases.Phase6Model;
import models.phases.Preparation3PhaseModel;
import models.phases.SplashScreenPhaseModel;

/**
 * @author Lukas
 *
 */
public class Preparation3PhaseViewGUI extends PhaseViewGUITemplate
{

	private Preparation3PhaseModel model;
	
	/**
	 * 
	 */
	public Preparation3PhaseViewGUI(Preparation3PhaseModel model) 
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
		
		Preparation3Controller controller=new Preparation3Controller(model);
		
		JButton btn= new JButton("");
		btn.addActionListener(controller);
		btn.setActionCommand("");

		this.container.add(btn);
	}



}
