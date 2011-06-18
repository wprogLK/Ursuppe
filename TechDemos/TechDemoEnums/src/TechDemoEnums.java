import java.util.ArrayList;
import java.util.Iterator;


public class TechDemoEnums {
	private int configNr;
	
	private ArrayList<Player> playersConfig1=new ArrayList<Player>();
	private ArrayList<Player> playersConfig2=new ArrayList<Player>();
	
	private ArrayList<EColor> colorsConfig1=new ArrayList<EColor>();
	private ArrayList<EColor> colorsConfig2=new ArrayList<EColor>();
	
	public TechDemoEnums()
	{
		this.colorsConfig1.add(EColor.Default);	//HEAD
		this.colorsConfig1.add(EColor.Default);	//TAIL
		
		this.colorsConfig2.add(EColor.Default);	//HEAD
		this.colorsConfig2.add(EColor.Default);	//TAIL
	}
	
	public void setConfigNr(int configNR)
	{
		this.configNr=configNR;
	}
	
	private void addColor(EColor color)
	{
		if(this.configNr==1)
		{
			assert(!this.colorsConfig1.contains(color));
		}
		else if(this.configNr==2)
		{
			assert(!this.colorsConfig2.contains(color));
		}
		
		
		this.addColorAtEnd(color);
		
	}
	
	private void addColorAtEnd(EColor color)
	{
		if(this.configNr==1)
		{
			assert(this.colorsConfig1.size()>=2);
			
			int endIndex=this.colorsConfig1.size()-1;
			
			this.colorsConfig1.add(endIndex, color);
			
			System.out.println("New COLOR LIST CONFIG 1 IS NOW: " +this.colorsConfig1);
		}
		else if(this.configNr==2)
		{
			assert(this.colorsConfig2.size()>=2);
			
			int endIndex=this.colorsConfig2.size()-1;
			
			this.colorsConfig2.add(endIndex, color);
			
			System.out.println("New COLOR LIST CONFIG 2 IS NOW: " +this.colorsConfig2);
		}
		
		
	}
	
	public void config1() 
	{
		this.configNr=1;
		
		assert(this.playersConfig1.isEmpty());
		
		this.createNewPlayer("Player One", EColor.Blue);
		
		EColor.Blue.getPlayer().setName("NEW Player One");
		
	}

	public void showDefault()
	{
		System.out.println("CONFIGURATION DEFAULT: ");
		
		System.out.println("-------------------------");
		
			
		System.out.println("EColor.Blue.getPlayer(): " +EColor.Blue.getPlayer().toString());
		System.out.println("EColor.Red.getPlayer(): " +EColor.Red.getPlayer().toString());
		System.out.println("EColor.Yellow.getPlayer(): " +EColor.Yellow.getPlayer().toString());
			
		System.out.println();

		
		System.out.println();
	}
	
	public void show() 
	{
		
		System.out.println("CONFIGURATION: " + this.configNr);
		
		System.out.println("-------------------------");
		
		System.out.println();
		
		System.out.println("EColor.Blue.getPlayer(): " +EColor.Blue.getPlayer().toString());
		System.out.println("EColor.Red.getPlayer(): " +EColor.Red.getPlayer().toString());
		System.out.println("EColor.Yellow.getPlayer(): " +EColor.Yellow.getPlayer().toString());
		
		
	}
	
	public void showWithIterator()
	{
		System.out.println("CONFIG NR " +this.configNr);
		Iterator<EColor> itr = null;
		
		if (this.configNr==1)
		{
			itr=this.colorsConfig1.iterator();
		}
		else if(this.configNr==2)
		{
			itr=this.colorsConfig2.iterator();
		}
		
		while(itr.hasNext())
		{
			EColor currentColor=itr.next();
			System.out.println("CURRENT COLOR is " + currentColor + " Data is: " +currentColor.getPlayer().toString());
		}
		
		System.out.println();
//		Iterator itr = al.iterator(); 
//		while(itr.hasNext()) {
//
//		    Object element = itr.next(); 
//		    System.out.print(element + " ");
//
//		} 
	}

	public void config2() 
	{
		this.configNr=2;
		
		assert(this.playersConfig2.isEmpty());
		
		this.createNewPlayer("Player One", EColor.Blue);
		this.createNewPlayer("Player Two", EColor.Red);
		
	}
	
	private void createNewPlayer(String name, EColor color)
	{
		Player player=new Player(name,color);
		
		color.setPlayer(player);
		
		this.addColor(color);
		
		switch(this.configNr)
		{
			case 1:
			{
				this.playersConfig1.add(player);
				break;
			}
			case 2:
			{
				this.playersConfig2.add(player);
				break;
			}
			default:
			{
				System.out.println("-----------------------------ERROR---------------------");
			}
		}
	}

	public void showCurrentPlayer() {
		System.out.println();
		
	}

}
