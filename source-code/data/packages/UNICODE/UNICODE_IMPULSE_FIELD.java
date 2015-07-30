package data.packages.UNICODE;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Image;
import java.awt.geom.Line2D;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.util.Random;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import java.io.File;
import javax.swing.Timer;
import java.awt.Point;
import java.awt.Rectangle;

public class UNICODE_IMPULSE_FIELD extends JPanel 
{
    //attributes
		//establish convenience pack
		private UNICODE_ConveniencePack conveniencePack = new UNICODE_ConveniencePack ( );
        //IMPULSE REPOSITIONING VARS
            //establish current coordinates genrated by pressed down mouse
            private Point heldMouseCoords = null;
            //establish current coordinates geenrated by mouse dragging
            private Point draggedMouseCoords = null;
    
        //image vars
        private ArrayList <UNICODE_IMPULSE_CONNECTOR> impulseConnectors = new ArrayList <UNICODE_IMPULSE_CONNECTOR> ( );
        private ArrayList <UNICODE_IMPULSE_SHAPE> impulses = new ArrayList <UNICODE_IMPULSE_SHAPE> ( );
		private ArrayList <UNICODE_IMPULSE_DESCRIPTOR> impulseDescriptors = new ArrayList <UNICODE_IMPULSE_DESCRIPTOR> ( );
        
        //dimension
        private int impulseWidth, impulseHeight, spatialMultiplier;
        
        //cardinality
        private int maxImpulses = 0;
        
        //randomizer
        private Random randomizer = new Random ( );
        
        //coordinate gen vars
			//prebaked limits for y upper and lower bound.
			private int prebakedLowerBoundLimitForY = 0, prebakedUpperBoundLimitForY = 0;
            //limits - y values stay in the same range, x values are still random, but still need to grow.
            private int lowerBoundLimitY, upperBoundLimitY;
            private int lowerBoundLimitX, upperBoundLimitX;
            //list generators
                //initials
                private int initialLowerBoundX = 100, initialUpperBoundX = 110;
                //lists
                private ArrayList <Integer> xCoordList = new ArrayList <Integer> ( ), yCoordList = new ArrayList <Integer> ( );
        //establish impulse action dock
        private UNICODE_MenuPanel impulseActionDock = null;
		private UNICODE_DonutMenuWindow impulseActionWindow = null;
        
        //establish C: drive file list
        private ArrayList StreamFolderFileList = null, StreamFolderSearchNamesList = null;
        private int currentImpulseIndex, currentDescriptorIndex;
        
        //animation timer
        private Timer nodeEntityMenuAnimationTimer = new Timer ( 1, new nodeEntityMenuAnimationListener ( ) );
        //scroll vars
            //timer
            private Timer scrollRightwardsTimer = new Timer ( 1, new scrollRightwardsListener ( ) ), scrollLeftwardsTimer = new Timer ( 1, new scrollLeftwardsListener ( ) );
			private Timer scrollDurationTimer = new Timer ( 1, new scrollDurationListener ( ) );
			private int maximumScrollDuration = 0; //two thousand milliesconds
			private double scrollDurationTracker = 0;
			
            //scroll value vars
                //rate
                private double scrollRate;
                //adjuster
                private int scrollRightwardsAdjuster, scrollLeftwardsAdjuster;
                //mouse wheel int controller
                private int rotationDirection;
				//scrool amount tracker
				private int scrollAmountTracker = 0;
                
        //establish name search vars
        private String searchName = "";
        
        //establish line visibility controll boolean
        private boolean lineVisibilityEnquiry = true;
        
        
        //establish field stream
        private String fieldStream = "";
        
        //establish whether this field is a home folder
        private boolean homeStreamEnquiry = false;
        private int minimumNodeFieldCardinalityBeforeChopping = 0;
		
		//establish variables so as to navigate to previous directories
			//1.establish variable to store fieldStreams
			private ArrayList <String> previousFieldStreamList = new ArrayList <String> ( );
			//2.Establish counter to keep track of the number of directory accesses.
			//this is to prevent attempt to navigate beyond the initial directory.
			private int fieldAccessCounter = 0;
			
			//establish folderIndicatorScaleFactor
			int folderIndicatorScaleFactor = 0;
			
			
		//impulses description requirements
		private Color impulseDescriptorBodyFolderColour = null, impulseDescriptorBodyFileColour = null, impulseDescriptionColour = null;
		private boolean descriptionModeEnquiry = false;
		
		//establish description view controller
		private UNICODE_DescriptionViewController descriptionViewController = null;
		
		//colour scheme variables
			//all
			private Color connectorColour = null, connectorGlowColour = null;
			//default mode
			private Color defaultModeImpulseFolderColour = null, defaultModeImpulseFileColour = null;
			//descriptive mode
			private Color descriptionModeImpulseFolderColour = null, descriptionModeImpulseFileColour = null;
			private Color descriptionModeFolderFontColour = null, descriptionModeFileFontColour = null;
			
		//searching 
		private boolean forwardNavigationActionEnquiry = false, firstLetterSearchModeEnquiry = false;
		private int searchButtonClickIndex = 0;
		
		//opacity level of impulse descriptors
		private float descriptorOpacityLevel = 0.0f;
        
	//connector glow properties
	private int connectorThickness = 0, connectorGlowThickness = 0, connectorGlowQualityMultiplier = 0, connectorGlowBrightness = 0;
	
	//establish search box
	private UNICODE_MessageBoxWindow searchBox = null;
	
	//establish screen dimension
	private Dimension screenDimension = null;
	
	//establish content location indicator
	private UNICODE_ContentLocationIndicator contentLocationIndicator = null;
	
	//establish identifier to show whether description mode is enabled
	private String viewModeAnswer = "";
	
    //descriptor RESIZING VARS
			//establish timer delay value - how much time before the next enlargement occurs?
			private int descriptorResizeDelay = 1;
			
			//establish descriptor resize indices. When a button is hovered over it will equate that index to this variable,
			//then this variable will be used to enlarge that button only, when passed to the button array in
			//timer action listener
			private int resizableDescriptorIndex = -1;
			
			//establish descriptor resizing timer
			private Timer descriptorResizingTimer = new Timer ( descriptorResizeDelay, new descriptorResizeActionListening ( ) );
			
			//establish max descriptor scale - when descriptor is hovered over, how much should it scale to before stop enlarging?
			private double dimensionAlterationRate = 0.8, projectedWidthPercentageRate = 8.0;
			
			//establish font name - the name that describes the external font file used to construct descriptor labels etc
			private UNICODE_CustomFont customFont = null;
			private String fontName = "";
			//establish dir displayer
			private Color directoryDisplayerBackgroundColour = null, directoryDisplayerForegroundColour = null;
			private UNICODE_DirectoryDisplayer directoryDisplayer = null;
			private boolean directoryDisplayerVisibilityEnquiry = false;
			//establish file manager
			private UNICODE_ConfigurationManager configurationManager = null;
			
			
			//dynamic highligher
			private UNICODE_DirectoryEditor directoryEditor = null;
			private UNICODE_DynamicHiglighter dynamicHiglighter = null;
			private boolean dynamicHighlightingModeEnquiry = false;
			private ImageIcon dynamicHighlightingImageIcon = null;
			private Image dynamicHighlightingImage = null;
			private String dynamicHighlightingStream = "data/temporary/currentContext.png", dynamicHighlightingDirectory = "data/temporary/";
			private ArrayList dynamicHighlightingAffectedFileList = new ArrayList ( );
			
		//Line renderer properties colour is derived via 'connectorColour'.
		private double lineRendererEntityLength = 0, lineRendererEntitySpatialDistance = 0;
		
		//establish camera
		private UNICODE_IMPULSE_FIELD_CAMERA impulseFieldCamera = null;
		private String cameraDisplayAnswer = "";
		
		//param bubble
		private UNICODE_ParameterBubble parameterBubble = null;
		
    //constructor
    public UNICODE_IMPULSE_FIELD ( UNICODE_MessageBoxWindow _searchBox, UNICODE_ConfigurationManager _configurationManager, String _fontName, double _dimensionAlterationRate, double _projectedWidthPercentageRate, String _viewModeAnswer, int _impulseWidth, int _impulseHeight, int _spatialMultiplier, double _scrollRate, int _maximumScrollDuration, String _fieldStream, boolean _homeStreamEnquiry, Dimension _screenDimension, int _folderIndicatorScaleFactor, UNICODE_DescriptionViewController _descriptionViewController, Color _connectorColour, Color _connectorGlowColour, Color _defaultModeImpulseFolderColour, Color _defaultModeImpulseFileColour, Color _descriptionModeImpulseFolderColour, Color _descriptionModeImpulseFileColour, Color _descriptionModeFolderFontColour, Color _descriptionModeFileFontColour, float _descriptorOpacityLevel, int _connectorThickness, int _connectorGlowThickness, int _connectorGlowQualityMultiplier, int _connectorGlowBrightness, Color _contentLocationIndicatorColor, Color _directoryDisplayerBackgroundColour, Color _directoryDisplayerForegroundColour, int _contentLocationIndicatorSpaceSeparator, int _contentLocationIndicatorMinimumWidth, int _contentLocationIndicatorMaximumWidth, int directoryDisplayerVisualDisplayLimit, double _lineRendererEntityLength, double _lineRendererEntitySpatialDistance, String _cameraDisplayAnswer )
    {
		//search box
		searchBox = _searchBox;
		
		//establish file manager
		configurationManager = _configurationManager;
		
		//establish descriptor font name
		fontName = _fontName;
		customFont = new UNICODE_CustomFont ( "data/fonts/" );
		
		//establish view mode answer
		viewModeAnswer = _viewModeAnswer;
	
		//establish colour scheme vars
			//all
			connectorColour = _connectorColour;
			connectorGlowColour = _connectorGlowColour;
			//default mode
			defaultModeImpulseFolderColour = _defaultModeImpulseFolderColour;
			defaultModeImpulseFileColour = _defaultModeImpulseFileColour;
			//description mode
			descriptionModeImpulseFolderColour = _descriptionModeImpulseFolderColour;
			descriptionModeImpulseFileColour = _descriptionModeImpulseFileColour;
			descriptionModeFolderFontColour = _descriptionModeFolderFontColour;
			descriptionModeFileFontColour = _descriptionModeFileFontColour;
		
		//establish glow properties
		connectorThickness = _connectorThickness; 
		connectorGlowThickness = _connectorGlowThickness;
		connectorGlowQualityMultiplier = _connectorGlowQualityMultiplier;
		connectorGlowBrightness = _connectorGlowBrightness;
		
		//opacity level of impulse descriptors
		descriptorOpacityLevel = _descriptorOpacityLevel;
			
		//establish screen dimension
		screenDimension = _screenDimension;
			
		//compute minimumNodeFieldCardinalityBeforeChopping
		minimumNodeFieldCardinalityBeforeChopping = ( ( int ) screenDimension.getWidth ( ) / 100 ) + 2;
		
		//establish description view controller
		descriptionViewController = _descriptionViewController;

        //establish impulse dimension
        impulseWidth = _impulseWidth;
        impulseHeight = _impulseHeight;
		
		//establish spatial multiplier ( will directly affect the proximity latency between impulses ) 
		spatialMultiplier = _spatialMultiplier;
        
        //establish field stream
        fieldStream = _fieldStream;
        
        //establish home stream enquiry
        homeStreamEnquiry = _homeStreamEnquiry;
        
        //scroll rate and max scroll duration
        scrollRate = _scrollRate;
		maximumScrollDuration = _maximumScrollDuration;
		
		//establish content location indicator
		contentLocationIndicator = new UNICODE_ContentLocationIndicator ( _contentLocationIndicatorColor, _contentLocationIndicatorSpaceSeparator, _contentLocationIndicatorMinimumWidth, _contentLocationIndicatorMaximumWidth, screenDimension, scrollRate );
        
        //establish home folder array list wrt generated list function
        StreamFolderFileList = generateStreamFolderDirectories ( fieldStream );
        StreamFolderSearchNamesList = generateStreamFolderSearchNames ( fieldStream );
        
        //estabish max impulses
        int fileCardinality = StreamFolderFileList.size ( );
        maxImpulses = fileCardinality;
        
		//establish folderIndicatorScaleFactor
		folderIndicatorScaleFactor = _folderIndicatorScaleFactor;

		//initialise previous directory as home folder
		//getPreviousFieldStreamList ( ).add ( fieldStream );

		
		//establish resizing requirements
		dimensionAlterationRate = _dimensionAlterationRate;
		projectedWidthPercentageRate = _projectedWidthPercentageRate;
		
		//establish directory displayer
			//setup colours
			directoryDisplayerBackgroundColour = _directoryDisplayerBackgroundColour;
			directoryDisplayerForegroundColour = _directoryDisplayerForegroundColour;
			//create instance
			directoryDisplayer = new UNICODE_DirectoryDisplayer ( customFont, configurationManager, 100, 100, fontName, 13.0f, this, "core:", directoryDisplayerBackgroundColour, directoryDisplayerForegroundColour, ( getContentLocationIndicatorMaxWidth ( ) * 3 ), directoryDisplayerVisualDisplayLimit );
	
		//establish dynamic highlighter
		directoryEditor = new UNICODE_DirectoryEditor ( );
		dynamicHiglighter = new UNICODE_DynamicHiglighter ( 10, 10, new Color ( 245, 245, 245 ), new Color ( 222, 222, 222 ) );
	
		//initialise line entity properties
		lineRendererEntityLength = _lineRendererEntityLength;
		lineRendererEntitySpatialDistance = _lineRendererEntitySpatialDistance;
		
		//etstablish camera display answer
		cameraDisplayAnswer = _cameraDisplayAnswer;
		
		//establish parameter bubble
		parameterBubble = new UNICODE_ParameterBubble ( 80, 200, 4, ( int ) configurationManager.getBufferDimensionFromFile ( ).getWidth ( ), this );

        //add mouse listening
        addMouseMotionListener ( new mouseListening ( ) );
        addMouseListener ( new mouseListening ( ) );

        //add mouse wheel listener
        addMouseWheelListener ( new mouseWheelListenening ( ) );
        
        //set focus to this panel
        setFocusable ( true );
	}
	

        
    //paint component
    public void paintComponent ( Graphics graphics )
    {
        super.paintComponent ( graphics );
        Graphics2D graphics2d = ( Graphics2D ) graphics;
       
	    drawFieldComponents ( graphics, graphics2d );
    }
    
    //get panel midline
    public int getPanelMidpointY ( )
    {
        return this.getHeight ( ) / 2 - 100;
    }
    
    //get impulse list
    public ArrayList <UNICODE_IMPULSE_SHAPE> getImpulseList ( )
    {
        return impulses;
    }
    
    
    public String getFieldStream ( )
    {
        return fieldStream;
    }
	
	public int getFieldAccessCounter ( )
	{
		return fieldAccessCounter;
	}
	
	public double getScrollRate ( )
	{
		return scrollRate;
	}
	
	public UNICODE_DirectoryDisplayer getDirectoryDisplayer ( )
	{
		return directoryDisplayer;
	}
	
	public int getMaxBubblesForCurrentlyViewableField ( )
	{
        // //reset bounds according to how many nodes where found, with respect to the spatial order in which they were born.
		// if ( maxImpulses < minimumNodeFieldCardinalityBeforeChopping )
			// setBounds ( getX ( ), getY ( ), getWidth ( ) + minimumNodeFieldCardinalityBeforeChopping, getHeight ( ) ); //AN APPROPRIATE FORMULAE FOR EXTENDING THE FRAME'S WIDTH, WITH RESPECT TO THE NEW NODES FOUND.
		// else
			// setBounds ( getX ( ), getY ( ), ( maxImpulses * ( impulseWidth * maxImpulses ) ), getHeight ( ) ); //AN APPROPRIATE FORMULAE FOR EXTENDING THE FRAME'S WIDTH, WITH RESPECT TO THE NEW NODES FOUND.
		return 0;
	}
	
	public void setFieldAccessCounter ( int value )
	{
		fieldAccessCounter = value;
	}
	
	public void incFieldAccessCounter ( )
	{
		fieldAccessCounter ++;
	}
	
	public void decFieldAccessCounter ( )
	{
		fieldAccessCounter --;
	}	
	
	public ArrayList getPreviousFieldStreamList ( )
	{
		return previousFieldStreamList;
	}
	
	public void growPreviousFieldStreamList ( String value )
	{
		previousFieldStreamList.add ( value );
	}
	
	public void shrinkPreviousFieldStreamList ( )
	{
		previousFieldStreamList.remove ( previousFieldStreamList.size ( ) - 1 );
	}
	
    public void setFieldStream ( String value )
    {
        fieldStream = value;
    }
    
    public boolean getHomeStreamEnquiry ( )
    {
        return homeStreamEnquiry;
    }
	
	public boolean getDescriptionModeEnquiry ( )
	{
		return descriptionModeEnquiry;
	}
	
	public ArrayList <UNICODE_IMPULSE_DESCRIPTOR> getDescriptorList ( )
	{
		return impulseDescriptors;
	}
	
	public void setDescriptionModeEnquiry ( boolean value )
	{
		descriptionModeEnquiry = value;
	}
	
	public boolean getStreamEmptyEnquiry ( String value )
	{
		return conveniencePack.getDirectoryCardinality ( value ) > 0;
	}
	
	public ArrayList getStreamFolderFileList ( )
	{
		return StreamFolderFileList;
	}
	
	public int getSearchButtonClickIndex ( )
	{
		return searchButtonClickIndex;
	}
	
	public void resetSearchButtonClickIndex ( )
	{
		searchButtonClickIndex = 0;
	}
	
	public void setSearchButtonClickIndex ( int value )
	{
		searchButtonClickIndex = value;
	}
	
	public void incSearchButtonClickIndex ( )
	{
		searchButtonClickIndex ++;
	}
	
	public String getViewModeAnswer ( )
	{
		return viewModeAnswer;
	}
	
	public void setViewModeAnswer ( String value )
	{
		viewModeAnswer = value;
		descriptionViewController.setValue ( value );
		configurationManager.updateDescriptionViewController ( descriptionViewController );
	}
	
	//display directory displayer
	public void showDirectoryDisplayer ( )
	{
		directoryDisplayer.show ( false, 0 );
	}

	//if forwardNaviagationEnquiry is supplied false, it is to be assumed that
	//the user is trying to return to previous directory.
	//certain params are left blank based on the type of mutation required.
	//eg.commencingLetter and keyWOrds left blank in navigation mutation
	public void reInitializeField ( String value, boolean forwardNavigationEnquiry )
	{
		if ( getStreamEmptyEnquiry ( value ) ) //if folder is not empty.
		{
				//the user may either forward or backward navigate.
				//this counter will be used to eventually prevent attempt to naviaget backwards, 
				//when there exists no directory to act upon. So, this counter will increment if forward,
				//decrement if backward.
				if ( forwardNavigationEnquiry )
				{
					//store fieldStream as previous stream before mutating currentFieldStream
					growPreviousFieldStreamList ( getFieldStream ( ) );
					//increment fieldAccessCounter
					incFieldAccessCounter ( );
				}
			//reset main stream
			setFieldStream ( value );
			//reset list of entities streams
			StreamFolderFileList = generateStreamFolderDirectories ( value ); 
			//reset list of entities stream names
			StreamFolderSearchNamesList = generateStreamFolderSearchNames ( value );
			
			//reset max impulse value
			maxImpulses = StreamFolderFileList.size ( ); //reset max impulses
			//reset dimension lists
			xCoordList.clear ( );
			yCoordList.clear ( );
			//reset impulses and connectors
			impulses.clear ( );
			impulseConnectors.clear ( );
			//reset descriptors list
			impulseDescriptors.clear ( );
		}
		else
			new UNICODE_MessageBoxWindow ( "Directory is empty!", 0.9f, new Color ( 10, 10, 10 ), Color.black, Color.gray, Color.white, true, "data/images/all/message box/okay/","rr", 10, 10, 0 );
	}
	
	//field mutation for search result generation
	public void reInitializeFieldSearchModeA ( String value, String commencingLetter )
	{
		//reset list of entities streams
		StreamFolderFileList = generateStreamFolderDirectoriesSearchModeA ( value, commencingLetter ); 
		//reset list of entities stream names
		StreamFolderSearchNamesList = generateStreamFolderSearchNamesSearchModeA ( value, commencingLetter );
				
		//reset max impulse value
		maxImpulses = StreamFolderFileList.size ( ); //reset max impulses
		//reset dimension lists
		xCoordList.clear ( );
		yCoordList.clear ( );
		//reset impulses and connectors
		impulses.clear ( );
		impulseConnectors.clear ( );
		//reset descriptors list
		impulseDescriptors.clear ( );
	}
	
	//field mutation for search result generation
	public void reInitializeFieldSearchModeB ( String value, String keyWord )
	{
		//reset list of entities streams
		StreamFolderFileList = generateStreamFolderDirectoriesSearchModeB ( value, keyWord ); 
		//reset list of entities stream names
		StreamFolderSearchNamesList = generateStreamFolderSearchNamesSearchModeB ( value, keyWord );
				
		//reset max impulse value
		maxImpulses = StreamFolderFileList.size ( ); //reset max impulses
		//reset dimension lists
		xCoordList.clear ( );
		yCoordList.clear ( );
		//reset impulses and connectors
		impulses.clear ( );
		impulseConnectors.clear ( );
		//reset descriptors list
		impulseDescriptors.clear ( );
	}
	
	//field mutation for search result generation
	public void reInitializeFieldSearchModeC ( String value )
	{
		//reset list of entities streams
		StreamFolderFileList = generateStreamFolderDirectoriesSearchModeC ( value ); 
		//reset list of entities stream names
		StreamFolderSearchNamesList = generateStreamFolderSearchNamesSearchModeC ( value );
				
		//reset max impulse value
		maxImpulses = StreamFolderFileList.size ( ); //reset max impulses
		//reset dimension lists
		xCoordList.clear ( );
		yCoordList.clear ( );
		//reset impulses and connectors
		impulses.clear ( );
		impulseConnectors.clear ( );
		//reset descriptors list
		impulseDescriptors.clear ( );
	}
	
	//field mutation for search result generation
	public void reInitializeFieldSearchModeD ( String value )
	{
		//reset list of entities streams
		StreamFolderFileList = generateStreamFolderDirectoriesSearchModeD ( value ); 
		//reset list of entities stream names
		StreamFolderSearchNamesList = generateStreamFolderSearchNamesSearchModeD ( value );
				
		//reset max impulse value
		maxImpulses = StreamFolderFileList.size ( ); //reset max impulses
		//reset dimension lists
		xCoordList.clear ( );
		yCoordList.clear ( );
		//reset impulses and connectors
		impulses.clear ( );
		impulseConnectors.clear ( );
		//reset descriptors list
		impulseDescriptors.clear ( );
	}
	
	//field mutation for dynamic highlighting mutation result
	public void reInitializeFieldSearchModeE ( String value, ArrayList ignoranceList )
	{
		//reset list of entities streams
		StreamFolderFileList = generateStreamFolderDirectoriesE ( value, ignoranceList ); 
		//reset list of entities stream names
		StreamFolderSearchNamesList = generateStreamFolderSearchNamesE ( value, ignoranceList );
				
		//reset max impulse value
		maxImpulses = StreamFolderFileList.size ( ); //reset max impulses
		//reset dimension lists
		xCoordList.clear ( );
		yCoordList.clear ( );
		//reset impulses and connectors
		impulses.clear ( );
		impulseConnectors.clear ( );
		//reset descriptors list
		impulseDescriptors.clear ( );
	}
	
	public void drawFieldComponents ( Graphics graphics, Graphics2D graphics2d )
	{  
		generateFieldComponents ( );
		if ( dynamicHighlightingModeEnquiry )
			graphics2d.drawImage ( dynamicHighlightingImage, scrollAmountTracker, 0, this );

                    
        //draw impulse entities
		impulseFieldCamera.enableImpulseComponentRendering ( graphics, graphics2d, this, maxImpulses, lineVisibilityEnquiry, lineRendererEntityLength, lineRendererEntitySpatialDistance, descriptionViewController, impulses, impulseDescriptors, impulseConnectors, rotationDirection, scrollAmountTracker );
		
					
			//since the impulse action dock is now on a window rather than a panel, it no longer binds its orientation to the parent panel, since it's a JFrame.
			//So only when scrolling occurs, (mouse rotation mutation) should we consider the new location of the parent impulse field panel, and adjust the location of the 
			//impulse window then.
			if ( ( rotationDirection > 0 ) && ( getX ( ) > 0 ) )
			{	
				int panelScrollRightwardsIncrementAmount = 0;
				
				if ( conveniencePack.getIntegerIncrementEnquiry ( getX ( ), getWidth ( ) ) )
					panelScrollRightwardsIncrementAmount += getX ( );

                UNICODE_IMPULSE_SHAPE impulseShape = impulses.get ( currentImpulseIndex );
                UNICODE_IMPULSE_BODY impulseBody = impulseShape.getBody ( );
				impulseActionWindow.setBounds ( ( int ) ( ( impulseBody.getBody ( ).getX ( ) - 110/2 ) + impulseBody.getBody ( ).getWidth ( )/2 ) + ( int ) panelScrollRightwardsIncrementAmount, ( int ) ( ( impulseBody.getBody ( ).getY ( ) - 110/2 ) + impulseBody.getBody ( ).getHeight ( )/2 ), 110, 110 );
			}
			else if ( ( rotationDirection < 0 ) && ( getX ( ) < 0 ) )
			{			
				int panelScrollLeftwardsIncrementAmount = 0;
				
				if ( conveniencePack.getIntegerDecrementEnquiry ( getX ( ), -getWidth ( ) ) )
					panelScrollLeftwardsIncrementAmount -= getX ( );
				
                UNICODE_IMPULSE_SHAPE impulseShape = impulses.get ( currentImpulseIndex );
                UNICODE_IMPULSE_BODY impulseBody = impulseShape.getBody ( );
				impulseActionWindow.setBounds ( ( int ) ( ( impulseBody.getBody ( ).getX ( ) - 110/2 ) + impulseBody.getBody ( ).getWidth ( )/2 ) - ( int ) panelScrollLeftwardsIncrementAmount, ( int ) ( ( impulseBody.getBody ( ).getY ( ) - 110/2 ) + impulseBody.getBody ( ).getHeight ( )/2 ), 110, 110 );
			}				
			
		impulseFieldCamera.draw ( graphics2d, Color.lightGray );
		
		contentLocationIndicator.draw ( graphics2d );
		dynamicHiglighter.draw ( graphics, graphics2d );
		parameterBubble.draw ( graphics2d, new Color ( 245, 245, 245 ) );
		graphics2d.dispose ( );
	}
	
	public void generateFieldComponents ( )
	{
		prebakedLowerBoundLimitForY = getPanelMidpointY ( ) + 100;
		prebakedUpperBoundLimitForY = getPanelMidpointY ( ) - 100;
				
		impulseFieldCamera = new UNICODE_IMPULSE_FIELD_CAMERA ( 60, prebakedLowerBoundLimitForY, prebakedUpperBoundLimitForY, configurationManager, cameraDisplayAnswer );
		
        //reset bounds according to how many nodes where found, with respect to the spatial order in which they were born.
		if ( maxImpulses < minimumNodeFieldCardinalityBeforeChopping )
			setBounds ( getX ( ), getY ( ), getWidth ( ) + minimumNodeFieldCardinalityBeforeChopping, getHeight ( ) ); //AN APPROPRIATE FORMULAE FOR EXTENDING THE FRAME'S WIDTH, WITH RESPECT TO THE NEW NODES FOUND.
		else
			setBounds ( getX ( ), getY ( ), ( maxImpulses * ( impulseWidth * maxImpulses ) ), getHeight ( ) ); //AN APPROPRIATE FORMULAE FOR EXTENDING THE FRAME'S WIDTH, WITH RESPECT TO THE NEW NODES FOUND.


        //compute y limits
            //compute lowerBoundLimitY
            lowerBoundLimitY = prebakedLowerBoundLimitForY;
            //compute upperBoundLimitY
            upperBoundLimitY = prebakedUpperBoundLimitForY;
        //compute x limits
            //compute lowerBoundLimitX
            lowerBoundLimitX = initialLowerBoundX;
            //compute upperBoundLimitX
            upperBoundLimitX = initialUpperBoundX;
            
        //generate y coords
        for ( int i = 0; i < maxImpulses; i ++ )
        {
            int y = randomizer.nextInt ( lowerBoundLimitY ) + upperBoundLimitY;
            yCoordList.add ( y );
        }
            
        //generat x coords
        for ( int i = 0; i < maxImpulses; i ++ )
        {
            int x = randomizer.nextInt ( lowerBoundLimitX ) + upperBoundLimitX;
            
            xCoordList.add ( x );
            
            lowerBoundLimitX += impulseWidth * spatialMultiplier; //change the lower bound by increasing its value by a multiple of an impulse's width * 10.
            upperBoundLimitX += ( impulseWidth * spatialMultiplier ); //change the upper bound by the same value.
        }
        
        //generate impulse list
        for ( int i = 0; i < maxImpulses; i ++ )
            impulses.add ( new UNICODE_IMPULSE_SHAPE ( ( String ) StreamFolderFileList.get ( i ), ( String ) StreamFolderSearchNamesList.get ( i ), xCoordList.get ( i ), yCoordList.get ( i ), impulseWidth, impulseHeight, i, folderIndicatorScaleFactor, defaultModeImpulseFolderColour, defaultModeImpulseFileColour ) );
        
        //generate impulse connectors
        for ( int i = 0; i < maxImpulses; i ++ )
            if ( ( i + 1 ) < maxImpulses )
                impulseConnectors.add ( new UNICODE_IMPULSE_CONNECTOR ( xCoordList.get ( i ), yCoordList.get ( i ), xCoordList.get ( i + 1 ), yCoordList.get ( i + 1 ), impulses, descriptionViewController.getValue ( ), connectorColour, connectorGlowColour, connectorThickness, connectorGlowThickness, connectorGlowQualityMultiplier, connectorGlowBrightness ) );
        
		//generate impulse descriptions       
		for ( int i = 0; i < maxImpulses; i ++ )
			impulseDescriptors.add ( new UNICODE_IMPULSE_DESCRIPTOR ( impulses.get ( i ).getBody ( ), customFont, fontName, descriptionModeImpulseFolderColour, descriptionModeImpulseFileColour, descriptionModeFolderFontColour, descriptionModeFileFontColour, descriptorOpacityLevel, dimensionAlterationRate, projectedWidthPercentageRate ) );
	}
    
    //mouse listening
    private class mouseListening implements MouseListener, MouseMotionListener
    { 
        public void mouseClicked ( MouseEvent mEvent )
        { 
			if ( !parameterBubble.getParameterBubbleEnableEnquiry ( ) )
			{
				for ( int i = 0; i < maxImpulses; i ++ )
				{
					UNICODE_IMPULSE_SHAPE impulseShape = impulses.get ( i );
					UNICODE_IMPULSE_BODY impulseBody = impulseShape.getBody ( );  
					UNICODE_IMPULSE_DESCRIPTOR descriptorShapes = impulseDescriptors.get ( i );
					
					if ( getViewModeAnswer ( ).equals ( "off" ) )
					{
						if ( impulseShape.getShape ( ).contains ( mEvent.getX ( ), mEvent.getY ( ) ) )
						{
							impulseActionDock.audioPlayer.playAudio ( "enter.wav" );
							impulseActionDock.setVisible ( true );
							impulseActionWindow.setBounds ( ( int ) ( ( impulseBody.getBody ( ).getX ( ) - 110/2 ) + impulseBody.getBody ( ).getWidth ( )/2 ), ( int ) ( ( impulseBody.getBody ( ).getY ( ) - 110/2 ) + impulseBody.getBody ( ).getHeight ( )/2 ), 110, 110 );
							
							if ( impulseBody.representsDirectory ( impulseBody.getEntityStream ( ) ) )
								impulseActionDock.setBackground ( descriptionModeImpulseFolderColour );
							else
								impulseActionDock.setBackground ( descriptionModeImpulseFileColour );
							
							enableImpulseActionDonutWindow ( );
							currentImpulseIndex = impulseBody.getEntityIndex ( );
							nodeEntityMenuAnimationTimer.start ( );
						}
					}
					else if ( getViewModeAnswer ( ).equals ( "on" ) )
					{	
						if ( descriptorShapes.getShape ( ).contains ( mEvent.getX ( ), mEvent.getY ( ) ) )
						{
							UNICODE_IMPULSE_SHAPE impulseShapeAtSelectedDescriptorShape = impulses.get ( i );
							UNICODE_IMPULSE_BODY impulseBodyAtSelectedDescriptorShape = impulseShapeAtSelectedDescriptorShape.getBody ( );  
						
							impulseActionDock.audioPlayer.playAudio ( "enter.wav" );
							impulseActionDock.setVisible ( true );
							impulseActionWindow.setBounds ( ( int ) ( ( impulseBodyAtSelectedDescriptorShape.getBody ( ).getX ( ) - 110/2 ) + impulseBodyAtSelectedDescriptorShape.getBody ( ).getWidth ( )/2 ), ( int ) ( ( impulseBodyAtSelectedDescriptorShape.getBody ( ).getY ( ) - 110/2 ) + impulseBodyAtSelectedDescriptorShape.getBody ( ).getHeight ( )/2 ), 110, 110 );
							
							if ( impulseBodyAtSelectedDescriptorShape.representsDirectory ( impulseBodyAtSelectedDescriptorShape.getEntityStream ( ) ) )
								impulseActionDock.setBackground ( descriptionModeImpulseFolderColour );
							else
								impulseActionDock.setBackground ( descriptionModeImpulseFileColour );
								
							enableImpulseActionDonutWindow ( );
							currentImpulseIndex = impulseBody.getEntityIndex ( );
							nodeEntityMenuAnimationTimer.start ( );
						}
					}
				}
					
				dynamicHiglighter.toggleContactEnquiryStateAtMousePress ( mEvent, maxImpulses, impulses, impulseDescriptors );
				repaint ( );
			}            
        }

        public void mouseReleased ( MouseEvent mEvent )
        {
			if ( !parameterBubble.getParameterBubbleEnableEnquiry ( ) )
			{
				dynamicHiglighter.toggleContactEnquiryStateAtMouseRelease ( mEvent, maxImpulses, impulses, impulseDescriptors );
				dynamicHiglighter.establishMouseReleasedEvent ( getNodeHighligherThreads ( ) );
				disableDynamicHighlightingPerformanceMode ( );	
			}
        }
        
        public void mouseEntered ( MouseEvent mEvent )
        {
        }  
        
        public void mouseExited ( MouseEvent mEvent )
        {
        }
     
        public void mousePressed ( MouseEvent mEvent )
        {
			if ( !parameterBubble.getParameterBubbleEnableEnquiry ( ) )
			{		
				//establish mouse pressed coords
				heldMouseCoords = mEvent.getPoint ( );

				dynamicHiglighter.establishMousePressedEvent ( mEvent );
				enableDynamicHighlightingPerformanceMode ( );	
				
				repaint ( );
			}
        }
        
        public void mouseDragged ( MouseEvent mEvent )
        {         
            // for ( int i = 0; i < maxImpulses; i ++ )
            // {
                // UNICODE_IMPULSE_SHAPE impulseShape = impulses.get ( i );
                // if ( impulseShape.getShape ( ).contains ( mEvent.getX ( ), mEvent.getY ( ) ) )
                // {  
                    // //establish dragged mouse coordinates
                    // draggedMouseCoords = mEvent.getLocationOnScreen ( );   
            
                    // double relocationX = draggedMouseCoords.getX ( ) - heldMouseCoords.getX ( );
                    // double relocationY = draggedMouseCoords.getY ( ) - heldMouseCoords.getY ( );
                    
                    // //impulseShape.setRelocationEnquiry ( true );
                    // //impulseShape.setRelocationOrientation ( relocationX, relocationY );
                // }
            // }
			if ( !parameterBubble.getParameterBubbleEnableEnquiry ( ) )
			{
				dynamicHiglighter.establishMouseDraggedEvent ( mEvent, scrollAmountTracker );
				enableDynamicHighlightingPerformanceMode ( );		
			
				repaint ( );
			}
        }
        public void mouseMoved ( MouseEvent mEvent )
        {
			parameterBubble.enableMoseMovementResponse ( mEvent );
			
			if ( parameterBubble.getParameterBubbleEnableEnquiry ( ) )
				parameterBubble.updateParameterBubbleLocation ( mEvent );
			else
			{
				//search box aider
				for ( int i = 0; i < maxImpulses; i ++ )
				{
					UNICODE_IMPULSE_SHAPE impulseShape = impulses.get ( i );
					UNICODE_IMPULSE_BODY impulseBody = impulseShape.getBody ( );  
					UNICODE_IMPULSE_DESCRIPTOR descriptorShape = impulseDescriptors.get ( i );
					
					if ( getViewModeAnswer ( ).equals ( "off" ) )
					{
						if ( impulseShape.getShape ( ).contains ( mEvent.getX ( ), mEvent.getY ( ) ) )
						{
							if ( impulseShape.getBody ( ).representsDirectory ( impulseShape.getBody ( ).getEntityStream ( ) ) )
							{
								searchBox.getPanel ( ).getTextField ( ).setText ( "folders:" );
							}
							else
							{
								searchBox.getPanel ( ).getTextField ( ).setText ( "files:" );
							}
						}
					}
					else if ( getViewModeAnswer ( ).equals ( "on" ) )
					{	
						if ( descriptorShape.getShape ( ).contains ( mEvent.getX ( ), mEvent.getY ( ) ) )
						{
							if ( impulseShape.getBody ( ).representsDirectory ( impulseShape.getBody ( ).getEntityStream ( ) ) )
							{
								searchBox.getPanel ( ).getTextField ( ).setText ( "folders:" );
							}
							else
							{
								searchBox.getPanel ( ).getTextField ( ).setText ( "files:" );
							}
						}
					}
				}
				
				///////////////////////////////////////////////
				//resizing response generation
				///////////////////////////////////////////////
				for ( int descriptors = 0; descriptors < impulseDescriptors.size ( ); descriptors ++ )
				{
					UNICODE_IMPULSE_DESCRIPTOR descriptorShape = impulseDescriptors.get ( descriptors );
					
					if ( descriptorShape.getShape ( ).contains ( mEvent.getX ( ), mEvent.getY ( ) ) )
					{
						descriptorResizingTimer.start ( );
						//place a break after getting button index
						//otherwise the timer will only be called when
						//the descriptors has reached the end of its value in its for loop
						//instead of the value index we need to resize the appropriate button
						currentDescriptorIndex = descriptors;
						impulseDescriptors.get ( descriptors ).setResizeEnquiry ( true );
						resizableDescriptorIndex = descriptors; break;//tell the resize timer which button to resize
					}
					else
					{
						descriptorResizingTimer.stop ( ); //stop resize timer
						impulseDescriptors.get ( descriptors ).setResizeEnquiry ( false );
						impulseDescriptors.get ( descriptors ).resetDescriptor ( );
					}
				}

			}
			
            repaint ( );
        }
    }
    
    //FOR IMPULSE FIELD NAVIGATION
		//generate arraylist that contains each C:Drive dir.
		public ArrayList generateStreamFolderDirectories ( String _fieldStream )
		{
			ArrayList value = new ArrayList ( );
			
			File directory = new File ( _fieldStream );
			String [ ] fileList = directory.list ( );
			
			for ( int i = 0; i < fileList.length; i ++ )
				value.add ( _fieldStream + fileList [ i ] );
				
			return value;
		}
		//generate arraylist that contains each search name. (what the user will search for via each impulse node.
		public ArrayList generateStreamFolderSearchNames ( String _fieldStream )
		{
			ArrayList value = new ArrayList ( );
			
			File directory = new File ( _fieldStream );
			String [ ] fileList = directory.list ( );
			
			for ( int i = 0; i < fileList.length; i ++ )
				value.add ( fileList [ i ] );
				
			return value;
		}
	
	//FOR IMPULSE FIELD SEARCHING..
		//A.SEARCH NAMES THAT BEGIN WITH 'USER' SPECIFIED 'LETTER', WE WILL REGENERATE THE IMPULSE FIELD WITH THOSE ONLY. 
			//generate arraylist that contains each C:Drive dir, only if the file/folder begins with supplied letter
			public ArrayList generateStreamFolderDirectoriesSearchModeA ( String _fieldStream, String commencingLetter )
			{
				ArrayList value = new ArrayList ( );
				
				File directory = new File ( _fieldStream );
				String [ ] fileList = directory.list ( );
				
				for ( int i = 0; i < fileList.length; i ++ )
					//only build the array list, with respect to the number of names that begin with the commencinLetter
					if ( fileList [ i ].startsWith ( commencingLetter ) )
						value.add ( _fieldStream + fileList [ i ] );
					
				return value;
			}
			
			//generate arraylist that contains each search name. (what the user will search for via each impulse node.
			//,Ofcourse, only if the file/folder begins with supplied letter
			public ArrayList generateStreamFolderSearchNamesSearchModeA ( String _fieldStream, String commencingLetter )
			{
				ArrayList value = new ArrayList ( );
				
				File directory = new File ( _fieldStream );
				String [ ] fileList = directory.list ( );
				
				for ( int i = 0; i < fileList.length; i ++ )
					//only build the array list, with respect to the number of names that begin with the commencinLetter
					if ( fileList [ i ].startsWith ( commencingLetter ) )
						value.add ( fileList [ i ] );
					
				return value;
			}
			
		//B.SEARCH NAMES THAT BEGIN WITH 'USER' SPECIFIED 'KEYWORD', WE WILL REGENERATE THE IMPULSE FIELD WITH THOSE ONLY. 
			//generate arraylist that contains each C:Drive dir, only if the file/folder contains with the keyword
			public ArrayList generateStreamFolderDirectoriesSearchModeB ( String _fieldStream, String keyword )
			{
				ArrayList value = new ArrayList ( );
				
				File directory = new File ( _fieldStream );
				String [ ] fileList = directory.list ( );
				
				for ( int i = 0; i < fileList.length; i ++ )
				{
					String fullString = fileList [ i ];
					String formattedFullString = fullString.toLowerCase ( ), formattedSearchString = keyword.toLowerCase ( );
					
					//only build the array list, with respect to the number of names that contain the keyword
					if ( conveniencePack.stringSubsetEnquiry ( formattedFullString, formattedSearchString ) )
						value.add ( _fieldStream + fullString );
				}
				return value;
			}
			
			//generate arraylist that contains each search name. (what the user will search for via each impulse node.
			//,Ofcourse, only if the file/folder contains the supplied keyword
			public ArrayList generateStreamFolderSearchNamesSearchModeB ( String _fieldStream, String keyword )
			{
				ArrayList value = new ArrayList ( );
				
				File directory = new File ( _fieldStream );
				String [ ] fileList = directory.list ( );
				
				for ( int i = 0; i < fileList.length; i ++ )
				{
					String fullString = fileList [ i ];
					String formattedFullString = fullString.toLowerCase ( ), formattedSearchString = keyword.toLowerCase ( );
					
					//only build the array list, with respect to the number of names that contain the keyword
					if ( conveniencePack.stringSubsetEnquiry ( formattedFullString, formattedSearchString ) )
						value.add ( fullString );
				}
				return value;
			}
			
		//C.RETURN ENTIES THAT REPRESENT FOLDERS ONLY
			//generate arraylist that contains each C:Drive dir, only if the entity represents a folder
			public ArrayList generateStreamFolderDirectoriesSearchModeC ( String _fieldStream )
			{
				ArrayList value = new ArrayList ( );
				
				File directory = new File ( _fieldStream );
				String [ ] fileList = directory.list ( );
				
				for ( int i = 0; i < fileList.length; i ++ )
					if ( new File ( _fieldStream + fileList [ i ] ).isDirectory ( ) ) //add to list only if this is a directory
						value.add ( _fieldStream + fileList [ i ] );
					
				return value;
			}
			
			//generate arraylist, only if the entity represents a folder
			public ArrayList generateStreamFolderSearchNamesSearchModeC ( String _fieldStream )
			{
				ArrayList value = new ArrayList ( );
				
				File directory = new File ( _fieldStream );
				String [ ] fileList = directory.list ( );
				
				for ( int i = 0; i < fileList.length; i ++ )
					if ( new File ( _fieldStream + fileList [ i ] ).isDirectory ( ) ) //add to list only if this is a directory
						value.add ( fileList [ i ] );
					
				return value;
			}
			
		//D.RETURN ENTIES THAT DON'T REPRESENT FOLDERS, but rather files.
			//generate arraylist that contains each C:Drive dir, only if the entity represents a file
			public ArrayList generateStreamFolderDirectoriesSearchModeD ( String _fieldStream )
			{
				ArrayList value = new ArrayList ( );
				
				File directory = new File ( _fieldStream );
				String [ ] fileList = directory.list ( );
				
				for ( int i = 0; i < fileList.length; i ++ )
					if ( !new File ( _fieldStream + fileList [ i ] ).isDirectory ( ) ) //add to list only if this is a directory
						value.add ( _fieldStream + fileList [ i ] );
					
				return value;
			}
			
			//generate arraylist, only if the entity represents a folder
			public ArrayList generateStreamFolderSearchNamesSearchModeD ( String _fieldStream )
			{
				ArrayList value = new ArrayList ( );
				
				File directory = new File ( _fieldStream );
				String [ ] fileList = directory.list ( );
				
				for ( int i = 0; i < fileList.length; i ++ )
					if ( !new File ( _fieldStream + fileList [ i ] ).isDirectory ( ) ) //add to list only if this is a directory
						value.add ( fileList [ i ] );
					
				return value;
			}
			String test = "";
		//E.RETURN ENTIES THAT DON'T INCLUDE FILE NAMES IN DELETION/COPY/CUT ACTION LIST.
			//generate arraylist that contains each C:Drive dir.
			public ArrayList generateStreamFolderDirectoriesE ( String _fieldStream, ArrayList ignoranceList  )
			{
				ArrayList value = new ArrayList ( );
				
				File directory = new File ( _fieldStream );
				String [ ] fileList = directory.list ( );
				
				for ( int i = 0; i < fileList.length; i ++ )
					if ( !fileList [ i ].equals ( ignoranceList.get ( i ) ) )
						value.add ( _fieldStream + fileList [ i ] );
						

				return value;
			}
			//generate arraylist that contains each search name. (what the user will search for via each impulse node.
			public ArrayList generateStreamFolderSearchNamesE ( String _fieldStream, ArrayList ignoranceList )
			{
				ArrayList value = new ArrayList ( );
				
				File directory = new File ( _fieldStream );
				String [ ] fileList = directory.list ( );
				
				for ( int i = 0; i < fileList.length; i ++ )
					if ( !fileList [ i ].equals ( ignoranceList.get ( i ) ) )
						value.add ( fileList [ i ] );
					
				return value;
			}
    
    //generate target impulse index
    public int getTargetImpulseIndex ( String searchName )
    {
        int value = 0;
        for ( int i = 0; i < StreamFolderSearchNamesList.size ( ); i ++ )
            if ( StreamFolderSearchNamesList.get ( i ).equals ( searchName ) )
                value = i; 
        return value;
    }
	
	public int getContentLocationIndicatorMaxWidth ( )
	{
		return ( int ) contentLocationIndicator.getMaxWidth ( );
	}
	
    //line visibility controller
    public boolean getLineVisibilityEnquiry ( )
    {
        return lineVisibilityEnquiry;
    }
	
	public boolean getDirectoryDisplayerVisibilityEnquiry ( )
	{
		return directoryDisplayerVisibilityEnquiry;
	}
	
    public void setLineVisibilityEnquiry ( boolean value )
    {
        lineVisibilityEnquiry = value;
    }
    
    public void toggleLineVisibilityControl ( )
    {
        if ( getLineVisibilityEnquiry ( ) )
            setLineVisibilityEnquiry ( false );
        else if ( !getLineVisibilityEnquiry ( ) )
            setLineVisibilityEnquiry ( true );
			
		repaint ( );
    }
	
	public void setDirectoryDisplayerVisibilityEnquiry ( boolean value )
	{
		directoryDisplayerVisibilityEnquiry = value;
	}
	
    public void toggleDirectoryDisplayerVisibilityControl ( )
    {
        if ( getDirectoryDisplayerVisibilityEnquiry ( ) )
            directoryDisplayer.hide ( );
        else if ( !getDirectoryDisplayerVisibilityEnquiry ( ) )
            directoryDisplayer.show ( false, 0 );
			
		repaint ( );
	}
	
	public void updateDirectoryDisplayer ( )
	{
		directoryDisplayer.update ( );
	}
	
    public void toggleDescriptionModeControl ( UNICODE_AudioPlayer audioPlayer, String successAudioStream, String failureAudioStream, UNICODE_ConfigurationManager configurationManager )
    {
        if ( configurationManager.getDescriptionViewAnswerFromFile ( ).equals ( "on" ) )
		{
            descriptionViewController.setValue ( "off" );
			audioPlayer.playAudio ( failureAudioStream );
		}
        else if ( configurationManager.getDescriptionViewAnswerFromFile ( ).equals ( "off" ) )
		{
			descriptionViewController.setValue ( "on" );
			audioPlayer.playAudio ( successAudioStream );
		}
		
		configurationManager.updateDescriptionViewController ( descriptionViewController );
		setViewModeAnswer ( configurationManager.getDescriptionViewAnswerFromFile ( ) );
			
		repaint ( );
    }
 
	
 
    //dock animation functions
        //dock animation listener
        private class nodeEntityMenuAnimationListener implements ActionListener
        {
            public void actionPerformed ( ActionEvent actionEvent )
            {
                impulseActionDock.performAxisAnimation ( 70, 180, 2.4f, 0.756f, 0.3f, "clockwise", nodeEntityMenuAnimationTimer );
                repaint ( );
            }
        }    
        public UNICODE_MenuPanel getImpulseActionDock ( )
        {
            return impulseActionDock;
        }
        public UNICODE_DonutMenuWindow getImpulseActionWindow ( )
        {
            return impulseActionWindow;
        }
        //mutators
        public void setImpulseActionDock ( UNICODE_MenuPanel _impulseActionDock )
        {
            impulseActionDock = _impulseActionDock;
        }
		public void setImpulseActionWindow ( UNICODE_DonutMenuWindow _impulseActionWindow )
		{
			impulseActionWindow = _impulseActionWindow;
		}
		public void disableImpulseActionDonutWindow ( )
		{
			impulseActionWindow.setVisible ( false );
		}
		public void enableImpulseActionDonutWindow ( )
		{
			impulseActionWindow.setVisible ( true );
		}
        //accesor for current impulse index
        public int getCurrentImpulseIndex ( )
        {
            return currentImpulseIndex;
        }
		public boolean getFirstLetterSearchModeEnquiry ( )
		{
			return firstLetterSearchModeEnquiry;
		}
		public void setFirstLetterSearchModeEnquiry ( boolean value )
		{
			firstLetterSearchModeEnquiry = value;
		}
		public boolean getForwardNavigationActionEnquiry ( )
		{
			return forwardNavigationActionEnquiry;
		}
		public void setForwardNavigationActionEnquiry ( boolean value )
		{
			forwardNavigationActionEnquiry = value;
		}
    
    //scroll functions
        //mouse wheel controller - user driven
        private class mouseWheelListenening implements MouseWheelListener
        {
            public void mouseWheelMoved ( MouseWheelEvent mouseWheelEvent )
            {
                UNICODE_IMPULSE_SHAPE impulseShape = impulses.get ( currentImpulseIndex );
                UNICODE_IMPULSE_BODY impulseBody = impulseShape.getBody ( );
			
                rotationDirection = mouseWheelEvent.getWheelRotation ( );
                
				if ( !parameterBubble.getParameterBubbleEnableEnquiry ( ) )
				{
					if ( rotationDirection > 0 )
						slide ( "leftwards" );
					else if ( rotationDirection < 0 )
						slide ( "rightwards" );
				}
				else
					parameterBubble.updateParameterBubbleSize ( rotationDirection, 10 );
					
                repaint ( );
            }
        }
        
        //scroll action listeners - non - user driven
            //mutators
            public void setSearchName ( String value )
            {
                searchName = value;
            }
        
            //leftwards
            private class scrollRightwardsListener implements ActionListener
            {
                public void actionPerformed ( ActionEvent actionEvent ) 
                {
					if ( scrollDurationTracker < maximumScrollDuration )
					{
						if ( getX ( ) > - getLastDescriptorLocation ( ) )
						{
							UNICODE_IMPULSE_SHAPE impulseShape = impulses.get ( currentImpulseIndex );
							UNICODE_IMPULSE_BODY impulseBody = impulseShape.getBody ( );

							scrollLeftwardsAdjuster = getX ( ) - ( int )  scrollRate;
							setBounds ( scrollLeftwardsAdjuster, getY ( ), getWidth ( ), getHeight ( ) );
							contentLocationIndicator.performAnimation ( "rightwards" );
							directoryDisplayer.provideRightwardStabilization ( );
							scrollAmountTracker += scrollRate;
						}
					}
					else
						scrollRightwardsTimer.stop ( );
                }
            }
            //rightwards
            private class scrollLeftwardsListener implements ActionListener
            {
                public void actionPerformed ( ActionEvent actionEvent ) 
                {
					if ( scrollDurationTracker < maximumScrollDuration )
					{
						if ( getX ( ) < getFirstDescriptorLocation ( ) )
						{
							UNICODE_IMPULSE_SHAPE impulseShape = impulses.get ( currentImpulseIndex );
							UNICODE_IMPULSE_BODY impulseBody = impulseShape.getBody ( );
							
							scrollRightwardsAdjuster = getX ( ) + ( int ) scrollRate;
							setBounds ( scrollRightwardsAdjuster, getY ( ), getWidth ( ), getHeight ( ) );
							contentLocationIndicator.performAnimation ( "leftwards" );
							directoryDisplayer.provideLeftwardStabilization ( );	
							scrollAmountTracker -= scrollRate;
						}
					}
					else
						scrollLeftwardsTimer.stop ( );
                }
            }
			
			private class scrollDurationListener implements ActionListener
            {
                public void actionPerformed ( ActionEvent actionEvent ) 
                {
					scrollDurationTracker += 1000;
                }
            }
            //funtion to perfom node search by name supplied via button dock 0,'s text field.
            public void performNameSearch ( String targetFolderName )
            {
                //set search name wrt user specified value
                setSearchName ( targetFolderName );
            
                UNICODE_IMPULSE_SHAPE impulseShape = impulses.get ( getTargetImpulseIndex ( searchName ) );
                UNICODE_IMPULSE_BODY impulseBody = impulseShape.getBody ( );
                int impulseBodyX = ( int ) impulseBody.getBody ( ).getX ( );
                
                //start appropriate scroll timers.
                if ( scrollRightwardsAdjuster < impulseBodyX )
                    scrollRightwardsTimer.start ( );
//                 else if ( scrollLeftwardsAdjuster > impulse.getImpulse ( ).getX ( ) )
//                     scrollLeftwardsTimer.start ( );
            }
			
    //button resize action listeenr
    private class descriptorResizeActionListening implements ActionListener
    {
        public void actionPerformed ( ActionEvent aEvent )
        {
            establishDescriptorVisualResponseActionListenerComponent ( );
            repaint ( );
        }
    }	
	
	//establish descriptor visual response action listener component
	public void establishDescriptorVisualResponseActionListenerComponent ( )
	{
		if ( impulseDescriptors.get ( resizableDescriptorIndex ).getDimensionAlterationRate ( ) <= impulseDescriptors.get ( resizableDescriptorIndex ).getProjectedWidth ( ) )
			impulseDescriptors.get ( currentDescriptorIndex ).performGeometricalMutation ( );
	}
	
	public void performNodeDeletionViaDynamicHighlighting ( )
	{
		for ( int i = 0; i < impulses.size ( ); i ++ )
			if ( dynamicHiglighter.getStrokeEntailPointEnquiry ( ( int ) impulses.get ( i ).getBody ( ).getBody ( ).getX ( ), ( int ) impulses.get ( i ).getBody ( ).getBody ( ).getY ( ) ) )
				dynamicHighlightingAffectedFileList.add ( impulses.get ( i ).getBody ( ).getEntityStream ( ) );
		reInitializeFieldSearchModeE ( fieldStream, dynamicHighlightingAffectedFileList );
	}
	
	public Thread [ ] getNodeHighligherThreads ( )
	{
		Thread [ ] value = new Thread [ 4 ];	
		
		Thread _0 = new Thread
		(
		   new Runnable ( )
		   {
			   public void run ( )
			   {
					performNodeDeletionViaDynamicHighlighting ( );
					dynamicHiglighter.getActionBox ( ).setVisible ( false );
					new UNICODE_MessageBoxWindow ( "File deletion complete!", 0.9f, new Color ( 10, 10, 10 ), Color.black, Color.gray, Color.white, true, "data/images/all/message box/okay/","rr", 10, 10, 0 );    
					dynamicHiglighter.reset ( );
			   }
		   }
		);
		Thread _1 = new Thread
		(
		   new Runnable ( )
		   {
			   public void run ( )
			   {
					
			   }
		   }
		);
		Thread _2 = new Thread
		(
		   new Runnable ( )
		   {
			   public void run ( )
			   {
			   }
		   }
		);
		Thread _3 = new Thread
		(
		   new Runnable ( )
		   {
			   public void run ( )
			   {
					dynamicHiglighter.reset ( );
			   }
		   }
		);
		value [ 0 ] = _0;
		value [ 1 ] = _1;
		value [ 2 ] = _2;
		value [ 3 ] = _3;
		return value;
	}
	
	public void enableDynamicHighlightingPerformanceMode ( )
	{
		//we only want to create bg when file doesn't exist.
		//Otherwise the puspose of this mode would be defated, sontantly generating 
		//bcrkground images when we don't need to.
		if ( !new File ( dynamicHighlightingStream ).exists ( ) ) 
		{
			new File ( dynamicHighlightingDirectory ).mkdir ( );
			conveniencePack.saveScreen ( dynamicHighlightingStream );
			dynamicHighlightingImageIcon = new ImageIcon ( dynamicHighlightingStream );
			dynamicHighlightingImage = dynamicHighlightingImageIcon.getImage ( );
			dynamicHighlightingImage = createImage ( dynamicHighlightingImage.getSource ( ) );
			dynamicHighlightingModeEnquiry = true;
		}
	}
	public void disableDynamicHighlightingPerformanceMode ( )
	{
		if ( new File ( dynamicHighlightingStream ).exists ( ) ) 
		{
			directoryEditor.removeFolder ( new File ( dynamicHighlightingDirectory ) );
			dynamicHighlightingModeEnquiry = false;
		}
	}
	
	public void resetScrollDuration ( )
	{
		scrollDurationTimer.stop ( );
		scrollDurationTracker = 0;
		scrollDurationTimer.start ( );
	}
	
	public void slide ( String direction )
	{
		if ( direction.equals ( "rightwards" ) )
		{
			resetScrollDuration ( );
			scrollLeftwardsTimer.stop ( );
			scrollRightwardsTimer.start ( );
		}
		else if ( direction.equals ( "leftwards" ) )
		{
			resetScrollDuration ( );
			scrollRightwardsTimer.stop ( );
			scrollLeftwardsTimer.start ( );
		}
		repaint ( );
	}
	
	//with occupied by impulse descriptors
	public int getPreciseWidth ( )
	{
		int returnValue = 0;
		
		Rectangle rectangle = null;
	
		//get the position of the last descriptor.
		UNICODE_IMPULSE_DESCRIPTOR descriptorShape = impulseDescriptors.get ( maxImpulses - 1 );
				
		//create a rectangle wrt to data derived above
		rectangle = new Rectangle ( 0, 0, ( int ) ( descriptorShape.getBody ( ).getX ( ) + descriptorShape.getBody ( ).getWidth ( ) ), 1 );
		
		//establish returnValue
		returnValue = ( int ) rectangle.getWidth ( );
		
		return returnValue;
	}
	
	public int getLastDescriptorLocation ( )
	{
		int returnValue = 0;
		
		//get the position of the last descriptor.
		UNICODE_IMPULSE_DESCRIPTOR descriptorShape = impulseDescriptors.get ( maxImpulses - 1 );
		
		//establish returnValue
		returnValue = ( int ) ( descriptorShape.getBody ( ).getX ( ) + descriptorShape.getBody ( ).getWidth ( ) );
		
		return returnValue;
	}
	
	public int getFirstDescriptorLocation ( )
	{
		int returnValue = 0;
		
		//get the position of the last descriptor.
		UNICODE_IMPULSE_DESCRIPTOR descriptorShape = impulseDescriptors.get ( 0 );
		
		//establish returnValue
		returnValue = ( int ) ( descriptorShape.getBody ( ).getX ( ) );
		
		return returnValue;
	}	

	public void toggleParameterBubbleEnableEnquiry ( )
	{
		parameterBubble.toggleParameterBubbleEnableEnquiry ( this );
	}
}
