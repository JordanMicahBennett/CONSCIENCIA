package data.packages.CONSCIENCIA_EDITOR;

//Written by Jordan Micah Bennett copyRight uni-code(tm) inc. 2012
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import javax.swing.JOptionPane;

public class CONSCIENCIA_EDITOR_NewProjectCreator
{
    //attributes
    private String projectName = "", projectAuthor = "";
    private String projectLocation = "";
    private String sampleStartupContent = "";
    
    public CONSCIENCIA_EDITOR_NewProjectCreator ( String _projectLocation )
    {
        //establish project name
        projectName = JOptionPane.showInputDialog ( "Enter project name : " );
        
        //etablish project author 
        projectAuthor = JOptionPane.showInputDialog ( "Enter project author ( s ) : " );
        
        //establish project location
        projectLocation = _projectLocation;
        
        //establish project sample content
        sampleStartupContent = "////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// \n//Project Author ( s ) : " + projectAuthor + "\n//Project : " + projectName + "\n//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////" +  "\n\n" + "import data.kits.UNICODE.*;\n\npublic class ConscienciaWriter\n{\n\tpublic ConscienciaWriter ()\n\t{\n\t}\n}";
        
        //generate the project
        generateEmptyProject ( );
    }
    
    //utils
    public void generateEmptyProject ( )
    {
        //create file instance wrt location supplied
        File project = new File ( projectLocation + "/" + projectName );
        
        //create folder
        project.mkdirs ( );
        
        //generate project core file
        generateProjectCoreFile ( );
        
        //generate sample code
        generateSampleFile ( );
    }
    
    
    public String getNewDirectory ( )
    {
        return projectLocation + "/" + projectName;
    }
    
    //generate project core file
    public void generateProjectCoreFile ( )
    {
        try
        {
            PrintWriter printWriter = new PrintWriter ( new FileWriter ( projectLocation + "/" + projectName + "/consciencia.dll" ) );
            printWriter.println ( projectName + "::" + projectAuthor );
            printWriter.close ( );
        }
        catch ( Exception error ) { }
    }
    
    //generate sample file
    public void generateSampleFile ( )
    {
        try
        {
            PrintWriter printWriter = new PrintWriter ( new FileWriter ( projectLocation + "/" + projectName + "/sampleClass.java" ) );
            printWriter.println ( sampleStartupContent );
            printWriter.close ( );
        }
        catch ( Exception error ) { }
    }
}
