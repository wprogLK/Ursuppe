package GUIComponents;
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
import helpClasses.SaveAndLoad;
/**
 * @author Lukas
 *
 */
public class UrsuppeGUI implements ActionListener 
{
	private int updateIntervall=35;	//Milliseconds between two time ticks
	private Timer updateTimer;

	private GamePhases activePhase;
	private GamePhases changePhase;
	private PhaseLogic phaseLogic;
	
	private  Game game;
	
	private JFrame window;
	private JFrame windowCheat;
	private JFrame windowAbout;
	private JFrame windowWelcome;
	
	
	private int windowsX;
	private int windowsY;
	
	private JPanel panelMain=new JPanel();
	
	//MENU
	JMenuBar menuBarNormal=new JMenuBar();
	JMenuBar menuBarCheat=new JMenuBar();
	JMenuBar menuBarAbout=new JMenuBar();
	JMenuBar menuBarWelcome=new JMenuBar();
	
	JMenu menuGame=new JMenu("Game");
	JMenu menuCheats=new JMenu("Cheats");
	JMenu menuAbout=new JMenu("About");
	
	JMenu menuSubCurrentGame=new JMenu("Current game");
	JMenu menuSubOtherGame=new JMenu("Other game");
	
	JMenuItem menuItemGoToNextPhase=new JMenuItem("Go to next phase if possible");
	JMenuItem menuItemExitGame=new JMenuItem("Exit this game");
	JMenuItem menuItemNewGame=new JMenuItem("Start a new game");
	JMenuItem menuItemSeeInfo=new JMenuItem("Info");
	JMenuItem menuItemCheat=new JMenuItem("go to cheats");
	JMenuItem menuItemLoadGame=new JMenuItem("Load a game");
	JMenuItem menuItemSaveGame=new JMenuItem("Save a game");
	
	private boolean showWindow;
	private boolean showMenu;
	
	private final GamePhases startPhase=GamePhases.phaseSetNames;	//@USER: if you want run the test set this to GamePhases.phaseSetNames IMPORTANT!!!!!!!!	
	
	public UrsuppeGUI(boolean showWindow,boolean showMenu) {
		
		this.showWindow=showWindow;
		this.showMenu=showMenu;
		
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

		this.showMenu=this.startPhase.getShowMenu();
		
		///////////
		//WINDOW://
		///////////
		
		this.window =new JFrame(gameTitle);
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		//////////
		//TIMER://
		//////////
		this.updateTimer=new Timer(this.updateIntervall,this);
		this.run();
		
	
		
		this.window.setContentPane(this.panelMain);
		
		SaveAndLoad.saveObject(this.panelMain, "mainPanel.urs");
		
		
		if (this.showWindow)
		{
			this.window.setVisible(true);
			
		}
		
		setWindowsSize();
		
		this.updateData();
		this.forceResize();			
	
		
		this.showMenu=this.activePhase.getShowMenu();
		
		//CREATE MENUS:
		this.createMenuNormal();
		this.createMenuAbout();
		this.createMenuCheat();
		
		decideShowMenu();
	}

	private void decideShowMenu() {
		if (this.showMenu && this.startPhase==GamePhases.phaseSetNames)
		{
			boolean showWindowInThisWindow=GamePhases.phaseSetNames.getShowMenu();
			this.letShowMenu(this.menuBarNormal,showWindowInThisWindow,this.window);
			
		}
		else if (this.showMenu && this.startPhase==GamePhases.phaseAbout)
		{
			
			boolean showWindowInThisWindow=GamePhases.phaseAbout.getShowMenu();
			this.letShowMenu(this.menuBarAbout,showWindowInThisWindow,this.windowAbout);
			
		}
		else if (this.showMenu && this.startPhase==GamePhases.phaseCheat)
		{
			
			boolean showWindowInThisWindow=GamePhases.phaseCheat.getShowMenu();
			this.letShowMenu(this.menuBarCheat,showWindowInThisWindow,this.windowCheat);
			
		}
	}

	private void setWindowsSize() {
		int x=this.startPhase.getWindowsX();
		int y=this.startPhase.getWindowsY();
		this.window.setSize(x, y);
	}
	
	private JFrame createANewWindow(String title, JPanel content)
	{
		///////////
		//WINDOW://
		///////////
		
		System.out.println("Create a new window with title: "+ title);
		JFrame newWindow =new JFrame(title);

		newWindow.setContentPane(content);
		newWindow.setSize(1000, 1000);

		newWindow.setVisible(true);
		return newWindow;

	}
	
	private void createMenuNormal()
	{
		//MENU:
		this.menuBarNormal.add(this.menuGame);
		
		//*GAME MENU*//
		this.menuGame.setMnemonic(KeyEvent.VK_G);

		this.menuGame.add(this.menuSubCurrentGame);
		this.menuGame.add(this.menuSubOtherGame);
		
		this.menuSubCurrentGame.setMnemonic(KeyEvent.VK_A);
	
		this.menuSubCurrentGame.add(this.menuItemGoToNextPhase);
		this.menuSubCurrentGame.add(this.menuItemExitGame);
		this.menuSubCurrentGame.add(this.menuItemLoadGame);
		this.menuSubCurrentGame.add(this.menuItemSaveGame);
		
		this.menuSubOtherGame.setMnemonic(KeyEvent.VK_O);
		this.menuSubOtherGame.add(this.menuItemNewGame);
		

		//*CHEATS MENU*//
		this.menuCheats.setMnemonic(KeyEvent.VK_C);
		this.menuBarNormal.add(this.menuCheats);
		this.menuCheats.add(this.menuItemCheat);
		
		//*ABOUT MENU*//
		this.menuAbout.setMnemonic(KeyEvent.VK_I);
		this.menuBarNormal.add(this.menuAbout);
		this.menuAbout.add(this.menuItemSeeInfo);
		
		//Add to listener:
		this.menuItemGoToNextPhase.addActionListener(this);
		this.menuItemNewGame.addActionListener(this);
		this.menuItemCheat.addActionListener(this);
		this.menuItemExitGame.addActionListener(this);
		this.menuItemSeeInfo.addActionListener(this);

	}
	
	private void createMenuAbout()
	{
		//TODO
	}
	
	private void createMenuCheat()
	{
		//TODO
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

	private void letShowMenu(JMenuBar menuBar, boolean show, JFrame frame)
	{
		if (show)
		{
			frame.setJMenuBar(menuBar);
			
		}
	}
	
	@Override
	/**
	 * its only for the timer. timer resize the window
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.updateTimer)
		{
			System.out.println("start loading mainPanel (in UrsuppeGUI) ...  ");
			this.panelMain=(JPanel) SaveAndLoad.loadObject("mainPanel.urs");
			System.out.println("END loading mainPanel (in UrsuppeGUI)  ");
			
			this.resize();

			showMenu();

		}
		else if (e.getSource()==this.menuItemCheat)
		{
			//this.windowCheat=createANewWindow("Cheats", new PhaseCheat(this.game));
			//TODO: uncomment that
			int x=GamePhases.phaseCheat.getWindowsX();
			int y=GamePhases.phaseCheat.getWindowsY();
			
			this.windowCheat.setLocation(500, 0);
			this.windowCheat.setSize(x, y);
			
			boolean showWindowInThisWindow=GamePhases.phaseCheat.getShowMenu();
			this.letShowMenu(this.menuBarCheat,showWindowInThisWindow,this.windowCheat);
			
			
		}
		else if (e.getSource()==this.menuItemExitGame)
		{
			System.exit(0);
		}
		else if (e.getSource()==this.menuItemGoToNextPhase)
		{
			//TODO
			System.out.print("Go next phase...");
			
		}
		else if (e.getSource()==this.menuItemNewGame)
		{
			this.game.createGame();
			
			this.createNew();
			
		}
		else if (e.getSource()==this.menuItemSeeInfo)
		{
			this.windowAbout=createANewWindow("About this game", new PhaseAbout(this.game));
	
			int x=GamePhases.phaseAbout.getWindowsX();
			int y=GamePhases.phaseAbout.getWindowsY();
			
			this.windowAbout.setSize(x, y);
			
			boolean showWindowInThisWindow=GamePhases.phaseAbout.getShowMenu();
			this.letShowMenu(this.menuBarAbout,showWindowInThisWindow,this.windowAbout);
		}
		else
		{
			System.out.print("Error: error in UrsuppeGUI (in actionPerformed()): oops it not in the menu!");
		}
	}

	private void showMenu() {
		if(this.activePhase==GamePhases.phaseAbout)
		{
			this.letShowMenu(this.menuBarAbout,this.activePhase.getShowMenu(),this.windowAbout);
		}
		else if(this.activePhase==GamePhases.phaseCheat)
		{
			this.letShowMenu(this.menuBarCheat,this.activePhase.getShowMenu(),this.windowCheat);
		}
		else if(this.activePhase==GamePhases.phaseAbout)
		{
			this.letShowMenu(this.menuBarWelcome,this.activePhase.getShowMenu(),this.windowWelcome);
		}
		else //phase setNames, setFirstAmeba, and phase 0- phase6
		{
			this.letShowMenu(this.menuBarNormal,this.activePhase.getShowMenu(),this.window);
		}
	}
	
	private void updateData()
	{
		this.changePhase=this.phaseLogic.getChangePhase();
		this.activePhase=this.phaseLogic.getActivePhase();
		
		this.windowsX=this.activePhase.getWindowsX();
		this.windowsY=this.activePhase.getWindowsY();
	}
	
	private void resize()
	{
		this.updateData();
		
		//////////////////////////////////////////////
		//CHECK IF ANOTHER PHASE SHOULD BE SHOW NOW://	
		//////////////////////////////////////////////
		if (this.changePhase!=GamePhases.phaseEmpty && this.phaseLogic.getDoResize()==true)
		{
			this.forceResize();
		}
		
		
		this.phaseLogic.doSelfResize(this.windowsX, this.windowsY,this.activePhase);		//important for null layouts
	}
	
	private void forceResize()
	{
		this.activePhase=this.changePhase;
		
		this.phaseLogic.setDoResize(false);

		if (this.activePhase==null)
		{
			this.activePhase=this.startPhase;
		}

		this.window.setSize(this.windowsX, this.windowsY);

		this.phaseLogic.doSelfResize(this.windowsX, this.windowsY,this.activePhase);	//important for null layouts
		
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
			
			public JFrame getWindow()
			{
				return this.window;
			}
			
			public int getWindowHeight()
			{
				return this.windowsY;
			}	
			
			
			public int getWindowWidth()
			{
				return this.windowsX;
			}
	
			////////////
			//SETTERS://
			////////////
		
			public void showWindow()
			{
				this.window.setVisible(true);
			}
		
			public void hideWindow()
			{
				this.window.setVisible(false);
			}
		
		
			
	
	
	
		/////////////////
		//FAKED EVENTS://
		/////////////////
		
		public void fakeTimer()
		{
			this.phaseLogic.update();
			this.forceResize();
		}
		
		public void fakeShowAbout()
		{
			this.menuItemSeeInfo.doClick();
		}
		
		public void fakeShowCheat()
		{
			this.menuItemCheat.doClick();
		}

}
