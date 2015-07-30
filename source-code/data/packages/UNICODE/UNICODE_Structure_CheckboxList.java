package data.packages.UNICODE; //Author(s): Jordan Micah Bennett
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

public class UNICODE_Structure_CheckboxList 
{
    //attributes
    private String label;
    private JPanel list_pane, destination_pane;
    private int checkbox_index;
    private JCheckBox [ ] checkboxes;
    private int maximum_checkboxes;
    private int layout_type;
    //changing boundaries.
    private int scrolling_up_attempts, scrolling_down_attempts;
    //establish screen dimensions
        //get screen dimensio
        private Dimension screen_dimension = Toolkit.getDefaultToolkit ( ).getScreenSize ( );  
        //establish variables
        private int screen_width = ( int ) screen_dimension.getWidth ( ), screen_height = ( int ) screen_dimension.getHeight ( );
        
    //constructor
    public UNICODE_Structure_CheckboxList ( int current_index, int max_boxxes, JPanel dest_pane, int _layout_type  ) 
    {
        //initialsie max boxxes
        maximum_checkboxes = max_boxxes;
        //initialise check boxxes array
        checkboxes = new JCheckBox [ maximum_checkboxes ];
        //init maximum
        checkbox_index = current_index;
        //establish destination panel
        destination_pane = dest_pane;
        //establish layout type
        layout_type = _layout_type;
    } 
    
    
    //constructor - convenience accessor
    public UNICODE_Structure_CheckboxList ( ) 
    {
        
    } 
    
    //methods
        //accessors
        public JCheckBox [ ] getCheckboxes ( )
        {
            return checkboxes;
        }
        public int getCheckboxIndex ( )
        {
            return checkbox_index;
        }
        public boolean getVisibility ( )
        {
            return list_pane.isVisible ( );
        }
        public JPanel getPane ( )
        {
            return list_pane;
        }
        public int getX ( )
        {
            return list_pane.getX ( );
        }
        public int getY ( )
        {
            return list_pane.getY ( );
        }
        public int getWidth ( )
        {
            return list_pane.getWidth ( );
        }
        public int getHeight ( )
        {
            return list_pane.getHeight ( );
        }
        public int getScrollUpAttempts ( )
        {
            return scrolling_up_attempts;
        }
        public int getScrollDownAttempts ( )
        {
            return scrolling_down_attempts;
        }
        public int getMaximumcheckboxes ( )
        {
            return maximum_checkboxes;
        }
        //mutators
            //dynamic -- add Box from defaultly directorized images
            public void addBox ( String directory, String label, boolean selected, String icon_name, String rollover_icon_name, String selected_icon_name, String rollover_selected_icon_name )
            {
                //initialise tha newly added box
                checkboxes [ checkbox_index ] = makeCheckBox ( directory, label, selected, icon_name, rollover_icon_name, selected_icon_name, rollover_selected_icon_name );
                //update check box index
                incCheckBoxIndex ( 1 ); 
            }

            public void incCheckBoxIndex ( int value )
            {
                checkbox_index += value;
            }
            public void decCheckBoxIndex( int value )
            {
                checkbox_index -= value;
            }       
            public void incMaximum ( int value )
            {
                maximum_checkboxes += value;
            }
            public void decMaximum ( int value )
            {
                maximum_checkboxes -= value;
            }      
            public void setScrollUpAttempts ( int value )
            {
                scrolling_up_attempts = value;
            }
            public void setScrollDownAttempts ( int value )
            {
                scrolling_down_attempts = value;
            }
            public void incScrollUpAttempts ( int value )
            {
                scrolling_up_attempts += value;
            }
            public void incScrollDownAttempts ( int value )
            {
                scrolling_down_attempts += value;
            }
            public void deScrollUpAttempts ( int value )
            {
                scrolling_up_attempts -= value;
            }
            public void decScrollDownAttempts ( int value )
            {
                scrolling_down_attempts -= value;
            }
            //static
            public void resetBounds ( int x_change, int y_change, int w_change, int h_change )
            {
                list_pane.setBounds ( getX ( ) + x_change, getY ( ) + y_change, getWidth ( ) + w_change, getHeight ( ) + h_change );
            }

            public void setMaximumcheckboxes ( int value )
            {
                maximum_checkboxes = value;
            }

    //misc
        public JCheckBox makeCheckBox ( String directory, String label, boolean selected, String icon_name, String rollover_icon_name, String selected_icon_name, String rollover_selected_icon_name )
        {
            return new UNICODE_Structure_Checkbox ( directory, label, selected, icon_name, rollover_icon_name, selected_icon_name, rollover_selected_icon_name );
        }
        public void colourcheckboxes ( Color background_colour, Color foreground_colour )
        {
            for ( int boxxes = 0; boxxes < getMaximumcheckboxes ( ); boxxes ++ )
            {
                checkboxes [ boxxes ].setBackground ( background_colour );
                checkboxes [ boxxes ].setForeground ( foreground_colour );
            }
        }
        //repaints the checkboxes, the panel on which it is placed, and the destination panel.
        public void repaint ( )
        {
            for ( int boxxes = 0; boxxes < getMaximumcheckboxes ( ); boxxes ++ )
            {
                checkboxes [ boxxes ].repaint ( );
            }
            getPane ( ).repaint ( );
            destination_pane.repaint ( );
        }
        
        public void setup ( Color background_colour, Color foreground_colour )
        {
            //initilize checkbox containers
                //intialise panel
                list_pane = new JPanel ( );

            for ( int boxxes = 0; boxxes < getMaximumcheckboxes ( ); boxxes ++ )
            {
                list_pane.add ( checkboxes [ boxxes ] );
                checkboxes [ boxxes ].setBackground ( background_colour );
                checkboxes [ boxxes ].setForeground ( foreground_colour );
            }
            list_pane.setLayout ( new BoxLayout ( list_pane, layout_type ) );
            //add the list pane to a scrollable pane
            //list_scroll_pane.add ( list_pane );
            //add scroll pane to destination panel
            destination_pane.add ( list_pane );
        }
        
        //scroll up
        public void scrollDown ( int distance, int check_box_height )
        {
            //compute number of screens worth of data
            int num_screens_worth_data = getHeight ( ) / screen_height;   
            int bottom_most_piece = 5;
            //scrolling down is only neccessary if there more than one page worth of data
            if ( num_screens_worth_data > 0 )
            {
                //compute y scroll up limit
                int y_coord_limit = - ( ( getHeight ( ) / ( num_screens_worth_data / 2 ) ) + ( ( check_box_height + bottom_most_piece ) * ( ( int ) Math.pow ( num_screens_worth_data, 2 ) ) ) );
                //test to see if y coord is still greater than the last portion of checkbox list
                if ( getY ( ) >= y_coord_limit )
                    resetBounds ( 0, -distance, 0, 0 );   
            }   
        }
        public void scrollUp ( int distance )
        {
            if ( getY ( ) <= 0 )
                resetBounds ( 0, distance, 0, 0 );      
        }
}
