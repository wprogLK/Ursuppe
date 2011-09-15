import java.awt.event.ActionEvent;
import java.util.Observer;


public class MainGUI {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Model model = new Model();
	
		Observer view1 = new RealView(1, model);
		Observer view2 = new RealView(2, model);
		
		model.addObserver(view1);
		model.addObserver(view2);
		
		//new ButtonView(model);
	
		
	}

}
