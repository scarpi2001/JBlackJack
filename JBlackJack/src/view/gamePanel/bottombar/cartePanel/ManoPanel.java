package view.gamePanel.bottombar.cartePanel;

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
        
        int width = 100;
        int height = 150;
        
        for (Carta carta : mano.getCarte()) 
        {
            Image scalata = new ImageIcon(carta.getImmagine()).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon iconaScalata = new ImageIcon(scalata);

            JLabel cartaLabel = new JLabel(iconaScalata);  
            add(cartaLabel);
        }
    }
}
