package game;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import phases.*;

import enums.*;

/**
 * 
 */

/**
 * @author lukas
 *
 */
public class PhaseLogic extends JPanel implements ActionListener {
	
	//BASIC THINGS OF THE PHASE-LOGIC:
	private GamePhases activePhase;		//is the active phase
	
	private GamePhases actualPhase;
	private GamePhases changePhase;		//is the phase to change to
	
	
	
	//THE PHASES:
	private PhaseSetNames phaseSetNames;
	private PhaseSetFirstAmeba phaseSetFirstAmeba;
	private Phase0 phase0;
	private Phase1 phase1;
	private Phase2 phase2;
	private Phase3 phase3;
	private Phase4 phase4;
	private Phase5 phase5;
	private Phase6 phase6;
	private PhaseAbout phaseAbout;
	private PhaseCheat phaseCheat;
	private PhaseWelcome phaseWelcome;
	
	private UrsuppeGUI ursuppeGui;
	
	
	//COMPONENTS OF THIS CLASS
		//nothing
	
	//VARIABLES OF THIS CLASS
		//GENERAL:
		private Game game;
		private boolean doResize;
		
		private GamePhases startPhase;
		
		private Calc calc=new Calc();
		
	
	
	
	//TIMER FOR THE LOGIC:
	private int logicIntervall=35; //Milliseconds between checks
	private Timer logicTimer;
	
	
	/**
	 * 
	 */
	public PhaseLogic(Game game, GamePhases startPhase, UrsuppeGUI ursuppeGui)
	{
		//////////////////////////////////
		//ARGUMENTS OF THE CONSTRUCTOR://
		/////////////////////////////////
		this.game=game;
		this.startPhase=startPhase;
		
	
	
		
		//////////////////////
		//CREATE THE PHASES://
		//////////////////////

		createPhase(ursuppeGui);
		
		////////////////////////
		//SET THE START PHASE://
		///////////////////////
		
		this.setStartPhase(startPhase);
		
		
		//////////////////
		//CREATE TIMERS://
		//////////////////
		
		initAndStartTimer();
	}

	private void initAndStartTimer() {
		this.logicTimer=new Timer(this.logicIntervall,this);
		this.logicTimer.start();
	}

	private void createPhase(UrsuppeGUI ursuppeGui) {
		this.phaseSetNames=new PhaseSetNames(this.game);
		this.phaseSetFirstAmeba=new PhaseSetFirstAmeba(this.game);
		this.phase0=new Phase0(this.game);
		this.phase1=new Phase1(this.game);
		this.phase2=new Phase2(this.game);
		this.phase3=new Phase3(this.game);
		this.phase4=new Phase4(this.game);
		this.phase5=new Phase5(this.game);
		this.phase6=new Phase6(this.game);
		this.phaseAbout=new PhaseAbout(this.game);
		this.phaseCheat=new PhaseCheat(this.game);
		this.phaseWelcome=new PhaseWelcome(this.game,ursuppeGui);
	}
	
	//////////////////////
	//GETTERS & SETTERS://
	//////////////////////
	
		////////////
		//SETTERS://
		////////////
		
	
		public void setDoResize(boolean value)
		{
			this.doResize=value;
		}
	
		public void setStartPhase(GamePhases startPhase)
		{
			this.deactivateAllPhases();
			
			switch(startPhase)
			{
				case phaseEmpty:
				{
					this.activePhase=GamePhases.phaseEmpty;
					break;
				}
				case phaseSetNames:
				{
					this.activePhase=GamePhases.phaseSetNames;
					this.phaseSetNames.activate();
					this.add(this.phaseSetNames);
					
					break;
				}
				case phase0:
				{
					this.phase0.setPanelPhase0Part1();
					
					this.activePhase=GamePhases.phase0;
					this.phase0.activate();
					this.add(this.phase0);
					break;
				}
				case phaseSetFirstAmeba:
				{
					this.activePhase=GamePhases.phaseSetFirstAmeba;
					this.phaseSetFirstAmeba.activate();
					this.add(this.phaseSetFirstAmeba);
					break;
				}
				case phase1:
				{
					this.activePhase=GamePhases.phase1;
					this.phase1.activate();
					this.add(this.phase1);
					break;
				}
				case phase2:
				{
					this.activePhase=GamePhases.phase2;
					this.phase2.activate();
					this.add(this.phase2);
				
					break;
				}
				case phase3:
				{	
					this.activePhase=GamePhases.phase3;
					this.phase3.activate();
					this.add(this.phase3);
					
					break;
				}
				case phase4:
				{
					this.activePhase=GamePhases.phase4;
					this.phase4.activate();
					this.add(this.phase4);
				
					
					break;
				}
				case phase5:
				{
					this.activePhase=GamePhases.phase5;
					this.phase5.activate();
					this.add(this.phase5);
				
					break;
				}
				case phase6:
				{
					this.activePhase=GamePhases.phase6;
					this.phase6.activate();
					this.add(this.phase6);
				
					break;
				}
				case phaseAbout:
				{
					this.activePhase=GamePhases.phaseAbout;
					this.phaseAbout.activate();
					this.add(this.phaseAbout);
				
					break;
				}
				case phaseCheat:
				{
					this.activePhase=GamePhases.phaseCheat;
					this.phaseCheat.activate();
					this.add(this.phaseCheat);
				
					break;
				}
				case phaseWelcome:
				{
					this.activePhase=GamePhases.phaseWelcome;
					this.phaseWelcome.activate();
					this.add(this.phaseWelcome);
				
					break;
				}
				default:
				{
					System.out.println("Error (PhaseLogic.class): exception in setStartPhase()!");
					//TODO 
				}
				
				this.actualPhase=this.activePhase;
			}
		}
		
		////////////
		//GETTERS://
		////////////

		
		public GamePhases getStartPhase()
		{
			return this.startPhase;
		}
		
		public GamePhases getActivePhase()
		{
			return this.activePhase;
		}
		
		public GamePhases getActualPhase()
		{
			return this.actualPhase;
		}
		
		
		public GamePhases getChangePhase()
		{
			return this.changePhase;
		}
		
		public boolean getDoResize()
		{
			return this.doResize;
		}
		
	
		
	////////////////////////////////
	//ACTIVATE & DEACTIVE METHODS://	
	////////////////////////////////
		
	public void deactivateAllPhases()
	{
		this.phaseSetNames.deactivate();
		this.phase0.deactivate();
		this.phase1.deactivate();
		this.phase2.deactivate();
		this.phase3.deactivate();
		this.phase4.deactivate();
		this.phase5.deactivate();
		this.phase6.deactivate();
		this.phaseAbout.deactivate();
		this.phaseCheat.deactivate();
		this.phaseWelcome.deactivate();
		
	}
	
	public void activatePhase(GamePhases phase)
	{
		this.doUpdate(phase); //Update the data of the phase
		this.actualPhase=phase;
		
		switch(phase)
		{
			case phaseEmpty:
			{
				
				//do nothing, because no activating is necessary!
				break;
			}
			case phaseSetNames:
			{
				this.phaseSetNames.activate();
				
				this.removeAll(); 				
				this.add(this.phaseSetNames);	
				break;
			}
			case phase0:
			{
				this.phase0.activate();
				
				this.removeAll(); 				
				
				this.phase0.setPanelPhase0Part1(); 
				
				this.add(this.phase0);		
				break;
			}
			case phaseSetFirstAmeba:
			{
				this.phaseSetFirstAmeba.activate();
				
				this.removeAll(); 			
			
				this.add(this.phaseSetFirstAmeba);		
				break;
			}
			case phase1:
			{
				this.phase1.activate();
				
				this.removeAll(); 				
			
				this.add(this.phase1);			
				break;
			}
			case phase2:
			{
				this.phase2.activate();
				
				this.removeAll(); 				
			
				this.add(this.phase2);		
				break;
			}
			case phase3:
			{	
				this.phase3.activate();
				
				this.removeAll(); 				
			
				this.add(this.phase3);			
				break;
			}
			case phase4:
			{
				this.phase4.activate();
				
				this.removeAll(); 			
			
				this.add(this.phase4);		
				break;
				
			}
			case phase5:
			{
				this.phase5.activate();
				
				this.removeAll(); 				
			
				this.add(this.phase5);		
				break;
				
				
			}
			case phase6:
			{
				this.phase6.activate();
				
				this.removeAll(); 			
			
				this.add(this.phase6);		
			
				
				break;
			}
			case phaseAbout:
			{
				this.phaseAbout.activate();
				
				this.removeAll(); 			
				this.add(this.phaseAbout);	
				break;
			}
			case phaseCheat:
			{
				this.phaseCheat.activate();
				
				this.removeAll();
				this.add(this.phaseCheat);
				break;
			}
			case phaseWelcome:
			{
				this.phaseWelcome.activate();
				
				this.removeAll();
				this.add(this.phaseWelcome);
				break;
			}
			default:
			{
				System.out.println("Error (PhaseLogic.class): exception in setStartPhase()!");
				//TODO 
			}
		}
	}
		
	///////////////////
	//UPDATE METHODS://
	///////////////////

	public void doUpdate(GamePhases phase)
	{
		switch(phase)
		{
			case phaseEmpty:
			{
				break;
			}
			case phaseSetNames:
			{
				this.phaseSetNames.doUpdate();
				break;
			}
			case phase0:
			{
				this.phase0.doUpdate();
				break;
			}
			case phaseSetFirstAmeba:
			{
				this.phaseSetFirstAmeba.doUpdate();
				break;
			}
			case phase1:
			{
				this.phase1.doUpdate();
				break;
			}
			case phase2:
			{
				this.phase2.doUpdate();
				break;
			}
			case phase3:
			{	
				this.phase3.doUpdate();
				break;
			}
			case phase4:
			{
				this.phase4.doUpdate();
				break;
			}
			case phase5:
			{
				this.phase5.doUpdate();
				break;
			}
			case phase6:
			{
				this.phase6.doUpdate();
				break;
			}
			case phaseAbout:
			{
				this.phaseAbout.doUpdate();
				break;
			}
			case phaseCheat:
			{
				this.phaseCheat.doUpdate();
				break;
			}
			case phaseWelcome:
			{
				this.phaseWelcome.doUpdate();
				break;
			}
			default:
			{
				System.out.println("Error (PhaseLogic.class): exception in setStartPhase()!");
				//TODO 
				break;
			}
		}
	}
	
	/**
	 * get all information of the active phase and update it in the phaseLogic
	 */
	public void update()
	{
		switch(this.activePhase)
		{
		
			case phaseEmpty:
			{
				break;
			}
			case phaseSetNames:
			{
				this.changePhase=this.phaseSetNames.getActivePhase();
				break;
			}
			case phase0:
			{
				this.changePhase=this.phase0.getActivePhase();
				break;
			}
			case phaseSetFirstAmeba:
			{
				this.changePhase=this.phaseSetFirstAmeba.getActivePhase();
				break;
			}
			
			case phase1:
			{
				this.changePhase=this.phase1.getActivePhase();
				break;
			}
			case phase2:
			{
				this.changePhase=this.phase2.getActivePhase();
				break;
			}
			case phase3:
			{	
				this.changePhase=this.phase3.getActivePhase();
				break;
			}
			case phase4:
			{
				this.changePhase=this.phase4.getActivePhase();
				break;
			}
			case phase5:
			{
				this.changePhase=this.phase5.getActivePhase();
				break;
			}
			case phase6:
			{
				this.changePhase=this.phase6.getActivePhase();
				break;
			}
			case phaseAbout:
			{
				this.changePhase=this.phaseAbout.getActivePhase();
				break;
			}
			case phaseCheat:
			{
				this.changePhase=this.phaseCheat.getActivePhase();
				break;
			}
			case phaseWelcome:
			{
				this.changePhase=this.phaseWelcome.getActivePhase();
				break;
			}
			default:
			{
				System.out.println("Error (PhaseLogic.class): exception in update()!");
				//TODO 
				break;
			}
		}
		
		checkToChangePhase();
		
	}

	private void checkToChangePhase() {
		if (this.changePhase!=GamePhases.phaseEmpty && this.changePhase!=this.activePhase)
		{
			this.activePhase=this.changePhase;
			this.game.setReadDirection(this.activePhase.getReadDirection());
			
			this.deactivateAllPhases();
			this.activatePhase(this.activePhase);
			
			this.doResize=true;
		}
	}
	
	
	/**
	 * for handle null layouts correct!
	 * @param width
	 * @param height
	 * @param phase
	 */
	public void doSelfResize(int width, int height, GamePhases phase)
	{
		this.setSize(width, height);
		
		switch(phase)
		{
		
			case phaseEmpty:
			{
				break;
			}
			case phaseSetNames:
			{
				this.phaseSetNames.setSize(width, height);
				this.calc.calcPanel(this.phaseSetNames, 0, 0);		
				
				break;
			}
			case phase0:
			{
				this.phase0.setSize(width, height);
				this.calc.calcPanel(this.phase0, 0, 0);		
				
				break;
			}
			case phaseSetFirstAmeba:
			{
				this.phaseSetFirstAmeba.setSize(width, height);
				this.calc.calcPanel(this.phaseSetFirstAmeba, 0, 0);	
				
			
				
				break;
			}
			
			case phase1:
			{
				this.phase1.setSize(width, height);
				this.calc.calcPanel(this.phase1, 0, 0);		
			
				break;
			}
			case phase2:
			{
				this.phase2.setSize(width, height);
				this.calc.calcPanel(this.phase2, 0, 0);		
			
			
				break;
			}
			case phase3:
			{	
				this.phase3.setSize(width, height);
				this.calc.calcPanel(this.phase3, 0, 0);		
			
			
				break;
			}
			case phase4:
			{
				this.phase4.setSize(width, height);
				this.calc.calcPanel(this.phase4, 0, 0);	
			
			
				break;
			}
			case phase5:
			{
				this.phase5.setSize(width, height);
				this.calc.calcPanel(this.phase5, 0, 0);		
			
			
				break;
			}
			case phase6:
			{
				this.phase6.setSize(width, height);
				this.calc.calcPanel(this.phase6, 0, 0);		
			
			
				break;
			}
			case phaseAbout:
			{
				this.phaseAbout.setSize(width, height);
				this.calc.calcPanel(this.phaseAbout, 0, 0);		
			
			
				break;
			}
			case phaseCheat:
			{
				this.phaseCheat.setSize(width,height);
				this.calc.calcPanel(this.phaseCheat, 0, 0);
				
				break;
			}
			case phaseWelcome:
			{
				this.phaseWelcome.setSize(width,height);
				this.calc.calcPanel(this.phaseWelcome, 0, 0);
				
				break;
			}
			default:
			{
				System.out.println("Error (PhaseLogic.class): exception in doSelfResize(int width, int height, GamePhases phase)!");
				//TODO 
				break;
			}
		}
		
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.logicTimer)
		{
			this.update(); 
		}
		
	}
	
	/////////////////////
	//ONLY FOR TESTING://
	/////////////////////
	
	
		//////////////////////
		//GETTERS & SETTERS://
		//////////////////////
			
			////////////
			//GETTERS://
			////////////
	
			/**
			 * don't forget to cast the return value to the phase you want to have
			 * 
			 * its only returns the phase as JPanel!
			 */
			public JPanel getPhase(GamePhases phase)
			{
				switch(phase)
				{
					case phase0:
					{
						return phase0;
					}
					case phase1:
					{
						return phase1;
					}
					case phase2:
					{
						return phase2;
					}
					case phase3:
					{
						return phase3;
					}
					case phase4:
					{
						return phase4;
					}
					case phase5:
					{
						return phase5;
					}
					case phase6:
					{
						return phase6;
					}
					case phaseSetNames:
					{
						return phaseSetNames;
					}
					case phaseSetFirstAmeba:
					{
						return phaseSetFirstAmeba;
					}
					case phaseAbout:
					{
						return phaseAbout;
					}
					case phaseCheat:
					{
						return phaseCheat;
					}
					case phaseWelcome:
					{
						return phaseWelcome;
					}
					default:
					{
						System.out.println("Error (PhaseLogic.class): exception in getPhase(GamePhases phase)!");
						//TODO
						return null;
					}
				}
			}
			
		

			
}
