package data.packages.UNICODE;
import java.awt.geom.Ellipse2D;
import java.io.File;
import javax.activation.MimetypesFileTypeMap;
import javax.swing.JPanel;

public class UNICODE_IMPULSE_BODY 
{
    //attributes
    private Ellipse2D impulseBody = null;
    private String entityStream, entitySearchName;
    private double x, y, width, height;
    private boolean directoryEnquiry = false; //will be used to determine whether the current UNICODE_IMPULSE_BODY represents an entity that is a directory. If not, it is assumed that it represnets a file instead.
    private int entityIndex;
    private UNICODE_ConveniencePack conveniencePack = new UNICODE_ConveniencePack ( );
    private UNICODE_DirectoryEditor directoryEditor = new UNICODE_DirectoryEditor ( );
	private int folderIndicatorScaleFactor = 0;
    
    //constructor
    public UNICODE_IMPULSE_BODY ( String _entityStream, String _entitySearchName, double _x, double _y, double _width, double _height, int _entityIndex, int _folderIndicatorScaleFactor )
    {
        //establish entity stream
        entityStream = _entityStream;
        
        //establish entity index
        entityIndex = _entityIndex;
        
        //establish entity directory name
        entitySearchName = _entitySearchName;
		
		//establish folderIndicatorScaleFactor - folders shapes are bigger than files. this dictates the disparity, which is to say it controlls by how much folder shapes will be bigger.
		folderIndicatorScaleFactor = _folderIndicatorScaleFactor;
        
        //establish orientation
        x = _x;
        y = _y;
        
        //establish dimension
		if ( representsDirectory ( getEntityStream ( ) ) )
		{
			width = _width + folderIndicatorScaleFactor;
			height = _height + folderIndicatorScaleFactor;
		}
		else
		{
			width = _width;
			height = _height;
		}
			
        
        //establish UNICODE_IMPULSE_BODY
        impulseBody = new Ellipse2D.Double ( x, y, width, height );
    }
    
    
    //accessors
    public Ellipse2D getBody ( )
    {
        return impulseBody;
    }
    
    public String getEntityStream ( )
    {
        return entityStream + "/";
    }
    
    public String getEntitySearchName ( )
    {
        return entitySearchName;
    }    
    
    public int getEntityIndex ( )
    {
        return entityIndex;
    }
    
    public boolean representsDirectory ( String stream )
    {
        return new File ( stream ).isDirectory ( );
    }
    
    //actions
        //0.open folder
        public void openRepresentedWindowsVersion ( )
        {
            directoryEditor.openFolder ( getEntityStream ( ) );
        }
		
		//the 'fieldGenerationMethod' varibale determines how impulse field will be mutated:
        public void openRepresentedImpulseVersion ( JPanel masterPanel, UNICODE_IMPULSE_FIELD impulseField, String _streamFolder, boolean forwardNavigationEnquiry )
        {
			//if node represents a folder, then hide current impulse field, then add new impulse field with folder enties
			if ( representsDirectory ( _streamFolder ) )
			{
				impulseField.reInitializeField ( _streamFolder, forwardNavigationEnquiry );
				impulseField.repaint ( );
				masterPanel.repaint ( );
			}
			//otherwise its a file, herego, use default windows file opener. Windows will handle this automaitically
			else
				openRepresentedWindowsVersion ( );
		}
		
		//mutates the impulse field, but rather than accoutning for navigation, it accounts for search results
		//based on a letter supplied as 'commencingLetter'
        public void openRepresentedImpulseVersionSearchModeA ( JPanel masterPanel, UNICODE_IMPULSE_FIELD impulseField, String impulseFieldStream, String commencingLetter )
        {
			impulseField.reInitializeFieldSearchModeA ( impulseFieldStream, commencingLetter );
			impulseField.repaint ( );
			masterPanel.repaint ( );
		}
		
		//mutates the impulse field, but rather than accoutning for navigation, it accounts for search results
		//based on a keyword supplied as 'keyWord'
        public void openRepresentedImpulseVersionSearchModeB ( JPanel masterPanel, UNICODE_IMPULSE_FIELD impulseField, String impulseFieldStream, String keyWord )
        {
			impulseField.reInitializeFieldSearchModeB ( impulseFieldStream, keyWord );
			impulseField.repaint ( );
			masterPanel.repaint ( );
		}
		
		//mutates the impulse field, but rather than accoutning for navigation, it accounts for search results
		//based on the fact that we only want to return folder entities
        public void openRepresentedImpulseVersionSearchModeC ( JPanel masterPanel, UNICODE_IMPULSE_FIELD impulseField, String impulseFieldStream )
        {
			impulseField.reInitializeFieldSearchModeC ( impulseFieldStream );
			impulseField.repaint ( );
			masterPanel.repaint ( );
		}
		
		//mutates the impulse field, but rather than accoutning for navigation, it accounts for search results
		//based on the fact that we only want to return file entities
        public void openRepresentedImpulseVersionSearchModeD ( JPanel masterPanel, UNICODE_IMPULSE_FIELD impulseField, String impulseFieldStream )
        {
			impulseField.reInitializeFieldSearchModeD ( impulseFieldStream );
			impulseField.repaint ( );
			masterPanel.repaint ( );
		}
        
        //1.ATTRIBUTES/PROPERTIS
        //attributes
            //get name
            public String getRepresentedName ( )
            {
                return new File ( getEntityStream ( ) ).getName ( );
            }
            //get type
            public String getRepresentedType ( String stream )
            {
                String returnValue = "";
                
                if ( representsDirectory ( stream ) )
                    returnValue = "Folder";
                else
                    returnValue = new MimetypesFileTypeMap ( ).getContentType ( new File ( getEntityStream ( ) ) );
                
                return returnValue;
            }
            //get size
            public String getRepresentedSize ( )
            {
                double bytes = new File ( getEntityStream ( ) ).length ( );
                double kilobytes = ( bytes / 1024 );
                double megabytes = ( kilobytes / 1024 );
                
                return "" + kilobytes + "Kb or " + bytes + " bytes";
            }
            //get last modified
            public String getRepresentedLastModified ( )
            {
                return "" + new File ( getEntityStream ( ) ).lastModified ( );
            }
            //get whether entity is hidden
            public String getRepresentedHiddenEnquiry ( )
            {
                return "" + new File ( getEntityStream ( ) ).isHidden ( );
            }
        
        //2.copy
        public void copyRepresented ( )
        {
        }

        //3.rename
        public void renameRepresented ( String newName )
        {
            String oldFileName = new File ( getEntityStream ( ) ).getName ( );
            new File ( getEntityStream ( ) ).renameTo ( conveniencePack.makeFile ( getEntityStream ( ).replace ( oldFileName, newName ) ) );
        }
        //4.delete
        public void deleteRepresented ( )
        {
            //check if a folder is being deleted, if so, use directory editor 
            //for deletion, else use simple delete method on file wrt to stream.
            if ( representsDirectory ( getEntityStream ( ) ) )
                directoryEditor.removeFolder ( conveniencePack.makeFile ( getEntityStream ( ) ) );
            else
                new File ( getEntityStream ( ) ).delete ( );
        }
        //5.set read only
        public void setRepresentedReadOnly ( )
        {
            new File ( getEntityStream ( ) ).setReadOnly ( );
        } 
}
