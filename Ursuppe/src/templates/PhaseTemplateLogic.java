package templates;

import java.io.PrintStream;

import javax.swing.JPanel;

import enums.EActions;
import enums.EColor;
import enums.EPhases;
import enums.EPlayer;

import annotations.OnlyForTesting;

import gameObjectsASCII.PhaseAASCII;
import gameObjectsGUI.PhaseAGUI;
import helper.LanguageSetup;
import helper.UserInput;
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
public abstract class PhaseTemplateLogic extends LanguageSetup implements IPhase
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
	
	protected PrintStream outStream;
	protected PrintStream errorStream;
	
	protected Boolean isInTestMode=false;
	
	private Boolean isSetActionsOn=true;
	
	private Boolean restartOn=false;
	
	private EActions currentAction=EActions.ActionBeforRunning;
	private EActions nextAction=EActions.ActionDoAllPreAction;
	///////////////////
	//DO RUN ACTIONS?//
	///////////////////
	
	private boolean doRunActionA=false;
	private boolean doRunActionB=false;
	private boolean doRunActionC=false;
	private boolean doRunActionD=false;
	private boolean doRunActionE=false;
	private boolean doRunActionF=false;
	private boolean doRunActionG=false;
	private boolean doRunActionH=false;
	private boolean doRunActionI=false;
	private boolean doRunActionJ=false;
	private boolean doRunActionK=false;
	private boolean doRunActionL=false;
	private boolean doRunActionM=false;
	private boolean doRunActionN=false;
	private boolean doRunActionO=false;
	private boolean doRunActionP=false;
	private boolean doRunActionQ=false;
	private boolean doRunActionR=false;
	private boolean doRunActionS=false;
	private boolean doRunActionT=false;
	private boolean doRunActionU=false;
	private boolean doRunActionV=false;
	private boolean doRunActionW=false;
	private boolean doRunActionX=false;
	private boolean doRunActionY=false;
	private boolean doRunActionZ=false;
	
	//////////
	//THREAD//
	//////////
	
	protected boolean waiting=true;
	
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
		
		if(this.isInTestMode)
		{
			this.waiting=true;
		}
	}
	
	public void turnOnSetActionsToRun()
	{
		this.isSetActionsOn=true;
	}
	
	public void turnOffSetActionsToRun()
	{
		this.isSetActionsOn=false;
	}
	
	public void setOutStream(PrintStream out)
	{
		this.outStream=out;
	}
	public void setErrorStream(PrintStream err)
	{
		this.errorStream=err;
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
		 * activates/deactivates actions which should run in the concrete phaseLogic and resets all of them before running any of them
		 * 
		 * <p>
		 * implemented in a concrete phase in its method
		 * can be override by a concrete phase 
		 * </p>
		 * @return 
		 * 
		 * @see PhaseALogic
		 */
		public final void setActionsToRun()
		{
			if(this.isSetActionsOn)
			{
				this.changeActionToRun();
			}
		}
	
		public abstract void changeActionToRun();
		

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
			//this.outStream.println("IN PHASE TEMPLATE LOGIC: - PHASE OF GAME: " + this.game.getCurrentPhase() + "\n - ePHASE OF GAME: " + this.game.getCurrentEPhase());
			
			this.currentAction=EActions.ActionBeforRunning;
			
			this.isRunning=true;
			
			this.turnOffRestart();
			
			this.setActionsToRun();
			
			this.nextAction=EActions.ActionDoAllPreAction;
			
			this.checkIfRunInTestMode();
			
			this.currentAction=EActions.ActionDoAllPreAction;
			
			this.doAllPreAction();
			this.update();
			
			//...ACTION A
		
			
			if (!this.doNothing)
			{
				
				if(this.doRunActionA)
				{
					this.nextAction=EActions.ActionA;
					
					this.checkIfRunInTestMode();
					
					this.currentAction=EActions.ActionA;
					
					this.runActionA();
					this.resetInput();
				}
			}
			
			//...ACTION B
			//this.waitForAValidInput();
			
			
			if (!this.doNothing)
			{
				if(this.doRunActionB)
				{
					this.nextAction=EActions.ActionB;
					this.checkIfRunInTestMode();
					
					this.currentAction=EActions.ActionB;
					
					this.runActionB();
					this.resetInput();
				}
			}
		
			//...ACTION C
			//this.waitForAValidInput();
			
			
			if (!this.doNothing)
			{
				if(this.doRunActionC)
				{
					this.nextAction=EActions.ActionC;
					this.checkIfRunInTestMode();
					
					this.currentAction=EActions.ActionC;
					
					this.runActionC();
					this.resetInput();
				}
			}

			//...ACTION D
			//this.waitForAValidInput();
			
			
			if (!this.doNothing)
			{
				if(this.doRunActionD)
				{
					this.nextAction=EActions.ActionD;
					this.checkIfRunInTestMode();
					
					this.currentAction=EActions.ActionD;
					
					this.runActionD();
					this.resetInput();
				}
			}
			
			//...ACTION E
			//this.waitForAValidInput();
			
			
			if (!this.doNothing)
			{
				if(this.doRunActionE)
				{
					this.nextAction=EActions.ActionE;
					this.checkIfRunInTestMode();
					
					this.currentAction=EActions.ActionE;
					
					this.runActionE();
					this.resetInput();
				}
			}
			
			//...ACTION F
			//this.waitForAValidInput();
		
			
			if (!this.doNothing)
			{
				if(this.doRunActionF)
				{
					this.nextAction=EActions.ActionF;
					this.checkIfRunInTestMode();
					
					this.currentAction=EActions.ActionF;
					
					this.runActionF();
					this.resetInput();
				}
			}
			
			//...ACTION G
			//this.waitForAValidInput();
			
			
			if (!this.doNothing)
			{
				if(this.doRunActionG)
				{
					this.nextAction=EActions.ActionG;
					this.checkIfRunInTestMode();
					
					this.currentAction=EActions.ActionG;
					
					this.runActionG();
					this.resetInput();
				}
			}
			
			//...ACTION H
			//this.waitForAValidInput();
			
			
			if (!this.doNothing)
			{
				if(this.doRunActionH)
				{
					this.nextAction=EActions.ActionH;
					this.checkIfRunInTestMode();
					
					this.currentAction=EActions.ActionH;
					
					this.runActionH();
					this.resetInput();
				}
			}
			
			//...ACTION I
			//this.waitForAValidInput();
			
			
			if (!this.doNothing)
			{
				if(this.doRunActionI)
				{
					this.nextAction=EActions.ActionI;
					this.checkIfRunInTestMode();
					
					this.currentAction=EActions.ActionI;
					
					this.runActionI();
					this.resetInput();
				}
			}
			
			//...ACTION J
			//this.waitForAValidInput();
			
			
			if (!this.doNothing)
			{
				if(this.doRunActionJ)
				{
					this.nextAction=EActions.ActionJ;
					this.checkIfRunInTestMode();
					
					this.currentAction=EActions.ActionJ;
					
					this.runActionC();
					this.resetInput();
				}
			}
			
			//...ACTION K
			//this.waitForAValidInput();
			
			
			if (!this.doNothing)
			{
				if(this.doRunActionK)
				{
					this.nextAction=EActions.ActionK;
					this.checkIfRunInTestMode();
					
					this.currentAction=EActions.ActionK;
					
					this.runActionK();
					this.resetInput();
				}
			}
		
			//...ACTION L
			//this.waitForAValidInput();
		
			
			if (!this.doNothing)
			{
				if(this.doRunActionL)
				{
					this.nextAction=EActions.ActionL;
					this.checkIfRunInTestMode();
					
					this.currentAction=EActions.ActionL;
					
					this.runActionL();
					this.resetInput();
				}
			}
			
			//...ACTION M
			//this.waitForAValidInput();
			
			
			if (!this.doNothing)
			{
				if(this.doRunActionM)
				{
					this.nextAction=EActions.ActionM;
					this.checkIfRunInTestMode();
					
					this.currentAction=EActions.ActionM;
					
					this.runActionM();
					this.resetInput();
				}
			}
			
			//...ACTION N
			//this.waitForAValidInput();
			
			
			if (!this.doNothing)
			{
				if(this.doRunActionN)
				{
					this.nextAction=EActions.ActionN;
					this.checkIfRunInTestMode();
					
					this.currentAction=EActions.ActionN;
					
					this.runActionN();
					this.resetInput();
				}
			}
			
			//...ACTION O
			//this.waitForAValidInput();
			
			
			if (!this.doNothing)
			{
				if(this.doRunActionO)
				{
					this.nextAction=EActions.ActionO;
					this.checkIfRunInTestMode();
					
					this.currentAction=EActions.ActionO;
					
					this.runActionO();
					this.resetInput();
				}
			}
			
			//...ACTION P
			//this.waitForAValidInput();
			
			
			if (!this.doNothing)
			{
				if(this.doRunActionP)
				{
					this.nextAction=EActions.ActionP;
					this.checkIfRunInTestMode();
					
					this.currentAction=EActions.ActionP;
					
					this.runActionP();
					this.resetInput();
				}
			}
			
			//...ACTION Q
			//this.waitForAValidInput();
			
			
			if (!this.doNothing)
			{
				if(this.doRunActionQ)
				{
					this.nextAction=EActions.ActionQ;
					this.checkIfRunInTestMode();
					
					this.currentAction=EActions.ActionQ;
					
					this.runActionQ();
					this.resetInput();
				}
			}
			
			//...ACTION R
			//this.waitForAValidInput();
			
			if (!this.doNothing)
			{
				if(this.doRunActionR)
				{
					this.nextAction=EActions.ActionR;
					this.checkIfRunInTestMode();
					
					this.currentAction=EActions.ActionR;
					
					
					this.runActionR();
					this.resetInput();
				}
			}
			
			//...ACTION S
			//this.waitForAValidInput();
			
			
			if (!this.doNothing)
			{
				if(this.doRunActionS)
				{
					this.nextAction=EActions.ActionS;
					this.checkIfRunInTestMode();
					
					this.currentAction=EActions.ActionS;
					
					this.runActionS();
					this.resetInput();
				}
			}
			
			//...ACTION T
			//this.waitForAValidInput();
			
			if (!this.doNothing)
			{
				if(this.doRunActionT)
				{
					this.nextAction=EActions.ActionT;
					this.checkIfRunInTestMode();
					
					this.currentAction=EActions.ActionT;
					
					
					this.runActionT();
					this.resetInput();
				}
			}
			
			//...ACTION U
			//this.waitForAValidInput();
			
			if (!this.doNothing)
			{
				if(this.doRunActionU)
				{
					this.nextAction=EActions.ActionU;
					this.checkIfRunInTestMode();
					
					this.currentAction=EActions.ActionU;
					
					
					this.runActionU();
					this.resetInput();
				}
			}
			
			//...ACTION V
			//this.waitForAValidInput();
		
			
			if (!this.doNothing)
			{
				if(this.doRunActionV)
				{
					this.nextAction=EActions.ActionV;
					this.checkIfRunInTestMode();
					
					this.currentAction=EActions.ActionV;
					
					this.runActionV();
					this.resetInput();
				}
			}
			
			//...ACTION W
			//this.waitForAValidInput();
			
			
			if (!this.doNothing)
			{
				if(this.doRunActionW)
				{
					this.nextAction=EActions.ActionW;
					this.checkIfRunInTestMode();
					
					this.currentAction=EActions.ActionW;
					
					this.runActionW();
					this.resetInput();
				}
			}
			
			//...ACTION X
			//this.waitForAValidInput();
			
			
			if (!this.doNothing)
			{
				if(this.doRunActionX)
				{
					this.nextAction=EActions.ActionX;
					this.checkIfRunInTestMode();
					
					this.currentAction=EActions.ActionX;
					
					this.runActionX();
					this.resetInput();
				}
			}
			
			//...ACTION Y
			//this.waitForAValidInput();
		
			
			if (!this.doNothing)
			{
				if(this.doRunActionY)
				{
					this.nextAction=EActions.ActionY;
					this.checkIfRunInTestMode();
					
					this.currentAction=EActions.ActionY;
					
					this.runActionY();
					this.resetInput();
				}
			}
			
			//...ACTION Z
			//this.waitForAValidInput();
		
			if (!this.doNothing)
			{
				if(this.doRunActionZ)
				{
					this.nextAction=EActions.ActionZ;
					
					this.checkIfRunInTestMode();
					this.currentAction=EActions.ActionZ;
					
					this.runActionZ();
					this.resetInput();
				}
			}
			
			//...END
			
			this.isRunning=false;
			this.currentAction=EActions.ActionAfterRunning; //TODO add nextAction
			this.doAllAfterAction();
			
			this.waitForRestart();
		
		
			
			this.runPhase();	//"Restart phase and run it again";
		}

		private void waitForRestart() 
		{
			if(!this.restartOn)
			{	
				this.suspend();
			}
			else
			{
				//Do a restart
			}
			
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
		protected void doPreAction()
		{
			
		}
		
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
		protected void doAfterAction()
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
		
		public final void waitBeforePhaseChange()
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
				String strInput=this.doCastToString(input);
				if (strInput.toLowerCase().equals(this.rb.getString("instructionExit")))
				//if (input.equals("Exit") || input.equals("exit"))
				{
					this.doExit();
				}
				else
				{
					validBasicInput=false;
				}
				

				if(validBasicInput)
				{
					this.doNothing=true;
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
		
		
		/**
		 * try to cast the input object into a color
		 *
		 * @return 
		 * 		<ul>
		 * 			<li> <u>true</u> if the cast was <u>successful</u> </li>
		 * 			<li> <u>false</u> if the input was <u>not successful</u> </li>
		 * 		</ul>
		 */
		public final boolean tryCastToEColor(Object input)
		{
			boolean valid = false;
			
			String inputString=input.toString();

			EColor chosedColor=null;

			for(EColor color:EColor.class.getEnumConstants())
			{
				if(color.toString().equals(inputString))
				{
					chosedColor=color;

					valid=true;
				}
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
		
		/**
		 * do to cast the input object into a color
		 *
		 * @return 
		 * 		<ul>
		 * 			<li> <u>true</u> if the cast was <u>successful</u> </li>
		 * 			<li> <u>false</u> if the input was <u>not successful</u> </li>
		 * 		</ul>
		 */
		public final EColor doCastToEColor(Object input)
		{
			String inputString=input.toString();

			EColor chosedColor=null;

			for(EColor color:EColor.class.getEnumConstants())
			{
				if(color.toString().toLowerCase().equals(inputString.toLowerCase()))
				{
					return color;
				}
			}
				
			return chosedColor;
		}
		
		public void doLogicAfterAction() 
		{
	
		}
		
		public void doLogicPreAction()
		{
			
		}
		
		private final void doAllPreAction()
		{
			
			this.game.skipTailOrHeadPlayer(); //TODO Maybe delete this? And do it in each logic separately
			
			this.doLogicPreAction();
			this.doPreAction();
		}
		
		private final void doAllAfterAction()
		{
			//System.out.println("START IN PHASE TEMPLATE LOGIC DO ALL AFTER ACTION");
			
			this.doLogicAfterAction();
			this.doAfterAction();
			
			//System.out.println("END IN PHASE TEMPLATE LOGIC DO ALL AFTER ACTION");
		}
		
		protected final boolean nextPlayer()	//TODO CHECK THIS!!!
		{
			
			boolean validPlayer=this.game.nextPlayer();
			
			//System.out.println("NEXT PLAYER in phase template logic is " + validPlayer);
			
			if(this.isInTestMode)
			{
				this.turnOnWaiting();
			}
			
			//TODO HERE COMMENT
			this.doWaiting();
			return validPlayer;
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
				this.outStream.println("IN PHASE TEMPLATE LOGIC: MESSAGE IS " +message);
				this.setInputA(message);
				this.setInputB(message);
				this.setInputC(message);
				this.setInputD(message);
				this.setInputE(message);
				this.setInputF(message);
				this.setInputG(message);
				this.setInputH(message);
				this.setInputI(message);
				this.setInputJ(message);
				this.setInputK(message);
				this.setInputL(message);
				this.setInputM(message);
				this.setInputN(message);
				this.setInputO(message);
				this.setInputP(message);
				this.setInputQ(message);
				this.setInputR(message);
				this.setInputS(message);
				this.setInputT(message);
				this.setInputU(message);
				this.setInputV(message);
				this.setInputW(message);
				this.setInputX(message);
				this.setInputY(message);
				this.setInputZ(message);
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
			
			////////////
			//ACTION D//
			////////////
			/**
			 * checks the input of Action D
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
			public  boolean checkInputActionD(Object inputD)
			{ 
				return false;
			}
			
			/**
			 * try to set input of Action D and use the input in the concrete phaseLogic
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
			public boolean setInputD(Object inputD)
			{
				return false;
			}
			
			////////////
			//ACTION E//
			////////////
			/**
			 * checks the input of Action E
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
			public  boolean checkInputActionE(Object inputE)
			{ 
				return false;
			}
			
			/**
			 * try to set input of Action E and use the input in the concrete phaseLogic
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
			public boolean setInputE(Object inputE)
			{
				return false;
			}
			
			////////////
			//ACTION F//
			////////////
			/**
			 * checks the input of Action F
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
			public  boolean checkInputActionF(Object inputF)
			{ 
				return false;
			}
			
			/**
			 * try to set input of Action F and use the input in the concrete phaseLogic
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
			public boolean setInputF(Object inputF)
			{
				return false;
			}
			
			////////////
			//ACTION G//
			////////////
			/**
			 * checks the input of Action G
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
			public  boolean checkInputActionG(Object inputG)
			{ 
				return false;
			}
			
			/**
			 * try to set input of Action G and use the input in the concrete phaseLogic
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
			public boolean setInputG(Object inputG)
			{
				return false;
			}
			
			////////////
			//ACTION H//
			////////////
			/**
			 * checks the input of Action H
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
			public  boolean checkInputActionH(Object inputH)
			{ 
				return false;
			}
			
			/**
			 * try to set input of Action H and use the input in the concrete phaseLogic
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
			public boolean setInputH(Object inputH)
			{
				return false;
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
			public  boolean checkInputActionI(Object inputI)
			{ 
				return false;
			}
			
			/**
			 * try to set input of Action I and use the input in the concrete phaseLogic
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
			public boolean setInputI(Object inputI)
			{
				return false;
			}
			
			////////////
			//ACTION J//
			////////////
			/**
			 * checks the input of Action J
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
			public  boolean checkInputActionJ(Object inputJ)
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
			public boolean setInputJ(Object inputJ)
			{
				return false;
			}
			
			////////////
			//ACTION K//
			////////////
			/**
			 * checks the input of Action K
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
			public  boolean checkInputActionK(Object inputK)
			{ 
				return false;
			}
			
			/**
			 * try to set input of Action K and use the input in the concrete phaseLogic
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
			public boolean setInputK(Object inputK)
			{
				return false;
			}
			
			
			////////////
			//ACTION L//
			////////////
			/**
			 * checks the input of Action L
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
			public  boolean checkInputActionL(Object inputL)
			{ 
				return false;
			}
			
			/**
			 * try to set input of Action L and use the input in the concrete phaseLogic
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
			public boolean setInputL(Object inputL)
			{
				return false;
			}
			
			////////////
			//ACTION M//
			////////////
			/**
			 * checks the input of Action M
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
			public  boolean checkInputActionM(Object inputM)
			{ 
				return false;
			}
			
			/**
			 * try to set input of Action M and use the input in the concrete phaseLogic
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
			public boolean setInputM(Object inputM)
			{
				return false;
			}
			
			////////////
			//ACTION N//
			////////////
			/**
			 * checks the input of Action N
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
			public  boolean checkInputActionN(Object inputN)
			{ 
				return false;
			}
			
			/**
			 * try to set input of Action N and use the input in the concrete phaseLogic
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
			public boolean setInputN(Object inputN)
			{
				return false;
			}
			
			////////////
			//ACTION O//
			////////////
			/**
			 * checks the input of Action O
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
			public  boolean checkInputActionO(Object inputO)
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
			public boolean setInputO(Object inputO)
			{
				return false;
			}
			
			////////////
			//ACTION P//
			////////////
			/**
			 * checks the input of Action P
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
			public  boolean checkInputActionP(Object inputP)
			{ 
				return false;
			}
			
			/**
			 * try to set input of Action P and use the input in the concrete phaseLogic
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
			public boolean setInputP(Object inputP)
			{
				return false;
			}
			
			////////////
			//ACTION Q//
			////////////
			/**
			 * checks the input of Action Q
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
			public  boolean checkInputActionQ(Object inputQ)
			{ 
				return false;
			}
			
			/**
			 * try to set input of Action Q and use the input in the concrete phaseLogic
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
			public boolean setInputQ(Object inputQ)
			{
				return false;
			}
			
			////////////
			//ACTION R//
			////////////
			/**
			 * checks the input of Action R
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
			public  boolean checkInputActionR(Object inputR)
			{ 
				return false;
			}
			
			/**
			 * try to set input of Action R and use the input in the concrete phaseLogic
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
			public boolean setInputR(Object inputR)
			{
				return false;
			}
			
			////////////
			//ACTION S//
			////////////
			/**
			 * checks the input of Action S
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
			public  boolean checkInputActionS(Object inputS)
			{ 
				return false;
			}
			
			/**
			 * try to set input of Action S and use the input in the concrete phaseLogic
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
			public boolean setInputS(Object inputS)
			{
				return false;
			}
			
			////////////
			//ACTION T//
			////////////
			/**
			 * checks the input of Action T
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
			public  boolean checkInputActionT(Object inputT)
			{ 
				return false;
			}
			
			/**
			 * try to set input of Action T and use the input in the concrete phaseLogic
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
			public boolean setInputT(Object inputT)
			{
				return false;
			}
			
			////////////
			//ACTION U//
			////////////
			/**
			 * checks the input of Action U
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
			public  boolean checkInputActionU(Object inputU)
			{ 
				return false;
			}
			
			/**
			 * try to set input of Action U and use the input in the concrete phaseLogic
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
			public boolean setInputU(Object inputU)
			{
				return false;
			}
			
			////////////
			//ACTION V//
			////////////
			/**
			 * checks the input of Action V
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
			public  boolean checkInputActionV(Object inputV)
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
			public boolean setInputV(Object inputV)
			{
				return false;
			}
			
			////////////
			//ACTION W//
			////////////
			/**
			 * checks the input of Action W
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
			public  boolean checkInputActionW(Object inputW)
			{ 
				return false;
			}
			
			/**
			 * try to set input of Action W and use the input in the concrete phaseLogic
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
			public boolean setInputW(Object inputW)
			{
				return false;
			}
			
			////////////
			//ACTION X//
			////////////
			/**
			 * checks the input of Action X
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
			public  boolean checkInputActionX(Object inputX)
			{ 
				return false;
			}
			
			/**
			 * try to set input of Action X and use the input in the concrete phaseLogic
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
			public boolean setInputX(Object inputX)
			{
				return false;
			}
			
			////////////
			//ACTION Y//
			////////////
			/**
			 * checks the input of Action Y
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
			public  boolean checkInputActionY(Object inputY)
			{ 
				return false;
			}
			
			/**
			 * try to set input of Action Y and use the input in the concrete phaseLogic
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
			public boolean setInputY(Object inputY)
			{
				return false;
			}
			
			////////////
			//ACTION Z//
			////////////
			/**
			 * checks the input of Action Z
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
			public  boolean checkInputActionZ(Object inputZ)
			{ 
				return false;
			}
			
			/**
			 * try to set input of Action Z and use the input in the concrete phaseLogic
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
			public boolean setInputZ(Object inputZ)
			{
				return false;
			}
			
			
		///////////////
		//RUN ACTIONS//
		///////////////
			
		/**
		 * runs action A
		 */
		protected final void runActionA()
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
		protected final void runActionB()
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
		protected final void runActionC()
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
		
		/**
		 * runs action D
		 */
		protected final void runActionD()
		{
			this.doPreActionD();
			
			while(! this.isInputValid && !this.doNothing)
			{
				this.waitForAValidInput();
				this.doActionD();
			}
			
			if (!this.doNothing)
			{
				this.doAfterActionD();
			}
		}
		
		/**
		 * runs action E
		 */
		protected final void runActionE()
		{
			this.doPreActionE();
			
			while(! this.isInputValid && !this.doNothing)
			{
				this.waitForAValidInput();
				this.doActionE();
			}
			
			if (!this.doNothing)
			{
				this.doAfterActionE();
			}
		}
		
		/**
		 * runs action F
		 */
		protected final void runActionF()
		{
			this.doPreActionF();
			
			while(! this.isInputValid && !this.doNothing)
			{
				this.waitForAValidInput();
				this.doActionF();
			}
			
			if (!this.doNothing)
			{
				this.doAfterActionF();
			}
		}
		
		/**
		 * runs action G
		 */
		protected final void runActionG()
		{
			this.doPreActionG();
			
			while(! this.isInputValid && !this.doNothing)
			{
				this.waitForAValidInput();
				this.doActionG();
			}
			
			if (!this.doNothing)
			{
				this.doAfterActionG();
			}
		}
		
		/**
		 * runs action H
		 */
		protected final void runActionH()
		{
			this.doPreActionH();
			
			while(! this.isInputValid && !this.doNothing)
			{
				this.waitForAValidInput();
				this.doActionH();
			}
			
			if (!this.doNothing)
			{
				this.doAfterActionH();
			}
		}
		
		/**
		 * runs action I
		 */
		protected final void runActionI()
		{
			this.doPreActionI();
			
			while(! this.isInputValid && !this.doNothing)
			{
				this.waitForAValidInput();
				this.doActionI();
			}
			
			if (!this.doNothing)
			{
				this.doAfterActionI();
			}
		}
		
		/**
		 * runs action J
		 */
		protected final void runActionJ()
		{
			this.doPreActionJ();
			
			while(! this.isInputValid && !this.doNothing)
			{
				this.waitForAValidInput();
				this.doActionJ();
			}
			
			if (!this.doNothing)
			{
				this.doAfterActionJ();
			}
		}
		
		/**
		 * runs action K
		 */
		protected final void runActionK()
		{
			this.doPreActionK();
			
			while(! this.isInputValid && !this.doNothing)
			{
				this.waitForAValidInput();
				this.doActionK();
			}
			
			if (!this.doNothing)
			{
				this.doAfterActionK();
			}
		}
		
		/**
		 * runs action L
		 */
		protected final void runActionL()
		{
			this.doPreActionL();
			
			while(! this.isInputValid && !this.doNothing)
			{
				this.waitForAValidInput();
				this.doActionL();
			}
			
			if (!this.doNothing)
			{
				this.doAfterActionL();
			}
		}
		
		/**
		 * runs action M
		 */
		protected final void runActionM()
		{
			this.doPreActionM();
			
			while(! this.isInputValid && !this.doNothing)
			{
				this.waitForAValidInput();
				this.doActionM();
			}
			
			if (!this.doNothing)
			{
				this.doAfterActionM();
			}
		}
		
		/**
		 * runs action N
		 */
		protected final void runActionN()
		{
			this.doPreActionN();
			
			while(! this.isInputValid && !this.doNothing)
			{
				this.waitForAValidInput();
				this.doActionN();
			}
			
			if (!this.doNothing)
			{
				this.doAfterActionN();
			}
		}
		
		/**
		 * runs action O
		 */
		protected final void runActionO()
		{
			this.doPreActionO();
			
			while(! this.isInputValid && !this.doNothing)
			{
				this.waitForAValidInput();
				this.doActionO();
			}
			
			if (!this.doNothing)
			{
				this.doAfterActionO();
			}
		}
		
		/**
		 * runs action P
		 */
		protected final void runActionP()
		{
			this.doPreActionP();
			
			while(! this.isInputValid && !this.doNothing)
			{
				this.waitForAValidInput();
				this.doActionP();
			}
			
			if (!this.doNothing)
			{
				this.doAfterActionP();
			}
		}
		
		/**
		 * runs action Q
		 */
		protected final void runActionQ()
		{
			this.doPreActionQ();
			
			while(! this.isInputValid && !this.doNothing)
			{
				this.waitForAValidInput();
				this.doActionQ();
			}
			
			if (!this.doNothing)
			{
				this.doAfterActionQ();
			}
		}
		
		/**
		 * runs action R
		 */
		protected final void runActionR()
		{
			this.doPreActionR();
			
			while(! this.isInputValid && !this.doNothing)
			{
				this.waitForAValidInput();
				this.doActionR();
			}
			
			if (!this.doNothing)
			{
				this.doAfterActionR();
			}
		}
		
		/**
		 * runs action S
		 */
		protected final void runActionS()
		{
			this.doPreActionS();
			
			while(! this.isInputValid && !this.doNothing)
			{
				this.waitForAValidInput();
				this.doActionS();
			}
			
			if (!this.doNothing)
			{
				this.doAfterActionS();
			}
		}
		
		/**
		 * runs action T
		 */
		protected final void runActionT()
		{
			this.doPreActionT();
			
			while(! this.isInputValid && !this.doNothing)
			{
				this.waitForAValidInput();
				this.doActionT();
			}
			
			if (!this.doNothing)
			{
				this.doAfterActionT();
			}
		}
		
		/**
		 * runs action U
		 */
		protected final void runActionU()
		{
			this.doPreActionU();
			
			while(! this.isInputValid && !this.doNothing)
			{
				this.waitForAValidInput();
				this.doActionU();
			}
			
			if (!this.doNothing)
			{
				this.doAfterActionU();
			}
		}
		
		/**
		 * runs action V
		 */
		protected final void runActionV()
		{
			this.doPreActionA();
			
			while(! this.isInputValid && !this.doNothing)
			{
				this.waitForAValidInput();
				this.doActionV();
			}
			
			if (!this.doNothing)
			{
				this.doAfterActionV();
			}
		}
		
		/**
		 * runs action W
		 */
		protected final void runActionW()
		{
			this.doPreActionW();
			
			while(! this.isInputValid && !this.doNothing)
			{
				this.waitForAValidInput();
				this.doActionW();
			}
			
			if (!this.doNothing)
			{
				this.doAfterActionW();
			}
		}
		
		/**
		 * runs action X
		 */
		protected final void runActionX()
		{
			this.doPreActionX();
			
			while(! this.isInputValid && !this.doNothing)
			{
				this.waitForAValidInput();
				this.doActionX();
			}
			
			if (!this.doNothing)
			{
				this.doAfterActionX();
			}
		}
		
		/**
		 * runs action Y
		 */
		protected final void runActionY()
		{
			this.doPreActionY();
			
			while(! this.isInputValid && !this.doNothing)
			{
				this.waitForAValidInput();
				this.doActionY();
			}
			
			if (!this.doNothing)
			{
				this.doAfterActionY();
			}
		}
		
		/**
		 * runs action Z
		 */
		protected final void runActionZ()
		{
			this.doPreActionZ();
			
			while(! this.isInputValid && !this.doNothing)
			{
				this.waitForAValidInput();
				this.doActionZ();
			}
			
			if (!this.doNothing)
			{
				this.doAfterActionZ();
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
				this.currentPhase=EPhases.phaseExit;
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
		
			
			///////////////
			//DO ACTION D//
			///////////////
			
			/**
			 * does action D
			 */
			private final void doActionD()
			{
				this.actionDInput();
				
				while(!this.isInputNew)
				{
					this.waitForANewInput();
				}
			}
			
			/**
			 * does things before run Action D in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doPreActionD()
			{
				
			}

			/**
			 * <ul>
			 * 	<li> if ASCII: {@link #setInputD(Object)} (waiting for new input)</li>
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
			public void actionDInput()
			{
				
			}
			
			/**
			 * does things after ran Action D in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doAfterActionD()
			{
				
			}
	
			///////////////
			//DO ACTION E//
			///////////////
			
			/**
			 * does action E
			 */
			private final void doActionE()
			{
				this.actionEInput();
				
				while(!this.isInputNew)
				{
					this.waitForANewInput();
				}
			}
			
			/**
			 * does things before run Action E in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doPreActionE()
			{
				
			}

			/**
			 * <ul>
			 * 	<li> if ASCII: {@link #setInputE(Object)} (waiting for new input)</li>
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
			public void actionEInput()
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
			public void doAfterActionE()
			{
				
			}
			
			///////////////
			//DO ACTION F//
			///////////////
			
			/**
			 * does action F
			 */
			private final void doActionF()
			{
				this.actionFInput();
				
				while(!this.isInputNew)
				{
					this.waitForANewInput();
				}
			}
			
			/**
			 * does things before run Action F in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doPreActionF()
			{
				
			}

			/**
			 * <ul>
			 * 	<li> if ASCII: {@link #setInputF(Object)} (waiting for new input)</li>
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
			public void actionFInput()
			{
				
			}
			
			/**
			 * does things after ran Action F in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doAfterActionF()
			{
				
			}
			
			///////////////
			//DO ACTION G//
			///////////////
			
			/**
			 * does action G
			 */
			private final void doActionG()
			{
				this.actionGInput();
				
				while(!this.isInputNew)
				{
					this.waitForANewInput();
				}
			}
			
			/**
			 * does things before run Action G in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doPreActionG()
			{
				
			}

			/**
			 * <ul>
			 * 	<li> if ASCII: {@link #setInputG(Object)} (waiting for new input)</li>
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
			public void actionGInput()
			{
				
			}
			
			/**
			 * does things after ran Action G in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doAfterActionG()
			{
				
			}
			
			///////////////
			//DO ACTION H//
			///////////////
			
			/**
			 * does action A
			 */
			private final void doActionH()
			{
				this.actionHInput();
				
				while(!this.isInputNew)
				{
					this.waitForANewInput();
				}
			}
			
			/**
			 * does things before run Action H in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doPreActionH()
			{
				
			}

			/**
			 * <ul>
			 * 	<li> if ASCII: {@link #setInputH(Object)} (waiting for new input)</li>
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
			public void actionHInput()
			{
				
			}
			
			/**
			 * does things after ran Action H in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doAfterActionH()
			{
				
			}
			
			///////////////
			//DO ACTION I//
			///////////////
			
			/**
			 * does action I
			 */
			private final void doActionI()
			{
				this.actionIInput();
				
				while(!this.isInputNew)
				{
					this.waitForANewInput();
				}
			}
			
			/**
			 * does things before run Action I in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doPreActionI()
			{
				
			}

			/**
			 * <ul>
			 * 	<li> if ASCII: {@link #setInputI(Object)} (waiting for new input)</li>
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
			public void actionIInput()
			{
				
			}
			
			/**
			 * does things after ran Action I in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doAfterActionI()
			{
				
			}
			
			///////////////
			//DO ACTION J//
			///////////////
			
			/**
			 * does action J
			 */
			private final void doActionJ()
			{
				this.actionJInput();
				
				while(!this.isInputNew)
				{
					this.waitForANewInput();
				}
			}
			
			/**
			 * does things before run Action J in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doPreActionJ()
			{
				
			}

			/**
			 * <ul>
			 * 	<li> if ASCII: {@link #setInputJ(Object)} (waiting for new input)</li>
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
			public void actionJInput()
			{
				
			}
			
			/**
			 * does things after ran Action J in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doAfterActionJ()
			{
				
			}
			
			///////////////
			//DO ACTION K//
			///////////////
			
			/**
			 * does action K
			 */
			private final void doActionK()
			{
				this.actionKInput();
				
				while(!this.isInputNew)
				{
					this.waitForANewInput();
				}
			}
			
			/**
			 * does things before run Action K in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doPreActionK()
			{
				
			}

			/**
			 * <ul>
			 * 	<li> if ASCII: {@link #setInputK(Object)} (waiting for new input)</li>
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
			public void actionKInput()
			{
				
			}
			
			/**
			 * does things after ran Action K in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doAfterActionK()
			{
				
			}
			
			///////////////
			//DO ACTION L//
			///////////////
			
			/**
			 * does action L
			 */
			private final void doActionL()
			{
				this.actionLInput();
				
				while(!this.isInputNew)
				{
					this.waitForANewInput();
				}
			}
			
			/**
			 * does things before run Action L in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doPreActionL()
			{
				
			}

			/**
			 * <ul>
			 * 	<li> if ASCII: {@link #setInputL(Object)} (waiting for new input)</li>
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
			public void actionLInput()
			{
				
			}
			
			/**
			 * does things after ran Action L in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doAfterActionL()
			{
				
			}
			
			///////////////
			//DO ACTION M//
			///////////////
			
			/**
			 * does action M
			 */
			private final void doActionM()
			{
				this.actionMInput();
				
				while(!this.isInputNew)
				{
					this.waitForANewInput();
				}
			}
			
			/**
			 * does things before run Action M in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doPreActionM()
			{
				
			}

			/**
			 * <ul>
			 * 	<li> if ASCII: {@link #setInputM(Object)} (waiting for new input)</li>
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
			public void actionMInput()
			{
				
			}
			
			/**
			 * does things after ran Action M in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doAfterActionM()
			{
				
			}
			
			///////////////
			//DO ACTION N//
			///////////////
			
			/**
			 * does action N
			 */
			private final void doActionN()
			{
				this.actionNInput();
				
				while(!this.isInputNew)
				{
					this.waitForANewInput();
				}
			}
			
			/**
			 * does things before run Action N in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doPreActionN()
			{
				
			}

			/**
			 * <ul>
			 * 	<li> if ASCII: {@link #setInputN(Object)} (waiting for new input)</li>
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
			public void actionNInput()
			{
				
			}
			
			/**
			 * does things after ran Action N in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doAfterActionN()
			{
				
			}
			
			///////////////
			//DO ACTION O//
			///////////////
			
			/**
			 * does action O
			 */
			private final void doActionO()
			{
				this.actionOInput();
				
				while(!this.isInputNew)
				{
					this.waitForANewInput();
				}
			}
			
			/**
			 * does things before run Action O in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doPreActionO()
			{
				
			}

			/**
			 * <ul>
			 * 	<li> if ASCII: {@link #setInputO(Object)} (waiting for new input)</li>
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
			public void actionOInput()
			{
				
			}
			
			/**
			 * does things after ran Action O in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doAfterActionO()
			{
				
			}
			
			///////////////
			//DO ACTION P//
			///////////////
			
			/**
			 * does action P
			 */
			private final void doActionP()
			{
				this.actionPInput();
				
				while(!this.isInputNew)
				{
					this.waitForANewInput();
				}
			}
			
			/**
			 * does things before run Action P in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doPreActionP()
			{
				
			}

			/**
			 * <ul>
			 * 	<li> if ASCII: {@link #setInputP(Object)} (waiting for new input)</li>
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
			public void actionPInput()
			{
				
			}
			
			/**
			 * does things after ran Action P in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doAfterActionP()
			{
				
			}
			
			///////////////
			//DO ACTION Q//
			///////////////
			
			/**
			 * does action Q
			 */
			private final void doActionQ()
			{
				this.actionQInput();
				
				while(!this.isInputNew)
				{
					this.waitForANewInput();
				}
			}
			
			/**
			 * does things before run Action Q in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doPreActionQ()
			{
				
			}

			/**
			 * <ul>
			 * 	<li> if ASCII: {@link #setInputQ(Object)} (waiting for new input)</li>
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
			public void actionQInput()
			{
				
			}
			
			/**
			 * does things after ran Action Q in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doAfterActionQ()
			{
				
			}
			
			///////////////
			//DO ACTION R//
			///////////////
			
			/**
			 * does action R
			 */
			private final void doActionR()
			{
				this.actionRInput();
				
				while(!this.isInputNew)
				{
					this.waitForANewInput();
				}
			}
			
			/**
			 * does things before run Action R in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doPreActionR()
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
			public void actionRInput()
			{
				
			}
			
			/**
			 * does things after ran Action R in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doAfterActionR()
			{
				
			}
			
			///////////////
			//DO ACTION S//
			///////////////
			
			/**
			 * does action S
			 */
			private final void doActionS()
			{
				this.actionSInput();
				
				while(!this.isInputNew)
				{
					this.waitForANewInput();
				}
			}
			
			/**
			 * does things before run Action S in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doPreActionS()
			{
				
			}

			/**
			 * <ul>
			 * 	<li> if ASCII: {@link #setInputS(Object)} (waiting for new input)</li>
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
			public void actionSInput()
			{
				
			}
			
			/**
			 * does things after ran Action S in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doAfterActionS()
			{
				
			}
			
			///////////////
			//DO ACTION T//
			///////////////
			
			/**
			 * does action T
			 */
			private final void doActionT()
			{
				this.actionTInput();
				
				while(!this.isInputNew)
				{
					this.waitForANewInput();
				}
			}
			
			/**
			 * does things before run Action T in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doPreActionT()
			{
				
			}

			/**
			 * <ul>
			 * 	<li> if ASCII: {@link #setInputT(Object)} (waiting for new input)</li>
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
			public void actionTInput()
			{
				
			}
			
			/**
			 * does things after ran Action T in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doAfterActionT()
			{
				
			}
			
			///////////////
			//DO ACTION U//
			///////////////
			
			/**
			 * does action U 
			 */
			private final void doActionU()
			{
				this.actionUInput();
				
				while(!this.isInputNew)
				{
					this.waitForANewInput();
				}
			}
			
			/**
			 * does things before run Action U in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doPreActionU()
			{
				
			}

			/**
			 * <ul>
			 * 	<li> if ASCII: {@link #setInputU(Object)} (waiting for new input)</li>
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
			public void actionUInput()
			{
				
			}
			
			/**
			 * does things after ran Action U in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doAfterActionU()
			{
				
			}
			
			///////////////
			//DO ACTION V//
			///////////////
			
			/**
			 * does action V
			 */
			private final void doActionV()
			{
				this.actionVInput();
				
				while(!this.isInputNew)
				{
					this.waitForANewInput();
				}
			}
			
			/**
			 * does things before run Action V in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doPreActionV()
			{
				
			}

			/**
			 * <ul>
			 * 	<li> if ASCII: {@link #setInputV(Object)} (waiting for new input)</li>
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
			public void actionVInput()
			{
				
			}
			
			/**
			 * does things after ran Action V in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doAfterActionV()
			{
				
			}
			
			///////////////
			//DO ACTION W//
			///////////////
			
			/**
			 * does action W
			 */
			private final void doActionW()
			{
				this.actionWInput();
				
				while(!this.isInputNew)
				{
					this.waitForANewInput();
				}
			}
			
			/**
			 * does things before run Action W in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doPreActionW()
			{
				
			}

			/**
			 * <ul>
			 * 	<li> if ASCII: {@link #setInputW(Object)} (waiting for new input)</li>
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
			public void actionWInput()
			{
				
			}
			
			/**
			 * does things after ran Action W in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doAfterActionW()
			{
				
			}
			
			///////////////
			//DO ACTION X//
			///////////////
			
			/**
			 * does action X
			 */
			private final void doActionX()
			{
				this.actionXInput();
				
				while(!this.isInputNew)
				{
					this.waitForANewInput();
				}
			}
			
			/**
			 * does things before run Action X in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doPreActionX()
			{
				
			}

			/**
			 * <ul>
			 * 	<li> if ASCII: {@link #setInputX(Object)} (waiting for new input)</li>
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
			public void actionXInput()
			{
				
			}
			
			/**
			 * does things after ran Action X in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doAfterActionX()
			{
				
			}
			
			///////////////
			//DO ACTION Y//
			///////////////
			
			/**
			 * does action Y
			 */
			private final void doActionY()
			{
				this.actionYInput();
				
				while(!this.isInputNew)
				{
					this.waitForANewInput();
				}
			}
			
			/**
			 * does things before run Action Y in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doPreActionY()
			{
				
			}

			/**
			 * <ul>
			 * 	<li> if ASCII: {@link #setInputY(Object)} (waiting for new input)</li>
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
			public void actionYInput()
			{
				
			}
			
			/**
			 * does things after ran Action Y in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doAfterActionY()
			{
				
			}
			
			///////////////
			//DO ACTION Z//
			///////////////
			
			/**
			 * does action Z
			 */
			private final void doActionZ()
			{
				this.actionZInput();
				
				while(!this.isInputNew)
				{
					this.waitForANewInput();
				}
			}
			
			/**
			 * does things before run Action Z in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doPreActionZ()
			{
				
			}

			/**
			 * <ul>
			 * 	<li> if ASCII: {@link #setInputZ(Object)} (waiting for new input)</li>
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
			public void actionZInput()
			{
				
			}
			
			/**
			 * does things after ran Action Z in the concrete phaseLogic
			 * 
			 * <p>
			 * can be implemented in a concrete phaseLogic in its method
			 * can be override by a concrete phaseLogic
			 * </p>
			 * 
			 * @see PhaseALogic
			 */
			public void doAfterActionZ()
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
	
	public final boolean getDoRunActionD()
	{
		return this.doRunActionD;
	}
	
	public final boolean getDoRunActionE()
	{
		return this.doRunActionE;
	}
	
	public final boolean getDoRunActionF()
	{
		return this.doRunActionF;
	}
	
	public final boolean getDoRunActionG()
	{
		return this.doRunActionG;
	}
	
	public final boolean getDoRunActionH()
	{
		return this.doRunActionH;
	}
	
	public final boolean getDoRunActionI()
	{
		return this.doRunActionI;
	}
	
	public final boolean getDoRunActionJ()
	{
		return this.doRunActionJ;
	}
	
	public final boolean getDoRunActionK()
	{
		return this.doRunActionK;
	}
	
	public final boolean getDoRunActionL()
	{
		return this.doRunActionL;
	}
	
	public final boolean getDoRunActionM()
	{
		return this.doRunActionM;
	}
	
	public final boolean getDoRunActionN()
	{
		return this.doRunActionN;
	}
	
	public final boolean getDoRunActionO()
	{
		return this.doRunActionO;
	}
	
	public final boolean getDoRunActionP()
	{
		return this.doRunActionP;
	}
	
	public final boolean getDoRunActionQ()
	{
		return this.doRunActionQ;
	}
	
	public final boolean getDoRunActionR()
	{
		return this.doRunActionR;
	}
	
	public final boolean getDoRunActionS()
	{
		return this.doRunActionS;
	}
	
	public final boolean getDoRunActionT()
	{
		return this.doRunActionT;
	}
	
	public final boolean getDoRunActionU()
	{
		return this.doRunActionU;
	}
	
	public final boolean getDoRunActionV()
	{
		return this.doRunActionV;
	}
	
	public final boolean getDoRunActionW()
	{
		return this.doRunActionW;
	}
	
	public final boolean getDoRunActionX()
	{
		return this.doRunActionX;
	}
	
	public final boolean getDoRunActionY()
	{
		return this.doRunActionY;
	}
	
	public final boolean getDoRunActionZ()
	{
		return this.doRunActionZ;
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
		
		public final void activateActionD()
		{
			this.doRunActionD=true;
		}
		
		public final void activateActionE()
		{
			this.doRunActionE=true;
		}
		
		public final void activateActionF()
		{
			this.doRunActionF=true;
		}
		
		public final void activateActionG()
		{
			this.doRunActionG=true;
		}
		
		public final void activateActionH()
		{
			this.doRunActionH=true;
		}
		
		public final void activateActionI()
		{
			this.doRunActionI=true;
		}

		public final void activateActionJ()
		{
			this.doRunActionJ=true;
		}
		
		public final void activateActionK()
		{
			this.doRunActionK=true;
		}
		
		public final void activateActionL()
		{
			this.doRunActionL=true;
		}
		
		public final void activateActionM()
		{
			this.doRunActionM=true;
		}
		
		public final void activateActionN()
		{
			this.doRunActionN=true;
		}
		
		public final void activateActionO()
		{
			this.doRunActionO=true;
		}
		
		public final void activateActionP()
		{
			this.doRunActionP=true;
		}
		
		public final void activateActionQ()
		{
			this.doRunActionQ=true;
		}
		
		public final void activateActionR()
		{
			this.doRunActionR=true;
		}
		
		public final void activateActionS()
		{
			this.doRunActionS=true;
		}
		
		public final void activateActionT()
		{
			this.doRunActionT=true;
		}
		
		public final void activateActionU()
		{
			this.doRunActionU=true;
		}
		
		public final void activateActionV()
		{
			this.doRunActionV=true;
		}
		
		public final void activateActionW()
		{
			this.doRunActionW=true;
		}
		
		public final void activateActionX()
		{
			this.doRunActionX=true;
		}
		
		public final void activateActionY()
		{
			this.doRunActionY=true;
		}
		
		public final void activateActionZ()
		{
			this.doRunActionZ=true;
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
		
		public final void deactivateActionD()
		{
			this.doRunActionD=false;
		}
		
		public final void deactivateActionE()
		{
			this.doRunActionE=false;
		}
		
		public final void deactivateActionF()
		{
			this.doRunActionF=false;
		}
		public final void deactivateActionG()
		{
			this.doRunActionG=false;
		}
		
		public final void deactivateActionH()
		{
			this.doRunActionH=false;
		}
		
		public final void deactivateActionI()
		{
			this.doRunActionI=false;
		}
		
		public final void deactivateActionJ()
		{
			this.doRunActionJ=false;
		}
		
		public final void deactivateActionK()
		{
			this.doRunActionK=false;
		}
		
		public final void deactivateActionL()
		{
			this.doRunActionL=false;
		}
		
		public final void deactivateActionM()
		{
			this.doRunActionM=false;
		}
		
		public final void deactivateActionN()
		{
			this.doRunActionN=false;
		}
		
		public final void deactivateActionO()
		{
			this.doRunActionO=false;
		}
		
		public final void deactivateActionP()
		{
			this.doRunActionP=false;
		}
		
		public final void deactivateActionQ()
		{
			this.doRunActionQ=false;
		}
		
		public final void deactivateActionR()
		{
			this.doRunActionR=false;
		}
		
		public final void deactivateActionS()
		{
			this.doRunActionS=false;
		}
		
		public final void deactivateActionT()
		{
			this.doRunActionT=false;
		}
		
		public final void deactivateActionU()
		{
			this.doRunActionU=false;
		}
		
		public final void deactivateActionV()
		{
			this.doRunActionV=false;
		}
		
		public final void deactivateActionW()
		{
			this.doRunActionW=false;
		}
		
		public final void deactivateActionX()
		{
			this.doRunActionX=false;
		}
		
		public final void deactivateActionY()
		{
			this.doRunActionY=false;
		}
		
		public final void deactivateActionZ()
		{
			this.doRunActionZ=false;
		}
		
		///////////////////
		///////GETTERS/////
		///////////////////
		public final EActions getCurrentAction()
		{
			return this.currentAction;
		}
		
		public final EActions getNextAction()
		{
			return this.nextAction;
		}
		
		public final boolean isWaiting()
		{
			return this.waiting;
		}
		
		///////////////////
		//////SETTERS//////
		///////////////////
		
		public final void turnOnRestart()
		{
			this.restartOn=true;
		}
		
		public final void turnOffRestart()
		{
			this.restartOn=false;
		}
		
		public final void turnOnWaiting()
		{
			this.waiting=true;
		}
		
		public final void turnOffWaiting()
		{
			this.waiting=false;
		}
		
		public final void turnOnTestMode()
		{
			this.turnOnWaiting();
			this.isInTestMode=true;
		}
		///////////////////
		//PRIVATE METHODS//
		///////////////////
		
		protected final void checkIfRunInTestMode()	
		{
			if(this.isInTestMode)
			{
				this.turnOnWaiting();
				this.doWaiting();
			}
			else
			{
				//this.turnOffWaiting();
				this.doWaiting();
			}
		}
		
		protected final void doWaiting()
		{
			while(this.waiting)
			{
				this.doNothing=true;
				//do nothing
				this.waitForAValidInput();
			}
			this.doNothing=false;
		}
		
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
		
		@OnlyForTesting
		public  void fakeInputB(String inputB)
		{
			
		}
		
		@OnlyForTesting
		public  void fakeInputC(String inputC)
		{
			
		}
		
		@OnlyForTesting
		public  void fakeInputD(String inputD)
		{
			
		}
		
		@OnlyForTesting
		public  void fakeInputE(String inputE)
		{
			
		}
		
		@OnlyForTesting
		public  void fakeInputF(String inputF)
		{
			
		}
		
		@OnlyForTesting
		public  void fakeInputG(String inputG)
		{
			
		}
		
		@OnlyForTesting
		public  void fakeInputH(String inputH)
		{
			
		}
		
		@OnlyForTesting
		public  void fakeInputI(String inputI)
		{
			
		}
		
		@OnlyForTesting
		public  void fakeInputJ(String inputJ)
		{
			
		}
		
		@OnlyForTesting
		public  void fakeInputK(String inputK)
		{
			
		}
		
		@OnlyForTesting
		public  void fakeInputL(String inputL)
		{
			
		}
		
		@OnlyForTesting
		public  void fakeInputM(String inputM)
		{
			
		}
		
		@OnlyForTesting
		public  void fakeInputN(String inputN)
		{
			
		}
		
		@OnlyForTesting
		public  void fakeInputO(String inputO)
		{
			
		}
		
		@OnlyForTesting
		public  void fakeInputP(String inputP)
		{
			
		}
		
		@OnlyForTesting
		public  void fakeInputQ(String inputQ)
		{
			
		}
		
		@OnlyForTesting
		public  void fakeInputR(String inputR)
		{
			
		}
		
		@OnlyForTesting
		public  void fakeInputS(String inputS)
		{
			
		}
		
		@OnlyForTesting
		public  void fakeInputT(String inputT)
		{
			
		}
		
		@OnlyForTesting
		public  void fakeInputU(String inputU)
		{
			
		}
		
		@OnlyForTesting
		public  void fakeInputV(String inputV)
		{
			
		}
		
		@OnlyForTesting
		public  void fakeInputW(String inputW)
		{
			
		}
		
		@OnlyForTesting
		public  void fakeInputX(String inputX)
		{
			
		}

		@OnlyForTesting
		public  void fakeInputY(String inputY)
		{
			
		}
		
		@OnlyForTesting
		public  void fakeInputZ(String inputZ)
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
