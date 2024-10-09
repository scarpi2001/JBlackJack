package view.gamePanel.bottombar;

import java.awt.*;
import javax.swing.*;

import model.ModelManager;
import model.giocatori.Giocatore;
import view.gamePanel.giocatorePanel.GiocatorePanel;

/**
 * pannello contenente i pannelli dei giocatori,
 * quando viene aggiornato toglie i pannelli e li ricrea,
 * in questo modo l'aggiornamento si adatta facilmente al cambiamento del numero dei giocatori
 */
public class GiocatoriPanel extends JPanel
{ 	
	public GiocatoriPanel() 
    {
		setLayout(new FlowLayout(FlowLayout.LEFT, 40, 10)); 
        setOpaque(false);
    }
	
	public void aggiornaGiocatori()
	{
		removeAll();
		ModelManager model = ModelManager.getInstance();
        
		//se ho inizializzato la partita creo i pannelli giocatore
		if(model.getGiocatoriPartita().size() != 0)
		{			
			for(int i = 0; i < model.getNumeroGiocatoriPartita(); i++)
			{
				Giocatore giocatore = model.getGiocatoriPartita().get(i);
				add(new GiocatorePanel(giocatore));
			}
		}
		
		revalidate();
        repaint();
	}
}
