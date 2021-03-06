package gameObjectsASCII;

import exceptions.InputException;
import interfaces.IModule;
import interfaces.IPlayer;
import helper.LanguagePack;
import helper.UserInput;
import logics.PhaseALogic;
import logics.PhasePreparation2Logic;
/**
 * the third preparation phase for ASCII game
 * @author Lukas Keller
 * @version 1.0.0
 */
public class PhasePreparation3ASCII extends PhasePreparation2Logic
{
	//TODO
	
	/////////////
	//BASICS...//
	/////////////
		////////////
		//...LOGIC//
		////////////
	
		public PhasePreparation3ASCII(IModule module) 
		{
			super(module);
		}

		@Override
		public void doPreActionFirstRun()
		{
			this.outStream.println(LanguagePack.getTranslation("phasePreparation3Title"));
		}
		
		@Override
		public void doAfterActionLastRun()
		{
			this.outStream.println(LanguagePack.getTranslation("preaparation3Sucessful"));
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
		this.outStream.println(LanguagePack.getTranslation("preparation3Instruction"));
	}
	
	@Override
	public void actionAInput()
	{
		String pos=UserInput.readInput(this.getPossiblePositions());
		
		try
		{
			this.setInputA(pos);
		} 
		catch (Exception e)
		{
			//TODO
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void doAfterActionA()
	{
		IPlayer player=this.game.getCurrentPlayer();
		
		String message=player.getName() + " "+ LanguagePack.getTranslation("preparation2Choosed") + " " + player.getScore();
		this.outStream.println(message);
	}

	//////////////////
	//PRIVAT METHODS//
	//////////////////
	private String getPossiblePositions()
	{
		String str="";
		
		for(int pos:this.possibleStartPositions)
		{
			str+="( " + pos + " ) : " + LanguagePack.getTranslation("wordPosition") + pos + "\n";
		}
		
		return str;
	}
	
}
