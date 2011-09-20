/**
 * 
 */
package views.GUI.phases;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.util.Observable;

import javax.swing.JButton;

import controllers.GameEndPhaseController;
import controllers.MainMenuController;
import controllers.SplashScreenController;

import templates.PhaseViewASCIITemplate;
import templates.PhaseViewGUITemplate;

import models.phases.GameEndPhaseModel;
import models.phases.MainMenuPhaseModel;
import models.phases.SplashScreenPhaseModel;

/**
 * @author Lukas
 *
 */
public class GameEndPhaseViewGUI extends PhaseViewGUITemplate
{

	private GameEndPhaseModel model;
	
	/**
	 * 
	 */
	public GameEndPhaseViewGUI(GameEndPhaseModel model) 
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
		
		GameEndPhaseController controller=new GameEndPhaseController(model);
		
		JButton btn= new JButton("GameEnd");
		btn.addActionListener(controller);
		btn.setActionCommand("GameEnd");

		this.container.add(btn);
	}



}
