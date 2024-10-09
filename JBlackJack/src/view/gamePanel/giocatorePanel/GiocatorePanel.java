package view.gamePanel.giocatorePanel;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import model.carte.Mano;
import model.giocatori.Giocatore;
import view.MyJLabel;

/**
 * pannello di un giocatore della partita,
 * composto dalla label del nome dell'username del giocatore e dal pannello che contiene le mani 
 */
public class GiocatorePanel extends JPanel
{ 		
	public GiocatorePanel(Giocatore giocatore) 
    {
		setLayout(new BorderLayout());
		setOpaque(false);

		JLabel usernameLabel = new MyJLabel.Builder().build();
		usernameLabel.setText(giocatore.getUsername());
		add(usernameLabel, BorderLayout.NORTH);

		List<Mano> mani = giocatore.getMani();
		ManiPanel maniPanel = new ManiPanel(giocatore, mani);
		add(maniPanel, BorderLayout.CENTER);
    } 
}
