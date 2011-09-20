/**
 * 
 */
package views.ASCII;


import templates.GameObjectViewASCIITemplate;
import models.CompassSquareModel;

/**
 * @author Lukas
 *
 */
public class CompassSquareViewASCII extends GameObjectViewASCIITemplate
{

	private CompassSquareModel model;
	
	/**
	 * 
	 */
	public CompassSquareViewASCII(CompassSquareModel model) 
	{
		this.model=model;
	}

	public void start()
	{
		System.out.println("START: MAIN MENU");
	}

}
