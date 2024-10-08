package view.gamePanel.giocatorePanel;

import java.awt.FlowLayout;
import javax.swing.JPanel;
import java.util.List;

import model.carte.Mano;
import model.giocatori.Giocatore;

/**
 * pannello delle mani di un giocatore della partita, 
 * contiene i pannelli delle mani del giocatore
 */
public class ManiPanel extends JPanel
{ 	
	public ManiPanel(Giocatore giocatore, List<Mano> mani) 
    {
		setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10)); 
        setOpaque(false);
        
        removeAll();
        
 
		for (Mano mano : mani) 
		{	        
			ManoPanel manoPanel = new ManoPanel(giocatore, mano);
			add(manoPanel); 	                               
		}
    }
}
