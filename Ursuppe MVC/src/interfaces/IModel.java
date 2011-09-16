package interfaces;

import java.util.Observer;

import enums.EPhase;

public interface IModel 
{
	public EPhase getCurrentEPhase();
	
	public void addObserver(Observer o);
	public void notifyObservers(Object arg);
	
	public void deleteObservers();
}
