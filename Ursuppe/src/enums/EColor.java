package enums;

import java.util.ArrayList;

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
	Default("DE","de"),
	Red("RE","re"),
	Blue("BL","bl"),
	Yellow("YE","ye"),
	Black("BL","bl"),
	White("WH","wh"),
	Pink("PI","pi"),
	Green("GE","ge"),
	Gray("GA","gA");

	private IPlayer player;
	
	private String bigBegin;
	private String smallBegin;
	
	EColor(String bigBegin, String smallBegin)
	{
		this.bigBegin=bigBegin;
		this.smallBegin=smallBegin;
	}
	
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
	
	public String getBigBegin()
	{
		return this.bigBegin;
	}
	
	public String getSmallBegin()
	{
		return this.smallBegin;
	}
}
