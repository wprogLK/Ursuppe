package Hamcrests;
/**
 * 
 */

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
public class checkPlayer extends TypeSafeMatcher<Player>
{
	private final Player player;
	
	public checkPlayer(Player player)
	{
		this.player=player;
	}
	

	
	public void describeMismatchSafely(Player player, Description mismatchDescription)
	{
		mismatchDescription.appendValue(player);
		mismatchDescription.appendText(" differend by ");
		mismatchDescription.appendValue(this.player);
		
	}
	
	@Override
	public void describeTo(Description description) {
		description.appendText(" the player ");
		description.appendValue(this.player); 
	}

	@Override
	protected boolean matchesSafely(Player player) {		
		return (this.player==player);
	}
	
	@Factory
	public static Matcher<Player> checkPlayer(final Player player)
	{
		return new checkPlayer(player);
	}







	
}
