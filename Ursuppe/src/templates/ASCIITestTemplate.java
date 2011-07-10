
package templates;


import interfaces.IModule;

import module.ModuleASCII;
import module.ModuleGUI;




public class ASCIITestTemplate extends Thread
{
	protected final IModule module=new ModuleASCII();
	

	protected void waitingBetweenTwoInputs()
	{
		try 
		{
			Thread.sleep(1000);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
		
	protected void waitingGeneral()
	{
		try 
		{
			Thread.sleep(500);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
}






		
		
