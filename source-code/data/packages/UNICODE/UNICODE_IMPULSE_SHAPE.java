package data.packages.UNICODE;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.Graphics2D;
import java.awt.Color;

public class UNICODE_IMPULSE_SHAPE 
{
    //attributes
    private Shape impulseShape = null;
    //runtime transformation requirements
    private AffineTransform oldAffineTransformation;
    private boolean relocationEnquiry = false;
    private double xRelocationCoord, yRelocationCoord;
    private UNICODE_IMPULSE_BODY impulseBody = null;
	private Color defaultModeImpulseFolderColour = null, defaultModeImpulseFileColour = null;
    private String impulseEntityStream = null;
	
    //constructor    
    public UNICODE_IMPULSE_SHAPE ( String _impulseEntityStream, String impulseEntitySearchName, int x, int y, int width, int height, int impulseIndex, int folderIndicatorScaleFactor, Color _defaultModeImpulseFolderColour, Color _defaultModeImpulseFileColour )
    {
		//establish entity stream
		impulseEntityStream = _impulseEntityStream;
		
		//establish colour scheme
		defaultModeImpulseFolderColour = _defaultModeImpulseFolderColour;
		defaultModeImpulseFileColour = _defaultModeImpulseFileColour;
		
        impulseBody = new UNICODE_IMPULSE_BODY ( impulseEntityStream, impulseEntitySearchName, x, y, width, height, impulseIndex, folderIndicatorScaleFactor );
        impulseShape = impulseBody.getBody ( );
    }
    
    //misc
    public void drawShape ( Graphics2D graphics2d )
    {
        //DRAW SHAPE, WHILE CONTROLLING WHICH SHAPE MAY BE RELOCATED @ RUNTIME.
            //establish saved affine transformation
            establishOldAffineTransform ( graphics2d );
			  
			//set impulse node colour
			if ( impulseBody.representsDirectory ( impulseEntityStream ) )
				graphics2d.setColor ( defaultModeImpulseFolderColour );
			else 
				graphics2d.setColor ( defaultModeImpulseFileColour );
			
            //translate shape
            if ( relocationEnquiry )
                graphics2d.translate ( xRelocationCoord, yRelocationCoord );
            //draw impulse
            graphics2d.fill ( impulseShape );
            ////////////////////////////////////////////////////////////////////////////////////////////////////////
            //RESET PAINT COMPONENT GRAPHICS2D TRANSFORM, TO PREVENT TRANSLATION of non-selected buttons, or other 
            //objects and components currently in the graphics2d context
            ////////////////////////////////////////////////////////////////////////////////////////////////////////
            //reset stransformation so that other components are unaffected
            restoreOldTransform ( graphics2d );   
    }
    
    public Shape getShape ( )
    {
        return impulseShape;
    }
    
    public UNICODE_IMPULSE_BODY getBody ( )
    {
        return impulseBody;
    }
    public boolean getRelocationEnquiry ( )
    {
        return relocationEnquiry;
    }
    
    public void setRelocationEnquiry ( boolean value )
    {
        relocationEnquiry = value;
    }
    
    public void setRelocationOrientation ( double xValue, double yValue )
    {
        xRelocationCoord = xValue;
        yRelocationCoord = yValue;
    }
    
    public String getRelocationString ( )
    {
        return "" + xRelocationCoord + "," + yRelocationCoord;
    }
    
    //re-location after startup related accessors and mutators
        public AffineTransform getOldAffineTransform ( )
        {
            return oldAffineTransformation;
        }
        //establish old affine transform ( used in draw function )
        public void establishOldAffineTransform ( Graphics2D graphics2d )
        {
            oldAffineTransformation = graphics2d.getTransform ( );
        }
        //restore old transform so as to not affect other un-selected buttons or 
        //graphics 2d component or object currently on screem
        //only selected items must be scaled, this ensures this.
        public void restoreOldTransform ( Graphics2D graphics2d )
        {
            graphics2d.setTransform ( getOldAffineTransform ( ) );
        }
        
}
