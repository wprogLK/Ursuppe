package Hamcrests;
/**
 * 
 */
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import enums.GameColor;
/**
 *  A hamcrestTest for integer
 * @author Lukas
 *
 */
public class checkString extends TypeSafeMatcher<String>
{
	private final String string;
	
	public checkString(String string)
	{
		this.string=string;
	}
	

	
	public void describeMismatchSafely(String string, Description mismatchDescription)
	{
		mismatchDescription.appendValue(string);
		mismatchDescription.appendText(" differend by ");
		mismatchDescription.appendValue(this.string);
		
	}
	
	@Override
	public void describeTo(Description description) {
		description.appendText(" the string ");
		description.appendValue(this.string); 
	}

	@Override
	protected boolean matchesSafely(String string) {		
		return (this.string.matches(string));
	}
	
	@Factory
	public static Matcher<String> checkString(final String string)
	{
		return new checkString(string);
	}




	
}
