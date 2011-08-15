package game;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
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
public class CompassSquare extends JPanel implements ISquare{
	//---------------
	//COMPASSSQUARE:
	//---------------	
	private GameDirection direction;
	
	private Board board;
	//---------------
	//PANEL:
	//---------------
	private BufferedImage image;
	private String fileName;

	private int posX;
	private int posY;
	private Point2D center;
	private AffineTransform aTrans;
	
	
	private JLabel labelOzoneValue;
	
	/**
	 * 
	 */
	public CompassSquare(int posX,int posY, Board board, JLabel labelOzoneValue, int ozoneValue) {
		//---------------
		//COMPASSSQUARE:
		//---------------
		this.board=board;
		
		//---------------
		//PANEL:
		//---------------
		this.fileName="compassSquare.jpg";
		
		this.posX=posX;
		this.posY=posY;
		
		this.setImage();
		
		this.setCompassCenter();
		
		this.aTrans=new AffineTransform();
		this.aTrans.translate(this.center.getX(), this.center.getY());
		
		this.labelOzoneValue=labelOzoneValue;
		this.updateOzoneLayer(ozoneValue);
	}
	
	
	public void updateOzoneLayer(int ozoneValue)
	{
		this.labelOzoneValue.setText(" " + ozoneValue);
		
		Calc calc=new Calc();
		
		calc.calcLabel(this.labelOzoneValue, this.posX+5, this.posY+this.image.getHeight()-20);
	}
	
	//GETTERS AND SETTERS
	//---------------
	//COMPASSSQUARE:
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
		return true;
	}
	
	public int getX()
	{
		return this.posX;
	}
	
	public int getY()
	{
		return this.posY;
	}
	
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
	
	public void setAmebasWindDirection() 
	{
		//do nothing
	}
	
	public void setWindDirection(GameDirection direction) 
	{
		this.direction=direction;
		
		this.fileName=direction.getFileName();
		
		this.setImage();
	}
	
	public void setCompassCenter()
	{
		this.center=new Point2D.Double(this.image.getWidth()/2+this.posX-50,this.image.getHeight()/2+this.posX-50 );
	}
	
	public GameDirection getWindDirection()
	{
		return this.direction;
	}
	
	//HELPERMETHODS
	//---------------
	//COMPASSSQUARE:
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
	
	public ArrayList<Ameba> getAmebasOfColor(GameColor color) {
		return null;
	}
	
	//---------------
	//PANEL:
	//---------------
	
	
	//OTHERMETHODES
	//---------------
	//COMPASSSQUARE:
	//---------------
	
	public String description()
	{	
		return "CompassSquare: " + this.direction;
	}
	
	public double calculateAngleFromDegreeToRadian(double angleDegree)
	{
		return (angleDegree*Math.PI)/180;
	}
	//---------------
	//PANEL:
	//---------------
	
	public void draw(Graphics g)
	{
		Graphics2D g2d=(Graphics2D)g;

		g2d.drawImage(this.image, this.posX, this.posY, this);
	}
	
	public void updatePlayers(Graphics g)
	{
		//do nothing
	}
	
	public void updateAmebasAndFood(Graphics g)
	{
		//do nothing
	}
	


	public void moveAbebas() {
		//do nothing
		
	}


	@Override
	public void amebaEatAndShit(Ameba ameba) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setVisibleOfFoodLabel(boolean b) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public int getNrOfFood(GameColor color) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public Player getPlayer() {
		// TODO Auto-generated method stub
		return null;
	}


	

	

}
