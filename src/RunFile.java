import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


/****
 * This is Driver class, class have main function to run the code.
 * For look and feel swings NibusLookAndFeel is used.
 * **/


public class RunFile {
		public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
		{
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			new Design();   //create the instance of the Design class.
		}
}

// end of class RunFile
