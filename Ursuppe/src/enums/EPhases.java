package enums;

import annotations.OnlyForTesting;
import interfaces.IPhase;
/**
 * is for see the different phases
 * @author Lukas Keller
 * @version 1.0.0
 *
 */
public enum EPhases 
{	
	
	/////////
	//ENUMS//
	/////////
	
		defaultPhase,
		phaseA,
		//////////
		//*GAME*//
		//////////
			//////////////
			//**BASICS**//
			//////////////
			phaseSplashScreen,
		
			phaseExit,
			phaseMainMenu,
			
			phaseSaveGame,
			phaseLoadGame,
			
			phaseBreakMenu,
			phaseStatistics,
			
			phaseAbout,
			phaseHelp,
			phaseOptions,
			phaseAchievements,
			
			phaseCheats,
		
			//////////////
			//**SETUPS**//
			//////////////
			phaseNewGame,
			phasePreparation1(EPlayingOrder.Ascending),
			phasePreparation2(EPlayingOrder.Ascending),
			
			////////////
			//**PLAY**//
			////////////
			phase1(EPlayingOrder.Ascending),
			phase2(EPlayingOrder.Descending),
			phase3(EPlayingOrder.Descending),
			phase4(EPlayingOrder.Descending),
			phase5(EPlayingOrder.Descending),
			phase6(EPlayingOrder.Descending),
			
			phaseGameEnd;
		
			
	//////////
	//PARAMS//
	//////////
		private IPhase phase;
		
		private EPhases nextPhase;
		private EPhases lastPhase;
		
		private EPlayingOrder order;
		
	///////////
	//METHODS//
	///////////
		
		EPhases(EPlayingOrder ordner)
		{
			this.order=order;
		}
		
		EPhases()
		{
			this.order=EPlayingOrder.Default;
		}
		
		
		/**
		 * setup the data (last and next ePhase) of current ePhase to {@code defaultPhase} if it is {@code null}
		 */
		public void setPhasesInformationToIPhase()
		{
			if (this.lastPhase==null)
			{
				this.lastPhase=this.defaultPhase;
			}
			
			if (this.nextPhase==null)
			{
				this.nextPhase=this.defaultPhase;
			}
			
			this.phase.setLastPhase(this.lastPhase);
			this.phase.setNextPhase(this.nextPhase);
		}
		
		///////////
		//SETTERS//
		///////////
		@OnlyForTesting
		public void setOrder(EPlayingOrder order)
		{
			this.order=order;
		}
		/**
		 * set the real {@link IPhase} to the current ePhase
		 * @param the real Phase of type {@code IPhase}
		 */
		public void setPhase(IPhase phase)
		{
			this.phase=phase;
			
			this.setPhasesInformationToIPhase();
			
		}
		
		/**
		 * set the next {@code ePhase}
		 * @param nextPhase
		 */
		public void setNextPhase(EPhases nextPhase)
		{
			this.nextPhase=nextPhase;
		}
		
		/**
		 * set the last {@code ePhase}
		 * @param lastPhase
		 */
		public void setLastPhase(EPhases lastPhase)
		{
			this.lastPhase=lastPhase;
		}
		
		///////////
		//GETTERS//
		///////////		
		
		/**
		 * gets the real IPhase
		 * @return IPhase
		 */
		public IPhase getPhase()
		{
			return this.phase;
		}
	
		/**
		 * gets the next ePhase of the current phase
		 * @return EPhase
		 */
		public EPhases getNextPhase()
		{
			return this.nextPhase;
		}
		
		/**
		 * gets the last ePhase of the current phase
		 * @return EPhase
		 */
		public EPhases getLastPhase()
		{
			return this.lastPhase;
		}

		public EPlayingOrder getOrder() 
		{
			return this.order;
		}
		
	
		
}
