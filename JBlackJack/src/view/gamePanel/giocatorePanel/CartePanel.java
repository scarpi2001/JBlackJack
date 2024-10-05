package view.gamePanel.giocatorePanel;

import java.awt.*;
import javax.swing.*;
import javax.swing.Timer;
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
    
	
	/*
	public CartePanel(List<Carta> carte) 
    {
        setLayout(new FlowLayout(FlowLayout.LEFT)); 
        setOpaque(false);
        
        int width = 100;
        int height = 150;

        Timer timer = new Timer(500, null); // Timer con un ritardo di 500 ms tra l'aggiunta delle carte
        timer.setRepeats(true);

        final int[] cartaIndex = {0}; // Usato per tenere traccia della carta corrente

        timer.addActionListener(e -> {
            if (cartaIndex[0] < carte.size()) 
            {
                // Ottieni la carta corrente
                Carta carta = carte.get(cartaIndex[0]);

                // Scala l'immagine della carta
                Image scalata = new ImageIcon(carta.getImmagine()).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                ImageIcon iconaScalata = new ImageIcon(scalata);

                // Crea il JLabel e lo aggiungi al pannello
                JLabel cartaLabel = new JLabel(iconaScalata);
                add(cartaLabel);

                revalidate(); // Aggiorna il layout del pannello
                repaint();    // Ridisegna il pannello per mostrare la nuova carta

                // Passa alla prossima carta
                cartaIndex[0]++;
            } 
            else 
            {
                // Se tutte le carte sono state aggiunte, ferma il timer
                timer.stop();
            }
        });

        timer.start(); // Avvia il timer per l'aggiunta progressiva delle carte
    }
    */
}
