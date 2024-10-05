package view.gamePanel;

import javax.swing.JPanel;

import model.ModelManager;
import model.giocatore.Giocatore;
import model.giocatore.GiocatoreDealer;
import view.gamePanel.giocatorePanel.GiocatorePanel;

public class BodyPanel extends JPanel
{
	public BodyPanel() 
    {	   
		setOpaque(false);
    }
	
	public void updateDealer()
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
