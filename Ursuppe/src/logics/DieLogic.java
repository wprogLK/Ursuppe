package logics;

import interfaces.IDie;

import java.util.Random;

public class DieLogic implements IDie
{
	private int value;
	private final static int MAXVALUE=6;
	
	@Override
	public int roll()
	{
		Random rnd=new Random();
		
		this.value=rnd.nextInt(MAXVALUE)+1;
		
		return this.value;
	}
	
	@Override
	public int getValue()
	{
		return value;
	}
}
