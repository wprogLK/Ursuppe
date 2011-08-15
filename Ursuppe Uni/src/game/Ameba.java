package game;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
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
/*DR you do have a lot code commented out down here. Why?
 * still need this code? if not delete it 
 * 
 * LK: The commented out code is an old version of the code. Of course we will delete this code in our really final version of the game.
 */
public class Ameba extends JPanel implements ActionListener{
	//---------------
	//AMEBA:
	//---------------
	
	private GameColor color;
	private int damagePoints;

	private int nrOfShitNewFoods=2;
	private int nr;
	private int maxDamagePoints;
	
	private boolean isDead;
	
	//FOOD:
	private int nrOfEatBlueFood;
	private int nrOfEatRedFood;
	private int nrOfEatYellowFood;
	
	//---------------
	//PANEL:
	//---------------
	
	private int m_velocityX;
	private int m_velocityY;
	private int posX;
	private int posY;
	
	
	private int squareX;
	private int squareY;
	
	private GameDirection direction;
	
	private final int speedFactor=5;
	
	/*DR so your genes move with the help of a AffineTransformation?
	 * I think you should separate the moving on the board from the moving in the game?
	 * Clearly separate GUI and application! 
	 * 
	 * LK  yes our amebas move with the help of a AffineTransformation. We need this for implement an animation of moving.
	 * In the version without gui you can't see the the gui. Our gui is fixed implemented in the game, its really hard to separate it. We will see...
	 */
	private AffineTransform aTrans;
	
	private int stepsToMove; //TODO: depends on the height of the ladderSquare
	
	private final int STEPSTOMOVE=100/5;
	private boolean doMove;
	
	private String fileName;
	private BufferedImage image;
	//DR GUI stuff...
	//---------------
	//ANIMATION: 
	//---------------
	private int animation_interval=35; //Milliseconds between updates
	private Timer animation_timer;
	/**
	 * 
	 */
	/*DR mhm I think your ameba knows to much, why should such a tiny little thing
	 * know his position and the direction and his color
	 * they have no brains - real world and model should be closer!
	 * and the game or the player is responsible for such things
	 * 
	 * LK our amebas have to know its color for the file (and its in the game also like this), 
	 * the position is for the animation and the direction is for finding the neighbor and if a player want to wriggle an ameba, 
	 * it has only to change the direction of the current ameba not of the whole game and its nr (like in the real game)
	 * Our amebas have a brain, otherwise a gene intelligence is implausible 
	 * 
	 */
	
	public Ameba(GameColor color, int posX, int posY, GameDirection direction, int nr) {
		//---------------
		//AMEBA:
		//---------------
		this.color=color;
		this.direction=direction;
		this.damagePoints=0;		//TODO: Later, it's an argument of the Constructor!
		this.nr=nr;					
		this.maxDamagePoints=2;
		this.isDead=false;
		
		//FOOD:
		this.nrOfEatBlueFood=0;
		this.nrOfEatRedFood=0;
		this.nrOfEatYellowFood=0;
		
		//---------------
		//PANEL:
		//---------------
		this.aTrans=new AffineTransform();	
		this.aTrans.translate(posX, posY);			
		this.m_velocityX=5; 						
		this.m_velocityY=0;							
		this.fileName=color.getFileNameAmeba();
		this.setImage();
		this.stepsToMove=this.STEPSTOMOVE;
		this.posX=posX;
		this.posY=posY;
		this.doMove=false;
		
		this.squareX=0;
		this.squareY=0;
		
		/*DR again you should still have a normal ASCII game and your GUI should only hook up and
		 * change how all this is represented 
		 * Clearly separate GUI and application
		 * 
		 * LK we will implement maybe also a ASCII (at the moment we haven't one!)
		 */

		
		
		this.setSize(100, 100);
		this.setVisible(true);
		
		//---------------
		//ANIMATION:
		//---------------
		animation_timer=new Timer(animation_interval,this);
		//animation_timer.stop();
	}
	
	
	//GETTERS AND SETTERS
	//---------------
	//AMEBA:
	//--------------
	
	public void setMaxDamagePoints(int value)
	{
		this.maxDamagePoints=value;
	}
	
	public int getDamagePoints()
	{
		return this.damagePoints;
	}
	

	
	public void setNrOfEatFoodOfColor(GameColor color, int nrFoodToEat)
	{
		assert nrFoodToEat>=0;
		
		switch(color)
		{
			case blue:
			{
				this.nrOfEatBlueFood=nrFoodToEat;
				break;
			}
			case red:
			{
				this.nrOfEatRedFood=nrFoodToEat;
				break;
			}
			case yellow:
			{
				this.nrOfEatYellowFood=nrFoodToEat;
				break;
			}
			default:
			{
				System.out.println("Error in Ameba.class: unknown case in method  setNrOfEatFoodOfColor(GameColor color, int nr)");
				//TODO DR what TODO?
				//LK Maybe an exception?
				break;
			}
		}
	}
	
	//DR this is a multigetter? that's wierd
	//LK why is this wierd? Its useful...
	public int getNrOfEatFoodOfColor(GameColor color)
	{
		switch(color)
		{
			case blue:
			{
				return this.nrOfEatBlueFood;
			}
			case red:
			{
				return this.nrOfEatRedFood;
				
			}
			case yellow:
			{
				return this.nrOfEatYellowFood;
			
			}
			default:
			{
				System.out.println("Error in Ameba.class: unknown case in method  getNrOfEatFoodOfColor(GameColor color)");
				//TODO
				return -1;
			}
		}
	}
	
	public GameDirection getDirection()
	{
		return this.direction;
	}
	
	public GameColor getColor()
	{
		return this.color;
	}
	
	public void setDamagePoints(int damagePoints)
	{
		this.damagePoints=damagePoints;
		
		this.checkIsDeath();
	}
	
	public boolean isDead()
	{
		return this.isDead;
	}
	
	public int getNumber()
	{
		return this.nr;
	}
	
	public void setPositionOfAmeba(int x, int y)
	{
		this.aTrans=new AffineTransform();
		this.aTrans.translate(x, y);			
		this.posX=x;
		this.posY=y;
	}
	
	//DR GUI.... again
	//---------------
	//PANEL:
	//---------------
	
	private void setImage()
	{
		
		if (this.damagePoints<=this.maxDamagePoints)
		{
			InputStream input= ClassLoader.getSystemResourceAsStream(this.fileName+"NR"+this.nr+"DP"+this.damagePoints+".jpg");
		
			assert input!=null;
			try {
				
				this.image=ImageIO.read(input);
				input.close();
			} catch (IOException e) {
				// TODO: Better Exception
				e.printStackTrace();
			}
		}
		else
		{		
			this.isDead=true;
		}
	}
	
	public void setDirection(GameDirection direction)
	{
		this.m_velocityX=direction.getX()*this.speedFactor;
		this.m_velocityY=direction.getY()*this.speedFactor;
		this.direction=direction;
	}
	
	public void setMove(Boolean run)
	{
		this.doMove=run;
		
		if (run)
		{
			
			
			this.animation_timer.start();
		}
		else
		{
			this.animation_timer.stop();
		
		}
	}
	
	public void setPosition(int x, int y)
	{
		this.posX=x;
		this.posY=y;

		this.aTrans.translate(this.posX, this.posY); //TODO: is this necessary?
	}
	
	public Point2D getPosition()
	{
		Point2D point=new Point2D.Double(this.posX, this.posY);
		return point;
	}
	
	public void setSquarePosition(int x, int y)
	{
		this.squareX=x;
		this.squareY=y;
	}
	
	public Point2D getSquarePosition()
	{
		Point2D point=new Point2D.Double(this.squareX,this.squareY);
		return point;
	}
	
	public void addDamagePoint()
	{
		this.damagePoints++;
		
		this.checkIsDeath();
	}
	
	//HELPERMETHODS
	//---------------
	//AMEBA:
	//---------------

	
	public void checkIsDeath()
	{
		if (this.damagePoints>=this.maxDamagePoints)
		{
			this.isDead=true;
		}
	}
	
	public String description()
	{
		return color.toString();
	}
	
	public String toString()
	{
		return "Ameba " + this.nr + "  " +this.color;
	}
	
	public ArrayList<Food> getFoodOfColor(GameColor color, ArrayList<Food> food)
	{
		ArrayList<Food> coloredFood=new ArrayList<Food>();
		
		for(int i=0; i<food.size();i++)
		{
			if(food.get(i).getColor().equals(color))
			{
				coloredFood.add(food.get(i));
			}
		}
		
		return coloredFood;
	}
	
	//---------------
	//PANEL:
	//---------------
	public void resetStepsToMove()
	{
		this.stepsToMove=this.STEPSTOMOVE;
	}
	
	//OTHERMETHODES
	//---------------
	//AMEBA:
	//---------------
	
	public ArrayList<Integer> eatFood(ArrayList<Integer> foods)
	{
		int availableBlueFood=foods.get(0);
		int availableRedFood=foods.get(1);
		int availableYellowFood=foods.get(2);
			
		boolean isHungry=false;
		
		assert this.isEatValid();
		
		availableBlueFood=availableBlueFood-this.nrOfEatBlueFood;
		availableRedFood=availableRedFood-this.nrOfEatRedFood;
		availableYellowFood=availableYellowFood-this.nrOfEatYellowFood;
		
		//Check:
		if(availableBlueFood<0 || availableRedFood<0 || availableYellowFood<0)
		{
			isHungry=true;
			this.addDamagePoint();
		}
		
		if(isHungry)
		{
			if(availableBlueFood<0)
			{
				availableBlueFood=0;
			}
			
			if(availableRedFood<0)
			{
				availableRedFood=0;
			}
			
			if(availableYellowFood<0)
			{
				availableYellowFood=0;
			}
		}		
		
		//SHIT:
		switch(this.color)
		{
			case blue:
			{
				availableBlueFood=availableBlueFood+this.nrOfShitNewFoods;
				break;
			}
			case red:
			{
				availableRedFood=availableRedFood+this.nrOfShitNewFoods;
				break;
			}
			case yellow:
			{
				availableYellowFood=availableYellowFood+this.nrOfShitNewFoods;
				break;
			}
			default:
			{
				System.out.println("Error in Ameba.class: unkown case in method eatFood()");
				//TODO
				break;
			}
		}
		
		ArrayList<Integer> newFoods=new ArrayList<Integer>();
		
		newFoods.add(availableBlueFood);
		newFoods.add(availableRedFood);
		newFoods.add(availableYellowFood);		
		this.checkIsDeath();
		
		return newFoods;
		
	}
	
	/**
	 * don't eat its food!
	 * @return
	 */
	private boolean isEatValid()
	{
		switch(this.color)
		{
			case blue:
			{
				return this.nrOfEatBlueFood==0;
			}
			case red:
			{
				return this.nrOfEatRedFood==0;
			}
			case yellow:
			{
				return this.nrOfEatYellowFood==0;
			}
			default:
			{
				System.out.println("Error in Ameba.class: unkown case in method isEatValid()");
				//TODO
				return false;
			}
		}
	}
	
	
	
	public int getHowManyFoodToShit()
	{
		return this.nrOfShitNewFoods;
	}
	
	//---------------
	//PANEL:
	//---------------
	
	public void draw(Graphics g)
	{
		Graphics2D g2d=(Graphics2D)g; 
		this.move();
		
		this.setImage();
		
		g2d.drawImage(this.image, this.aTrans, this);
	}
	
	public void move()
	{
		
		if (this.doMove)
		{
			if (this.stepsToMove>0)
			{
				this.aTrans.translate(this.m_velocityX, this.m_velocityY);
				this.stepsToMove-=1;
			}
			else
			{
				this.doMove=false;
				this.resetStepsToMove();
			}
		}
		
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		this.repaint(); 
		
	}
	


}
