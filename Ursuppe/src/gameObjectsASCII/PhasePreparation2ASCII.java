package gameObjectsASCII;

import interfaces.IPlayer;
import helper.UserInput;
import logics.PhaseALogic;
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
	
		@Override
		public void doPreActionFirstRun()
		{
			this.outStream.println(this.rb.getString("phasePreparation2Title"));
		}
		
		@Override
		public void doAfterActionLastRun()
		{
			this.outStream.println(this.rb.getString("preaparation2Sucessful"));
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
		this.outStream.println(this.rb.getString("preparation2Instruction"));
	}
	
	@Override
	public void actionAInput()
	{
		String pos=UserInput.readInput(this.getPossiblePositions());
		
		this.setInputA(pos);
	}
	
	@Override
	public void doAfterActionA()
	{
		IPlayer player=this.game.getCurrentPlayer();
		
		String[] arg={player.getName(),this.doCastToString(player.getScore())};
		String message=String.format(this.rb.getString("preparation2ChoosedNEW"), arg);
		
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
			str+="( " + pos + " ) : " + this.rb.getString("wordPosition") + pos + "\n";
		}
		
		return str;
	}
	
}
