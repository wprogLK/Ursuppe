package Hamcrests;
/**
 * 
 */
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
/**
 *  A hamcrestTest for integer
 * @author Lukas
 *
 */
public class checkInteger extends TypeSafeMatcher<Integer>
{
	private final int number;
	
	public checkInteger(int number)
	{
		this.number=number;
	}
	

	
	public void describeMismatchSafely(int number, Description mismatchDescription)
	{
		mismatchDescription.appendValue(number);
		mismatchDescription.appendText(" differend by ");
		mismatchDescription.appendValue(this.number);
		
	}
	
	@Override
	public void describeTo(Description description) {
		description.appendText(" a numeric value of ");
		description.appendValue(this.number); 
	}

	@Override
	protected boolean matchesSafely(Integer number) {		
		return (this.number==number);
	}
	
	@Factory
	public static Matcher<Integer> checkInteger(final int number)
	{
		return new checkInteger(number);
	}




	
}
