package enums;

/**
 * Token for human or AI players
 * @author Lukas Keller
 * @version 1.0.0
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
	
	/**
	 * get the char of the token
	 * @return first letter of the token
	 */
	public char getToken()
	{
		return this.token;
	}
}
