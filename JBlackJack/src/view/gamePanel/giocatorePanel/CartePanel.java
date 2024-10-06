package view.gamePanel.giocatorePanel;

import java.awt.*;
import javax.swing.*;
import java.util.List;

import model.ModelManager;
import model.carte.Carta;
import model.giocatore.Giocatore;
import model.giocatore.GiocatoreDealer;

public class CartePanel extends JPanel
{	
	
	public CartePanel(Giocatore giocatore, List<Carta> carte) 
    {
		setLayout(new FlowLayout(FlowLayout.LEFT)); 
        setOpaque(false);
        
        int width = 100;
        int height = 150;
        
        ModelManager model = ModelManager.getInstance();
        boolean isDealer = giocatore instanceof GiocatoreDealer;
        
        for (int i = 0; i < carte.size(); i++) 
        {   
            Carta carta = carte.get(i);
            ImageIcon iconaScalata;
            
            //se il giocatore è il dealer, la carta è la 2 e non è stata scoperta
            if (isDealer && i == 1 && !model.isCartaDealerScoperta()) 
            {
                //carico immagine carta coperta
                Image coperta = new ImageIcon("src/resources/images/carte/BackgroundBlack.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                iconaScalata = new ImageIcon(coperta);
            } 
            else 
            {
                Image scalata = new ImageIcon(carta.getImmagine()).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                iconaScalata = new ImageIcon(scalata);
            }
            
            JLabel cartaLabel = new JLabel(iconaScalata);  
            add(cartaLabel);
        }        
    }
}
