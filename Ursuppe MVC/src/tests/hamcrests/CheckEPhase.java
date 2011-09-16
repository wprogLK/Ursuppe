package tests.hamcrests;
/**
 * 
 */
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import enums.EPhase;

/**
 * Hamcrest for EPhases
 * @author Lukas Keller
 * @version 1.0.0
 *
 */
public class CheckEPhase extends TypeSafeMatcher<EPhase>
{
	private final EPhase ePhase;
	
	public CheckEPhase(EPhase ePhase)
	{
		this.ePhase=ePhase;
	}
	

	
	public void describeMismatchSafely(EPhase ePhase, Description mismatchDescription)
	{
		mismatchDescription.appendValue(ePhase);
		mismatchDescription.appendText(" differend by ");
		mismatchDescription.appendValue(this.ePhase);
		
	}
	
	@Override
	public void describeTo(Description description) {
		description.appendText(" the ePhase ");
		description.appendValue(this.ePhase); 
	}

	
	
	@Factory
	public static Matcher<EPhase> checkEPhases(final EPhase ePhase)
	{
		return new CheckEPhase(ePhase);
	}

	@Override
	protected boolean matchesSafely(EPhase ePhase) {
		return (this.ePhase==ePhase);
	}




	
}
