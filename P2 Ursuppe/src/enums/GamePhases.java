package enums;

public enum GamePhases {
	/////////
	//ENUM://
	/////////
	
	phase6("Score", "[HERE Description of phase 6]", GameReadDirection.down, null,2000,2000,true), 
	phase5("Death", "[HERE Description of phase 5]", GameReadDirection.down, phase6,2000,2000,true), 
	phase4("Cell division", "[HERE Description of phase 4]", GameReadDirection.down, phase5,2000,2000,true),
	phase3("Buy genes", "[HERE Description of phase 3]", GameReadDirection.down, phase4,2000,2000,true), 
	phase2("Enviroments and genedefects", "[HERE Description of phase 2]", GameReadDirection.down, phase3,2000,2000,true), 
	phase1("Move/Drift and Eat", "[HERE Description of phase 1]", GameReadDirection.up, phase2,2000,2000,true), 
	phaseSetFirstAmeba("Set first two amebas", "[HERE Description of phaseSetFirstAmeba]", GameReadDirection.up,phase1,1900,1200,true),
	phase0("Prepare", "[HERE Description of phase 0]", GameReadDirection.up, phaseSetFirstAmeba,1700,300,true), 
	phaseSetNames("Set players name", "[HERE Description of phase set players name]", GameReadDirection.up, phase0,1000,300,true), 
	phaseEmpty("Empty", "This phase is empty", GameReadDirection.up,null,0,0,false),
	phaseAbout("About", "[HERE Description of phase about]", GameReadDirection.up, phase0,1000,480,false),
	phaseCheat("Cheat", "Cheat what you want!",GameReadDirection.up,phase0,1050,980,false),
	phaseWelcome("Welcome","Welcome to Ursuppe!",GameReadDirection.up,phase0,1000,500,false);
	
	private String title;
	private String description;
	private GameReadDirection readDirection;
	private GamePhases nextPhase;
	private int windowsX;
	private int windowsY;
	private boolean showMenu;
	
	GamePhases(String title, String description, GameReadDirection readDirection, GamePhases nextPhase, int windowsX, int windowsY, boolean showMenu)
	{
		this.title=title;
		this.description=description;
		this.readDirection=readDirection;

		this.nextPhase=nextPhase;
		
		this.windowsX=windowsX;
		this.windowsY=windowsY;
		
		this.showMenu=showMenu;
		
		
	}
	
	//////////////////////
	//GETTERS & SETTERS://
	//////////////////////
	
		////////////
		//GETTERS://
		////////////
		public boolean getShowMenu()
		{
			return this.showMenu;
		}
	
	
		public String getTitle()
		{
			return this.title;
		}
		
		public String getDescription()
		{
			return this.description;
		}
		
		public GameReadDirection getReadDirection()
		{
			return this.readDirection;
		}
		
		public GamePhases getNextPhase()
		{
			if (nextPhase==null)
			{
				return phase0;
			}
			else
			{
				return this.nextPhase;
			}
		}
		
		public int getWindowsX()
		{
			return this.windowsX;
		}
		
		public int getWindowsY()
		{
			return this.windowsY;
		}
		
		////////////
		//SETTERS://
		///////////
		
		public void setShowMenu(boolean value)
		{
			this.showMenu=value;
		}
}


