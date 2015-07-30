package data.packages.CONSCIENCIA_EDITOR;

//just returns formatted method list, via ReflectionManager
import java.awt.Color;
import data.packages.UNICODE.*;

public class CONSCIENCIA_EDITOR_MethodAssistanceManager
{
    //attributes
    private CONSCIENCIA_EDITOR_EditorAreaDisplayManager ConscienciaWriterEditorAreaDisplayManager = null;
    private String derivedContent = "";
    private String keyWord = "";
    private UNICODE_ConveniencePack conveniencePack = new UNICODE_ConveniencePack ( );
    private String regexUsageAnswer = "";
    
    public CONSCIENCIA_EDITOR_MethodAssistanceManager ( String _keyWord, CONSCIENCIA_EDITOR_EditorAreaDisplayManager _ConscienciaWriterEditorAreaDisplayManager, Color bgColour, String editorContentType, String _regexUsageAnswer)
    {   
        //establish keyword
        keyWord = _keyWord;
        
        //establish editor area container
        ConscienciaWriterEditorAreaDisplayManager = _ConscienciaWriterEditorAreaDisplayManager;
        
        //establish regex usage answer
        regexUsageAnswer = _regexUsageAnswer;
        
        //load derived content
        loadDerivedContent ( );
        
        //generate tabs
        generateAssistance ( bgColour, editorContentType );
    }
    //constructor 2
    public CONSCIENCIA_EDITOR_MethodAssistanceManager ( String _keyWord, String _regexUsageAnswer )
    {   
        //establish keyword
        keyWord = _keyWord;

        //establish regex usage answer
        regexUsageAnswer = _regexUsageAnswer;
        
        //load derived content
        loadDerivedContent ( );
    }
    //accessors
    public String getDerivedContent ( )
    {
        return derivedContent;
    }
    
    //utils
    public void loadDerivedContent ( )
    {
        //establish eflect manager
        CONSCIENCIA_EDITOR_ReflectionsManager ConscienciaWriterReflectionsManager = new CONSCIENCIA_EDITOR_ReflectionsManager ( keyWord, regexUsageAnswer );
        //get data from reflect manager
        for ( int i = 0; i < ConscienciaWriterReflectionsManager.getMethods ( ).length; i ++ )
            derivedContent += ConscienciaWriterReflectionsManager.getMethods ( ) [ i ] + "\n";
    }
    
    public void generateAssistance ( Color bgColour, String editorContentType )
    {
        ConscienciaWriterEditorAreaDisplayManager.addEditor ( 20, bgColour, editorContentType, "(Spy) Data Type : " + keyWord, derivedContent, 1 );
        ConscienciaWriterEditorAreaDisplayManager.setSelectedIndex ( ConscienciaWriterEditorAreaDisplayManager.indexOfTab ( "(Spy) Data Type : " + keyWord ) );
    }
}
