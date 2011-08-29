/**
 * 
 */
package gameObjectsASCII;

import java.io.PrintStream;

import interfaces.IModule;
import interfaces.ISoupSquare;
import interfaces.ISquare;
import logics.BoardLogic;
import module.ModuleASCII;

/**
 * @author Lukas
 *
 */
public class BoardASCII extends BoardLogic{

	/**
	 * 
	 */
	public BoardASCII(PrintStream out, PrintStream error) 
	{
		super(out,error,new ModuleASCII(out,error));
	}
	
	public String toString()
	{
		String str="";
		
		for(int x=0; x<this.soupBoard.length-1;x++)
		{
			for(int y=0; y<this.soupBoard.length-1;y++)
			{
				ISoupSquare square=this.soupBoard[x][y];
				
				if(square==null)
				{
					str+="NULL\t";
				}
				else if(x==this.compassSquare.getPosX() && y==this.compassSquare.getPosY())
				{
					str+=this.compassSquare.toString();
				}
				else
				{
					str+=square.toString()+"\t";
				}
				
			
			}
			str+="\n";
		}
		
		return str;
	}

}
