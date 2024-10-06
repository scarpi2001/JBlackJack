package view.gamePanel.giocatorePanel;

import java.awt.*;
import javax.swing.*;

import model.ModelManager;
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
        
        ModelManager model = ModelManager.getInstance();
        
        if(mano.getConteggio() != 0)
        {        	
        	JLabel conteggioLabel = new MyJLabel.Builder().build();
        	
        	if(giocatore instanceof GiocatoreDealer && !model.isCartaDealerScoperta())
        	{
        		if(mano.getCarte().size() > 1)conteggioLabel.setText(mano.getConteggio() - mano.getCarte().get(1).getValore() + "");
        	}
        	else
        	{        		
        		conteggioLabel.setText(mano.getConteggio() + "");      		
        	}
        	
        	add(conteggioLabel, BorderLayout.NORTH);
        }
		
        add(new CartePanel(giocatore, mano.getCarte()), BorderLayout.CENTER);
        
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
