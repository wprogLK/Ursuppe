package enums;

import interfaces.IPhase;

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
			phaseExit,
			phaseWelcome,
			
			phaseSave,
			phaseLoad,
			
			phaseBreak,
			phaseStatisic,
			
			phaseAbout,
			phaseManual,
			
			phaseCheat,
		
			//////////////
			//**SETUPS**//
			//////////////
			phaseSetupNewGame,
			phaseSetupNames,
			phaseSetupOrderToPlay,
			phaseSetupFirstAmeba,
			
			////////////
			//**PLAY**//
			////////////
			phase1MoveAndEat,
			phase2EnviromentAndGeneDefects,
			phase3BuyGenes,
			phase4CellDivision,
			phase5Death,
			phase6Score,
			
			phaseWin;
		
	//////////
	//PARAMS//
	//////////
		private IPhase phase;
		
		private EPhases nextPhase;
		private EPhases lastPhase;
		
	///////////
	//METHODS//
	///////////
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
		
	
		
}
