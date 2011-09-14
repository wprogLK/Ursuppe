package logics;

import java.util.ArrayList;

import enums.EPhases;
import exceptions.InputException;
import templates.PhaseTemplateLogic;
import interfaces.IModule;
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
	
	public PhasePreparation2Logic(IModule module) 
	{
		super(module);
	}

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
	public void setInputA(Object inputA) throws Exception
	{
		boolean validBasic = false;
		this.isInputNew=true;
		
		validBasic=this.checkBasicInputs(inputA);
		
		if(!validBasic)
		{
			this.checkInputActionA(inputA);
		}
	}
	
	
	@Override
	public final void checkInputActionA(Object inputA) throws Exception
	{
		int inputPos=this.doCastToInteger(inputA);
		this.understandInputPosition(inputPos);
	}
	
	private void understandInputPosition(int inputPos) throws Exception
	{
		if(this.possibleStartPositions.contains(inputPos))
		{
			this.editOrderToPlay(inputPos);
			
			int index=this.possibleStartPositions.indexOf(inputPos);
			this.possibleStartPositions.remove(index);
			
			this.editOrderToPlay(inputPos);
			
			this.game.getCurrentPlayer().setScore(inputPos);
			
			this.isInputValid=true;
		}
		else
		{
			this.module.throwInputExceptionImpossibleStartPosition();
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
