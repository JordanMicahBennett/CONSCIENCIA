package data.packages.CONSCIENCIA_EDITOR; //Author(s): Jordan Micah Bennett
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.awt.Color;
import java.util.ArrayList;
import java.awt.Dimension;

import data.packages.UNICODE.*;

public class CONSCIENCIA_EDITOR_ConfigurationManager
{
    // attributes
        //establish opacity level variables
        private ArrayList config_lines = new ArrayList ( );
        private String [ ] config_labels = 
                                            {
												"colour::",
												"font-colour::",
												"font-name::",
												"font-size::",
												"label-description-padding::",
												"line-spacing::",
												"buffer-dimension::",
												"app-opacity::"
                                            };
                                        
        //string to colour conterter
        private UNICODE_StringToColourConverter string_to_colour_converter = new UNICODE_StringToColourConverter ( );
        
        //establish convenience pack
        private UNICODE_ConveniencePack conveniencePack = new UNICODE_ConveniencePack ( );
        
    //constructor
    public CONSCIENCIA_EDITOR_ConfigurationManager ( String configurationFileStream )
    {
        loadConfigData ( configurationFileStream );
    }
    
    //load config data
    public void loadConfigData ( String configurationFileStream )
    {
        //generate config lines
        try
        {
            int count = 0;
            Scanner scanner = new Scanner ( new File ( configurationFileStream ) );
            while ( scanner.hasNext ( ) ) 
            {
               config_lines.add ( scanner.next ( ) );
            }
            scanner.close ( );
        }
        catch ( Exception error )
        {
        }   

    }
	
    //get colour from file, so program can know what colour to start with 
    public Color getColourFromFile ( )
    {
        Color colour = null;
        
        //convert array list index 0, colour config line to a string separated by spaces
        String [ ] rgb_array = conveniencePack.getDelimitedArray ( conveniencePack.getDelimitedArray ( ( String ) config_lines.get ( 0 ), "", "::", 2 ) [ 1 ], "", ",", 3 );
        String colour_string = rgb_array [ 0 ] + " " + rgb_array [ 1 ] + " " + rgb_array [ 2 ];
        
        //convert the string into a fucking colour 
        colour = string_to_colour_converter.getColourFromString ( colour_string.replace ( "null", "" ) );

        //return the fucking colour
        return colour;
    }
	
	public Color getFontColourFromFile ( )
	{
        Color colour = null;
        
        //convert array list index 0, colour config line to a string separated by spaces
        String [ ] rgb_array = conveniencePack.getDelimitedArray ( conveniencePack.getDelimitedArray ( ( String ) config_lines.get ( 1 ), "", "::", 2 ) [ 1 ], "", ",", 3 );
        String colour_string = rgb_array [ 0 ] + " " + rgb_array [ 1 ] + " " + rgb_array [ 2 ];
        
        //convert the string into a fucking colour 
        colour = string_to_colour_converter.getColourFromString ( colour_string.replace ( "null", "" ) );

        //return the fucking colour
        return colour;
	}
	
    //getFontStreamStringFromFile from file
    public String getFontNameFromFile ( )
    {
        String value = null;
        
        value = conveniencePack.getDelimitedArray ( ( String ) config_lines.get ( 2 ), "", "::", 2 ) [ 1 ];
        
        return value;
    }
	
    //get font size from file
    public int getFontSizeFromFile ( )
    {
        int value = 0;
        value = Integer.parseInt ( conveniencePack.getDelimitedArray ( ( String ) config_lines.get ( 3 ), "", "::", 2 ) [ 1 ] );
        return value;
    }	
	
    //get label-description-padding from file
    public int getLabelDescriptionPadding ( )
    {
        int value = 0;
        value = Integer.parseInt ( conveniencePack.getDelimitedArray ( ( String ) config_lines.get ( 4 ), "", "::", 2 ) [ 1 ] );
        return value;
    }	
	
    //get line spacing from file
    public int getLineSpacingFromFile ( )
    {
        int value = 0;
        value = Integer.parseInt ( conveniencePack.getDelimitedArray ( ( String ) config_lines.get ( 5 ), "", "::", 2 ) [ 1 ] );
        return value;
    }	
	
	
    //get buffer dimension from file
    public Dimension getBufferDimensionFromFile ( )
    {
		Dimension returnValue = null;
		
        String bufferDimensionString = bufferDimensionString = conveniencePack.getDelimitedArray ( ( String ) config_lines.get ( 6 ), "", "::", 2 ) [ 1 ];
		
		int width = Integer.parseInt ( conveniencePack.getDelimitedArray ( bufferDimensionString, "", ",", 2 ) [ 0 ] );
		int height = Integer.parseInt ( conveniencePack.getDelimitedArray ( bufferDimensionString, "", ",", 2 ) [ 1 ] );
		
		returnValue = new Dimension ( width, height );
		
		return returnValue;
	}
	
    //get opacity from file, so program can know what opacity to start with
    public float getOpacityFromFile ( )
    {
        float opacity = 0.0f;
        opacity = Float.parseFloat ( conveniencePack.getDelimitedArray ( ( String ) config_lines.get ( 7 ), "", "::", 2 ) [ 1 ] );
        return opacity;
    }
	
    public void updateConfigFile ( )
    {
        try
        {
            PrintWriter pw = new PrintWriter ( new FileWriter ( "data/config/CONFIG.ini" ) );
            for ( int configs = 0; configs < config_lines.size ( ); configs ++ )
                pw.println ( config_lines.get ( configs ) );
            pw.close ( );
        }
        catch ( Exception error )
        {
        }  
    }
	
	public void defineLabels ( String label_string, String delimiter )
	{
		config_labels = conveniencePack.makeArray ( label_string, delimiter );
	}
	
	public String [ ] getConfigLabels ( )
	{
		return config_labels;
	}
}
