package logics;

import java.util.ArrayList;

import enums.EPhases;
import templates.PhaseTemplateLogic;
import interfaces.IPhase;
import interfaces.IPlayer;

/**
 * This preparation phase is about set the players on the board
 * @author Lukas Keller
 * @version 1.0.0
 * 
 * @see IPhase
 */
public abstract class PhasePreparation2Logic extends PhaseTemplateLogic
{
	//////////
	//BASICS//
	//////////

	//////////
	//INPUTS//
	//////////
	
																					//ACTION A: chose position
	
	protected ArrayList<Integer> possibleStartPositions=new ArrayList<Integer>();
	protected ArrayList<IPlayer> orderToPlay=new ArrayList<IPlayer>();

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
		this.currentPhase=EPhases.phasePreparation2;
	}
	
	@Override
	public void doLogicPreActionFirstRun()
	{
		this.preparePossibleStartPositions();
		
		this.prepareOrderToPlay();
	}
	
	private void prepareOrderToPlay() 
	{
		for(int i=0; i<this.game.getNumbersOfPlayers();i++)
		{
			this.orderToPlay.add(null);
		}
	}

	private void preparePossibleStartPositions() 
	{
		for(int i=1; i<this.game.getNumbersOfPlayers()+1;i++)
		{
			this.possibleStartPositions.add(i);
		}
		
	}
	
	public final void doLogicAfterAction()
	{
		boolean validPlayer=!this.nextPlayer();
		
		if(validPlayer)
		{
			this.turnOnRestart();
		}
		else
		{
			this.turnOffRestart();
			
			this.overrideOrderToPlay();
			
			this.waiting=true;
			
			this.currentPhase=EPhases.phasePreparation3;
		}
		
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
		int inputPos=0;
		
		if(!this.tryCastToInteger(inputA))
		{
			return false;
		}
		else
		{
			inputPos=this.doCastToInteger(inputA);
			return this.understandInputPosition(inputPos);
		}
	}
	
	private boolean understandInputPosition(int inputPos) 
	{
		if(this.possibleStartPositions.contains(inputPos))
		{
			this.editOrderToPlay(inputPos);
			
			int index=this.possibleStartPositions.indexOf(inputPos);
			this.possibleStartPositions.remove(index);
			
			this.editOrderToPlay(inputPos);
			
			this.game.getCurrentPlayer().setScore(inputPos);
			
			return true;
		}
		else
		{
			return false;
		}
	}

	private void editOrderToPlay(int inputPos) 
	{
		int index=inputPos-1;
		IPlayer player=this.game.getCurrentPlayer();
		
		this.orderToPlay.remove(index);
		this.orderToPlay.add(index, player);
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
	private void overrideOrderToPlay() 
	{
		this.game.overrideAllNormalPlayers(this.orderToPlay);
	}
	
	
	
}
