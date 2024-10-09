package view.gamePanel;

import javax.swing.*;

import model.ModelManager;
import model.giocatori.Giocatore;
import model.giocatori.GiocatoreDealer;
import view.gamePanel.giocatorePanel.GiocatorePanel;

/**
 * pannello per il corpo della partita, 
 * contiene il pannello del dealer
 */
public class BodyGamePanel extends JPanel
{
	public BodyGamePanel() 
    {	   
		setOpaque(false);
    }
	
	public void aggiornaDealer()
	{
		removeAll();
		
		ModelManager model = ModelManager.getInstance();
		if(model.getGiocatoriPartita().size() != 0)
		{			
			Giocatore dealer = GiocatoreDealer.getInstance();
			add(new GiocatorePanel(dealer));   
		}
	    
	    revalidate();
        repaint();
	}
}