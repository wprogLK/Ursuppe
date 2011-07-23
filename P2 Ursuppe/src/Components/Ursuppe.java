package Components;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;


import phases.PhaseAbout;
import phases.PhaseCheat;
import phases.PhaseSetFirstAmeba;
import phases.PhaseSetNames;

import enums.*;
import game.Game;
import game.PhaseLogic;
/**
 * @author Lukas
 *
 */
public class Ursuppe implements ActionListener 
{
	private int updateIntervall=35;	//Milliseconds between two time ticks
	private Timer updateTimer;

	private GamePhases activePhase;
	private GamePhases changePhase;
	private PhaseLogic phaseLogic;
	
	private  Game game;
	
	
	
	
	
	private final GamePhases startPhase=GamePhases.phaseWelcome;	//@USER: if you want run the test set this to GamePhases.phaseSetNames IMPORTANT!!!!!!!!	
	
	public Ursuppe() {
		
		this.createNew();
		
		
		
	}
	
	private void createNew()
	{
		String version="1.4.0";
		String gameTitle="Ursuppe - Version " + version;
		this.game=new Game(gameTitle);
		this.game.setVersion(version);
		
		this.phaseLogic=new PhaseLogic(game,startPhase);

		this.activePhase=this.startPhase;
		this.changePhase=this.startPhase;
		

		
		this.updateData();
		
	
	}

	

	
	public  void run()
	{
		this.updateTimer.start();
	}
	
	public void stop()
	{
		this.updateTimer.stop();
	}
	
	public int getUpdateIntervall()
	{
		return this.updateIntervall;
	}
	
	public void setUpdateIntervall(int intervall)
	{
		this.updateIntervall=intervall;
		this.updateTimer=new Timer(this.updateIntervall,this);
	}

	
	
	@Override
	/**
	 * its only for the timer. timer resize the window
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.updateTimer)
		{
			this.resize();

		
		}
		
	}

	
	
	private void updateData()
	{
		this.changePhase=this.phaseLogic.getChangePhase();
		this.activePhase=this.phaseLogic.getActivePhase();
	
	}
	
	private void resize()
	{
		this.updateData();
		
		
		
		
	
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
	
			public GamePhases getActivePhase()
			{
				return this.activePhase;
			}
			
			public GamePhases getChangePhase()
			{
				return this.changePhase;
			}
			
			public PhaseLogic getPhaseLogic()
			{
				return this.phaseLogic;
			}
			
			public Game getGame()
			{
				return this.game;
			}
			
		
	

		
		
			
	
	
	
		/////////////////
		//FAKED EVENTS://
		/////////////////
		
		public void fakeTimer()
		{
			this.phaseLogic.update();
		
		}
	
}
