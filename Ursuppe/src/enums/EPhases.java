package enums;

import annotations.OnlyForTesting;
import interfaces.IPhase;
/**
 * contains all phases
 * 
 * <p><u> Overview:</u> </p>
 * 
 * <ul>
 * 	<li> DEFAULT: </li>
 * 		<ul> 
 * 			<li> defaultPhase </li>
 * 		</ul>
 * 
 * 	<li> BASICS: </li>
 * 		<ul> 
 * 			<li> phaseSplashScreen </li>
 * 			<li> phaseExit </li>
 * 			<li> phaseMainMenu </li>
 * 			<li> phaseSaveGame </li>
 * 			<li> phaseLoadGame </li>
 * 			<li> phaseBreakMenu </li>
 * 			<li> phaseStatistics </li>
 * 			<li> phaseAbout </li>
 * 			<li> phaseHelp </li>
 * 			<li> phaseOptions </li>
 * 			<li> phaseAchievements </li>
 * 			<li> phaseCheats </li>
 * 		</ul>
 * 
 * <li> SETUPS: </li>
 * 		<ul> 
 * 			<li> phaseNewGame </li>
 * 			<li> phasePreparation1 </li>
 * 			<li> phasePreparation2 </li>
 * 			<li> phasePreparation3 </li>
 * 		</ul>
 * 
 *  <li> PLAY: </li>
 * 		<ul> 
 * 			<li> phase1 </li>
 * 			<li> phase2 </li>
 * 			<li> phase3 </li>
 * 			<li> phase4 </li>
 * 			<li> phase5 </li>
 *  		<li> phase6 </li>
 *   		<li> phaseGameEnd </li>
 * 		</ul>
 * 
 * 
 * <br>
 * 
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
			phasePreparation1(EPlayingOrder.Descending),
			phasePreparation2(EPlayingOrder.Descending),
			phasePreparation3(EPlayingOrder.Descending),
			
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
		
		/**
		 * Constructor with order
		 */
		EPhases(EPlayingOrder order)
		{
			this.order=order;
		}
		
		/**
		 * Default constructor.
		 * 
		 * Sets the EPlayingOrder default
		 */
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
		
		/**
		 * gets the playing order of this ePhase.
		 * @return plaiying order of this ePhase
		 */
		public EPlayingOrder getOrder() 
		{
			return this.order;
		}
		
	
		
}
