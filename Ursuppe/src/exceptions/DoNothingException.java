package exceptions;

public class DoNothingException extends Exception
{
	 String message;

	    public DoNothingException() {
	        this(null);
	    }

	    public DoNothingException(String message) {
	        super(message);
	        this.message = message;
	    }
}
