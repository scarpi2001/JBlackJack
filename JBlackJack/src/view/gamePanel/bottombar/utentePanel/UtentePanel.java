package view.gamePanel.bottombar.utentePanel;

import javax.swing.*;

public class UtentePanel extends JPanel
{
	private ScommettiPanel scommettiPanel;
	private ActionsPanel actionsPanel;
	  
    public UtentePanel() 
    {
    	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);
        
        //startPanel (bet, quantità)
        scommettiPanel = new ScommettiPanel();
        add(scommettiPanel);
        
        //actionsPanel (hit, stay, split)
        actionsPanel = new ActionsPanel();
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
