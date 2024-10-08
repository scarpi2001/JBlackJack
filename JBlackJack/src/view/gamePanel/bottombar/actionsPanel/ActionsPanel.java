package view.gamePanel.bottombar.actionsPanel;

import java.awt.*;
import javax.swing.*;

/**
 * pannello contenente i pannelli, 
 * di prebet e postbet con le azioni eseguibili dall'utente
 */
public class ActionsPanel extends JPanel
{
	private PreBetPanel preBetPanel;
	private PostBetPanel postBetPanel;
	  
    public ActionsPanel() 
    {
    	setLayout(new BorderLayout());
        setOpaque(false);
        
        //prebet (bet, quantità)
        preBetPanel = new PreBetPanel();
        preBetPanel.setMaximumSize(new Dimension(300, 100));
        add(preBetPanel, BorderLayout.SOUTH);
        
        //postbet (hit, stay, split)
        postBetPanel = new PostBetPanel();
        add(postBetPanel, BorderLayout.CENTER);
        postBetPanel.setVisible(false);
    }

    public void setPreBetPanelVisible(boolean visible) 
    {
    	preBetPanel.setVisible(visible);
    }
    
    public void setPostBetPanelVisible(boolean visible) 
    {
    	postBetPanel.setVisible(visible);
    }
    
    public void setSplitVisible(boolean visible) 
    {
    	postBetPanel.setSplitVisible(visible);
    }
}
