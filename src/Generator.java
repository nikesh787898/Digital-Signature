


import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.security.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


//This class Take care of design and Backend of the Generating the Signature file and
// key file.

class Generator extends JInternalFrame implements ActionListener {
	
	//Instance varibale for Generater class.
	JTextField tf;
	ButtonGroup group ;
	JTextField textfield;
	File selectedFile;
	File selectedDirectory;
	JRadioButton zip;
	JRadioButton files ;
		static final int xPosition = 200, yPosition = 300;
		public Generator() {
			super("Signature Generator", false, // resizable
					true, // closable
					false, // maximizable
					true);// iconifiable
			setSize(600, 300);
			// Set the window's location.
			setLocation(xPosition , yPosition);
			setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
			setLayout(new BorderLayout());
			design();
		}
		
		//This function take care of the Design of the generater frame.
		void design()
		{ 	
			JLabel heading=new JLabel("<html><h1 style=\"color:green; border:1px solid cyan;\">&nbsp; &nbsp;  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Signature Generator</h1></font></html>");
			add(heading,"North");
	        tf=new JTextField();
	        tf.setEnabled(false);
	        JPanel centerPanel=new JPanel();
	        centerPanel.setLayout(new GridLayout(5,1));
	        JPanel first=new JPanel();
	        first.setLayout(new BorderLayout());
	        JButton selectFile=new JButton("<html><span style=\"color:green; font-size:14px; \">Select File </h1></span></html>");
	        selectFile.addActionListener(this);
	        first.add(tf);
	        first.add(selectFile,"East");
	        centerPanel.add(first);
	        JPanel second=new JPanel();
	        second.setLayout(new GridLayout(1,3));
	        zip = new JRadioButton("Zip");
	        files= new JRadioButton("Files");
	        group= new ButtonGroup();
	        second.add(zip);
	        second.add(files);
	        group.add(zip);
	        group.add(files);
	        centerPanel.add(second);
	        JPanel third=new JPanel();
	        third.setLayout(new BorderLayout());
	       
	        textfield=new JTextField();
	        textfield.setEnabled(false);
	        third.add(textfield);
	        JButton selectDirectory=new JButton("<html><span style=\"color:green; font-size:14px; \">Select Directory</h1></span></html>");
	        selectDirectory.addActionListener(this);
	        third.add(selectDirectory,"East");
	        centerPanel.add(third);
	        JPanel four=new JPanel();
	        four.setLayout(new GridLayout(1,3));
	        four.add(new JLabel(" "));
	        JButton generate=new JButton("<html><span style=\"color:green; font-size:14px; \">Generate Signature</h1></span></html>");
	        generate.addActionListener(this);
	        four.add(generate);
	        four.add(new JLabel(" "));
	        centerPanel.add(new JPanel());
	        centerPanel.add(four);
	        add(centerPanel);   //adding main panel to the frame.
	       // chooser.showOpenDialog(this);
	        
		}
		
		// Action for button related to the generater class.
		@Override
		public void actionPerformed(ActionEvent ae)
		{
			String key=ae.getActionCommand();
			if(key.equals("<html><span style=\"color:green; font-size:14px; \">Select File </h1></span></html>"))
			{
				JFileChooser chooser=new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int returnVal =chooser.showOpenDialog(this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					selectedFile=chooser.getSelectedFile();
					tf.setText(selectedFile+"");
		        }
			}
			else if(key.equals("<html><span style=\"color:green; font-size:14px; \">Select Directory</h1></span></html>"))
			{
				JFileChooser chooser=new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal =chooser.showOpenDialog(this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					selectedDirectory=chooser.getSelectedFile();
					textfield.setText(selectedDirectory+"");
		        }
			}
			else
			{
				
				//validation support and error messages
				if(this.selectedDirectory==null)
				{
					JOptionPane.showMessageDialog(this, "Please Select Destination Directory.");
					return;
				}
				if(this.selectedFile==null)
				{
					JOptionPane.showMessageDialog(this, "Please Select File" );
					return ;
				}
				try
				{
				//Main logic for generating key for the file.
					
				/***
				 * The KeyPairGenerator class is used to generate pairs of public and private keys. Key pair generators are constructed using the getInstance factory methods (static methods that return instances of a given class).
A Key pair generator for a particular algorithm creates a public/private key pair that can be used with this algorithm. It also associates algorithm-specific parameters with each of the generated keys.

There are two ways to generate a key pair: in an algorithm-independent manner, and in an algorithm-specific manner. The only difference between the two is the initialization of the object:
				 * **/
				KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
				
				
				/**
				 * his class provides a cryptographically strong random number generator (RNG).
A cryptographically strong random number minimally complies with the statistical random number generator tests specified in FIPS 140-2, Security Requirements for Cryptographic Modules, section 4.9.1. Additionally, SecureRandom must produce non-deterministic output. Therefore any seed material passed to a SecureRandom object must be unpredictable, and all SecureRandom output sequences must be cryptographically strong, as described in RFC 1750: Randomness Recommendations for Security.

A caller obtains a SecureRandom instance via the no-argument constructor or one of the getInstance methods:

      SecureRandom random = new SecureRandom();
				 * **/
				
				SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
				keyGen.initialize(1024, random);
				
				
				//This class is a simple holder for a key pair (a public key and a private key). It does not enforce any security, and, when initialized, should be treated like a PrivateKey.
				KeyPair pair = keyGen.generateKeyPair();
				
				//A private key. This interface contains no methods or constants. It merely serves to group (and provide type safety for) all private key interfaces. Note: The specialized private key interfaces extend this interface. See, for example, the DSAPrivateKey interface in java.security.interfaces.
				PrivateKey priv = pair.getPrivate();
				
				/**
				 * A public key. This interface contains no methods or constants. It merely serves to group (and provide type safety for) all public key interfaces. Note: The specialized public key interfaces extend this interface. See, for example, the DSAPublicKey interface in java.security.interfaces.
				 * ***/
				
				PublicKey pub = pair.getPublic();
				
				
				/**
				 * The Signature class is used to provide applications the functionality of a digital signature algorithm. Digital signatures are used for authentication and integrity assurance of digital data.
The signature algorithm can be, among others, the NIST standard DSA, using DSA and SHA-1. The DSA algorithm using the SHA-1 message digest algorithm can be specified as SHA1withDSA. In the case of RSA, there are multiple choices for the message digest algorithm, so the signing algorithm could be specified as, for example, MD2withRSA, MD5withRSA, or SHA1withRSA. The algorithm name must be specified, as there is no default.

A Signature object can be used to generate and verify digital signatures.
				 * **/
				
				Signature dsa = Signature.getInstance("SHA1withDSA", "SUN"); 
				dsa.initSign(priv);
				
				FileInputStream fis = new FileInputStream(selectedFile);
				BufferedInputStream bufin = new BufferedInputStream(fis);
				byte[] buffer = new byte[1024];
				int len;
				while ((len = bufin.read(buffer)) >= 0) {
					dsa.update(buffer, 0, len);
				};
				bufin.close();
				
				byte[] realSig = dsa.sign();
				
				/* save the signature in a file */
				String des=this.selectedDirectory+File.separator+selectedFile.getName()+".Sign";
				FileOutputStream sigfos = new FileOutputStream(des);
				sigfos.write(realSig);
				sigfos.close();
				
				/* save the public key in a file */
				byte[] fileKey = pub.getEncoded();
				String desti=this.selectedDirectory+File.separator+selectedFile.getName()+".key";
				FileOutputStream keyfos = new FileOutputStream(desti);
				keyfos.write(fileKey);
				keyfos.close();
				
				//zip file if zip is selected
			
				if(zip.isSelected())
				{
					FileOutputStream fos = new FileOutputStream(selectedDirectory+File.separator+"Demo.zip");
					ZipOutputStream zos = new ZipOutputStream(fos);
					addToZipFile(desti, zos);
					addToZipFile(des, zos);
					//Read file and add them to zip
					addToZipFile(selectedFile.toString(), zos);
					
					zos.close();
					fos.close();
					
					//Delete temp files
					new File(des).delete();
					new File(desti).delete();
				}
				textfield.setText("");
				tf.setText("");
				this.selectedDirectory=null;
				this.selectedFile=null;
				
				
				//Message for user
				JOptionPane.showMessageDialog(this, "Process Completed Successfully.");
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
			}
		}
		
		//This function add the file to the zip
		public  void addToZipFile(String fileName, ZipOutputStream zos) throws FileNotFoundException, IOException {

			System.out.println("Writing '" + fileName + "' to zip file");

			File file = new File(fileName);
			FileInputStream fis = new FileInputStream(file);
			ZipEntry zipEntry = new ZipEntry(file.getName());
			zos.putNextEntry(zipEntry);

			byte[] bytes = new byte[1024];
			int length;
			while ((length = fis.read(bytes)) >= 0) {
				zos.write(bytes, 0, length);
			}

			zos.closeEntry();
			fis.close();
		}
	}


//End of the class Generator.