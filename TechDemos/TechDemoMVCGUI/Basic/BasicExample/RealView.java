import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class RealView extends JFrame implements Observer
{
	private int id;
	
	private Model model;
	
	private JTextField txtNumber;
	private JTextField txtName;
	private JLabel labelName;
	
	public RealView(int id, Model model)
	{
		this.id=id;
		this.model=model;
		
		setTitle("View " + id);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		getContentPane().setLayout(new FlowLayout());
		
		txtNumber=new JTextField(20);
		txtName=new JTextField(20);
		labelName=new JLabel("Your name is : " );
		
		getContentPane().add(txtNumber);
		getContentPane().add(txtName);
		getContentPane().add(labelName);
		
		Control control=new Control(model);
		
		JButton btn= new JButton("Increment");
		btn.addActionListener(control);
		btn.setActionCommand("inc");
		getContentPane().add(btn);
		
		JButton btnName= new JButton("Set name");
		btnName.addActionListener(control);
		getContentPane().add(btnName);
		
		//this.update(model, id);
		
		setLocation(250,60*id);
		pack();
		setVisible(true);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		txtNumber.setText("View " + id + ": " + model.getValue());
		System.out.println("UPDATE");
		
		
		if(this.txtName.isEditable())
		{
			try {
				this.txtName.setEditable(false);
				this.model.setName(txtName.getText());
				this.txtName.setBackground(Color.gray);
				
			} catch (Exception e) {
				this.txtName.setBackground(Color.red);
				this.txtName.setEditable(true);
			}
		}
		
	
	}

}
