package enums;

public enum EDirections 
{
	North(2),
	South(4),
	West(1),
	East(3),
	Middle(5);
	
	private int dieValue;
	
	EDirections(int dieValue)
	{
		this.dieValue=dieValue;
	}
	
	public static EDirections getDirection(int value)
	{
		switch(value)
		{
			case(1):
			{
				return West;
			}
			case(2):
			{
				return North;
			}
			case(3):
			{
				return East;
			}
			case(4):
			{
				return South;
			}
			case(5):
			{
				return Middle;
			}
			default:
			{
				//TODO EXCEPTION
				return null;
			}
		}

	}
}



