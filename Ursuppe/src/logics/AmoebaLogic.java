/**
 * 
 */
package logics;

import enums.EColor;
import interfaces.IAmoeba;

/**
 * @author Lukas Keller
 * @version 1.0.0
 *
 */
public class AmoebaLogic implements IAmoeba
{
	protected EColor color;
	protected int number;
	protected int damagePoints;
	
	public AmoebaLogic()
	{
		this.color=EColor.Default;
		this.number=1;
		this.damagePoints=0;
	}
	
	public AmoebaLogic(EColor color, int number)
	{
		this.color=color;
		this.number=number;
		this.damagePoints=0;
	}
	
	//////////
	//BASICS//
	//////////
	
	@Override
	public final void resetDamagePoints()
	{
		this.damagePoints=0;
	}
	
	@Override
	public final void addOneDamagePoint()
	{
		this.damagePoints++;
	}
	
	///////////
	//SETTERS//
	///////////
	
	@Override
	public final void setColor(EColor color)
	{
		this.color=color;
	}
	
	///////////
	//GETTERS//
	///////////
	
	@Override
	public final EColor getColor() 
	{
		return this.color;
	}

}
