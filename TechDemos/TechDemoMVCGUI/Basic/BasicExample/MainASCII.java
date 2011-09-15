import java.awt.event.ActionEvent;
import java.util.Observer;


public class MainASCII {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Model model = new Model();
	
		ASCIIView view1 = new ASCIIView(1, model);
		ASCIIView view2 = new ASCIIView(2, model);
		
		model.addObserver(view1);
		model.addObserver(view2);
		
		view1.run();
		view2.run();
		//new ButtonView(model);
	
		
	}

}
