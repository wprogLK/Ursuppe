package enums;


public enum GameDirection {
	/////////
	//ENUM://
	/////////
	North(0,-1,0,2,"compassSquareNorth.jpg",'U'), 
	East(1,0,90,3,"compassSquareEast.jpg",'R'), 
	South(0,1,180,4,"compassSquareSouth.jpg",'D'), 
	West(-1,0,270,1,"compassSquareWest.jpg",'W'), 
	Middle(0,0,0,5,"compassSquareMiddle.jpg",'M'), 
	Free(0,0,0,6,"compassSquare.jpg",'F');
	
	private int x, y, dieValue;
	private double angle;		//Angle in Degree;
	private String fileName;
	private char sign;
	
	GameDirection(int x, int y, int angle, int dieValue, String fileName, char sign)
	{
		this.x=x;
		this.y=y;
		this.angle=angle;
		this.dieValue=dieValue;
		this.fileName=fileName;
		this.sign=sign;
	}
	
	public char getSign()
	{
		return this.sign;
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


