package enums;


public enum GameDirection {
	/////////
	//ENUM://
	/////////
	North(0,-1,0,2,"compassSquareNorth.jpg"), 
	East(1,0,90,3,"compassSquareEast.jpg"), South(0,1,180,4,"compassSquareSouth.jpg"), 
	West(-1,0,270,1,"compassSquareWest.jpg"), Middle(0,0,0,5,"compassSquareMiddle.jpg"), 
	Free(0,0,0,6,"compassSquare.jpg");
	
	private int x, y, dieValue;
	private double angle;		//Angle in Degree;
	private String fileName;
	
	GameDirection(int x, int y, int angle, int dieValue, String fileName)
	{
		this.x=x;
		this.y=y;
		this.angle=angle;
		this.dieValue=dieValue;
		this.fileName=fileName;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}

	public double getAngle()
	{
		return this.angle;
	}

	public int getDieValue()
	{
		return this.dieValue;
	}
	
	public String getFileName()
	{
		return this.fileName;
	}

	public GameDirection getGameDirection(int dieValue)
	{
		switch (dieValue)
		{
		case 1:
			return West;
		case 2:
			return North;
		case 3:
			return East;
		case 4:
			return South;
		case 5:
			return Middle;
		case 6:
		{
			return Free;
		}
		default:
		{
			System.out.println("Fehler: Du hast eine ung�ltigen Wert gew�rfelt!"); //TODO: create an exception
			return Middle;
		}
			
		}
	}
}


