package data.packages.UNICODE; 
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.awt.GraphicsDevice.WindowTranslucency;
import java.awt.geom.Ellipse2D;
import java.awt.Shape;

public class UNICODE_DonutMenuWindow extends JFrame
{
    //atributes
    public UNICODE_DonutShape shape = null;
	
    //constructor
    public UNICODE_DonutMenuWindow ( UNICODE_MenuPanel menuPanel, double frameX, double frameY, double frameWidth, double frameHeight, double holeWidth, double holeHeight, float frameOpacity )
    {
        //establish donut shape
        shape = new UNICODE_DonutShape ( 0, 0, frameWidth, frameHeight, holeWidth, holeHeight );
        
        //establish Jframe stuff
        setDefaultLookAndFeelDecorated ( true ); /*translucency requirement*/
        
        //remove frame from window
        setUndecorated ( true );
        
        //add gui panel
        add ( menuPanel );
		
		//set menu panel true
		menuPanel.setVisible ( true );
		
		//establish this window as the menu panels attribute
		menuPanel.setDonutMenuFrame ( this );
        
        //corectly display all panel components by passing panel to set content pane
        setContentPane ( menuPanel ); /*translucency requirement*/
                
        //set layout 
        setLayout ( new BoxLayout ( getContentPane ( ), BoxLayout.X_AXIS ) ) ;
        
        //i have to double fucking remove the border from the frame which i don't want, since the intial frame cancels this out or something.
        getRootPane ( ).setWindowDecorationStyle ( 0 );
        
        //set application window dimension
        //setSize ( new Dimension ( panel.getWidth ( ), panel.getHeight ( ) ) );
        setSize ( new Dimension ( ( int ) frameWidth, ( int ) frameHeight ) );
        
        //establish location
        setLocationRelativeTo ( null );
        
        //set frame shape
        setShape ( shape.getBody ( ) );
            
        //set opacity
        setOpacity ( frameOpacity );
        
        //show the frame
        setVisible ( true );
    }
	
	public void kill ( )
	{
		setVisible ( false );
	}
	
	public Shape getBody ( )
	{
		return shape.getBody ( );
	}
}
