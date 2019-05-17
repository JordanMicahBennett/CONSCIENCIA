package data.packages.CONSCIENCIA_EDITOR;

//Written by Jordan Micah Bennett copyRight uni-code(tm) inc. 2012
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.util.Scanner;
import data.packages.UNICODE.*;


public class CONSCIENCIA_EDITOR_Utils
{
    //attributes
    private CONSCIENCIA_EDITOR_EditorAreaDisplayManager editorAreaDisplayManager = null;
    private JFrame applicationFrame = null;
    private int editorStartY;
    private String editorContentType = "";
    private Color editorBgColour = null;
    private UNICODE_ConveniencePack conveniencePack = new UNICODE_ConveniencePack ( );
    private UNICODE_DirectoryEditor directoryEditor = new UNICODE_DirectoryEditor ( );

    //constructor
    public CONSCIENCIA_EDITOR_Utils ( JFrame _applicationFrame, CONSCIENCIA_EDITOR_EditorAreaDisplayManager _editorAreaDisplayManager, int _editorStartY, String _editorContentType, Color _editorBgColour )
    {
        //establish editor area display manager
        editorAreaDisplayManager = _editorAreaDisplayManager;
        
        //establish app frame
        applicationFrame = _applicationFrame;
        
        //establish other stuff
        editorContentType = _editorContentType;
        editorStartY = _editorStartY;
        editorBgColour = _editorBgColour;
    }
    
    //constructor 2 - access generic functions such as showMessage
    public CONSCIENCIA_EDITOR_Utils ( JFrame _applicationFrame )
    {
        applicationFrame = _applicationFrame;
    }
    
    //constructor 3 - access delete function
    public CONSCIENCIA_EDITOR_Utils ( JFrame _applicationFrame, CONSCIENCIA_EDITOR_EditorAreaDisplayManager _editorAreaDisplayManager )
    {
        applicationFrame = _applicationFrame;
        editorAreaDisplayManager = _editorAreaDisplayManager;
    }
    
    //constructor 4 - build function ..or other functions that only require an active/valid 'CONSCIENCIA_EDITOR_EditorAreaDisplayManager' instance.
    public CONSCIENCIA_EDITOR_Utils (  CONSCIENCIA_EDITOR_EditorAreaDisplayManager _editorAreaDisplayManager )
    {
    }
    
    //constructor 5 - generic (no params required)
    public CONSCIENCIA_EDITOR_Utils ( )
    {
    }
    
    
    //utils
    public void save ( final String inputStream, final String fileName )
    {
        Thread overwriteFileThread = new Thread 
        (
            new Runnable ( )
            {
                public void run ( )
                {
                    try
                    {
                        PrintWriter pw = new PrintWriter ( new FileWriter ( inputStream + "/" + fileName ) );
                        pw.print ( editorAreaDisplayManager.getCurrentEditorText ( ) );
                        pw.close ( );
                    }
                    catch ( Exception error ) { }
                }
            }
        );
                
        Thread saveFileThread = new Thread 
        (
            new Runnable ( )
            {
                public void run ( )
                {
                    String suppliedName = JOptionPane.showInputDialog ( "Enter file name : " );
                    String name =  inputStream + "/" + suppliedName;
                    try
                    {
                        name = name.replace ( ".speech", "" ) + ".speech";
                        
                        PrintWriter pw = new PrintWriter ( new FileWriter ( name ) );
                        pw.print ( editorAreaDisplayManager.getCurrentEditorText ( ) );
                        pw.close ( );   
                    }
                    catch ( Exception error ) { }
                    
                    //add editor to reflect newly saved file.
                    editorAreaDisplayManager.addEditor ( editorStartY, editorBgColour, editorContentType, suppliedName, editorAreaDisplayManager.getCurrentEditorText ( ), 1 );
                }
            }
        );
        new CONSCIENCIA_EDITOR_MessageBoxWindow ( overwriteFileThread, saveFileThread, 0.9f, new Color ( 255, 255, 255 ), new Color ( 255, 255, 255 ), true, "data/images/all/message box/save/" );
    }
    
    
    public void delete ( final String directory )
    {
        Thread deleteTabThread = new Thread 
        (
            new Runnable ( )
            {
                public void run ( )
                {
                    editorAreaDisplayManager.removeCurrentTab ( );
                }
            }
            
        );
        
        Thread deleteFileThread = new Thread 
        (
            new Runnable ( )
            {
                public void run ( )
                {
                    directoryEditor.removeFolder ( conveniencePack.makeFile ( directory + "/" + editorAreaDisplayManager.getCurrentTabTitle ( ) ) );
                    editorAreaDisplayManager.removeCurrentTab ( );
                }
            }
        );
        new CONSCIENCIA_EDITOR_MessageBoxWindow ( deleteTabThread, deleteFileThread, 0.9f, new Color ( 255, 255, 255 ), new Color ( 255, 255, 255 ), true, "data/images/all/message box/delete/" );
    }
    
    public void open ( final UNICODE_LastKnownDirectoryController lastKnownDirectoryController, final  UNICODE_ConfigurationManager configurationManager, final String lastKnownDirectory )
    {
        Thread openProjectThread = new Thread 
        (
            new Runnable ( )
            {
                public void run ( )
                {
                    UNICODE_FileSelection oldProjectFileOpener = new UNICODE_FileSelection ( "C:" );
                    oldProjectFileOpener.initDirectorySearch ( );
                    lastKnownDirectoryController.setLastKnownDirectory ( oldProjectFileOpener.getDirectory ( ) );
                    configurationManager.updateLastKnownDirectory ( lastKnownDirectoryController ); 
                    editorAreaDisplayManager.removeAll ( );
                    new CONSCIENCIA_EDITOR_ProjectParser ( oldProjectFileOpener.getDirectory ( ), "consciencia.dll", editorAreaDisplayManager, new Color ( 255, 255, 255 ) );
                     new UNICODE_MessageBoxWindow ( true, "Project parsing successful", 0.9f, new Color ( 255, 255, 255 ), new Color ( 255, 255, 255 ), new Color ( 255, 255, 255 ), new Color ( 0, 0, 0 ), true, "data/images/all/message box/okay/","rr", 70, 70, 10, 10, 0, 0 ); 
                    editorAreaDisplayManager.setAllIcons ( );
                }
            }
        );
        
        Thread openFileThread = new Thread 
        (
            new Runnable ( )
            {
                public void run ( )
                {
                    UNICODE_FileSelection currentProjectFileOpener = new UNICODE_FileSelection ( lastKnownDirectory );
                    currentProjectFileOpener.initFileSearch ( applicationFrame );
                    //if file is already opened, then direct user to that tab 
                    if ( fileAlreadyOpenedEnquiry ( currentProjectFileOpener.getSelectedFile ( ) ) )
                        editorAreaDisplayManager.setSelectedIndex ( editorAreaDisplayManager.indexOfTab ( currentProjectFileOpener.getSelectedFile ( ) ) );
                    else //else add a new tab in CONSCIENCIA_EDITOR with the selected file as the tab's title.
                        editorAreaDisplayManager.addEditor ( 20, new Color ( 255, 255, 255 ), "text/java", currentProjectFileOpener.getSelectedFile ( ), getFileContentFromFileInCurrentProject ( currentProjectFileOpener.getDirectory ( ) ), 1 );
                }
            }
        );
       new CONSCIENCIA_EDITOR_MessageBoxWindow ( openProjectThread, openFileThread, 0.9f, new Color ( 255, 255, 255 ), new Color ( 255, 255, 255 ), true, "data/images/all/message box/open/" );
    }
    
    public void newItem ( final UNICODE_LastKnownDirectoryController lastKnownDirectoryController, final UNICODE_ConfigurationManager configurationManager, final String lastKnownDirectory )
    {
        Thread newProjectThread = new Thread 
        (
            new Runnable ( )
            {
                public void run ( )
                {
                    UNICODE_FileSelection newProjectFileCreator = new UNICODE_FileSelection ( "C:" );
                    newProjectFileCreator.initDirectorySearch ( );
                    CONSCIENCIA_EDITOR_NewProjectCreator newProjectCreator = new CONSCIENCIA_EDITOR_NewProjectCreator ( newProjectFileCreator.getDirectory ( ) );
                    lastKnownDirectoryController.setLastKnownDirectory ( newProjectCreator.getNewDirectory ( ) );
                    configurationManager.updateLastKnownDirectory ( lastKnownDirectoryController );
                    editorAreaDisplayManager.removeAll ( );
                    new CONSCIENCIA_EDITOR_ProjectParser ( lastKnownDirectory, "consciencia.dll", editorAreaDisplayManager, new Color ( 255, 255, 255 ) );
                    editorAreaDisplayManager.setAllIcons ( );
                }
            }
        );
        
        Thread newFileThread = new Thread 
        (
            new Runnable ( )
            {
                public void run ( )
                {
                    editorAreaDisplayManager.addEditor ( 20, new Color ( 255, 255, 255 ), "text/java", null, null, 0 );
                }
            }
        );
        
       new CONSCIENCIA_EDITOR_MessageBoxWindow ( newProjectThread, newFileThread, 0.9f, new Color ( 255, 255, 255 ), new Color ( 255, 255, 255 ), true, "data/images/all/message box/new/" );
    }
    
    public void showMessage ( String message, String title, String iconType )
    {
        conveniencePack.displayMessage ( applicationFrame, message, title, iconType );
    }
    
    public String getFileContentFromFileInCurrentProject ( String fileStream )
    {
        String content = "";
        try
        {
            Scanner scanner = new Scanner ( new File ( fileStream ) );
            while ( scanner.hasNext ( ) )
                content += "\n" + scanner.nextLine ( );
            scanner.close ( );
        }
        catch ( Exception error ) { }
        return content;
    }
    
    public void build ( String projectLocation, String javacStream, String spaceSymbol, String javaFileName, int compilerWindowHeight, int compilerWindowWidth )
    {
        //Compile and run
        //new UNICODE_Compiler ( ).compileAndRun ( "data/template/compilationTemplate.ini", javacStream, spaceSymbol, projectLocation + "/", javaFileName.replace ( ".java", "" ), "compilation", compilerWindowHeight, compilerWindowWidth, "F8", "Black" );
    }
    
    public boolean fileAlreadyOpenedEnquiry ( String targetFileName )
    {
        boolean flag = false;
        for ( int i = 0; i < editorAreaDisplayManager.getTabCount ( ); i ++ )
            if ( targetFileName.equals ( editorAreaDisplayManager.getTitleAt ( i ) ) )
                flag = true;
        return flag;
    }
}
