package enums;
/**
 * All possibles directions which a player can roll and an amoeba can move.
 * 
 * <br><u> Overview: </u>
 * 
 * <table border="1">
 * 	<tr>
 * 		<th> Direction </th>
 * 		<th> Value </th>
 * 	</tr>
 * 	<tr>
 * 		<td> North </td>
 * 		<td> 2 </td>
 * 	</tr>
 * 
 * 	<tr>
 * 		<td> South </td>
 * 		<td> 4 </td>
 * 	</tr>
 * 
 * 	<tr>
 * 		<td> West </td>
 * 		<td> 1 </td>
 * 	</tr>
 * 
 * 	<tr>
 * 		<td> East </td>
 * 		<td> 3 </td>
 * 	</tr>
 * 
 * 	<tr>
 * 		<td> Middle </td>
 * 		<td> 5 </td>
 * 	</tr>
 * </table>
 * 
 * @author Lukas Keller
 * @version 1.0.0
 *
 */
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
	
	/**
	 * returns the direction of the incoming value
	 * @param value, should be between 1 and 5
	 * @return the direction
	 */
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



