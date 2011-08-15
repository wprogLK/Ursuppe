package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
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
public class LadderSquare extends JPanel implements ISquare{
	//---------------
	//LADDERSQUARE:
	//---------------
	private ArrayList<Player> aPlayer;
	private Boolean isStartLadderSquare;
	private Boolean isNormalLadderSquare;
	private Boolean isDarkLadderSquare;
	private Boolean isEmpty;
	private Player player;
	private int number;
	
	//---------------
	//PANEL:
	//---------------
	private String fileName;
	private BufferedImage image;
	private int posX;
	private int posY;
	/**
	 * 
	 */
	public LadderSquare(boolean isStart, int startNr, boolean isNormal, boolean isDark, int posX, int posY) {
		//---------------
		//LADDERSQUARE:
		//---------------
		this.player=null;
		this.aPlayer=new ArrayList<Player>();
		this.isStartLadderSquare=isStart;
		this.isNormalLadderSquare=isNormal;
		this.isDarkLadderSquare=isDark;
		this.posX=posX;
		this.posY=posY;
		this.isEmpty=true;
		this.number=startNr;
		
		if(isStart)
		{
			if (startNr==1)
			{
				this.fileName="ladderStartSquareOne.jpg";
			}
			else if (startNr==2)
			{
				this.fileName="ladderStartSquareTwo.jpg";
			}
			else if (startNr==3)
			{
				this.fileName="ladderStartSquareThree.jpg";
			}
		}
		else if (isNormal)
		{
			this.fileName="ladderNormalSquare.jpg";
		}
		else if (isDark)
		{
			this.fileName="ladderDarkSquare.jpg";
		}
		
		//---------------
		//PANEL:
		//---------------
		setPreferredSize(new Dimension(1500,30));
		
	}
	
	
	//GETTERS AND SETTERS
	//---------------
	//LADDERSQUARE:
	//---------------
	public Player getPlayer()
	{
		return this.player;
	}
	
	public void setPlayer(Player player)
	{
		assert this.isEmpty;
		this.player=player;
		this.isEmpty=false;
		
		this.player.setPosition(this.posX, this.posY);
	}
	
	public boolean isEmpy()
	{
		return this.isEmpty;
	}
	
	public boolean isStartLadderSquare()
	{
		return this.isStartLadderSquare;
	}
	
	public boolean isNormalLadderSquare()
	{
		return this.isNormalLadderSquare;
	}
	
	public boolean isDarkLadderSquare()
	{
		return this.isDarkLadderSquare;
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
		return true;
	}
	
	public boolean isCompassSquare()
	{
		return false;
	}
	
	public int getX()
	{
		return this.posX;
	}
	
	public int getY()
	{
		return this.posY;
	}
	
	public ArrayList<Player> getPlayers()
	{
		return this.aPlayer;
	}
	
	@Override
	public ArrayList<Ameba> getAmebasOfColor(GameColor color) {
		return null;
	}
	
	public int getNumber()
	{
		return this.number;
	}
	
	@Override
	public boolean containAmeba(Ameba ameba) {
		return false;
	}
	
	//---------------
	//PANEL:
	//---------------
	
	private void setImage()
	{
		InputStream input= ClassLoader.getSystemResourceAsStream(this.fileName);
		assert input!=null;
		try {
			this.image=ImageIO.read(input);
			input.close();
		} catch (IOException e) {
			// TODO: Better Exception
			e.printStackTrace();
		}
	}
	
	//HELPERMETHODS
	//---------------
	//LADDERSQUARE:
	//---------------
	
	public void addAmeba(Ameba ameba) 
	{
		// do nothing...
	}
	
	public void removeAmeba(Ameba ameba)
	{
		// do nothing...
	}
	
	public Ameba getAnAmeba()
	{
		return null;
	}
	
	
	public void removePlayer(Player player)
	{
		this.player=null;
		this.isEmpty=true;
	}
	
	//---------------
	//PANEL:
	//---------------
	
	
	//OTHERMETHODES
	//---------------
	//LADDERSQUARE:
	//---------------
	
	public String description() {
		String str="|";
		
		if (this.isStartLadderSquare())
		{
			str="Start ";
		}
		else if (this.isNormalLadderSquare())
		{
			str="Normal ";
		}
		else if (this.isDarkLadderSquare())
		{
			str="Dark ";
		}
		else
		{
			str="";
		}
		
		return str;
	}
	
	//---------------
	//PANEL:
	//---------------
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D)g;
		this.setImage();
	
		this.updatePlayers(g);
		g2d.drawImage(this.image, this.posX, this.posY, this);
	}
	
	public void draw(Graphics g)
	{
		Graphics2D g2d=(Graphics2D)g;
		this.setImage();
	
		
		this.updatePlayers(g);
		
		
		g2d.drawImage(this.image, this.posX, this.posY, this);
		
	}
	

	public void updatePlayers(Graphics g)			
	{
		
		if (this.player!=null)
		{
			this.player.setPosition(this.getX(), this.getY());
			this.player.draw(g);
		
		}
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

	


}
