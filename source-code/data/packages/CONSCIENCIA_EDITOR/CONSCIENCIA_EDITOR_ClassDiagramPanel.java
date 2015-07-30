package data.packages.CONSCIENCIA_EDITOR;

//Written by Jordan Micah Bennett copyRight uni-code(tm) inc. 2012
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import data.packages.UNICODE.*;

public class CONSCIENCIA_EDITOR_ClassDiagramPanel extends JPanel
{
    //attributes
    //establish list of class diagram boxxes
    private UNICODE_ClassDiagramBox [ ] classDiagramBoxes = null;
    
    //establish array list to capture all project file names
    private ArrayList projectFileNamesList = new ArrayList ( );
    
    //establish editor mode enquiry - this will enable the toggling and detoglling of this panel 
    private boolean editorModeEnquiry = false; //is ConscienciaWriter in editor mode, or in class diagram view mode? Anyway editorMode = false is precondition where we do class diagram shit.

    //establish current coordinates genrated by pressed down mouse
    private Point held_mouse_coords = null;
    //establish current coordinates geenrated by mouse dragging
    private Point dragged_mouse_coords = null;
    
    ///constructor
    public CONSCIENCIA_EDITOR_ClassDiagramPanel ( ArrayList _projectFileNamesList, boolean _editorModeEnquiry, String regexUsageAnswer )
    {
        //establish project file name list
        projectFileNamesList = _projectFileNamesList;
        
        //establish editor mode enquiry
        editorModeEnquiry = _editorModeEnquiry;
        
        //generate class diagram boxes
        generateClassDiagrams ( regexUsageAnswer );
        
        //add mouse listener
        addMouseListener ( new mouseListening ( ) );
        addMouseMotionListener ( new mouseListening ( ) );        
    }
     
    //utils
    public void generateClassDiagrams ( String regexUsageAnswer )
    {
        //establish class diagram boxes variable wrt num project files in project
        classDiagramBoxes = new UNICODE_ClassDiagramBox [ projectFileNamesList.size ( ) ];
        
        //establish method assistance, to derive available methods
        CONSCIENCIA_EDITOR_MethodAssistanceManager methodAssistanceManager = null;
        
        //define each class diagram box wrt file name list
        for ( int i = 0; i < projectFileNamesList.size ( ); i ++ )
        {
            //methodAssistanceManager = new UNICODE_MethodAssistanceManager ( ( String ) projectFileNamesList.get ( i ), regexUsageAnswer );
            classDiagramBoxes [ i ] = new UNICODE_ClassDiagramBox ( ( String ) projectFileNamesList.get ( i ), null );
        }
            
        //add boxes to this panel
        for ( int i = 0; i < classDiagramBoxes.length; i ++ )
            add ( classDiagramBoxes [ i ] );
    }
    
    
    //mouse listening
    private class mouseListening implements MouseMotionListener, MouseListener
    {
        public void mouseClicked ( MouseEvent mEvent )
        {
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
        }
        
        public void mouseDragged ( MouseEvent mEvent )
        {         
            //establish dragged mouse coordinates
            dragged_mouse_coords = mEvent.getLocationOnScreen ( );    
            //application_frame.setLocation ( ( int ) ( dragged_mouse_coords.getX ( ) - held_mouse_coords.getX ( ) ), ( int ) ( dragged_mouse_coords.getY ( ) - held_mouse_coords.getY ( ) ) );
        }
        
        public void mouseMoved ( MouseEvent mEvent )
        {
        }
    }
}
