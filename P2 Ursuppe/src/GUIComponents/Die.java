package GUIComponents;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * @author Lukas
 *
 */
/*DR why o why?
 * Separate GUI and application
 * You just blow up your code, it gets less readable and even you don't know how to fix bugs
 * or add new features...
 * 
 * LK I know, maybe I will do this later, but first I have to look that everything else is ok
 */
public class Die extends JPanel{
	//---------------
	//DIE:
	//---------------
	static final int FACES = 6;
	private int lastValue;
	private boolean isInTestMode;
	private int expectedValue;
	//---------------
	//PANEL:
	//---------------
	private String fileName;
	private BufferedImage image;
	
	private final int posX=0;
	private final int posY=0;	
	
	
	/**
	 * 
	 */
	public Die() 
	{
		//---------------
		//DIE:
		//---------------
		this.expectedValue=0;
		this.lastValue=1; //DefaultValue
		this.isInTestMode=false;
		
		//---------------
		//PANEL:
		//---------------
		this.setImage();
		this.repaint();
		setPreferredSize(new Dimension(this.image.getWidth(),this.image.getHeight()));
		
	}
	
	
	//GETTERS AND SETTERS
	//---------------
	//DIE:
	//---------------
	public int getHeightOfDie()
	{
		return this.image.getHeight();
	}
	
	public int getWidthOfDie()
	{
		return this.image.getWidth();
	}
	
	public void testModeOn()
	{
		this.isInTestMode=true;
	}
	
	public void testModeOff()
	{
		this.isInTestMode=false;
	}
	
	public void setExpectedValue(int value)
	{
		assert this.isInTestMode;
		assert value >= 1 && value <= FACES;
		
		this.expectedValue=value;
	}
	
	public boolean isInTestMode()
	{
		return this.isInTestMode;
	}
	
	
	
	//---------------
	//PANEL:
	//---------------
	
	private void setImage()
	{
		switch (this.lastValue)
		{
		case 1:
			this.fileName="dieOne.jpg";
			break;
		case 2:
			this.fileName="dieTwo.jpg";
			break;
		case 3:
			this.fileName="dieThree.jpg";
			break;
		case 4:
			this.fileName="dieFour.jpg";
			break;
		case 5:
			this.fileName="dieFive.jpg";
			break;
		default:
			this.fileName="dieSix.jpg";
			break;
		}
		
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
	//OTHERMETHODES
	//---------------
	//DIE:
	//---------------
	
	public int roll() 
	{
		if (!this.isInTestMode)
		{
			int result = 1 + (int) (FACES * Math.random());
			assert result >= 1 && result <= FACES;
			this.lastValue=result;
			
			this.repaint();	//Update the graphic of the die
			
			return result;
		}
		else
		{
			assert expectedValue >= 1 && expectedValue <= FACES;
			this.lastValue=expectedValue;
			
			this.repaint();	//Update the graphic of the die
			
			return expectedValue;
		}
	}
	
	//---------------
	//PANEL:
	//---------------

	public void draw(Graphics g)
	{
		
		Graphics2D g2d=(Graphics2D)g;
		this.setImage();
		g2d.drawImage(this.image, this.posX,  this.posY, this);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D)g;
		this.setImage();
		g2d.drawImage(this.image, this.posX,  this.posY, this);
	}
}
