package data.packages.CONSCIENCIA_EDITOR; //Author(s): Jordan Micah Bennett

import javax.swing.JPanel;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import java.awt.Point;

import data.packages.UNICODE.*;

public abstract class CONSCIENCIA_EDITOR_MenuPanel extends JPanel
{
    //attributes
    
    //PANEL REPOSITIONING VARS
        // //establish current coordinates genrated by pressed down mouse
        // private Point held_mouse_coords = null;
        
        // //establish current coordinates geenrated by mouse dragging
        // private Point dragged_mouse_coords = null;
    
    
    //establish variables
    private int xCoord, yCoord;
    
    //establish audio player
    public UNICODE_AudioPlayer audioPlayer = new UNICODE_AudioPlayer ( );
    
    //establish audio archive
    private String [ ] audioArchive = { "startup.wav", "ambience.wav", "terminate.wav", "enter.wav", "exit.wav", "restoration success.wav", "minimization success.wav", "memory log success.wav" };
    
    //establish a bushman button to access convenience functions ( not actual button )
    private UNICODE_Button bb_access = new UNICODE_Button ( );
    
    //establish a bushman menu to access convenience functions ( not actual menu )
    private UNICODE_Menu bmAccess = new UNICODE_Menu ( );
   
    //establish button coord generators for all menus
    private UNICODE_Menu [ ] cGens = new UNICODE_Menu [ 1 ];
    
    //establish button arrays for menus
    private ArrayList <UNICODE_Button> buttonPack = new ArrayList <UNICODE_Button> ( );
        
    //establish bushman menus to hold button packs
    private UNICODE_Menu menu = null;
    
    private int MAXIMUM_BUTTONS = 0;

    //represents the value of the last button index, after buttons have been generated.
    public int LAST_BUTTON = 0;

    //BUTTON RESIZING VARS
        //establish timer delay value - how much time before the next enlargement occurs?
        private int buttonResizeDelay = 1;
        
        //establish button resize indices. When a button is hovered over it will equate that index to this variable,
        //then this variable will be used to enlarge that button only, when passed to the button array in
        //timer action listener
        private int resizableButtonIndex = -1;
        
        //establish button resizing timer
        private Timer buttonResizingTimer = new Timer ( buttonResizeDelay, new buttonResizeActionListening ( ) );
        
        //establish max button scale - when button is hovered over, how much should it scale to before stop enlarging?
        private double maxButtonScale = 1.22, scaleChangeRate = 0.0023;
		
		//establish array list of custom objects passable to this menu
		private ArrayList <Object> customComponentList = null;
		
		//establish button outline colour
		private Color buttonOutlineColour = null;
		
		//establish instance for mouse event access to this panel
		private JPanel thisPanel = this;
        
    //constructor
    public CONSCIENCIA_EDITOR_MenuPanel ( ArrayList <Object> _customComponentList, boolean menuVisibility, int _xCoord, int _yCoord, int _MAXIMUM_BUTTONS, int BUTTON_PROXIMITY, int AXIS_PROXIMITY, String axisDirection, String axisLayoutType, String buttonListDirectory, int buttonWidth, int buttonHeight, int buttonArcHeight, int buttonArcDepth, Color bgColour, Color _buttonOutlineColour )
    {
        //establish bg colour
        setBackground ( bgColour );
		
		//establish custom component list
		customComponentList = _customComponentList;
		
		//establish ubtton outline colour
		buttonOutlineColour = _buttonOutlineColour;
        
        //establish dimension
        xCoord = _xCoord;
        yCoord = _yCoord;

        //establish max buttons
        MAXIMUM_BUTTONS = _MAXIMUM_BUTTONS;
 
        //establish menu wrt button pack
        menu = new UNICODE_Menu ( buttonPack, menuVisibility, MAXIMUM_BUTTONS, xCoord, yCoord );

        //generate buttons
        generateButtons ( axisDirection, axisLayoutType, buttonListDirectory, buttonWidth, buttonHeight, buttonArcHeight, buttonArcDepth, BUTTON_PROXIMITY, AXIS_PROXIMITY );
        
        //add mouse listener
        addMouseListener ( new mouseListening ( ) );
        addMouseMotionListener ( new mouseListening ( ) );
        
        //set focus capability
        setFocusable ( true );
    }
    
    
    //paint component
    public void paintComponent ( Graphics graphics )
    {
        super.paintComponent ( graphics );
        Graphics2D graphics2d = ( Graphics2D ) graphics;
        
        //establish anti aliasing
        //anti_alias_manager.setupAntiAliasing ( graphics2d );

        menu.showMenu ( graphics2d, buttonOutlineColour, this );
    }
    
    
    //accessors
    public UNICODE_Menu getMenu ( )
    {
        return menu;
    }

    //misc
	//generate graphical buttons
    public void generateButtons ( String axisDirection, String axisLayoutType, String buttonListDirectory, int buttonWidth, int buttonHeight, int buttonArcHeight, int buttonArcDepth, int BUTTON_PROXIMITY, int AXIS_PROXIMITY )
    {
        //generate axis generation data for menus via convenience functions
        //establish menu array used for coordinate generation THEN define buttons
        //define buttons - main menu
        cGens = bmAccess.defineGridAxisCoordinateGenerationArray ( 1, " " + MAXIMUM_BUTTONS, false, BUTTON_PROXIMITY, AXIS_PROXIMITY, xCoord, yCoord, axisDirection, axisLayoutType );  
        
        for ( int i = 0; i < MAXIMUM_BUTTONS; i ++ )
        {
            if ( i < ( MAXIMUM_BUTTONS - 1 ) )
                menu.getButtonList ( ).add ( new UNICODE_Button ( cGens [ 0 ].getXGen ( ) [ i ], cGens [ 0 ].getYGen ( ) [ i ], buttonWidth, buttonHeight, buttonArcHeight, buttonArcDepth, bb_access.cCTL ( bb_access.cTL ( "" + i + "d.png " + i + "s.png " + i + "s.png" ) ), 0, false, 0, buttonListDirectory ) );
            else
                menu.getButtonList ( ).add ( new UNICODE_Button ( cGens [ 0 ].getXGen ( ) [ i ], cGens [ 0 ].getYGen ( ) [ i ], buttonWidth - 16, buttonHeight - 16, buttonArcHeight, buttonArcDepth, bb_access.cCTL ( bb_access.cTL ( "" + i + "d.png " + i + "s.png " + i + "s.png " ) ), 0, false, 0, buttonListDirectory ) );
            
            LAST_BUTTON ++;
        }
		
		LAST_BUTTON --; //WE WANT THE LITERAL VALUE OF LAST BUTTON, RATHER THAN THE LIMIT, SO WE DECREMENT ONE PLACE BEFORE THAT LIMIT.
    }
	
	//part of mouseClickDefinition. Will allow users to pass objects from gui panel, to add customized components
	//that may be usable in their implementation of this class. 
	public ArrayList <Object> getCustomComponentList ( )
	{
		return customComponentList;
	}
	public void addCustomComponent ( Object value )
	{
		customComponentList.add ( value );
	}	
	
	public void setCustomComponentList ( ArrayList <Object> value )
	{
		customComponentList = value;
	}	
	
    //allow user to define mouseClickedExtendedDefinition Function
    public abstract void mouseClickedExtendedDefinition ( MouseEvent mEvent );
	public abstract void mouseMovedExtendedDefinition ( MouseEvent mEvent );
	public abstract void mouseEnteredExtendedDefinition ( MouseEvent mEvent );
	public abstract void mouseExitedExtendedDefinition ( MouseEvent mEvent );
	public abstract void mouseDraggedExtendedDefinition ( MouseEvent mEvent );
	public abstract void mouseReleasedExtendedDefinition ( MouseEvent mEvent );
	public abstract void mousePressedExtendedDefinition ( MouseEvent mEvent );

    //mouse listener
    private class mouseListening implements MouseListener, MouseMotionListener
    { 
        public void mouseClicked ( MouseEvent mEvent )
        {
            mouseClickedExtendedDefinition ( mEvent );
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
            // //establish mouse pressed coords
            // held_mouse_coords = mEvent.getPoint ( );
        }
        
        public void mouseDragged ( MouseEvent mEvent )
        {         
            // //establish dragged mouse coordinates
            // dragged_mouse_coords = mEvent.getLocationOnScreen ( );    
            // thisPanel.setLocation ( ( int ) ( dragged_mouse_coords.getX ( ) - held_mouse_coords.getX ( ) ), ( int ) ( dragged_mouse_coords.getY ( ) - held_mouse_coords.getY ( ) ) );
        }
        
        public void mouseMoved ( MouseEvent mEvent )
        {
            //bmAccess.establishButtonVisualResponseTimerTriggerComponent ( getMenu ( ).getVisibility ( ), MAXIMUM_BUTTONS, buttonResizingTimer, maxButtonScale,  resizableButtonIndex, buttonPack, mEvent );           
            if ( getMenu ( ).getVisibility ( ) )
            {
                for ( int buttons = 0; buttons < getMenu ( ).getButtonList ( ).size ( ); buttons ++ )
                {
                    if ( getMenu ( ).getButtonList ( ).get ( buttons ) .contains ( mEvent.getX ( ), mEvent.getY ( ) ) )
                    {
                        buttonResizingTimer.start ( );
                        getMenu ( ).getButtonList ( ).get ( buttons ) .setScaleMaximum ( maxButtonScale );//reset max button scale
                        getMenu ( ).getButtonList ( ).get ( buttons ) .setTextureIndex ( 1 ); //switch button texture layer
                        //place a break after getting button index
                        //otherwise the timer will only be called when
                        //the buttons has reached the end of its value in its for loop
                        //instead of the value index we need to resize the appropriate button
                        resizableButtonIndex = buttons; break;//tell the resize timer which button to resize
                    }
                    else
                    {
                        buttonResizingTimer.stop ( ); //stop resize timer
                        getMenu ( ).getButtonList ( ).get ( buttons ) .setResized ( false ); //disable resizing
                        getMenu ( ).getButtonList ( ).get ( buttons ) .resetScale ( ); //reset scale to original
                        getMenu ( ).getButtonList ( ).get ( buttons ) .setTextureIndex ( 0 ); //reset texture index
                    }
                }
            }
            repaint ( );
        }
    }
    
    //button resize action listeenr
    private class buttonResizeActionListening implements ActionListener
    {
        public void actionPerformed ( ActionEvent aEvent )
        {
            bmAccess.establishButtonVisualResponseActionListenerComponent ( getMenu ( ).getVisibility ( ), resizableButtonIndex, getMenu ( ).getButtonList ( ), scaleChangeRate );
            repaint ( );
        }
    }
	
	//returns an audio string name based on "alias" entered
	public String getAudioByAlias ( String alias )
	{
		String audio_name = "";
		if ( alias.equals ( "s" ) )
			audio_name = audioArchive [ 0 ];
		if ( alias.equals ( "e" ) )
			audio_name = audioArchive [ 3 ];
		if ( alias.equals ( "x" ) )
			audio_name = audioArchive [ 4 ];
		if ( alias.equals ( "t" ) )
			audio_name = audioArchive [ 2 ];
		if ( alias.equals ( "a" ) )
			audio_name = audioArchive [ 1 ];
		if ( alias.equals ( "rs" ) )
			audio_name = audioArchive [ 5 ];
		if ( alias.equals ( "ms" ) )
			audio_name = audioArchive [ 6 ];
		if ( alias.equals ( "mls" ) )
			audio_name = audioArchive [ 7 ];
		return audio_name;
	}

}
