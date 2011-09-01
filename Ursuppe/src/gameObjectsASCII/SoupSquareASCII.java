/**
 * 
 */
package gameObjectsASCII;

import java.util.ArrayList;

import enums.EColor;

import helper.Setting;
import logics.SoupSquareLogic;

/**
 * @author Lukas
 *
 */
public class SoupSquareASCII extends SoupSquareLogic{

	/**
	 * 
	 */
	public SoupSquareASCII() {
		// TODO Auto-generated constructor stub
	
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
		switch(nr)
		{
			case 1:
			{
				return this.getLineOne();
			}
			case 2:
			{
				return this.getLineTwo();
			}
			case 3:
			{
				return this.getLineThree();
			}
			default:
			{
				//TODO Exception
				return "";
			}
		}
	}
	
	
	///////////////////
	//PRIVATE METHODS//
	///////////////////
	
	private String getLineOne()
	{
		
		
		String format=this.prepareStringForLineOne();
		
		System.out.println("FORMAT: " + format);
		
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
			str+=color.getBigBegin()+": %s  ";
		}
		
		str+="||  ";
		
		return str;
		
	}



	private void getAllNumbersOfAmebas(ArrayList<Integer> numbersOfAmoebas) {
		for(EColor color:Setting.usedColors)
		{
			int number=this.getAmoebaNumberWithColor(color);
			System.out.println("Color " + color + "NUMBER " + number);
			numbersOfAmoebas.add(number);
			
		}
		
		System.out.println("size: " + numbersOfAmoebas.size());
	}
	
	private String getLineTwo()
	{
		return "soupSquare";
	}
	
	private String getLineThree()
	{
		String format=this.prepareStringForLineThree();
		
		System.out.println("FORMAT 3: " + format);
		
		ArrayList<Integer> numbersOfFoods=new ArrayList<Integer>();
		
		this.getAllNumbersOfFoods(numbersOfFoods);
		
		Object[] a=numbersOfFoods.toArray();
		
		String lineThree=String.format(format, a);//numbersOfAmoebas);
		
		
		return lineThree;
	}

	private String prepareStringForLineThree() {
		String str="";
		
		for(EColor color:Setting.usedColors)
		{
			str+=color.getSmallBegin()+": %s  ";
		}
		
		str+="||  ";
		
		return str;
		
	}


	private void getAllNumbersOfFoods(ArrayList<Integer> numbersOfFoods) {
		for(EColor color:Setting.usedColors)
		{
			int number=this.getFoodNumberWithColor(color);
			System.out.println("Color " + color + "NUMBER " + number);
			numbersOfFoods.add(number);
			
		}
		
		System.out.println("size: " + numbersOfFoods.size());
		
	}

}
