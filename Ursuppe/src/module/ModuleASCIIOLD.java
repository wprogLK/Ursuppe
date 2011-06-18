package module;

import gameObjectsASCII.*;
import interfaces.IGame;
import interfaces.IPhase;
import interfaces.IPlayer;

import com.google.inject.AbstractModule;

/**
 * used for Google Guice and used if {@code game} is running in ASCII mode.
 * 
 * @author Lukas Keller
 * @version 1.0.0
 *
 * @see IPhase
 * @see PhaseAASCII
 * @see IGame
 * @see GameASCII
 */
public class ModuleASCIIOLD extends AbstractModule
{
	/**
	 * binds all ASCII phases and the ASCII game.
	 * 
	 *<p>
	 *binds...
	 * 	<ul>
	 * 		<li> Phases: </li>
	 * 		<ul>
	 * 			<li> ... {@link PhaseAASCII} to {@link IPhase} </li>
	 * 		</ul>
	 *		<li> GameObjects: </li>
	 *		<ul>
	 * 			<li> ... {@link GameASCII} to {@link IGame} </li>
	 * 			<li> ... {@link PlayerASCII} to {@link IPlayer} </li>
	 * 		</ul>
	 * 	</ul>
	 *</p>
	 */
	protected void configure() 
	{
		bind(IPhase.class).to(PhaseAASCII.class);
		bind(IGame.class).to(GameASCII.class);
		bind(IPlayer.class).to(PlayerASCII.class);
	}

}
