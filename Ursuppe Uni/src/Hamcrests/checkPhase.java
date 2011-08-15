package Hamcrests;
/**
 * 
 */
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import enums.GamePhases;
/**
 *  A hamcrestTest for integer
 * @author Lukas
 *
 */
public class checkPhase extends TypeSafeMatcher<GamePhases>
{
	private final GamePhases phase;
	
	public checkPhase(GamePhases phase)
	{
		this.phase=phase;
	}
	

	
	public void describeMismatchSafely(GamePhases phase, Description mismatchDescription)
	{
		mismatchDescription.appendValue(phase);
		mismatchDescription.appendText(" differend by ");
		mismatchDescription.appendValue(this.phase);
		
	}
	
	@Override
	public void describeTo(Description description) {
		description.appendText(" the phase ");
		description.appendValue(this.phase); 
	}

	
	
	@Factory
	public static Matcher<GamePhases> checkPhase(final GamePhases phase)
	{
		return new checkPhase(phase);
	}

	@Override
	protected boolean matchesSafely(GamePhases phase) {
		return (this.phase==phase);
	}




	
}
