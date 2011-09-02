package logics;

import interfaces.IDie;

import java.util.Random;
/**
 * @author Lukas Keller
 * @version 1.0.0
 *
 */
public class DieLogic implements IDie
{
	private int value;
	private final static int MAXVALUE=6;
	
	private boolean testModeOn=false;
	
	@Override
	public int roll()
	{
		if(!testModeOn)
		{
			Random rnd=new Random();
		
			this.value=rnd.nextInt(MAXVALUE)+1;
		}
		
		return this.value;
	}
	
	@Override
	public int getValue()
	{
		return value;
	}

	@Override
	public void turnOnTestMode() {
		this.testModeOn=true;
		
	}

	@Override
	public void turnOffTestMode() {
		this.testModeOn=false;
		
	}

	@Override
	public void setFakeValue(int value) 
	{
		this.value=value;
		
	}
}
