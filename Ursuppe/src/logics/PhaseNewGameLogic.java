package logics;

import java.util.ArrayList;
import enums.EPhases;
import enums.EToken;
import templates.PhaseTemplateLogic;
import helper.ReadAndWriteFiles;
import helper.SaveAndLoad;
import interfaces.IPhase;
import interfaces.IPlayer;

/**
 * 
 * @author Lukas Keller
 * @version 1.0.0
 * 
 * @see IPhase
 */
public abstract class PhaseNewGameLogic extends PhaseTemplateLogic
{
	//////////
	//BASICS//
	//////////

	//////////
	//INPUTS//
	//////////
	protected ArrayList<String> arrayHumanPlayers;
	protected ArrayList<String> arrayAIPlayers;
										//ACTION A: Do you want to add a player or go back or play?
										//ACTION B: Load an exist player or create a new one?
										//ACTION C: Load (AI and human)
										//ACTION D: Create a new one: Part 1: Name
										//ACTION E: Create a new one: Part 2: age/birthday
										//ACTION F: Create a new one: Part 3: favorite color

		////////////
		//...LOGIC//
		////////////
		@Override
		public final void setActionsToRun()
		{
			this.activateActionA();
		}
		
	//////////
	//BASICS//
	//////////
	@Override
	protected void setCurrentPhase()
	{
		this.currentPhase=EPhases.phaseNewGame;
	}
	
	public PhaseNewGameLogic()
	{
		this.prepareActionC();
		
	}
	
	///////////////
	//RUN ACTIONS//
	///////////////

	

	////////////
	//ACTION A//
	////////////
	
	@Override
	public  boolean setInputA(Object inputA)
	{
		boolean validBasic = false;
		this.isInputNew=true;
		
		validBasic=this.checkBasicInputs(inputA);
		if(validBasic)
		{
			return true;
		}
		else
		{
			boolean valid=this.checkInputActionA(inputA);
			this.isInputValid=valid;
			return valid;
		}
	}
	
	
	@Override
	public final boolean checkInputActionA(Object inputA)
	{
		String inputString="";
		
		if(!this.tryCastToString(inputA))
		{
			return false;
		}
		else
		{
			inputString=this.doCastToString(inputA);
		}
		
		boolean valid=this.tryUnderstandInputA(inputString);
		
		return valid;
	}
	
	/**
	 * try to understand/interpret the input and do the right thing
	 * 
	 * @param inputA
	 * @return - true if correct <br/> - false if incorrect
	 */
	private boolean tryUnderstandInputA(String inputA)
	{
		if (inputA.equals(this.rb.getString("instructionPhaseNewGameAddPlayer")))	//add new player
		{
			this.activateActionB();
			return true;
		}
		else if (inputA.equals(this.rb.getString("instructionPhaseNewGamePlay")))	//play
		{
			this.currentPhase=EPhases.phasePreparation1;
			return true;
		}
		else
		{
			return false;
		}
		
	}
	


	////////////
	//ACTION B//
	////////////
	@Override
	public  boolean setInputB(Object inputB)
	{
		String inputString="";
		boolean validBasic = false;
		this.isInputNew=true;
		
		if(!this.tryCastToString(inputB))
		{
			return false;
		}
		else
		{
			inputString=this.doCastToString(inputB);
		}
		
	
		validBasic=this.checkBasicInputs(inputString);
		
		if(validBasic)
		{
			this.isInputValid=true;
			return true;
		}
		else
		{
			boolean valid=this.checkInputActionB(inputString);
			this.isInputValid=valid;
			return valid;
		}
		
	}
	
	
	@Override
	public final boolean checkInputActionB(Object inputB)
	{
		String inputString="";
		
		if(!this.tryCastToString(inputB))
		{
			return false;
		}
		else
		{
			inputString=this.doCastToString(inputB);
		}
		
		boolean valid=this.tryUnderstandInputB(inputString);
		
		return valid;
	}
	
	/**
	 * try to understand/interpret the input and do the right thing
	 * 
	 * @param inputA
	 * @return - true if correct <br/> - false if incorrect
	 */
	private boolean tryUnderstandInputB(String inputB)
	{
		if (inputB.equals(this.rb.getString("instructionPhaseNewGameLoadPlayer")))	//load player
		{
			this.activateActionC();
			return true;
		}
		else if (inputB.equals(this.rb.getString("instructionPhaseNewGameCreatePlayer")))	//create new player
		{
			activateActionD();
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	////////////
	//ACTION C//
	////////////
	
	
	@Override
	public  boolean setInputC(Object inputC)
	{
		
		
		String inputString="";
		boolean validBasic = false;
		this.isInputNew=true;
		
		if(!this.tryCastToString(inputC))
		{
			return false;
		}
		else
		{
			inputString=this.doCastToString(inputC);
		}
		
	
		validBasic=this.checkBasicInputs(inputString);
		
		if(validBasic)
		{
			this.isInputValid=true;
			return true;
		}
		else
		{
			boolean valid=this.checkInputActionC(inputC);
			this.isInputValid=valid;
			return valid;
		}
		
	}
	
	
	private void prepareActionC() 
	{
		this.arrayHumanPlayers=ReadAndWriteFiles.readSaveHumanPlayers();
		this.arrayAIPlayers=ReadAndWriteFiles.readSaveAIPlayers();
	}

	@Override
	public final boolean checkInputActionC(Object inputC)
	{
		String input=this.doCastToString(inputC);
		
		char token=input.charAt(0);
		String number=input.subSequence(1, input.length()).toString();
		
		if(!this.tryCastToInteger(number))
		{
			return false;
		}
		else
		{
			int intInput=this.doCastToInteger(number);
			boolean valid=this.tryUnderstandInputC(token,intInput);
			
			return valid;
		}
	}
	
	
	/**
	 * try to understand/interpret the input and do the right thing
	 * 
	 * @param inputC
	 * @return - true if correct <br/> - false if incorrect
	 */
	private boolean tryUnderstandInputC(char token, int number)
	{
		boolean valid=true;
	
		ArrayList<String> tmpArray=new ArrayList<String>();
		String fileToken="";
		
		switch (token)
		{
			case 'h' | 'H':
			{
				tmpArray=this.arrayHumanPlayers;
				fileToken="HU";
			}
			case 'A' | 'a':
			{
				tmpArray=this.arrayAIPlayers;
				fileToken="AI";
			}
			default:
			{
				//TODO
				valid=false;
			}
		}
		
		String filename=fileToken+tmpArray.get(number);
		
		IPlayer player=SaveAndLoad.loadPlayer(filename);
		
		return valid;
	}
	
//	protected int countNumbersOfSameToken(ArrayList<String> input  , EToken token)
//	{
//		int counter=0;
//		
//		for(String str:input)
//		{
//			String prefix=str.subSequence(0, 2).toString();
//			
//			if(prefix.equals(token.toString()))
//			{
//				counter++;
//			}
//		}
//		
//		return counter;
//	}
//	
//	/**
//	 * sorts out all strings which starts with the token and give it back
//	 * @param savePlayersInput
//	 * @param token
//	 * @return ArrayList<String>
//	 */
//	protected ArrayList<String> sortSavePlayerFiles(ArrayList<String> savePlayersInput, EToken token)
//	{
//		ArrayList<String> savePlayersOutput=new ArrayList<String>();
//		
//		for(String str:savePlayersInput)
//		{
//			String prefix=str.subSequence(0, 2).toString();
//			
//			
//			if(prefix.equals(token.toString()))
//			{
//				savePlayersOutput.add(str);
//			}
//		}
//		
//		
//		return savePlayersOutput;
//	}
//	
//	protected String prepareHumanPlayerFiles(ArrayList<String> input,int startToCount)
//	{
//		String output="";
//		
//		input=this.sortSavePlayerFiles(input, EToken.HU);
//		
//		output=this.createStringForPlayers(input,EToken.HU,startToCount);
//		
//		return output;
//	}
//	
//	protected String prepareArtificalIntelligencePlayerFiles(ArrayList<String> input, int startToCount)
//	{
//		String output="";
//		
//		input=this.sortSavePlayerFiles(input, EToken.AI);
//		
//		output=this.createStringForPlayers(input,EToken.AI,startToCount);
//		
//		return output;
//	}
//	
//	/**
//	 * creates a numbered (start with number {@code startToCount} and formated string with all players with the token
//	 * @param input
//	 * @param token
//	 * @return numbered and formated string with all players with the token
//	 */
//	protected String createStringForPlayers(ArrayList<String> input,EToken token, int startToCount)
//	{
//		String output="";
//		//System.out.println("ARRAY ( " + token + " ) BEFORE: " + input );
//		for(String str:input)
//		{
//			String strName=str.subSequence(2, str.length()).toString();
//		
//			output=output+ "( " + startToCount + " ) \t " +strName + "\n";
//			
//			startToCount++;
//		}
//		//System.out.println("ARRAY ( " + token + " ) AFTER: " + input );
//		
//		
//		return output;
//	}
	
	
	
	////////////
	//ACTION D//
	////////////
	@Override
	public  boolean setInputD(Object inputD)
	{
		String inputString="";
		boolean validBasic = false;
		this.isInputNew=true;
		
		if(!this.tryCastToString(inputD))
		{
			return false;
		}
		else
		{
			inputString=this.doCastToString(inputD);
		}
		
	
		validBasic=this.checkBasicInputs(inputString);
		
		if(validBasic)		
		{
			this.isInputValid=true;
			return true;
		}
		else
		{
			boolean valid=this.checkInputActionD(inputString);
			this.isInputValid=valid;
			return valid;
		}
		
	}
	
	
	@Override
	public final boolean checkInputActionD(Object inputD)
	{
		if(!this.tryCastToInteger(inputD))
		{
			return false;
		}
		else
		{
			this.game.getPlayer(1).setAge(this.doCastToInteger(inputD)); 
			return true;
		}
	}
	
	
	////////////
	//ACTION E//
	////////////
	@Override
	public  boolean setInputE(Object inputE)
	{
		String inputString="";
		boolean validBasic = false;
		this.isInputNew=true;
		
		if(!this.tryCastToString(inputE))
		{
			return false;
		}
		else
		{
			inputString=this.doCastToString(inputE);
		}
		
	
		validBasic=this.checkBasicInputs(inputString);
		
		if(validBasic)
		{
			this.isInputValid=true;
			return true;
		}
		else
		{
			boolean valid=this.checkInputActionE(inputString);
			this.isInputValid=valid;
			return valid;
		}
		
	}
	
	
	@Override
	public final boolean checkInputActionE(Object inputE)
	{
		if(!this.tryCastToInteger(inputE))
		{
			return false;
		}
		else
		{
			this.game.getPlayer(1).setAge(this.doCastToInteger(inputE)); 
			return true;
		}
	}
	
	////////////
	//ACTION F//
	////////////
	@Override
	public  boolean setInputF(Object inputF)
	{
		String inputString="";
		boolean validBasic = false;
		this.isInputNew=true;
		
		if(!this.tryCastToString(inputF))
		{
			return false;
		}
		else
		{
			inputString=this.doCastToString(inputF);
		}
		
	
		validBasic=this.checkBasicInputs(inputString);
		
		if(validBasic)
		{
			this.isInputValid=true;
			return true;
		}
		else
		{
			boolean valid=this.checkInputActionE(inputString);
			this.isInputValid=valid;
			return valid;
		}
		
	}
	
	
	@Override
	public final boolean checkInputActionF(Object inputF)
	{
		if(!this.tryCastToInteger(inputF))
		{
			return false;
		}
		else
		{
			this.game.getPlayer(1).setAge(this.doCastToInteger(inputF)); 
			return true;
		}
	}
	
	

	///////////
	//SETTERS//
	///////////
	
	
	

	
	
	///////////
	//GETTERS//
	///////////
	
	
}
