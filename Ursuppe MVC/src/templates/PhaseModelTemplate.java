/**
 * 
 */
package templates;

import interfaces.IModel;

import java.util.Observable;

import enums.EPhase;

/**
 * @author Lukas
 *
 */
public abstract class PhaseModelTemplate extends Observable implements IModel 
{
	protected EPhase currentEPhase;
	
	/**
	 * 
	 */
	public PhaseModelTemplate(EPhase ePhase)
	{
		this.currentEPhase=ePhase;
	}
	
	
	protected void changePhase(EPhase newEPhase)
	{
		this.currentEPhase=newEPhase;
		
		setChanged();
		notifyObservers();
	}


}
