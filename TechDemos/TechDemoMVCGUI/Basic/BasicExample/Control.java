import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Control implements ActionListener{
	private Model model;
	
	public Control(Model model)
	{
		this.model=model;
	}
	
	
	public void actionPerformed(ActionEvent evt)
	{
		System.out.println("EVT: " + evt.getActionCommand());
		
		if(evt.getActionCommand().equals("Set name"))
		{
			//
		}
		else if(evt.getActionCommand().equals("Increment"))
		{
			model.incValue();
		}
		
	
	}

}
