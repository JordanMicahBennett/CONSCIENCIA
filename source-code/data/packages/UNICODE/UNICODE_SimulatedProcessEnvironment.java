package data.packages.UNICODE;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.QuadCurve2D;

import data.packages.UNICODE.*;

public class UNICODE_SimulatedProcessEnvironment
{
    //establish attributes
    private UNICODE_MosPanel guiPanel = null;
    public ArrayList <UNICODE_SimulatedProcessEnvironmentRegion> environmentRegions = null;
    public ArrayList <UNICODE_SimulatedProcessEnvironmentRegionConnector> environmentRegionConnectors = null;
    public UNICODE_SimulatedTaskScheduler SIMULATED_TASK_SCHEDULER = null;
    public double PROCESS_EXECUTION_SPEED;
    public int NUMBER_OF_ACTIVE_SIMULATED_CORES, NUMBER_OF_ACTIVE_SIMULATED_CORES_EXLPORATION_INDEX;
    private int MAXIMUM_PROCESSOR_STEP_SIZE, MINIMUM_PROCESSOR_STEP_SIZE = 1;
    private int [ ] PROCESSOR_STEP_SIZES;
    private UNICODE_MosConfigurationManager configurationManager = null;
    
    //constructor
    public UNICODE_SimulatedProcessEnvironment ( UNICODE_MosPanel guiPanel, UNICODE_MosConfigurationManager configurationManager )
    {
         //establish features
            //establish gui panel
            this.guiPanel = guiPanel;
                
            //establish process execution speed
            this.configurationManager = configurationManager;
            this.PROCESS_EXECUTION_SPEED = configurationManager.getProcessExecutionSpeedFromFile ( );
            this.NUMBER_OF_ACTIVE_SIMULATED_CORES = getNumberOfActiveSimulatedCores ( );
            
            //esbtablish native processor features
            PROCESS_EXECUTION_SPEED = 0.0; //give this public access
            NUMBER_OF_ACTIVE_SIMULATED_CORES = getNumberOfActiveSimulatedCores ( ); /*give this public access */ 
            NUMBER_OF_ACTIVE_SIMULATED_CORES_EXLPORATION_INDEX = 0;
            MAXIMUM_PROCESSOR_STEP_SIZE = 4; /* auto set maximum cores to 4 where simulation has 4*/ 
            MINIMUM_PROCESSOR_STEP_SIZE = 1; /*auto set minimum to 1, a working simulation has atleast one operational core */
            PROCESSOR_STEP_SIZES = new int [ MAXIMUM_PROCESSOR_STEP_SIZE ];
            PROCESSOR_STEP_SIZES = new int [ ] { 1, 2, 4, 8 };
            
            ///////////////////////////////
            //generate regions
            ///////////////////////////////   
                ///////////////////////////////
                //establish region space
                ///////////////////////////////
                environmentRegions = new ArrayList <UNICODE_SimulatedProcessEnvironmentRegion> ( );
                ///////////////////////////////
                //establish region pos vars
                ///////////////////////////////
                int appWidth = 1200, regionWidth = 200, regionHeight = 84, subUnitWidth = 28, subUnitHeight = 28;
                
                ///////////////////////////////
                //generate regions
                ///////////////////////////////
                    //0.generate 'new' region
                    environmentRegions.add ( new UNICODE_SimulatedProcessEnvironmentRegion ( guiPanel, new Point ( regionWidth, regionWidth ), new Dimension ( regionWidth, regionHeight ), new Dimension ( subUnitWidth, subUnitHeight ), Color.black, new Color ( 70, 70, 70 ), UNICODE_SimulatedTaskType.NEW, false, "new" ) );
                    //1.generate 'terminated' region
                    environmentRegions.add ( new UNICODE_SimulatedProcessEnvironmentRegion ( guiPanel, new Point ( appWidth - ( regionWidth * 2 ), regionWidth ), new Dimension ( regionWidth, regionHeight ), new Dimension ( subUnitWidth, subUnitHeight ), Color.black, new Color ( 70, 70, 70 ), UNICODE_SimulatedTaskType.TERMINATED, false, "terminated" ) );
                    //2.generate 'ready' region
                    environmentRegions.add ( new UNICODE_SimulatedProcessEnvironmentRegion ( guiPanel, new Point ( regionWidth + ( regionWidth / 2 ), 370 ), new Dimension ( regionWidth, regionHeight ), new Dimension ( subUnitWidth, subUnitHeight ), Color.gray, new Color ( 70, 70, 70 ), UNICODE_SimulatedTaskType.READY, true, "ready" ) );
                    //3.generate 'running' region
                    environmentRegions.add ( new UNICODE_SimulatedProcessEnvironmentRegion ( guiPanel, new Point ( appWidth - ( ( regionWidth * 2 ) + ( regionWidth / 2 ) ), 370 ), new Dimension ( regionWidth, regionHeight ), new Dimension ( subUnitWidth, subUnitHeight ), Color.black, new Color ( 70, 70, 70 ), UNICODE_SimulatedTaskType.RUNNING, true, "running" ) );
                    //4.generate 'waiting' region
                    environmentRegions.add ( new UNICODE_SimulatedProcessEnvironmentRegion ( guiPanel, new Point ( ( appWidth / 2 ) - ( regionWidth / 2 ), 520 ), new Dimension ( regionWidth, regionHeight ), new Dimension ( subUnitWidth, subUnitHeight ), Color.black, new Color ( 70, 70, 70 ), UNICODE_SimulatedTaskType.WAITING, true, "waiting" ) );
        
        
            ///////////////////////////////
            //generate region connectors
            ///////////////////////////////   
                ///////////////////////////////
                //establish region space
                ///////////////////////////////
                environmentRegionConnectors = new ArrayList <UNICODE_SimulatedProcessEnvironmentRegionConnector> ( );
                /////////////////////////////////////
                //establish region connector pos vars
                /////////////////////////////////////
                ///////////////////////////////
                //generate regions connectors
                ///////////////////////////////
                    //0.generate 'new' to 'ready' region connector
                    environmentRegionConnectors.add ( new UNICODE_SimulatedProcessEnvironmentRegionConnector ( guiPanel, new Point ( regionWidth, regionWidth + ( regionWidth / 4 ) ), new Point ( regionWidth + ( regionWidth / 2 ), 370 + ( regionWidth / 4 ) ), new Dimension ( regionWidth / 2 + regionWidth / 2, 370 + 30 ), new Color ( 70, 70, 70 ), UNICODE_SimulatedTaskManipulationType.ADMITTING, "admitting" ) );
                    //1.generate 'waiting' to 'ready' region connector
                    environmentRegionConnectors.add ( new UNICODE_SimulatedProcessEnvironmentRegionConnector ( guiPanel, new Point ( regionWidth + ( regionWidth / 2 ), 370 + ( regionWidth / 4 ) ), new Point ( ( appWidth / 2 ) - ( regionWidth / 2 ), 520 + ( regionWidth / 4 ) ), new Dimension ( regionWidth / 2 + regionWidth, 520 + 30 ), new Color ( 70, 70, 70 ), UNICODE_SimulatedTaskManipulationType.IOEVENTCOMPLETING, "ioeventcompleting" ) );
                    //2.generate 'running' to 'terminated' region connector
                    environmentRegionConnectors.add ( new UNICODE_SimulatedProcessEnvironmentRegionConnector ( guiPanel, new Point ( appWidth - ( regionWidth ), regionWidth + ( regionWidth / 4 ) ), new Point ( appWidth - ( ( regionWidth ) + ( regionWidth / 2 ) ), 370 + ( regionWidth / 4 ) ), new Dimension ( appWidth - regionWidth / 2 - ( regionWidth / 2 ), 370 + 30  ), new Color ( 70, 70, 70 ), UNICODE_SimulatedTaskManipulationType.EXITING, "exiting" ) );
                    //3.generate 'running' to 'waiting' region connector
                    environmentRegionConnectors.add ( new UNICODE_SimulatedProcessEnvironmentRegionConnector ( guiPanel, new Point ( appWidth - ( ( regionWidth ) + ( regionWidth / 2 ) ), 370 + ( regionWidth / 4 ) ), new Point ( ( appWidth / 2 + regionWidth ) - ( regionWidth / 2 ), 520 + ( regionWidth / 4 ) ), new Dimension ( appWidth - 370 / 2 - ( regionWidth / 2 ), 520 + 30 ), new Color ( 70, 70, 70 ), UNICODE_SimulatedTaskManipulationType.IOEVENTWAITING, "ioeventwaiting" ) );
                    //4.generate 'ready' to 'running' region connector
                    environmentRegionConnectors.add ( new UNICODE_SimulatedProcessEnvironmentRegionConnector ( guiPanel, new Point ( regionWidth + ( regionWidth / 2 ) + regionWidth, 370 ), new Point ( appWidth - ( ( regionWidth * 2 ) + ( regionWidth / 2 ) ), 370 ), new Dimension ( appWidth - ( regionWidth * 3 ), 300 ), new Color ( 70, 70, 70 ), UNICODE_SimulatedTaskManipulationType.INTERRUPTING, "interrupting" ) );
                    //5.generate 'running' to 'ready' region connector
                    environmentRegionConnectors.add ( new UNICODE_SimulatedProcessEnvironmentRegionConnector ( guiPanel, new Point ( regionWidth + ( regionWidth / 2 ) + regionWidth, 450 ), new Point ( appWidth - ( ( regionWidth * 2 ) + ( regionWidth / 2 ) ), 450 ), new Dimension ( appWidth - ( regionWidth * 3 ), 510 ), new Color ( 70, 70, 70 ), UNICODE_SimulatedTaskManipulationType.DISPATCHING, "dispatching" ) );
       
        ///////////////////////////////
        //generate simulatedTaskScheduler
        ///////////////////////////////   
        SIMULATED_TASK_SCHEDULER = new UNICODE_SimulatedTaskScheduler ( guiPanel, 1, 3, new UNICODE_SimulatedTaskScheduler ( ).getGeneratedStreamList ( "notepad calculator paint", 3 ), new UNICODE_SimulatedTaskScheduler ( ).getGeneratedStreamList ( "data/images/inner-images/moss/tasks/task0.png data/images/inner-images/moss/tasks/task1.png data/images/inner-images/moss/tasks/task2.png", 3 ), subUnitWidth, environmentRegions, environmentRegionConnectors );
    }
    //methods
        //accessors
        public int getNumberOfActiveSimulatedCores ( )
        {
            return PROCESS_EXECUTION_SPEED == configurationManager.getProcessExecutionSpeedsFromFile ( ) [ 3 ] ? 8 : PROCESS_EXECUTION_SPEED == configurationManager.getProcessExecutionSpeedsFromFile ( ) [ 2 ] ? 4 : PROCESS_EXECUTION_SPEED == configurationManager.getProcessExecutionSpeedsFromFile ( ) [ 1 ] ? 2 : PROCESS_EXECUTION_SPEED == configurationManager.getProcessExecutionSpeedsFromFile ( ) [ 0 ] ? 1 : 1; 
        }
        //mutators
        public void updateProcessExecutionSpeed ( double value )
        {
            UNICODE_ProcessExecutionSpeedController processExecutionSpeedController = new UNICODE_ProcessExecutionSpeedController ( );
            processExecutionSpeedController.setValue ( value );
            configurationManager.updateProcessExecutionSpeed ( processExecutionSpeedController );
            configurationManager.updateConfigFile ( );
        }
        
        public void setProcessExecutionSpeed ( int _NUMBER_OF_ACTIVE_SIMULATED_CORES )
        {
            switch ( _NUMBER_OF_ACTIVE_SIMULATED_CORES )
            {
                case 1: PROCESS_EXECUTION_SPEED = configurationManager.getProcessExecutionSpeedsFromFile ( ) [ 0 ]; break;
                case 2: PROCESS_EXECUTION_SPEED = configurationManager.getProcessExecutionSpeedsFromFile ( ) [ 1 ]; break;
                case 4: PROCESS_EXECUTION_SPEED = configurationManager.getProcessExecutionSpeedsFromFile ( ) [ 2 ]; break;
                case 8: PROCESS_EXECUTION_SPEED = configurationManager.getProcessExecutionSpeedsFromFile ( ) [ 3 ]; break;
            }
        }
        
        public void updateProcessExecutionSpeed ( )
        {
            if ( NUMBER_OF_ACTIVE_SIMULATED_CORES_EXLPORATION_INDEX < MAXIMUM_PROCESSOR_STEP_SIZE - 1 )
            {
                NUMBER_OF_ACTIVE_SIMULATED_CORES_EXLPORATION_INDEX ++;
                setProcessExecutionSpeed ( PROCESSOR_STEP_SIZES [ NUMBER_OF_ACTIVE_SIMULATED_CORES_EXLPORATION_INDEX ] );
                updateProcessExecutionSpeed ( PROCESS_EXECUTION_SPEED );
            }
            else
            {
                NUMBER_OF_ACTIVE_SIMULATED_CORES_EXLPORATION_INDEX = 0;
                setProcessExecutionSpeed ( PROCESSOR_STEP_SIZES [ NUMBER_OF_ACTIVE_SIMULATED_CORES_EXLPORATION_INDEX ] );
                updateProcessExecutionSpeed ( PROCESS_EXECUTION_SPEED ); 
            }
        }
        
        //extras
        public void draw ( Graphics2D graphics2d )
        {
            //draw regions
            for ( UNICODE_SimulatedProcessEnvironmentRegion environmentRegion : environmentRegions )
                environmentRegion.draw ( graphics2d );
                
            //draw region connectors
            for ( UNICODE_SimulatedProcessEnvironmentRegionConnector environmentRegionConnector : environmentRegionConnectors )
                environmentRegionConnector.draw ( graphics2d );
                
            //draw tasks
                SIMULATED_TASK_SCHEDULER.draw ( graphics2d );
        }
}