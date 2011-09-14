/**
 * 
 */
package helper;

import java.util.*;

/**
 * it's for every class which needs language. 
 * 
 * @author Lukas Keller
 * @version 1.0.0
 */
public abstract class LanguagePack
{
	private static String language;
	private static String country;
	private static Locale locale;
	private static ResourceBundle rb;
	
	public static void setup()
	{
		LanguagePack.defaultLanguageSetup();
		LanguagePack.createBundle();
	}
	
	public static void setup(String[] args)
	{
		if(args.length !=2)
		{
			LanguagePack.defaultLanguageSetup();
		}
		else
		{
			LanguagePack.language = new String(args[0]);
			LanguagePack.country = new String(args[1]);
		}
		
		LanguagePack.createBundle();
	}
	
	/**
	 * default language
	 */
	private static void defaultLanguageSetup()
	{
		LanguagePack.language = new String("en");
		LanguagePack.country = new String("GB");	
	}
	
	private static void createBundle()
	{
		LanguagePack.locale=new Locale(LanguagePack.language,LanguagePack.country);
		LanguagePack.rb=ResourceBundle.getBundle("MessagesBundle", LanguagePack.locale);
	}
	
	public static String getTranslation(String expression)
	{
		return LanguagePack.rb.getString(expression);
	}
	
	public static String getTranslation(String expression, String[] args)
	{
		return String.format(LanguagePack.rb.getString(expression), args);
	}

	public static String getTranslation(String expression, String input) 
	{
		return String.format(LanguagePack.rb.getString(expression), input);
	}
	
	
}
