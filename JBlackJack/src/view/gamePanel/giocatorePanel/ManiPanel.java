package view.gamePanel.giocatorePanel;

import java.awt.FlowLayout;
import javax.swing.JPanel;
import java.util.List;

import model.carte.Mano;

public class ManiPanel extends JPanel
{ 	
	public ManiPanel(List<Mano> mani) 
    {
		setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10)); 
        setOpaque(false);
        
        removeAll();
        				
		for (Mano mano : mani) 
		{	        
			ManoPanel manoPanel = new ManoPanel(mano);
			add(manoPanel); 	                               
		}
    }
}
