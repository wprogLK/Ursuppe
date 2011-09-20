/**
 * 
 */
package views.ASCII;


import templates.GameObjectViewASCIITemplate;
import models.BoardModel;

/**
 * @author Lukas
 *
 */
public class BoardViewASCII extends GameObjectViewASCIITemplate
{

	private BoardModel model;
	
	/**
	 * 
	 */
	public BoardViewASCII(BoardModel model) 
	{
		this.model=model;
	}

	public void start()
	{
		System.out.println("START: MAIN MENU");
	}

}
