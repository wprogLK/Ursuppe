package interfaces;

import enums.EColor;

public interface IAmoeba 
{
	/**
	 * @return the color of the amoeba
	 */
	public EColor getColor();

	void setColor(EColor color);

	void resetDamagePoints();

	void addOneDamagePoint();
}
