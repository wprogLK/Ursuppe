/**
 * 
 */
package views.ASCII;


import templates.GameObjectViewASCIITemplate;
import models.SoupSquareModel;

/**
 * @author Lukas
 *
 */
public class SoupSquareViewASCII extends GameObjectViewASCIITemplate
{

	private SoupSquareModel model;
	
	/**
	 * 
	 */
	public SoupSquareViewASCII(SoupSquareModel model) 
	{
		this.model=model;
	}

	public void start()
	{
		System.out.println("START: MAIN MENU");
	}

}
