package gameObjectsASCII;

import helper.UserInput;
import logics.PhaseALogic;
import logics.PhaseMainMenuLogic;

public class PhaseMainMenuASCII extends PhaseMainMenuLogic
{
	String strMenu="";
	
	/////////////
	//BASICS...//
	/////////////
		////////////
		//...LOGIC//
		////////////
	
		@Override
		public void doPreAction()
		{
			this.outStream.println(this.rb.getString("phaseMainMenuTitle"));
		}
		

	///////////
	//ACTIONS//
	///////////
	

	////////////
	//ACTION A//
	////////////
	
	@Override
	public void doPreActionA()
	{
		System.out.println();
	}
	
	@Override
	public void actionAInput()
	{
		String strMenu=this.buildMenu();
		
		String name=UserInput.readInput(this.rb.getString("mainMenuInstruction") + "\n" + strMenu);
		this.setInputA(name);
	}
	
	


	@Override
	public void doAfterActionA()
	{
		this.outStream.println("Thank you for your name");
	}
	
	///////////
	//PRIVATE//
	///////////
	/**
	 * Builds the hole menu in ASCII 
	 * @return
	 */
	private String buildMenu() 
	{
		this.addEntry(1,this.rb.getString("phaseMainMenuNewGame"));
		this.addEntry(2,this.rb.getString("phaseMainMenuLoadGame"));
		this.addEntry(3,this.rb.getString("phaseMainMenuOptions"));
		this.addEntry(4,this.rb.getString("phaseMainMenuHelp"));
		this.addEntry(5,this.rb.getString("phaseMainMenuCheats"));
		this.addEntry(6,this.rb.getString("phaseMainMenuAchievements"));
		this.addEntry(7,this.rb.getString("phaseMainMenuStatistics"));
		this.addEntry(8,this.rb.getString("phaseMainMenuAbout"));
		this.addEntry(9,this.rb.getString("phaseMainMenuExit"));
		return this.strMenu;
	}


	private void addEntry(int entryNr, String title) 
	{
		this.strMenu=this.strMenu+"\t - ( " +entryNr +" )" + "\t " + title + "\n";
		
	}

	
	
}
