
public enum EColor 
{
	Default,
	Red,
	Blue,
	Yellow;

	
	private Player player;
	
	public void setPlayer(Player player)
	{
		this.player=player;
	}
	
	public Player getPlayer()
	{
		return this.player;
	}
	
	private EColor()
	{
		
		this.player=new DefaultPlayer();
		//this.player=new Player("Default", this);
	}
}
