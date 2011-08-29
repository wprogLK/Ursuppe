package interfaces;

public interface ISoupSquare
{
	public void setUpSquare(ISoupSquare square);
	public void setDownSquare(ISoupSquare square);
	
	public void setLeftSquare(ISoupSquare square);
	public void setRightSquare(ISoupSquare square);
	
	public ISoupSquare getUpSquare();
	public ISoupSquare getDownSquare();
	
	public ISoupSquare getLeftSquare();
	public ISoupSquare getRightSquare();
	
	
	public boolean hasUpSquare();
	public boolean hasDownSquare();
	
	public boolean hasLeftSquare();
	public boolean hasRightSquare();
	
}
