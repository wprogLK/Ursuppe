/**
 * 
 */
package views.ASCII;


import templates.GameObjectViewASCIITemplate;
import models.PlayerModel;

/**
 * @author Lukas
 *
 */
public class PlayerViewASCII extends GameObjectViewASCIITemplate
{

	private PlayerModel model;
	
	/**
	 * 
	 */
	public PlayerViewASCII(PlayerModel model) 
	{
		this.model=model;
	}

	public void start()
	{
		System.out.println("START: MAIN MENU");
	}

}
