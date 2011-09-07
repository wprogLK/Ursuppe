package interfaces;

import java.util.ArrayList;
import java.util.Date;

import enums.EColor;
import enums.EPlayer;

public interface IPlayer {
	
	///////////
	//SETTERS//
	///////////
	
	public void setName(String name);
	public void setAge(int age);
	public void setColor(EColor color);
	public void setBirthday(Date birthday);
	public void setType(EPlayer eType);
	
	void setScore(int value);
	
	public void setAmoeba(ArrayList<IAmoeba> amoebas);
	///////////
	//GETTERS//
	///////////
	
	public String getName();
	public int getAge();

	public EColor getColor();
	
	public EPlayer getType();
	
	public int getScore();
	
	ArrayList<IAmoeba> getAmoebasOffBoard();
	ArrayList<IAmoeba> getAmoebasOnBoard();
	
	/**
	 * WARNING: Should only called by the boardLogic or game!!
	 * @param amoeba
	 */
	void putAmoebaOnTheBoard(IAmoeba amoeba);
	
	/**
	 *  WARNING: Should only called by the boardLogic or game!!
	 * @param amoeba
	 */
	void takeAmoebaOffTheBoard(IAmoeba amoeba);
	
	
}
