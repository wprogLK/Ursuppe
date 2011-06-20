package enums;

import interfaces.IPlayer;
import gameObjectsASCII.PlayerASCII;
import gameObjectsGUI.PlayerGUI;
/**
 * are all colors of the possible players.
 * 
 * <p>
 * max. numbers of players are 8.
 * <p>
 * the {@code default} color is <u>only</u> for the head and tail element of the players list
 * </p>
 * 
 * <p>
 * possible colors are:
 * <ul>
 * 	<li> Red </li>
 * 	<li> Blue </li>
 * 	<li> Yellow </li>
 * 	<li> Black </li>
 * 	<li> White </li>
 * 	<li> Pink </li>
 * 	<li> Green </li>
 * 	<li> Gray </li>
 * </ul>
 * </p>
 * @author Lukas
 *  @version 1.0.0
 */
public enum EColor 
{
	Default,
	Red,
	Blue,
	Yellow,
	Black,
	White,
	Pink,
	Green,
	Gray;

	private IPlayer player;
	
	/**
	 * sets the player to the color
	 * @param player
	 * @see PlayerTemplate
	 * @see PlayerASCII
	 * @see PlayerGUI
	 * @see IPlayer
	 */
	public void setPlayer(IPlayer player)
	{
		this.player=player;
	}
	
	/**
	 * gets the the player with the color, could be {@code null}!
	 * @return IPlayer
	 */
	public IPlayer getPlayer()
	{
		return this.player;
	}
}
