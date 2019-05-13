//Author: Jordan Micah Bennett
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.ArrayList;

import data.packages.CONSCIENCIA_EDITOR.*;
import data.packages.CUP.*;
import data.packages.UNICODE.*;
import data.packages.CONSCIENCIA.SYNTAX.*;

public class CONSCIENCIA_EDITOR_ConsolePanel extends JPanel
{
    //attribs
        //Mouse position tracking variables
        private Point heldMouseCords = null, draggedMouseCords = null;
        private JFrame applicationFrame = null;
        
        //config manager
        private CONSCIENCIA_EDITOR_ConfigurationManager configurationManager = null;
        
        //establish consoleField
        public UNICODE_ConsoleField consoleField = null;
        
        //establish utility package
        private UNICODE_ConveniencePack conveniencePack = null;
        
        //establish button dock 0
        private BUTTON_DOCK_1 buttonDock1 = null;
        
        //establish font colour
        private Color fontColour = null;
        private String fontName = null;
        private int fontSize = 0;
        
        //establish gui panel
        private UNICODE_GuiPanel unicodeGuiPanel = null;
        
        //establish environment
        private Environment parserEnvironment = null;
        
        //establish parser
        private CONSCIENCIAParser parser = null;


    //constructor
    public CONSCIENCIA_EDITOR_ConsolePanel ( JFrame applicationFrame, CONSCIENCIA_EDITOR_ConfigurationManager configurationManager, UNICODE_GuiPanel unicodeGuiPanel )
    {
        this.applicationFrame = applicationFrame;
        this.configurationManager = configurationManager;
        this.conveniencePack = new UNICODE_ConveniencePack ( );
        this.unicodeGuiPanel = unicodeGuiPanel;
        
        //establish console attributes wrt config manager
            //establish background colour
            setBackground ( configurationManager.getColourFromFile ( ) );
            //establish font attributes
            fontColour = configurationManager.getFontColourFromFile ( );
            fontName = configurationManager.getFontNameFromFile ( );
            fontSize = configurationManager.getFontSizeFromFile ( );
            
        
        //establish parsing environment, and casj parser
        parserEnvironment = new Environment ( );
        updateParser ( "" );
        
        //synchronize my gui editor with environment's required editor attributes
        parserEnvironment.establishEditorSynchronization ( applicationFrame, this );
        
        //establish button dock
            //component list is a compaction of components not native to the base abstract class of BUTTON_DOCK_1 parent UNICODE_MenuPanel.
            buttonDock1 = new BUTTON_DOCK_1 ( getGeneratedComponentList ( ), true, ( int ) configurationManager.getBufferDimensionFromFile ( ).getWidth ( ) - ( 68 * 2 + 10 * 2 ), 20, 2, 90, 2220, "clockwise", "horizontal", "data/images/window menu/", 82, 82, configurationManager.getColourFromFile ( ), new Color ( 255, 255, 255 ), "rr", 23, 23, 0 );
        
        //establish console field
            consoleField = new UNICODE_ConsoleField ( 20, 20, configurationManager.getLineSpacingFromFile ( ), ".input : ", "_", true, configurationManager.getLabelDescriptionPadding ( ), this );
            //setup details for generating output line
            consoleField.setAdditionalUpdatingThread ( generateAdditionalUpdateThread ( ) );
            consoleField.setAdditionalUpdatingThreadKeyCode ( KeyEvent.VK_UP );
        
        //add button dock panel to this
        add ( buttonDock1 );       
            
        //establish listeners
            //add mouse listeners
            addMouseMotionListener ( new mouseListening ( ) ) ;
            addMouseListener ( new mouseListening ( ) ) ;
            
            //add mouse wheel listener
            addMouseWheelListener ( new mouseWheelListenening ( ) );        
            
            //add key listening
            addKeyListener ( new keyListening ( ) );
            
            //set focus to this panel
            setFocusable ( true );
    }
    
    //paint component
    public void paintComponent ( Graphics graphics )
    {
        super.paintComponent ( graphics );
        Graphics2D graphics2d = ( Graphics2D ) graphics;
        
        //render text
        consoleField.draw ( graphics, graphics2d, fontName, fontSize, fontColour );
        
        //setup boundary for JPanel
        buttonDock1.setBounds ( 0, 630, applicationFrame.getWidth ( ), 200 );
        
        //update tooltip - used in toScheme to determine the current location of a generated automata environment panel.
        //see toScheme >> visitExpEloquenceSimulationSimpleCellularAutomata
        setToolTipText ( "" + consoleField.getPointerLocation ( ) );
    }
    
    
    //establish key listeneing
    private class keyListening implements KeyListener 
    {
        public void keyPressed ( KeyEvent keyEvent ) 
        {
            consoleField.allowUpdating ( keyEvent );
            repaint ( );
        }
        public void keyReleased ( KeyEvent keyEvent ) 
        {
        }
        public void keyTyped ( KeyEvent keyEvent ) 
        {
        }
    }
    //mouse listener
    private class mouseListening implements MouseMotionListener, MouseListener
    {
        public void mouseEntered ( MouseEvent mouseEvent )
        {
        }
        public void mouseExited ( MouseEvent mouseEvent )
        {
        }
        public void mousePressed ( MouseEvent mouseEvent )
        {
            //establish mouse pressed coords
            heldMouseCords = mouseEvent.getPoint ( );
        }
        public void mouseDragged ( MouseEvent mouseEvent )
        {         
            //establish dragged mouse coordinates
            draggedMouseCords = mouseEvent.getLocationOnScreen ( );    
            int draggedMouseCoordX = ( int ) ( draggedMouseCords.getX ( ) - heldMouseCords.getX ( ) ), draggedMouseCoordY = ( int ) ( draggedMouseCords.getY ( ) - heldMouseCords.getY ( ) );
            applicationFrame.setLocation ( draggedMouseCoordX, draggedMouseCoordY );
            repaint ( );
        }
        public void mouseReleased ( MouseEvent mouseEvent )
        {
        }
        public void mouseMoved ( MouseEvent mouseEvent )
        {
        }
        public void mouseClicked ( MouseEvent mouseEvent )
        {
        }
    }
    
    //mouse wheel controller - user driven
    private class mouseWheelListenening implements MouseWheelListener
    {
        public void mouseWheelMoved ( MouseWheelEvent mouseWheelEvent )
        {
            int rotationDirection = mouseWheelEvent.getWheelRotation ( );
            
            consoleField.scrollField ( rotationDirection, 8 );
            
            repaint ( );
        }
    }

    //I use this to add the functionality of adding a customized console line that
    //has label "output".
    //This thread will start, under the allow updating unction of console field,
    //and represents additional functionality under that function.
    public Thread generateAdditionalUpdateThread ( )
    {
        Thread returnValue = new Thread 
        (
            new Runnable ( )
            {
                public void run ( )
                {
                    if ( unicodeGuiPanel == null )
                        consoleField.addLine ( ".output : ", "" + getParseResult ( consoleField.getLineText ( ), parserEnvironment ), generateAdditionalUpdateThread ( ) );
                    else
                        consoleField.addLine ( ".output : ", "" + getParseResult ( unicodeGuiPanel.getCurrentTabContent ( ) + consoleField.getLineText ( ), parserEnvironment ), generateAdditionalUpdateThread ( ) );
                }
            }
        );
        
        return returnValue;
    }

    //PARSER SPECIFIC RESULT GENERATED VIA USER INPUT
    public String getParseResult ( String input,  Environment environment ) 
    {
        String returnValue = "";
        ASTProgram _ASTProgram = null;
        ConscienciaEvaluator translator = new ConscienciaEvaluator ( );
        String topOfParseStack = "";
        
        try
        {
            if ( !input.equals ( "" ) || ( input != null ) ) //only attempt parsing if input isn't null or empty
            {
                updateParser ( input );
                    
                _ASTProgram = ( ASTProgram ) parser.parse ( ).value;
            }
        } 
        catch ( Exception e ) 
        {
            System.out.println ( "parse error: " + e.getMessage ( ) );
            e.printStackTrace ( );
        }
        if ( _ASTProgram != null )
            try
            {
                Object result;
               
                result = _ASTProgram.visit ( translator, environment );
                
                if ( unicodeGuiPanel != null ) //this process is only required when I want to derive results for extra content.
                {
                    //things become a little complicated when it comes time to derive results, 
                    //when my parse input stream is more than one line of user input. (ie from a file..like content from one of our IDE tabs)
                    //when reading from an entire file, in addition to considering new user input (each parseResult call)
                    //if the result will now be the entire stack of sequence in the program, instead of that single input line.
                    
                    //The point is I need to deduce the top of the stack, I don't want to show the entire parse stack.
                    //Using trivial regex and basic string manipulation, I can indeed derive the top of the stack from the peculiar stack string generated at the end of a parseResult c../
                    String rawStackString = result.toString ( ).replace ( "\n", "::" ); //It is for the best that the "::" be an unusable character set in the langauge
                    String refinedStackString = conveniencePack.getRegexComponents ( "(.*)(\\::)(.*)(\\::)(\\))", rawStackString, "$3" );
                    
                    topOfParseStack += refinedStackString;
                    
                    returnValue += topOfParseStack;
                    System.out.println ( returnValue ); //force continued input
                }
                else
                    returnValue += result.toString ( );
            } 
            catch ( Exception e )
            {
                consoleField.addLine ( ".output : ", "" + e.getMessage ( ), generateAdditionalUpdateThread ( ) );
                System.out.println ( e.getMessage ( ) );
            }
            
        return returnValue;
    }
    
    //will be used to initialise parser based on ide tab content.
    public void updateParser ( String input )
    {
        parser = new CONSCIENCIAParser ( new CONSCIENCIALexer ( conveniencePack.getInputStreamFromInput ( input ) ) );
    }
    
    public ArrayList getGeneratedComponentList ( )
    {
        ArrayList returnValue = new ArrayList ( );
        
        returnValue.add ( this );
        returnValue.add ( consoleField );
        returnValue.add ( applicationFrame );
        returnValue.add ( parserEnvironment );
        
        return returnValue;
    }
}
