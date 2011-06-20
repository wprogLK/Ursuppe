package gameObjectsASCII;

import main.GameLogic;
import module.ModuleASCII;
import templates.GameTemplate;
/**
 * {
 * 
 * @author Lukas Keller
 * @version 1.0.0
 *
 */
public class GameASCII extends GameTemplate{

	public GameASCII()
	{
		this.module=new ModuleASCII();
		
		this.setupPlayers();
	}
	
	@Override
	public void createNew() 
	{
		this.gameLogic=new GameLogic(this);
		this.gameLogic.createASCII();
		this.gameLogic.setGameToAllPhases();
	}

}
