package view.gamePanel.giocatorePanel;

import java.awt.FlowLayout;
import javax.swing.JPanel;
import java.util.List;

import model.carte.Mano;
import model.giocatore.Giocatore;

public class ManiPanel extends JPanel
{ 	
	public ManiPanel(Giocatore giocatore) 
    {
		setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10)); 
        setOpaque(false);
        
        removeAll();
        
        List<Mano> mani = giocatore.getMani();
		for (Mano mano : mani) 
		{	        
			ManoPanel manoPanel = new ManoPanel(giocatore, mano);
			add(manoPanel); 	                               
		}
    }
}
