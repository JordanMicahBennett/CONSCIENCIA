///author: jordan micah bennett
import javax.swing.JFrame;
import static java.awt.GraphicsDevice.WindowTranslucency.*;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.geom.RoundRectangle2D;
import javax.swing.BoxLayout;

import data.packages.CONSCIENCIA_EDITOR.*;
import data.packages.CONSCIENCIA.SYNTAX.*;
import data.packages.UNICODE.*;

public class CONSCIENCIA_EDITOR_ConsoleWindow extends JFrame
{
    //establish configuration manager
    CONSCIENCIA_EDITOR_ConfigurationManager configurationManager = null;
    
    //establish main program gui panel stuff
        //establsih gui panel: takes frame,
        CONSCIENCIA_EDITOR_ConsolePanel consolePanel = null;
        
        
    public CONSCIENCIA_EDITOR_ConsoleWindow ( String configurationStream, UNICODE_GuiPanel unicodeGuiPanel )
    {
        //estabish configurations
        configurationManager = new CONSCIENCIA_EDITOR_ConfigurationManager ( configurationStream );
        
        //establish consolePanel
        consolePanel = new CONSCIENCIA_EDITOR_ConsolePanel ( this, configurationManager, unicodeGuiPanel );
        
        //establish Jframe stuff
        setDefaultLookAndFeelDecorated ( true ); /*translucency requirement*/
        
        //remove frame from window
        setUndecorated ( true );
        
        //add gui panel
        add ( consolePanel );
        
        //corectly display all panel components by passing panel to set content pane
        setContentPane ( consolePanel ); /*translucency requirement*/
            
        //set layout 
        //setLayout ( new BoxLayout ( getContentPane ( ), BoxLayout.X_AXIS ) ) ;
        
        //i have to double fucking remove the border from the frame which i don't want, since the intial frame cancels this out or something.
        getRootPane ( ).setWindowDecorationStyle ( 0 );
        
        //set application window dimension
        setSize ( new Dimension ( ( int ) configurationManager.getBufferDimensionFromFile ( ).getWidth ( ), ( int ) configurationManager.getBufferDimensionFromFile ( ).getHeight ( ) ) );
        
        //establish location
        setLocationRelativeTo ( null );
        
        //set frame shape
        setShape ( new RoundRectangle2D.Double ( 0, 0, ( int ) configurationManager.getBufferDimensionFromFile ( ).getWidth ( ), ( int ) configurationManager.getBufferDimensionFromFile ( ).getHeight ( ), 30, 30 ) );
            
        //set opacity
        setOpacity ( 1.0f );
        
        //set visiblility
        setVisible ( true );
    }
}