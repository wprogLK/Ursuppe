
package tests;
/**
 * 
 */
import static org.junit.Assert.*;

import interfaces.IUrsuppe;


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
	private String fileNameWithPathSimpleGameTest;
	
	
	/**
	 * 
	 */
	@Test
	public IUrsuppe simpleGameTest() 
	{
		String[] instructions={"fd","df"};
		
		
		
//		this.setPriority(10);
		
		IUrsuppe ursuppe;
		
		ursuppe=new UrsuppeASCII(this.module,EPhase.phaseSplashScreen);
		
		ursuppe.turnOnTestMode();
		this.prepareForTesting("SimpleGameTestASCII.tes", instructions);
		ursuppe.run();
//		this.waitingGeneral(); 
//		
//		game.turnOnCurrentPhaseWaiting();
//	
		
		return ursuppe;
		
	}
	
	@Given("simpleGameTest")
	public IUrsuppe phaseShouldBePhaseSplashScreen(IUrsuppe ursuppe)
	{
		
		assertCurrentEPhase(ursuppe, EPhase.phaseSplashScreen);
		System.out.println("DA");
//		game.turnOffCurrentPhaseWaiting();
//		this.waitingGeneral();
		
//		game.turnOffCurrentPhaseWaiting();
//		this.waitingGeneral();
		
		assertCurrentEPhase(ursuppe, EPhase.phaseMainMenu);
		
//		game.turnOffCurrentPhaseWaiting();
//		this.waitingGeneral();
//		
//		game.turnOffCurrentPhaseWaiting();
//		this.waitingGeneral();
//		
//		assertCurrentEPhase(game, EPhases.phaseNewGame);
//		
		return ursuppe;
	}
	

	
	
	
}






	
	