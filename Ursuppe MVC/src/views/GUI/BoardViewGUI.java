/**
 * 
 */
package views.GUI;

import java.awt.Container;
import java.awt.FlowLayout;
import java.util.Observable;

import templates.GameObjectViewGUITemplate;

import models.BoardModel;

/**
 * @author Lukas
 *
 */
public class BoardViewGUI  extends GameObjectViewGUITemplate
{
	private BoardModel model;
	
	/**
	 * 
	 */
	public BoardViewGUI(BoardModel model) 
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
		
//		JButton btn= new JButton("ABOUT");
//		btn.addActionListener(controller);
//		btn.setActionCommand("exit");
//
//		this.container.add(btn);
	}



}
