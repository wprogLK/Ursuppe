package controllers;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.phases.MainMenuPhaseModel;
import models.phases.NewGamePhaseModel;

public class NewGameController implements ActionListener
{
	private NewGamePhaseModel model;
	
	public NewGameController(NewGamePhaseModel model)
	{
		this.model=model;
	}
	
	public void actionPerformed(ActionEvent evt)
	{
		
		
	}
}
