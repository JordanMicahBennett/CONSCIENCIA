package data.packages.UNICODE; //Author(s): Jordan Micah Bennett
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.awt.Color;
import java.util.ArrayList;

public class UNICODE_ConfigurationManager
{
    // attributes
        //establish opacity level variables
        private ArrayList config_lines = new ArrayList ( );
        private String [ ] config_labels = 
                                            {
                                                "colour::",
                                                "opacity::",
                                                "anti-aliasing::",
												"last-project-directory::",
												"enable-regex-in-method-availaibility-display::",
												"javac-OR-jre-bin-directory::",
												"compiler-window-size::"
                                            };
                                        
        //string to colour conterter
        private UNICODE_StringToColourConverter string_to_colour_converter = new UNICODE_StringToColourConverter ( );
        
        //establish convenience pack
        private UNICODE_ConveniencePack convenience_access = new UNICODE_ConveniencePack ( );
        
    //constructor
    public UNICODE_ConfigurationManager ( String config_file_stream )
    {
        loadConfigData ( config_file_stream );
    }
    
    //load config data
    public void loadConfigData ( String config_file_stream )
    {
        //generate config lines
        try
        {
            int count = 0;
            Scanner scanner = new Scanner ( new File ( config_file_stream ) );
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
    
    //get opacity from file, so program can know what opacity to start with
    public float getOpacityFromFile ( )
    {
        float opacity = 0.0f;
        opacity = Float.parseFloat ( convenience_access.getDelimitedArray ( ( String ) config_lines.get ( 1 ), "", "::", 2 ) [ 1 ] );
        return opacity;
    }
    //get colour from file, so program can know what colour to start with 
    public Color getColourFromFile ( )
    {
        Color colour = null;
        
        //convert array list index 0, colour config line to a string separated by spaces
        String [ ] rgb_array = convenience_access.getDelimitedArray ( convenience_access.getDelimitedArray ( ( String ) config_lines.get ( 0 ), "", "::", 2 ) [ 1 ], "", ",", 3 );
        String colour_string = rgb_array [ 0 ] + " " + rgb_array [ 1 ] + " " + rgb_array [ 2 ];
        
        //convert the string into a fucking colour 
        colour = string_to_colour_converter.getColourFromString ( colour_string.replace ( "null", "" ) );

        //return the fucking colour
        return colour;
    }
    //get anti alias value
    public String getAntiAliasingStateFromFile ( )
    {
        String alias_state = null;
        
        alias_state = convenience_access.getDelimitedArray ( ( String ) config_lines.get ( 2 ), "", "::", 2 ) [ 1 ];
        
        return alias_state;
    }
    //get last directory fom file
    public String getLastDirectoryFromFile ( )
    {
        String lastDirectory = null;
        
        lastDirectory = convenience_access.getDelimitedArray ( ( String ) config_lines.get ( 3 ), "", "::", 2 ) [ 1 ];
        
        return lastDirectory;
    }	
    //get Method Package Regex Usage Answer from file
    public String getMethodPackageRegexUsageAnswerFromFile ( )
    {
        String regexUsageAnswer = null;
        
        regexUsageAnswer = convenience_access.getDelimitedArray ( ( String ) config_lines.get ( 4 ), "", "::", 2 ) [ 1 ];
        
        return regexUsageAnswer;
    }
    //get Javac Location from file
    public String getJavacLocationFromFile ( )
    {
        String javacLocation = null;
        
        javacLocation = convenience_access.getDelimitedArray ( ( String ) config_lines.get ( 5 ), "", "::", 2 ) [ 1 ];
        
        return javacLocation;
    }
    //get Compiler Window Size String from file
    public String getCompilerWindowSizeString ( )
    {
        String compilerWindowSizeString = null;
        
        compilerWindowSizeString = convenience_access.getDelimitedArray ( ( String ) config_lines.get ( 6 ), "", "::", 2 ) [ 1 ];
        
        return compilerWindowSizeString;
    }
	
    //update opacity
    public void updateOpacity ( UNICODE_OpacityController opacity_manager )
    {
        //set opacity config index to new opacity level
        config_lines.set ( 1, config_labels [ 1 ] + opacity_manager.getOpacLevel ( ) );
        //print the config array list contents
        updateConfigFile ( );
    }
	
    //update colour config:: takes a colour, and extracts rgb integer components, 
    //and prints them to the colour config file.
    public void updateColour ( Color colour )
    {
        //establish output colour config ( what bushman gui kit config reconizes as colour data at parsing )
        String [ ] rgb_array = convenience_access.getDelimitedArray ( string_to_colour_converter.getRGBString ( colour ), "", " ", 3 );
        String output_colour = rgb_array [ 0 ] + "," + rgb_array [ 1 ] + "," + rgb_array [ 2 ];
        //set colour config index to new colour
        config_lines.set ( 0, config_labels [ 0 ] + output_colour );
        //print the config array list contents
        updateConfigFile ( );
    }
	
    //update anti - alias file
    public void updateAntiAliasing ( UNICODE_AntiAliasingController anti_alias_manager )
    {
        //set anti - aliasing config index to new anti - aliasing state answer
        config_lines.set ( 2, config_labels [ 2 ] + anti_alias_manager.getRenderingAnswer ( ) );
        //print the config array list contents
        updateConfigFile ( );
    }
    //update last known directory
    public void updateLastKnownDirectory ( UNICODE_LastKnownDirectoryController lastKnownDirectoryController )
    {
        //set last known directory
        config_lines.set ( 3, config_labels [ 3 ] + lastKnownDirectoryController.getLastKnownDirectory ( ) );
        //print the config array list contents
        updateConfigFile ( );
    }
    //update Method Package Regex Usage Answer
    public void updateMethodPackageRegexUsageAnswer ( UNICODE_MethodPackageRegexUsageController methodPackageRegexUsageController )
    {
        //set Method Package Regex Usage Answer
        config_lines.set ( 4, config_labels [ 4 ] + methodPackageRegexUsageController.getRegexUsageAnswer ( ) );
        //print the config array list contents
        updateConfigFile ( );
    }
    //update JavacLocation 
    public void updateJavacLocation ( UNICODE_JavacLocationController javacLocationController )
    {
        //set Method Package Regex Usage Answer
        config_lines.set ( 5, config_labels [ 5 ] + javacLocationController.getJavacLocation ( ) );
        //print the config array list contents
        updateConfigFile ( );
    }
    //update JavacLocation 
    public void updateCompilerWindowSizeController ( UNICODE_CompilerWindowSizeController compilerWindowSizeController )
    {
        //set Method Package Regex Usage Answer
        config_lines.set ( 6, config_labels [ 6 ] + compilerWindowSizeController.getCompilerWindowSizeString ( ) );
        //print the config array list contents
        updateConfigFile ( );
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
		config_labels = convenience_access.makeArray ( label_string, delimiter );
	}
	
	public String [ ] getConfigLabels ( )
	{
		return config_labels;
	}
}
