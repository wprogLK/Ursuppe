/**
 * 
 */
package templates;

import interfaces.IModel;

import java.io.PrintStream;
import java.util.Observable;

import enums.EPhase;

/**
 * @author Lukas
 *
 */
public abstract class PhaseModelTemplate extends Observable implements IModel 
{
	protected EPhase currentEPhase;
	
	protected PrintStream outStream;
	protected PrintStream errStream;
	
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
	
	@Override
	public final EPhase getCurrentEPhase() 
	{
		return this.currentEPhase;
	}


}
