/**
 * 
 */
package views.GUI;

import java.awt.Container;
import java.awt.FlowLayout;
import java.util.Observable;

import templates.GameObjectViewGUITemplate;


import models.PlayerModel;

/**
 * @author Lukas
 *
 */
public class PlayerViewGUI  extends GameObjectViewGUITemplate
{
	private PlayerModel model;
	
	/**
	 * 
	 */
	public PlayerViewGUI(PlayerModel model) 
	{
		this.model=model;
		
		this.create();
	}

	public void update(Observable o, Object arg) 
	{
		// TODO Auto-generated method stub
	}

	
	protected void create()
	{
		this.container=new Container();
		
		this.container.setLayout(new FlowLayout());
		
//		AboutController controller=new AboutController(model);
//		
//		JButton btn= new JButton("ABOUT");
//		btn.addActionListener(controller);
//		btn.setActionCommand("exit");
//
//		this.container.add(btn);
	}



}
