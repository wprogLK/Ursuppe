package Components;
import helpClasses.Calc;
import helpClasses.FillWithBlank;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
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
public class SoupSquare extends JPanel implements ISquare{
	//---------------
	//SOUPSQUARE:
	//---------------
	private ArrayList<Food> aFood;
	private ArrayList<Ameba> aAmebas; 
	private GameDirection direction;
	private boolean isEmpty;
	
	private Board board;
	
	private int nrOfFoodBlue;
	private int nrOfFoodRed;
	private int nrOfFoodYellow;
	
	private ArrayList<Integer> food=new ArrayList<Integer>();
	
	private final int NRSTARTFOOD=2;
	//---------------
	//PANEL:
	//---------------
	private JLabel labelFoodBlue;
	private JLabel labelFoodRed;
	private JLabel labelFoodYellow;
	
	private BufferedImage image;
	private String fileName;
	private int posX;
	private int posY;
	
	private final int widthOfFood=10;
	private final int widthOfAmeba=30;
	

	public SoupSquare(int posX, int posY, Board board, JLabel labelFoodBlue,JLabel labelFoodRed, JLabel labelFoodYellow ) {
		//---------------
		//SOUPSQUARE:
		//---------------
		this.labelFoodBlue=labelFoodBlue;
		this.labelFoodRed=labelFoodRed;
		this.labelFoodYellow=labelFoodYellow;
		
		this.aFood=new ArrayList<Food>();
		this.aAmebas=new ArrayList<Ameba>();
		
		this.board=board;
		
		this.posX=posX;
		this.posY=posY;
		
		this.initFood();
	
		this.direction=null;
		
		this.isEmpty=true;
		//---------------
		//PANEL:
		//---------------
		this.fileName="soupSquare.jpg";
	}
	
	//GETTERS AND SETTERS
	//---------------
	//SOUPSQUARE:
	//---------------
	public int getNrOfFood(GameColor color)
	{
		return this.countFood(color);
	}
	
	public boolean isEmpy()
	{
		return this.isEmpty;
	}
	
	
	public boolean isInvalidSquare()
	{
		return false;
	}
	
	public boolean isSoupSquare()
	{
		return true;
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
		return this.posX;
	}
	
	public int getY()
	{
		return this.posY;
	}
	
	public ArrayList<Ameba> getAmebasOfColor(GameColor color)
	{
		ArrayList<Ameba> specificAmeba= new ArrayList<Ameba>();
		
		
		for (int i=0; i<aAmebas.size();i++)
		{
			if (aAmebas.get(i).getColor().equals(color))
			{
				specificAmeba.add(aAmebas.get(i));
			}
		}
		
		return specificAmeba;
	}
	
	private void setAmebaPosition(Ameba ameba)
	{
		int i=this.aAmebas.size();
		ameba.setPosition(this.posX/100, this.posY/100+i*this.widthOfAmeba);
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
	//SOUPSQUARE:
	//---------------
	
	public void addAmeba(Ameba ameba)
	{
		assert !this.aAmebas.contains(ameba);
		this.setAmebaPosition(ameba);
		this.aAmebas.add(ameba);
		this.isEmpty=false;
		
	}
	
	public void amebaEatAndShit(Ameba ameba)
	{
		this.ledEatAmeba(ameba);
		updateLabels();
	}
	
	public void removeAmeba(Ameba ameba)
	{
		assert !this.aAmebas.isEmpty();
		this.aAmebas.remove(ameba);
		
		if(this.aAmebas.isEmpty())
		{
			this.isEmpty=true;
		}
	}
	
	public Ameba getAnAmeba()
	{
		if (!(this.aAmebas.size()==0))
		{
			return this.aAmebas.get(0);
		}
		else
		{
			System.out.println("Sorry, aber auf diesem SoupSquare hat es keine Am�be! Es wird null zur�ckgegeben.");
			return null;
		}
		
	}
	
	public void addPlayer(Player player)
	{
		//do nothing
	}
	
	public void removePlayer(Player player)
	{
		//do nothing
	}
	
	private int countFood(GameColor color)
	{
		switch(color)
		{
		case blue:
		{
			return this.nrOfFoodBlue;
		}
		case red:
		{
			return this.nrOfFoodRed;
			
		}
		case yellow:
		{
			return this.nrOfFoodYellow;
		}
		default:
		{
			System.out.println("Error in SoupSquare.class: unkown case in method coutFood(GameColor color)");
			//TODO
			return 0;
		}
		}
	}
	
	private int countAmebas(GameColor color)
	{
		int count=0;
		for (int i=0; i<aAmebas.size();i++)
		{
			if (aAmebas.get(i).getColor()==color)
			{
				count +=1;
			}
		}
		return count;
	}
	
	/**
	 * used for soupsquares which are invalid or compasssquare
	 * @param visible
	 */
	public void setVisibleOfFoodLabel(boolean visible)
	{
		this.labelFoodBlue.setVisible(visible);
		this.labelFoodRed.setVisible(visible);
		this.labelFoodYellow.setVisible(visible);
	}
	
	
	private void initFood()
	{
		createAllFood();
		
		setStartNrOfFood();
		
		addNrOfFood();
		

		calcLabels();
		
	
		createIconFood();
	}

	private void createIconFood() {
		Food blueFood=new Food(GameColor.blue,"foodB.jpg",this.posX+5*1+this.widthOfFood,this.posY+this.widthOfFood);
		Food redFood=new Food(GameColor.red,"foodR.jpg",this.posX+5*1+this.widthOfFood,this.posY+15+this.widthOfFood);
		Food yellowFood=new Food(GameColor.yellow,"foodY.jpg",this.posX+5*1+this.widthOfFood,this.posY+30+this.widthOfFood);
		
		
		this.aFood.add(blueFood);
		this.aFood.add(redFood);
		this.aFood.add(yellowFood);
	}

	private void calcLabels() {
		Calc calc=new Calc();
		calc.calcLabel(labelFoodBlue, posX+25, posY+this.widthOfFood-3);
		calc.calcLabel(labelFoodRed, posX+25, posY+15+this.widthOfFood-3);
		calc.calcLabel(labelFoodYellow, posX+25, posY+30+this.widthOfFood-3);
	}

	private void addNrOfFood() {
		this.food.add(this.nrOfFoodBlue);
		this.food.add(this.nrOfFoodRed);
		this.food.add(this.nrOfFoodYellow);
	}

	private void setStartNrOfFood() {
		this.nrOfFoodBlue=NRSTARTFOOD;
		this.nrOfFoodRed=NRSTARTFOOD;
		this.nrOfFoodYellow=NRSTARTFOOD;
	}

	private void createAllFood() {
		this.createFoodOfColor(GameColor.blue, NRSTARTFOOD);
		this.createFoodOfColor(GameColor.red, NRSTARTFOOD);
		this.createFoodOfColor(GameColor.yellow, NRSTARTFOOD);
	}
	
	
	public void createFoodOfColor(GameColor color, int nr)
	{
				switch(color)
				{
					case blue:
					{
						this.nrOfFoodBlue+=nr;
						break;
					}
					case red:
					{
						this.nrOfFoodRed+=nr;
						break;
					}
					case yellow:
					{
						this.nrOfFoodYellow+=nr;
						break;
					}
					default:
					{
						System.out.println("Error in SoupSquare.class: unkown case in createFoodOfColor(GameColor color, int nr)!");
						//TODO
						break;
					}
				}
				
				updateLabels();
		
	}
	
	public boolean containAmeba(Ameba ameba)
	{
		return this.aAmebas.contains(ameba);
	}
	
	public void ledEatAmeba(Ameba ameba)
	{
		
		this.food=ameba.eatFood(this.food);
		
		this.nrOfFoodBlue=this.food.get(0);
		this.nrOfFoodRed=this.food.get(1);
		this.nrOfFoodYellow=this.food.get(2);
		
		this.updateLabels();
	}
	//---------------
	//PANEL:
	//---------------
	
	
	//OTHERMETHODES
	//---------------
	//SOUPSQUARE:
	//---------------
	
	public String description()
	{
		int nrFoodBlue= countFood(GameColor.blue);
		int nrFoodYellow= countFood(GameColor.yellow);
		int nrFoodRed=countFood(GameColor.red);
		
		int nrAmebasBlue=countAmebas(GameColor.blue);
		int nrAmebasYellow=countAmebas(GameColor.yellow);
		int nrAmebasRed=countAmebas(GameColor.red);
		
		String str="B" + nrAmebasBlue + "Y" + nrAmebasYellow + "R" + nrAmebasRed + " -- " +"b" + nrFoodBlue + "y" +nrFoodYellow + "r" +nrFoodRed; 
		
		return str;
	}
	
	public String toString()
	{
		int nrFoodBlue= countFood(GameColor.blue);
		int nrFoodYellow= countFood(GameColor.yellow);
		int nrFoodRed=countFood(GameColor.red);
		
		int nrAmebasBlue=countAmebas(GameColor.blue);
		int nrAmebasYellow=countAmebas(GameColor.yellow);
		int nrAmebasRed=countAmebas(GameColor.red);
		
		String str="  B " + nrAmebasBlue + " Y " + nrAmebasYellow + " R " + nrAmebasRed + " -- " +"b " + nrFoodBlue + " y " +nrFoodYellow + " r " +nrFoodRed  + "  "; 
		
		return str;
	}
	
	public String getUpperStringPart()
	{
		int nrFoodBlue= countFood(GameColor.blue);
		int nrFoodYellow= countFood(GameColor.yellow);
		int nrFoodRed=countFood(GameColor.red);
		
		String str="b " + nrFoodBlue + " y " +nrFoodYellow + " r " +nrFoodRed;
		
		
		
		str=FillWithBlank.fillWithBlank(str, 13);
		//System.out.println("???????????????????: FOOD " + str.length());
		return  str;
	}
	
	public String getDownerStringPart()
	{
		String str="";
		
		str=FillWithBlank.fillWithBlank(str, 13);
		
		return  str;
	}
	
	public String getMiddleStringPart()
	{
		
		int nrAmebasBlue=countAmebas(GameColor.blue);
		int nrAmebasYellow=countAmebas(GameColor.yellow);
		int nrAmebasRed=countAmebas(GameColor.red);
		
	
		String str="B " + nrAmebasBlue + " Y " + nrAmebasYellow + " R " + nrAmebasRed; 
		
		
		
		
		str=FillWithBlank.fillWithBlank(str, 13);
		//System.out.println("???????????????????: AMEBA " + str.length());
		return  str;
		
		
		
	}

	
	//---------------
	//PANEL:
	//---------------

	
	public void draw(Graphics g)
	{
		
		Graphics2D g2d=(Graphics2D)g;
		this.setImage();
		g2d.drawImage(this.image, this.posX, this.posY, this);
		
		drawAmebas(g);
		
		drawFood(g);
	}

	private void drawFood(Graphics g) {
		for (int i=0; i<this.aFood.size();i++)
		{
			Food food= this.aFood.get(i);
			food.draw(g);
		}
	}

	private void drawAmebas(Graphics g) {
		for (int i=0; i<this.aAmebas.size();i++)
		{
			Ameba ameba= this.aAmebas.get(i);
			
			ameba.draw(g);
		}
	}
	
	public void updateAmebasAndFood(Graphics g)	
	{
		//Draw ameba
		for (int i=0; i<this.aAmebas.size();i++)
		{
			Ameba ameba= this.aAmebas.get(i);
			ameba.move();
			ameba.draw(g);
		}
		
		drawFood(g);
		
				
	}
	
	public void updateLabels()
	{
		this.labelFoodBlue.setText(""+this.nrOfFoodBlue);
		this.labelFoodRed.setText("" +this.nrOfFoodRed);	
		this.labelFoodYellow.setText("" +this.nrOfFoodYellow);	
		
		assert Integer.parseInt(this.labelFoodBlue.getText())==this.nrOfFoodBlue;
		assert Integer.parseInt(this.labelFoodRed.getText())==this.nrOfFoodRed;
		assert Integer.parseInt(this.labelFoodYellow.getText())==this.nrOfFoodYellow;
	}
	
	public void updatePlayers(Graphics g)			
	{
		//do nothing
	}

	public void setAmebasWindDirection() {
		for (int i=0; i<this.aAmebas.size(); i++)
		{
			Ameba ameba=this.aAmebas.get(i);
			ameba.setDirection(this.direction);
			ameba.setMove(true);
		}
		
	}


	public void moveAbebas() {
		//do nothing
		
	}

	public void setWindDirection(GameDirection direction)
	{
		this.direction=direction;
	}

	@Override
	public Player getPlayer() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	


}
