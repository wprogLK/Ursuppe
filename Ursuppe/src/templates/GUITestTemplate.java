
package templates;

import gameObjectsGUI.GameGUI;
import interfaces.IGame;
import interfaces.IModule;

import module.ModuleGUI;

import org.junit.Test;
import ch.unibe.jexample.JExample;
import ch.unibe.jexample.Given;

import org.junit.runner.RunWith;

import com.google.inject.Guice;
import com.google.inject.Injector;


public class GUITestTemplate extends Thread
{
	protected final IModule module=new ModuleGUI();
	
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






		
		
