package enums;

/**
 * Token for human or AI players
 * @author Lukas Keller
 *
 */
public enum EToken
{	
	
	/////////
	//ENUMS//
	/////////
	
	HU('H'),
	AI('A');
	
	private char token;
	
	
	EToken(char token)
	{
		this.token=token;
	}
	
	public char getToken()
	{
		return this.token;
	}
}
