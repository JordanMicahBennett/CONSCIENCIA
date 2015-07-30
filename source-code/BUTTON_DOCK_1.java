//author: jordan micah bennett
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

import data.packages.UNICODE.*;
import data.packages.CONSCIENCIA_EDITOR.*;
import data.packages.CONSCIENCIA.SYNTAX.*;

public class BUTTON_DOCK_1 extends UNICODE_MenuPanel
{
    public BUTTON_DOCK_1 ( ArrayList <Object> customComponentList, boolean menuVisibility, int _xCoord, int _yCoord, int _MAXIMUM_BUTTONS, int BUTTON_PROXIMITY, int AXIS_PROXIMITY, String axisDirection, String axisLayoutType, String buttonListDirectory, int buttonWidth, int buttonHeight, Color bgColour, Color buttonOutlineColor, String buttonShapeType, int arcHeight, int arcDepth, int lastButtonChopValue )
    {
        super ( customComponentList, menuVisibility, _xCoord, _yCoord, _MAXIMUM_BUTTONS, BUTTON_PROXIMITY, AXIS_PROXIMITY, axisDirection, axisLayoutType, buttonListDirectory, buttonWidth, buttonHeight, bgColour, buttonOutlineColor, buttonShapeType, arcHeight, arcDepth, lastButtonChopValue );
    }

    //abstract method
    public void mouseClickedExtendedDefinition ( MouseEvent mEvent )
    {        
        //access settings
        if ( getMenu ( ).getButtonList ( ).get ( 0 ).contains ( mEvent.getX ( ), mEvent.getY ( ) ) )
        {
            
            audioPlayer.playAudio ( "" + getAudioByAlias ( "t" ) ); 
        }
        
        //quit software
        if ( getMenu ( ).getButtonList ( ).get ( LAST_BUTTON ).contains ( mEvent.getX ( ), mEvent.getY ( ) ) )
        {
            CONSCIENCIA_EDITOR_ConsoleWindow consoleWindow = ( CONSCIENCIA_EDITOR_ConsoleWindow ) getCustomComponentList ( ).get ( 2 );
            
            audioPlayer.playAudio ( "" + getAudioByAlias ( "t" ) ); 
            
            consoleWindow.setVisible ( false );
        }
        repaint ( );
    }
    

    public void mouseMovedExtendedDefinition ( MouseEvent mEvent )
    {
    }
    
    public void mouseEnteredExtendedDefinition ( MouseEvent mEvent )
    {
    }
    
    public void mouseExitedExtendedDefinition ( MouseEvent mEvent )
    {
    }
    
    public void mouseDraggedExtendedDefinition ( MouseEvent mEvent )
    {
    }
    
    public void mouseReleasedExtendedDefinition ( MouseEvent mEvent )
    {
    }
    
    public void mousePressedExtendedDefinition ( MouseEvent mEvent )
    {
    }
    
    public void mouseWheelRolledExtendedDefinition ( MouseWheelEvent mwEvent )
    {
    }


    
    //extra rendering
    public void drawMore ( )
    {
    }
}  