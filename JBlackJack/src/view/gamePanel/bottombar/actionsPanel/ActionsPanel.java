package view.gamePanel.bottombar.actionsPanel;

import javax.swing.*;

public class ActionsPanel extends JPanel
{
	private PreBetPanel scommettiPanel;
	private PostBetPanel actionsPanel;
	  
    public ActionsPanel() 
    {
    	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);
        
        //startPanel (bet, quantità)
        scommettiPanel = new PreBetPanel();
        add(scommettiPanel);
        
        //actionsPanel (hit, stay, split)
        actionsPanel = new PostBetPanel();
        add(actionsPanel);
        actionsPanel.setVisible(false);
    }

    public void setBetPanelVisible(boolean visible) 
    {
    	scommettiPanel.setVisible(visible);
    }
    
    public void setActionsPanelVisible(boolean visible) 
    {
    	actionsPanel.setVisible(visible);
    }
    
    public void setSplitVisible(boolean visible) 
    {
    	actionsPanel.setSplitVisible(visible);
    }
}
