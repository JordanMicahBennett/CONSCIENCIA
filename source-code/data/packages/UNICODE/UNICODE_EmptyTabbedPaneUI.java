package data.packages.UNICODE;

import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;
import java.awt.Graphics;
import java.awt.Color;

public class UNICODE_EmptyTabbedPaneUI extends BasicTabbedPaneUI
{
	private class ScrollableTabPanel extends JPanel implements UIResource 
	{
		public ScrollableTabPanel ( ) 
		{
			setLayout ( null );
			setBackground ( Color.red );
		}
		
		public void paintComponent ( Graphics graphics )
		{
			super.paintComponent ( graphics );

			graphics.setColor ( Color.lightGray );
			graphics.fillRect ( ( int ) tabPane.getBounds ( ).getX ( ), ( int ) tabPane.getBounds ( ).getY ( ), ( int ) tabPane.getBounds ( ).getWidth ( ), ( int ) tabPane.getBounds ( ).getHeight ( ) ); 
		}
	}

}