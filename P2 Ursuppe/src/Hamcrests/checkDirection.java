package Hamcrests;
/**
 * 
 */
import enums.GameDirection;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import Components.Player;
/**
 *  A hamcrestTest for integer
 * @author Lukas
 *
 */
public class checkDirection extends TypeSafeMatcher<GameDirection>
{
	private final GameDirection direction;
	
	public checkDirection(GameDirection direction)
	{
		this.direction=direction;
	}
	

	
	public void describeMismatchSafely(GameDirection direction, Description mismatchDescription)
	{
		mismatchDescription.appendValue(direction);
		mismatchDescription.appendText(" differend by ");
		mismatchDescription.appendValue(this.direction);
		
	}
	
	@Override
	public void describeTo(Description description) {
		description.appendText(" the direction ");
		description.appendValue(this.direction); 
	}

	@Override
	protected boolean matchesSafely(GameDirection direction) {		
		return (this.direction==direction);
	}
	
	@Factory
	public static Matcher<GameDirection> checkDirection(final GameDirection direction)
	{
		return new checkDirection(direction);
	}







	
}
