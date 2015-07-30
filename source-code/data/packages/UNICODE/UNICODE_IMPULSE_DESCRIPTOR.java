package data.packages.UNICODE; //Author(s): Jordan Micah Bennett
import java.awt.geom.Ellipse2D;
import java.awt.Shape;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.AlphaComposite;

public class UNICODE_IMPULSE_DESCRIPTOR
{
	//attributes
	private Shape impulseDescriptorShape = null;
	private Ellipse2D impulseDescriptorBody = null;
	private String impulseDescriptionText = "";
	private UNICODE_ConveniencePack conveniencePack = new UNICODE_ConveniencePack ( );	
	private Color descriptionModeImpulseFolderColour = null, descriptionModeImpulseFileColour = null;
	private Color descriptionModeFolderFontColour = null, descriptionModeFileFontColour = null;
	private double descriptorBodyWidth, descriptorBodyHeight, initialDescriptorBodyWidth, initialDescriptorBodyHeight;
	private int textWidth, textHeight;
	private UNICODE_IMPULSE_BODY impulseBodyClass = null;
	private String impulseEntityStream = null;
	private float descriptorOpacityLevel = 0.0f;
	
        //establish resizing requirements
            //establish scale
            private double dimensionAlterationRate = 1.2, originalDimensionAlterationRate = 1.2;
            //establish scale limits
            private double projectedWidth = 0.0;
			//impulse orientation and dimension
			double impulseX, impulseY, impulseWidth, impulseHeight;
			private boolean resizeEnquiry = false;
	//font
	private UNICODE_CustomFont customFont = null;
	private String fontName = "";
		
				
	//constructor
	public UNICODE_IMPULSE_DESCRIPTOR ( UNICODE_IMPULSE_BODY _impulseBodyClass, UNICODE_CustomFont _customFont, String _fontName, Color _descriptionModeImpulseFolderColour, Color _descriptionModeImpulseFileColour, Color _descriptionModeFolderFontColour, Color _descriptionModeFileFontColour, float _descriptorOpacityLevel, double _dimensionAlterationRate, double projectedWidthPercentageRate )
	{
		//intialise font stream
		customFont = _customFont;
		fontName = _fontName;
		
		//establish opacity level
		descriptorOpacityLevel = _descriptorOpacityLevel;
		
		//establish impulse body
		impulseBodyClass = _impulseBodyClass;
		
		//establishe enity stream
		impulseEntityStream = impulseBodyClass.getEntityStream ( );
		
		//establish impulse dimensions
		impulseX = impulseBodyClass.getBody ( ).getX ( );
		impulseY = impulseBodyClass.getBody ( ).getY ( );
		impulseWidth = impulseBodyClass.getBody ( ).getWidth ( );
		impulseHeight = impulseBodyClass.getBody ( ).getHeight ( );
	
		//establish impulse description text
		impulseDescriptionText = impulseBodyClass.getEntitySearchName ( );
		
		//compute text dimension based on text
		textWidth = ( int ) conveniencePack.getDisplayWidthFromString ( impulseDescriptionText, 16 );
		textHeight = ( int ) conveniencePack.getDisplayHeightFromString ( impulseDescriptionText, 16 );
		
		//establish body dimension
		descriptorBodyWidth = textWidth;
		descriptorBodyHeight = descriptorBodyWidth;
		//save dimensions
		initialDescriptorBodyWidth = descriptorBodyWidth;
		initialDescriptorBodyHeight = descriptorBodyHeight;
		
		//establish impulse descriptor shape - formulae to center a child component on parent, given that the child was already established, and rather adopted by a parent = cX - pW/2 + cW/2
		impulseDescriptorBody = new Ellipse2D.Double ( impulseX - descriptorBodyWidth/2 + impulseWidth/2, impulseY - descriptorBodyHeight/2 + impulseHeight/2, descriptorBodyWidth, descriptorBodyHeight );
		
		//establish impulse descriptor shape with respect to body
		impulseDescriptorShape = impulseDescriptorBody;
		
		//establish colours
		descriptionModeImpulseFolderColour = _descriptionModeImpulseFolderColour;
		descriptionModeImpulseFileColour = _descriptionModeImpulseFileColour;
		descriptionModeFolderFontColour = _descriptionModeFolderFontColour;
		descriptionModeFileFontColour = _descriptionModeFileFontColour;
		
		//supply dimension alteration rate
		dimensionAlterationRate = _dimensionAlterationRate;
			//projectedWidthPercentageRate - indicates the rate of increase, of how much the width should ultimately grow to.
		projectedWidth = descriptorBodyWidth + ( projectedWidthPercentageRate * descriptorBodyWidth ); //compute projected width per shape; a precursor value is used to produce a mutated, bigger width.
	}
	
	//accessors
	public Shape getShape ( )
	{
		return impulseDescriptorShape;
	}
	
	public Ellipse2D getBody ( )
	{
		return impulseDescriptorBody;
	}


	
	//accessors
		//resizing requirements
		public double getDimensionAlterationRate ( )
		{
			return dimensionAlterationRate;
		}
		public double getOriginalDimensionAlterationRate ( )
		{
			return originalDimensionAlterationRate;
		}
		public double getProjectedWidth ( ) 
		{
			return projectedWidth;
		}
		public boolean getResizeEnquiry ( )
		{
			return resizeEnquiry;
		}
	//mutators
		//dynamic mutators
			//resizing requirements
			public void incDimensionAlterationRate ( double value )
			{
				dimensionAlterationRate += value;
			}
			public void decDimensionAlterationRate ( double value )
			{
				dimensionAlterationRate -= value;
			}
 
		//static mutators
			//resizing requiremnts
			public void setDimensionAlterationRate ( double value )
			{
				dimensionAlterationRate = value;
			}
			public void setProjectedWidth ( double value ) 
			{
				projectedWidth = value;
			}
			public void resetDimensionAlterationRate ( )
			{
				dimensionAlterationRate = originalDimensionAlterationRate;
			}
			public void setResizeEnquiry ( boolean value )
			{
				resizeEnquiry = value;
			}
	
	public void drawImpulseDescriptor ( Graphics graphics, Graphics2D graphics2d, JPanel pane )
	{
		//establish shape
			//colour the shape
			if ( impulseBodyClass.representsDirectory ( impulseEntityStream ) )
				graphics2d.setColor ( descriptionModeImpulseFolderColour );
			else
				graphics2d.setColor ( descriptionModeImpulseFileColour );

			//the following two lines:
			//allows for the generation of translucent effects on the fly.
			//Attained via: http://www.java-gaming.org/index.php/topic,1546.
			System.setProperty ( "sun.java2d.translaccel", "true" );
			System.setProperty ( "sun.java2d.ddforcevram", "true" ); 
			
			graphics2d.setComposite ( AlphaComposite.getInstance ( AlphaComposite.SRC_OVER, descriptorOpacityLevel ) ); //set alpha composite of descriptor 
			
			graphics2d.fill ( impulseDescriptorBody ); //draw the shape

			graphics2d.setComposite ( AlphaComposite.getInstance ( AlphaComposite.SRC_OVER, 1.0f ) ); //reset alpha composite of descriptor, so after-occuring components are unscaved
			
		//establish description
			//colour the shape
			if ( impulseBodyClass.representsDirectory ( impulseEntityStream ) )
				graphics2d.setColor ( descriptionModeFolderFontColour );
			else
				graphics2d.setColor ( descriptionModeFileFontColour );
				
			//draw the description
			if ( resizeEnquiry )
			{
				customFont.write ( graphics, impulseDescriptionText, ( int ) ( impulseDescriptorBody.getX ( ) + ( ( descriptorBodyWidth + ( dimensionAlterationRate + ( textWidth / 2 ) ) ) / textHeight ) ), ( int ) ( impulseDescriptorBody.getY ( ) + ( ( descriptorBodyHeight + dimensionAlterationRate ) / 2 ) ), 12.0f, fontName );
				//graphics2d.drawString ( impulseDescriptionText, ( int ) ( impulseDescriptorBody.getX ( ) + ( ( descriptorBodyWidth + ( dimensionAlterationRate + ( textWidth / 2 ) ) ) / textHeight ) ), ( int ) ( impulseDescriptorBody.getY ( ) + ( ( descriptorBodyHeight + dimensionAlterationRate ) / 2 ) ) );
			}
			else	
			{
				customFont.write ( graphics, impulseDescriptionText, ( int ) ( impulseDescriptorBody.getX ( ) + ( descriptorBodyWidth / textHeight ) ), ( int ) ( impulseDescriptorBody.getY ( ) + ( descriptorBodyHeight / 2 ) ), 12.0f, fontName );
				//graphics2d.drawString ( impulseDescriptionText, ( int ) ( impulseDescriptorBody.getX ( ) + ( descriptorBodyWidth / textHeight ) ), ( int ) ( impulseDescriptorBody.getY ( ) + ( descriptorBodyHeight / 2 ) ) );
			}
	}
	
	public void mutateGeometricalDimension ( )
	{
		//establish impulse descriptor shape - formulae to center a child component on parent, given that the child was already established, and rather adopted by a parent = cX - pW/2 + cW/2
		impulseDescriptorBody = new Ellipse2D.Double ( ( impulseX - descriptorBodyWidth/2 + impulseWidth/2 ) - dimensionAlterationRate/2, ( impulseY - descriptorBodyHeight/2 + impulseHeight/2 ) - dimensionAlterationRate/2, descriptorBodyWidth + dimensionAlterationRate, descriptorBodyHeight + dimensionAlterationRate );
		
		//establish impulse descriptor shape with respect to body
		impulseDescriptorShape = impulseDescriptorBody;
	}
	
	public void resetGeometricalDimension ( )
	{
		//establish impulse descriptor shape - formulae to center a child component on parent, given that the child was already established, and rather adopted by a parent = cX - pW/2 + cW/2
		impulseDescriptorBody = new Ellipse2D.Double ( impulseX - descriptorBodyWidth/2 + impulseWidth/2, impulseY - descriptorBodyHeight/2 + impulseHeight/2, initialDescriptorBodyWidth, initialDescriptorBodyHeight );
		
		//establish impulse descriptor shape with respect to body
		impulseDescriptorShape = impulseDescriptorBody;
	}
	
	public void performGeometricalMutation ( )
	{
		incDimensionAlterationRate ( dimensionAlterationRate );	
		mutateGeometricalDimension ( );
	}	
	
	public void resetDescriptor ( )
	{
		resetGeometricalDimension ( );
		resetDimensionAlterationRate ( );
	}

}
