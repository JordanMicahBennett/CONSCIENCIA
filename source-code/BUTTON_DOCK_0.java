//author: jordan micah bennett
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JFrame;

import data.packages.CONSCIENCIA_EDITOR.*;
import data.packages.UNICODE.*;
import data.packages.CONSCIENCIA.SYNTAX.*;

public class BUTTON_DOCK_0 extends CONSCIENCIA_EDITOR_MenuPanel
{
    //custom components imported
    
    //constructor
    public BUTTON_DOCK_0 ( ArrayList <Object> customComponentList, boolean menuVisibility, int _xCoord, int _yCoord, int _MAXIMUM_BUTTONS, int BUTTON_PROXIMITY, int AXIS_PROXIMITY, String axisDirection, String axisLayoutType, String buttonListDirectory, int buttonWidth, int buttonHeight, int buttonArcHeight, int buttonArcDepth, Color bgColour, Color buttonOutlineColour )
    {
        super ( customComponentList, menuVisibility, _xCoord, _yCoord, _MAXIMUM_BUTTONS, BUTTON_PROXIMITY, AXIS_PROXIMITY, axisDirection, axisLayoutType, buttonListDirectory, buttonWidth, buttonHeight, buttonArcHeight, buttonArcDepth, bgColour, buttonOutlineColour );
    }
    
    //abstract method
    public void mouseClickedExtendedDefinition ( MouseEvent mEvent )
    {
        //create new project/new tab
        if ( getMenu ( ).getButtonList ( ).get ( 0 ).contains ( mEvent.getX ( ), mEvent.getY ( ) ) )
        {
            audioPlayer.playAudio ( "" + getAudioByAlias ( "e" ) ); 
            new CONSCIENCIA_EDITOR_Utils ( ( JFrame ) getCustomComponentList ( ).get ( 0 ), ( CONSCIENCIA_EDITOR_EditorAreaDisplayManager ) getCustomComponentList ( ).get ( 1 ) )
            .newItem 
            ( 
                ( UNICODE_LastKnownDirectoryController ) getCustomComponentList ( ).get ( 2 ), 
                ( UNICODE_ConfigurationManager ) getCustomComponentList ( ).get ( 3 ),
                ( String ) getCustomComponentList ( ).get ( 4 )
            );
        }
        
        //save as/override file
        if ( getMenu ( ).getButtonList ( ).get ( 1 ).contains ( mEvent.getX ( ), mEvent.getY ( ) ) )
        { 
            audioPlayer.playAudio ( "" + getAudioByAlias ( "e" ) ); 
            new CONSCIENCIA_EDITOR_Utils ( ( JFrame ) getCustomComponentList ( ).get ( 0 ), ( CONSCIENCIA_EDITOR_EditorAreaDisplayManager ) getCustomComponentList ( ).get ( 1 ) )
            .save 
            ( 
                ( String ) getCustomComponentList ( ).get ( 4 ), 
                ( String ) getCustomComponentList ( ).get ( 5 )
            );
        }
        
        //open project/open file
        if ( getMenu ( ).getButtonList ( ).get ( 2 ).contains ( mEvent.getX ( ), mEvent.getY ( ) ) )
        { 
            audioPlayer.playAudio ( "" + getAudioByAlias ( "e" ) ); 
            new CONSCIENCIA_EDITOR_Utils ( ( JFrame ) getCustomComponentList ( ).get ( 0 ), ( CONSCIENCIA_EDITOR_EditorAreaDisplayManager ) getCustomComponentList ( ).get ( 1 ) )
            .open 
            ( 
                ( UNICODE_LastKnownDirectoryController ) getCustomComponentList ( ).get ( 2 ), 
                ( UNICODE_ConfigurationManager ) getCustomComponentList ( ).get ( 3 ),
                ( String ) getCustomComponentList ( ).get ( 4 )
            );
        }
        
        //delete tab/delete file ( which also removes current tab )
        if ( getMenu ( ).getButtonList ( ).get ( 3 ).contains ( mEvent.getX ( ), mEvent.getY ( ) ) )
        { 
            audioPlayer.playAudio ( "" + getAudioByAlias ( "e" ) ); 
            new CONSCIENCIA_EDITOR_Utils ( ( JFrame ) getCustomComponentList ( ).get ( 0 ), ( CONSCIENCIA_EDITOR_EditorAreaDisplayManager ) getCustomComponentList ( ).get ( 1 ) )
            .delete 
            ( 
                ( String ) getCustomComponentList ( ).get ( 4 )
            );
        }

        //compile java code in current tab
        if ( getMenu ( ).getButtonList ( ).get ( 4 ).contains ( mEvent.getX ( ), mEvent.getY ( ) ) )
        { 
            UNICODE_GuiPanel UNICODE_GuiPanel = ( UNICODE_GuiPanel ) getCustomComponentList ( ).get ( 17 );
            
            new CONSCIENCIA_EDITOR_ConsoleWindow ( "data/config/WINDOW_CONFIG.ini", UNICODE_GuiPanel );
        }
        
        
        //class diagram mode
        if ( getMenu ( ).getButtonList ( ).get ( 5 ).contains ( mEvent.getX ( ), mEvent.getY ( ) ) )
        { 
            CONSCIENCIA_EDITOR_EditorAreaDisplayManager editorAreaDisplayManager = ( CONSCIENCIA_EDITOR_EditorAreaDisplayManager ) getCustomComponentList ( ).get ( 1 );
            boolean editorModeEnquiry = ( boolean ) getCustomComponentList ( ).get ( 13 );
            audioPlayer.playAudio ( "" + getAudioByAlias ( "e" ) );
            if ( editorAreaDisplayManager.getEditorModeEnquiry ( ) )
            {
                editorAreaDisplayManager.removeAll ( );
                editorAreaDisplayManager.addClassDiagramPanel ( 20, new Color ( 255, 255, 255 ), ( String ) getCustomComponentList ( ).get ( 15 ), ( ArrayList ) getCustomComponentList ( ).get ( 12 ), editorModeEnquiry, ( String ) getCustomComponentList ( ).get ( 11 ) );
                editorAreaDisplayManager.setEditorModeEnquiry ( false );
                editorModeEnquiry = editorAreaDisplayManager.getEditorModeEnquiry ( );
            } 
            else
            {
                editorAreaDisplayManager.removeAll ( );
                CONSCIENCIA_EDITOR_ProjectParser CONSCIENCIAWriterLoader = new CONSCIENCIA_EDITOR_ProjectParser ( ( String ) getCustomComponentList ( ).get ( 4 ), "CONSCIENCIAWriter.dll", editorAreaDisplayManager, new Color ( 255, 255, 255 ) );
                editorAreaDisplayManager.setAllIcons ( );                            
                editorAreaDisplayManager.setEditorModeEnquiry ( true );
                editorModeEnquiry = editorAreaDisplayManager.getEditorModeEnquiry ( );
            }
        }
          
        //learn
        if ( getMenu ( ).getButtonList ( ).get ( 6 ).contains ( mEvent.getX ( ), mEvent.getY ( ) ) )
        { 
            CONSCIENCIA_EDITOR_EditorAreaDisplayManager editorAreaDisplayManager = ( CONSCIENCIA_EDITOR_EditorAreaDisplayManager ) getCustomComponentList ( ).get ( 1 );
            new CONSCIENCIA_EDITOR_MethodAssistanceManager 
            (
                editorAreaDisplayManager.getCurrentEditorSelection ( ), 
                editorAreaDisplayManager,
                new Color ( 255, 255, 255 ), 
                "text/java", 
                (  String ) getCustomComponentList ( ).get ( 11 )
            );
            audioPlayer.playAudio ( "" + getAudioByAlias ( "e" ) ); 
        }
        
        //website render
        if ( getMenu ( ).getButtonList ( ).get ( 7 ).contains ( mEvent.getX ( ), mEvent.getY ( ) ) )
        { 
            CONSCIENCIA_EDITOR_EditorAreaDisplayManager editorAreaDisplayManager = ( CONSCIENCIA_EDITOR_EditorAreaDisplayManager ) getCustomComponentList ( ).get ( 1 );
            boolean editorModeEnquiry = ( boolean ) getCustomComponentList ( ).get ( 13 );
            audioPlayer.playAudio ( "" + getAudioByAlias ( "e" ) );
            if ( editorAreaDisplayManager.getEditorModeEnquiry ( ) )
            {
                editorAreaDisplayManager.removeAll ( );
                editorAreaDisplayManager.addEditor ( 20, new Color ( 255, 255, 255 ), "text/java", "CONSCIENCIAWriter java videos", ( ArrayList ) getCustomComponentList ( ).get ( 16 ) );
                editorAreaDisplayManager.setEditorModeEnquiry ( false );
                editorModeEnquiry = editorAreaDisplayManager.getEditorModeEnquiry ( );
            } 
            else
            {
                editorAreaDisplayManager.removeAll ( );
                CONSCIENCIA_EDITOR_ProjectParser CONSCIENCIAWriterLoader = new CONSCIENCIA_EDITOR_ProjectParser ( ( String ) getCustomComponentList ( ).get ( 4 ), "CONSCIENCIAWriter.dll", editorAreaDisplayManager, new Color ( 255, 255, 255 )  );
                editorAreaDisplayManager.setAllIcons ( );                            
                editorAreaDisplayManager.setEditorModeEnquiry ( true );
                editorModeEnquiry = editorAreaDisplayManager.getEditorModeEnquiry ( );
            }
        }
        
        //settings menu
        if ( getMenu ( ).getButtonList ( ).get ( 8 ).contains ( mEvent.getX ( ), mEvent.getY ( ) ) )
        { 
            audioPlayer.playAudio ( "" + getAudioByAlias ( "t" ) ); 
        }
            
        //quit software
        if ( getMenu ( ).getButtonList ( ).get ( LAST_BUTTON ).contains ( mEvent.getX ( ), mEvent.getY ( ) ) )
        {
            audioPlayer.playAudio ( "" + getAudioByAlias ( "t" ) ); 
            System.exit ( 0 );
        }
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