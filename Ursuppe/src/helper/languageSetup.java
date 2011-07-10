/**
 * 
 */
package helper;

import java.util.*;

/**
 * it's for every class which needs language. 
 * 
 * @author Lukas
 */
public class languageSetup extends Thread
{
	private String language;
	private String country;
	private Locale locale;
	protected ResourceBundle rb;
	
	/**
	 * Default constructor
	 */
	public languageSetup()
	{
		this.setup();
	}
	
	/**
	 * specific constructor
	 */
	public languageSetup(String[] args)
	{
		this.setup(args);
	}
	
	public void setup()
	{
		this.defaultLanguageSetup();
		this.createBundle();
	}
	
	/**
	 * specific constructor
	 * @param args
	 */
	public void setup(String[] args)
	{
		if(args.length !=2)
		{
			this.defaultLanguageSetup();
		}
		else
		{
			 this.language = new String(args[0]);
	         this.country = new String(args[1]);
		}
		
		this.createBundle();
	}
	
	/**
	 * default language
	 */
	private void defaultLanguageSetup()
	{
		language = new String("en");
		country = new String("GB");	
	}
	
	private void createBundle()
	{
		locale=new Locale(this.language,this.country);
		this.rb=ResourceBundle.getBundle("languageBundle", this.locale);
	}
	
	
}
