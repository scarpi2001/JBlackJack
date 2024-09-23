package view.gamePanel.bottombar.giocatorePanel;

import javax.swing.*;

public class GiocatorePanel extends JPanel
{
	private BetPanel betPanel;
	private ActionsPanel actionsPanel;
	  
    public GiocatorePanel() 
    {
    	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);
        
        //startPanel (bet, quantità)
        betPanel = new BetPanel();
        add(betPanel);
        
        //actionsPanel (hit, stay, split)
        actionsPanel = new ActionsPanel();
        add(actionsPanel);
        actionsPanel.setVisible(false);
    }

    public void setBetPanelVisible(boolean visible) 
    {
    	betPanel.setVisible(visible);
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
