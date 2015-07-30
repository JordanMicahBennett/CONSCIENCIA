package data.packages.CONSCIENCIA_EDITOR;

//Written by Jordan Micah Bennett copyRight bushman inc. 2012
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;
import org.w3c.dom.*;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

import data.packages.UNICODE.*;

public class CONSCIENCIA_EDITOR_EditorAreaDisplayManager extends JTabbedPane 
{
    //attributes
    private int width, height, screenHeight, screenWidth;
    private String currentTabTitleLabelSegment = "";
    private String defaultIconStream = "", selectedIconStream = "";
    private boolean editorModeEnquiry = false;
    
    //constructor
    public CONSCIENCIA_EDITOR_EditorAreaDisplayManager ( int _width, int _height, int _screenHeight, int _screenWidth, String _defaultIconStream, String _selectedIconStream )
    {
        width = _width;
        height = _height;
        screenWidth = _screenWidth;
        screenHeight = _screenHeight;
        defaultIconStream = _defaultIconStream;
        selectedIconStream = _selectedIconStream;
		
		
		setUI ( new UNICODE_BlandTabbedPaneUI ( ) );
    }
    
    //accessors

    public String getCurrentTabTitle ( )
    {
        return getTitleAt ( getSelectedIndex ( ) );
    }
    public Component getCurrentComponent ( )
    {
        return getComponentAt ( getSelectedIndex ( ) );
    }
    public JEditorPane getCurrentEditorPanel ( )
    {
        CONSCIENCIA_EDITOR_EditorArea editorArea = ( CONSCIENCIA_EDITOR_EditorArea ) getCurrentComponent ( );
        return editorArea.getEditorPanel ( );
    }
    public String getCurrentEditorSelection ( )
    {
        CONSCIENCIA_EDITOR_EditorArea editorArea = ( CONSCIENCIA_EDITOR_EditorArea ) getCurrentComponent ( );
        return editorArea.getCurrentSelection ( );
    }
    public String getCurrentEditorText ( )
    {
        CONSCIENCIA_EDITOR_EditorArea editorArea = ( CONSCIENCIA_EDITOR_EditorArea ) getCurrentComponent ( );
        return editorArea.getText ( );
    }
    public String getCurrentEditorClassName ( )
    {
        CONSCIENCIA_EDITOR_EditorArea editorArea = ( CONSCIENCIA_EDITOR_EditorArea ) getCurrentComponent ( );
        return editorArea.getCurrentlySelectedClassName ( );
    }
    public Point getCurrentCaretLocation ( )
    {
        CONSCIENCIA_EDITOR_EditorArea editorArea = ( CONSCIENCIA_EDITOR_EditorArea ) getCurrentComponent ( );
        return editorArea.getCurrentCaretLocation ( );
    }
    
    public String getCurrentEditorMethodString ( )
    {
        CONSCIENCIA_EDITOR_EditorArea editorArea = ( CONSCIENCIA_EDITOR_EditorArea ) getCurrentComponent ( );
        return editorArea.getDerivedMethodString ( );
    }
    public void append ( String input )
    {
        try
        {
            getCurrentEditorPanel ( ).getDocument ( ).insertString ( getCurrentEditorPanel ( ).getDocument ( ).getLength ( ), input, null );
        }
        catch ( Exception error ) { } 
    }
    
    public boolean getEditorModeEnquiry ( )
    {
        return editorModeEnquiry;
    }
    
    //dynamic mutators
    public void setEditorModeEnquiry ( boolean value )
    {
        editorModeEnquiry = value;
    }
    
    public void initializeTabIconUpdateListener ( )
    {
        addChangeListener ( getStateChangeListening ( ) );
    }
    
    //utils
    
    public void setCurrentTabIcon ( int index, String iconStream )
    {
        Icon icon = new ImageIcon ( iconStream );
        setIconAt ( index, icon );
    }
    
    public void setAllIcons ( )
    {
        for ( int i = 0; i < getTabCount ( ); i ++ )
            setCurrentTabIcon ( i, defaultIconStream );
    }
    
    public void removeCurrentTab ( )
    {
        remove ( getSelectedIndex ( ) );
    }
    
    //add editor to tabbed pane.
    //ADD EDITOR SCENARIOS: THE FOLLOWING NUMBER LABEL OF SCENARIOS BELOW MAY BE PASSED IN ConscienciaTabCreationMode param, to control how 
    //0.Consciencia startup with no project; TITLE = default auto incrementing tab name, CONTENT = prebaked default sample class code
    //1.Consciencia in a project; TITLE = inherited file name, CONTENT = inherited file data
    //2.Consciencia in a project, new file; TITLE = default auto incrementing tab name, CONTENT = prebaked default sample class code
    //3.Consciencia in a project, learn button (auto-gen-2-tabs); TITLE1 = Standard Class Info, CONTENT = Standard Class Infos                                                            //TITLE2 = Examples, CONTENT = Examples
    public void addEditor ( int yStart, Color bgColour, String editorContentType, String customTitle, String customContent, int ConscienciaTabCreationMode )
    {
        setEditorModeEnquiry ( true );
        
        setBackground ( bgColour );
        
        CONSCIENCIA_EDITOR_EditorArea newEditorArea = new CONSCIENCIA_EDITOR_EditorArea ( width, height, screenWidth, screenHeight );
        
        //CONTROL HOW A TAB IS APENDED!!!
        String tabTitle = null, tabContent = null;
        
        switch ( ConscienciaTabCreationMode )
        {
            //0.Consciencia startup with no project; TITLE = default auto incrementing tab name, CONTENT = prebaked default sample class code
            case 0:
            {
                tabTitle = "Consciencia Untitled File : " + getTabCount ( );
                tabContent = newEditorArea.getSampleStartupContent ( );
            }
            break;
            
            //1.Consciencia in a project; TITLE = inherited file name, CONTENT = inherited file data
            case 1:
            {
                tabTitle = customTitle;
                tabContent = customContent;
            }
            break;
        }
        
        newEditorArea.establish ( null, editorContentType, tabContent, bgColour );
        
        add ( tabTitle, newEditorArea );
        
        setPreferredSize ( new Dimension ( width, height ) );
        
        setLocation ( screenWidth/2 - width/2, yStart );
    }
    
    //second signature which allows data from 3d array list and an index indicating page  from which information is currently being extracted.
    public void addEditor ( int yStart, Color bgColour, String editorContentType, String customTitle, ArrayList [ ] content, int currentFileIndex, int ConscienciaTabCreationMode )
    {
        setEditorModeEnquiry ( true );
        
        setBackground ( bgColour );
        
        CONSCIENCIA_EDITOR_EditorArea newEditorArea = new CONSCIENCIA_EDITOR_EditorArea ( width, height, screenWidth, screenHeight );
        
        //CONTROL HOW A TAB IS APENDED!!!
        String tabTitle = null, tabContent = null;
        
        switch ( ConscienciaTabCreationMode )
        {
            //0.Consciencia startup with no project; TITLE = default auto incrementing tab name, CONTENT = prebaked default sample class code
            case 0:
            {
                tabTitle = "Consciencia Untitled File : " + getTabCount ( );
                tabContent = newEditorArea.getSampleStartupContent ( );
            }
            break;
            
            //1.Consciencia in a project; TITLE = inherited file name, CONTENT = inherited file data
            case 1:
            {
                tabTitle = customTitle;
                
                //generate custom content
                String customContent = "";
                for ( int j = 0; j < content [ currentFileIndex ].size ( ); j ++ )
                    customContent += content [ currentFileIndex ].get ( j ) + "\n";
                        
                tabContent = customContent;
            }
            break;
        }
        
        newEditorArea.establish ( null, editorContentType, tabContent, bgColour );
        
        add ( tabTitle, newEditorArea );
        
        setPreferredSize ( new Dimension ( width, height ) );
        
        setLocation ( screenWidth/2 - width/2, yStart );
    }
    
    //html
    //second signature which allows data from 3d array list and an index indicating page  from which information is currently being extracted.
    public void addEditor ( int yStart, Color bgColour, String editorContentType, String customTitle, ArrayList content )
    {
        setEditorModeEnquiry ( true );
        
        setBackground ( bgColour );
        
        CONSCIENCIA_EDITOR_EditorArea newEditorArea = new CONSCIENCIA_EDITOR_EditorArea ( width, height, screenWidth, screenHeight );
        
        //CONTROL HOW A TAB IS APENDED!!!
        String tabTitle = null, tabContent = null;

        tabTitle = customTitle;
        
        //generate custom content
        String customContent = "";
        for ( int j = 0; j < content.size ( ); j ++ )
            customContent += content.get ( j ) + "\n";
                
        tabContent = customContent;
        
        newEditorArea.establish ( null, editorContentType, "", bgColour );
  
        add ( tabTitle, newEditorArea );
        
        setPreferredSize ( new Dimension ( width, height ) );
        
        setLocation ( screenWidth/2 - width/2, yStart );
    }
    
    //add class diagram panel 
    public void addClassDiagramPanel ( int yStart, Color bgColour, String projectName, ArrayList projectFileNamesList, boolean latestEditorModeEnquiry, String regexUsageAnswer )
    {
        setBackground ( bgColour );

        CONSCIENCIA_EDITOR_ClassDiagramPanel classDiagramPanel = new CONSCIENCIA_EDITOR_ClassDiagramPanel ( projectFileNamesList, latestEditorModeEnquiry, regexUsageAnswer );
        
        add ( "Class Diagram =>> " + projectName, classDiagramPanel );
        
        setPreferredSize ( new Dimension ( width, height ) );
        
        setLocation ( screenWidth/2 - width/2, yStart );
    }

    
    public ChangeListener getStateChangeListening ( )
    {
        return new ChangeListener ( )
        {
            public void stateChanged ( ChangeEvent changeEvent )
            {
                //reet all icons
                setAllIcons ( );
                //set new icon image
                setCurrentTabIcon ( getSelectedIndex ( ), selectedIconStream );
            }
        };
    }
}
