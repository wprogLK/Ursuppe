package interfaces;

import annotations.OnlyForTesting;


public interface IDie {

	public int roll();

	public int getValue();
	
	@OnlyForTesting
	public void turnOnTestMode();
	@OnlyForTesting
	public void turnOffTestMode();
	
	@OnlyForTesting
	public void setFakeValue(int value);

}
