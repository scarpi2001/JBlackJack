package view.gamePanel.bottombar.cartePanel;

import java.awt.*;
import javax.swing.*;

import model.ModelManager;
import model.carte.Mano;
import model.giocatore.Giocatore;


public class CartePanel extends JPanel
{ 	
	public CartePanel() 
    {
		setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10)); 
        setOpaque(false);
    }
	
	public void updateCarte()
	{
		removeAll();
		ModelManager model = ModelManager.getInstance();
        
		if(model.getGiocatoriPartita().size() != 0)
		{			
			for(int i = 0; i < model.getNumeroGiocatoriPartita(); i++)
			{
				Giocatore giocatore = model.getGiocatoriPartita().get(i);
				for (Mano mano : giocatore.getMani()) 
				{	        
					ManoPanel manoPanel = new ManoPanel(mano);
					add(manoPanel); 	                               
				}
			}
		}
	}
}
