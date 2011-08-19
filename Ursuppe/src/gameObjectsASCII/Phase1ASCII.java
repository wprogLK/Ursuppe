package gameObjectsASCII;

import logics.Phase1Logic;

/**
 * @author Lukas
 *
 */
public class Phase1ASCII extends Phase1Logic
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
			this.outStream.println(this.rb.getString("phase1Title"));
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

	}
	
	@Override
	public void actionAInput()
	{

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

	}
	
	@Override
	public void actionBInput()
	{

	}
	
	@Override
	public void doAfterActionB()
	{

	}

	
	
}
