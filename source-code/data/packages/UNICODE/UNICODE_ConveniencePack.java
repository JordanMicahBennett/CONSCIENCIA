package data.packages.UNICODE; //Author(s): Jordan Micah Bennett
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.net.URL;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
//import javax.media.Time;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.FontMetrics;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.Color;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.awt.Cursor;
import java.awt.event.MouseEvent;

public class UNICODE_ConveniencePack
{
	//attributes
    public UNICODE_ConveniencePack ( )
    {
    }
	
    //misc
		public void hideCursor ( JPanel context )
		{
			ImageIcon imageIcon = new ImageIcon ( "null" );
			Image image = imageIcon.getImage ( );
			image = context.createImage ( image.getSource ( ) );
			
			Cursor blankCursor = Toolkit.getDefaultToolkit ( ).createCustomCursor ( image, new Point ( 0, 0 ), "blank cursor" );
			
			context.setCursor ( blankCursor );
		}
		
		public void restoreCursor ( JPanel context )
		{
			context.setCursor ( Cursor.getDefaultCursor ( ) );
		}
	

        public String [ ] getDelimitedArray ( String data, String skip_data, String delimiter, int num_elements )
        {
            String [ ] string = new String [ num_elements ];
            //if i tell the function to perform exept when data is type  skip data, it
            //will do so without attempting to segment the aformentioned data type
                //establish data we want to delimit as delimited data
                Scanner scanner = new Scanner ( data ).useDelimiter ( delimiter );

                for ( int elements = 0; elements < num_elements; elements ++ )
                {
                    if ( !data.equals ( skip_data ) )
                        string [ elements ] = scanner.next ( );
                    else
                        string [ elements ] = skip_data;
                }
             
            return string;
        } 
        
        //get whether a substring of a string exists within a bigger string.
        public boolean stringSubsetEnquiry ( String full_string, String sub_string, int sub_portion_start, int sub_portion_end )
        {
            return full_string.indexOf ( sub_string.substring ( sub_portion_start, sub_portion_end ) ) != -1;
        }
		public String [ ] grownArray ( String [ ] array, String newElement )
		{
			String [ ] grownArray = new String [ array.length + 1 ];
			
			//pump old data into new array
			for ( int i = 0; i < array.length; i ++ )
				grownArray [ i ] = array [ i ]; 
	  
			//pump new data into new array
			grownArray [ grownArray.length - 1 ] = newElement;
			
			//redefine original array wrt grown array
			return grownArray;
		}
        //get whether a substring of a string exists within a bigger string.
        public boolean stringSubsetEnquiry ( String full_string, String sub_string )
        {
            return full_string.indexOf ( sub_string ) != -1;
        }
        
        //make array
        public String [ ] makeArray ( String list, String delimiter )
        {
            String [ ] array = list.split ( delimiter );
            return array;
        }
        
        public File makeFile ( String stream )
        {
            return new File ( stream );
        }
		public void makeDirectory ( String fileStream )
		{
			new File ( fileStream ).mkdir ( );
		}	
		public void makePhysicalFile ( String fileStream )
		{
			try
			{
				PrintWriter pw = new PrintWriter ( new FileWriter ( fileStream ) );
				pw.write ( "" );
				pw.close ( );
			}
			catch ( Exception error ) { };
		}
		public String getFileContent ( String fileStream, String contentDelimiter )
		{
			String value = "";
			try
			{
				Scanner scanner = new Scanner ( new File ( fileStream ) );
				while ( scanner.hasNext ( ) )
					value += scanner.nextLine ( ) + contentDelimiter;
			}
			catch ( Exception error ) { }
			return value;
		}	
			
		public void makePhysicalFile ( String fileStream, String content )
		{
			try
			{
				PrintWriter pw = new PrintWriter ( new FileWriter ( fileStream ) );
				pw.write ( content );
				pw.close ( );
			}
			catch ( Exception error ) { };
		}
		public void deleteFile ( File file )
		{
			file.delete ( );
		}	
		
    //method to convert string to url
    public URL makeUrl ( String path )
    {
        URL url = null;
        File file = new File ( path );
        try
        {
            url = file.toURL ( );
        }
        catch ( Exception error )
        {
        }
        return url;
    }	

	public void displayMessage ( JFrame frame, String message, String title, String iconType )
	{
		if ( ( iconType == "i" ) || ( iconType == "I" ) )
			JOptionPane.showMessageDialog ( frame, message, title, JOptionPane.INFORMATION_MESSAGE );
		if ( ( iconType == "w" ) || ( iconType == "W" ) )
			JOptionPane.showMessageDialog ( frame, message, title, JOptionPane.WARNING_MESSAGE );
		if ( ( iconType == "e" ) || ( iconType == "E" ) )
			JOptionPane.showMessageDialog ( frame, message, title, JOptionPane.ERROR_MESSAGE );
		if ( ( iconType == "q" ) || ( iconType == "Q" ) )
			JOptionPane.showMessageDialog ( frame, message, title, JOptionPane.QUESTION_MESSAGE );
	}
	
	//tests if filename is of type specified as target
	public boolean getFileTypeEnquiry ( String filename, String enquiryTarget )
	{
		return stringSubsetEnquiry ( filename, enquiryTarget );
	}
	
	//checks if file exists wrt file location supplied.
	//if not it creates this file physically (not in memory)
	public boolean getFileExistenceEnquiry ( String fileStream )
	{
		boolean flag = false;
		File file = new File ( fileStream );
		if ( file.exists ( ) )
			flag = false;
		else
		{
			flag = true;
			makePhysicalFile ( fileStream );
			file = null;
		}
		return flag;
	}
	
	//make timer data -- array containing minutes aat index 0, and seconds at index 1 
	public int [ ] makeTimerData ( int elapsedTime )
	{
		int [ ] TimerData = new int [ 2 ];
		int rawSeconds = elapsedTime/1000;
		int refinedSeconds = rawSeconds % 60;
		int minutes = rawSeconds / 60;
		TimerData [ 0 ] = minutes;
		TimerData [ 1 ] = refinedSeconds;
		return TimerData;
	}
	
	//convert minutes to milliseconds
	public int makeMilliseconds ( int minutes )
	{
		int seconds = minutes * 60;
		int milliseconds = seconds * 1000;
		return milliseconds;
	}
	
	public int makeMillisecondsFromSeconds ( int seconds )
	{
		int milliseconds = seconds * 1000;
		return milliseconds;
	}
	
	//make proper time from milliseconds
	//used to display time remaining from user profile in (user freindly form) 
	public String [ ] getTimeFromMilliseconds ( int milliseconds )
	{
		//get seconds
		int rawSeconds = milliseconds / 1000;
		int refinedSeconds = rawSeconds % 60;
		//get minutes
		int minutes = rawSeconds/60;
		
		String finalSeconds = "";
		
		if ( refinedSeconds < 10 )
			finalSeconds = "0" + refinedSeconds;
			
		else
			finalSeconds = "" + refinedSeconds;   
			
		String value [ ] = { "" + minutes, finalSeconds, "" + refinedSeconds };
		return value;
	} 
	
	//make proper time from milliseconds
	//used to display time remaining from user profile in (user freindly form) 
	public String getTime ( int milliseconds )
	{
		//get seconds
		int rawSeconds = milliseconds / 1000;
		int refinedSeconds = rawSeconds % 60;
		//get minutes
		int minutes = rawSeconds/60;
		
		String finalSeconds = "";
		
		if ( refinedSeconds < 10 )
			finalSeconds = "0" + refinedSeconds;
			
		else
			finalSeconds = "" + refinedSeconds;   
			
		String value = minutes + " : " + finalSeconds;
		return value;
	} 
	
	
	//get secs from millisecs
	public int getSeconds ( double milliseconds )
	{
		return ( int ) ( milliseconds / milliseconds );
	}
	
    public Font getCustomFont ( String inputStream )
    {
        Font font = null;
        try
        {
            font = Font.createFont ( Font.TRUETYPE_FONT, new File ( inputStream ) );
        }
        catch ( Exception error ) { }
        return font;
    }
	
    public void createCustomCursor ( String cursorImageStream, JPanel destinationPanel )
    {
        //create image
		ImageIcon imageIcon = new ImageIcon ( cursorImageStream );
		Image image = imageIcon.getImage ( );
		image = destinationPanel.createImage ( image.getSource ( ) );
		
        //initalise "hot spot"
        Point point = new Point ( 0, 0 );
		
		//create toolkit
        Toolkit toolkit = Toolkit.getDefaultToolkit ( );
		
        //initialise cursor
        Cursor cursor = toolkit.createCustomCursor ( image, point, "Cursor" );                       
    }        

	public String getUpperCaseFirstLetterOfWord ( String word )
	{
        String firstLetter = "" + word.charAt ( 0 );
        String finalWord = firstLetter.toUpperCase ( ) + word.substring ( 1, word.length ( ) );
		return finalWord;
	}

	
	public String getJavaKeyWord ( String input, String commentSymbol, String delimiter )
    {
		String value = "";
	
		//establish java component list
		UNICODE_Structure_JavaComponentList javaComponentList = new UNICODE_Structure_JavaComponentList ( );

		javaComponentList = getJavaComponentStructureList ( commentSymbol, delimiter );
		
		for ( int i = 0; i < javaComponentList.size ( ); i ++ )
		{
			String componentName = ( String )  javaComponentList.get ( i ).getComponentName ( );
			String componentStream = ( String )  javaComponentList.get ( i ).getComponentStream ( );
			if ( input.equals ( componentName ) )
				value = componentStream + "." + componentName;
		}
		
        return value;
    }	
	
	public UNICODE_Structure_JavaComponentList getJavaComponentStructureList ( String commentSymbol, String delimiter )
	{
		UNICODE_Structure_JavaComponentList value = new UNICODE_Structure_JavaComponentList ( );
		try
		{
			Scanner scanner = new Scanner ( new File ( "data/config/COMPONENT LIST.ini" ) );
			while ( scanner.hasNext ( ) )
			{
				String line = scanner.nextLine ( );
				if ( !line.startsWith ( commentSymbol ) )
				{
					String name = getDelimitedArray ( line, "", delimiter, 2 ) [ 0 ];
					String stream = getDelimitedArray ( line, "", delimiter, 2 ) [ 1 ];
					value.addComponent ( name, stream );
				}
			}
		}
		catch ( Exception error ) { }
		return value;
	}
	
	
	public boolean getJavaStandardComponentEnquiry ( String input, String commentSymbol, String delimiter )
	{
		boolean flag = false;
		//establish java component list
		UNICODE_Structure_JavaComponentList javaComponentList = new UNICODE_Structure_JavaComponentList ( );
		
		javaComponentList = getJavaComponentStructureList ( commentSymbol, delimiter );
		
		for ( int i = 0; i < javaComponentList.size ( ); i ++ )
		{
			String componentName = ( String )  javaComponentList.get ( i ).getComponentName ( );
			if ( stringSubsetEnquiry ( componentName, input ) )
				flag = true;
		}
		return flag;
	}

	
	public String getUNICODEKeyWord ( String input )
	{
		String value = "";
		ArrayList valueList = new ArrayList ( );
		
		if ( stringSubsetEnquiry ( input, "UNICODE" ) )
		{
			valueList.add ( "data.packages.UNICODE." );
			valueList.add ( input );
		}
		
		for ( int i = 0; i < valueList.size ( ); i ++ )
			value += ( String ) valueList.get ( i );
		
		return getNonDuplicatedBsgkString ( value );
	}

	
	public String getNonDuplicatedBsgkString ( String input )
	{
		String value = "";
		value = input.replace ( "data.packages.UNICODE.data.packages.UNICODE.", "data.packages.UNICODE." );
		return value;
	}
	
	//burrowed function
	public String getNonDupilatedCharacters ( String duplicateTarget )
	{	
		StringBuilder noDupes = new StringBuilder ( );
		for ( int i = 0; i < duplicateTarget.length ( ); i++ ) 
		{
			String si = duplicateTarget.substring ( i, i + 1 );
			if ( noDupes.indexOf ( si ) == -1 )
			{
				noDupes.append ( si );
			}
		}
		return noDupes.toString ( );
	}


	public boolean getIntegerIncrementEnquiry ( int integer, int max )
	{
		boolean flag = false;
		int previous = integer;
		int next = integer + 1;
		if ( next < max )
		{
			if ( next > previous )
				flag = true;
			else
				flag = false;
		}
		return flag;
	}
	
	public boolean getIntegerDecrementEnquiry ( int integer, int min )
	{
		boolean flag = false;
		int next = integer;
		int previous = integer - 1;
		if ( previous > min )
		{
			if ( previous < next )
				flag = true;
			else
				flag = false;
		}
		return flag;
	}
	
	public int getDisplayWidthFromString ( Graphics graphics, Graphics2D graphics2d, UNICODE_CustomFont font, String ttfName, String string )
	{
		//establish font metrics uppon which I will call getStringBounds, so as to create a rectangle of label dimension
		FontMetrics label_metrics = graphics2d.getFontMetrics ( font.getFont ( graphics, ttfName, string ) );
		//establish rectangle, from which font dimension will be derived.
		Rectangle2D label_dimension = label_metrics.getStringBounds ( string, graphics );
		
		return ( int ) label_dimension.getWidth ( );
	}
	
	public int getDisplayHeightFromString ( Graphics graphics, Graphics2D graphics2d, UNICODE_CustomFont font, String ttfName, String string )
	{
		//establish font metrics uppon which I will call getStringBounds, so as to create a rectangle of label dimension
		FontMetrics label_metrics = graphics2d.getFontMetrics ( font.getFont ( graphics, ttfName, string ) );
		//establish rectangle, from which font dimension will be derived.
		Rectangle2D label_dimension = label_metrics.getStringBounds ( string, graphics );
		
		return ( int ) label_dimension.getHeight ( );
	}
	
	public double getDisplayWidthFromString ( String input, int fontSize )
	{
		Font font = new Font ( "Times New Roman", Font.PLAIN, fontSize );
		FontMetrics fontMetrics = new FontMetrics ( font ) { };
		return fontMetrics.getStringBounds ( input, null ).getWidth ( );
	}
	
	public double getDisplayHeightFromString ( String input, int fontSize )
	{
		Font font = new Font ( "Times New Roman", Font.PLAIN, fontSize );
		FontMetrics fontMetrics = new FontMetrics ( font ) { };
		return fontMetrics.getStringBounds ( input, null ).getHeight ( );
	}
	
	public UNICODE_CustomFont getUNICODEFont ( String stream )
	{
		return new UNICODE_CustomFont ( stream );
	}
	
    public int getLargestInteger ( ArrayList numList )
    {
        java.util.Collections.sort ( numList );
        return ( int ) numList.get ( numList.size ( ) - 1 );
    }
	
    public void printFile ( String content, String stream )
    {
        try
        {
            PrintWriter pw = new PrintWriter ( new FileWriter ( stream ) );
            pw.print ( content );
            pw.close ( );
        }
        catch ( Exception e ) { }
    }
	
	
	public ArrayList generateArrayListContentFromFile ( String fileStream )
	{
		ArrayList value = new ArrayList ( );
        try
        {
            Scanner scanner = new Scanner ( new File ( fileStream ) );
            while ( scanner.hasNext ( ) )
				value.add ( scanner.nextLine ( ) );
			scanner.close ( );
        }
        catch ( Exception e ) { }
		return value;
	}
	
    public String getFileContent ( String fileStream )
    {
        String value = "";
        try
        {
            Scanner scanner = new Scanner ( new File ( fileStream ) );
            while ( scanner.hasNext ( ) )
                value += scanner.nextLine ( ) + "\n";
        }
        catch ( Exception error ) { }
        return value;
    }	
	
	
    public String getMatchingValue ( String input, String searchTag, String searchTag2, String delimiter )
    {
        String value = "";
        ArrayList valueList = new ArrayList ( );
        
        //divide input into segments
        try
        {
            Scanner scanner = new Scanner ( input ).useDelimiter ( delimiter );
            
            while ( scanner.hasNext ( ) )
                valueList.add ( scanner.next ( ) );
            scanner.close ( );
        }
        catch ( Exception error ) { }
        
        
        //loop through the segment collection checking against the search tag
        for ( int i = 0; i < valueList.size ( ); i ++ )
            if ( stringSubsetEnquiry ( ( String ) valueList.get ( i ), searchTag ) )
                value = ( String ) valueList.get ( i );
			else if ( stringSubsetEnquiry ( ( String ) valueList.get ( i ), searchTag2 ) )
				value = ( String ) valueList.get ( i );
        
        return value;
		
		//proud of myself wrote this once, worked, well, as usual...
    }
	
	
	public void enableFullScreenMode ( JFrame applicationFrame )
	{
		java.awt.GraphicsEnvironment graphicsEnvironment = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment ( );
		java.awt.GraphicsDevice [ ] graphicsDevices = graphicsEnvironment.getScreenDevices ( );
		try
		{
			graphicsDevices [ 0 ].setFullScreenWindow ( applicationFrame );
		} 
		finally 
		{
			graphicsDevices [ 0 ].setFullScreenWindow ( applicationFrame );
		}
	}
	
    public void shutDownComputer ( )
    {
        String shutdownCmd = "shutdown -s";
        try
        {
            Process child = Runtime.getRuntime ( ).exec ( shutdownCmd );
        }
        catch ( Exception error ) { }
    }
	
	
    public void executeCommand ( String command )
    {
        try
        {
            Process child = Runtime.getRuntime ( ).exec ( command );
        }
        catch ( Exception error ) { }
    }
	
	public int getDirectoryCardinality ( String directory )
	{
		return new File ( directory ).list ( ).length;
	}
	
	//burrowed
	public void saveScreen ( String fileName )
	{
		try
		{
			Robot robot = new Robot();
	 
			BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			ImageIO.write(screenShot, "PNG", new File(fileName));
		}
		catch ( Exception error )
		{
		}
	}
	
	public String getRegexComponents ( String patternString, String inputString, String replacementControllerString )
	{
		String returnValue = "";
		
        Pattern pattern = Pattern.compile ( patternString );
        
        Matcher javacPathGenerationLineMatcher = pattern.matcher ( inputString );
        if ( javacPathGenerationLineMatcher.find ( ) )
            returnValue = javacPathGenerationLineMatcher.replaceAll ( replacementControllerString );
			
		return returnValue;
	}
	
	public String getRegexComponents ( String patternString, String inputString, String replacementControllerString, String skipIdentifierTag )
	{
		String returnValue = "";
		
        Pattern pattern = Pattern.compile ( patternString );
        
        Matcher javacPathGenerationLineMatcher = pattern.matcher ( inputString );
        if ( javacPathGenerationLineMatcher.find ( ) )
			if ( !stringSubsetEnquiry ( inputString, skipIdentifierTag ) )
				returnValue = javacPathGenerationLineMatcher.replaceAll ( replacementControllerString );
			
		return returnValue;
	}
	
	public InputStream getInputStreamFromInput ( String scannerLine )
	{
		return new ByteArrayInputStream ( scannerLine.getBytes ( ) );
	}
}
