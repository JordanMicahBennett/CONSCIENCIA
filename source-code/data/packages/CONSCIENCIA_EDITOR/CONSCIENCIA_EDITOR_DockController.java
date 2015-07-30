package data.packages.CONSCIENCIA_EDITOR;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import javax.swing.JPanel;

import data.packages.UNICODE.*;

public class CONSCIENCIA_EDITOR_DockController
{
    //attributes
    private CONSCIENCIA_EDITOR_EditorAreaDisplayManager editorAreaDisplayManager = null;
    private CONSCIENCIA_EDITOR_MenuPanel menuPanel = null;
    private boolean dockHiddenEnquiry = false;
    private JPanel guiPanel = null;
    
    //forward animation
    private Timer dockForwardAnimationTimer = new Timer ( 1, new dockForwardAnimationListener ( ) ), dockBackwardAnimationTimer = new Timer ( 1, new dockBackwardAnimationListener ( ) );
    private int incrementationRate, docIncrementationRate, animationHeightLimit, forwardMutatingHeight, forwardMutatingDockY;
    private int decrementationRate, docDecrementationRate, backwardMutatingHeight, backwardMutatingDockY;
    private int initialAnimationHeight, intialDockY;
    private boolean dockVisibilityEnquiry = true;

    //constructor - takes ( object to be maximized, object to be hidden )
    public CONSCIENCIA_EDITOR_DockController ( CONSCIENCIA_EDITOR_EditorAreaDisplayManager _editorAreaDisplayManager, CONSCIENCIA_EDITOR_MenuPanel _menuPanel, JPanel _guiPanel, int _incrementationRate, int _decrementationRate, int _animationHeightLimit, int dock_height, int _docIncrementationRate, int _docDecrementationRate, int dock_Y )
    {
        editorAreaDisplayManager = _editorAreaDisplayManager;
        menuPanel = _menuPanel;
        guiPanel = _guiPanel;
        incrementationRate = _incrementationRate;
        decrementationRate = _decrementationRate;
        docIncrementationRate = _docIncrementationRate;
        docDecrementationRate = _docDecrementationRate;
        animationHeightLimit = _animationHeightLimit;
        initialAnimationHeight = dock_height;
        intialDockY = dock_Y;
    }
    
    //misc
    public void toggleMenuPanel ( )
    {
        if ( dockVisibilityEnquiry ) 
        {
            forwardMutatingHeight = initialAnimationHeight;
            forwardMutatingDockY = intialDockY;
            dockForwardAnimationTimer.start ( );
            dockVisibilityEnquiry = false;
        }
        else
        {
            backwardMutatingDockY = menuPanel.getY ( );
            backwardMutatingHeight = forwardMutatingHeight;
            dockBackwardAnimationTimer.start ( );
            dockVisibilityEnquiry = true;
        }
    }
    
    public class dockForwardAnimationListener implements ActionListener
    {
        public void actionPerformed ( ActionEvent aEvent ) 
        {
            if ( forwardMutatingHeight <= animationHeightLimit )
            {
                forwardMutatingHeight += incrementationRate;
                forwardMutatingDockY += docDecrementationRate;
                editorAreaDisplayManager.setBounds ( editorAreaDisplayManager.getX ( ), editorAreaDisplayManager.getY ( ), editorAreaDisplayManager.getWidth ( ), forwardMutatingHeight );
                menuPanel.setBounds ( menuPanel.getX ( ), forwardMutatingDockY, menuPanel.getWidth ( ), menuPanel.getHeight ( ) );
                editorAreaDisplayManager.repaint ( );
                guiPanel.repaint ( );
            }
        }
    }
    
    public class dockBackwardAnimationListener implements ActionListener
    {
        public void actionPerformed ( ActionEvent aEvent ) 
        {
            if ( backwardMutatingHeight >= initialAnimationHeight )
            {
                backwardMutatingHeight -= decrementationRate;
                backwardMutatingDockY -= docIncrementationRate;
                editorAreaDisplayManager.setBounds ( editorAreaDisplayManager.getX ( ), editorAreaDisplayManager.getY ( ), editorAreaDisplayManager.getWidth ( ), backwardMutatingHeight );
                menuPanel.setBounds ( menuPanel.getX ( ), backwardMutatingDockY, menuPanel.getWidth ( ), menuPanel.getHeight ( ) );
                editorAreaDisplayManager.repaint ( );
                guiPanel.repaint ( );
            }
        }
    }
}
