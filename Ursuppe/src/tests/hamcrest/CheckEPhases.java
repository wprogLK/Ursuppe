package tests.hamcrest;
/**
 * 
 */
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import enums.EPhases;

/**
 * Hamcrest for EPhases
 * @author Lukas Keller
 * @version 1.0.0
 *
 */
public class CheckEPhases extends TypeSafeMatcher<EPhases>
{
	private final EPhases ePhase;
	
	public CheckEPhases(EPhases ePhase)
	{
		this.ePhase=ePhase;
	}
	

	
	public void describeMismatchSafely(EPhases ePhase, Description mismatchDescription)
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
	public static Matcher<EPhases> checkEPhases(final EPhases ePhase)
	{
		return new CheckEPhases(ePhase);
	}

	@Override
	protected boolean matchesSafely(EPhases ePhase) {
		return (this.ePhase==ePhase);
	}




	
}
