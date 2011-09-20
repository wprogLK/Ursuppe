/**
 * 
 */
package views.GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.util.Observable;

import javax.swing.JButton;

import controllers.AboutController;
import controllers.CheatsController;
import controllers.MainMenuController;
import controllers.SplashScreenController;

import templates.PhaseViewASCIITemplate;
import templates.PhaseViewGUITemplate;

import models.phases.AboutPhaseModel;
import models.phases.CheatsPhaseModel;
import models.phases.MainMenuPhaseModel;
import models.phases.SplashScreenPhaseModel;

/**
 * @author Lukas
 *
 */
public class CheatsPhaseViewGUI extends PhaseViewGUITemplate
{

	private CheatsPhaseModel model;
	
	/**
	 * 
	 */
	public CheatsPhaseViewGUI(CheatsPhaseModel model) 
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
		
		CheatsController controller=new CheatsController(model);
		
		JButton btn= new JButton("Exit");
		btn.addActionListener(controller);
		btn.setActionCommand("exit");

		this.container.add(btn);
	}



}
