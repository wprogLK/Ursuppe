package gameObjectsASCII;

import interfaces.IModule;

import java.util.ArrayList;

import enums.EColor;
import enums.EToken;
import exceptions.InputException;

import helper.LanguagePack;
import helper.ReadAndWriteFiles;
import helper.UserInput;
import logics.PhaseALogic;
import logics.PhaseNewGameLogic;
/**
 * This is the whole phase new game for the ASCII game
 * @author Lukas Keller
 * @version 1.0.0
 */
public class PhaseNewGameASCII extends PhaseNewGameLogic
{
	/////////////
	//BASICS...//
	/////////////
		////////////
		//...LOGIC//
		////////////
	
		public PhaseNewGameASCII(IModule module) 
		{
			super(module);
		}

		@Override
		public void doPreActionFirstRun()
		{
			this.outStream.println(LanguagePack.getTranslation("phaseNewGameTitle"));
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
		this.outStream.println(LanguagePack.getTranslation("phaseNewGameAddAPlayerOrPlay"));
	}
	
	@Override
	public void actionAInput()
	{
		String input=UserInput.readInput(LanguagePack.getTranslation("newGameAddOrPlay"));
		
		try
		{
			this.setInputA(input);
		} 
		catch (Exception e)
		{
			//TODO
			System.out.println(e.getMessage());
		}
	}
	
	////////////
	//ACTION B//
	////////////
	
	@Override
	public void doPreActionB()
	{
		this.outStream.println(LanguagePack.getTranslation("phaseNewGameLoadOrCreatePlayer"));
	}
	
	@Override
	public void actionBInput()
	{
		String input=UserInput.readInput(LanguagePack.getTranslation("newGameLoadOrCreatePlayer"));
		try
		{
			this.setInputB(input);
		}
		catch (Exception e)
		{
			//TODO
			System.out.println(e.getMessage());
		}
		
	}
	
	////////////
	//ACTION C//
	////////////
	
	@Override
	public void doPreActionC()
	{
		this.outStream.println(LanguagePack.getTranslation("phaseNewGameLoadAPlayer"));
	}
	
	@Override
	public void actionCInput()
	{
		String message=this.prepareStringForLoadPlayers(this.arrayHumanPlayers, this.arrayAIPlayers);
		
		String input=UserInput.readInput(LanguagePack.getTranslation("newGameLoadInstruction") + "\n" + message);
		try
		{
			this.setInputC(input);
		} 
		catch (Exception e)
		{
			//TODO
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void doAfterActionC()
	{
		this.outStream.println(LanguagePack.getTranslation("newGameSuccessfulLoad"));
	}
	
	////////////
	//ACTION D//
	////////////
	
	@Override
	public void doPreActionD()
	{
		this.outStream.println(LanguagePack.getTranslation("phaseNewGameCreatePlayer"));
	}
	
	@Override
	public void actionDInput()
	{
		String input=UserInput.readInput(LanguagePack.getTranslation("newGameNameInstruction"));
		try
		{
			this.setInputD(input);
		} 
		catch (Exception e)
		{
			//TODO
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void doAfterActionD()
	{
		this.outStream.println(LanguagePack.getTranslation("newGameSuccessfulName") + this.nameOfNewPlayer);
	}
	
	////////////
	//ACTION E/
	////////////
	
	@Override
	public void actionEInput()
	{
		String input=UserInput.readInput(LanguagePack.getTranslation("newGameAgeInstructuion"));
		try
		{
			this.setInputE(input);
		} 
		catch (Exception e)
		{
			//TODO
			System.out.println(e.getMessage());
		}
	}
	
	////////////
	//ACTION F/
	////////////
	
	@Override
	public void actionFInput()
	{
		String strList=this.createStringOfColors();
		
		String input=UserInput.readInput(LanguagePack.getTranslation("newGameColorInstruction") +"\n"+ strList);
		try
		{
			this.setInputF(input);
		} 
		catch (Exception e)
		{
			//TODO
			System.out.println(e.getMessage());
		}
	}
	
	///////////////////
	//PRIVATE METHODS//
	///////////////////
	
	/**
	 * creates a string of all possible EColors
	 * @return string with a list of all colors
	 */
	private String createStringOfColors() 
	{
		String list="";
		
		for(EColor color: EColor.class.getEnumConstants())
		{
			list+=color +"\n";
		}
		
		return list;
	}
	
	/**
	 * Prepare the human and AI players as a string
	 * @param arrHumanPlayers
	 * @param arrAIPlayers
	 * @return a string which includes a list of all AI and human players with their token and an index
	 */
	private String prepareStringForLoadPlayers(ArrayList<String> arrHumanPlayers, ArrayList<String> arrAIPlayers)
	{
		int nrHumanPlayers=0;
		int nrAIPlayers=0;
	
		String output="";
		String strHumanPlayers="Human players: \n";
		String strAIPlayers="Artifical intelligence players: \n";
		
		for(String strHuman:arrHumanPlayers)
		{
			strHumanPlayers=strHumanPlayers+ " ( H" + nrHumanPlayers + " ) " + "\t" + strHuman + "\n";
			nrHumanPlayers++;
		}
		
		for(String strAI:arrAIPlayers)
		{
			strAIPlayers=strAIPlayers+ " ( A" + nrAIPlayers + " ) " + "\t" + strAI + "\n";
			nrHumanPlayers++;
		}
		
		
		output=strHumanPlayers+strAIPlayers;
		
		return output;
	}
}
