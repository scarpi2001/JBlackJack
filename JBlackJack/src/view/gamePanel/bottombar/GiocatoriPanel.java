package view.gamePanel.bottombar;

import java.awt.*;
import javax.swing.*;

import model.ModelManager;
import model.carte.Mano;
import model.giocatore.Giocatore;
import view.gamePanel.giocatorePanel.GiocatorePanel;
import view.gamePanel.giocatorePanel.ManoPanel;


public class GiocatoriPanel extends JPanel
{ 	
	public GiocatoriPanel() 
    {
		setLayout(new FlowLayout(FlowLayout.LEFT, 40, 10)); 
        setOpaque(false);
    }
	
	public void updateGiocatori()
	{
		removeAll();
		ModelManager model = ModelManager.getInstance();
        
		//se ho inizializzato la partita
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
