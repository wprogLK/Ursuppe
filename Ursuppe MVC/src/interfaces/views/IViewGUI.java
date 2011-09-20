package interfaces.views;

import java.awt.Container;
import java.util.Observer;

import javax.swing.JFrame;

public interface IViewGUI extends Observer, IView
{
	public Container getContainer();
	public JFrame getJFrame();
}
