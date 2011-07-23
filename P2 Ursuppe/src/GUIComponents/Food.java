package GUIComponents;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import enums.GameColor;
import enums.GameDirection;

/**
 * 
 */

/**
 * @author Lukas
 *
 */
public class Food extends JPanel{
	//---------------
	//FOOD:
	//---------------
	private GameColor color;
	
	//---------------
	//PANEL:
	//---------------
	private BufferedImage image;
	private String fileName;
	
	private int posX;
	private int posY;
	
	public Food(GameColor color, String fileName, int posX, int posY) {
		//---------------
		//FOOD:
		//---------------
		this.color=color;
		//---------------
		//PANEL:
		//---------------
		this.posX=posX;
		this.posY=posY;
		this.fileName=fileName;
		this.setImage();
	
	}
	
	
	
	//GETTERS AND SETTERS

	public GameColor getColor()
	{
		return this.color;
	}
	
	public void setDirection(GameDirection direction)
	{
		//Do nothing
	}
	
	
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


	public void draw(Graphics g)
	{		
		Graphics2D g2d=(Graphics2D)g;
		
		this.setImage();
		
		g2d.drawImage(this.image,this.posX,this.posY, this);
	}
	

	

}
