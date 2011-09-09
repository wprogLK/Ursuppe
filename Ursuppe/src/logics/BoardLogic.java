/**
 * 
 */
package logics;

import java.awt.geom.Point2D;
import java.io.PrintStream;
import java.util.ArrayList;

import annotations.OnlyForTesting;

import interfaces.IAmoeba;
import interfaces.IBoard;
import interfaces.ICompassSquare;
import interfaces.IModule;
import interfaces.IPlayer;
import interfaces.ISoupSquare;
import interfaces.ISquare;

/**
 * @author Lukas Keller
 * @version 1.0.0
 */
public class BoardLogic implements IBoard
{
	/*
	 * NOTE: COORINATE SYSTEM OF 2D-ARRAY:
	 * 
	 * 	[0][0]	[0][1]	[0][2]
	 * 	[1][0]	[1][1]	[1][2]
	 * 	[2][0]	[2][1]	[2][2]
	 */
	
	
	protected IModule module;
	
	protected PrintStream outStream;
	protected PrintStream errorStream;
	
	protected ISoupSquare[][] soupBoard;
	protected Triples placesOfAmoebas=new Triples();
	protected ICompassSquare compassSquare;
	
	protected ArrayList<IAmoeba> onBoardAmoebas=new ArrayList<IAmoeba>();
	
	/**
	 * default constructor which build the original board
	 */
	public BoardLogic(PrintStream out, PrintStream error, IModule module)
	{
		this.module=module;
		
		this.outStream=out;
		this.errorStream=error;
		
		this.buildEmptyBoard(6,6);
		
		this.buildOriginalBoard();
	}

	///////////
	//GETTERS//
	///////////
	@Override
	public final ICompassSquare getCompassSquare() 
	{
		return this.compassSquare;
	}
	
	@Override
	public final ISoupSquare getSquare(int y, int x) 
	{
		x--;
		y--;
		return this.soupBoard[x][y];
	}
	///////////
	//SETTERS//
	///////////

	public final void setCompassSquare(ICompassSquare compass,int x, int y)
	{
		this.compassSquare=compass;
		this.compassSquare.setPosition(x, y);
	}
	
	///////////
	//AMOEBAS//
	///////////
	@Override
	public final void addAmoebaToSquare(IAmoeba amoeba, int x, int y)			//TODO: private
	{
		boolean validAmoeba=this.onBoardAmoebas.contains(amoeba);
		boolean validSquare=this.existSoupSquare(x,y);
		
		boolean valid=validAmoeba && validSquare;
		
		if(valid)
		{
			ISoupSquare square=this.getSquare(x, y);
			
			square.addAmoeba(amoeba);
			
			this.placesOfAmoebas.addTriple(x, y, amoeba);
		}
		else
		{
			//TODO: maybe throw an exception?
		}
	}
	
	@Override
	public final void removeAmoebaFromCurrentSquare(IAmoeba amoeba)			//TODO: private
	{
		boolean valid=this.onBoardAmoebas.contains(amoeba);
		
		if(valid)
		{
			ISoupSquare square=this.getSoupSquareOfAmoeba(amoeba);
			
			square.removeAmoeba(amoeba);
			
			this.placesOfAmoebas.removeTriple(amoeba);
		}
		else
		{
			//TODO: maybe throw an exception?
		}
		
		
	}
	
	@Override
	public final void setAmoebaOnBoard(IAmoeba amoeba)
	{
		boolean valid=!this.onBoardAmoebas.contains(amoeba);
		
		if(valid)
		{
			this.onBoardAmoebas.add(amoeba);
		}
		else
		{
			//TODO throw an  exception
		}
	}
	
	@Override
	public final void takeAmobeaOffBoard(IAmoeba amoeba)
	{
		boolean valid=this.onBoardAmoebas.contains(amoeba);
		
		if(valid)
		{
			this.onBoardAmoebas.remove(amoeba);
		}
		else
		{
			//TODO throw an  exception
		}
	}
	
	
	@Override
	@OnlyForTesting
	public void testAddAmeba() 
	{
		//TODO DELETE THE WHOLE METHOD
		IAmoeba amoeba=this.module.createAmoeba();
		
		this.soupBoard[1][1].addAmoeba(amoeba);
	}
	
	
	///////////
	//PLAYERS//
	///////////
	
	@Override
	public final void addPlayer(IPlayer player) 
	{
		// TODO
	}

	
	///////////
	//SQUARES//
	///////////
	@Override
	public final void addSquare(ISoupSquare square, int x, int y) //TODO: private
	{
		boolean isEmptySquare=this.isEmptySquare(x,y);
		assert(isEmptySquare);
		
		this.soupBoard[x][y]=square;
		
		this.configSquare(x,y,square);
	}
	
	////////////////////
	//PRIVATE METHODES//
	////////////////////
	
	private final void buildOriginalBoard() 
	{
		this.buildSoupSquares();
		
		this.buildCompassSquare();
	}

	private final void buildCompassSquare() 
	{
		this.compassSquare=this.module.createCompassSquare();
		
		this.setCompassSquare(compassSquare, 2, 2);
	}
	
	private final void buildSoupSquares() 
	{
		//First line
		for(int x=1;x<this.soupBoard.length-1;x++)
		{
			ISoupSquare square=this.module.createSoupSquare();
			this.soupBoard[0][x]=square;
		}
		
		//Middle lines
		for(int y=1;y<this.soupBoard.length-2;y++)
		{
			for(int x=0;x<this.soupBoard.length-1;x++)
			{
				ISoupSquare square=this.module.createSoupSquare();
				this.soupBoard[y][x]=square;
			}
		}
		
		//Last line
		ISoupSquare square=this.module.createSoupSquare();
		this.soupBoard[4][3]=square;
	}


	private final void buildEmptyBoard(int maxX, int maxY) 
	{
		
		this.soupBoard=new ISoupSquare[maxX][maxY];
	}

	private final boolean isEmptySquare(int x, int y) 
	{
		boolean valid=true;
		
		boolean validX=(x>=0) && (x<this.soupBoard.length-1);
		boolean validY=(y>=0) && (y<this.soupBoard[0].length-1);
		
		assert(validX && validY);
		
		boolean nullSquare=(this.soupBoard[x][y]==null);
		
		valid=validX && validY && nullSquare;
		
		return valid;
	}


	private final void configSquare(int x, int y, ISoupSquare square) 
	{
		//TODO CHECK THIS!
		//TODO REFACTOR
		ISoupSquare neighborLeft;
		ISoupSquare neighborRight;
		ISoupSquare neighborUp;
		ISoupSquare neighborDown;
	
		if(x==0)
		{
			neighborLeft=null;
		}
		else
		{
			neighborLeft=this.soupBoard[x-1][y];
		}
		
		if(y==0)
		{
			neighborUp=null;
		}
		else
		{
			neighborUp=this.soupBoard[x][y-1];
		}
		
		if(x==this.soupBoard.length-1)
		{
			neighborRight=null;
		}
		else
		{
			neighborRight=this.soupBoard[x+1][y];
		}
		
		if(y==this.soupBoard.length-1)
		{
			neighborDown=null;
		}
		else
		{
			neighborDown=this.soupBoard[x][y+1];
		}
		
		square.setDownSquare(neighborDown);
		square.setUpSquare(neighborUp);
		
		square.setLeftSquare(neighborLeft);
		square.setRightSquare(neighborRight);
		
	}
	
	
	private final boolean existSoupSquare(int y,int x) 
	{
		y--;
		x--;
		
		if(x<0 || x>this.soupBoard.length-1)
		{
			return false;
		}
		else if(y<0 || y>this.soupBoard.length-1)
		{
			return false;
		}
		else if(x==this.compassSquare.getPosX() && y==this.compassSquare.getPosY())
		{
			return false;
		}
		else if(this.soupBoard[x][y]==null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	private final ISoupSquare getSoupSquareOfAmoeba(IAmoeba amoeba)
	{
		boolean valid=this.onBoardAmoebas.contains(amoeba);
		
		if(valid)
		{
			int x=this.placesOfAmoebas.getXOfTriple(amoeba);
			int y=this.placesOfAmoebas.getYOfTriple(amoeba);
			
			return this.getSquare(x, y);
		}
		else
		{
			return null;
			//TODO: maybe throw an exception?
		}
	}
	
	////////////////////
	//ONLY FOR TESTING//
	////////////////////

	@Override
	@OnlyForTesting
	public boolean testExistSoupSquare(int x, int y)
	{
		return this.existSoupSquare(x, y);
	}
	
	/////////////////
	//INNER CLASSES//
	/////////////////
	
	private class Triples
	{
		private ArrayList<Point2D> listPoint;
		private ArrayList<IAmoeba> listAmoeba;
		
		public Triples()
		{
			this.listPoint=new ArrayList<Point2D>();
			this.listAmoeba=new ArrayList<IAmoeba>();
		}
		
		public void addTriple(int x, int y, IAmoeba amoeba)
		{
			Point2D pair=new Point2D.Double(x, y);
			
			this.listPoint.add(pair);
			
			this.listAmoeba.add(amoeba);
		}
		
		public int getXOfTriple(int index)
		{
			return (int) this.listPoint.get(index).getX();
		}
		
		public int getYOfTriple(int index)
		{
			return (int) this.listPoint.get(index).getY();
		}
		
		public IAmoeba getAmoebaOfTriple(int index)
		{
			return this.listAmoeba.get(index);
		}
		
		public void removeTriple(IAmoeba amoeba)
		{
			int index=this.listAmoeba.indexOf(amoeba);
			this.listAmoeba.remove(index);
			this.listPoint.remove(index);
		}
		
		@OnlyForTesting
		public void showList()
		{
			System.out.println("LIST: ");
			for(int i=0; i<this.listAmoeba.size();i++)
			{
				System.out.println(" AMOEBA: " + this.listAmoeba.get(i) + "  " + this.listPoint.get(i));
			}
		}
		
		public int getXOfTriple(IAmoeba amoeba)
		{
			int index = this.listAmoeba.indexOf(amoeba);
			return this.getXOfTriple(index);
		}
		
		public int getYOfTriple(IAmoeba amoeba)
		{
			int index = this.listAmoeba.indexOf(amoeba);
			return this.getYOfTriple(index);
		}
	}
	
}


