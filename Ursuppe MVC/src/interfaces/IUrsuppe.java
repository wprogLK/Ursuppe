package interfaces;

import java.util.Observer;

import annotations.OnlyForTesting;

import enums.EPhase;

public interface IUrsuppe extends Observer
{
	@OnlyForTesting
	public EPhase getCurrentEPhase();
	
	@OnlyForTesting
	public void turnOnTestMode();
	
	public void run();

}
