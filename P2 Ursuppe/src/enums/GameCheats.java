package enums;

public enum GameCheats {	//used if a phase has more than one part
	myNameIs("myNameIs [color] [name]" , "Change the name of player with [color] into [name]"),
	readCheatBook("readCheatBook ", "Show all cheats of the game");
	
	private String description;
	private String instruction;
	
	

	
	
	GameCheats(String instruction,String description)
	{
		this.description=description;
		this.instruction=instruction;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public String getInstruction()
	{
		return this.instruction;
	}
	
	public String getInfo()
	{
		return "\n " + this.instruction + "  \t \t " + this.description;
	}
}
