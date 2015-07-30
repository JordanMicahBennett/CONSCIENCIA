//author jordan micah bennett
//here some tricks are done to achive a very bland ui feel.
package data.packages.UNICODE;

import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

 
public class UNICODE_BlandTabbedPaneUI extends BasicTabbedPaneUI
{
	public UNICODE_BlandTabbedPaneUI ( )
	{
        try
        {
            UIManager.setLookAndFeel ( "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel" );
        }
        catch (  Exception ex ) 
		{
		}
		
		// UIManager.put ( "control", new Color ( 255, 255, 255 ) );
		// UIManager.put ( "nimbusBase", new Color ( 255, 255, 255 ) );
		// UIManager.put ( "nimbusFocus", new Color ( 255, 255, 255 ) );
		// UIManager.put ( "nimbusInfoBlue", new Color ( 255, 255, 255 ) );
		// UIManager.put ( "nimbusSelectionBackground", new Color ( 255, 255, 255 ) );
		// UIManager.put ( "nimbusSelectionBackground", new Color ( 255, 255, 255 ) );
			
	}

	private class ScrollableTabPanel extends JPanel implements UIResource 
	{
		public ScrollableTabPanel ( ) 
		{
			setLayout ( null );
		}

		public void paintComponent ( Graphics graphics ) 
		{
			super.paintComponent ( graphics );

			graphics.setColor ( Color.white );
			graphics.fillRect ( ( int ) tabPane.getBounds ( ).getX ( ), ( int ) tabPane.getBounds ( ).getY ( ), ( int ) tabPane.getBounds ( ).getWidth ( ), ( int ) tabPane.getBounds ( ).getHeight ( ) ); 
		}
    }
}