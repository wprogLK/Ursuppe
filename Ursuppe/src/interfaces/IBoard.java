package interfaces;

import annotations.OnlyForTesting;


public interface IBoard 
{
	/**
	 * add a ISoupSquare square on position x, y
	 * @param ISoupSquare square
	 * @param x
	 * @param y
	 */
	public void addSquare(ISoupSquare square, int x, int y);
	/**
	 * 
	 * @param x
	 * @param y
	 * @return the ISoupSquare on position x,y
	 */
	public ISoupSquare getSquare(int x, int y);
	/**
	 * @return ICompassSquare
	 */
	public ICompassSquare getCompassSquare();
	
	/**
	 * set the compassSquare on position x,y
	 * @param compass
	 * @param x
	 * @param y
	 */
	public void setCompassSquare(ICompassSquare compass, int x, int y);
	
	/**
	 * add a player to the board and the players amoebas too if needed
	 * @param player
	 */
	public void addPlayer(IPlayer player);
	
	@OnlyForTesting
	public void testAddAmeba();
}
