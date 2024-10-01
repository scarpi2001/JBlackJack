package view.gamePanel.bottombar;

import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

import model.carte.Carta;
import model.carte.Mano;

public class ManoPanel extends JPanel
{	
	public ManoPanel(Mano mano) 
    {
		setLayout(new FlowLayout(FlowLayout.LEFT)); 
        setOpaque(false);
        
        for (Carta carta : mano.getCarte()) 
        {
            JLabel cartaLabel = new JLabel(new ImageIcon(carta.getImmagine()));  
            add(cartaLabel);
        }
    }
}
