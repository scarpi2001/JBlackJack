package view.gamePanel.giocatorePanel;

import java.awt.*;
import javax.swing.*;

import model.giocatore.Giocatore;
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

		ManiPanel maniPanel = new ManiPanel(giocatore);
		add(maniPanel, BorderLayout.CENTER);
    } 
}
