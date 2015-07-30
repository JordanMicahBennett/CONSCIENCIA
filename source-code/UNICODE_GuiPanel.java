//author: jordan micah bennett
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JOptionPane;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.Component;
import java.awt.Point;
import java.awt.geom.RoundRectangle2D;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import java.util.Date;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.awt.RenderingHints;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

//editor
import javax.swing.JEditorPane;
import javax.swing.text.StyleContext;
import javax.swing.text.AttributeSet;
import javax.swing.JScrollPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.Font;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;
import javax.swing.Action;
import javax.swing.AbstractAction;
import jsyntaxpane.DefaultSyntaxKit;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeListener;
import java.lang.reflect.*;


import data.packages.CONSCIENCIA_EDITOR.*;
import data.packages.UNICODE.*;
import data.packages.CONSCIENCIA.SYNTAX.*;

public class UNICODE_GuiPanel extends JPanel
{
    //establish screen dimensions
        //get screen dimensio
        private Dimension screen_dimension = Toolkit.getDefaultToolkit ( ).getScreenSize ( );  
        //establish variables
        private int screen_width = 1280, screen_height = 720;
        //establish memento
        private int initial_screen_width = screen_width, initial_screen_height = screen_height;
        private float initial_opacity = 0.0f;
        
    //establish frame connection
    private JFrame application_frame; //will allow repositioning of frame by user
    //establish current coordinates genrated by pressed down mouse
    private Point held_mouse_coords = null;
    //establish current coordinates geenrated by mouse dragging
    private Point dragged_mouse_coords = null;

    //establish opacity manager
    private UNICODE_OpacityController opacity_manager = new UNICODE_OpacityController ( );
    
    //establish file manager
    private UNICODE_ConfigurationManager configurationManager = new UNICODE_ConfigurationManager ( "data/config/CONFIG.ini" );
    
    //estblish rednering hint to install anti-aliasing
    private UNICODE_AntiAliasingController anti_alias_manager = new UNICODE_AntiAliasingController ( configurationManager.getAntiAliasingStateFromFile ( ) );
    
    //establish executable invocator
    private UNICODE_ExecutableInvocation exec_invocator = new UNICODE_ExecutableInvocation ( );
    //file selector
    private ImageIcon backgroundImageIcon = null, genericBackgroundImageIcon = null;
    private Image backgroundImage = null, genericBackgroundImage = null;

    private UNICODE_FileSelection privateDirectorySelector = new UNICODE_FileSelection ( "C:" );
    
    //convnience pack
    private UNICODE_ConveniencePack conveniencePack = new UNICODE_ConveniencePack ( );

  
    //new file creation requirements
    private UNICODE_LastKnownDirectoryController lastKnownDirectoryController = new UNICODE_LastKnownDirectoryController ( "" );
    
    //copiler window size controller
    private UNICODE_CompilerWindowSizeController compilerWindowSizeController = new UNICODE_CompilerWindowSizeController ( "" );
    
    //editor text area    
    private UndoManager undoManager  = new UndoManager ( );
    
    private CONSCIENCIA_EDITOR_EditorAreaDisplayManager editorAreaDisplayManager = new CONSCIENCIA_EDITOR_EditorAreaDisplayManager ( 1100, 580, 1280, 720, "data/images/defaultTab.png", "data/images/selectedTab.png" );
    private String projectName = "", projectLocation = "";
    private ArrayList projectFileNameList = null;
    private boolean editorModeEnquiry = false;
    private CONSCIENCIA_EDITOR_ProjectParser CONSCIENCIALoader = null;
    
    //menus
    private JPanel mainContainer = new JPanel ( );
    private BUTTON_DOCK_0 mainMenuPanel = null;
    
    //dock controller
    private CONSCIENCIA_EDITOR_DockController dockController = null;
    private Timer dockControllerTimer = null;

    public UNICODE_GuiPanel ( JFrame bsgk_frame )
    {  
        //establish custom cursor
        //conveniencePack.createCustomCursor ( "data/images/logo.png", this );

        //establish project loader
        CONSCIENCIALoader = new CONSCIENCIA_EDITOR_ProjectParser ( getLastKnownDiretoryFromFile ( ), "CONSCIENCIA.dll", editorAreaDisplayManager, new Color ( 255, 255, 255 ) );
        projectName = CONSCIENCIALoader.getProjectName ( );
        projectFileNameList = CONSCIENCIALoader.getProjectFileNamesList ( );
        projectLocation =  CONSCIENCIALoader.getProjectLocation ( );

        //establish application frame
        application_frame = bsgk_frame;     
        
        //establish bg colours
        setBackground ( configurationManager.getColourFromFile ( ) ); //so black buttons outline is visible
        mainContainer.setBackground ( configurationManager.getColourFromFile ( ) );
        
        //establish menu panels
            //establish main menu panel
            mainMenuPanel = new BUTTON_DOCK_0 ( generatedCustomComponentListMenu0 ( ), true, 120, 80, 10, 60, 2220, "clockwise", "horizontal", "data/images/main menu/", 84, 84, 10, 10, configurationManager.getColourFromFile ( ), new Color ( 255, 255, 255 ) );
 
        //play startup audios
        //mainMenuPanel.audioPlayer.playAudio ( mainMenuPanel.getAudioByAlias ( "a" ) ); 
        
            
        //establish dock controller
        dockController = new CONSCIENCIA_EDITOR_DockController ( editorAreaDisplayManager, mainMenuPanel, this, 8, 8, 702, 584, 8, 8, 590 );

        //add key listener
        addKeyListener ( new keyListening ( ) );
        addMouseListener ( new mouseListening ( ) );
        addMouseMotionListener ( new mouseListening ( ) );
        
        //setup editor area
            editorAreaDisplayManager.setAllIcons ( );
            editorAreaDisplayManager.initializeTabIconUpdateListener ( );
        
            //add editorAreaDisplayManager to this panel
            if ( CONSCIENCIALoader.getProjectExistenceEnquiry ( ) )
                mainContainer.add ( editorAreaDisplayManager );
                
            else if ( !CONSCIENCIALoader.getProjectExistenceEnquiry ( ) )
                editorAreaDisplayManager.addEditor ( 20, new Color ( 230, 230, 230 ), "text/java", null, null, 0 );


        //add main menu panel
        mainMenuPanel.setPreferredSize ( new Dimension ( 1200, 210 ) ); //setup dimension
        mainContainer.add ( mainMenuPanel ); 

        //add main container 
        add ( mainContainer );
        
        //establish layout
        setLayout ( new BoxLayout ( this, BoxLayout.Y_AXIS ) );
        
        //set focus to this panel
        setFocusable ( true );
        
        //initialise initial opcaity level value at startup
        initial_opacity = getStartupOpacityLevel ( );
       
        //////////////////////////////////////////////
        //MOUSE WHEEL LISTENING
        ////////////////////////////////////////////// 
        addMouseWheelListener ( new mouseWheelListening ( ) );   
    }
    
    
    public String getLastKnownDiretoryFromFile ( )
    {
        return configurationManager.getLastDirectoryFromFile ( );
    }
    
    public String getMethodPackageRegexUsageAnswerFromFile ( )
    {
        return configurationManager.getMethodPackageRegexUsageAnswerFromFile ( );
    }
    
    public String getJavacLocationFromFile ( )
    {
        return configurationManager.getJavacLocationFromFile ( );
    }
    
    public String getCompilerWindowSizeString ( )
    {
        return configurationManager.getCompilerWindowSizeString ( );
    }    
    
    //return current opacity level
    public float getStartupOpacityLevel ( )
    {
        opacity_manager.setOpacLevel ( configurationManager.getOpacityFromFile ( ) );
        return opacity_manager.getOpacLevel ( );
    }

    public void paintComponent ( Graphics graphics )
    {
        super.paintComponent ( graphics );
        Graphics2D graphics2d = ( Graphics2D ) graphics;
        
        //establish anti aliasing
        anti_alias_manager.setupAntiAliasing ( graphics2d );
    }
    
   
    //mouse wheel rotation listenening
    private class mouseWheelListening implements MouseWheelListener
    {
        public void mouseWheelMoved ( MouseWheelEvent mwEvent )
        {
            int mouseWheelRotation = mwEvent.getWheelRotation ( );

                
            repaint ( );
        }
    }

    //establish key listeneing
    private class keyListening implements KeyListener 
    {
        public void keyPressed ( KeyEvent kEvent ) 
        {
            switch ( kEvent.getKeyCode ( ) )
            {
                case KeyEvent.VK_ESCAPE:
                {
                    System.exit ( 0 );
                }
                break;
                case KeyEvent.VK_D:
                {
                    dockController.toggleMenuPanel ( );
                }
                break;
                case KeyEvent.VK_PERIOD:
                {

                }
                break;
            }

            repaint ( );
        }
        public void keyReleased ( KeyEvent kEvent ) 
        {
        }
        public void keyTyped ( KeyEvent kEvent ) 
        {
        }
    }

    //mouse listener
    private class mouseListening implements MouseListener, MouseMotionListener
    { 
        public void mouseClicked ( MouseEvent mEvent )
        {
            dockController.toggleMenuPanel ( );
            repaint ( );
        }

        public void mouseReleased ( MouseEvent mEvent )
        {
        }
        
        public void mouseEntered ( MouseEvent mEvent )
        {
        }  
        
        public void mouseExited ( MouseEvent mEvent )
        {
        }
     
        public void mousePressed ( MouseEvent mEvent )
        {
            //establish mouse pressed coords
            held_mouse_coords = mEvent.getPoint ( );
            repaint ( );
        }
        
        public void mouseDragged ( MouseEvent mEvent )
        {         
            //establish dragged mouse coordinates
            dragged_mouse_coords = mEvent.getLocationOnScreen ( );    
            application_frame.setLocation ( ( int ) ( dragged_mouse_coords.getX ( ) - held_mouse_coords.getX ( ) ), ( int ) ( dragged_mouse_coords.getY ( ) - held_mouse_coords.getY ( ) ) );
            repaint ( );
        }
        public void mouseMoved ( MouseEvent mEvent )
        {
        }
    }
    
    public JPanel getPanel ( )
    {
        return this;
    }
    
    //add custom components which dont exist in UNICODE_MenuPanel by default,
    //where button click response is conceerned.
    public ArrayList <Object> generatedCustomComponentListMenu0 ( )
    {
        ArrayList <Object> value = new ArrayList <Object> ( );

        value.add ( application_frame ); //0
        value.add ( editorAreaDisplayManager ); //1
        value.add ( lastKnownDirectoryController ); //2
        value.add ( configurationManager ); //3
        value.add ( getLastKnownDiretoryFromFile ( ) ); //4
        value.add ( editorAreaDisplayManager.getCurrentTabTitle ( ) ); //5
        value.add ( projectLocation ); //6
        value.add ( getJavacLocationFromFile ( ) ); //7
        value.add ( "*" ); //8
        value.add ( Integer.parseInt ( conveniencePack.getDelimitedArray ( getCompilerWindowSizeString ( ), "", ",", 2 ) [ 0 ] ) ); //9
        value.add ( Integer.parseInt ( conveniencePack.getDelimitedArray ( getCompilerWindowSizeString ( ), "", ",", 2 ) [ 1 ] ) ); //10
        value.add ( getMethodPackageRegexUsageAnswerFromFile ( ) ); //11
        value.add ( projectFileNameList ); //12
        value.add ( editorModeEnquiry ); //13
        value.add ( CONSCIENCIALoader ); //14
        value.add ( projectName ); //15
        value.add ( conveniencePack.generateArrayListContentFromFile ( "data/html/CONSCIENCIA.htm" ) ); //16
        value.add ( this ); //17
        
        return value;
    }
    
    
    //methods
        //accessors
        public String getCurrentTabContent ( )
        {
            return editorAreaDisplayManager.getCurrentEditorText ( );
        }
        //mutators
}
