package data.packages.UNICODE;
import java.awt.geom.Line2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Rectangle;

public class UNICODE_IMPULSE_CONNECTOR
{
    //attributes
    private UNICODE_GlowingLine impulseConnector = null;
    //runtime transformation requirements
    private AffineTransform oldAffineTransformation;
    //establish impulse shape
    private ArrayList <UNICODE_IMPULSE_SHAPE> impulseShapes = null;
	//establish description view controler answer
	private String descriptionViewControllerAnswer = "";
	//establish colour scheme
	private Color connectorColour = null, connectorGlowColour = null;
	//glow properties
	private int connectorThickness = 0, connectorGlowThickness = 0, connectorGlowQualityMultiplier = 0, connectorGlowBrightness = 0;
	//UPDATE: dotted line rendering
	private UNICODE_LineRenderer lineRenderer = null;
	
	
    //constructor
    public UNICODE_IMPULSE_CONNECTOR ( int x1, int y1, int x2, int y2, ArrayList <UNICODE_IMPULSE_SHAPE> _impulseShapes, String _descriptionViewControllerAnswer, Color _connectorColour, Color _connectorGlowColour, int _connectorThickness, int _connectorGlowThickness, int _connectorGlowQualityMultiplier, int _connectorGlowBrightness )
    {
		//establish description view controller answer
		descriptionViewControllerAnswer = _descriptionViewControllerAnswer;
		
		//establish colour scheme
		connectorColour = _connectorColour;
		connectorGlowColour = _connectorGlowColour;
		
		//establish glow properties
		connectorThickness = _connectorThickness; 
		connectorGlowThickness = _connectorGlowThickness;
		connectorGlowQualityMultiplier = _connectorGlowQualityMultiplier;
		connectorGlowBrightness = _connectorGlowBrightness;
	
        //establish impulse shape
        impulseShapes = _impulseShapes;
        
        //establish line 2, 10, 2, 0,
        impulseConnector = new UNICODE_GlowingLine ( connectorThickness, connectorGlowThickness, connectorGlowQualityMultiplier, connectorGlowBrightness, connectorColour, connectorGlowColour.getRed ( ), connectorGlowColour.getGreen ( ), connectorGlowColour.getBlue ( ) );
    
		//establish connector
		lineRenderer = new UNICODE_LineRenderer ( );
	}
    
    public UNICODE_GlowingLine getBody ( )
    {
        return impulseConnector;
    }
    
    public void drawLine ( Graphics graphics, Graphics2D graphics2d, int impulseShapesIndex, double dotWidth, double dotSpace, Rectangle impulseCameraFrame )
    {
        //DRAW SHAPE, WHILE CONTROLLING WHICH SHAPE MAY BE RELOCATED @ RUNTIME.
            //establish saved affine transformation
            // establishOldAffineTransform ( graphics2d );
            //translate line
            // if ( impulseShapes.get ( impulseShapesIndex ).getRelocationEnquiry ( ) )
            // {
//                 if ( ( impulseShapesIndex + 1 ) < maxImpulses )
//                 {
                    double x1 = impulseShapes.get ( impulseShapesIndex ).getBody ( ).getBody ( ).getX ( );
                    double x2 = impulseShapes.get ( impulseShapesIndex + 1 ).getBody ( ).getBody ( ).getX ( );
                    double y1 = impulseShapes.get ( impulseShapesIndex ).getBody ( ).getBody ( ).getY ( ) + impulseShapes.get ( impulseShapesIndex ).getBody ( ).getBody ( ).getHeight ( ) / 2;
                    double y2 = impulseShapes.get ( impulseShapesIndex + 1 ).getBody ( ).getBody ( ).getY ( ) + impulseShapes.get ( impulseShapesIndex ).getBody ( ).getBody ( ).getHeight ( ) / 2;
                    
                    //impulseConnector.setOrientation ( ( int ) x1, ( int ) y1, ( int ) x2, ( int ) y2 );
					

//                 }
            // }
                
            //draw impulse connector
			//impulseConnector.draw ( graphics );
            lineRenderer.drawDottedLine ( graphics, ( int ) x1, ( int ) y1, ( int ) x2, ( int ) y2, dotWidth, dotSpace, connectorColour, impulseCameraFrame );
            ////////////////////////////////////////////////////////////////////////////////////////////////////////
            //RESET PAINT COMPONENT GRAPHICS2D TRANSFORM, TO PREVENT TRANSLATION of non-selected buttons, or other 
            //objects and components currently in the graphics2d context
            ////////////////////////////////////////////////////////////////////////////////////////////////////////
            //reset stransformation so that other components are unaffected
            //restoreOldTransform ( graphics2d );   
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
