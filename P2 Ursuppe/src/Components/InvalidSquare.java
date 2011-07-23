package Components;
import helpClasses.FillWithBlank;

import java.awt.Graphics;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import enums.GameColor;
import enums.GameDirection;

/**
 * 
 */

/**
 * @author Lukas
 *
 */
public class InvalidSquare extends JPanel implements ISquare{
	//---------------
	//INVALIDSQUARE:
	//---------------
	
	
	//---------------
	//PANEL:
	//---------------
	
	public InvalidSquare() {
		
	}
	
	
	//GETTERS AND SETTERS
	//---------------
	//INVALIDSQUARE:
	//---------------
	public boolean isEmpy()
	{
		return false;
	}
	
	public boolean isInvalidSquare()
	{
		return true;
	}
	
	public boolean isSoupSquare()
	{
		return false;
	}
	
	public boolean isLadderSquare()
	{
		return false;
	}
	
	public boolean isCompassSquare()
	{
		return false;
	}
	
	public int getX()
	{
		return -1;
	}
	
	public int getY()
	{
		return -1;
	}
	
	@Override
	public ArrayList<Ameba> getAmebasOfColor(GameColor color) {
		return new ArrayList<Ameba>();
	}
	
	@Override
	public boolean containAmeba(Ameba ameba) {
		return false;
	}
	
	
	//HELPERMETHODS
	//---------------
	//INVALIDSQUARE:
	//---------------
	
	public void addAmeba(Ameba ameba)
	{
		//do nothing
	}
	
	public void removeAmeba(Ameba ameba)
	{
		//do nothing
	}
	
	public Ameba getAnAmeba()
	{
		return null;
	}
	
	public void addPlayer(Player player)
	{
		//do nothing
	}
	
	public void removePlayer(Player player)
	{
		//do nothing
	}
	
	//---------------
	//PANEL:
	//---------------
	
	
	//OTHERMETHODES
	//---------------
	//INVALIDSQUARE:
	//---------------
	
	public String description()
	{
		return "    InvalidSquare    ";
	}
	
	//---------------
	//PANEL:
	//---------------

	public void draw(Graphics g)
	{
		//do nothing
	}
	
	public void updatePlayers(Graphics g)
	{
		//do nothing
	}
	
	public void updateAmebasAndFood(Graphics g)
	{
		//do nothing
	}



	public void setAmebasWindDirection() {
		//do nothing
		
	}


	public void moveAbebas() {
		//do nothing
		
	}
	
	public void setWindDirection(GameDirection direction)
	{
		//do nothing
	}


	@Override
	public void amebaEatAndShit(Ameba ameba) {
	
		
	}


	@Override
	public void setVisibleOfFoodLabel(boolean b) {
		
		
	}


	@Override
	public int getNrOfFood(GameColor color) {
		
		return 0;
	}


	@Override
	public Player getPlayer() {
		
		return null;
	}
	
	public String toString()
	{
		return "I ";
	}
	
	public String getUpperStringPart()
	{
		String str="";
		//str="[" +str + "]";
		str=FillWithBlank.fillWithBlank(str, 13);
		
		return  str;	
	}
	
	public String getDownerStringPart()
	{
		String str="";
	//	str="[" +str + "]";
		str=FillWithBlank.fillWithBlank(str, 13);
		
		return  str;	
	}
	
	public String getMiddleStringPart()
	{
		String str="";
		//str="[" +str + "]";
		str=FillWithBlank.fillWithBlank(str, 13);
		
		return  str;	
	}



	

}
