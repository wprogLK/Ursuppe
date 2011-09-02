package interfaces;

import annotations.OnlyForTesting;


public interface IDie {
	
	/**
	 * roll the die and get back a value
	 * @return a value between 1 and 6
	 */
	public int roll();
	
	/**
	 * @return the last rolled value between 1 and 6
	 */
	public int getValue();
	
	@OnlyForTesting
	public void turnOnTestMode();
	@OnlyForTesting
	public void turnOffTestMode();
	
	@OnlyForTesting
	public void setFakeValue(int value);

}
