/**
 * 
 */
package mains;

import modules.ModuleASCII;
import gameObjects.ASCII.UrsuppeASCII;

/**
 * @author Lukas
 *
 */
public class MainASCII 
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		UrsuppeASCII ursuppe=new UrsuppeASCII(new ModuleASCII());
		ursuppe.run();
	}

}
