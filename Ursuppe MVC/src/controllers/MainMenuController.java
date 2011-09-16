package controllers;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.phases.MainMenuPhaseModel;

public class MainMenuController implements ActionListener
{
	private MainMenuPhaseModel model;
	
	public MainMenuController(MainMenuPhaseModel model)
	{
		this.model=model;
	}
	
	public void actionPerformed(ActionEvent evt)
	{
		if(evt.getActionCommand().equals("exit"))
		{
			model.doExit();
		}
		
	}
}
