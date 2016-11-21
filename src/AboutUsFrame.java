import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


//This frame is for About menu



public class AboutUsFrame extends JFrame {
		public AboutUsFrame()
		{
			//Setting title for frame
			super("About Us");
			
			//set size and location
			setBounds(400, 150, 600, 400);
			setResizable(false);
			
			//close operation
			setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			
			//editor pane for html output
			JEditorPane pane=new JEditorPane();
			JScrollPane scrollPane=new JScrollPane(pane);
			pane.setContentType("text/html");
			pane.setEditable(false);
			add(scrollPane);
			try
			{
				
				//All the html input are from A file
				FileInputStream fin=new FileInputStream(new File("A"));
				BufferedReader br=new BufferedReader(new InputStreamReader(fin));
				String str="";
				String content="";
				while((str=br.readLine())!=null)
				{
					content+=str;
				}
				pane.setText(content);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		
}

//End of the file AboutusFrame.Java