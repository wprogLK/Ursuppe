import java.util.Locale;
import java.util.ResourceBundle;


public class mainEnglish {
	

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
			language=new String("en");
			country=new String("GB");
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
