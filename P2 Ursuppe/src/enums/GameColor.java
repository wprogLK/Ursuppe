package enums;


public enum GameColor {
	red("amebaR", "foodR.jpg", "playerR.jpg",30), yellow("amebaY", "foodY.jpg", "playerY.jpg",15), blue("amebaB", "foodB.jpg", "playerB.jpg",0), empty("amebaE", "foodB" , "playerE.gif", 0); //TODO create a real empty!
	
	private String fileNameAmeba;
	private String fileNameFood;
	private String fileNamePlayer;
	private int y;
	
	GameColor(String fileNameAmeba, String fileNameFood, String fileNamePlayer,int y)
	{
		this.fileNameAmeba=fileNameAmeba;
		this.fileNameFood=fileNameFood;
		this.fileNamePlayer=fileNamePlayer;
		this.y=y;
	}
	
	public String getFileNameAmeba()
	{
		return this.fileNameAmeba;
	}
	
	public String getFileNameFood()
	{
		return this.fileNameFood;
	}
	
	public String getFileNamePlayer()
	{
		return this.fileNamePlayer;
	}
	
	public int getY()
	{
		return this.y;
	}
}
