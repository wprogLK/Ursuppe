package logics;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import enums.EColor;
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

	static final long ONE_HOUR = 60 * 60 * 1000L;
	
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
			this.activateActionD();
			this.activateActionE();
			this.activateActionF();
			
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
		
		System.out.println("TOKEN = " + token);
		
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
			//TODO: throw an exception
			return false;
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
			this.turnOnRestart();
		}
		
		return valid;
	}
	

	
	
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
		boolean valid=true;
		String name=this.doCastToString(inputD);
		
		if(name.equals(""))
		{
			valid=false;
		}
		else
		{
			this.nameOfNewPlayer=name;
		}
		
		return valid;
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
		String strInput=this.doCastToString(inputE);
		
		boolean valid=true;
		
		//check if length is right
		if(strInput.length()!=10)
		{
			valid=false;
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
				// TODO
				e.printStackTrace();
				valid=false;
			}
			
			this.ageOfNewPlayer=this.calculateAge(this.birthdayDateOfNewPlayer); //TODO implement this method
		}	
			
		
		return valid;

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
			boolean valid=this.checkInputActionF(inputString);
			this.isInputValid=valid;
			return valid;
		}
		
	}
	
	
	@Override
	public final boolean checkInputActionF(Object inputF)
	{
		if(!this.tryCastToEColor(inputF))
		{
			return false;
		}
		else
		{
			this.colorOfNewPlayer=this.doCastToEColor(inputF);
			
			this.turnOnRestart();
			
			this.createNewPlayer();
			
			return true;
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
