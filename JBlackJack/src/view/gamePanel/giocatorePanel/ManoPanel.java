package view.gamePanel.giocatorePanel;

import java.awt.*;
import javax.swing.*;

import model.carte.Mano;
import view.MyJLabel;

public class ManoPanel extends JPanel
{	
	public ManoPanel(Mano mano) 
    {
		setLayout(new BorderLayout()); 
        setOpaque(false);
        
        JLabel conteggioLabel = new MyJLabel.Builder().build();
		conteggioLabel.setText(mano.getConteggio() + "");
		add(conteggioLabel, BorderLayout.NORTH);
		
        add(new CartePanel(mano.getCarte()), BorderLayout.CENTER);
        
        JLabel statoLabel = new MyJLabel.Builder().build();
        statoLabel.setText(mano.getStato() + "");
		add(statoLabel, BorderLayout.SOUTH);
        
        //debug
        setBorder(BorderFactory.createLineBorder(Color.white, 2));
    }
}
