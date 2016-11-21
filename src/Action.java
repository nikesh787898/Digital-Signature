import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;



/*
 * This class take care of all the action related to the main frame and menu of the main
 * frame.
 * **/


//class Action implements two listener for window and action of the menu.
public class Action implements WindowListener , ActionListener {
		
	
	//Data class is common data between the Action class and Design class.
	private Data d;
	public Action(Data d)
	{
		this.d=d;
		
		//Data class is singleton class.
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	//for closing the frame
	public void windowClosed(WindowEvent arg0) {
		
		if (JOptionPane.showConfirmDialog(d.frame, "Are you sure?", "WARNING",
		        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
		    System.exit(0);
		} 
		
	}

	//for closing the frame
	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		if (JOptionPane.showConfirmDialog(d.frame, "Are you sure?", "WARNING",
		        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
		    System.exit(0);
		} 
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	//action for menu of the frame
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		String key=ae.getActionCommand();   //for which button was pressed.
		if(key.equals("Signature Generator"))    
		{
			System.out.println("Generator");
			d.generatorFrame.setVisible(true);   //make frame visible
			
		} else if(key.equals("Signature Matcher"))
		{
			System.out.println("Matcghr");
			d.matcherFrame.setVisible(true);
		}
		else if(key.equals("Exit"))
		{
			System.out.println("Exit");
			if (JOptionPane.showConfirmDialog(d.frame, "Are you sure?", "WARNING",
			        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			    System.exit(0);
			} 
		}
		else
		{
			
			d.aboutUsFrame.setVisible(true);
		}
	}

	

}


// end of the class Action 