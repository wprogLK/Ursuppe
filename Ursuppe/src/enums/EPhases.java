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
		
			//////////////
			//**SETUPS**//
			//////////////
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
		
		public void setPhasesInformationToIPhase()
		{
			if (this.lastPhase==null)
			{
				//System.out.println("NULL last!");
				this.lastPhase=this.defaultPhase;
			}
			
			if (this.nextPhase==null)
			{
				//System.out.println("NULL next!");
				this.nextPhase=this.defaultPhase;
			}
			
		//	System.out.println("SET INFO TO IPHASE: \n lastPhase: " + this.lastPhase + "\n nextPhase: " + this.nextPhase);
			
			this.phase.setLastPhase(this.lastPhase);
			this.phase.setNextPhase(this.nextPhase);
		}
		
		///////////
		//SETTERS//
		///////////
		public void setPhase(IPhase phase)
		{
			//System.out.println("SET PHASE " + phase + " LAST PHASE WAS: " + this.lastPhase + " NEXT PHASE WILL BE: " + this.nextPhase);
			this.phase=phase;
			
			this.setPhasesInformationToIPhase();
			
		}
		
		public void setNextPhase(EPhases nextPhase)
		{
			this.nextPhase=nextPhase;
		}
		

		public void setLastPhase(EPhases lastPhase)
		{
			this.lastPhase=lastPhase;
		}
		
		///////////
		//GETTERS//
		///////////		
		
		public IPhase getPhase()
		{
			return this.phase;
		}
	
		public EPhases getNextPhase()
		{
			return this.nextPhase;
		}
		
		public EPhases getLastPhase()
		{
			return this.lastPhase;
		}
		
	
		
}
