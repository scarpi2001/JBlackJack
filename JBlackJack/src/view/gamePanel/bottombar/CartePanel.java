package view.gamePanel.bottombar;

import java.awt.*;
import javax.swing.*;

import model.ModelManager;
import model.carte.Mano;
import model.giocatore.Giocatore;


public class CartePanel extends JPanel
{ 
	private int yPosition;
	
	public CartePanel() 
    {
		setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10)); 
        setOpaque(false);
        yPosition = 0; // Inizializza la posizione Y per i pannelli        
    }
	
	public void updateCarte()
	{
		ModelManager model = ModelManager.getInstance();
        
        for(Giocatore giocatore : model.getGiocatoriPartita())
		{
			for (Mano mano : giocatore.getMani()) 
			{	        
				ManoPanel manoPanel = new ManoPanel(mano);
	            manoPanel.setBounds(100, yPosition, 100, 50); 
	            add(manoPanel); 	                               
		    }
		}
	}
}
