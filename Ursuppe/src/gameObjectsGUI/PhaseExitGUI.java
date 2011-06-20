package gameObjectsGUI;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import logics.PhaseALogic;

public class PhaseExitGUI extends PhaseALogic implements ActionListener{
	/////////////////
	//INPUT MANAGER//
	/////////////////
	private boolean validInputName=false;	//ACTION A
	private boolean validInputAge=false;	//ACTION B
	
	///////////////
	//COMPONENTS//
	//////////////
	
		//PANELS:
		private JPanel mainPanel;
	
		//LABELS:
		private JLabel labelMessage=new JLabel("Do you really want leave the game?");
		
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
		public void doPreAction()
		{
			this.mainPanel.removeAll();
			this.mainPanel.setLayout(new GridLayout(2,2));
	
			this.addComponentsToActionListener();
			this.addComponentsToMainPanel();
		}
		
		public void doAfterAction()
		{
			System.out.println("This was the techDemo run in GUI. Goodbye...");
			//System.exit(0);
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
			
			this.waitForAValidInput();	//IMPORTANT!!
			
			this.buttonNo.setEnabled(true);
			
		}
		else if(event.getSource().equals(this.buttonYes))
		{
			this.buttonYes.setEnabled(false);
			
			this.setAllInput("yes");
			
			this.waitForAValidInput();	//IMPORTANT!!
			
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


}
