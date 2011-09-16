package helpers;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Needed for silent run.
 * 
 * <br> <br> <u>Usage:</u>
 * <br>Use with {@code new PrintStream(new NullOutputStream()}
 * 
 * @author Lukas Keller
 * @version 1.0.0
 */
public class NullOutputStream extends OutputStream 
{
	/**
	 * Null implementation of inherited abstract method
	 */
	public void write(int b) throws IOException 
	{ 
		//do nothing
	}
}
