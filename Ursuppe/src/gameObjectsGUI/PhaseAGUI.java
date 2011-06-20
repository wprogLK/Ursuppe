package gameObjectsGUI;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import logics.PhaseALogic;

public class PhaseAGUI extends PhaseALogic implements ActionListener{
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
		private JLabel labelWelcome=new JLabel("Welcome to the techDemo of the logic of Ursuppe");
		private JLabel labelName=new JLabel("Name");
		private JLabel labelAge=new JLabel("Age");
		
		//TEXTFIELDS:
		private JTextField textFieldName=new JTextField();
		private JTextField textFieldAge=new JTextField();
		
		//BUTTONS:
		private JButton buttonExit=new JButton("Exit");
		private JButton buttonOK=new JButton("OK");
	
	/////////////
	//BASICS...//
	/////////////
		////////////
		//...LOGIC//
		////////////
		public void doPreAction()
		{
			this.mainPanel.setLayout(new GridLayout(4,3));
			
			this.mainPanel.removeAll();
			
			this.addComponentsToActionListener();
			this.addComponentsToMainPanel();
			
			this.setupComponents();
		}
		
		public void doAfterAction()
		{
			System.out.println("This was the techDemo run in GUI. Goodbye...");
			//System.exit(0);
		}
		
		///////
		//GUI//
		///////
		
		/**
		 *  setEditable(false) and changes colors of the inactive components
		 */
		private void setupComponents()
		{
			if (!this.getDoRunActionA())		//NAME
			{
				this.nameSetFalseEditable();
			}
			
			if (!this.getDoRunActionB())		//AGE
			{
				this.ageSetFalseEditable();
			}
		}
		
		//////////////
		//COMPONENTS//
		//////////////
		
			/////////////////////
			//SET EDITABLE TRUE//
			/////////////////////
			private void nameSetTrueEditable()
			{
				this.textFieldName.setBackground(Color.WHITE);
				this.textFieldName.setEditable(true);
				
				this.labelName.setEnabled(true);
			}
			
			private void ageSetTrueEditable()
			{
				this.textFieldAge.setBackground(Color.WHITE);
				this.textFieldAge.setEditable(true);
				
				this.labelAge.setEnabled(true);
			}
			
			//////////////////////
			//SET EDITABLE FALSE//
			//////////////////////
			
			private void nameSetFalseEditable()
			{
				this.validInputName=false;
				
				this.textFieldName.setBackground(Color.GRAY);
				this.textFieldName.setEditable(false);
				
				this.labelName.setEnabled(false);
			}
			
			private void ageSetFalseEditable()
			{	
				this.validInputAge=true;
				
				this.textFieldAge.setBackground(Color.GRAY);
				this.textFieldAge.setEditable(false);
				
				this.labelAge.setEnabled(false);
			}
	///////////
	//ACTIONS//
	///////////
		private void addComponentsToActionListener() 
		{
			this.buttonExit.addActionListener(this);
			this.buttonOK.addActionListener(this);
		}
		
		private void addComponentsToMainPanel() 
		{
			//row 1
			this.mainPanel.add(this.labelWelcome);
			this.mainPanel.add(new JLabel()); //Empty
			
			//row 2
			this.mainPanel.add(this.labelName);
			this.mainPanel.add(this.textFieldName);
			//row 3
			this.mainPanel.add(this.labelAge);
			this.mainPanel.add(this.textFieldAge);
			
			//row 4
			this.mainPanel.add(this.buttonExit); 
			this.mainPanel.add(this.buttonOK);
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
	
	private void trySetInputA()
	{
		if (!this.validInputName)
		{
			this.validInputName=this.setInputA(this.textFieldName.getText());
			
			
			if(!validInputName)
			{
				this.textFieldName.setBackground(Color.RED);
				this.textFieldName.setEditable(true);
			}
			else
			{
				this.nameSetFalseEditable();
			}
		}
		else
		{
			//already set a valid input for name
		}
	}
	
	private void trySetInputB()
	{
		if (!this.validInputAge)
		{
			this.validInputAge=this.setInputB(this.textFieldAge.getText());
			
			if(!validInputAge)
			{
				this.textFieldAge.setBackground(Color.RED);
				this.textFieldAge.setEditable(true);
			}
			else
			{
				this.ageSetFalseEditable();
			}
		}
		else
		{
			//already set a valid input for age
		}
	}
	
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
		if(event.getSource().equals(this.buttonOK))
		{
			this.buttonOK.setEnabled(false);
			
			this.trySetInputA();
			
			this.waitForAValidInput();	//IMPORTANT!!
			
			this.trySetInputB();
			
			this.buttonOK.setEnabled(true);
			
		}
		else if(event.getSource().equals(this.buttonExit))
		{
			this.buttonExit.setEnabled(false);
			this.setAllInput("Exit");
			this.buttonExit.setEnabled(true);
		}
		
	}

	////////////////////
	//ONLY FOR TESTING//
	////////////////////
		
		////////////////
		//FAKING INPUT//
		////////////////
		@Override
		public void fakeInputA(String inputA) 
		{
			this.textFieldName.setText(inputA);
		}
		
		//////////////////
		//FAKING BUTTONS//
		//////////////////
		public void fakeClickOK()
		{
			this.buttonOK.doClick();
		}
	

}
