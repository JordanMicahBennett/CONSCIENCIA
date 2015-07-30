package data.packages.CONSCIENCIA_EDITOR;

//Written by Jordan Micah Bennett copyRight uni-code(tm) inc. 2012
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.undo.UndoManager;
import java.awt.Color;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import jsyntaxpane.DefaultSyntaxKit;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Point;
import javax.swing.SwingUtilities;
import data.packages.UNICODE.*;

public class CONSCIENCIA_EDITOR_EditorArea extends JScrollPane
{
    //attributes
    ////////////////////////////////////////////////////////////////////////
    private JEditorPane editorPanel = new JEditorPane ( );
    private int width, height, screenHeight, screenWidth;
    private UNICODE_ConveniencePack conveniencePack = new UNICODE_ConveniencePack ( );
    private String sampleStartupContent = conveniencePack.getFileContent ( "data/template/ConscienciaWriterCodeTemplate.ini" );
   
    
    ////////////////////////////////////////////////////////////////////////
    //constructor
    public CONSCIENCIA_EDITOR_EditorArea ( int _width, int _height, int _screenWidth, int _screenHeight )
    {
        width = _width;
        height = _height;
        screenWidth = _screenWidth;
        screenHeight = _screenHeight;
        editorPanel.setBackground ( Color.gray );
		
        setViewportView ( editorPanel ); //Importante!
        DefaultSyntaxKit.initKit ( );
    }
    
    //accessors
    public String getSampleStartupContent ( )
    {
        return sampleStartupContent;
    }
    
    public String getText ( )
    {
        return editorPanel.getText ( );
    }
    
    public JEditorPane getEditorPanel ( )
    {
        return editorPanel;
    }
    
    public String getCurrentSelection ( )
    {
        int selectionStart = editorPanel.getSelectionStart ( );
        int selectionEnd = editorPanel.getSelectionEnd ( );
        return editorPanel.getText ( ).substring ( selectionStart, selectionEnd );
    }
    
    //What this function does is first collects class names (which is passed as a key word to reflectionManager to derive avilable methods) and instances, and compares any current selection in editor pane, to that of any 
    //instance discovered, according to a regex pattern i established. It is strict; only Classes declared as: Class instance = new Class ( ); will be detected as valid classes.
    //More allowances will be infactored to detect more appropriate patterns.
    public String getCurrentlySelectedClassName ( )
    {
        String value = "";
        
        
        String content = getText ( );
        Scanner scanner = new Scanner ( content );
        ArrayList segmentedContent = new ArrayList ( );
        ArrayList discoveredInstances = new ArrayList ( );
        ArrayList discoveredClassPack = new ArrayList ( );
        
        //possible patterns guidlines
        //0.JPanel panel = new JPanel ( );
        //1.int integer = 10;
        
        while ( scanner.hasNext ( ) )
            segmentedContent.add ( scanner.nextLine ( ) );
            
  
        //regex shit
        //bushman class pattern 0 = class instance = new class ( );
        //regex representation = term space term space punctuation space term space term space punctuation space punctuation
        Pattern p0 = Pattern.compile ( "(.*)(\\s+)(.*)(\\s+)(\\p{Punct})(\\s+)(.*)(\\s+)(.*)(\\s+)(\\p{Punct})(\\s+)(\\p{Punct})(\\p{Punct})");
                            //PATTERN1: [1]  [2]  [3] [4]      [5]       [6]  [7] [8]   [9] [10]    [11]      [12]     [13]        [14]    
                            //[1]=ClassName
                            //[3]=instance
                            //[5]='='
                            //[7]='new'
                            //[9]=ClassName
                            //[11]='('
                            //[13]=')'
                            //[14]=';'
                            
        //bushman class pattern 1 = Class instance = value;                  
        Pattern p1 = Pattern.compile ( "(.*)(\\s+)(.*)(\\s+)(\\p{Punct})(\\s+)(.*)(\\p{Punct})");    
                            //PATTERN1:  [1]  [2]  [3]  [4]      [5]     [6]   [7]     [8]  
                            //[1]=ClassName
                            //[3]=instance
                            //[5]='='
                            //[7]=value
                            //[8]=';'
        String className = "", classInstance = "";
        
        //this block really determines what classes and instances are added to the queue of invocable shit.
        for ( int i = 0; i < segmentedContent.size ( ); i ++ )
        {
            Matcher m0 = p0.matcher ( ( String ) segmentedContent.get ( i ) );
            Matcher m1 = p1.matcher ( ( String ) segmentedContent.get ( i ) );
            if ( m0.find ( ) )
            {
                //Strict conditions
                if 
                (
                    //1.Ensure that in "Class instance = new Class ( );", the term @ 'Class' postion is indeed 'Class'
                    ( m0.replaceAll ( "$1" ).equals ( m0.replaceAll ( "$9" ) ) )
                    &&
                    //2.Ensure that in "Class instance = new Class ( );"; the term @ '=' postion is indeed '='
                    ( m0.replaceAll ( "$5" ).equals ( "=" ) )
                    &&
                    //3.Ensure that in "Class instance = new Class ( );"; the term @ ';' postion is indeed ';'
                    ( m0.replaceAll ( "$14" ).equals ( ";" ) )
                )
                {
                    discoveredClassPack.add ( m0.replaceAll ( "$1" ) );
                    discoveredInstances.add ( m0.replaceAll ( "$3" ) );
                }
            }
            else if ( m1.find ( ) )
            {
                //Strict conditions
                if 
                (
                    //1.Ensure that in "Class instance = new Class ( );"; the term @ '=' postion is indeed '='
                    ( m1.replaceAll ( "$5" ).equals ( "=" ) )
                    &&
                    //2.Ensure that in "Class instance = new Class ( );"; the term @ ';' postion is indeed ';'
                    ( m1.replaceAll ( "$8" ).equals ( ";" ) )
                )
                {
                    discoveredClassPack.add ( m1.replaceAll ( "$1" ) );
                    discoveredInstances.add ( m1.replaceAll ( "$3" ) );
                }
            }
        }
  
         for ( int i = 0; i < discoveredInstances.size ( ); i ++ )
            if ( getCurrentSelection ( ).equals ( ( String ) discoveredInstances.get ( i ) ) )
                value = ( String ) discoveredClassPack.get ( i );
                
        return value;
    }

    public String getDerivedMethodString ( )
    {
        CONSCIENCIA_EDITOR_MethodAssistanceManager methodAssistanceManager = new CONSCIENCIA_EDITOR_MethodAssistanceManager ( getCurrentlySelectedClassName ( ), "yes" );
        return methodAssistanceManager.getDerivedContent ( );
    }
    
    public Point getCurrentCaretLocation ( )
    {
        Point value = new Point ( );
        value = getEditorPanel ( ).getCaret ( ).getMagicCaretPosition ( );
        SwingUtilities.convertPointToScreen ( value , this );
        return value;
    }
    
    
    public void hideArea ( )
    {
        this.setVisible ( false );
    }
    
    public void showArea ( )
    {
        this.setVisible ( true );
    }
    
    //utils
    
    public void setText ( String value )
    {
        editorPanel.setText ( value );
    }
    
    public void establish ( UndoManager undoManager, String contentType, String content, Color colour )
    {
        setBackground ( colour );
        editorPanel.setBackground ( colour );
        editorPanel.getDocument ( ).addUndoableEditListener ( undoManager );
        editorPanel.setContentType ( contentType ); 
        editorPanel.setText ( content );
    }    
}
