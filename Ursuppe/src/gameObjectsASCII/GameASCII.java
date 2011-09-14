package gameObjectsASCII;

import java.io.PrintStream;

import main.GameLogic;
import module.ModuleASCII;
import templates.GameTemplate;
/**
 * @author Lukas Keller
 * @version 1.0.0
 *
 */
public class GameASCII extends GameTemplate{

	/**
	 * setup for an ASCII game.
	 * 
	 * </br> sets the module for ASCII and calls the setupPlayers (in the GameTemplate class) for the head and tail player
	 * 
	 * @see GameTemplate
	 */
	public GameASCII(PrintStream out, PrintStream error)
	{
		super(out, error);
		
		this.module=new ModuleASCII(out,error);
		this.setupPlayers();
	}
	
	@Override
	public void createNew() 
	{
		this.gameLogic=new GameLogic(this);
		this.gameLogic.createASCII();
		this.gameLogic.setGameToAllPhases();
		
		this.die=this.module.createDie();
		this.board=this.module.createBoard(this);
	}
	
	
	/////////
	//BOARD//
	/////////
	@Override
	public void showBoard()
	{
		this.outStream.println(this.board.toString());
	}

}
