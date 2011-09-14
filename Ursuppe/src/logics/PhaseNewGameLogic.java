package logics;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import enums.EColor;
import enums.EPhases;
import enums.EToken;
import exceptions.InputException;
import exceptions.InputExceptions.ParseToIntegerException;
import templates.PhaseTemplateLogic;
import helper.LanguagePack;
import helper.ReadAndWriteFiles;
import helper.SaveAndLoad;
import interfaces.IModule;
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
	
	protected String nameOfNewPlayer;
	protected int ageOfNewPlayer;
	protected Date birthdayDateOfNewPlayer;
	protected EColor colorOfNewPlayer;
	
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
		public final void changeActionToRun()
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
	
	public PhaseNewGameLogic(IModule module)
	{
		super(module);
		this.prepareActionC();
		
	}
	
	///////////////
	//RUN ACTIONS//
	///////////////

	

	////////////
	//ACTION A//
	////////////
	
	@Override
	public  void setInputA(Object inputA) throws Exception
	{
		if(this.getDoRunActionA())
		{
			boolean validBasic = false;
			this.isInputNew=true;
			
			validBasic=this.checkBasicInputs(inputA);
			if(!validBasic)
			{
				this.checkInputActionA(inputA);
			}
		}
		
	}
	
	
	@Override
	public final void checkInputActionA(Object inputA) throws Exception
	{
		String inputString=this.doCastToString(inputA);
	
		this.tryUnderstandInputA(inputString);
	}
	
	/**
	 * try to understand/interpret the input and do the right thing
	 * 
	 * @param inputA
	 * @return - true if correct <br/> - false if incorrect
	 */
	private void tryUnderstandInputA(String inputA) throws Exception
	{
		if (inputA.equals(LanguagePack.getTranslation("instructionPhaseNewGameAddPlayer")))	//add new player
		{
			this.deactivateAllActions();
			
			this.activateActionB();
			this.isInputValid=true;
		}
		else if (inputA.equals(LanguagePack.getTranslation("instructionPhaseNewGamePlay")))	//play
		{
			this.isReadyToPlay();
			
			//this.isInputValid=true;
		}
		else
		{
			this.module.throwInputExceptionUnkownInstruction(inputA);
		}
		
	}
	


	private void isReadyToPlay() throws Exception
	{
		if (this.game.getNumbersOfPlayers()>=2)			
		{
			this.changeToPhase(EPhases.phasePreparation1);
			
			this.isInputValid=true;
		}
		else
		{
			this.module.throwGameExeptionNotEnoughPlayers();
		}
	}

	////////////
	//ACTION B//
	////////////
	@Override
	public  void setInputB(Object inputB) throws Exception
	{
		if(this.getDoRunActionB())
		{
			boolean validBasic = false;
			this.isInputNew=true;
		
			String inputString=this.doCastToString(inputB);
		
			validBasic=this.checkBasicInputs(inputString);
			
			if(!validBasic)
			{
				this.checkInputActionB(inputString);
			}
		}
	}
	
	
	@Override
	public final void checkInputActionB(Object inputB) throws Exception
	{
		String	inputString=this.doCastToString(inputB);
		
		this.tryUnderstandInputB(inputString);
		
	}
	
	/**
	 * try to understand/interpret the input and do the right thing
	 * 
	 * @param inputA
	 * @return - true if correct <br/> - false if incorrect
	 */
	private void tryUnderstandInputB(String inputB) throws Exception
	{
		if (inputB.equals(LanguagePack.getTranslation("instructionPhaseNewGameLoadPlayer")))	//load player
		{
			this.deactivateAllActions();
			
			this.activateActionC();
			this.isInputValid=true;
		}
		else if (inputB.equals(LanguagePack.getTranslation("instructionPhaseNewGameCreatePlayer")))	//create new player
		{
			this.deactivateAllActions();
			
			this.activateActionD();
			this.activateActionE();
			this.activateActionF();
			
			this.isInputValid=true;
		}
		else
		{
			this.module.throwInputExceptionUnkownInstruction(inputB);
		}
		
	}
	
	////////////
	//ACTION C//
	////////////
	
	
	@Override
	public  void setInputC(Object inputC) throws Exception
	{
		if(this.getDoRunActionC())
		{
			boolean validBasic = false;
			this.isInputNew=true;
			
			String inputString=this.doCastToString(inputC);
			
			validBasic=this.checkBasicInputs(inputString);
			
			if(!validBasic)
			{
				this.checkInputActionC(inputC);
			}
		}
		
	}
	
	
	private void prepareActionC() 
	{
		this.arrayHumanPlayers=ReadAndWriteFiles.readSaveHumanPlayers();
		this.arrayAIPlayers=ReadAndWriteFiles.readSaveAIPlayers();
	}

	@Override
	public final void checkInputActionC(Object inputC) throws Exception 
	{
		String input=this.doCastToString(inputC);
		
		char token=input.charAt(0);
		String number=input.subSequence(1, input.length()).toString();
		try
		{
			int intInput=this.doCastToInteger(number);
			this.tryUnderstandInputC(token,intInput);
		}
		catch(ParseToIntegerException e)
		{
			this.module.throwInputExceptionUnkownCombination(input);
		}
	
	
	}
	
	
	/**
	 * try to understand/interpret the input and do the right thing
	 * 
	 * @param inputC
	 * @return - true if correct <br/> - false if incorrect
	 */
	private void tryUnderstandInputC(char token, int number) throws Exception
	{
		//TODO: TEST THIS (with the new throwing exceptions!)
		
		boolean valid=true;
	
		ArrayList<String> tmpArray=new ArrayList<String>();
		String fileToken="";
		
		if(token=='h' | token=='H')
		{
			tmpArray=this.arrayHumanPlayers;
			fileToken="H";
		}
		else if(token=='A' | token=='a')
		{
			tmpArray=this.arrayAIPlayers;
				
			fileToken="A";
		}
		else
		{
			this.module.throwInputExceptionWrongToken(token);
		}
		
		//TODO: check if out of bounds exception
		String filename=fileToken+tmpArray.get(number);
		
		
		IPlayer player=SaveAndLoad.loadPlayer(filename+".urs");
		
		this.game.addPlayer(player);
		
		//Delete the player out of the arrayList:
		//TODO: check if out of bounds exception
		tmpArray.remove(number);
		
		if(valid)
		{
			this.isInputValid=true;
			this.turnOnRestart();
		}
		
	}
	
	
	////////////
	//ACTION D//
	////////////
	@Override
	public  void setInputD(Object inputD) throws Exception
	{
		if(this.getDoRunActionD())
		{
			boolean validBasic = false;
			this.isInputNew=true;
		
			String inputString=this.doCastToString(inputD);
			
			validBasic=this.checkBasicInputs(inputString);
			
			if(!validBasic)		
			{
				this.checkInputActionD(inputString);
			}
		}
		
	}
	
	
	@Override
	public final void checkInputActionD(Object inputD) throws Exception
	{
		boolean valid=true;
		String name=this.doCastToString(inputD);
		
		if(name.equals(""))
		{
			this.module.throwInputExceptionEmptyInput();
		}
		else
		{
			this.nameOfNewPlayer=name;
			this.isInputValid=true;
		}
	}
	
	
	////////////
	//ACTION E//
	////////////
	@Override
	public  void setInputE(Object inputE) throws Exception
	{
		if(this.getDoRunActionE())
		{
			boolean validBasic = false;
			this.isInputNew=true;
		
			String inputString=this.doCastToString(inputE);
		
			validBasic=this.checkBasicInputs(inputString);
			
			if(!validBasic)
			{
				this.checkInputActionE(inputString);
			}
		}
	}
	
	
	@Override
	public final void checkInputActionE(Object inputE) throws Exception
	{
		String strInput=this.doCastToString(inputE);
		
		boolean valid=true;
		
		//check if length is right
		if(strInput.length()<10)
		{
			this.module.throwInputExceptionWrongSize(LanguagePack.getTranslation("wordShort"));
		}
		else if(strInput.length()>10)
		{
			this.module.throwInputExceptionWrongSize(LanguagePack.getTranslation("wordLong"));
		}
		else
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
			dateFormat.setLenient(false);
			
			try 
			{
				this.birthdayDateOfNewPlayer= dateFormat.parse(strInput);
			} 
			catch (ParseException e) 
			{
				this.module.throwInputExceptionWrongDataFormat();
			}
			
			this.ageOfNewPlayer=this.calculateAge(this.birthdayDateOfNewPlayer); //TODO implement this method
			this.isInputValid=true;
		}	
			
	}
	
	
	////////////
	//ACTION F//
	////////////
	@Override
	public  void setInputF(Object inputF) throws Exception
	{
		boolean validBasic = false;
		this.isInputNew=true;
		
		String inputString=this.doCastToString(inputF);
	
		validBasic=this.checkBasicInputs(inputString);
		
		if(!validBasic)
		{
			this.checkInputActionF(inputString);
		}
		
	}
	
	
	@Override
	public final void checkInputActionF(Object inputF) throws Exception
	{
		
		this.colorOfNewPlayer=this.doCastToEColor(inputF);
			
		this.turnOnRestart();
			
		this.createNewPlayer();
			
		this.isInputValid=true;
	}
	
	

	///////////
	//SETTERS//
	///////////

	
	///////////
	//GETTERS//
	///////////
	
	///////////////////
	//PRIVATE METHODS//
	///////////////////
	
	private void createNewPlayer() 
	{
		IPlayer player=this.game.createANewPlayer(this.nameOfNewPlayer, this.birthdayDateOfNewPlayer,this.ageOfNewPlayer, this.colorOfNewPlayer);
		SaveAndLoad.saveHumanPlayer(player, nameOfNewPlayer, EToken.HU);
	}

	private int calculateAge(Date birthday) 
	{
		//TODO
		return 0;
	}

}
