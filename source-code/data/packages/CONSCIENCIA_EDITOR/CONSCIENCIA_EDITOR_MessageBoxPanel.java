 package data.packages.CONSCIENCIA_EDITOR; //Author(s): Jordan Micah Bennett
import javax.swing.JPanel;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.awt.Point;

import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.util.Scanner;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CONSCIENCIA_EDITOR_MessageBoxPanel extends JPanel
{
    //attributes
    //establish frame connection
    private JFrame messageBoxWindowFrame; //will allow repositioning of frame by user
    //establish current coordinates genrated by pressed down mouse
    private Point held_mouse_coords = null;
    //establish current coordinates geenrated by mouse dragging
    private Point dragged_mouse_coords = null;

    //establish message box dimension
    private int width = 400, height = 180;
    
    //establish maximum buttons
    private int MAXIMUM_BUTTONS = 3; 
    
	//click response threads
    private Thread thread0 = null, thread1 = null;
    
	//establish menu panel for this panel
	private CONSCIENCIA_EDITOR_MessageBoxMenuPanel menuPanel = null;
	
    public CONSCIENCIA_EDITOR_MessageBoxPanel ( Thread _thread0, Thread _thread1, Color backgroundColour, Color buttonOutlineColour, JFrame _messageBoxWindowFrame, String buttonTextureDir )
    {
        //establish threads
        thread0 = _thread0;
        thread1 = _thread1;
        
        //establish window frame
        messageBoxWindowFrame = _messageBoxWindowFrame;
        
        //establish bg colour
        setBackground ( backgroundColour );
        
        //establish dimension
        setSize ( new Dimension ( width, height ) );
		
		//establish menu panel
		menuPanel = new CONSCIENCIA_EDITOR_MessageBoxMenuPanel ( generatedCustomComponentList ( ), true, 100, 130, 3, 33, 1340, "clockwise", "horizontal", buttonTextureDir, 80, 80, 20, 20, backgroundColour, buttonOutlineColour );

		//add menu panel to this
		add ( menuPanel );

        //add mouse listener
        addMouseListener ( new mouseListening ( ) );
        addMouseMotionListener ( new mouseListening ( ) );
        
        //set focus
        setFocusable ( true );
    }
    
    
    //paint component
    public void paintComponent ( Graphics graphics )
    {
        super.paintComponent ( graphics );
        Graphics2D graphics2d = ( Graphics2D ) graphics;
        
        //establish anti aliasing
        //anti_alias_manager.setupAntiAliasing ( graphics2d );
    }
        
    
    //utils
    public void showMethodPanel ( int x, int y )
    {
        setLocation ( x, y );
        this.setVisible ( true );
    }
    public void hideMethodPanel ( )
    {
        this.setVisible ( false );
    } 
	
    //add custom components which dont exist in CONSCIENCIA_EDITOR_MenuPanel by default,
    //where button click response is conceerned.
    public ArrayList <Object> generatedCustomComponentList ( )
    {
        ArrayList <Object> value = new ArrayList <Object> ( );
        
        value.add ( thread0 );
        value.add ( thread1 );
		value.add ( this );
		value.add ( messageBoxWindowFrame );
        
        return value;
    }
	
    //mouse listener
    private class mouseListening implements MouseListener, MouseMotionListener
    { 
        public void mouseClicked ( MouseEvent mEvent )
        {
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
			repaint ( );
        }
        
        public void mouseDragged ( MouseEvent mEvent )
        {         
            //establish dragged mouse coordinates
            dragged_mouse_coords = mEvent.getLocationOnScreen ( );    
            messageBoxWindowFrame.setLocation ( ( int ) ( dragged_mouse_coords.getX ( ) - held_mouse_coords.getX ( ) ), ( int ) ( dragged_mouse_coords.getY ( ) - held_mouse_coords.getY ( ) ) );
			repaint ( );
        }
        public void mouseMoved ( MouseEvent mEvent )
        {
		}
    }
	
}
