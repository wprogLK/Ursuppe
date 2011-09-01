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
			String firstLine="";
			String secondLine="";
			String thirdLine="";
			
			for(int y=0; y<this.soupBoard.length-1;y++)
			{
				ISoupSquare square=this.soupBoard[x][y];
				
				if(square==null)
				{
					firstLine+="NULL1";
					secondLine+="NULL2";
					thirdLine+="NULL3";
				}
				else if(x==this.compassSquare.getPosX() && y==this.compassSquare.getPosY())
				{
					firstLine+=this.compassSquare.getLineNumber(1);
					secondLine+=this.compassSquare.getLineNumber(2);
					thirdLine+=this.compassSquare.getLineNumber(3);
				}
				else
				{
					firstLine+=square.getLineNumber(1);
					secondLine+=square.getLineNumber(2);
					thirdLine+=square.getLineNumber(3);
				}
			}
			str+=firstLine+"\n" +secondLine +"\n" + thirdLine + "\n";
			//str+="\n";
		}
		
		return str;
		
		
		
		
//		String str="";
//		
//		for(int x=0; x<this.soupBoard.length-1;x++)
//		{
//			for(int y=0; y<this.soupBoard.length-1;y++)
//			{
//				ISoupSquare square=this.soupBoard[x][y];
//				
//				if(square==null)
//				{
//					str+="NULL\t";
//				}
//				else if(x==this.compassSquare.getPosX() && y==this.compassSquare.getPosY())
//				{
//					str+=this.compassSquare.toString();
//				}
//				else
//				{
//					str+=square.toString()+"\t";
//				}
//				
//			
//			}
//			str+="\n";
//		}
//		
//		return str;
	}

}
