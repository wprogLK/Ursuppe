
package tests;
/**
 * 
 */
import static org.junit.Assert.*;

import enums.EPhases;
import enums.EPlayer;
import enums.EPlayingOrder;
import helper.Setting;
import interfaces.IGame;
import interfaces.IPhase;
import interfaces.IPlayer;

import org.junit.Test;
import ch.unibe.jexample.JExample;
import ch.unibe.jexample.Given;


import org.junit.runner.RunWith;

import templates.ASCIITestTemplate;


@RunWith(JExample.class)
public class NextPlayerTestAscending extends ASCIITestTemplate//Thread{
{	
	private IPlayer playerOne;
	private IPlayer playerTwo;
	
	/**
	 * 
	 */
	@Test
	public IGame TestAscending() 
	{
		this.setPriority(10);
		
		IGame game;
		
		game=module.createGame();
		
		game.setStartPhase(EPhases.phaseA);
		
		EPhases.phaseA.setOrder(EPlayingOrder.Ascending);
		
		game.createNew();
		
		this.playerOne=  game.createANewPlayer();
		this.playerTwo=  game.createANewPlayer();
		
		this.playerOne.setName("ONE");
		this.playerTwo.setName("TWO");
		
		game.showPlayers();
		
		game.turnOnTestMode();
		
		game.play();
		
		this.waitingGeneral(); 
		
		return game;
	}
	
@Given("TestAscending")
public IGame ascendingCurrentPlayerShouldBeTail(IGame game)
{
	IPlayer currentPlayer=game.getCurrentPlayer();
	
	assertTrue(currentPlayer.getType()==EPlayer.TailPlayer);
	
	return game;
}

@Given("ascendingCurrentPlayerShouldBeTail")
public IGame ascendingCurrentPlayerShouldBeTwo(IGame game)
{
	game.nextPlayer();
	game.showPlayers();
	
	IPlayer currentPlayer=game.getCurrentPlayer();
	assertTrue(currentPlayer.equals(this.playerTwo));
	
	return game;
}
	
@Given("ascendingCurrentPlayerShouldBeTwo")
public IGame ascendingCurrentPlayerShouldBeOne(IGame game)
{
	game.nextPlayer();
	game.showPlayers();
	
	IPlayer currentPlayer=game.getCurrentPlayer();
	assertTrue(currentPlayer.equals(this.playerOne));
	
	return game;
}
	
@Given("ascendingCurrentPlayerShouldBeOne")
public IGame ascendingCurrentPlayerShouldBeHead(IGame game)
{
	game.nextPlayer();
	game.showPlayers();
	
	IPlayer currentPlayer=game.getCurrentPlayer();
	System.out.println("CU " + currentPlayer.getName());
	assertTrue(currentPlayer.getType()==EPlayer.HeadPlayer);
	
	return game;
}
}







	
	