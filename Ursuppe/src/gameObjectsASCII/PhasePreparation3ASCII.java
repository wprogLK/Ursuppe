package gameObjectsASCII;

import interfaces.IPlayer;
import helper.UserInput;
import logics.PhaseALogic;
import logics.PhasePreparation2Logic;

public class PhasePreparation3ASCII extends PhasePreparation2Logic
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
			this.outStream.println(this.rb.getString("phasePreparation3Title"));
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
		
		String message=player.getName() + " "+ this.rb.getString("preparation2Choosed") + " " + player.getScore();
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
			str+="( " + pos + " ) : " + this.rb.getString("wordPosition") + pos + "\n";
		}
		
		return str;
	}
	
}
