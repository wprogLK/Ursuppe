
package templates;


import java.io.PrintStream;

import helper.Setting;
import helper.UserInput;
import interfaces.IModule;

import module.ModuleASCII;




public class ASCIITestTemplate extends Thread
{
	
	
	protected final PrintStream outStream= Setting.testOut;
	protected final PrintStream errStream= Setting.testErr;
	
	protected final IModule module=new ModuleASCII(this.outStream,this.errStream);
	
	
	public ASCIITestTemplate()
	{
		super();
		UserInput.turnOnTestMode();
	}
	
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






		
		
