package view.gamePanel.giocatorePanel;

import java.awt.*;
import javax.swing.*;

import model.giocatore.Giocatore;
import view.MyJLabel;

public class GiocatorePanel extends JPanel
{ 		
	public GiocatorePanel(Giocatore giocatore) 
    {
		setLayout(new BorderLayout());
		setOpaque(false);
		
		JLabel usernameLabel = new MyJLabel.Builder().build();
		usernameLabel.setText(giocatore.getUsername());
		add(usernameLabel, BorderLayout.NORTH);
		
		ManiPanel maniPanel = new ManiPanel(giocatore.getMani());
		add(maniPanel, BorderLayout.CENTER);
		
		//debug
		setBorder(BorderFactory.createLineBorder(Color.white, 2));
    }
}
