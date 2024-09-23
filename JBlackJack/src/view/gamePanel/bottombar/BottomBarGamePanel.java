package view.gamePanel.bottombar;

import java.awt.*;
import javax.swing.*;

import view.gamePanel.bottombar.giocatorePanel.GiocatorePanel;

public class BottomBarGamePanel extends JPanel
{
    private GiocatorePanel giocatorePanel;

    public BottomBarGamePanel() 
    {	
    	setLayout(new GridBagLayout());
        setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        
        //panel giocatore (per ogni giocatore ci deve essere un panel che si affianca al precedente)
        giocatorePanel = new GiocatorePanel();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        add(giocatorePanel, gbc);
    }
    
    public void setBetPanelVisible(boolean visible) 
    {
    	giocatorePanel.setBetPanelVisible(visible);
    }
    
    public void setActionsPanelVisible(boolean visible) 
    {
    	giocatorePanel.setActionsPanelVisible(visible);
    }
    
    public void setSplitVisible(boolean visible) 
    {
		giocatorePanel.setSplitVisible(visible);
    }
}
