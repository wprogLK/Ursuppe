
package tests;
/**
 * 
 */
import static org.junit.Assert.*;

import interfaces.IUrsuppe;


import enums.EMode;
import enums.EPhase;
import gameObjects.ASCII.UrsuppeASCII;
import helpers.UserInput;
//import interfaces.IGame;
//import interfaces.IPhase;
//import interfaces.IPlayer;

import org.junit.Test;
import ch.unibe.jexample.JExample;
import ch.unibe.jexample.Given;


import org.junit.runner.RunWith;

import tests.templates.ASCIITestTemplate;

@RunWith(JExample.class)
public class SimpleGameTestASCII extends ASCIITestTemplate
{	
	
	
	/**
	 * 
	 */
	@Test
	public UrsuppeASCII simpleGameTest() 
	{
		String[] instructions={"fd"};
		
		
		UrsuppeASCII ursuppe;
		
		ursuppe=new UrsuppeASCII(this.module,EPhase.phaseSplashScreen,EMode.testMode);
		
		ursuppe.turnOnTestMode();
		this.prepareForTesting("SimpleGameTestASCII.tes", instructions);
		ursuppe.run();
		
		return ursuppe;
		
	}
	
	@Given("simpleGameTest")
	public UrsuppeASCII phaseShouldBePhaseSplashScreen(UrsuppeASCII ursuppe)
	{
		
		assertCurrentEPhase(ursuppe, EPhase.phaseSplashScreen);
		
		
		ursuppe.getCurrentASCIIView().resume();
		
		System.out.println("DA");
		this.waitingGeneral();
		
		
		assertCurrentEPhase(ursuppe, EPhase.phaseMainMenu);
		
		return ursuppe;
	}
	

	
	
	
}






	
	