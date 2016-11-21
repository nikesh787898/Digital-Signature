import javax.swing.*;

import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;



//Data class contain common data between the Action class and Design class.

public class Data {
	private static Data d;
	
	//static block for making class singleton 
	static
	{
		d=new Data();
	}
	
	//private constructor for making  class sington.
	private Data()
	{
		
	}
	
	//  For making instance of the Data class avaiable outside of the class. 
	static  public Data get()
	{
		return d;
	}
	
	//Common Data.
	AboutUsFrame aboutUsFrame=new AboutUsFrame();
	public JFrame frame;
	public JDesktopPane desktop;
	public JInternalFrame generatorFrame;
	public JInternalFrame matcherFrame;

}
