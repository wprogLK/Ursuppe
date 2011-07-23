package GUIComponents;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

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
public class Board extends JPanel {
	//---------------
	//BOARD:
	//---------------
	
	private ISquare[][] board;
	private ISquare rndSquare;
	private ArrayList<LadderSquare> ladder;
	private ArrayList<Ameba> arrayAmeba;
	private Ameba activeAmeba=null;
	
	private GameDirection globalDirection;
	private GameDirection localDirection;
	
	private ISquare compassSquare;
	private SoupSquare activeSoupSquare;
	
	private int ozoneValue;
	
	
	//---------------
	//PANEL:
	//---------------
	private Calc calc=new Calc();
	private JLabel labelLetters=new JLabel("                      1                      2                      3                      4                      5");
	
	private JLabel labelOne=new JLabel("1");
	private JLabel labelTwo=new JLabel("2");
	private JLabel labelThree=new JLabel("3");
	private JLabel labelFour=new JLabel("4");
	private JLabel labelFive=new JLabel("5");
	
	
	
	private final int widthOfSoupSquare=100;
	
	private boolean isFirstDraw;
	private boolean isFirstPaint;
	
	private ArrayList<Player> players=new ArrayList<Player>();
	
	//---------------
	//ANIMATION:
	//---------------
	
	private int animation_interval=35; //Milliseconds between updates
	private Timer animation_timer;
	
	/**
	 * 
	 */
	public Board(ArrayList<Player> players, int ozoneValue) {

		for (int i=0; i<players.size();i++)
		{
			this.players.add(players.get(i));
		}
		
		
		//---------------
		//BOARD:
		//---------------
		initBoard();
		
		
		//---------------
		//PANEL:
		//---------------
		
		initPanel();
		
		calcComponents();
		
		
	
		
	}


	private void initBoard() {
		this.arrayAmeba=new ArrayList<Ameba>();
		this.setSquares();
		this.globalDirection=GameDirection.East;
		this.setScoreLadder();
	}

	private void initPanel() {
		this.setLayout(null);
		this.setSize(700, 700);
	
		
		this.isFirstDraw=true;
		this.isFirstPaint=true;
	}

	private void calcComponents() {
		this.calc.calcLabel(this.labelLetters, 50, 80);
		this.add(this.labelLetters);
		
		this.calc.calcLabel(this.labelOne, 50, 125);
		this.add(this.labelOne);
		
		this.calc.calcLabel(this.labelTwo, 50, 225);
		this.add(this.labelTwo);
		
		this.calc.calcLabel(this.labelThree, 50, 325);
		this.add(this.labelThree);
		
		this.calc.calcLabel(this.labelFour, 50, 425);
		this.add(this.labelFour);
		
		this.calc.calcLabel(this.labelFive, 50, 525);
		this.add(this.labelFive);
	}
	
	//GETTERS AND SETTERS
	//---------------
	//BOARD:
	//---------------
	public void setOzoneValue(int value)
	{
		this.ozoneValue=value;
		CompassSquare compassSquare= (CompassSquare) this.board[3][3];
		
	
		compassSquare.updateOzoneLayer(ozoneValue);
	}
	
	public int getOzoneValue()
	{
		return this.ozoneValue;
	}
	
	/**
	 * for set globalDirection of one single ameba
	 */
	public void setSingleWindDirection(GameDirection globalDirection)
	{
	
		for (int i = 0;i < this.arrayAmeba.size(); i++) 
		{
			Ameba ameba=this.arrayAmeba.get(i);
			
			ameba.setDirection(localDirection);
		}
		
	}
	
	
	public void setGlobalWindDirection(GameDirection globalDirection)
	{
		this.globalDirection=globalDirection;
		this.localDirection=globalDirection;
		
		ISquare compassSquare=this.board[3][3];
		
		compassSquare.setWindDirection(globalDirection);
	}
	
	public GameDirection getDirection()
	{
		return this.globalDirection;
	}
	
	public void setActiveSoupSquare(ISquare square)
	{
		assert square.isSoupSquare();
		
		this.activeSoupSquare=(SoupSquare) square;
	}
	
	public SoupSquare getActiveSoupSquare()
	{
		assert this.activeSoupSquare!=null;
		
		return this.activeSoupSquare;
	}
	
	public void setActiveAmeba(Ameba ameba)
	{
		this.activeAmeba=ameba;
	}
	
	public Ameba getActiveAmeba()
	{
		return this.activeAmeba;
	}
	
	private void setSquares()
	{
		this.board = new ISquare[7][7];
	
		setAllSquaresOnSoupSquares();
		
		overrideSquaresWithInvalidSquaresPart1();
					
		setCompassSquare();

			
		overrideSquaresWithInvalidSquaresPart2();
	}

	private void setCompassSquare() {
		ISquare soupSquare=board[3][3];
		soupSquare.setVisibleOfFoodLabel(false);

		JLabel labelOzoneValue=new JLabel();
		this.add(labelOzoneValue);
		
		this.compassSquare= new CompassSquare(300,300,this,labelOzoneValue, this.ozoneValue );
		board[3][3]=this.compassSquare;
	}

	private void overrideSquaresWithInvalidSquaresPart2() {
		ISquare soupSquare;
		soupSquare= board[1][5];
		soupSquare.setVisibleOfFoodLabel(false);
		
		soupSquare=board[2][5];
		soupSquare.setVisibleOfFoodLabel(false);

		soupSquare=board[3][5];
		soupSquare.setVisibleOfFoodLabel(false);

		soupSquare=board[5][5];
		soupSquare.setVisibleOfFoodLabel(false);
		
		soupSquare= board[1][1];
		soupSquare.setVisibleOfFoodLabel(false);
		
		board[1][5] = new InvalidSquare();
		board[2][5] = new InvalidSquare();
		board[3][5] = new InvalidSquare();
		board[5][5] = new InvalidSquare();
		board[1][1] = new InvalidSquare();
	}

	private void overrideSquaresWithInvalidSquaresPart1() {
		for (int i = 0; i < 7; i++) {
			// The invalid frame
			ISquare soupSquare=board[0][i];
			soupSquare.setVisibleOfFoodLabel(false);
			
			soupSquare=board[6][i];
			soupSquare.setVisibleOfFoodLabel(false);
			
			
			soupSquare=board[i][0];
			soupSquare.setVisibleOfFoodLabel(false);
			
			soupSquare= board[i][6];
			soupSquare.setVisibleOfFoodLabel(false);
			
			
			board[0][i] = new InvalidSquare();
			board[6][i] = new InvalidSquare();
			
			board[i][0] = new InvalidSquare();
			board[i][6] = new InvalidSquare();


		}
	}

	private void setAllSquaresOnSoupSquares() {
		for (int x = 0; x < 7; x++) {
			for (int y = 0; y < 7; y++) {
				JLabel labelFoodBlue=new JLabel();
				JLabel labelFoodRed=new JLabel();
				JLabel labelFoodYellow=new JLabel();
				
		
				
				this.add(labelFoodBlue);
				this.add(labelFoodRed);
				this.add(labelFoodYellow);
				
				board[x][y] = new SoupSquare(x*this.widthOfSoupSquare,y*this.widthOfSoupSquare,this,labelFoodBlue,labelFoodRed,labelFoodYellow); //TODO: CORRECT WITH THIS (=BOARD)?
			}
		}
	}
	
	
	public int getNumberOfAmebasOfColor(GameColor color)
	{
		int counter=0;
		
		for(int i=0; i<this.arrayAmeba.size();i++)
		{
			Ameba ameba=this.arrayAmeba.get(i);
			if(ameba.getColor()==color)
			{
				counter++;
			}
		}
		
		return counter;
	}
	
	public ArrayList<Ameba> getAllAmebas()
	{
		return this.arrayAmeba;
	}
	
	
	private void addAmeba(Ameba ameba)
	{
		assert !this.arrayAmeba.contains(ameba);
		this.arrayAmeba.add(ameba);
	}
	
	
	
	private void setScoreLadder()
	{
		this.ladder=new ArrayList<LadderSquare>();
		int  constX=110;
		int  constY=50;
		
		//A:
		//STARTSQUARES:
		createStartLadder(constX, constY);
		
		int sum=3;
		
		sum = createHorizontalLadderUp(constX, constY, sum);
		
		//B:
		sum = createVerticalLadder(constX, constY, sum);
		
		//C:
		sum = createHorzontalLadderDown(constX, constY, sum);
		
		assert(sum==50);
		
	
		
	}

	private int createHorzontalLadderDown(int constX, int constY, int sum) {
		for (int i=0; i<4;i++)
		{
			LadderSquare normalC=new LadderSquare(false,i+4+14+19,true,false,30*17-i*30+constX,19*30+constY);
			ladder.add(normalC);
			sum++;
		}
		
		for (int i=0; i<10; i++)
		{
			LadderSquare dark=new LadderSquare(false,i+4+14+19+4, false, true,30*17-4*30-i*30+constX,19*30+constY); 
			ladder.add(dark);
			sum++;
		}
		return sum;
	}

	private int createVerticalLadder(int constX, int constY, int sum) {
		for(int i=0; i<19;i++)
		{
			LadderSquare normalB=new LadderSquare(false,i+4+14,true,false,30*17+constX,i*30+constY);	
			ladder.add(normalB);
			sum++;
		}
		return sum;
	}

	private int createHorizontalLadderUp(int constX, int constY, int sum) {
		for (int i=0; i<14; i++)
		{
			LadderSquare normalA=new LadderSquare(false,i+4,true,false,90+i*30+constX,0+constY);
			ladder.add(normalA);
			sum++;
		}
		return sum;
	}

	private void createStartLadder(int constX, int constY) {
		LadderSquare startOne=new LadderSquare(true,1,false,false,0+constX,0+constY);
		LadderSquare startTwo=new LadderSquare(true,2,false,false,30+constX,0+constY);
		LadderSquare startThree=new LadderSquare(true,3,false,false,60+constX,0+constY);
		
		ladder.add(startOne);
		ladder.add(startTwo);
		ladder.add(startThree);
	}
	
	public ArrayList<LadderSquare> getLadder()
	{
		return this.ladder;
	}
	
	public LadderSquare getLadderSquare(int position)
	{
		assert position>0;
		assert position<this.ladder.size()+1;
		return this.ladder.get(position-1);
		
	}

	
	public ISquare getSquare(int x, int y)
	{
		return this.board[x][y];
	}
	
	public CompassSquare getCompassSquare()
	{
		return (CompassSquare) this.board[3][3];
	}
	
	

	
	public ISquare getMoveToSquare(Point2D point, Ameba ameba)
	{
		int x=ameba.getDirection().getX();
		int y=ameba.getDirection().getY();
		
		return this.board[(int) point.getX()+x][(int) point.getY()+y];
	}
	
	public ISquare getActualSquare(Point2D point)
	{
		return this.board[(int) point.getX()][(int) point.getY()];
	}
	
	public void setAnAmebaOnBoard(Ameba ameba)
	{
		assert !this.arrayAmeba.contains(ameba);
		this.arrayAmeba.add(ameba);
		
		ameba.setDirection(this.globalDirection);
	}
	
	public void setAnAmebaOffBoard(Ameba ameba)
	{
		assert this.arrayAmeba.contains(ameba);
		this.arrayAmeba.remove(ameba);
	}
	
	public Ameba setNewAmeba(double x, double y, GameColor color, GameDirection globalDirection)
	{
		
		this.setGlobalWindDirection(globalDirection);
		
		int squareX= (int) Math.floor((x-26)/100);
		int squareY= (int) Math.floor((y-47)/100);
		
		
		if ((squareX>=0 && squareX<=5) && (squareY>=0 && squareY<=5))
		{
			ISquare square=this.board[squareX][squareY];
			
			return checkIfAnAmebaWithSameColorIsOnSquare(color, squareX,squareY, square);

		}
		else
		{
			System.out.println("Fehler in Klasse Board (in der Methode setNewAmeba): keine korrekte Position gewählt!");
			//TODO: implement an Exception 
			return null;
		}
		

		
		
		
		
	}

	private Ameba checkIfAnAmebaWithSameColorIsOnSquare(GameColor color,int squareX, int squareY, ISquare square) {
		if (this.isAnAmebaWithColorOnSquare(color,square) || !square.isSoupSquare())
		{
			System.out.println("Fehler in Klasse Board (in Methode setNewAmeba): auf dem ausgew�hlten Square gibt es schon eine Am�be von der gleichen Farbe!");
			//TODO: implement an Exception 
			return null;
		}
		else
		{
			Ameba ameba = new Ameba(color, square.getX()+50,square.getY()+10, this.globalDirection,1); 
			this.addAmeba(ameba);
		
			square.addAmeba(ameba);
			
			ameba.setSquarePosition(squareX, squareY);
			return ameba;
		}
	}
	
	public ArrayList<Ameba> getAllAmebasOnBoard()
	{
		return this.arrayAmeba;
	}
	//---------------
	//PANEL:
	//---------------
	
	
	//---------------
	//ANIMATION:
	//---------------
	public void setSpeedAnimation(double speedFactor)
	{
		int delay=(int) Math.round(this.animation_interval/speedFactor);
		this.animation_timer.setDelay(delay);
	}
	
	//HELPERMETHODS
	//---------------
	//BOARD:
	//---------------
	public ISquare getSquareMoveTo(Ameba ameba)
	{
		Point2D point=ameba.getSquarePosition();
		return this.getMoveToSquare(point,ameba);
	}
	
	
	
	public void restBoard()
	{
		this.setScoreLadder();
		this.setSquares();
		this.isFirstDraw=true;
		this.isFirstPaint=true;
	}
	
	public boolean isAnAmebaWithColorOnSquare(GameColor color,ISquare square)
	{
		if (square.isSoupSquare())
		{
			ArrayList<Ameba> amebasWithColor=square.getAmebasOfColor(color);
		
			return !amebasWithColor.isEmpty();
		}
		else
		{
			return true;
		}
		
		
	}
	
	public void amebaEatAndShit(ISquare square, Ameba ameba)
	{
		square.amebaEatAndShit(ameba);
	}
	
	//---------------
	//PANEL:
	//---------------
	
	
	//---------------
	//ANIMATION:
	//---------------
	public void moveAnAmeba(Ameba ameba)
	{		
			Point2D point=ameba.getSquarePosition();
			
			ISquare goToSquare=this.getMoveToSquare(point,ameba);
			ISquare actualSquare=this.getActualSquare(point);
			
			if(goToSquare.isSoupSquare())
			{
				ameba.setMove(true);
				actualSquare.removeAmeba(ameba);
				goToSquare.addAmeba(ameba);
				
				ameba.setSquarePosition((int) point.getX()+ameba.getDirection().getX(), (int) point.getY()+ameba.getDirection().getY());
			
				this.amebaEatAndShit(goToSquare,ameba); 
			}
			else
			{
				this.amebaEatAndShit(actualSquare,ameba);
			}
	}
	
	
	
	
	//OTHERMETHODES
	//---------------
	//BOARD:
	//---------------
	
	
	
	//---------------
	//PANEL:
	//---------------

	public void draw(Graphics g)
	{	
		this.compassSquare.draw(g);
		
		this.updatePlayersOnLadder(g);
		this.updateAmebasAndFoodOnSoupSquare(g);
		
		if(this.isFirstDraw)
		{
			for (int y = 0; y < 7; y++) 
			{
				for (int x = 0; x < 7; x++)
				{
					updateSquare(g, y, x);
				}
			}
	
			drawLadder(g);
		}
		
	}

	private void updateSquare(Graphics g, int y, int x) {
		this.board[x][y].setWindDirection(this.globalDirection);
		this.board[x][y].setAmebasWindDirection();
		this.board[x][y].draw(g);
	}

	private void drawLadder(Graphics g) {
		for (int i = 0; i < this.ladder.size(); i++) 
		{
			this.ladder.get(i).draw(g);
		}
	}
	
	public void updatePlayersOnLadder(Graphics g)
	{
		for (int i = 1; i < this.ladder.size()+1; i++) 
		{
				if(!this.getLadderSquare(i).isEmpy())
				{
					Player player=this.getLadderSquare(i).getPlayer();
					if (player.getScore()!=this.getLadderSquare(i).getNumber())
					{
						int numberOfNewLadderSquare=player.getScore();					
						
						LadderSquare newLadderSquare=this.getLadderSquare(numberOfNewLadderSquare);
						LadderSquare oldLadderSquare=this.getLadderSquare(i);
						
						oldLadderSquare.removePlayer(player);
						newLadderSquare.setPlayer(player);
					}
				}
				
				
			
				this.getLadderSquare(i).updatePlayers(g);
		}
	}
	
	public void updateAmebasAndFoodOnSoupSquare(Graphics g)
	{
		for (int y = 0; y < 7; y++) 
		{
			for (int x = 0; x < 7; x++)
			{
				this.board[x][y].updateAmebasAndFood(g); 
			}
		}
	}
	
	/**
	 * This will called by repaint();
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		this.updatePlayersOnLadder(g);
		this.updateAmebasAndFoodOnSoupSquare(g);
		if(this.isFirstPaint)
		{
			for (int y = 0; y < 7; y++) 
			{
				for (int x = 0; x < 7; x++)
				{
					this.board[x][y].draw(g);
				}
			}
		
			drawLadder(g);
			compassSquare.draw(g);
		}
		
		for (int i=0; i<this.players.size(); i++)
		{
			Player player=players.get(i);
			player.draw(g);
			
		}
		
		
		
	
		
		

	}
	
	

}
