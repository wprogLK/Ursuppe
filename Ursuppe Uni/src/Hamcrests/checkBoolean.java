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
public class checkBoolean extends TypeSafeMatcher<Boolean>
{
	private final Boolean value;
	
	public checkBoolean(Boolean value)
	{
		this.value=value;
	}
	

	
	public void describeMismatchSafely(Boolean value, Description mismatchDescription)
	{
		mismatchDescription.appendValue(value);
		mismatchDescription.appendText(" differend by ");
		mismatchDescription.appendValue(this.value);
		
	}
	
	@Override
	public void describeTo(Description description) {
		description.appendText(" the value ");
		description.appendValue(this.value); 
	}

	@Override
	protected boolean matchesSafely(Boolean value) {		
		return (this.value==value);
	}
	
	@Factory
	public static Matcher<Boolean> checkBoolean(final Boolean value)
	{
		return new checkBoolean(value);
	}




	
}
