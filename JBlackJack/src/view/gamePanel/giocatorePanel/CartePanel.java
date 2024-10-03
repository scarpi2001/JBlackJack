package view.gamePanel.giocatorePanel;

import java.awt.*;
import javax.swing.*;

import java.util.List;

import model.carte.Carta;

public class CartePanel extends JPanel
{	
	public CartePanel(List<Carta> carte) 
    {
		setLayout(new FlowLayout(FlowLayout.LEFT)); 
        setOpaque(false);
        
        int width = 100;
        int height = 150;
        
        for (Carta carta : carte) 
        {      	
            Image scalata = new ImageIcon(carta.getImmagine()).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon iconaScalata = new ImageIcon(scalata);

            JLabel cartaLabel = new JLabel(iconaScalata);  
            add(cartaLabel);
        }
    }
}
