//
//package tests;
///**
// * 
// */
//import static org.junit.Assert.*;
//
//import enums.EPhases;
//import helper.Setting;
//import interfaces.IGame;
//import interfaces.IPhase;
//import interfaces.IPlayer;
//
//import org.junit.Test;
//import ch.unibe.jexample.JExample;
//import ch.unibe.jexample.Given;
//
//
//import org.junit.runner.RunWith;
//
//import templates.ASCIITestTemplate;
//
//
//@RunWith(JExample.class)
//public class SimpleGameTestASCII extends ASCIITestTemplate//Thread{
//{	
//	private IPlayer playerOne;
//	private IPlayer playerTwo;
//	
//	/**
//	 * 
//	 */
//	@Test
//	public IGame simpleGameWithOnePlayer() 
//	{
//		this.setPriority(10);
//		
//		IGame game;
//		
//		game=module.createGame();
//		
//		game.setStartPhase(EPhases.phaseA);
//		
//		return game;
//	}
//	
//	@Test
//	public IGame simpleGameWithTwoPlayers() 
//	{
//		this.setPriority(10);
//		
//		IGame game;
//		
//		game=module.createGame();
//		
//		game.setStartPhase(EPhases.phaseA);
//		
//		return game;
//	}
//	
//
//	
//	@Given("simpleGameWithOnePlayer")
//	public IGame createANewGameWithOnePlayer(IGame game)
//	{
//		game.createNew();
//		this.playerOne=  game.createANewPlayer();
//		System.out.println("current EPhase= "+ game.getCurrentEPhase());
//		this.outStream.println("With one player");
//		game.showPlayers();
//		
//		return game;
//	}
//	
//	@Given("simpleGameWithTwoPlayers")
//	public IGame createANewGameWithTwoPlayer(IGame game)
//	{
//		game.createNew();
//		this.playerOne=  game.createANewPlayer();
//		this.playerTwo=  game.createANewPlayer();
//		
//		this.outStream.println("With two players");
//		game.showPlayers();
//		
//		return game;
//	}
//	
//	@Given("createANewGameWithOnePlayer")
//	public IGame playGameWithOnePlayer(IGame game)
//	{
//		game.play();
//		return game;
//	}
//	
//	@Given("createANewGameWithTwoPlayer")
//	public IGame playGameWithTwoPlayer(IGame game)
//	{
//		game.play();
//		return game;
//	}
//	
//	@Given("playGameWithTwoPlayer")
//	public IGame setNamePlayerOne(IGame game)
//	{
//		this.waitingGeneral(); 		//Important!
//		
//		IPhase phaseA=game.getCurrentPhase();
//		
//		phaseA.waitForANewInput();
//		assertTrue(phaseA.setInputA("Lukas"));
//		this.outStream.println("Lukas");				//Not necessary, but it looks like a real userInput
//		
//		return game;
//	}
//	
//	@Given("setNamePlayerOne")
//	public IGame namePlayerOneShouldBeLukas(IGame game)
//	{
//		assertTrue(this.playerOne.getName().equals("Lukas"));
//		return game;
//	}
//	
//	@Given("namePlayerOneShouldBeLukas")
//	public IGame namePlayerTwoShouldBeSubjectName(IGame game)
//	{
//		assertTrue(this.playerTwo.getName().equals("[SubjectName]"));
//
//		return game;
//	}
//	
//	
//	
//}
//
//
//
//
//
//
//	
//	