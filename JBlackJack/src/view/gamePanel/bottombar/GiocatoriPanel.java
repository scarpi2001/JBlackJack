package view.gamePanel.bottombar;

import java.awt.*;
import javax.swing.*;

import model.ModelManager;
import model.giocatore.Giocatore;
import view.gamePanel.giocatorePanel.GiocatorePanel;

import javax.swing.Timer;
import java.util.TimerTask;

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
