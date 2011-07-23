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
public class checkColor extends TypeSafeMatcher<GameColor>
{
	private final GameColor color;
	
	public checkColor(GameColor color)
	{
		this.color=color;
	}
	

	
	public void describeMismatchSafely(GameColor color, Description mismatchDescription)
	{
		mismatchDescription.appendValue(color);
		mismatchDescription.appendText(" differend by ");
		mismatchDescription.appendValue(this.color);
		
	}
	
	@Override
	public void describeTo(Description description) {
		description.appendText(" the color ");
		description.appendValue(this.color); 
	}

	@Override
	protected boolean matchesSafely(GameColor color) {		
		return (this.color==color);
	}
	
	@Factory
	public static Matcher<GameColor> checkColor(final GameColor color)
	{
		return new checkColor(color);
	}




	
}
