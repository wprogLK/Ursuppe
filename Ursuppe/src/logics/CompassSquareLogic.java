package logics;

import templates.SquareTemplate;
import interfaces.ICompassSquare;
import interfaces.ISquare;

/**
 * @author Lukas
 *
 */
public class CompassSquareLogic extends SquareTemplate implements ICompassSquare
{
	private int x;
	private int y;

	public CompassSquareLogic() 
	{

	}

	@Override
	public void setPosition(int x, int y) {
		this.x=x;
		this.y=y;
		
	}

	@Override
	public int getPosX() {
		
		return this.x;
	}

	@Override
	public int getPosY() {
		return this.y;
	}

}
