package data.packages.CONSCIENCIA_EDITOR;

//Written by Jordan Micah Bennett copyRight uni-code(tm) inc. 2012
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.awt.Color;
import data.packages.UNICODE.*;

public class CONSCIENCIA_EDITOR_ProjectParser
{
    //attributes
    private UNICODE_ConveniencePack conveniencePack = new UNICODE_ConveniencePack ( );
    private File projectDirectory = null;
    private String coreProjectFileName = null;
    private File coreProjectFile = null;
    private ArrayList projectFileNamesList = new ArrayList ( );
    private ArrayList [ ] projectFileContents = null;
    private int maxProjectFiles = 0;
    private boolean projectExistenceEnquiry = false;
    private String projectLocation = "";
    //constructor
    public CONSCIENCIA_EDITOR_ProjectParser ( String _projectLocation, String _coreProjectFileName, CONSCIENCIA_EDITOR_EditorAreaDisplayManager ConscienciaWriterEditorAreaDisplayManager, Color bgColour )
    {
        //establish location
        projectLocation = _projectLocation;
        
        //establish core project file name
        coreProjectFileName = _coreProjectFileName;
        
        //establish project file name list
        projectFileNamesList = getProjectFileNamesList ( );
        
        //establish project file contents
        projectFileContents = getProjectDirectoryFileListContent ( );
        
        //establish maimum project files
        maxProjectFiles = projectFileNamesList.size ( );
        
        //loadProjectFiles
        loadProjectFiles ( ConscienciaWriterEditorAreaDisplayManager, bgColour );
    }

    //accessors
    public boolean getProjectExistenceEnquiry ( )
    {
        return projectExistenceEnquiry;
    }
    public String getProjectLocation ( )
    {
        return projectLocation;
    }

    //mutators
    
    ////////////////////////
    //utils
    ////////////////////////
    //gets a name list of project files of type java
    public ArrayList getProjectFileNamesList ( )
    {
        ArrayList value = new ArrayList ( );
        
        //establish core projec file
        File coreProjectFile = new File ( projectLocation + "/" + coreProjectFileName );

        //only generate variable list if a file by the name of the 'coreProjectFileName' exists
        if ( coreProjectFile.exists ( ) )
        {
            //establish project directory
            projectDirectory = new File ( projectLocation );
            
            //pass directory list to a temporary location
            String [ ] listOfDetectedFiles = projectDirectory.list ( );
           
            //ttransfer only .java datas to this list variable
            for ( int i = 0; i  < listOfDetectedFiles.length; i ++ )
            {
                String fileItem = listOfDetectedFiles [ i ];
                
                if ( fileItem.endsWith ( ".cog" ) && !conveniencePack.stringSubsetEnquiry ( fileItem, "__SHELL" ) )
                    value.add ( fileItem );
            }
        }
        
        //tell whether file exists 
        projectExistenceEnquiry = coreProjectFile.exists ( );
        
        return value;
    }
    
    //gets all the contents wrt to all file names
    public ArrayList [ ] getProjectDirectoryFileListContent ( )
    {
        ArrayList value [ ] = new ArrayList [ projectFileNamesList.size ( ) ];
        
        for ( int i = 0; i < projectFileNamesList.size ( ); i ++ )
        {
            value [ i ] = new ArrayList ( ); //establish Array List for each file name 

            try
            {
                Scanner scanner = new Scanner ( new File ( ( String ) projectLocation + "/" + projectFileNamesList.get ( i ) ) );
  
                while ( scanner.hasNext ( ) )
                    value [ i ].add ( scanner.nextLine ( ) ); 
     
                scanner.close ( );
            }
            catch ( Exception error ) { }
        }
        
        return value;
    }
    
    public void loadProjectFiles ( CONSCIENCIA_EDITOR_EditorAreaDisplayManager ConscienciaWriterEditorAreaDisplayManager, Color bgColour )
    {
        for ( int i = 0; i < maxProjectFiles; i ++ )
            ConscienciaWriterEditorAreaDisplayManager.addEditor ( 20, bgColour, "text/java", ( String ) projectFileNamesList.get ( i ), projectFileContents, i, 1 );
    }
    
    public String getProjectName ( )
    {
        String value = "";
        
        String line = "";
        try
        {
            Scanner scanner = new Scanner ( new File ( projectLocation + "/cas.dll" ) );
            
            while ( scanner.hasNext ( ) )
                line = scanner.nextLine ( );
 
            scanner.close ( );
        }
        catch ( Exception error ) { }
        
        //establish value
        value = conveniencePack.getDelimitedArray ( line, "", "::", 2 ) [ 0 ];
        
        return value;
    }
    
    public String getProjectAuthor ( )
    {
        String value = "";
        
        String line = "";
        try
        {
            Scanner scanner = new Scanner ( new File ( projectLocation + "/project.ini" ) );
            
            while ( scanner.hasNext ( ) )
                line = scanner.nextLine ( );
 
            scanner.close ( );
        }
        catch ( Exception error ) { }
        
        //establish value
        value = conveniencePack.getDelimitedArray ( line, "", "::", 2 ) [ 1 ];
        
        return value;
    }
    
    
    //uses a list of file names and contents, and spits them back out with different names, and contents, with a spplied pattern.
    //made because i was too smart to manually rename some files i want to rename, while manintaining their contents.
    public CONSCIENCIA_EDITOR_ProjectParser ( )
    {
        
    }
    ///////////////PART A - File List based on supplied dir.
    public ArrayList getSpitList ( String fileType, String folderOrigin )
    {
        ArrayList value = new ArrayList ( );
   
        //establish project directory
        File directory = new File ( folderOrigin );
        
        //pass directory list to a temporary location
        String [ ] listOfDetectedFiles = directory.list ( );
       
        //ttransfer only .java datas to this list variable
        for ( int i = 0; i  < listOfDetectedFiles.length; i ++ )
        {
            String line = listOfDetectedFiles [ i ];
                if ( line.endsWith ( fileType ) )
                    value.add ( line );
        }
            
        return value;
    }
    ////////PART B - File Contents wrt list above
    public ArrayList [ ] getSpitListFileContents ( String filesType, String tag, String tagReplacement, String folderOrigin )
    {
        ArrayList value [ ] = new ArrayList [ getSpitList ( filesType, folderOrigin ).size ( ) ];
        
        for ( int i = 0; i < getSpitList ( filesType, folderOrigin ).size ( ); i ++ )
        {
            value [ i ] = new ArrayList ( ); //establish Array List for each file name 

            try
            {
                Scanner scanner = new Scanner ( new File ( folderOrigin + getSpitList ( filesType, folderOrigin ).get ( i ) ) );
  
                while ( scanner.hasNext ( ) )
                {
                    String line = scanner.nextLine ( );
                    value [ i ].add ( line.replace ( tag, tagReplacement ) ); 
                }
     
                scanner.close ( );
            }
            catch ( Exception error ) { }
        }
        
        return value;
    }
    ///////////////PART C 
    public void spitThem ( String tag, String tagReplacement, String filesType, String folderOrigin, String folderDestination )
    {
        ArrayList value [ ] = getSpitListFileContents ( filesType, tag, tagReplacement, folderOrigin );

        for ( int i = 0; i < getSpitList ( filesType, folderOrigin ).size ( ); i ++ )
        {
            //A little low level interface indication { pulsating elipses :) }
            System.out.print ( periodAnimation ( i, 4, "." ) );
            try
            {
                String name = ( String ) getSpitList ( filesType, folderOrigin ).get ( i );
                PrintWriter pw = new PrintWriter ( new FileWriter ( folderDestination + name.replace ( tag, tagReplacement ) ) );
                
                for ( int j = 0;  j < value [ i ].size ( ); j ++ )
                    pw.println ( value [ i ].get ( j ) );
   
                pw.close ( );
            }
            catch ( Exception error ) { }
        }
        
        System.out.println ( "Spit complete!" );
        System.out.println ( "Spat : " + getSpitList ( filesType, folderOrigin ).size ( ) + " " + filesType + " files!" );
        System.out.println ( "Powered by unicode(tm) engine COPYRIGHT (2012)" );
    }
    //graphical indicators
    public String periodAnimation ( int animationIndex, int animationSymbolCardinality, String animationSymbol )
    {
        String value = "";
            if ( animationIndex % animationSymbolCardinality == 0 ) //if the cycle index has reached the limit specified in param, then clear screen.
                System.out.println ( "\f" );
            else
                value += animationSymbol; //else build animation value.
       
        return value;
    }
}
