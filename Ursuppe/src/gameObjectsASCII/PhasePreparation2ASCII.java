package gameObjectsASCII;

import exceptions.InputException;
import interfaces.IModule;
import interfaces.IPlayer;
import helper.LanguagePack;
import helper.UserInput;
import logics.PhasePreparation2Logic;
/**
 * the second preparation phase for ASCII game
 * @author Lukas Keller
 * @version 1.0.0
 */
public class PhasePreparation2ASCII extends PhasePreparation2Logic
{
	/////////////
	//BASICS...//
	/////////////
		////////////
		//...LOGIC//
		////////////
	
		public PhasePreparation2ASCII(IModule module) 
		{
			super(module);
		}

		@Override
		public void doPreActionFirstRun()
		{
			this.outStream.println(LanguagePack.getTranslation("phasePreparation2Title"));
		}
		
		@Override
		public void doAfterActionLastRun()
		{
			this.outStream.println(LanguagePack.getTranslation("preaparation2Sucessful"));
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
		this.outStream.println(LanguagePack.getTranslation("preparation2Instruction"));
	}
	
	@Override
	public void actionAInput() throws Exception
	{
		String pos=UserInput.readInput(this.getPossiblePositions());
		
		try 
		{
			this.setInputA(pos);
		} 
		catch (InputException e)
		{
			System.out.println("PhasePrep2 its a inputException " + e);
		}
	}
	
	@Override
	public void doAfterActionA() throws Exception
	{
		IPlayer player=this.game.getCurrentPlayer();
		
		String[] arg={player.getName(),this.doCastToString(player.getScore())};
		String message=LanguagePack.getTranslation("preparation2ChoosedNEW",arg);
		this.outStream.println(message);
	}

	//////////////////
	//PRIVAT METHODS//
	//////////////////
	/**
	 * create a string with all possible start positions
	 */
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
