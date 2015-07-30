package data.packages.UNICODE;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

public class UNICODE_DirectoryDisplayer 
{
	//attributes
	//establish custom font
	private UNICODE_CustomFont customFont = null;
	//establish stabilizing vars - since impulse field (its destination apnel) is always moving.
	private int fontAdjsutmentX, fontAdjsutmentY;
	//establish font requirements
	private String fontName = "";
	private float fontSize = 0.0f;
	//establish font orientation
	private int xCoord, yCoord;
	//establish impulse field instance (we'll take scroll rate and field stream from field)
	private UNICODE_IMPULSE_FIELD impulseField = null;
	//establish directory display component
	private UNICODE_MessageBoxWindow directoryDisplayComponent = null;
	//establish file manager
	private UNICODE_ConfigurationManager fileManager = null;
	//establish conveneince pack
	private UNICODE_ConveniencePack conveniencePack = new UNICODE_ConveniencePack ( );
	//establish root folder name
	private String rootFolderName = "";
	//establish colour vars
	private Color backgroundColour = null, foregroundColour = null;
	//establish y placement helper
	private int yPlacementHelper = 0;
	
	//establish visual delay requirements
	public Timer visualDelayTimer = new Timer ( 1, new visualDelayListening ( ) );
	private double visualDelayCount = 0, visualDelayLimit = 0;
	
	
	//constructor
	public UNICODE_DirectoryDisplayer ( UNICODE_CustomFont _customFont, UNICODE_ConfigurationManager _fileManager, int _xCoord, int _yCoord, String _fontName, float _fontSize, UNICODE_IMPULSE_FIELD _impulseField, String _rootFolderName, Color _backgroundColour, Color _foregroundColour, int _yPlacementHelper, int _visualDelayLimit )
	{
		//establish y placement helper
		yPlacementHelper = _yPlacementHelper;
		//establish root folder name
		rootFolderName = _rootFolderName;
		//establish file manager
		fileManager = _fileManager;
		//establish impulse field
		impulseField = _impulseField;
		//establish font orientation
		xCoord = _xCoord;
		yCoord = _yCoord;
		//establish font
		customFont = _customFont;
		//establsih font properties
		fontName = _fontName;
		fontSize = _fontSize;
		//establish colours
		backgroundColour = _backgroundColour;
		foregroundColour = _foregroundColour;
        //initialise directoryDisplayComponent
        directoryDisplayComponent = new UNICODE_MessageBoxWindow ( 0.7f, backgroundColour, Color.black, backgroundColour, foregroundColour, impulseField.getFieldStream ( ).replace ( "C:", rootFolderName ), getComputedImpulseFieldStreamWidth ( ), 22, false );
		//establish visual delay requirements
		visualDelayLimit = _visualDelayLimit;
		visualDelayTimer.start ( );
		directoryDisplayComponent.setVisible ( false ); //hide on startup
	}
	
	//methods
	//initial way - physically draw font in open space on field. The later way involved drawing this on a customizable uni-code frame instead.
	public void draw ( Graphics graphics, Color fontColour )
	{
		graphics.setColor ( fontColour );
		customFont.write ( graphics, impulseField.getFieldStream ( ), xCoord, yCoord, fontSize, fontName );
	}
	
	public void show ( boolean widthMutaionEnquiry, int newWidth )
	{
		directoryDisplayComponent.setVisible ( true );
		//show the directoryDisplayComponent
		int directoryDisplayComponentX = 0;
		int directoryDisplayComponentY = ( int ) fileManager.getBufferDimensionFromFile ( ).getHeight ( ) - ( yPlacementHelper ); //80 represents the size of the buttonDock0
		
		if ( widthMutaionEnquiry )
		{
			directoryDisplayComponentX = ( ( ( int ) fileManager.getBufferDimensionFromFile ( ).getWidth ( ) / 2 ) - ( newWidth / 2 ) ) + 12;
			directoryDisplayComponent.setBounds ( directoryDisplayComponentX, directoryDisplayComponentY, newWidth, directoryDisplayComponent.getHeight ( ) );
		}
		else
		{
			directoryDisplayComponentX = ( ( ( int ) fileManager.getBufferDimensionFromFile ( ).getWidth ( ) / 2 ) - ( directoryDisplayComponent.getWidth ( ) / 2 ) ) + 12;
			directoryDisplayComponent.setBounds ( directoryDisplayComponentX, directoryDisplayComponentY, directoryDisplayComponent.getWidth ( ), directoryDisplayComponent.getHeight ( ) );
		}
		directoryDisplayComponent.repaint ( );
	}
	
	
	public void hide ( )
	{
        //hide directoryDisplayComponent on startup
        directoryDisplayComponent.setBounds ( -1000, -1000, directoryDisplayComponent.getWidth ( ), directoryDisplayComponent.getHeight ( ) );
		directoryDisplayComponent.repaint ( );
	}
	
	public void provideRightwardStabilization ( )
	{
		xCoord += impulseField.getScrollRate ( );
	}
	
	public void provideLeftwardStabilization ( )
	{
		xCoord -= impulseField.getScrollRate ( );
	}
	
	public int getComputedImpulseFieldStreamWidth ( )
	{
		return ( int ) conveniencePack.getDisplayWidthFromString ( impulseField.getFieldStream ( ), ( int ) fontSize + 14 );
	}
	
	public void update ( )
	{
		directoryDisplayComponent.getPanel ( ).setLabelText ( impulseField.getFieldStream ( ).replace ( "C:", rootFolderName ) );
		show ( true, getComputedImpulseFieldStreamWidth ( ) );
	}
	
	
	//visual delay shit
	private class visualDelayListening implements ActionListener
	{
		public void actionPerformed ( ActionEvent actionEvent )
		{
			if ( visualDelayCount < visualDelayLimit )
				visualDelayCount ++;
			else
			{
				update ( ); //show directory displayer when visual display limit is reached.
				visualDelayTimer.stop ( );
			}
		}
	}
	
	public String getRootFolderName ( )
	{
		return rootFolderName;
	}
}
