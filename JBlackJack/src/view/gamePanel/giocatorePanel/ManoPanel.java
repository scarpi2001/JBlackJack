package view.gamePanel.giocatorePanel;

import java.awt.*;
import javax.swing.*;

import model.carte.Mano;
import model.giocatore.Giocatore;
import model.giocatore.GiocatoreDealer;
import view.MyJLabel;

public class ManoPanel extends JPanel
{	
	public ManoPanel(Giocatore giocatore, Mano mano) 
    {
		setLayout(new BorderLayout()); 
        setOpaque(false);
        
        if(mano.getConteggio() != 0)
        {        	
        	JLabel conteggioLabel = new MyJLabel.Builder().build();
        	conteggioLabel.setText(mano.getConteggio() + "");
        	add(conteggioLabel, BorderLayout.NORTH);
        }
		
        add(new CartePanel(mano.getCarte()), BorderLayout.CENTER);
        
        if(!(giocatore instanceof GiocatoreDealer) && mano.getStato() != Mano.Stato.IN_CORSO)
        {        	
        	JLabel statoLabel = new MyJLabel.Builder().build();
        	statoLabel.setText(mano.getStato() + "");
        	add(statoLabel, BorderLayout.SOUTH);
        }
        
        //debug
        //setBorder(BorderFactory.createLineBorder(Color.white, 2));
    }
}
