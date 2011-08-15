package game;

import java.awt.Rectangle;


import javax.swing.*;

/*DR what's this class for?
 * the name does not rly tell me and the methods neither (problemset09 should fix this^^)
 * 
 * LK calc=calculation;
 * Now we have a JavaDoc, is it now better?
 * 
 */

/**
 * it's calculate and set the bounds of different components
 */
public class Calc 
{
	private final int averageLengthOfOneChar=2;
	private final int extraSpaceForHeight=5;

	public Calc()
	{
		
	}
	/**
	 *  calculate the end position of a label, create a rectangle on position {@posX} {@posY} and setBounds of the label with this rectangle
	 * @param label
	 * @param posX
	 * @param posY
	 */
	public void calcLabel(JLabel label, double posX, double posY) 
	{
		String content=label.getText();
		int sizeFont=label.getFont().getSize();
		int lengthOfContent=content.length();
		
		int lengthOfRectangle=lengthOfContent*this.averageLengthOfOneChar*sizeFont;
		
		
		label.setBounds(this.createRectangle(posX, posY, lengthOfRectangle, sizeFont+this.extraSpaceForHeight));
	}
	
	/**
	 * calculate the end position of a button, create a rectangle on position {@posX} {@posY} and setBounds of the button with this rectangle
	 * @param button
	 * @param posX
	 * @param posY
	 */
	public void calcButton(JButton button, double posX, double posY) 
	{
		
		String content=button.getText();
		int sizeFont=button.getFont().getSize();
		int lengthOfContent=content.length();
		
		int lengthOfRectangle=lengthOfContent*sizeFont+10;
		
			
		button.setBounds(this.createRectangle(posX, posY, lengthOfRectangle, sizeFont+this.extraSpaceForHeight));
	}
	
	/**
	 * create a rectangle on position {@posX} {@posY} with a fix width and high of 900 | 300 and setBounds of the list with this rectangle
	 * @param list
	 * @param posX
	 * @param posY
	 */
	public void calcList(JList list, double posX, double posY) 
	{
		//TODO dynamic!
		
		list.setBounds(this.createRectangle(posX, posY, 900, 300));
	}
	
	/**
	 * calculate the end position of a textField, create a rectangle on position {@posX} {@posY} and setBounds of the textField with this rectangle
	 * @param textField
	 * @param posX
	 * @param posY
	 */
	public void calcTextField(JTextField textField, double posX, double posY)
	{
		int maxLengthOfContent=textField.getColumns();
		int sizeFont=textField.getFont().getSize();
		
		int lengthOfRectangel=maxLengthOfContent*sizeFont;
		
		textField.setBounds(this.createRectangle(posX, posY, lengthOfRectangel, sizeFont+this.extraSpaceForHeight));
	}
	
/**
 * calculate the end position of a radioButton, create a rectangle on position {@posX} {@posY} and setBounds of the radioButton with this rectangle
 * @param radioButton
 * @param posX
 * @param posY
 */
	public void calcRadioButton(JRadioButton radioButton, double posX, double posY) 
	{
		String content=radioButton.getText();
		int sizeFont=radioButton.getFont().getSize();
		int lengthOfContent=content.length();
		
		int lengthOfRectangle=lengthOfContent*sizeFont*this.averageLengthOfOneChar;
		
			
		radioButton.setBounds(this.createRectangle(posX, posY, lengthOfRectangle, sizeFont+this.extraSpaceForHeight));
	}
	
	/**
	 *calculate the end position of a panel, create a rectangle on position {@posX} {@posY} and setBounds of the panel with this rectangle
	 * @param panel
	 * @param posX
	 * @param posY
	 */
	public void calcPanel(JPanel panel, double posX, double posY) 
	{
		double widthPanel=panel.getWidth();
		double heightPanel=panel.getHeight();
		
		panel.setBounds(this.createRectangle(posX, posY, (int) widthPanel, (int) heightPanel));
	}
	
	/**
	 * calculate the end position of a board, create a rectangle on position {@posX} {@posY} and setBounds of the board with this rectangle
	 * @param board
	 * @param posX
	 * @param posY
	 */
	public void calcBoard(Board board, double posX, double posY) 
	{
		double widthBoard=board.getWidth();
		double heightBoard=board.getHeight();
		
			
		board.setBounds(this.createRectangle(posX, posY, (int) widthBoard, (int) heightBoard));
	}
	
	public void calcTextArea(JTextArea textArea, double posX, double posY, double posStartX , double posStartY) {
		
	
		
		textArea.setBounds(this.createRectangle(posX, posY, (int) posStartX, (int) posStartY));
	}
	
	/**
	 * calculate the end position of a die, create a rectangle on position {@posX} {@posY} and setBounds of the die with this rectangle
	 * @param die
	 * @param posX
	 * @param posY
	 */
	public void calcDie(Die die, double posX, double posY)
	{
		double widthDie=die.getWidthOfDie();
		double heightDie=die.getHeightOfDie();
		
		die.setBounds(this.createRectangle(posX, posY, (int) widthDie,(int) heightDie));
	}
	
	/**
	 * create a rectangle with startPosition {@posX} {@posY} and ends on position {@posX} + {@widthOfRectangle}  {@posY} + {@heightOfRectangle} 
	 * @param posX
	 * @param posY
	 * @param widthOfRectangle
	 * @param heightOfRectangle
	 * @return Rectangle rect
	 */
	private Rectangle createRectangle(double posX, double posY, int widthOfRectangle, int heightOfRectangle)
	{
		Rectangle rect=new Rectangle();
		
		
		rect.setRect(posX, posY, widthOfRectangle, heightOfRectangle);
		
		
		return rect;
	}
	
	

	

}
