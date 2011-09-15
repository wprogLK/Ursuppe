import java.util.Observable;
import java.util.Observer;



public class ASCIIView implements Observer
{
	private int id;
	
	private Model model;
	
	public ASCIIView(int id, Model model)
	{
		this.id=id;
		this.model=model;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("UPDATE");
	}
	
	public void run()
	{
		while(true)
		{
			String name=UserInput.realUserInput("Please enter your name (view #" + id+")");
			
			try 
			{
				this.model.setName(name);
				break;
			} 
			catch (Exception e) 
			{
				System.out.println(e.getMessage());
			}
		}
		System.out.println("After while");
		
	}

}
