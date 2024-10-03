package view.gamePanel;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import model.giocatore.Giocatore;
import model.giocatore.GiocatoreDealer;
import view.gamePanel.bottombar.GiocatoriPanel;
import view.gamePanel.bottombar.actionsPanel.ActionsPanel;
import view.gamePanel.giocatorePanel.GiocatorePanel;
import view.gamePanel.giocatorePanel.ManoPanel;

public class BodyPanel extends JPanel
{
	public BodyPanel() 
    {	   
		setOpaque(false);
    }
	
	public void updateDealer()
	{
		removeAll();
		
		Giocatore dealer = GiocatoreDealer.getInstance();
		add(new GiocatorePanel(dealer));   
	    
	    revalidate();
        repaint();
	}
}
