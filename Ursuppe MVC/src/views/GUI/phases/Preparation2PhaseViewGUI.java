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
import controllers.Preparation2Controller;
import controllers.SplashScreenController;

import templates.PhaseViewASCIITemplate;
import templates.PhaseViewGUITemplate;

import models.phases.MainMenuPhaseModel;
import models.phases.Phase6Model;
import models.phases.Preparation2PhaseModel;
import models.phases.SplashScreenPhaseModel;

/**
 * @author Lukas
 *
 */
public class Preparation2PhaseViewGUI extends PhaseViewGUITemplate
{

	private Preparation2PhaseModel model;
	
	/**
	 * 
	 */
	public Preparation2PhaseViewGUI(Preparation2PhaseModel model) 
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
		
		Preparation2Controller controller=new Preparation2Controller(model);
		
		JButton btn= new JButton("");
		btn.addActionListener(controller);
		btn.setActionCommand("");

		this.container.add(btn);
	}



}
