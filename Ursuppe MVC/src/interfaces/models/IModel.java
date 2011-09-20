package interfaces.models;

import java.util.Observer;

public interface IModel 
{
	public void addObserver(Observer o);
	public void notifyObservers(Object arg);
	
	public void deleteObservers();
}
