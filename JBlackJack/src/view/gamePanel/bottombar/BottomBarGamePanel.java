package view.gamePanel.bottombar;

import java.awt.*;
import javax.swing.*;

import model.ModelManager;
import view.gamePanel.bottombar.actionsPanel.ActionsPanel;

public class BottomBarGamePanel extends JPanel
{
    private ActionsPanel utentePanel;
    private GiocatoriPanel giocatoriPanel;
    
    public BottomBarGamePanel() 
    {	
    	setLayout(new GridBagLayout());
        setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        
        //panel azioni
        utentePanel = new ActionsPanel();
        gbc.fill = GridBagConstraints.VERTICAL; 
        gbc.weightx = 0.1; 
        gbc.gridx = 0; 
        gbc.gridy = 0; 
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        add(utentePanel, gbc);

        //panel giocatori
        giocatoriPanel = new GiocatoriPanel(); 
        gbc.fill = GridBagConstraints.BOTH; 
        gbc.weightx = 0.9; 
        gbc.gridx = 1; 
        gbc.gridy = 0; 
        gbc.anchor = GridBagConstraints.LAST_LINE_END; 
        add(giocatoriPanel, gbc); 
    
    }
	
	public void updateGiocatori()
	{
		giocatoriPanel.updateGiocatori();
	}
	
    public void setBetPanelVisible(boolean visible) 
    {
    	utentePanel.setBetPanelVisible(visible);
    }
    
    public void setActionsPanelVisible(boolean visible) 
    {
    	utentePanel.setActionsPanelVisible(visible);
    }
    
    public void setSplitVisible(boolean visible) 
    {
    	utentePanel.setSplitVisible(visible);
    }
}
