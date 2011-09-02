/**
 * 
 */
package templates;


import helper.Setting;
import interfaces.ISquare;

/**
 * @author Lukas
 *
 */
public class SquareTemplate implements ISquare
{
	private static int maxLength;
	
	private static int factor=8;
	
	private static String topSign="-";
	
	private static String spaceholderSign=" ";
	
	/////////////////////////////
	//ASCII METHODS: TO STRINGS//
	/////////////////////////////
	
	public static String nullSquareLineOne()
	{
		setMaxLength();
		return fillWithSign("",maxLength-1,"/");
	}
	
	
	private static void setMaxLength() 
	{
		maxLength=Setting.usedColors.size()*factor;
		maxLength+=1;	//otherwise when 7 players are playing and on a square  are more than 9 foods of each color the board isn't correct
	}


	public static String createLine()
	{
		setMaxLength();
		return fillWithSign("",maxLength-1,topSign);
	}
	
	
	public static String normalSquare(String input)
	{
		setMaxLength();
		return fillWithSign(input,maxLength-1,spaceholderSign);
	}
	
	
	private static String fillWithSign(String input, int localMaxLength, String sign)
	{
		boolean addAtHead=true;
		
		while(input.length()<localMaxLength)
		{
			if(addAtHead)
			{
				input=sign+input;
				
				addAtHead=false;
			}
			else
			{
				input=input+sign;
				addAtHead=true;
			}
		}
		
		return input;
	}
	
	
}
