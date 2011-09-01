package game;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import enums.GameColor;
import enums.GameGene;

/**
 * 
 */

/**
 * @author Lukas
 *
 */
public class Player extends JPanel{
	//---------------
	//PLAYER:
	//---------------
	private GameColor color;
	private int score;
	private String name;
	private int rolledFaceValue;
	private int bioPoint;
	private final int nrOfFoodsToEat=3;
	
	private ArrayList<GameGene> genes=new ArrayList<GameGene>();
	
	private int sumOfAllGenesOzoneValue=0;
	
	//---------------
	//PANEL:
	//---------------
	private int posX;
	private int posY;
	
	private  final int constDistance=2;
	private final int constDistance2=8;
	
	private String playerName;
	
	
	
	private String fileName;
	private BufferedImage image;
	
	private boolean isOnADarkSquare; 
	
	private ArrayList<Ameba> amebasOffBoard;	//possible to set
	private ArrayList<Ameba> amebasOnBoard;
	private ArrayList<Ameba> allAmebas;
	
	private int costOfMoveAnAmeba;
	private int costOfSetAnAmeba;
	
	//---------------
	//ANIMATION:
	//---------------
	private int animation_interval=35; //Milliseconds between updates
	private Timer animation_timer;
	
	/**
	 * 
	 */
	public Player(int bioPoints,GameColor color, String fileName, String playerName, ArrayList<Ameba> allAmebas) {
		//---------------
		//PLAYER:
		//---------------
		this.bioPoint=bioPoints;
		this.color=color;
		this.isOnADarkSquare=false;
		this.playerName=playerName;
		this.rolledFaceValue=0;
	
		this.amebasOnBoard=new ArrayList<Ameba>();		
		this.amebasOffBoard=new ArrayList<Ameba>();
		this.allAmebas=new ArrayList<Ameba>();
	
		
		for (int i=0; i<allAmebas.size(); i++)
		{
			this.amebasOffBoard.add(allAmebas.get(i));
			this.allAmebas.add(allAmebas.get(i));
		}
		
		setCosts();
		
		//---------------
		//PANEL:
		//---------------
		this.fileName=fileName;
		
		
		this.posX=0;
		this.posY=0;
	
		this.setImage();
		
		//---------------
		//PLAYER:
		//---------------
		this.setScore(0);
		
	}

	private void setCosts() {
		this.setCostOfMoveAnAmeba(1);
		this.setCostOfSetAnAmeba(6);
	}
	
	public void addGene(GameGene gene)
	{
		assert !this.genes.contains(gene);
		
		this.genes.add(gene);
		
		gene.buyGene();
		
		this.sumOfAllGenesOzoneValue+=gene.getOzoneValue();
		this.bioPoint=this.bioPoint-gene.getPrice();
		
		//IMPLEMENT HERE GENE LEBENSERWARTUNG: PART1
		if (gene.equals(GameGene.Lebenserwartung))
		{
			for(int i=0; i<this.amebasOffBoard.size();i++)
			{
				this.amebasOffBoard.get(i).setMaxDamagePoints(3);
			}
			
			for (int i=0; i<this.amebasOnBoard.size(); i++)
			{
				this.amebasOnBoard.get(i).setMaxDamagePoints(3); 
			}
		}
	
		//IMPLEMENT HERE GENE TEILUNGSRATE: PART1
		if (gene.equals(GameGene.Teilungsrate))
		{
			this.costOfSetAnAmeba=4;
		}
	}
	
	public void removeGene(GameGene gene)
	{
		assert this.genes.contains(gene);
		
		gene.sellGene();
		
		this.genes.remove(gene);
		this.sumOfAllGenesOzoneValue-=gene.getOzoneValue();
		
		//IMPLEMENT HERE GENE LEBENSERWARTUNG: PART2
		if (gene.equals(GameGene.Lebenserwartung))
		{
			for(int i=0; i<this.amebasOffBoard.size();i++)
			{
				this.amebasOffBoard.get(i).setMaxDamagePoints(2);
			}
			
			for (int i=0; i<this.amebasOnBoard.size(); i++)
			{
				this.amebasOnBoard.get(i).setMaxDamagePoints(2); 
			}
		}
		
		//IMPLEMENT HERE GENE TEILUNGSRATE: PART2
		if (gene.equals(GameGene.Lebenserwartung))
		{
			this.costOfSetAnAmeba=6;
		}
		
	}
	public ArrayList<GameGene> getGenes()
	{
		return this.genes;
	}
	
	public void addAmebaToOnBoard(Ameba ameba)
	{
		assert !this.amebasOnBoard.contains(ameba);
		this.amebasOnBoard.add(ameba);
		
		assert this.amebasOffBoard.contains(ameba);
		this.amebasOffBoard.remove(ameba);
	}
	
	public void addAmebaToOffBoard(Ameba ameba)
	{
		assert !this.amebasOffBoard.contains(ameba);
		this.amebasOffBoard.add(ameba);
		
		assert this.amebasOnBoard.contains(ameba);
		this.amebasOnBoard.remove(ameba);
	}
	
	public void addBioPoints(int points)
	{
		this.bioPoint=this.bioPoint+points;
	}
	
	public void subBioPoints(int points)
	{
		this.bioPoint=this.bioPoint-points;
	}
	
	//GETTERS AND SETTERS
	//---------------
	//PLAYER:
	//---------------
	public int getPosX()
	{
		return this.posX;
	}
	
	public int getPosY()
	{
		return this.posY;
	}
	
	public int getNumberOfGenes()
	{
		return this.genes.size();
	}
	
	public ArrayList<GameGene> getAllGenes()
	{
		return this.genes;
	}
	
	public boolean hasPlayerGene(GameGene gene)
	{
		return this.genes.contains(gene);
	}
	
	public int getSumOfAllGenesOzoneValue()
	{
		return this.sumOfAllGenesOzoneValue;
	}
	
	public int getScoreOfGenes()
	{
		int score=0;
		
		for (int i=0; i<this.genes.size();i++)
		{
			score=score+genes.get(i).getScore();
		}
		
		return score;
	}
	
	
	
	public int getCostOfMoveAnAmeba()
	{
		return this.costOfMoveAnAmeba;
	}
	public int getCostOfSetAmeba()
	{
		return this.costOfSetAnAmeba;
	}
	
	public void setCostOfMoveAnAmeba(int cost)
	{
		this.costOfMoveAnAmeba=cost;
	}
	
	public void setCostOfSetAnAmeba(int cost)
	{
		this.costOfSetAnAmeba=cost;
	}
	
	public int getNrOfFoodsToEat()
	{
		return this.nrOfFoodsToEat;
	}
	
	public int getBioPoints()
	{
		return this.bioPoint;
	}

	public void setBioPoints(int points)
	{
		this.bioPoint=points;
	}
	public Ameba getAmebaWithNumber(int numberOfAmeba)
	{
		assert (numberOfAmeba<5 && numberOfAmeba>0);
		
		Ameba ameba=this.allAmebas.get(numberOfAmeba-1);			
		
		assert (ameba.getNumber()==numberOfAmeba);
		
		return ameba;
	}
	
	public int getNumbersOfAmebasOnBoard()
	{
		return this.getAmebasOnBoard().size();
	}
	
	public int getNumbersOfAmebasOffBoard()
	{
		return this.getAmebasOffBoard().size();
	}
	
	public ArrayList<Ameba> getAmebasOffBoard()
	{
		return this.amebasOffBoard;
	}
	
	public ArrayList<Ameba> getAmebasOnBoard()
	{
		return this.amebasOnBoard;
	}
	
	public void setRolledFaceValue(int rolledFaceValue)
	{
		this.rolledFaceValue=rolledFaceValue;
	}
	
	public void setIsOnADarkSquare()
	{
		this.isOnADarkSquare=true;
	}
	
	public boolean getIsOnADarkSquare()
	{
		return this.isOnADarkSquare;
	}
	
	
	public void setPlayerName(String playerName)
	{
		this.playerName=playerName;
	}
	
	
	
	public void setScore(int score)
	{
		//assert score>0;
		this.score=score;
		this.posX=this.constDistance*score*this.image.getWidth()+this.constDistance2;
		this.posY=0;
	}
	
	public GameColor getColor()
	{
		return this.color;
	}
	
	public int getScore()
	{
		//assert score>0;
		return this.score;
	}
	
	public String getPlayerName()
	{
		return this.playerName;
	}
	
	public int getRolledFaceValue()
	{
		return this.rolledFaceValue;
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
	//PLAYER:
	//---------------
	public void setPosition(int x, int y)
	{
		this.posX=x;
		this.posY=y;
	}
	
	public String description()
	{
		return color.toString();
	}
	
	public String toString()
	{
		return "Player " + this.playerName + " [ "  + this.color + " ]: Amebas on Board: " +  this.amebasOnBoard + " Amebas off Board: " + this.amebasOffBoard +" Genes: " + this.genes + " Score: "+ this.score + " BioPoints: " + this.bioPoint;
	}
	
	public void addScore(int score)
	{
		this.score+=score;
	}
	

	public void draw(Graphics g)
	{
		Graphics2D g2d=(Graphics2D)g;
		g2d.drawImage(this.image, this.posX,this.posY, this);
	}
	
	


}
