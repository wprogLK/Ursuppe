package logics;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import enums.EColor;
import enums.EPhases;
import enums.EToken;
import exceptions.InputException;
import templates.PhaseTemplateLogic;
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
	public  void setInputA(Object inputA) throws InputException
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
	public final void checkInputActionA(Object inputA) throws InputException
	{
		String inputString="";
		
		if(!this.tryCastToString(inputA))
		{
			throw this.module.createInputExceptionParseToString();
		}
		else
		{
			inputString=this.doCastToString(inputA);
		}
		
		this.tryUnderstandInputA(inputString);
	}
	
	/**
	 * try to understand/interpret the input and do the right thing
	 * 
	 * @param inputA
	 * @return - true if correct <br/> - false if incorrect
	 */
	private void tryUnderstandInputA(String inputA) throws InputException
	{
		if (inputA.equals(this.rb.getString("instructionPhaseNewGameAddPlayer")))	//add new player
		{
			this.activateActionB();
			this.isInputValid=true;
		}
		else if (inputA.equals(this.rb.getString("instructionPhaseNewGamePlay")))	//play
		{
			this.isReadyToPlay();
			
			//this.isInputValid=true;
		}
		else
		{
			throw this.module.createInputExceptionUnkownInstruction(inputA);
		}
		
	}
	


	private void isReadyToPlay() throws InputException
	{
		if (this.game.getNumbersOfPlayers()>=2)			
		{
			System.out.println("...play");
			
			this.changeToPhase(EPhases.phasePreparation1);
			
			this.isInputValid=true;
		}
		else
		{
			throw this.module.createInputException("You can not play the game. You are not enough players.");
		}
	}

	////////////
	//ACTION B//
	////////////
	@Override
	public  void setInputB(Object inputB) throws InputException
	{
		if(this.getDoRunActionB())
		{
			String inputString="";
			boolean validBasic = false;
			this.isInputNew=true;
			
			if(!this.tryCastToString(inputB))
			{
				throw this.module.createInputExceptionParseToString();
			}
			else
			{
				inputString=this.doCastToString(inputB);
			}
			
		
			validBasic=this.checkBasicInputs(inputString);
			
			if(!validBasic)
			{
				this.checkInputActionB(inputString);
			}
		}
	}
	
	
	@Override
	public final void checkInputActionB(Object inputB) throws InputException
	{
		String inputString="";
		
		if(!this.tryCastToString(inputB))
		{
			throw this.module.createInputExceptionParseToString();
		}
		else
		{
			inputString=this.doCastToString(inputB);
		}
		
		this.tryUnderstandInputB(inputString);
		
	}
	
	/**
	 * try to understand/interpret the input and do the right thing
	 * 
	 * @param inputA
	 * @return - true if correct <br/> - false if incorrect
	 */
	private void tryUnderstandInputB(String inputB) throws InputException
	{
		if (inputB.equals(this.rb.getString("instructionPhaseNewGameLoadPlayer")))	//load player
		{
			this.activateActionC();
			this.isInputValid=true;
		}
		else if (inputB.equals(this.rb.getString("instructionPhaseNewGameCreatePlayer")))	//create new player
		{
			this.activateActionD();
			this.activateActionE();
			this.activateActionF();
			
			this.isInputValid=true;
		}
		else
		{
			throw this.module.createInputExceptionUnkownInstruction(inputB);
		}
		
	}
	
	////////////
	//ACTION C//
	////////////
	
	
	@Override
	public  void setInputC(Object inputC) throws InputException
	{
		if(this.getDoRunActionC())
		{
			String inputString="";
			boolean validBasic = false;
			this.isInputNew=true;
			
			if(!this.tryCastToString(inputC))
			{
				throw this.module.createInputExceptionParseToString();
			}
			else
			{
				inputString=this.doCastToString(inputC);
			}
		
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
	public final void checkInputActionC(Object inputC) throws InputException 
	{
		String input=this.doCastToString(inputC);
		
		char token=input.charAt(0);
		String number=input.subSequence(1, input.length()).toString();
		
		if(!this.tryCastToInteger(number))
		{
			throw this.module.createInputExceptionParseToInteger();
		}
		else
		{
			int intInput=this.doCastToInteger(number);
			this.tryUnderstandInputC(token,intInput);
		}
	}
	
	
	/**
	 * try to understand/interpret the input and do the right thing
	 * 
	 * @param inputC
	 * @return - true if correct <br/> - false if incorrect
	 */
	private void tryUnderstandInputC(char token, int number) throws InputException
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
			throw this.module.createInputException("Error: Token " + token + " not found!");
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
	public  void setInputD(Object inputD) throws InputException
	{
		if(this.getDoRunActionD())
		{
			String inputString="";
			boolean validBasic = false;
			this.isInputNew=true;
			
			if(!this.tryCastToString(inputD))
			{
				throw this.module.createInputExceptionParseToString();
			}
			else
			{
				inputString=this.doCastToString(inputD);
			}
			
		
			validBasic=this.checkBasicInputs(inputString);
			
			if(!validBasic)		
			{
				this.checkInputActionD(inputString);
			}
		}
		
	}
	
	
	@Override
	public final void checkInputActionD(Object inputD) throws InputException
	{
		boolean valid=true;
		String name=this.doCastToString(inputD);
		
		if(name.equals(""))
		{
			throw this.module.createInputException("Error: Please enter a name with at least one letter. Try again...");
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
	public  void setInputE(Object inputE) throws InputException
	{
		if(this.getDoRunActionE())
		{
			String inputString="";
			boolean validBasic = false;
			this.isInputNew=true;
			
			if(!this.tryCastToString(inputE))
			{
				throw this.module.createInputExceptionParseToString();
			}
			else
			{
				inputString=this.doCastToString(inputE);
			}
		
			validBasic=this.checkBasicInputs(inputString);
			
			if(!validBasic)
			{
				this.checkInputActionE(inputString);
			}
		}
	}
	
	
	@Override
	public final void checkInputActionE(Object inputE) throws InputException
	{
		String strInput=this.doCastToString(inputE);
		
		boolean valid=true;
		
		//check if length is right
		if(strInput.length()<10)
		{
			throw this.module.createInputException("Error: Your input " + inputE + " was too short! Try it again...");
		}
		else if(strInput.length()>10)
		{
			throw this.module.createInputException("Error: Your input " + inputE + " was too long! Try it again...");
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
				throw this.module.createInputException("Error: Your input hadn't the correct format! Try it again...");
			}
			
			this.ageOfNewPlayer=this.calculateAge(this.birthdayDateOfNewPlayer); //TODO implement this method
			this.isInputValid=true;
		}	
			
	}
	
	
	////////////
	//ACTION F//
	////////////
	@Override
	public  void setInputF(Object inputF) throws InputException
	{
		String inputString="";
		boolean validBasic = false;
		this.isInputNew=true;
		
		if(!this.tryCastToString(inputF))
		{
			throw this.module.createInputExceptionParseToString();
		}
		else
		{
			inputString=this.doCastToString(inputF);
		}
		
	
		validBasic=this.checkBasicInputs(inputString);
		
		if(!validBasic)
		{
			this.checkInputActionF(inputString);
		}
		
	}
	
	
	@Override
	public final void checkInputActionF(Object inputF) throws InputException
	{
		if(!this.tryCastToEColor(inputF))
		{
			throw this.module.createInputExceptionParseToEColor();
		}
		else
		{
			this.colorOfNewPlayer=this.doCastToEColor(inputF);
			
			this.turnOnRestart();
			
			this.createNewPlayer();
			
			this.isInputValid=true;
		}
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
