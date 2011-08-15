package game;
import java.awt.*;
import java.util.ArrayList;

import enums.GameColor;
import enums.GameDirection;

/**
 * 
 */

/**
 * @author Lukas
 *
 */
public interface ISquare{

	public boolean isInvalidSquare();
	public boolean isSoupSquare();
	public boolean isLadderSquare();
	public boolean isCompassSquare();
	
	public int getY();
	public int getX();
	
	public void addAmeba(Ameba ameba);
	public void removeAmeba(Ameba ameba);
	public boolean containAmeba(Ameba ameba);
	public void amebaEatAndShit(Ameba ameba);
	
	/**
	 * it's only for testing the move of an ameba in the Game-Class
	 * @return Ameba
	 */
	public Ameba getAnAmeba();
	
	public ArrayList<Ameba> getAmebasOfColor(GameColor color);
	
	public void removePlayer(Player player);
	public Player getPlayer();
	
	public void draw(Graphics g);
	public void updateAmebasAndFood(Graphics g);
	public void updatePlayers(Graphics g);
	
	public void repaint();
	public String description();
	public void setAmebasWindDirection();
	public void moveAbebas();
	
	public void setWindDirection(GameDirection direction);
	
	public boolean isEmpy();
	public void setVisibleOfFoodLabel(boolean b);

	public int getNrOfFood(GameColor color);

	

}
