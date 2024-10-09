package view.gamePanel.bottombar;

import java.awt.*;
import javax.swing.*;

import view.gamePanel.bottombar.actionsPanel.ActionsPanel;

/**
 * pannello per la bottomBar della partita,
 * contiene il pannello con le azione eseguibili dall'utente
 * e il pannello contenente i giocatori della partita
 */
public class BottomBarGamePanel extends JPanel
{
    private ActionsPanel actionsPanel;
    private GiocatoriPanel giocatoriPanel;
    
    public BottomBarGamePanel() 
    {	
    	setLayout(new GridBagLayout());
        setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        
        //panel azioni
        actionsPanel = new ActionsPanel();
        gbc.fill = GridBagConstraints.VERTICAL; 
        gbc.weightx = 0.1; 
        gbc.gridx = 0; 
        gbc.gridy = 0; 
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        add(actionsPanel, gbc);

        //panel giocatori
        giocatoriPanel = new GiocatoriPanel(); 
        gbc.fill = GridBagConstraints.BOTH; 
        gbc.weightx = 0.9; 
        gbc.gridx = 1; 
        gbc.gridy = 0; 
        gbc.anchor = GridBagConstraints.LAST_LINE_END; 
        add(giocatoriPanel, gbc); 
    
    }
	
    public void setActionsPanelVisible(boolean visible) 
    {
    	actionsPanel.setVisible(visible);
    }
    
    public void setPreBetPanelVisible(boolean visible) 
    {
    	actionsPanel.setPreBetPanelVisible(visible);
    }
    
    public void setPostBetPanelVisible(boolean visible) 
    {
    	actionsPanel.setPostBetPanelVisible(visible);
    }
    
    public void setSplitVisible(boolean visible) 
    {
    	actionsPanel.setSplitVisible(visible);
    }
    
    public void aggiornaGiocatori()
    {
    	giocatoriPanel.aggiornaGiocatori();
    }
}
