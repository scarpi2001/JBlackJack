package view.gamePanel.bottombar.actionsPanel;

import javax.swing.*;

public class ActionsPanel extends JPanel
{
	private PreBetPanel preBetPanel;
	private PostBetPanel postBetPanel;
	  
    public ActionsPanel() 
    {
    	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);
        
        //prebet (bet, quantità)
        preBetPanel = new PreBetPanel();
        add(preBetPanel);
        
        //postbet (hit, stay, split)
        postBetPanel = new PostBetPanel();
        add(postBetPanel);
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