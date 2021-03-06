package data.packages.UNICODE;

//Author: Jordan Micah Bennett
import javax.swing.JPanel;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Point;
import javax.swing.JFrame;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import javax.swing.Timer;
import java.awt.Polygon;
import java.awt.AlphaComposite;

public class UNICODE_DynamicHiglighter
{
    //attributes
        //Mouse position tracking variables
        private Point heldMouseCords = null, draggedMouseCords = null;
        
        //Paint object geometry
            //Actual geometry that represents collection of physical strokes
            private ArrayList <Ellipse2D> strokes = new ArrayList <Ellipse2D> ( );
            //Stroke properties 
                //dimension
                private int strokeWidth = 12, strokeHeight = 12;
                //colour
                private Color strokeColour = new Color ( 245, 245, 245 ); 
                //after stroke cycle mechanism
                    //polygon representing stroke cycle
                        //actual geometry representing stroke cycle
                        private Polygon strokesPolygon = new Polygon ( );
                        //properties
                        private Color strokesPolygonColour = new Color ( 224, 224, 224 );
                    //used to determine whether primary stroke has been fulfilled.
                    private boolean strokeCycleCompletionEnquiry = false, impulseContactEnquiry = false;
                    //timer
                    private Timer strokeCycleTimer = new Timer ( 1, new strokeCycleListener ( ) );
                    //elapsed time tracker
                    private int strokeCycleElapsedTime = 0;
                    //cycle threshholds
                    private double strokeCycleMaximum = 1;
					
				//establish action threads
				private Thread thread0 = null, thread1 = null, thread2 = null, thread3 = null;
				private UNICODE_MessageBoxWindow actionBox = null;
                    
                    
    public UNICODE_DynamicHiglighter ( int strokeWidth, int strokeHeight, Color strokeColour, Color strokesPolygonColour )
    {
        //establish stroke dimension
        this.strokeWidth = strokeWidth;
        this.strokeHeight = strokeHeight;
        
        //establish stroke colour scheme
            //stroke
            this.strokeColour = strokeColour;
            this.strokesPolygonColour = strokesPolygonColour;
    }
    
    
    //paint component
    public void draw ( Graphics graphics, Graphics2D graphics2d )
    {
        //set stroke colour
        graphics2d.setColor ( strokeColour );
            
        //draw strokes
        for ( Ellipse2D stroke : strokes )
            graphics2d.fill ( stroke );
            
        //if primary stroke cycle is complete, this implies that a shape has been generated wrt.
        //strokes made. Show this shape in that event.
        if ( strokeCycleCompletionEnquiry )
        {
            //the following two lines:
            //allows for the generation of translucent effects on the fly.
            //Attained via: http://www.java-gaming.org/index.php/topic,1546.
            System.setProperty ( "sun.java2d.translaccel", "true" );
            System.setProperty ( "sun.java2d.ddforcevram", "true" ); 
            graphics2d.setComposite ( AlphaComposite.getInstance ( AlphaComposite.SRC_OVER, 0.4f ) ); //set alpha composite of descriptor 
            graphics2d.setColor ( strokesPolygonColour );
            graphics2d.fill ( strokesPolygon );
            graphics2d.setComposite ( AlphaComposite.getInstance ( AlphaComposite.SRC_OVER, 1.0f ) ); //set alpha composite of descriptor 
        }
    }
    
    
    public void establishMouseDraggedEvent ( MouseEvent mouseEvent, int scrollAmountTracker )
    {
		if ( !getImpulseContactEnquiry ( ) )
		{
			//establish dragged mouse coordinates
			draggedMouseCords = mouseEvent.getLocationOnScreen ( );    
			int draggedMouseCoordX = ( int ) ( draggedMouseCords.getX ( ) - heldMouseCords.getX ( ) ), draggedMouseCoordY = ( int ) ( draggedMouseCords.getY ( ) - heldMouseCords.getY ( ) );

			//generate new stroke
			double updatedOriginX = getUpdatedOrigin ( draggedMouseCoordX, heldMouseCords.getX ( ), draggedMouseCoordY, heldMouseCords.getY ( ) ) [ 0 ] + scrollAmountTracker;
			double updatedOriginY = getUpdatedOrigin ( draggedMouseCoordX, heldMouseCords.getX ( ), draggedMouseCoordY, heldMouseCords.getY ( ) ) [ 1 ];
			
			strokes.add ( new Ellipse2D.Double ( updatedOriginX, updatedOriginY, strokeWidth, strokeHeight ) );
			strokesPolygon.addPoint ( ( int ) updatedOriginX, ( int ) updatedOriginY );
		}
    }
	
	public void setupThreads ( Thread [ ] threadArray )
	{
		//setup threads
		thread0 = threadArray [ 0 ];
		thread1 = threadArray [ 1 ];
		thread2 = threadArray [ 2 ];
		thread3 = threadArray [ 3 ];
	}
    
    public void establishMousePressedEvent ( MouseEvent mouseEvent )
    {
        //establish mouse pressed coords
		if ( !getImpulseContactEnquiry ( ) )
			heldMouseCords = mouseEvent.getPoint ( );
    }
    
    public void establishMouseReleasedEvent ( Thread [ ] threadArray )
    {
		setupThreads ( threadArray ); //renew thread pool to avert thread re-use error, @ each released event.
		
		if ( !getImpulseContactEnquiry ( ) )
			strokeCycleTimer.start ( );
    }
	
    
    //The origin will change when the mouse is one more toggled.
    //this ensures that we achieve a respactable approximation of where 
    //the trokes should begin occuring.
    public double [ ] getUpdatedOrigin ( double dX, double hX, double dY, double hY )
    {
        double [ ] value = new double [ 2 ];
        value [ 0 ] = hX + ( dX - 170 );
        value [ 1 ] = hY + ( dY - 40 );
        return value;
    }
    
    
    private class strokeCycleListener implements ActionListener 
    {
        public void actionPerformed ( ActionEvent actionEvent )
        {
			if ( !getImpulseContactEnquiry ( ) )
			{
				strokeCycleElapsedTime += 1;
				strokeCycleWatch ( );
			}
        }
    }
    public void strokeCycleWatch ( )
    {
        if ( strokeCycleElapsedTime > strokeCycleMaximum )
        {
            strokeCycleCompletionEnquiry = true;
            strokeCycleTimer.stop ( );
            strokeCycleElapsedTime = 0;
			actionBox = new UNICODE_MessageBoxWindow ( thread0, thread1, thread2, thread3, "~delete, cut or copy?~", 0.9f,  new Color ( 0, 0, 0 ), Color.black, Color.gray, Color.white, true, "data/images/all/message box/dynamic file manipulation/","rr", 10, 10, 12 );
        }
    }
    
    public void reset ( )
    {
        strokeCycleCompletionEnquiry = false;
        strokesPolygon.reset ( );
        strokes.clear ( );
    }
    
    public Polygon getStrokeCycleBody ( )
    {
        return strokesPolygon;
    }
    
    public boolean getStrokeEntailPointEnquiry ( Point point )
    {
        return strokesPolygon.contains ( point );
    }
	
	public boolean getImpulseContactEnquiry ( )
	{
		return impulseContactEnquiry;
	}
    
	public void setImpulseContactEnquiry ( boolean value )
	{
		impulseContactEnquiry = value;
	}
	
    public boolean getStrokeEntailPointEnquiry ( int x, int y )
    {
        return strokesPolygon.contains ( new Point ( x, y ) );
    }
	
	public UNICODE_MessageBoxWindow getActionBox ( )
	{
		return actionBox;
	}
	
	public void toggleContactEnquiryStateAtMouseRelease ( MouseEvent mEvent, int maxImpulses, ArrayList <UNICODE_IMPULSE_SHAPE> impulses, ArrayList <UNICODE_IMPULSE_DESCRIPTOR> impulseDescriptors )
	{
		for ( int i = 0; i < maxImpulses; i ++ )
		{
			UNICODE_IMPULSE_SHAPE impulseShape = impulses.get ( i );
			UNICODE_IMPULSE_BODY impulseBody = impulseShape.getBody ( );  
			UNICODE_IMPULSE_DESCRIPTOR descriptorShapes = impulseDescriptors.get ( i );
			
			if ( ( impulseShape.getShape ( ).contains ( mEvent.getX ( ), mEvent.getY ( ) ) ) || ( descriptorShapes.getShape ( ).contains ( mEvent.getX ( ), mEvent.getY ( ) ) ) )
				setImpulseContactEnquiry ( true );
		}	
	}
	
	public void toggleContactEnquiryStateAtMousePress ( MouseEvent mEvent, int maxImpulses, ArrayList <UNICODE_IMPULSE_SHAPE> impulses, ArrayList <UNICODE_IMPULSE_DESCRIPTOR> impulseDescriptors )
	{
		for ( int i = 0; i < maxImpulses; i ++ )
		{
			UNICODE_IMPULSE_SHAPE impulseShape = impulses.get ( i );
			UNICODE_IMPULSE_BODY impulseBody = impulseShape.getBody ( );  
			UNICODE_IMPULSE_DESCRIPTOR descriptorShapes = impulseDescriptors.get ( i );
			
			if ( ( !impulseShape.getShape ( ).contains ( mEvent.getX ( ), mEvent.getY ( ) ) ) || ( !descriptorShapes.getShape ( ).contains ( mEvent.getX ( ), mEvent.getY ( ) ) ) )
				setImpulseContactEnquiry ( false );
		}	
	}
}
