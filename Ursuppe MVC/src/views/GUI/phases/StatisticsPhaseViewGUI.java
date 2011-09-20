/**
 * 
 */
package views.GUI.phases;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.util.Observable;

import javax.swing.JButton;

import controllers.StatisticsController;
import controllers.MainMenuController;
import controllers.SplashScreenController;

import templates.PhaseViewASCIITemplate;
import templates.PhaseViewGUITemplate;

import models.phases.StatisticsPhaseModel;
import models.phases.MainMenuPhaseModel;
import models.phases.SplashScreenPhaseModel;

/**
 * @author Lukas
 *
 */
public class StatisticsPhaseViewGUI extends PhaseViewGUITemplate
{

	private StatisticsPhaseModel model;
	
	/**
	 * 
	 */
	public StatisticsPhaseViewGUI(StatisticsPhaseModel model) 
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
		
		StatisticsController controller=new StatisticsController(model);
		
		JButton btn= new JButton("Statistics");
		btn.addActionListener(controller);
		btn.setActionCommand("Statistics");

		this.container.add(btn);
	}



}
