/**
 * 
 */
package logics;

import java.io.PrintStream;

import interfaces.IAmoeba;
import interfaces.IBoard;
import interfaces.ICompassSquare;
import interfaces.IModule;
import interfaces.IPlayer;
import interfaces.ISoupSquare;

/**
 * @author Lukas
 *
 */
public class BoardLogic implements IBoard
{
	/*
	 * 	[0][0]	[0][1]	[0][2]
	 * 	[1][0]	[1][1]	[1][2]
	 * 	[2][0]	[2][1]	[2][2]
	 */
	protected IModule module;
	
	protected PrintStream outStream;
	protected PrintStream errorStream;
	
	protected ISoupSquare[][] soupBoard;
	protected ICompassSquare compassSquare;
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


	private void buildOriginalBoard() 
	{
		this.buildSoupSquares();
		
		this.buildCompassSquare();
	}


	private void buildCompassSquare() 
	{
		this.compassSquare=this.module.createCompassSquare();
		
		this.setCompassSquare(compassSquare, 2, 2);
	}


	private void buildSoupSquares() 
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


	private void buildEmptyBoard(int maxX, int maxY) 
	{
		this.soupBoard=new ISoupSquare[maxX][maxY];
	}


	@Override
	public void addSquare(ISoupSquare square, int x, int y) 
	{
		boolean isEmptySquare=this.isEmptySquare(x,y);
		assert(isEmptySquare);
		
		this.soupBoard[x][y]=square;
		
		this.configSquare(x,y,square);
	}

	private boolean isEmptySquare(int x, int y) 
	{
		boolean valid=true;
		
		boolean validX=(x>=0) && (x<this.soupBoard.length-1);
		boolean validY=(y>=0) && (y<this.soupBoard[0].length-1);
		
		assert(validX && validY);
		
		boolean nullSquare=(this.soupBoard[x][y]==null);
		
		valid=validX && validY && nullSquare;
		
		return valid;
	}


	private void configSquare(int x, int y, ISoupSquare square) 
	{
		//TODO CHECK THIS!
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


	@Override
	public ISoupSquare getSquare(int x, int y) 
	{
		return this.soupBoard[x][y];
	}


	@Override
	public ICompassSquare getCompassSquare() 
	{
		return this.compassSquare;
	}

	public void setCompassSquare(ICompassSquare compass,int x, int y)
	{
		this.compassSquare=compass;
		this.compassSquare.setPosition(x, y);
	}

	
	@Override
	public void addPlayer(IPlayer player) 
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void testAddAmeba() {
		IAmoeba amoeba=this.module.createAmoeba();
		
		this.soupBoard[1][1].addAmoeba(amoeba);
		
	}


	




	
	
}
