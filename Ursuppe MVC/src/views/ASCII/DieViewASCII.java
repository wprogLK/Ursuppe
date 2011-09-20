/**
 * 
 */
package views.ASCII;


import templates.GameObjectViewASCIITemplate;
import models.DieModel;

/**
 * @author Lukas
 *
 */
public class DieViewASCII extends GameObjectViewASCIITemplate
{

	private DieModel model;
	
	/**
	 * 
	 */
	public DieViewASCII(DieModel model) 
	{
		this.model=model;
	}

	public void start()
	{
		System.out.println("START: MAIN MENU");
	}

}
