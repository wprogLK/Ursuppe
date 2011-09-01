package interfaces;

import annotations.OnlyForTesting;


public interface IBoard 
{
	public void addSquare(ISoupSquare square, int x, int y);
	public ISoupSquare getSquare(int x, int y);
	
	public ICompassSquare getCompassSquare();
	public void setCompassSquare(ICompassSquare compass, int x, int y);
	
	public void addPlayer(IPlayer player);
	
	@OnlyForTesting
	public void testAddAmeba();
}
