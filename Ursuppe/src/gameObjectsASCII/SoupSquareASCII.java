/**
 * 
 */
package gameObjectsASCII;

import java.util.ArrayList;

import enums.EColor;

import helper.Setting;
import logics.SoupSquareLogic;

/**
 * @author Lukas Keller
 * @version 1.0.0
 */
public class SoupSquareASCII extends SoupSquareLogic{

	/**
	 * 
	 */
	public SoupSquareASCII() 
	{
		//TODO ?
	}
	
	/////////////
	//TO STRING//
	/////////////
	
	public String toString()
	{
		return "SoupSquare";
	}
	
	public String getLineNumber(int nr)
	{
		String line="";
		
		switch(nr)
		{
			case 1:
			{
				line=this.getLineOne();
				break;
			}
			case 2:
			{
				line=this.getLineTwo();
				break;
			}
			case 3:
			{
				line=this.getLineThree();
				break;
			}
			default:
			{
				//TODO Exception
				line="";
				break;
			}
		}
		
		return this.normalSquare(line);
	}
	
	
	///////////////////
	//PRIVATE METHODS//
	///////////////////
	
	private String getLineOne()
	{
		String format=this.prepareStringForLineOne();
		
//		System.out.println("FORMAT: " + format); //TODO DELETE
		
		ArrayList<Integer> numbersOfAmoebas=new ArrayList<Integer>();
		
		this.getAllNumbersOfAmebas(numbersOfAmoebas);
		
		Object[] a=numbersOfAmoebas.toArray();
		
		String lineOne=String.format(format, a);//numbersOfAmoebas);
		
		return lineOne;
	}
	
	private String prepareStringForLineOne() {
		String str="";
		
		for(EColor color:Setting.usedColors)
		{
			str+=" "+color.getBigBegin()+": %s ";
		}
		
		return str;
		
	}

	private void getAllNumbersOfAmebas(ArrayList<Integer> numbersOfAmoebas) 
	{
		for(EColor color:Setting.usedColors)
		{
			int number=this.getAmoebaNumberWithColor(color);
			numbersOfAmoebas.add(number);
		}
	}
	
	private String getLineTwo()
	{
		return "soupSquare";
	}
	
	private String getLineThree()
	{
		String format=this.prepareStringForLineThree();
		
		ArrayList<Integer> numbersOfFoods=new ArrayList<Integer>();
		
		this.getAllNumbersOfFoods(numbersOfFoods);
		
		Object[] array=numbersOfFoods.toArray();
		
		String lineThree=String.format(format, array);//numbersOfAmoebas);
		
		return lineThree;
	}

	private String prepareStringForLineThree() {
		String str="";
		
		for(EColor color:Setting.usedColors)
		{
			str+=" "+color.getSmallBegin()+": %s ";
		}
		
		return str;
	}


	private void getAllNumbersOfFoods(ArrayList<Integer> numbersOfFoods) {
		for(EColor color:Setting.usedColors)
		{
			int number=this.getFoodNumberWithColor(color);
			//System.out.println("Color " + color + "NUMBER " + number); //TODO DELETE
			numbersOfFoods.add(number);
		}
	}

}
