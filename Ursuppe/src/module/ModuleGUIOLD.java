package module;

import gameObjectsGUI.*;
import interfaces.IGame;
import interfaces.IPhase;
import interfaces.IPlayer;

import com.google.inject.AbstractModule;
/**
 * used for Google Guice and used if {@code game} is running in GUI mode.
 * 
 * @author Lukas Keller
 * @version 1.0.0
 *
 * @see IPhase
 * @see PhaseAGUI
 * @see IGame
 * @see GameGUI
 */
public class ModuleGUIOLD extends AbstractModule
{
	/**
	 * binds all GUI phases and the GUI game.
	 * 
	 *<p>
	 *binds...
	 * 	<ul>
	 * 		<li> Phases: </li>
	 * 		<ul>
	 * 			<li> ... {@link PhaseAGUI} to {@link IPhase} </li>
	 * 		</ul>
	 *		<li> GameObjects: </li>
	 *		<ul>
	 * 			<li> ... {@link GameGUI} to {@link IGame} </li>
	 * 			<li> ... {@link PlayerGUI} to {@link IPlayer} </li>
	 * 		</ul>
	 * 	</ul>
	 *</p>
	 */
	protected void configure() 
	{
		bind(IPhase.class).to(PhaseAGUI.class);
		bind(IGame.class).to(GameGUI.class);
		bind(IPlayer.class).to(PlayerGUI.class);
	}

}
