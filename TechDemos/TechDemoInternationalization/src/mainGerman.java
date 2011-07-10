import java.util.*;


public class mainGerman {
	

	static ResourceBundle rb;
	static Locale locale;
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		setupLocal(args);
		
		
        System.out.println(rb.getString("welcome"));
        System.out.println(rb.getString("name"));
        System.out.println(rb.getString("bye"));
	}
	
	private static  void setupLocal(String[] args)
	{
		String language;
		String country;
		
		
		if(args.length !=2)
		{
			language=new String("de");
			country=new String("CH");
		}
		else
		{
			language = new String(args[0]);
			country = new String(args[1]);
		}
		
		locale = new Locale(language, country);
		rb = ResourceBundle.getBundle("MessagesBundle", locale);
	}	

}
