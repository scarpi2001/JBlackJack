package view.gamePanel.bottombar;

import java.awt.*;
import javax.swing.*;

import model.ModelManager;
import view.gamePanel.bottombar.utentePanel.UtentePanel;

public class BottomBarGamePanel extends JPanel
{
    private UtentePanel utentePanel;
    private CartePanel cartePanel;
    
    public BottomBarGamePanel() 
    {	
    	setLayout(new GridBagLayout());
        setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        
        // Panel giocatore
        utentePanel = new UtentePanel();
        gbc.fill = GridBagConstraints.VERTICAL; 
        gbc.weightx = 0.1; 
        gbc.gridx = 0; 
        gbc.gridy = 0; 
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        add(utentePanel, gbc);

        // Panel carte
        cartePanel = new CartePanel(); 
        gbc.fill = GridBagConstraints.BOTH; 
        gbc.weightx = 0.9; 
        gbc.gridx = 1; 
        gbc.gridy = 0; 
        gbc.anchor = GridBagConstraints.LAST_LINE_END; 
        add(cartePanel, gbc); 
    
    }
    
	@Override
	public void paintComponent(Graphics g)
	{
		ModelManager model = ModelManager.getInstance();
		
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;
		
		//disegna cartePanel
		cartePanel.repaint();
	}
	
	public void updateCarte()
	{
		cartePanel.updateCarte();
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
