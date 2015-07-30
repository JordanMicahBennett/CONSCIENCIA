package data.packages.UNICODE; //Author(s): Jordan Micah Bennett //Author(s): Jordan Micah Bennett
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Toolkit;
import java.awt.FontMetrics;
import java.awt.geom.Rectangle2D;
import java.awt.Shape;
import java.awt.Rectangle;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import java.awt.Point;
import javax.swing.JPanel;

public class UNICODE_ConsoleField
{
	//attributes
	private int currentLineIndex = 0;
	private ArrayList <UNICODE_ConsoleFieldLine> consoleFieldLines = new ArrayList <UNICODE_ConsoleFieldLine> ( );
	
	//global line attribs all subsequent console lines will inherit these attributes, and or a variation.
	private int xStart = 0, yStart = 0, lineSpacingAmount = 0, paddingBetweenLabelAndDescription = 0;
	private String label, pointerSymbol;
	private boolean pointerDisplayEnquiry = false;
	
	//establish consoleInputField requirement custom font
	private UNICODE_CustomFont customFont = new UNICODE_CustomFont ( "data/fonts/" );
	
	private UNICODE_ConveniencePack conveniencePack = new UNICODE_ConveniencePack ( );
	
	private Thread additionalUpdatingThread = null;
	private int additionalUpdatingThreadKeyCode = 0;
		
	private JPanel eloquenceStructure0Panel = null;
	
	public UNICODE_ConsoleField ( int xStart, int yStart, int lineSpacingAmount, String label, String pointerSymbol, boolean pointerDisplayEnquiry, int paddingBetweenLabelAndDescription, JPanel eloquenceStructure0Panel )
	{
		this.xStart = xStart;
		this.yStart = yStart;
		this.lineSpacingAmount = lineSpacingAmount;
		this.label = label;
		this.pointerSymbol = pointerSymbol;
		this.pointerDisplayEnquiry = pointerDisplayEnquiry;
		this.paddingBetweenLabelAndDescription = paddingBetweenLabelAndDescription;
		this.eloquenceStructure0Panel = eloquenceStructure0Panel;
		initiate ( );
	}
	
	
	//methods
		//accessors
		public int getCurrentLineIndex ( )
		{
			return currentLineIndex;
		}
		public int getXStart ( )
		{
			return xStart;
		}
		public int getYStart ( )
		{
			return yStart;
		}
		public int getLineSpacingAmount ( )
		{
			return consoleFieldLines.get ( getCurrentLineIndex ( ) - 1 ).getY ( ) + lineSpacingAmount;
		}
		public Point getPointerLocation ( )
		{
			return new Point ( ( int ) consoleFieldLines.get ( getCurrentLineIndex ( ) ).getX ( ), ( int ) consoleFieldLines.get ( getCurrentLineIndex ( ) ).getY ( ) );
		}
		public String getLabel ( )
		{
			return label;
		}
		public boolean getPointerDisplayEnquiry ( )
		{
			return pointerDisplayEnquiry;
		}	
		public String getPointerSymbol ( )
		{
			return pointerSymbol;
		}
		public UNICODE_CustomFont getCustomFont ( )
		{
			return customFont;
		}
		public int getPaddingBetweenLabelAndDescription ( )
		{
			return paddingBetweenLabelAndDescription;
		}
		public Thread getAdditionalUpdatingThread ( )
		{
			return additionalUpdatingThread;
		}
		
		public int getAdditionalUpdatingThreadKeyCode ( )
		{
			return additionalUpdatingThreadKeyCode;
		}
		public int getLineNumber ( )
		{
			return consoleFieldLines.size ( );
		}
		//mutators
		public void incCurrentLineIndex ( )
		{
			currentLineIndex ++;
		}
		public void decCurrentLineIndex ( )
		{
			currentLineIndex --;
		}
		public void setCurrentLineIndex ( int value )
		{
			currentLineIndex = value;
		}
		public void setAdditionalUpdatingThread ( Thread value )
		{
			additionalUpdatingThread = value;
		}
		public void setAdditionalUpdatingThreadKeyCode ( int value )
		{
			additionalUpdatingThreadKeyCode = value;
		}
		
		//"grow" or "shrink" a bushman text field based on which key is toggled ( pressed )
		public void initiate ( )
		{
			consoleFieldLines.add ( new UNICODE_ConsoleFieldLine ( getXStart ( ), getYStart ( ), "0" + getLineNumber ( ) + getLabel ( ), true, getPointerDisplayEnquiry ( ), getPaddingBetweenLabelAndDescription ( ) ) );
		}
		
		
		public void allowUpdating ( KeyEvent kEvent )
		{
			//allow all console fields to be updated, given that they are enabled
			consoleFieldLines.get ( getCurrentLineIndex ( ) ).allowUpdating ( kEvent );
			
			//disable previous line, then create new one.
			if ( kEvent.getKeyCode ( ) == 40 ) 
			{
				consoleFieldLines.get ( getCurrentLineIndex ( ) ).setFocus ( false );
				addLine ( );
			}
			
			//customized updating thread
			if ( kEvent.getKeyCode ( ) == additionalUpdatingThreadKeyCode ) 
				additionalUpdatingThread.start ( );
		}
		
		
		public void draw ( Graphics graphics, Graphics2D graphics2d, String fontName, int fontSize, Color fontColour )
		{
			for ( int i = 0; i < consoleFieldLines.size ( ); i ++ )
				consoleFieldLines.get ( i ).draw ( graphics, graphics2d, getCustomFont ( ), consoleFieldLines.get ( i ).getX ( ), consoleFieldLines.get ( i ).getY ( ), fontName, fontSize, fontColour );
		
			//draw pointer
			int pointerX = getXStart ( ) +
			( int ) conveniencePack.getDisplayWidthFromString ( consoleFieldLines.get ( getCurrentLineIndex ( ) ).getText ( ) , fontSize ) + 
			( int ) conveniencePack.getDisplayWidthFromString ( consoleFieldLines.get ( getCurrentLineIndex ( ) ).getLabel ( ) , fontSize );
			
			if ( getPointerDisplayEnquiry ( ) )
			{
				graphics.setColor ( fontColour );
				getCustomFont ( ).write ( graphics, getPointerSymbol ( ), pointerX, consoleFieldLines.get ( getCurrentLineIndex ( ) ).getY ( ) + 4, fontSize, fontName );
			}
		}
		
		//internal
			public void addLine ( )
			{
				incCurrentLineIndex ( );
				consoleFieldLines.add ( new UNICODE_ConsoleFieldLine ( getXStart ( ), getLineSpacingAmount ( ), "" + getLineNumber ( ) + getLabel ( ), true, getPointerDisplayEnquiry ( ), getPaddingBetweenLabelAndDescription ( ) ) );
			}
		
		//external
			public void addLine ( String customLabel, Thread renewedAdditionalUpdatingThread )
			{
				additionalUpdatingThread = renewedAdditionalUpdatingThread;
				incCurrentLineIndex ( );
				consoleFieldLines.add ( new UNICODE_ConsoleFieldLine ( getXStart ( ), getLineSpacingAmount ( ), "" + getLineNumber ( ) + customLabel, true, getPointerDisplayEnquiry ( ), getPaddingBetweenLabelAndDescription ( ) ) );						
			}
			
			public void addLine ( String customLabel, String customDescription, Thread renewedAdditionalUpdatingThread )
			{
				additionalUpdatingThread = renewedAdditionalUpdatingThread;
				incCurrentLineIndex ( );
				consoleFieldLines.add ( new UNICODE_ConsoleFieldLine ( getXStart ( ), getLineSpacingAmount ( ), "" + getLineNumber ( ) + customLabel, true, getPointerDisplayEnquiry ( ), getPaddingBetweenLabelAndDescription ( ) ) );
				consoleFieldLines.get ( getCurrentLineIndex ( ) ).setText ( customDescription );
			}
			public void scrollField ( int rotationDirection, int scrollRate )
			{
				for ( int i = 0; i < consoleFieldLines.size ( ); i ++ )
				{
					//scroll upwards
					if ( rotationDirection < 0 )
					{
						consoleFieldLines.get ( i ).setY ( consoleFieldLines.get ( i ).getY ( ) - scrollRate );	
						eloquenceStructure0Panel.setLocation ( eloquenceStructure0Panel.getX ( ), ( int ) ( eloquenceStructure0Panel.getY ( ) - scrollRate ) );
					}
					//scroll downwards						
					if ( rotationDirection > 0 )
					{
						consoleFieldLines.get ( i ).setY ( consoleFieldLines.get ( i ).getY ( ) + scrollRate );		
						eloquenceStructure0Panel.setLocation ( eloquenceStructure0Panel.getX ( ), ( int ) ( eloquenceStructure0Panel.getY ( ) + scrollRate ) );
					}
				}		
			}
			public String getLineText ( )
			{
				return consoleFieldLines.get ( getCurrentLineIndex ( ) ).getText ( );
			}
}