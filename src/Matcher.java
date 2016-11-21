import javax.swing.*;

import java.security.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.security.spec.*;
import java.sql.Date;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;



//Class Matcher take care of matching the main file and signature file and key file.

class Matcher extends JInternalFrame  implements ActionListener{

	
		JTextField mainFile;
		JTextField signFile;
		JTextField keyFile;
		JTextField zipFile;
		JTextField outputFile;
		File main;
		File sign;
		File key;
		File Zip;
		File output;
		JRadioButton zip;
		JRadioButton files ;
		ButtonGroup group ;
		static final int xPosition = 30, yPosition = 30;
		public Matcher() {
			super("Signature Matcher", false, // resizable
					true, // closable
					false, // maximizable
					true);// iconifiable
			setSize(600, 500);
			// Set the window's location.
			setLocation(xPosition , yPosition);
			setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
			setLayout(new BorderLayout());
			design();
		}
		
		//take care of the design
		void design()
		{
			JLabel heading=new JLabel("<html><h1 style=\"color:green; border:1px solid cyan;\">&nbsp; &nbsp;  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Signature Matcher</h1></font></html>");
			add(heading,"North");
			 mainFile=new JTextField("Main File");
		     mainFile.setEnabled(false);
		     keyFile=new JTextField("Key File");
		     keyFile.setEnabled(false);
		     signFile=new JTextField("Signature File");
		     signFile.setEnabled(false);
		     zipFile=new JTextField("Zip File");
		     zipFile.setEnabled(false);
		     JPanel centerPanel=new JPanel();
		     centerPanel.setLayout(new GridLayout(9,1));
		     JPanel first=new JPanel();
		     first.setLayout(new BorderLayout());
		     
		     //first.add(new JLabel("<html><span style=\"color:green; font-size:14px; \">Main File : </h1></span></html>"),"West");
		     JButton mainFileButton=new JButton("<html><span style=\"color:green; font-size:13px; \">Select Main File </h1></span></html>");
		     mainFileButton.addActionListener(this);
		     first.add(mainFileButton,"East");
		     first.add(mainFile);
		     centerPanel.add(first);


		     JPanel second=new JPanel();
		     second.setLayout(new BorderLayout());
		     JButton selectSignButton=new JButton("<html><span style=\"color:green; font-size:14px; \"> &nbsp; Signature  File </h1></span></html>");
		     selectSignButton.addActionListener(this);
		     //second.add(new JLabel("<html><span style=\"color:green; font-size:14px; \">Signature File : </h1></span></html>"),"West");
		     second.add(signFile);
		     second.add(selectSignButton,"East");
		     centerPanel.add(second);
		     
		     
		     JPanel third=new JPanel();
		     third.setLayout(new BorderLayout());
		     JButton selectKeyButton=new JButton("<html><span style=\"color:green; font-size:14px; \">Select Key  File </h1></span></html>");
		     selectKeyButton.addActionListener(this);
		    // third.add(new JLabel("<html><span style=\"color:green; font-size:14px; \">Key File : </h1></span></html>"),"West");
		     third.add(keyFile);
		     third.add(selectKeyButton,"East");
		     centerPanel.add(third);
		     
		     
		     centerPanel.add(new JLabel("<html><span style=\"color:green; font-size:14px; \">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ---Or---</h1></span></html>"));
		     
		     JPanel four=new JPanel();
		     four.setLayout(new BorderLayout());
		     JButton selectZipButton=new JButton("<html><span style=\"color:green; font-size:14px; \">Select Zip  File </h1></span></html>");
		     selectZipButton.addActionListener(this);
		    // four.add(new JLabel("<html><span style=\"color:green; font-size:14px; \">Zip File : </h1></span></html>"),"West");
		     four.add(zipFile);
		     four.add(selectZipButton,"East");
		     centerPanel.add(four);
		     
		     
		     
		     JPanel five=new JPanel();
		     five.setLayout(new GridLayout(1,5));
		     zip = new JRadioButton("Zip");
		     files= new JRadioButton("Files");
		     group= new ButtonGroup();
		     five.add(new JLabel());
		     five.add(zip);
		     five.add(new JLabel());
		     five.add(files);
		     five.add(new JLabel());
		     group.add(zip);
		     group.add(files);
		     centerPanel.add(five);
		     
		     
		   
		     	
		     
		     
		     JPanel seven=new JPanel();
		     seven.setLayout(new GridLayout(1,3));
		     seven.add(new JLabel(" "));
		     JButton match=new JButton("<html><span style=\"color:green; font-size:14px; \">Match Signature</h1></span></html>");
		     match.addActionListener(this);
		     seven.add(match);
		     seven.add(new JLabel(" "));
		     centerPanel.add(new JPanel());
		     centerPanel.add(seven);
		     
		     
		     
		     
		     add(centerPanel);
		     files.setSelected(true);
		}
		
		
		
		//Actions for all the menus and button of the metcher class.
		@Override
		public void actionPerformed(ActionEvent e) {
			
			String key=e.getActionCommand();
			System.out.println(key);
			if(key.equals("<html><span style=\"color:green; font-size:13px; \">Select Main File </h1></span></html>"))
			{
				JFileChooser chooser=new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int returnVal =chooser.showOpenDialog(this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					main=chooser.getSelectedFile();
					mainFile.setText(main+"");
		        }
			}
			else if(key.equals("<html><span style=\"color:green; font-size:14px; \"> &nbsp; Signature  File </h1></span></html>"))
			{
				JFileChooser chooser=new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int returnVal =chooser.showOpenDialog(this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					sign=chooser.getSelectedFile();
					signFile.setText(sign+"");
		        }
			}
			else if(key.equals("<html><span style=\"color:green; font-size:14px; \">Select Key  File </h1></span></html>"))
			{
				JFileChooser chooser=new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int returnVal =chooser.showOpenDialog(this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					this.key=chooser.getSelectedFile();
					keyFile.setText(this.key+"");
		        }
			}
			else if(key.equals("<html><span style=\"color:green; font-size:14px; \">Select Zip  File </h1></span></html>"))
			{
				JFileChooser chooser=new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int returnVal =chooser.showOpenDialog(this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					this.Zip=chooser.getSelectedFile();
					zipFile.setText(this.Zip+"");
		        }
			}
			else if(key.equals("<html><span style=\"color:green; font-size:14px; \">Select Directory</h1></span></html>"))
			{
				JFileChooser chooser=new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal =chooser.showOpenDialog(this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					this.output=chooser.getSelectedFile();
					outputFile.setText(this.output+"");
		        }
			}
			else
			{
				
				if(files.isSelected())
				{
					if(main==null)
					{
						JOptionPane.showMessageDialog(this, "Please Select Main File.");
						return;
					}
					else if(this.key==null)
					{
						JOptionPane.showMessageDialog(this, "Please Select Key File.");
						return;
					}
					else if(this.sign==null)
					{
						JOptionPane.showMessageDialog(this, "Please Select Signature File.");
						return;
					}
					try
					{
						
					
					FileInputStream keyfis = new FileInputStream(this.key);
					byte[] encKey = new byte[keyfis.available()];  
					keyfis.read(encKey);
					System.out.println(encKey);
					keyfis.close();
					
					X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encKey);
					KeyFactory keyFactory = KeyFactory.getInstance("DSA", "SUN");
					
					PublicKey pubKey = keyFactory.generatePublic(pubKeySpec);
					
					FileInputStream sigfis = new FileInputStream(sign);
					byte []sigToVerify = new byte[sigfis.available()]; 
					sigfis.read(sigToVerify);
					System.out.println(sigToVerify);
					sigfis.close();
					
					Signature sig = Signature.getInstance("SHA1withDSA", "SUN");
					sig.initVerify(pubKey);
					
					FileInputStream datafis = new FileInputStream(this.main);
					BufferedInputStream bufin = new BufferedInputStream(datafis);

					byte[] buffer = new byte[1024];
					int len;
					while (bufin.available() != 0) {
						len = bufin.read(buffer);
						sig.update(buffer, 0, len);
					};

					bufin.close();
					
					boolean verifies = sig.verify(sigToVerify);

					if(verifies)
					{
						JOptionPane.showMessageDialog(this, "Success : File, key and signature matched.");
					}
					else
					{
						JOptionPane.showMessageDialog(this, "Error : File, key and signature not  matched.");
					}
					}
					catch(Exception ee)
					{
						JOptionPane.showMessageDialog(this, "Error : Invaild File");
						ee.printStackTrace();
					}
				}
				else
				{
					
					if(Zip==null)
					{
						JOptionPane.showMessageDialog(this, "Please Select Zip File.");
						return;
					}
					try {
						ZipFile zf = new ZipFile(Zip);
					      Enumeration<? extends ZipEntry> entries = zf.entries();
					      
					      File key1=null;
					      File sign1=null;
					      File cpp=null;
					      
					      while (entries.hasMoreElements()) {
					    	  
					    	  ZipEntry ze = (ZipEntry) entries.nextElement();
					    	  System.out.println(ze.getName());
					    	  if(ze.getName().substring(ze.getName().lastIndexOf('.')).equals(".key"))
					    	  {
					    		  System.out.println("Key");
					    		  key1=new File(ze.getName());
					    		  
					    		  FileOutputStream out=new FileOutputStream(key1);
					    		  BufferedInputStream br=new BufferedInputStream(zf.getInputStream(ze));
					    		  int ch;
					    		  while((ch=br.read())!=-1)
					    		  {
					    			  out.write(ch);
					    		  }
					    		  
					    		  br.close();
					    		   
					    		  out.close();
					    					    		  
					    		  
					    		  
					    	  } else if(ze.getName().substring(ze.getName().lastIndexOf('.')).equals(".Sign"))
					    	  {
					    		  System.out.println("Sign");
					    		  sign1=new File(ze.getName());
					    		  FileOutputStream out=new FileOutputStream(sign1);
					    		  BufferedInputStream br=new BufferedInputStream(zf.getInputStream(ze));
					    		  int ch;
					    		  while((ch=br.read())!=-1)
					    		  {
					    			  out.write(ch);
					    		  }
					    		  
					    		  br.close();
					    		   
					    		  out.close();
					    		  
					    		  
					    		  
					    	  }
					    	  else
					    	  {
					    		  cpp=new File(ze.getName());
					    		  FileOutputStream out=new FileOutputStream(cpp);
					    		  BufferedInputStream br=new BufferedInputStream(zf.getInputStream(ze));
					    		  int ch;
					    		  while((ch=br.read())!=-1)
					    		  {
					    			  out.write(ch);
					    		  }
					    		  
					    		  br.close();
					    		   
					    		  out.close();
					    		  
					    	  }
					    	 
					        }
					      	
					      	
						
					      
					      FileInputStream keyfis = new FileInputStream(key1);
							byte[] encKey = new byte[keyfis.available()];  
							keyfis.read(encKey);
							System.out.println(encKey);
							keyfis.close();
							
							X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encKey);
							KeyFactory keyFactory = KeyFactory.getInstance("DSA", "SUN");
							
							PublicKey pubKey = keyFactory.generatePublic(pubKeySpec);
							
							FileInputStream sigfis = new FileInputStream(sign1);
							byte []sigToVerify = new byte[sigfis.available()]; 
							sigfis.read(sigToVerify);
							System.out.println(sigToVerify);
							sigfis.close();
							
							Signature sig = Signature.getInstance("SHA1withDSA", "SUN");
							sig.initVerify(pubKey);
							
							FileInputStream datafis = new FileInputStream(cpp);
							BufferedInputStream bufin = new BufferedInputStream(datafis);

							byte[] buffer = new byte[1024];
							int len;
							while (bufin.available() != 0) {
								len = bufin.read(buffer);
								sig.update(buffer, 0, len);
							};

							bufin.close();
							
							boolean verifies = sig.verify(sigToVerify);

							if(verifies)
							{
								JOptionPane.showMessageDialog(this, "Success : File, key and signature matched.");
							}
							else
							{
								JOptionPane.showMessageDialog(this, "Error : File, key and signature not  matched.");
							}
					      
					      
					      
					      
							key1.delete();
							cpp.delete();
							sign1.delete();
					        zf.close();
					      
					    } catch (Exception e1) {
					      e1.printStackTrace();
					      JOptionPane.showMessageDialog(this, "Error : Invaild File");
					    }
					finally
					{
						
					}

					
				}
			}
		}
		
	}
