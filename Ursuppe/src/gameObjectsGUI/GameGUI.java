package gameObjectsGUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.GameLogic;
import module.ModuleASCII;
import module.ModuleGUI;

import templates.GameTemplate;

public class GameGUI extends GameTemplate
{
	private JPanel mainPanel;
	private JFrame mainWindow;
	
	public GameGUI()
	{
		this.module=new ModuleGUI();
	
		this.setupPlayers();
		
		this.setupWindow();
		
		//mainWindow.pack();
	}
	
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
	public void playExtras()
	{
		mainWindow.setVisible(true);
	}
	
	public JPanel getMainPanel()
	{
		return this.mainPanel;
	}

	@Override
	public void update()
	{
		this.mainPanel.updateUI();			//IMPORTANT: UPDATE THE PANEL WITH ITS CURRENT COMPONENTS!
	}
	
}
