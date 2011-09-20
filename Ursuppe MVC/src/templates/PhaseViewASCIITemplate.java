/**
 * 
 */
package templates;

import interfaces.views.IViewASCII;

import java.io.PrintStream;
import java.util.Observable;

import annotations.OnlyForTesting;

import enums.EMode;
import helpers.Setting;
/**
 * @author Lukas
 *
 */
public abstract class PhaseViewASCIITemplate extends Thread implements IViewASCII
{
	protected PrintStream outStream=Setting.asciiOut;
	protected PrintStream errStream=Setting.asciiErr;
	
	private EMode mode=EMode.asciiMode;
	/**
	 * 
	 */
	public PhaseViewASCIITemplate() 
	{
		
	}
	
	
	@Override
	public void update(Observable arg0, Object arg1) 
	{
		System.out.println("UPDATE IN VIEW");
	}
	
	protected void runInTestMode()
	{
		switch(mode)
		{
			case testMode:
			{
				this.suspend();
			}
		}
	}
	
	@Override
	@OnlyForTesting
	public void turnOnTestMode()
	{
		System.out.println("TURN ON TEST MODE IN VIEW");
		 mode=EMode.testMode;
	}
	
	public void doWait()
	{
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


}
