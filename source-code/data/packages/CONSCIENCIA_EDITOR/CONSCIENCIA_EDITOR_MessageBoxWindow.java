package data.packages.CONSCIENCIA_EDITOR; //Author(s): Jordan Micah Bennett
import javax.swing.JFrame;
import java.awt.GraphicsDevice.WindowTranslucency.*;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.geom.RoundRectangle2D;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JRootPane;

public class CONSCIENCIA_EDITOR_MessageBoxWindow extends JFrame
{
    //attributes
    private CONSCIENCIA_EDITOR_MessageBoxPanel panel = null;
    
    //TWO SIGNATURES; FRAME LOCATION SETTABLE/CENTERED BY DEFAULT
    public CONSCIENCIA_EDITOR_MessageBoxWindow ( Thread thread0, Thread thread1, float alphaLevel, int x, int y, Color backgroundColour, Color buttonOutlineColour, boolean roundedEnquiry, String buttonTextureDir )
    {
        //establish method panel
        panel = new CONSCIENCIA_EDITOR_MessageBoxPanel ( thread0, thread1, backgroundColour, buttonOutlineColour, this, buttonTextureDir );
        
        //establish Jframe stuff
        setDefaultLookAndFeelDecorated ( true ); /*translucency requirement*/
        
        //remove frame from window
        setUndecorated ( true );
        
        //add gui panel
        add ( panel );
		
        //corectly display all panel components by passing panel to set content pane
        setContentPane ( panel ); /*translucency requirement*/
		
		//set layout 
		setLayout ( new BoxLayout ( getContentPane ( ), BoxLayout.X_AXIS ) ) ;
		
		//i have to double fucking remove the border from the frame which i don't want, since the intial frame cancels this out or something.
		//i didn't know zero was valid as a psrameter till this god forsaken day.
		getRootPane ( ).setWindowDecorationStyle ( 0 );
		
		
        //set application window dimension
        setSize ( new Dimension ( panel.getWidth ( ), panel.getHeight ( ) ) );
        
        //establish location
        setLocation ( x, y );
        
        //set frame shape
        if ( roundedEnquiry )
            setShape ( new RoundRectangle2D.Double ( 0, 0, panel.getWidth ( ), panel.getHeight ( ), 30, 30 ) );
            
        //set opacity
        setOpacity ( alphaLevel );
        
        //show the frame
        setVisible ( true );
    }

    public CONSCIENCIA_EDITOR_MessageBoxWindow ( Thread thread0, Thread thread1, float alphaLevel, Color backgroundColour, Color buttonOutlineColour, boolean roundedEnquiry, String buttonTextureDir )
    {
        //establish method panel
        panel = new CONSCIENCIA_EDITOR_MessageBoxPanel ( thread0, thread1, backgroundColour, buttonOutlineColour, this, buttonTextureDir );
        
        //establish Jframe stuff
        setDefaultLookAndFeelDecorated ( true ); /*translucency requirement*/
        
        //remove frame from window
        setUndecorated ( true );
        
        //add gui panel
        add ( panel );
        
        //corectly display all panel components by passing panel to set content pane
        setContentPane ( panel ); /*translucency requirement*/
		        
		//set layout 
		setLayout ( new BoxLayout ( getContentPane ( ), BoxLayout.X_AXIS ) ) ;
		
		//i have to double fucking remove the border from the frame which i don't want, since the intial frame cancels this out or something.
		getRootPane ( ).setWindowDecorationStyle ( 0 );
        
        //set application window dimension
        //setSize ( new Dimension ( panel.getWidth ( ), panel.getHeight ( ) ) );
        setSize ( new Dimension ( panel.getWidth ( ), panel.getHeight ( ) ) );
        
        //establish location
        setLocationRelativeTo ( null );
        
        //set frame shape
        if ( roundedEnquiry )
            setShape ( new RoundRectangle2D.Double ( 0, 0, panel.getWidth ( ), panel.getHeight ( ), 30, 30 ) );
            
        //set opacity
        setOpacity ( alphaLevel );
        
        //show the frame
        setVisible ( true );
    }
    
}
