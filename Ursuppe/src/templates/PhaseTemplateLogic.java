package templates;

import javax.swing.JPanel;

import enums.EPhases;

import annotations.OnlyForTesting;

import gameObjectsASCII.PhaseAASCII;
import gameObjectsGUI.PhaseAGUI;
import interfaces.IGame;
import interfaces.IPhase;

import main.GameLogic;

/**
 * is the abstract template for every PhaseLogics. 
 * @author Lukas Keller
 * @version 1.0.0
 * 
 * @see IPhase
 */
public abstract class PhaseTemplateLogic extends Thread implements IPhase
{
	//////////
	//BASICS//
	//////////
	protected boolean isInputNew=false;
	protected boolean isInputValid=false;
	
	protected IGame game;
	
	protected boolean isRunning=false;
	protected boolean doNothing=true;
	
	protected EPhases currentPhase;
	protected EPhases lastPhase;
	protected EPhases nextPhase;
	
	///////////////////
	//DO RUN ACTIONS?//
	///////////////////
	
	private boolean doRunActionA=false;
	private boolean doRunActionB=false;
	private boolean doRunActionC=false;
	
	//////////
	//BASICS//
	//////////
	
	/**
	 * Constructor:
	 * <br />
	 * set the priority of the extended Thread up to 3
	 */
	public PhaseTemplateLogic()
	{
		this.setPriority(3);
		
		this.setCurrentPhase();
	}
	
	//////////
	//THREAD//
	//////////
	
	
	@Override
	/**
	 *called by start() the first time in the GameLogic to start the Thread
	 */
	public final void run()
	{
		this.runPhase();
	}
	
	@Override
	public final void runPhase()
	{
		this.setCurrentPhase();
		this.setNextPhase();
		
		this.doNothing=false;
		this.isRunning=true;
		this.runLogic();
	}
	
	@Override
	public final void doStart()
	{
		this.isRunning=true;
		this.doNothing=false;
		this.resume();
		
		this.update();			//IMPORTANT!
	}
	
	/////////////
	//BASICS...//
	/////////////
		public final String toString()
		{
			return 	this.getCurrentPhase().toString();
		}
	
		public void update()
		{
			this.game.update();
		}
		
		/**
		 * activates/deactivates actions which should run in the concrete phaseLogic
		 * 
		 * <p>
		 * implemented in a concrete phase in its method
		 * can be override by a concrete phase 
		 * </p>
		 * @return 
		 * 
		 * @see PhaseALogic
		 */
		public abstract void setActionsToRun();
	
		@Override
		public final void setGame(IGame game)
		{
			this.game=game;
		}
	
		/////////////////
		//...SET PHASES//
		/////////////////
		/**
		 * sets the currentPhase in the concrete phaseLogic
		 * 
		 * <p>
		 * <u>have to (!)</u> implemented in a concrete phase in its method!
		 * can be override by a concrete phase 
		 * </p>
		 * 
		 * @see PhaseALogic
		 */
		protected abstract void setCurrentPhase();
		
		/**
		 * sets the lastPhase in the concrete phaseLogic
		 * 
		 * <p>
		 * <u>have to (!)</u> called by the {@link GameLogic} at the {@code} checkPhaseChange() method
		 * </p>
		 * 
		 * @see PhaseALogic
		 */
		public final void setLastPhase(EPhases lastPhase)
		{
			this.lastPhase=lastPhase;
		}
		
		/**
		 * sets the <u>default</u> nextPhase in the concrete phaseLogic
		 * 
		 * <p>
		 *  <u>have to (!)</u> implemented in a concrete phase in its method!
		 * </p>
		 * 
		 * @see PhaseALogic
		 */
		public void setNextPhase()
		{

		}
		
		/**
		 * sets the a nextPhase in the concrete phaseLogic
		 * 
		 * 
		 * @see PhaseALogic
		 */
		public final void setNextPhase(EPhases nextPhase)
		{
			this.nextPhase=nextPhase;
		}
		
		
		//////////////////
		//...GETS PHASES//
		//////////////////
		public final EPhases getCurrentPhase()
		{
			return this.currentPhase;
		}
		
		public final EPhases getLastPhase()
		{
			return this.lastPhase;
		}
		
		public final EPhases getNextPhase()
		{
			return this.nextPhase;
		}
		
		////////////
		//...LOGIC//
		////////////
	
		/**
		 * calls {@link #setActionsToRun()} and then runs the whole logic of the real phase 
		 * 
		 * <p>
		 * runs the logic which is implemented in a concrete phaseLogic in its method)
		 * can be override by a concrete phase 
		 * </p>
		 * 
		 * @see PhaseALogic
		 */
		public void runLogic()
		{
			
			//#BugHunting
			System.out.println("IN PHASE TEMPLATE LOGIC: - PHASE OF GAME: " + this.game.getCurrentPhase() + "\n - ePHASE OF GAME: " + this.game.getCurrentEPhase());
			
			this.isRunning=true;
			
			this.setActionsToRun();
			
			
			if (!this.doNothing)
			{
				this.doPreAction();
				this.update();
				if(this.doRunActionA)
				{
					this.runActionA();
					this.resetInput();
				}
			}
			
			
			//this.waitForAValidInput();
			
			if (!this.doNothing)
			{
				if(this.doRunActionB)
				{
					this.runActionB();
					this.resetInput();
				}
			}
		
			//this.waitForAValidInput();
			
			if (!this.doNothing)
			{
				if(this.doRunActionC)
				{
					this.runActionC();
					this.resetInput();
				}
			}


			//this.waitForAValidInput();
			
			if (!this.doNothing)
			{
				this.doAfterAction();
			}
			
			this.isRunning=false;
			
			//this.waitForRestart();
			//System.out.println("do suspend A PHASE...");
			//System.out.println("is interrupted? " +this.isInterrupted());
			this.suspend();
			//System.out.println("RESTART A PHASE...");
			
			this.runPhase();	//"Restart phase and run it again";
		}
		
		/**
		 * does some things before start the real logic of the phase
		 * 
		 * <p>
		 * can do some extra things which are implemented in a concrete phase in its method
		 * can be override by a concrete phase 
		 * </p>
		 * 
		 * @see PhaseAASCII
		 * @see PhaseAGUI
		 */
		public abstract void doPreAction();
		
		/**
		 * does some things after the real logic of the phase terminate
		 * 
		 * <p>
		 * can do some extra things which are implemented in a concrete phase in its method
		 * can be override by a concrete phase 
		 * </p>
		 * 
		 * @see PhaseAASCII
		 * @see PhaseAGUI
		 */
		public void doAfterAction()
		{

		}
		
		
		/**
		 * resets the input: the variables {@code isInputNew} and {@code isInputValid}.
		 * 
		 * <p>
		 * have to called between each instruction of a concrete phase logic
		 * </p>
		 * 
		 */
		public final void resetInput()
		{
			this.isInputNew=false;
			this.isInputValid=false;
		}
	
		//////////////
		//...WAITING//
		//////////////
	
		
		/**
		 * sleeps 10 milliseconds
		 * 
		 * <p>
		 * used for in each while loop which is waiting for a valid input
		 * </p>
		 * 
		 */
		public final void waitForAValidInput()
		{
			try 
			{
				Thread.sleep(10); //OLD=500
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
		
		/**
		 * sleeps 5 milliseconds
		 * 
		 * <p>
		 * used for in each while loop which is waiting for a new input
		 * </p>
		 * 
		 */
		public final void waitForANewInput()
		{
			try 
			{
				Thread.sleep(5);//OLD=200
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
		
//		/**
//		 * is while loop(Condition is isRunning==false) and sleeps 1000 milliseconds
//		 * 
//		 * <p>
//		 * used for in each while loop which is waiting for a "restart"
//		 * </p>
//		 * 
//		 */
//		public final void waitForRestart()
//		{
//			while(!this.isRunning)
//			{
//				try 
//				{
//					Thread.sleep(1000);
//				} 
//				catch (InterruptedException e) 
//				{
//					e.printStackTrace();
//				}
//			}
//			
//		}
		
		//////////////////////
		//...CHECKING INPUTS//
		//////////////////////
		
		/**
		 * check if the {@code input} is a basic instruction and execute the basic actions.
		 * 
		 *	<table border="1">
		 * 		<tr>
		 * 			<th>Possible input</th>
		 * 			<th>Possible input</th>
		 * 			<th>Possible input</th>
		 * 			<th>Basic action description</th>
		 * 			<th>Basic action link</th>
		 *		</tr>
		 *	
		 *		<tr>
		 *			<small>
		 * 				<th>Exit</th>
		 * 				<th>exit</th>
		 * 				<th>-</th>
		 * 				<th>finish the game</th>
		 * 				<th>{@link #doExit()}</th>
		 * 			</small>
		 *		</tr>
		 *	
		 *	</table>
		 * @throws DoNothingException 
		 *
		 */
		protected final boolean checkBasicInputs(Object input)// throws DoNothingException
		{
			boolean validCast=this.tryCastToString(input);
			
			if(!validCast)
			{
				return false;
			}
			else
			{
				boolean validBasicInput=true;
				
				if (input.equals("Exit") || input.equals("exit"))
				{
					this.doExit();
				}
				else
				{
					validBasicInput=false;
				}
				

				if(validBasicInput)
				{
					//this.interrupt(); //TODO HERE
					//this.isRunning=false;
					this.doNothing=true;
					//throw new DoNothingException();
				}
				
				return validBasicInput;
			
			}
			
		}
		
	///////////
	//ACTIONS//
	///////////
		
		///////////////////
		//TRY BASIC CASTS//
		///////////////////
		/**
		 * try to cast the input object into a String
		 *
		 * @return 
		 * 		<ul>
		 * 			<li> <u>true</u> if the cast was <u>successful</u> </li>
		 * 			<li> <u>false</u> if the input was <u>not successful</u> </li>
		 * 		</ul>
		 */
		public final boolean tryCastToString(Object input)
		{
			boolean valid = true;
			
			try
			{
				String inputString=input.toString();
			}
			catch(ClassCastException e)
			{
				valid =false;
			}
			
			
			return valid;
		}
		
		/**
		 * try to cast the input object into a Integer
		 *
		 * @return 
		 * 		<ul>
		 * 			<li> <u>true</u> if the cast was <u>successful</u> </li>
		 * 			<li> <u>false</u> if the input was <u>not successful</u> </li>
		 * 		</ul>
		 */
		public final boolean tryCastToInteger(Object input)
		{
			boolean valid = true;
			
			String inputString="";
			//Cast to String
			try
			{
				inputString=input.toString();
			}
			catch(ClassCastException e)
			{
				valid = false;
			}
			
			//cast String to Integer:
			try
			{
				int inputInteger=Integer.parseInt(inputString);
			}
			catch(NumberFormatException e)
			{
				valid = false;
			}
			
			return valid;
		}
		
		///////////////////
		//DO BASIC CASTS//
		///////////////////
		/**
		 * do cast the input object into a String
		 *
		 * @return 
		 * 		<ul>
		 * 			<li> <u>a String</u> if the cast was <u>successful</u> </li>
		 * 			<li> <u>null</u> if the input was <u>not successful</u> </li>
		 * 		</ul>
		 */
		public final String doCastToString(Object input)
		{
			
			try
			{
				String inputString=input.toString();
				return inputString;
			}
			catch(ClassCastException e)
			{
				return null;
			}
		}
		
		/**
		 * do  cast the input object into a Integer
		 *
		 * @return 
		 * 		<ul>
		 * 			<li> <u>true</u> if the cast was <u>successful</u> </li>
		 * 			<li> <u>false</u> if the input was <u>not successful</u> </li>
		 * 		</ul>
		 */
		public final Integer doCastToInteger(Object input)
		{
			String inputString="";
			//Cast to String
			try
			{
				inputString=input.toString();
			}
			catch(ClassCastException e)
			{
				return null;
			}
			
			//cast String to Integer:
			try
			{
				int inputInteger=Integer.parseInt(inputString);
				return inputInteger;
			}
			catch(ClassCastException e)
			{
				return null;
			}
		}
		
	
		
		///////////////////////////////
		//SET AND CHECK ACTION INPUTS//
		///////////////////////////////
			
			///////
			//ALL//
			///////
			/**
			* calls every setInputMethod of the concrete phaseLogic and try to set everywhere the same input {@code message}.
			* 
			* <p>
			* 	This method <u>can only call by a GUIPhase</u> and only if the game is running in the GUI mode!
			* 	For <u>example</u> if in the GUIPhase contains an "ExitButton" then will the button calls setAllInput("Exit")
			* </p>
			* 
			* <p>
			* 	The <u>message</u> should be a valid basic input (for <u>more details</u> see the JavaDoc of {@link #checkBasicInputs(String)}
			* </p>
			* 
			* <p>
			* 	Can do some extra things which are <u>implemented in a concrete phaseLogic</u> in its method (should be call each set-method of actions to set the message as input)
			* 	Can be override by a concrete phase 
			* </p>
			* 
			* @see PhaseAASCII
		 	* @see PhaseAGUI
		 	* @see IPhase
		 	*/
			@Override
			public final void setAllInput(String message)
			{
				System.out.println("IN PHASE TEMPLATE LOGIC: MESSAGE IS " +message);
				this.setInputA(message);
				this.setInputB(message);
				this.setInputC(message);
			}
		
			////////////
			//ACTION A//
			////////////
			/**
			 * checks the input of Action A
			 * 
			 * <p>
			 * must be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic 
			 * </p>
			 * 
			 * @return 
			 * 		<ul>
			 * 			<li> <u>true</u> if the input was <u>valid</u> </li>
			 * 			<li> <u>false</u> if the input was <u>invalid</u> </li>
			 * 		</ul>
			 * 
			 * @see PhaseALogic
			 */
			public  boolean checkInputActionA(Object inputA)
			{ 
				return false;
			}
			
			/**
			 * try to set input of Action A and use the input in the concrete phaseLogic
			 * 
			 * <p>
			 * must be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @return 
			 * 		<ul>
			 * 			<li> <u>true</u> if the input was <u>set</u> </li>
			 * 			<li> <u>false</u> if the input was <u>not set</u> </li>
			 * 		</ul>
			 * 
			 * @see PhaseALogic
			 */
			public boolean setInputA(Object inputA)
			{
				return false;
			}
			
			
			////////////
			//ACTION B//
			////////////
			/**
			 * checks the input of Action B
			 * 
			 * <p>
			 * must be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic 
			 * </p>
			 * 
			 * @return 
			 * 		<ul>
			 * 			<li> <u>true</u> if the input was <u>valid</u> </li>
			 * 			<li> <u>false</u> if the input was <u>invalid</u> </li>
			 * 		</ul>
			 * 
			 * @see PhaseALogic
			 */
			public  boolean checkInputActionB(Object inputB)
			{
				return false;
			}
			
			/**
			 * try to set input of Action B and use the input in the concrete phaseLogic
			 * 
			 * <p>
			 * must be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @return 
			 * 		<ul>
			 * 			<li> <u>true</u> if the input was <u>set</u> </li>
			 * 			<li> <u>false</u> if the input was <u>not set</u> </li>
			 * 		</ul>
			 * 
			 * @see PhaseALogic
			 */
			public  boolean setInputB(Object inputB)
			{
				return false;
			}
			////////////
			//ACTION C//
			////////////
		
			/**
			 * checks the input of Action C
			 * 
			 * <p>
			 * must be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic 
			 * </p>
			 * 
			 * @return 
			 * 		<ul>
			 * 			<li> <u>true</u> if the input was <u>valid</u> </li>
			 * 			<li> <u>false</u> if the input was <u>invalid</u> </li>
			 * 		</ul>
			 * 
			 * @see PhaseALogic
			 */
			public  boolean checkInputActionC(Object inputC)
			{
				return false;
			}
			
			/**
			 * try to set input of Action C and use the input in the concrete phaseLogic
			 * 
			 * <p>
			 * must be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @return 
			 * 		<ul>
			 * 			<li> <u>true</u> if the input was <u>set</u> </li>
			 * 			<li> <u>false</u> if the input was <u>not set</u> </li>
			 * 		</ul>
			 * 
			 * @see PhaseALogic
			 */
			public  boolean setInputC(Object inputC)
			{
				return false;
			}
			
		///////////////
		//RUN ACTIONS//
		///////////////
			
		/**
		 * runs action A
		 */
		private final void runActionA()
		{
			this.doPreActionA();
			
			while(! this.isInputValid && !this.doNothing)
			{
				this.waitForAValidInput();
				this.doActionA();
			}
			
			if (!this.doNothing)
			{
				this.doAfterActionA();
			}
		}
		
		/**
		 * runs action B
		 */
		private final void runActionB()
		{
			this.doPreActionB();
			
			while(! this.isInputValid && !this.doNothing)
			{
				this.waitForAValidInput();
				this.doActionB();
			}
			
			if (!this.doNothing)
			{
				this.doAfterActionB();
			}
		
		}
		
		/**
		 * runs action C
		 */
		private final void runActionC()
		{
			this.doPreActionC();
			
			while(! this.isInputValid && !this.doNothing)
			{
				this.waitForAValidInput();
				this.doActionC();
			}
			
			if (!this.doNothing)
			{
				this.doAfterActionC();
			}
		}
		
		//////////////
		//DO ACTIONS//
		//////////////
		
			
			//////////////////////////
			//DO BASIC PHASE ACTIONS//
			//////////////////////////
			/**
			 * stops the game
			 */
			protected final void doExit()
			{
				System.out.println("EEEEEEEEEEEEEXXXXXXXXXXXXXXXXXXIIIIIIIIIIIIIIITTTTTTTTTTTT");
				this.currentPhase=EPhases.phaseExit;
				

				//System.exit(0);
				//TODO (CHANGED HERE...)
			}
			
			///////////////
			//DO ACTION A//
			///////////////
			
			/**
			 * does action A
			 */
			private final void doActionA()
			{
				this.actionAInput();
				
				while(!this.isInputNew)
				{
					this.waitForANewInput();
				}
			}
			
			/**
			 * does things before run Action A in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doPreActionA()
			{
				
			}
			
			/**
			 * <ul>
			 * 	<li> if ASCII: {@link #setInputA(Object)} (waiting for new input)</li>
			 * 	<li> else : do something else </li>
			 * </ul>
			 * 
			 * <p>
			 * can be implemented in a concrete phaseASCII/phaseGUI in its method
			 * can be override by a concrete phaseASCII/phaseGUI
			 * </p>
			 * 
			 * @see PhaseAASCII
			 * @see PhaseAGUI
			 */
			public void actionAInput()
			{
				
			}
			
			/**
			 * does things after ran Action A in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doAfterActionA()
			{
				
			}
			
			////////////
			//ACTION B//
			////////////
			
			/**
			 * does action B
			 */
			private final void doActionB()
			{
				this.actionBInput();
				
				while(!this.isInputNew)
				{
					this.waitForANewInput();
				}
			}
			
			/**
			 * does things before run Action B in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doPreActionB()
			{
				
			}
			
			/**
			 * <ul>
			 * 	<li> if ASCII: {@link #setInputB(Object)} (waiting for new input) </li>
			 * 	<li> else : do something else </li>
			 * </ul>
			 * 	
			 * <p>
			 * can be implemented in a concrete phaseASCII/phaseGUI in its method
			 * can be override by a concrete phaseASCII/phaseGUI
			 * </p>
			 * 
			 * @see PhaseAASCII
			 * @see PhaseAGUI
			 */
			public void actionBInput()
			{
				
			}
			
			/**
			 * does things after ran Action B in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doAfterActionB()
			{
				
			}
			////////////
			//ACTION C//
			////////////
		
			/**
			 * does action C
			 */
			private final void doActionC()
			{
				this.actionCInput();
				
				while(!this.isInputNew)
				{
					this.waitForANewInput();
				}
			}
			
			/**
			 * does things before run Action C in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doPreActionC()
			{
				
			}
			
			/**
			 * <ul>
			 * 	<li> if ASCII: {@link #setInputC(Object)} (waiting for new input)</li>
			 * 	<li> else : do something else </li>
			 * </ul>
			 * 
			 * <p>
			 * can be implemented in a concrete phaseASCII/phaseGUI in its method
			 * can be override by a concrete phaseASCII/phaseGUI
			 * </p>
			 * 
			 * @see PhaseAASCII
			 * @see PhaseAGUI
			 */
			public void actionCInput()
			{
				
			}
			
			/**
			 * does things after ran Action C in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseCLogic
			 */
			public void doAfterActionC()
			{
				
			}
		
	
	///////////
	//SETTERS//
	///////////
	
	@Override
	public void setMainPanel(JPanel mainPanel) 
	{
		
	}
	
	/**
	 * set {@code isRunning = false} 
	 */
	protected void endRunning()
	{
		this.isRunning=false;
	}

	///////////
	//GETTERS//
	///////////
	
	public final boolean getDoRunActionA()
	{
		return this.doRunActionA;
	}
	
	public final boolean getDoRunActionB()
	{
		return this.doRunActionB;
	}
	
	public final boolean getDoRunActionC()
	{
		return this.doRunActionC;
	}
	
	public final boolean getIsRunning()
	{
		return this.isRunning;
	}
	
	
	////////////////////////////////
	//ACTIVATE/DEATCTIVATE ACTIONS//
	////////////////////////////////
		
		////////////
		//ACTIVATE//
		////////////
	
		public final void activateActionA()
		{
			this.doRunActionA=true;
		}
		
		public final void activateActionB()
		{
			this.doRunActionB=true;
		}
		
		public final void activateActionC()
		{
			this.doRunActionC=true;
		}

		//////////////
		//DEACTIVATE//
		//////////////
		
		public final void deactivateActionA()
		{
			this.doRunActionA=false;
		}
		
		public final void deactivateActionB()
		{
			this.doRunActionB=false;
		}
		
		public final void deactivateActionC()
		{
			this.doRunActionC=false;
		}
		
		
		///////////////////
		//PRIVATE METHODS//
		///////////////////
		/**
		 * checks if an inputString is equals "Yes" or "yes" or "y" or "Y" and return if it is equal.
		 * @return 
		 * 		- true
		 * 		<br/>
		 * 		or
		 * 		<br/> 
		 * 		- false
		 */
		protected final boolean inputEqualsYes(String inputString)
		{
			if(inputString.equals("Yes") || inputString.equals("yes") || inputString.equals("y") || inputString.equals("Y"))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		
		/**
		 * checks if an inputString is equals "No" or "no" or "n" or "N" and return if it is equal.
		 * @return 
		 * 		- true
		 * 		<br/>
		 * 		or
		 * 		<br/> 
		 * 		- false
		 */
		protected final boolean inputEqualsNo(String inputString)
		{
			if(inputString.equals("No") || inputString.equals("no") || inputString.equals("n") || inputString.equals("N"))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		
	////////////////////
	//ONLY FOR TESTING//
	////////////////////

		/*
		 * The faking methods shouldn't be abstract, otherwise they have to be in classes where they don't need to be.
		 */
		////////////////
		//FAKING INPUTS//
		/////////////////
		
		@OnlyForTesting
		public  void fakeInputA(String inputA)
		{
			
		}
		
		//////////////////
		//FAKING BUTTONS//
		//////////////////
		
		//*PHASE A*//
		@OnlyForTesting
		public void fakeClickOK()
		{
			
		}
		
		
		@OnlyForTesting
		public  void fakeClickExit()
		{
			
		}
		
		//*PHASE EXIT*//
		@OnlyForTesting
		public void fakeClickNo()
		{
			
		}
		
		@OnlyForTesting
		public void fakeClickYes()
		{
			
		}
		
}
