package gameObjectsASCII;

import java.util.ArrayList;

import enums.EColor;
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
			this.outStream.println(this.rb.getString("phaseNewGameTitle"));
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
		this.outStream.println(this.rb.getString("phaseNewGameAddAPlayerOrPlay"));
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
		this.outStream.println(this.rb.getString("phaseNewGameLoadOrCreatePlayer"));
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
		this.outStream.println(this.rb.getString("phaseNewGameLoadAPlayer"));
	}
	
	@Override
	public void actionCInput()
	{
		//SORT OF HUMAN AND AI PLAYERS AND PREPARE THEM AS TWO STRINGS
	
		String message=this.prepareStringForLoadPlayers(this.arrayHumanPlayers, this.arrayAIPlayers);
		
		
		String input=UserInput.readInput(this.rb.getString("newGameLoadInstruction") + "\n" + message);
		this.setInputC(input);
	}
	
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
	
	@Override
	public void doAfterActionC()
	{
		this.outStream.println(this.rb.getString("newGameSuccessfulLoad"));
	}
	
	////////////
	//ACTION D//
	////////////
	
	@Override
	public void doPreActionD()
	{
		this.outStream.println(this.rb.getString("phaseNewGameCreatePlayer"));
	}
	
	@Override
	public void actionDInput()
	{
		String input=UserInput.readInput(this.rb.getString("newGameNameInstruction"));
		this.setInputD(input);
	}
	
	@Override
	public void doAfterActionD()
	{
		this.outStream.println(this.rb.getString("newGameSuccessfulName") + this.nameOfNewPlayer);
	}
	
	////////////
	//ACTION E/
	////////////
	
	@Override
	public void actionEInput()
	{
		String input=UserInput.readInput(this.rb.getString("newGameAgeInstructuion"));
		this.setInputE(input);
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
		String strList=this.createStringOfColors();
		String input=UserInput.readInput(this.rb.getString("newGameColorInstruction") +"\n"+ strList);
		this.setInputF(input);
	}
	

	@Override
	public void doAfterActionF()
	{
		
	}
	
	
	///////////////////
	//PRIVATE METHODS//
	///////////////////
	

	private String createStringOfColors() 
	{
		String list="";
		
		for(EColor color: EColor.class.getEnumConstants())
		{
			list+=color +"\n";
		}
		
		return list;
	}
}
