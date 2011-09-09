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
	 * add a player to the board on a "ladderSquares"/ the scoreLadder
	 * @param player
	 */
	public void addPlayer(IPlayer player);
	
	public void addAmoebaToSquare(IAmoeba amoeba, int x, int y);
	public void removeAmoebaFromCurrentSquare(IAmoeba amoeba);
	public void setAmoebaOnBoard(IAmoeba amoeba);
	public void takeAmobeaOffBoard(IAmoeba amoeba);
	
	////////////////////
	//ONLY FOR TESTING//
	////////////////////
	
	@OnlyForTesting
	public boolean testExistSoupSquare(int x, int y);
	@OnlyForTesting
	public void testAddAmeba();
	
}
