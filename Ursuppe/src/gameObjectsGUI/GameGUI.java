package gameObjectsGUI;

import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JPanel;

import enums.EPhases;

import main.GameLogic;
import module.ModuleASCII;
import module.ModuleGUI;

import templates.GameTemplate;

public class GameGUI extends GameTemplate
{
	private JPanel mainPanel;
	private JFrame mainWindow;
	
	/**
	 * setup for an ASCII game.
	 * 
	 * </br> sets the module for ASCII, calls the setupPlayers (in the GameTemplate class) for the head and tail player, and calls the setup for the window
	 * 
	 * @see GameTemplate
	 */
	public GameGUI(PrintStream outStream, PrintStream errorStream)
	{
		super(outStream, errorStream);
		this.module=new ModuleGUI(outStream,outStream);
	
		this.setupPlayers();
		
		this.setupWindow();
	}
	
	/**
	 * creates the main window of the hole game
	 */
	private void setupWindow()
	{
		this.mainWindow=new JFrame("TestDemo: The logic of the ursuppe");
		
		this.mainPanel=new JPanel();
		
		mainWindow.setContentPane(mainPanel);
		
		mainWindow.setSize(1000, 1000);
	}
	
	@Override
	public void createNew() 
	{
		this.gameLogic=new GameLogic(this);
		this.gameLogic.createGUI();
		this.gameLogic.setGameToAllPhases();
		
		super.setMainPanelToAllPhases(this.mainPanel);
	}
	
	
	@Override
	/**
	 * setup if the mainWindow is visible or not
	 */
	public void playExtras()
	{
		mainWindow.setVisible(true);
	}
	
	public JPanel getMainPanel()
	{
		return this.mainPanel;
	}

	@Override
	/**
	 * IMPORTANT: UPDATE THE PANEL WITH ITS CURRENT COMPONENTS!
	 */
	public void update()
	{
		this.mainPanel.updateUI();
	}
	
}
