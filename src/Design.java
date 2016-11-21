import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;


/***
 * This class File contain design related code.
 * All the basic design are created using swing.
 * **/


public class Design {
		Data d;
		Action action;
		
		//init method initlize member of class Data, Data class is used for holding common information between design and Action class.
		
		private void init()
		{
			d=Data.get();
			action=new Action(d);
			d.frame=new JFrame("Digital Signature Verification");
			d.desktop = new javax.swing.JDesktopPane()

{
    ImageIcon icon = new ImageIcon("image/a.jpg");
    Image image = icon.getImage();

    Image newimage = image.getScaledInstance(1500, 1000, Image.SCALE_SMOOTH);

    // For paint the image at the background of the panel.
    @Override     
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(newimage, 0, 0, this);
    }
}
;
			d.generatorFrame=new Generator();
			d.matcherFrame=new Matcher();
		}  
		// end of init method
		
		//
		/**
		 * after init design method place all the component at correct place using lauoyts.
		 * 
		 * **/
		public Design()
		{
			init();
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			d.frame.setIconImage(new ImageIcon("image/f.png").getImage());
			d.frame.setSize(screenSize);
			d.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			d.frame.addWindowListener(action);
			d.frame.setContentPane(d.desktop);
			d.frame.setJMenuBar(createMenuBar());
			d.desktop.add(d.generatorFrame);
			d.desktop.add(d.matcherFrame);
			d.frame.setVisible(true);
		}
		
		//method create menu bar of of the main frame.
		protected JMenuBar createMenuBar() {
			JMenuBar menuBar = new JMenuBar();
			JMenu menu1 = new JMenu("File");
			JMenu menu2 = new JMenu("About");
			JMenuItem menuItem1 = new JMenuItem("Signature Generator");
			JMenuItem menuItem2 = new JMenuItem("Signature Matcher");
			JMenuItem menuItem3 = new JMenuItem("Exit");
			JMenuItem menuItem4 = new JMenuItem("About Us");
			menu1.add(menuItem1);
			menu1.add(menuItem2);
			menu1.add(menuItem3);
			menu2.add(menuItem4);
			menuBar.add(menu1);
			menuBar.add(menu2);
			ImageIcon image1=new ImageIcon(new ImageIcon("image/a.png").getImage().getScaledInstance(10, 10,  java.awt.Image.SCALE_SMOOTH));
			ImageIcon image2=new ImageIcon(new ImageIcon("image/b.png").getImage().getScaledInstance(10, 10,  java.awt.Image.SCALE_SMOOTH));
			ImageIcon image3=new ImageIcon(new ImageIcon("image/c.jpg").getImage().getScaledInstance(10, 10,  java.awt.Image.SCALE_SMOOTH));
			//set icon for all the menu
			menuItem1.setIcon(image1);
			menuItem2.setIcon(image2);
			menuItem3.setIcon(image3);
			
			//adding listener to the all menus
			menuItem1.addActionListener(action);
			menuItem2.addActionListener(action);
			menuItem3.addActionListener(action);
			menuItem4.addActionListener(action);
			return menuBar;
		}
}


// end of the Design class.