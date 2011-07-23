package game;

import Components.Ursuppe;
import GUIComponents.UrsuppeGUI;
import helpClasses.ReadShowGUIOrNormal;

/**
 *	runs this to run the whole game;
 * @author Lukas
 *
 */
public class Main 
{
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{

		boolean createGUI=ReadShowGUIOrNormal.read();
		
		if (createGUI)
		{
			System.out.println("RUN IN GUI...");
			UrsuppeGUI ursuppeGui=new UrsuppeGUI(true,true);
			ursuppeGui.run();
		}
		else
		{
			System.out.println("RUN IN ASCII...");
			Ursuppe ursuppe=new Ursuppe();
		}
		
	}

}
