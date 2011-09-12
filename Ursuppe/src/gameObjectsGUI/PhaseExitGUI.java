package gameObjectsGUI;

import interfaces.IModule;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import annotations.OnlyForTesting;

import logics.PhaseExitLogic;

public class PhaseExitGUI extends PhaseExitLogic implements ActionListener{
	
	public PhaseExitGUI(IModule module)
	{
		super(module);
	}
	
	/////////////////
	//INPUT MANAGER//
	/////////////////
	
	//nothing
	
	///////////////
	//COMPONENTS//
	//////////////
	
		//PANELS:
		private JPanel mainPanel;
	
		//LABELS:
		private JLabel labelMessage=new JLabel("Do you really want to leave the game?");
		
		//TEXTFIELDS:
		
		//BUTTONS:
		private JButton buttonYes=new JButton("Yes");
		private JButton buttonNo=new JButton("No");
	
	/////////////
	//BASICS...//
	/////////////
		////////////
		//...LOGIC//
		////////////
		@Override
		/**
		 * Setup of the layout of the mainPanel and setup of the components
		 */
		public void doPreAction()
		{
			this.mainPanel.removeAll();
			this.mainPanel.setLayout(new GridLayout(2,2));
	
			this.addComponentsToActionListener();
			this.addComponentsToMainPanel();
		}
		
		
		///////
		//GUI//
		///////
		
		
		//////////////
		//COMPONENTS//
		//////////////
		
			/////////////////////
			//SET EDITABLE TRUE//
			/////////////////////
			
			
	
			
			//////////////////////
			//SET EDITABLE FALSE//
			//////////////////////
			
		
			
		
	///////////
	//ACTIONS//
	///////////
		private void addComponentsToActionListener() 
		{
			this.buttonYes.addActionListener(this);
			this.buttonNo.addActionListener(this);
		}
		
		private void addComponentsToMainPanel() 
		{
			//row 1
			this.mainPanel.add(this.labelMessage);
			this.mainPanel.add(new JLabel()); //Empty
			
			//row 2
			this.mainPanel.add(this.buttonNo);
			this.mainPanel.add(this.buttonYes);
		
		}

	////////////
	//ACTION A//
	////////////
	
	//nothing
		
	////////////
	//ACTION B//
	////////////
		
	//nothing
	
	//////////////
	//SET INPUTS//
	//////////////
	
	
	///////////
	//SETTERS//
	///////////
	
	@Override
	public void setMainPanel(JPanel mainPanel)
	{
		this.mainPanel=mainPanel;
	}

	@Override
	public void actionPerformed(ActionEvent event) 
	{
		if(event.getSource().equals(this.buttonNo))
		{
			this.buttonNo.setEnabled(false);
			
			this.setAllInput("no");
			
			this.buttonNo.setEnabled(true);
			
		}
		else if(event.getSource().equals(this.buttonYes))
		{
			this.buttonYes.setEnabled(false);
			
			this.setAllInput("yes");
			
			this.buttonYes.setEnabled(true);
		}
		
	}

	////////////////////
	//ONLY FOR TESTING//
	////////////////////
		
		////////////////
		//FAKING INPUT//
		////////////////
		
		
		//////////////////
		//FAKING BUTTONS//
		//////////////////
		
		@Override
		public void fakeClickNo()
		{
			this.buttonNo.doClick();
		}

		@Override
		public void fakeClickYes()
		{
			this.buttonYes.doClick();
		}
}
