package gameObjectsASCII;

import helper.ReadAndWriteFiles;
import helper.UserInput;
import logics.PhaseALogic;
import logics.PhaseNewGameLogic;

public class PhaseNewGameASCII extends PhaseNewGameLogic
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
			System.out.println(this.rb.getString("phaseNewGameTitle"));
		}
		
		@Override
		public void doAfterAction()
		{

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
		System.out.println(this.rb.getString("phaseNewGameAddAPlayerOrPlay"));
	}
	
	@Override
	public void actionAInput()
	{
		String input=UserInput.readInput(this.rb.getString("newGameAddOrPlay"));
		this.setInputA(input);
	}
	
	@Override
	public void doAfterActionA()
	{
		
	}
	
	////////////
	//ACTION B//
	////////////
	
	@Override
	public void doPreActionB()
	{
		System.out.println(this.rb.getString("phaseNewGameLoadOrCreatePlayer"));
	}
	
	@Override
	public void actionBInput()
	{
		String input=UserInput.readInput(this.rb.getString("newGameLoadOrCreatePlayer"));
		this.setInputB(input);
	}
	
	@Override
	public void doAfterActionB()
	{
	}

	////////////
	//ACTION C//
	////////////
	
	@Override
	public void doPreActionC()
	{
		
		System.out.println(this.rb.getString("phaseNewGameLoadAPlayer"));
	}
	
	@Override
	public void actionCInput()
	{
		String input=UserInput.readInput(this.rb.getString("newGameLoadInstruction"));
		this.setInputB(input);
	}
	
	@Override
	public void doAfterActionC()
	{
	}
	
	////////////
	//ACTION D//
	////////////
	
	@Override
	public void doPreActionD()
	{
		System.out.println(this.rb.getString("phaseNewGameCreatePlayer"));
	}
	
	@Override
	public void actionDInput()
	{
		String input=UserInput.readInput(this.rb.getString("newGameNameInstruction"));
		this.setInputB(input);
	}
	
	@Override
	public void doAfterActionD()
	{
	}
	
	////////////
	//ACTION E/
	////////////
	
	@Override
	public void doPreActionE()
	{
	}
	
	@Override
	public void actionEInput()
	{
		String input=UserInput.readInput(this.rb.getString("newGameAgeInstructuion"));
		this.setInputB(input);
	}
	
	@Override
	public void doAfterActionE()
	{
		
	}
	
	////////////
	//ACTION F/
	////////////
	
	@Override
	public void doPreActionF()
	{
	}
	
	@Override
	public void actionFInput()
	{
		String input=UserInput.readInput(this.rb.getString("newGameAgeInstructuion"));
		this.setInputB(input);
	}
	
	@Override
	public void doAfterActionF()
	{
		
	}
}
