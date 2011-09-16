package controllers;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.phases.SplashScreenPhaseModel;

public class SplashScreenController implements ActionListener
{
	private SplashScreenPhaseModel model;
	
	public SplashScreenController(SplashScreenPhaseModel model)
	{
		this.model=model;
	}
	
	public void actionPerformed(ActionEvent evt)
	{
		if(evt.getActionCommand().equals("goToMainMenu"))
		{
			System.out.println("MODEL IN SPLASH SCREEN CONTROLLER " + model);
			model.goToMainMenu();
		}
	}
}
