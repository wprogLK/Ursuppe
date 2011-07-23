package enums;

/*DR mhm it's a bit of a mess, but it's not a bad idea to make the genes enums,
 * although I don't like it that your enums have so much informations.
 * It would be better to have some sort of GeneBank who's responsible for checking how much genes are available and so on.
 * Currently (I think) your enums are directly responsible for this?
 * 
 * LK:  Yes, our genes are responsible for checking how much genes are available, so it is more simpler than with another class.
 * 		Why do you think 6 parameters are too much for an enum?
 */
public enum GameGene {
	/////////
	//ENUM://
	/////////
	TestGene("TestGene", "This is only a testGene", 1,4,0,2),
	Intelligence("Intelligence", "Totaly useless", 1,2,3,1),
	Sporen("Sporen", "Bei der Zellteilung in Phase 4, freie Platzwahl", 1,3,3,1),
	Strahlenschutz("Schrahlenschutz" , "Schützt in Phase 2 doppelt gegen Genschaden. 1. Durch seine negative Empfindlichkeit. 2. Zusätzlich zahlt der Strahlenschutz im Fall von Gendefekten eine Differenz von 4 ab, wenn er abgegeben wird. Für die Fortschrittsberechnung zählt dieses Gen nicht!",0,5,-2,1),
	Stromlienenform("Stromlinienform", "Bewegung und Zappeln kosten keine Biopunkte mehr", 1, 5, 4,1),
	Lebenserwartung("Lebenserwartung" , "Amöbe stirbt erst mit 3 DamagePoints.",1,5,5,1),
	Teilungsrate("Teilungsrate" , "Zellteilung kostet nur noch 4 BioPunkte pro Teilung", 1,6,5,1),
	PortalGene("PortalGene", "Wenn eine Amöbe auf ein Hinderniss stösst kommt sie am Rand, entgegengesetzt ihrer Bewegungsrichtung, der Ursuppe wieder raus. Zählt als 2 Gene", 2, 6, 6,1),
	Wetterfrosch("WetterFrosch", "Diese Gen kann einmal gebraucht werden in Phase 1. Gen ermöglicht Windrichtung für alle sich auf dem Bord befindenten Amöben nach Wunsch zu ändern", 1, 4, 5,1);
	
	
	private String name;
	private String description;
	private int score;
	private int price;
	private int ozoneValue;
	private int nrOfGenes;
	private int availableNrOfGenes;
	
	GameGene(String name, String description, int score, int price, int ozoneValue, int nrOfGenes)
	{
		this.name=name;
		this.description=description;
		this.score=score;
		this.price=price;
		this.ozoneValue=ozoneValue;
		this.nrOfGenes=nrOfGenes;
		this.availableNrOfGenes=nrOfGenes;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public int getScore()
	{
		return this.score;
	}
	
	public int getPrice()
	{
		return this.price;
	}
	
	public int getOzoneValue()
	{
		return this.ozoneValue;
	}
	
	public void buyGene()
	{
		this.availableNrOfGenes--;
	}
	
	public void sellGene()
	{
		this.availableNrOfGenes++;
	}
	
	public int getAvailableNrOfGenes()
	{
		return this.availableNrOfGenes;
	}
	
	public String toString()
	{
	
		return this.name ; 
	}
	
}


