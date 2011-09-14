package gameObjectsASCII;

import interfaces.IModule;
import helper.LanguagePack;
import helper.UserInput;
import logics.PhaseALogic;
import logics.PhaseMainMenuLogic;

public class PhaseBreakMenuASCII extends PhaseMainMenuLogic
{
	public PhaseBreakMenuASCII(IModule module) 
	{
		super(module);
	}


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
			this.outStream.println(LanguagePack.getTranslation("phaseBreakMenuTitle"));	
		}
		

	///////////
	//ACTIONS//
	///////////
	

	////////////
	//ACTION A//
	////////////
	
//	@Override
//	public void doPreActionA()
//	{
//		this.outStream.println();
//	}
//	
//	@Override
//	public void actionAInput()
//	{
//		String strMenu=this.buildMenu();
//		
//		String name=UserInput.readInput(this.rb.getString("mainMenuInstruction") + "\n" + strMenu);
//		this.setInputA(name);
//	}
//	
//	@Override
//	public void doAfterActionA()
//	{
//		this.outStream.println("Thank you for your name");
//	}
//	
	///////////
	//PRIVATE//
	///////////
	/**
	 * Builds the hole menu in ASCII 
	 * @return
	 */
	private String buildMenu() 
	{
		this.addEntry(1,LanguagePack.getTranslation("phaseMainMenuNewGame"));
		this.addEntry(2,LanguagePack.getTranslation("phaseMainMenuLoadGame"));
		this.addEntry(3,LanguagePack.getTranslation("phaseMainMenuOptions"));
		this.addEntry(4,LanguagePack.getTranslation("phaseMainMenuHelp"));
		this.addEntry(5,LanguagePack.getTranslation("phaseMainMenuCheats"));
		this.addEntry(6,LanguagePack.getTranslation("phaseMainMenuAchievements"));
		this.addEntry(7,LanguagePack.getTranslation("phaseMainMenuStatistics"));
		this.addEntry(8,LanguagePack.getTranslation("phaseMainMenuAbout"));
		this.addEntry(9,LanguagePack.getTranslation("phaseMainMenuExit"));
		return this.strMenu;
	}


	private void addEntry(int entryNr, String title) 
	{
		this.strMenu=this.strMenu+"\t - ( " +entryNr +" )" + "\t " + title + "\n";
		
	}

	
	
}
