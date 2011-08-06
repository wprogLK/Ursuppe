package gameObjectsASCII;

import java.util.ArrayList;

import enums.EToken;

import helper.ReadAndWriteFiles;
import helper.UserInput;
import logics.PhaseALogic;
import logics.PhaseNewGameLogic;

public class PhaseNewGameASCII extends PhaseNewGameLogic
{

	
	/////////////
	//BASICS...//
	/////////////
		////////////
		//...LOGIC//
		////////////
	
		@Override
		public void doPreAction()
		{
			System.out.println(this.rb.getString("phaseNewGameTitle"));
		}
		
		@Override
		public void doAfterAction()
		{

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
		System.out.println(this.rb.getString("phaseNewGameAddAPlayerOrPlay"));
	}
	
	@Override
	public void actionAInput()
	{
		String input=UserInput.readInput(this.rb.getString("newGameAddOrPlay"));
		this.setInputA(input);
	}
	
	@Override
	public void doAfterActionA()
	{
		
	}
	
	////////////
	//ACTION B//
	////////////
	
	@Override
	public void doPreActionB()
	{
		System.out.println(this.rb.getString("phaseNewGameLoadOrCreatePlayer"));
	}
	
	@Override
	public void actionBInput()
	{
		String input=UserInput.readInput(this.rb.getString("newGameLoadOrCreatePlayer"));
		this.setInputB(input);
	}
	
	@Override
	public void doAfterActionB()
	{
	}

	////////////
	//ACTION C//
	////////////
	
	@Override
	public void doPreActionC()
	{
		
		
		System.out.println(this.rb.getString("phaseNewGameLoadAPlayer"));
	}
	
	@Override
	public void actionCInput()
	{
		//SORT OF HUMAN AND AI PLAYERS AND PREPARE THEM AS TWO STRINGS
		ArrayList<String> input=ReadAndWriteFiles.readSavePlayers();
		
	
		System.out.println("RAW LIST: " + input);
		
		int humanCounts=this.countNumbersOfSameToken(input, EToken.HU);
		int AICounts=this.countNumbersOfSameToken(input, EToken.AI);
		
		
		
		String strHuman=this.rb.getString("phaseNewGameHumanPlayers") + ": \n" +this.prepareHumanPlayerFiles(input,1);
		String strAI=this.rb.getString("phaseNewGameAIPlayers") + ": \n" + this.prepareArtificalIntelligencePlayerFiles(input,humanCounts+1);
		String strSpecial=this.rb.getString("phaseNewGameSpecialPlayers") + ": \n( 0 ) \t " + this.rb.getString("instructionBack") + "\n";
		
		String message=strSpecial+strHuman+strAI;
		
		String inputN=UserInput.readInput(this.rb.getString("newGameLoadInstruction") + "\n" + message);
		this.setInputB(inputN);
	}
	
	@Override
	public void doAfterActionC()
	{
	}
	
	////////////
	//ACTION D//
	////////////
	
	@Override
	public void doPreActionD()
	{
		System.out.println(this.rb.getString("phaseNewGameCreatePlayer"));
	}
	
	@Override
	public void actionDInput()
	{
		String input=UserInput.readInput(this.rb.getString("newGameNameInstruction"));
		this.setInputB(input);
	}
	
	@Override
	public void doAfterActionD()
	{
	}
	
	////////////
	//ACTION E/
	////////////
	
	@Override
	public void doPreActionE()
	{
	}
	
	@Override
	public void actionEInput()
	{
		String input=UserInput.readInput(this.rb.getString("newGameAgeInstructuion"));
		this.setInputB(input);
	}
	
	@Override
	public void doAfterActionE()
	{
		
	}
	
	////////////
	//ACTION F/
	////////////
	
	@Override
	public void doPreActionF()
	{
	}
	
	@Override
	public void actionFInput()
	{
		String input=UserInput.readInput(this.rb.getString("newGameAgeInstructuion"));
		this.setInputB(input);
	}
	
	@Override
	public void doAfterActionF()
	{
		
	}
}
