package data.packages.UNICODE;
import java.awt.geom.Ellipse2D;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.PointerInfo;
import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Point;
import javax.swing.JPanel;
import java.util.ArrayList;

public class UNICODE_ParameterBubble
{
	//attributes
	private Ellipse2D parameterBubble = null;
	private UNICODE_ConveniencePack conveniencePack = null;
	
	//properties
	private int initialSpan = 0;
	
	//field scrolling requriements
	private UNICODE_IMPULSE_FIELD impulseField = null;
	private int scrollLowerLimit = 0, scrollUpperPadding = 0;
	private int bufferWidth = 0;
	
	private boolean parameterBubbleEnableEnquiry = false;
	//constructor
	public UNICODE_ParameterBubble ( int initialSpan, int scrollLowerLimit, int scrollUpperPadding, int bufferWidth, UNICODE_IMPULSE_FIELD impulseField )
	{
		this.initialSpan = initialSpan;
		
		//establish field scroling requirments
		this.scrollLowerLimit = scrollLowerLimit;
		this.scrollUpperPadding = scrollUpperPadding;
		this.bufferWidth = bufferWidth;
		this.impulseField = impulseField;
		
		conveniencePack = new UNICODE_ConveniencePack ( );
		parameterBubble = new Ellipse2D.Double ( ( int ) getMouseLocation ( ).getX ( ), ( int ) getMouseLocation ( ).getY ( ), initialSpan, initialSpan );
	}
	
	//methods
		//accessors
		public boolean getParameterBubbleEnableEnquiry ( )
		{
			return parameterBubbleEnableEnquiry;
		}
		public Point getMouseLocation ( )
		{
			Point returnValue = null;
			
			PointerInfo pointerInformation = MouseInfo.getPointerInfo ( );
			
			returnValue = pointerInformation.getLocation ( );
			
			return returnValue;
		}
		
		//mutators
		public void setParameterBubbleEnableEnquiry ( boolean value )
		{
			parameterBubbleEnableEnquiry = value;
		}

		public void updateParameterBubbleSize ( int rotationDirection, int bubbleAlterationRate )
		{
			int postiveSpan = ( int ) ( parameterBubble.getWidth ( ) + bubbleAlterationRate );
			int negativeSpan = ( int ) ( parameterBubble.getWidth ( ) - bubbleAlterationRate );
			
			//increment size
			if ( rotationDirection < 0 ) 
				parameterBubble = new Ellipse2D.Double ( ( int ) parameterBubble.getX ( ), ( int ) parameterBubble.getY ( ), postiveSpan, postiveSpan );
			//decrement size
			else if ( rotationDirection > 0 ) 
				parameterBubble = new Ellipse2D.Double ( ( int ) parameterBubble.getX ( ), ( int ) parameterBubble.getY ( ), negativeSpan, negativeSpan );
		}
		
		public void allowImpulseFieldSliding ( MouseEvent mouseEvent )
		{
			if ( parameterBubbleEnableEnquiry )
			{
				//if parameter bubble is being moved rightwards.
				if ( mouseEvent.getX ( ) > ( bufferWidth - scrollUpperPadding ) )
					impulseField.slide ( "rightwards" );
				else if ( mouseEvent.getX ( ) < scrollLowerLimit )
					impulseField.slide ( "leftwards" );
			}
		}
		
		public void enableMoseMovementResponse ( MouseEvent mouseEvent )
		{
			allowImpulseFieldSliding ( mouseEvent );
		}
		
		public void updateParameterBubbleLocation ( MouseEvent mouseEvent )
		{
			parameterBubble = new Ellipse2D.Double ( ( int ) mouseEvent.getX ( ), ( int ) mouseEvent.getY ( ), ( int ) parameterBubble.getWidth ( ), ( int ) parameterBubble.getHeight ( ) );
		}
	
		//other
		public void draw ( Graphics2D graphics2d, Color colour )
		{
			if ( getParameterBubbleEnableEnquiry ( ) )
			{
				graphics2d.setColor ( colour );
				graphics2d.draw ( parameterBubble );
			}
		}
		
		public void toggleParameterBubbleEnableEnquiry ( JPanel context )
		{
			if ( getParameterBubbleEnableEnquiry ( ) )
			{
				setParameterBubbleEnableEnquiry ( false );
				conveniencePack.restoreCursor ( context );
			}
			else
			{
				setParameterBubbleEnableEnquiry ( true );
				conveniencePack.hideCursor ( context );
			}
		}
}