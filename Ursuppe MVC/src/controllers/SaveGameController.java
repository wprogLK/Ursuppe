package controllers;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.phases.MainMenuPhaseModel;
import models.phases.SaveGamePhaseModel;

public class SaveGameController implements ActionListener
{
	private SaveGamePhaseModel model;
	
	public SaveGameController(SaveGamePhaseModel model)
	{
		this.model=model;
	}
	
	public void actionPerformed(ActionEvent evt)
	{
		
		
	}
}
