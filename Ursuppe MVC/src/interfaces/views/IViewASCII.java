package interfaces.views;

import java.util.Observer;

public interface IViewASCII extends Observer, IView
{
	public void start();
	
	//////////
	//THREAD//
	//////////
	public void suspend();
	public void resume();
	
	
	////////////////////
	//ONLY FOR TESTING//
	////////////////////
	
	public void turnOnTestMode();
}
