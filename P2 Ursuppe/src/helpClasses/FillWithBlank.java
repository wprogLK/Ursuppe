package helpClasses;

public abstract class FillWithBlank {

	public static String   fillWithBlank(String str, int maxLength )
	{
		//System.out.println("LENGTH " + str.length());
		int lengthStr=str.length();
		
		//maxLength=13;
		
		for (int i=lengthStr; i<maxLength;i++) //IMPORTANT 26= length of output of SoupSquare
		{
			str= str + " ";
			//System.out.println("STR : " + str);
		}
		
		return str;
	}
	
}
