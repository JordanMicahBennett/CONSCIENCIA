package data.packages.UNICODE;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Shape;
import java.awt.Rectangle;
import javax.swing.JPanel;
import java.awt.AlphaComposite;

public class UNICODE_IMPULSE_FIELD_CAMERA
{
	//attributes
	private Rectangle body = null, tempBody = null;
	private int xOrigin = 0;
	private int prebakedLowerBoundLimitForY = 0, prebakedUpperBoundLimitForY = 0;
	private int width = 0, height = 0;
	private int rightScrollTrackerForBody = 0, leftScrollTrackerForBody = 0;
	private int screenWidth = ( int ) java.awt.Toolkit.getDefaultToolkit ( ).getScreenSize ( ).getWidth ( );
	private int screenHeight = ( int ) java.awt.Toolkit.getDefaultToolkit ( ).getScreenSize ( ).getHeight ( );
	private String cameraDisplayAnswer = "";
	
    //constructor
    public UNICODE_IMPULSE_FIELD_CAMERA ( int originAndEndSpace, int _prebakedLowerBoundLimitForY, int _prebakedUpperBoundLimitForY, UNICODE_ConfigurationManager configurationManager, String _cameraDisplayAnswer )
	{
		//compute orientation
		xOrigin = 0 + originAndEndSpace;
		
		//establish camera display answer
		cameraDisplayAnswer = _cameraDisplayAnswer;
		
		//compute dimension
		prebakedLowerBoundLimitForY = _prebakedLowerBoundLimitForY;
		prebakedUpperBoundLimitForY = _prebakedUpperBoundLimitForY;
		
		width = ( int ) configurationManager.getBufferDimensionFromFile ( ).getWidth ( ) - originAndEndSpace * 2;
		height = prebakedLowerBoundLimitForY + ( prebakedUpperBoundLimitForY / 2 );
		
		//establish camara bdy
			//body shifts to capture newly possible impulses;
			body = new Rectangle ( xOrigin, prebakedUpperBoundLimitForY, width, height );
	}
	
	//methods
		//accessors
		public Rectangle getBody ( )
		{	
			return body;
		}
		
		//functioons
		public void enableImpulseComponentRendering ( Graphics graphics, Graphics2D graphics2d, UNICODE_IMPULSE_FIELD impulseField, int maxImpulses, boolean lineVisibilityEnquiry, double lineRendererEntityLength, double lineRendererEntitySpatialDistance, UNICODE_DescriptionViewController descriptionViewController, ArrayList <UNICODE_IMPULSE_SHAPE> impulses, ArrayList <UNICODE_IMPULSE_DESCRIPTOR> impulseDescriptors, ArrayList <UNICODE_IMPULSE_CONNECTOR> impulseConnectors, int rotationDirection, int scrollAmountTracker )
		{
			//establish camera update
			updateCamera ( rotationDirection, scrollAmountTracker );
		
			//control impulse node here go, descriptor rendering
			for ( int i = 0; i < maxImpulses; i ++ )
				if ( body.contains ( impulses.get ( i ).getBody ( ).getBody ( ).getX ( ), impulses.get ( i ).getBody ( ).getBody ( ).getY ( ) ) )
					if ( descriptionViewController.getValue ( ).equals ( "on" ) )
					{
						impulses.get ( i ).drawShape ( graphics2d );
						impulseDescriptors.get ( i ).drawImpulseDescriptor ( graphics, graphics2d, impulseField );
					}
					else
						impulses.get ( i ).drawShape ( graphics2d );
						
			//draw connectors ( lines )
            if ( lineVisibilityEnquiry )
			{
                for ( int i = 0; i < maxImpulses; i ++ )
                    if ( ( i + 1 ) < maxImpulses )	
						impulseConnectors.get ( i ).drawLine ( graphics, graphics2d, i, lineRendererEntityLength, lineRendererEntitySpatialDistance, body );
			}
		}
		
		public void draw ( Graphics2D graphics2d, Color cameraColourForBody )
		{
			if ( cameraDisplayAnswer.equals ( "on" ) )
			{
				graphics2d.setColor ( cameraColourForBody );
				graphics2d.draw ( body );
			}
		}
		
		public void updateCamera ( int rotationDirection, int scrollAmountTracker )
		{
			//leftward scrolling
			if ( rotationDirection > 0 )
			{
				leftScrollTrackerForBody = xOrigin + scrollAmountTracker;
				
				body.setBounds ( leftScrollTrackerForBody, prebakedUpperBoundLimitForY, width, height );
			}
			
			//rightward scrolling	
			else if ( rotationDirection < 0 )
			{
				rightScrollTrackerForBody = ( int ) body.getX ( ) + scrollAmountTracker;
				
				body.setBounds ( rightScrollTrackerForBody, prebakedUpperBoundLimitForY, width, height );
			}
		}
}