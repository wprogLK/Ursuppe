package phases;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import enums.GameColor;
import enums.GamePhases;
import game.Game;
import game.Player;


/**
 * 
 */

/**
 * @author lukas
 *
 */
public class PhaseSetNames extends JPanel implements ActionListener 
{
	//BASIC THINGS OF THIS GUI:
	private boolean isActive;		//if this PhaseGui is the actual gui, the "showing gui"
	private GamePhases activePhase;		//this is IMPORTANT!
	private Game game;
	
	//COMPONENTS OF THIS GUI:
	
		//PANELS:
		private JPanel panelPhaseSetName; 		//Need every GUI! (its the main panel) //IMPORTANT: it contains only other panels!!!
		private JPanel panelButtons;
		
		//LABELS:
		private JLabel labelGameTitle;
		private JLabel labelPhaseTitle;
		
		private JLabel labelNamePlayerBlue;
		private JLabel labelNamePlayerRed;
		private JLabel labelNamePlayerYellow;
	
		//TEXTFIELDS:
		private JTextField textFieldNamePlayerBlue;
		private JTextField textFieldNamePlayerRed;
		private JTextField textFieldNamePlayerYellow;
	
		//BUTTONS:
		private JButton buttonOK;
		private JButton buttonExit;
	
		//RADIO BUTTONS:
		
	
		//BOXES
		
	
		//OTHER STUFF
		
		//SPECIAL COMPONENTS:
	
//		//MENU
//		JMenuBar menuBar=new JMenuBar();
//		
//		JMenu menuGame=new JMenu("Game");
//		JMenu menuCheats=new JMenu("Cheats");
//		JMenu menuAbout=new JMenu("About");
//		
//		JMenu menuSubCurrentGame=new JMenu("Current game");
//		JMenu menuSubOtherGame=new JMenu("Other game");
//		
//		JMenuItem menuItemGoToNextPhase=new JMenuItem("Go to next phase if possible");
//		JMenuItem menuItemExitGame=new JMenuItem("Exit this game");
//		JMenuItem menuItemNewGame=new JMenuItem("Start a new game");
//		JMenuItem menuItemSeeInfo=new JMenuItem("Info");
//		JMenuItem menuItemCheat=new JMenuItem("go to cheats");
		
		
	//VARIABLES OF THIS PHASE:
	private String namePlayerBlue;
	private String namePlayerRed;
	private String namePlayerYellow;
	
	private String gameTitle;
	private String phaseTitle;
	
	
	
	/**
	 * Constructor
	 */
	public PhaseSetNames(Game game) {
		//ARGUMENTES OF THE CONSTRUCTOR:
		this.game=game;
		this.gameTitle=game.getGameTitle();
		
		init();
			
		//CREATE ALL PANELS AND COMPONENTES:
			this.createPanels();
		
	}

	private void init() {
		//CLASS VARIABLES:
		this.isActive=false;
		
		//IMPORTANT STUFF:
		this.activePhase=GamePhases.phaseEmpty;		
		
		//CONFIGURE THIS PANEL:
			//here you can set the Layout and other elementary things
			this.setLayout(new FlowLayout());
			this.setSize(100,100);		//TODO!
			this.setVisible(true);
	}

	//////////////////////
	//GETTERS & SETTERS://
	//////////////////////
	
	//to communicate with the GUILOGIC:
	public String getNamePlayerBlue()
	{
		return this.namePlayerBlue;
	}
	
	//to communicate with the GUILOGIC:
	public String getNamePlayerRed()
	{
		return this.namePlayerRed;
	}
	
	//to communicate with the GUILOGIC:
	public String getNamePlayerYellow()
	{
		return this.namePlayerYellow;
	}
	
	//to communicate with the GUILOGIC:
	public GamePhases getActivePhase()
	{
		return this.activePhase;
	}
	
	
	
	
	
	
	
	///////////////////////////////////
	////////ACTIVATE/DEACTIVATE:///////
	///////////////////////////////////
	//set this GUI to the active GUI (call by GUILOGIC)
	public void activate()
	{
		this.activePhase=GamePhases.phaseEmpty;
		this.isActive=true;
		this.setVisible(true);
		updateData();
	}
	
	public void deactivate()
	{
		this.isActive=false;
		this.setVisible(false);
	}
	
	//////////////////////
	////////UPDATE:///////
	//////////////////////
	public void doUpdate()
	{
		this.updateData();
		this.updateComponents();
	}
	
	private void updateData()
	{
		
	}
	
	/**
	 * UPDATE the components and class variables of this phase //IMPORTANT FOR EVERY PHASE
	 */
	public void updateComponents()
	{
		//PANELS:
		
		//LABELS:
		updateLabels();
		//TEXTFIELDS:
	
	
		//BUTTONS:
	
	
		//RADIO BUTTONS:
		
	
		//BOXES
		
	
		//OTHER STUFF
		
		//SPECIAL STUFF:
		
		
//		//MENU:
//		this.menuBar.add(this.menuGame);
//		
//		this.menuGame.add(this.menuSubCurrentGame);
//		this.menuGame.add(this.menuSubOtherGame);
//		
//		this.menuSubCurrentGame.add(this.menuItemGoToNextPhase);
//		this.menuSubCurrentGame.add(this.menuItemExitGame);
//		
//		this.menuSubOtherGame.add(this.menuItemNewGame);
//		
//
//		
//		this.menuBar.add(this.menuCheats);
//		this.menuCheats.add(this.menuItemCheat);
//		
//		this.menuBar.add(this.menuAbout);
//		this.menuAbout.add(this.menuItemSeeInfo);
//		
//		//this.menuBar.setBounds(0, 0, 1000, 100);
//		
//		this.add(menuBar);
		
	}

	private void updateLabels() {
		this.labelGameTitle.setText(this.gameTitle);
		this.labelPhaseTitle.setText(this.phaseTitle);
	}
	
	/**
	 * update dates in the game IMPORTANT (also for every other phase!!!) !!
	 */
	private void updateGame()
	{
		//TODO!
		getPlayerNames();
		
	
		
		setPlayerName();
		
		//System.out.println("PHASE SET NAME: UPDATE GAMES: name player blue: " + playerBlue.getPlayerName());
		
		
		
	}

	private void setPlayerName() {
		Player playerBlue=this.game.getPlayer(GameColor.blue);
		Player playerRed=this.game.getPlayer(GameColor.red);
		Player playerYellow=this.game.getPlayer(GameColor.yellow);
		
		playerBlue.setPlayerName(this.namePlayerBlue);
		playerRed.setPlayerName(this.namePlayerRed);
		playerYellow.setPlayerName(this.namePlayerYellow);
	}


	
	private void getPlayerNames() {
		this.namePlayerBlue=this.textFieldNamePlayerBlue.getText();
		this.namePlayerRed=this.textFieldNamePlayerRed.getText();
		this.namePlayerYellow=this.textFieldNamePlayerYellow.getText();
	}
	
	/////////////////////////////
	////////CREATE PANELS:///////
	/////////////////////////////
	
	/**
	 * call createPanelGUI1(), createPanelButtons(), createPanelLabels()
	 * this method will be called by the constructor
	 */
	private void createPanels()
	{
		this.createPanelButtons();
		
		//IMPORTANT: createPanelGui1() have to be called at the End of this method!!
		this.createPanelPhaseSetName();
	}
	
	private void createPanelPhaseSetName()
	{	
		
		////////////////
		//CREATE PART://
		////////////////
		
		//...Create panels
		createPanel();
		
		//...Create labels
		createLabel();
		
		//...Create textFields
		createTextFields();
		
		
		//...Create buttons
		
		//...Create radioButtons
		
		
		//...Create boxes
		
		
		//...Create other Stuff
		
		
		/////////////////////////
		//ADD TO LISTENER PART://
		/////////////////////////
		
		//...add panels to listener
		
		
		//...add labels to listener
		
		
		//...add textFields to listener
		
		
		//...add buttons to listener
		
		//...add radioButtons to listener
		
		
		//...add boxes to listener
		
		
		//...add other Stuff to listener
		
		
		///////////////////////
		//ADD TO PANELS PART://
		///////////////////////
		
		//...add panels to panels
		
		
		//...add labels to panels
		
		//...add textFields to panels
		
		
		//...add buttons to panels
		
		//...add radioButtons to panels
		
		
		//...add boxes to panels
		
		
		//...add other Stuff to panels
		
		
		/////////////////////////////////////
		//ADD PANELS TO THE PANELGUI1 PART://
		/////////////////////////////////////
		
		addToPanel();
		
		/////////////////////////////////////////
		//ADD THE PANELGUI1 TO THIS PANEL PART://
		/////////////////////////////////////////
		this.add(this.panelPhaseSetName);
	}

	private void addToPanel() {
		//ROW 1:
		this.panelPhaseSetName.add(this.labelGameTitle);
		this.panelPhaseSetName.add(this.labelPhaseTitle);
		
		//ROW 2:
		this.panelPhaseSetName.add(new JLabel(""));  // for empty cell
		this.panelPhaseSetName.add(new JLabel(""));  // for empty cell
		
		//ROW 3:
		this.panelPhaseSetName.add(this.labelNamePlayerBlue);
		this.panelPhaseSetName.add(this.textFieldNamePlayerBlue);
		
		//ROW 4:
		this.panelPhaseSetName.add(this.labelNamePlayerRed);
		this.panelPhaseSetName.add(this.textFieldNamePlayerRed);
		
		//ROW 5:
		this.panelPhaseSetName.add(this.labelNamePlayerYellow);
		this.panelPhaseSetName.add(this.textFieldNamePlayerYellow);
		
		//ROW 6:
		this.panelPhaseSetName.add(new JLabel(""));  // for empty cell
		this.panelPhaseSetName.add(this.panelButtons);
	}

	private void createTextFields() {
		this.textFieldNamePlayerBlue=new JTextField(30);
		this.textFieldNamePlayerRed=new JTextField(30);
		this.textFieldNamePlayerYellow=new JTextField(30);
	
		this.textFieldNamePlayerBlue.setText("Enter name of player blue");
		this.textFieldNamePlayerRed.setText("Enter name of player red");
		this.textFieldNamePlayerYellow.setText("Enter name of player yellow");
	}

	private void createLabel() {
		this.labelGameTitle=new JLabel(this.gameTitle);
		this.labelPhaseTitle=new JLabel(this.phaseTitle);
		
		this.labelNamePlayerBlue=new JLabel("Name of Player blue: ");
		this.labelNamePlayerRed=new JLabel("Name of Player red: ");
		this.labelNamePlayerYellow=new JLabel("Name of Player yellow: ");
	}

	private void createPanel() {
		this.panelPhaseSetName=new JPanel(new GridLayout(6,2));
	}
	
	private void createPanelButtons()
	{
		////////////////
		//CREATE PART://
		////////////////
		
		//...Create panels
		this.panelButtons=new JPanel(new FlowLayout());
		
		//...Create labels
		
		
		//...Create textFields
		
		
		//...Create buttons
		this.buttonOK=new JButton("Ok");
		this.buttonExit=new JButton("Exit");
		
		//...Create radioButtons
		
		
		//...Create boxes
		
		
		//...Create other Stuff
		
		
		/////////////////////////
		//ADD TO LISTENER PART://
		/////////////////////////
		
		//...add panels to listener
		
		
		//...add labels to listener
		
		
		//...add textFields to listener
		
		
		//...add buttons to listener
		this.buttonOK.addActionListener(this);
		this.buttonExit.addActionListener(this);
		
		//...add radioButtons to listener
		
		
		//...add boxes to listener
		
		
		//...add other Stuff to listener
		
		
		///////////////////////
		//ADD TO PANELS PART://
		///////////////////////
		
		//...add panels to panels
		
		
		//...add labels to panels

		
		//...add textFields to panels
		
		
		//...add buttons to panels
		this.panelButtons.add(this.buttonOK);
		this.panelButtons.add(this.buttonExit);
		
		//...add radioButtons to panels
		
		
		//...add boxes to panels
		
		
		//...add other Stuff to panels
		
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
	
		if(e.getSource()==this.buttonOK)
		{
			//System.out.println("GO PHASE 0");
			this.activePhase=GamePhases.phase0;
			this.updateGame();
		}
		else if (e.getSource()==this.buttonExit)
		{
			//System.out.println("ESCAPE THE GAME!"); //TODO
			System.exit(0);
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
	
		

			////////////
			//*LABELS*//
			////////////
	
		
			
			
			
			/////////////
			//*BUTTONS*//
			/////////////
	
	
			////////////////
			//*TEXTFIELDS*//
			////////////////
	
			
			
			////////////
			//SETTERS://
			////////////
		
		
		
			////////////
			//*LABELS*//
			////////////
		
		
			
			
			
			/////////////
			//*BUTTONS*//
			/////////////
		
		
			////////////////
			//*TEXTFIELDS*//
			////////////////
			
			
	
	
	
		/////////////////
		//FAKED EVENTS://
		/////////////////
		
			
			/////////////
			//*BUTTONS*//
			////////////
		
			public void fakeClickButtonOK()
			{
				this.buttonOK.doClick();
			}
			

			///////////////////
			//*RADIO BUTTONS*//
			///////////////////
	
	
			////////////////
			//*TEXTFIELDS*//
			////////////////
			public void fakeWriteTextFieldNamePlayerBlue(String input)
			{
				this.textFieldNamePlayerBlue.setText(input);
			}
			
			public void fakeWriteTextFieldNamePlayerRed(String input)
			{
				this.textFieldNamePlayerRed.setText(input);
			}
			
			public void fakeWriteTextFieldNamePlayerYellow(String input)
			{
				this.textFieldNamePlayerYellow.setText(input);
			}
}
