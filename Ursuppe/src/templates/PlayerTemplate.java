package templates;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;

import enums.EColor;
import enums.EPlayer;
import interfaces.IPlayer;
import interfaces.IAmoeba;
/**
 * @author Lukas Keller
 * @version 1.0.0
 *
 */
public abstract class PlayerTemplate implements IPlayer, Serializable
{
	protected ArrayList<IAmoeba> amoebasOffBoard=new ArrayList<IAmoeba>();
	protected ArrayList<IAmoeba> amoebasOnBoard=new ArrayList<IAmoeba>();
	
	protected String name;
	protected int age;
	protected Date birthday;
	protected EColor color;
	protected EPlayer eType;
	protected int score;
	/**
	 * is the default constructor
	 * 
	 * </br> name will be {@code [SubjectName]}
	 * </br> age will be {@code 0}
	 * </br> color will be {@link EColor#Default}
	 */
	public PlayerTemplate()
	{
		this.setName("[SubjectName]");
		this.setAge(0);
		this.setColor(EColor.Default);
		this.score=-1;
	}
	
	/**
	 * is the concrete constructor for a new player
	 * @param name
	 * @param age
	 * @param color
	 */
	public PlayerTemplate(String name, Date birthday,int age, EColor color)
	{
		this.setName(name);
		this.setAge(age);
		this.setColor(color);
		this.setBirthday(birthday);
		this.score=-1;
	}
	
	
	///////////
	//AMOEBAS//
	///////////
	
	@Override 
	public void takeAmoebaOffTheBoard(IAmoeba amoeba)
	{
		assert this.amoebasOnBoard.contains(amoeba);
		assert !this.amoebasOffBoard.contains(amoeba);
		
		this.amoebasOnBoard.remove(amoeba);
		this.amoebasOffBoard.add(amoeba);
	}
	
	@Override 
	public void putAmoebaOnTheBoard(IAmoeba amoeba)
	{
		assert this.amoebasOnBoard.contains(amoeba);
		assert !this.amoebasOffBoard.contains(amoeba);
		
		this.amoebasOnBoard.remove(amoeba);
		this.amoebasOffBoard.add(amoeba);
	}
	
	
	
	

	///////////
	//SETTERS//
	///////////
	@Override
	public void setAmoeba(ArrayList<IAmoeba> amoebas)
	{
		this.amoebasOffBoard=amoebas;
		this.amoebasOnBoard.clear();
	}
	
	@Override
	public void setType(EPlayer eType)
	{
		this.eType=eType;
	}
	
	@Override
	public void setName(String name) 
	{
		this.name=name;
	}

	@Override
	public void setAge(int age) 
	{
		this.age=age;
	}
	
	@Override
	public void setColor(EColor color)
	{
		this.color=color;
		this.updateAmoebaColor();
	}
	
	@Override
	public void setBirthday(Date birthday) 
	{
		this.birthday=birthday;
	}
	
	@Override
	public void setScore(int value)
	{
		this.score=value;
	}

	///////////
	//GETTERS//
	///////////
	@Override
	public ArrayList<IAmoeba> getAmoebasOffBoard()
	{
		return this.amoebasOffBoard;
	}
	
	@Override
	public ArrayList<IAmoeba> getAmoebasOnBoard()
	{
		return this.amoebasOnBoard;
	}
	
	
	@Override
	public int getScore()
	{
		return this.score;
	}
	
	@Override
	public String getName() 
	{
		return this.name;
	}

	@Override
	public int getAge() 
	{
		return this.age;
	}
	
	@Override
	public EColor getColor()
	{
		return this.color;
	}
	
	@Override
	public EPlayer getType()
	{
		return this.eType;
	}
	
	///////////////////
	//PRIVATE METHODS//
	///////////////////
	
	private void updateAmoebaColor()
	{
		for(IAmoeba amoeba:this.amoebasOffBoard)
		{
			amoeba.setColor(this.color);
		}
		
		for(IAmoeba amoeba:this.amoebasOnBoard)
		{
			amoeba.setColor(this.color);
		}
	}
}
