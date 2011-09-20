/**
 * 
 */
package views.ASCII;


import templates.GameObjectViewASCIITemplate;
import models.AmoebaModel;

/**
 * @author Lukas
 *
 */
public class AmoebaViewASCII extends GameObjectViewASCIITemplate
{

	private AmoebaModel model;
	
	/**
	 * 
	 */
	public AmoebaViewASCII(AmoebaModel model) 
	{
		this.model=model;
	}

	public void start()
	{
		System.out.println("START: MAIN MENU");
	}

	

}
