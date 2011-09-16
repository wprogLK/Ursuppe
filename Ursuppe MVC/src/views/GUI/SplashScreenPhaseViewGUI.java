/**
 * 
 */
package views.GUI;

import java.awt.Container;
import java.awt.FlowLayout;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.SplashScreenController;

import templates.PhaseViewASCIITemplate;
import templates.PhaseViewGUITemplate;

import models.phases.SplashScreenPhaseModel;

/**
 * @author Lukas
 *
 */
public class SplashScreenPhaseViewGUI extends PhaseViewGUITemplate
{

	private SplashScreenPhaseModel model;
	
	/**
	 * 
	 */
	public SplashScreenPhaseViewGUI(SplashScreenPhaseModel model) 
	{
		System.out.println("MODEL splash screen gui " + model);
		this.model=model;
		
		this.create();
	}

	@Override
	public void update(Observable o, Object arg) 
	{
		// TODO Auto-generated method stub
	}

	private void create()
	{
		this.container=new Container();
		System.out.println("CREATE GUI...");
		
		this.container.setLayout(new FlowLayout());
		
		SplashScreenController controller=new SplashScreenController(model);
		
		JButton btn= new JButton("Start");
		btn.addActionListener(controller);
		btn.setActionCommand("goToMainMenu");

		this.container.add(btn);
	}
}
