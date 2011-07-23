/**
 * 
 */
package game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JPanel;
import javax.swing.Timer;

import Components.Ameba;
import Components.Board;
import Components.Die;
import Components.ISquare;
import Components.Player;
import Components.SoupSquare;

import enums.*;

/**
 * @author Lukas
 *
 */
/*DR Fix Ursuppe stage 2 and 3
 * First of all GUI and application are two different things
 * and like group19 said on Friday you should be able to separate thos two things into 
 * TWO separate packages
 * and your game should run with GUI or without (ASCII)
 * to go on with I can't run your ScriptedGame1 I get an assertion error...
 * I'm glad your repo is fixed but I see a lot of work for you...
 * Next thing I don't rly like is that you have so much code commented out that's the same as 
 * red stuff - I just don't like it and no one need it^^
 * But I see that you spent most of your time to get this nice GUI which is a lot more
 * than most of the other groups have but even so you still don't pass the problemset5.2 
 * which is simply to have a ASCII game (or a GUI game but separated from the rest)
 * I can't get your test coverage due to failing tests.
 *
 */

/*
 * LK We know GUI and application should be two different things. At the moment it's impossible to separate the gui and the app.
 * We haven't got a ASCII at the moment, but you and Aaron said it will be ok if we have only a GUI, didn't you? Maybe we will implement this week a little 
 * ASCII. We will see...
 * When I run the ScriptedGame1 all is green... what's your assertion error and where it is?
 * I commented things out, because it's work in progress. Later I will delete this stuff.
 * Yes we spend a lot of time with this problemset and the gui. ;-)
 */
public class Game extends JPanel implements ActionListener {
	////////////////////////////
	//COMPONENTES OF THE GAME://
	////////////////////////////
	private Die die;
	private Board board;
	private Stack<GameDirection> stackOfWindDirection=new Stack<GameDirection>();
	private Stack<Integer> stackOfOzone=new Stack<Integer>();
	
	//////////////////////////
	//VARIABLES OF THE GAME://
	//////////////////////////
	
		//COLORS:
		private final GameColor gameColorBlue=GameColor.blue;
		private final GameColor gameColorRed=GameColor.red;
		private final GameColor gameColorYellow=GameColor.yellow;
		private final GameColor gameColorEmpty=GameColor.empty;
	
		//PLAYERS:
		private Player playerBlue;
		private Player playerRed;
		private Player playerYellow;
		private Player playerEmpty;
		
		private int numberOfPlayers;
		
		private final int bioPointsAtStart=4;
		
		//AMEBAS:
		private ArrayList<Ameba> amebasBlue;
		private ArrayList<Ameba> amebasRed;
		private ArrayList<Ameba> amebasYellow;
		private ArrayList<Ameba> amebasEmpty;
		
		private Ameba activeAmeba=null;
		private SoupSquare activeSoupSquare=null;
		
		//OZONE LAYER
		private final int MIN_OZONE=6;
		private final int MAX_OZONE=14;
		
		private int actualOzone;
		
		//GENES:
		private ArrayList<GameGene> aGenes;
		
		//GAME:
		private ArrayList<Player> orderPlayers;
		
		private boolean isGameOver;
		
		private GameDirection actualeDirection;	//Direction set by a player for one ameba
		private GameDirection globaleDirection; //Direction of one round
		
		private int numberOfActualRound;
		
		private GameReadDirection readDirection;
		
		private String gameTitle;
		
		private final int numbersOfColumn=5;
		
		private String version;
	//////////
	//TIMER://
	//////////
		
		private int animationsIntervall=35; //Milliseconds between two time ticks
		private Timer animationsTimer;
	
		
	/**
	 * 
	 */
	public Game(String gameTitle) {
		this.gameTitle=gameTitle;
		
		this.createGame();
	}
	
	///////////////////
	//CREATE METHODS://
	///////////////////
	
	public void createGame()
	{
		
		
		//////////////////////////
		//VARIABLES OF THE GAME://
		//////////////////////////
		
			//GAME:
			this.numberOfActualRound=1;
			
			this.orderPlayers=new ArrayList<Player>();
			
			this.isGameOver=false;
			
			
			this.numberOfActualRound=1;
			
			//AMEBAS:
			this.createAmebas();
			
			//PLAYERS:
			this.createPlayers();
			
		
			////////////////////////////
			//COMPONENTES OF THE GAME://
			////////////////////////////
			this.die=new Die();
			this.board=new Board(this.orderPlayers,-1);
			
			this.createStackOfWindDirection(30);
			this.createStackOfOzone(30);
			
			/////////////////////////////
			//OZON AND WIND DIRECTION Part 2//
			////////////////////////////////
			
			
			this.board.setOzoneValue(this.actualOzone);
			
			
			
			createGenes();
			
			//ONLY FOR TESTING:
			this.playerBlue.addGene(GameGene.TestGene);
			this.playerRed.addGene(GameGene.Intelligence);
			
			assert(this.playerRed.getSumOfAllGenesOzoneValue()==3);
			
		//////////
		//TIMER://
		//////////
			
			createAndStartTimer();
	}

	private void createAndStartTimer() {
		this.animationsTimer=new Timer(this.animationsIntervall,this);
		this.animationsTimer.start();
	}

	private void createGenes() {
		this.aGenes=new ArrayList<GameGene>();
		
		this.aGenes.add(GameGene.TestGene);
		this.aGenes.add(GameGene.Intelligence);
		this.aGenes.add(GameGene.Lebenserwartung);
		this.aGenes.add(GameGene.PortalGene);
		this.aGenes.add(GameGene.Sporen);
		this.aGenes.add(GameGene.Strahlenschutz);
		this.aGenes.add(GameGene.Stromlienenform);
		this.aGenes.add(GameGene.Teilungsrate);
		this.aGenes.add(GameGene.Wetterfrosch);
	}
	
	/**
	 * Creates a random stack of integers
	 * @param length
	 */
	public void createStackOfOzone(int length)
	{
		Random rnd=new Random();
		
		for (int i=0; i<length; i++)
		{
			int value=rnd.nextInt(this.MAX_OZONE-this.MIN_OZONE)+this.MIN_OZONE+1;
			
			assert value<=this.MAX_OZONE && value>=this.MIN_OZONE;
			
			this.stackOfOzone.push(2);  //TODO Change back to value
		}
		this.getNextOzonValue();
	}
	
	/**
	 * create a random stack of wind directions
	 * @param length
	 */
	public void createStackOfWindDirection(int length)
	{
		Random rnd=new Random();
		for(int i=0; i<length; i++)
		{
			int index=rnd.nextInt(4);
			
			switch(index)
			{
			case 0:
			{
				this.stackOfWindDirection.push(GameDirection.East);
				break;
			}
			case 1:
			{
				this.stackOfWindDirection.push(GameDirection.Middle);
				break;
			}
			case 2:
			{
				this.stackOfWindDirection.push(GameDirection.North);
				break;
			}
			case 3:
			{
				this.stackOfWindDirection.push(GameDirection.South);
				break;
			}
			case 4:
			{
				this.stackOfWindDirection.push(GameDirection.West);
				break;
			}
			default:
			{
				System.out.println("Error in Game.class: unknown case in method createStackOfWindDirection");
			}
			}
		}
		this.getNextWindDirection();
	}
	
	public void createPlayers()
	{
		setNewPlayers();
		
		this.numberOfPlayers=3;
		
		createOrderOfPlayers();
	}

	private void createOrderOfPlayers() {
		this.orderPlayers.add(this.playerEmpty); 		//Add an empty player [HEAD] (will used later for check if all player play in a phase)
		this.orderPlayers.add(this.playerBlue);
		this.orderPlayers.add(this.playerRed);
		this.orderPlayers.add(this.playerYellow);
		this.orderPlayers.add(this.playerEmpty); 		//Add an empty player [TAIL] (will used later for check if all player play in a phase)
	}

	private void setNewPlayers() {
		this.playerBlue=new Player(this.bioPointsAtStart,this.gameColorBlue,this.gameColorBlue.getFileNamePlayer(),"[PLAYER NAME BLUE]",this.amebasBlue);
		this.playerRed=new Player(this.bioPointsAtStart,this.gameColorRed,this.gameColorRed.getFileNamePlayer(),"[PLAYER NAME RED]",this.amebasRed);
		this.playerYellow=new Player(this.bioPointsAtStart,this.gameColorYellow,this.gameColorYellow.getFileNamePlayer(),"[PLAYER NAME YELLOW]",this.amebasYellow);
		this.playerEmpty=new Player(this.bioPointsAtStart,this.gameColorEmpty,this.gameColorEmpty.getFileNamePlayer(), "[PLAYER NAME EMPTY]",this.amebasEmpty);
	}
	
	public void createAmebas()
	{
		this.amebasBlue=new ArrayList<Ameba>();
		this.amebasRed=new ArrayList<Ameba>();
		this.amebasYellow=new ArrayList<Ameba>();
		this.amebasEmpty=new ArrayList<Ameba>();

		createBlueAmbeas();		
		
		createRedAmebas();
		
		createYellowAmebas();
		
		createEmptyAmebas();
		
	}

	private void createEmptyAmebas() {
		for (int i=0; i<4; i++)
		{
			Ameba newAmeba=new Ameba(GameColor.empty,0,0,this.globaleDirection,i+1);
			this.amebasEmpty.add(newAmeba);
		}
	}
	
	private void createYellowAmebas() {
		for (int i=0; i<4; i++)
		{
			Ameba newAmeba=new Ameba(GameColor.yellow,0,0,this.globaleDirection,i+1);
			this.amebasYellow.add(newAmeba);
		}
	}
	
	private void createRedAmebas() {
		for (int i=0; i<4; i++)
		{
			Ameba newAmeba=new Ameba(GameColor.red,0,0,this.globaleDirection,i+1);
			this.amebasRed.add(newAmeba);
		}
	}

	private void createBlueAmbeas() {
		for (int i=0; i<4; i++)
		{
			Ameba newAmeba=new Ameba(GameColor.blue,0,0,this.globaleDirection,i+1);
			this.amebasBlue.add(newAmeba);
		}
	}
	
	//////////////////////
	//GETTERS & SETTERS://
	//////////////////////
	
		////////////
		//GETTERS://
		////////////
		public String getVersion()
		{
			return this.version;
		}
	
	
		public ArrayList<GameGene> getArrayOfGenes()
		{
			return this.aGenes;
		}
		
		public int getNextOzonValue()
		{
			assert !this.stackOfOzone.isEmpty();
			
			this.actualOzone=this.stackOfOzone.pop();
			
			this.board.setOzoneValue(this.actualOzone);
			return this.actualOzone;
		}
		
		public GameDirection getGlobalWindDirection()
		{
			return this.globaleDirection;
		}
		
		public GameDirection getActualeWindDirection()
		{
			return this.actualeDirection;
		}
	
		public int getOzoneValue()
		{
			return this.actualOzone;
		}
		
		public GameDirection getNextWindDirection()
		{
			assert !this.stackOfWindDirection.isEmpty();
			
			this.globaleDirection=this.stackOfWindDirection.pop();
			this.actualeDirection=this.globaleDirection;
			
			this.board.setGlobalWindDirection(this.globaleDirection);
			
			this.board.getCompassSquare().setWindDirection(this.actualeDirection);
			
			
			return 	this.globaleDirection;
		}
	
		public Stack<GameDirection> getStackOfWindDirection()
		{
			return this.stackOfWindDirection;
		}
		
		public int getnumbersOfColumn()
		{
			return this.numbersOfColumn;
		}
	
		public int getNumberOfPlayers()
		{
			return this.orderPlayers.size()-2;
		}
	
		public Player getEmptyPlayer()
		{
			return this.playerEmpty;
		}
	
		public Player getActualPlayer()
		{
			int indexToGet=0;
			
			switch (this.readDirection)
			{
				case up:
				{
					indexToGet=1;
					break;
				}
				case down:
				{
					indexToGet=this.orderPlayers.size()-2;
					break;
				}
				default:
				{
					System.out.println("Error (Game.class): exception in nextPlayer()!");
					//TODO: Exception!
				}
			}
	
			return this.orderPlayers.get(indexToGet);
		}
		
		public ArrayList<Player> getOrderPlayers()
		{
			return this.orderPlayers;
		}
		
		public Board getBoard()
		{
			return this.board;
		}
		
		public Die getDie()
		{
			return this.die;
		}
		
		public GameDirection getGlobaleDirection()
		{
			return this.globaleDirection;
		}
		
		public GameDirection getActualeDirection()
		{
			return this.actualeDirection;
		}
		
		public String getGameTitle()
		{
			return this.gameTitle;
		}
		
		public int getNumberOfActualRound()
		{
			return this.numberOfActualRound;
		}
		
		public Player getPlayer(GameColor color)
		{
			switch(color)
			{
				case blue:
				{
					return this.playerBlue;
				}
				case red:
				{
					return this.playerRed;
				}
				case yellow:
				{
					return this.playerYellow;
				}
				case empty:
				{
					return this.playerEmpty;
				}
				default:
				{
					System.out.println("Error (Game.class): exception in getPlayer()! Return value is playerEmpty!");
					//TODO: Exception!
					return this.playerEmpty;
				}
			}
		}
		
		public Ameba getActiveAmeba()
		{
			return this.board.getActiveAmeba();
		}
		
		public SoupSquare getActiveSoupSquare()
		{
			return this.board.getActiveSoupSquare();
		}
		
		public GameReadDirection getReadDirection()
		{
			return this.readDirection;
		}
		
		public ArrayList<GameColor> getOrderOfPlayersColor()
		{
			ArrayList<GameColor> order=new ArrayList<GameColor>();
			
			for(int i=0; i<this.orderPlayers.size(); i++)
			{
				order.add(this.orderPlayers.get(i).getColor());
			}
			return order;
		}
	
		////////////
		//SETTERS://
		////////////
		
		public void setIsOver()
		{
			this.isGameOver=true;
		}
		
		public void setIsNotOver()
		{
			this.isGameOver=false;
		}
		public void setVersion(String version)
		{
			this.version=version;
		}
		
		public void setActiveAmeba(Ameba ameba)
		{
			this.activeAmeba=ameba;
			this.board.setActiveAmeba(ameba);
		}
		
		public void setActiveSoupSquare(ISquare square)
		{
			assert square.isSoupSquare();
			this.activeSoupSquare=(SoupSquare) square;
			this.board.setActiveSoupSquare(square);
		}

		/**
		 * sets the read direction how the players in the arrayList will be read and moved
		 * This method have to be called every time when the phase change! IMPORTANT!!
		 */
		public void setReadDirection(GameReadDirection readDirection)
		{
			this.readDirection=readDirection;
		}
		
		public void setOrderPlayers(ArrayList<Player> orderOfPlayers)
		{
			this.orderPlayers=orderOfPlayers;
		}
		
		public void setBoard(Board board)
		{
			this.board=board;
		}
		
		public void setDie(Die die)
		{
			this.die=die;
		}
		
		public void setGlobaleDirection(GameDirection globaleDirection)
		{
			this.globaleDirection=globaleDirection;
		}
		
		public void  setActualeDirection(GameDirection actualeDirection)
		{
			this.actualeDirection=actualeDirection;
		}
		
	//////////////////
	//OTHER METHODS://
	//////////////////
		
		///////////
		//PLAYER://
		///////////
		
		public void nextPlayer()
		{
			Player oldPlayer=this.getActualPlayer();
			int indexToInsert=0;
			
			switch (this.readDirection)
			{
				case up:
				{
					indexToInsert=this.orderPlayers.size()-1;
					break;
				}
				case down:
				{
					indexToInsert=0;
					break;
				}
				default:
				{
					System.out.println("Error (Game.class): exception in nextPlayer()!");
					//TODO: Exception!
				}
			}
			
			this.orderPlayers.remove(oldPlayer);
			
			this.orderPlayers.add(indexToInsert, oldPlayer);
			
			this.getActualPlayer();
			
		}
		
		/////////
		//GAME://
		/////////
		
		public void incrementNumberOfActualRound()
		{
			this.numberOfActualRound++;
		}
		
		
	////////////////////
	//SPECIAL METHODS://
	////////////////////
	public void draw(Graphics g)
	{
		super.repaint();
		this.board.draw(g);
		this.die.draw(g);
		
	}
		
		
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==this.animationsTimer)
		{
			this.board.repaint();
			this.playerBlue.repaint();
			this.playerRed.repaint();
			this.playerYellow.repaint();
		}
		
	}

	/**
	 * TO SKIP EMPTY PLAYERS:
	 */
	public void skipEmptyPlayer() {
		int i=0;
		
		while(this.getActualPlayer().equals(this.playerEmpty))	
		{	
			this.nextPlayer(); 
			i++;
		}
		System.out.println("i " + i);
		
	}
	
	/////////////////////
	//ONLY FOR TESTING://
	/////////////////////
	
	
		//////////////////////
		//GETTERS & SETTERS://
		//////////////////////
			
			////////////
			//GETTERS://
			////////////
	
			public int getBioPointsAtStart()
			{
				
				return this.bioPointsAtStart;
			}
			
			public int getMAX_OZONE()
			{
				return this.MAX_OZONE;
			}
			
			public int getMIN_OZONE()
			{
				return this.MIN_OZONE;
			}
			
			public boolean getIsOver()
			{
				return this.isGameOver;
			}

			public void addPlayedRound() {
				this.numberOfActualRound++;
			}
	
			
			
			////////////
			//SETTERS://
			////////////
		
			/**
			 * this is for testing specific situation 
			 * @param stack
			 */
			public void setStackOfOzone(Stack<Integer> stack)
			{
				this.stackOfOzone=stack;
				this.getNextOzonValue();
			}
		
			
			/**
			 * this is for testing specific situation 
			 * @param stack
			 */
			public void setStackOfWindDirection(Stack<GameDirection> stack)
			{
				this.stackOfWindDirection=stack;
				this.getNextWindDirection();
			}
			
			public String getBoardAsString()
			{
				return this.board.getBoardAsString();
		
			}
			
			public String getInformationAboutPlayers()
			{
				String str="";
				
				String infoBlue=this.playerBlue.description();
				String infoRed=this.playerRed.description();
				String infoYellow=this.playerYellow.description();
				
				str="INFORMATION ABOUT THE PLAYERS: \n " +"-------------------------------\n \t" + infoBlue +"\n \t"+ infoRed +"\n \t"+ infoYellow +"\n \t";
				
				return str;
				
			}

			public void repaireOrderOfPlayers() {
				
				Player head=this.getOrderPlayers().get(0);
				Player tail=this.getOrderPlayers().get(this.getNumberOfPlayers());
				while(head!=this.getEmptyPlayer() && tail!=this.getEmptyPlayer())
				{
					this.nextPlayer();
					System.out.println("Try to fixe order of players...");
					System.out.println("The new order is: " + this.getOrderPlayers());
					head=this.getOrderPlayers().get(0);
					tail=this.getOrderPlayers().get(this.getNumberOfPlayers());
				}
				System.out.println("FIXED ORDER OF PLAYERS");
					
			}
				
			
		
}
