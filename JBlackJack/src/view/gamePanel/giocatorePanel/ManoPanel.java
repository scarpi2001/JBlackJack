package view.gamePanel.giocatorePanel;

import java.awt.*;
import javax.swing.*;

import model.ModelManager;
import model.carte.Mano;
import model.giocatore.Giocatore;
import model.giocatore.GiocatoreDealer;
import model.giocatore.GiocatoreUtente;
import view.MyJLabel;

public class ManoPanel extends JPanel
{	
	private Giocatore giocatore;
	private Mano mano;
	
	public ManoPanel(Giocatore giocatore, Mano mano) 
    {
		this.giocatore = giocatore;
		this.mano = mano;
		
		setLayout(new BorderLayout()); 
        setOpaque(false);
        
        ModelManager model = ModelManager.getInstance();
        
        //conteggio
        if(mano.getConteggio() != 0)
        {        	
        	JLabel conteggioLabel = new MyJLabel.Builder().build();
        	
        	if(giocatore instanceof GiocatoreDealer && !model.isCartaDealerScoperta())
        	{
        		if(mano.getCarte().size() > 0)conteggioLabel.setText(mano.getCarte().get(0).getValore() + "");
        	}
        	else
        	{        		
        		conteggioLabel.setText(mano.getConteggio() + "");      		
        	}
        	
        	add(conteggioLabel, BorderLayout.NORTH);
        }
		
        //carte
        add(new CartePanel(giocatore, mano.getCarte()), BorderLayout.CENTER);
        
        //stato
        if(!(giocatore instanceof GiocatoreDealer) && mano.getStato() != Mano.Stato.IN_CORSO)
        {    
        	JLabel statoLabel = new MyJLabel.Builder().font(new Font("Arial", Font.BOLD, 16)).build();
        	if(giocatore instanceof GiocatoreUtente && mano.getStato() == Mano.Stato.VINTA)
        	{        		
        		statoLabel.setText("Mano " + mano.getStato().toString().toLowerCase() + ", vincita: " + model.getScommessaUtentePartita()*2);
        	}
        	else if(giocatore instanceof GiocatoreUtente && mano.getStato() == Mano.Stato.PAREGGIATA)
        	{        		
        		statoLabel.setText("Mano " + mano.getStato().toString().toLowerCase() + ", vincita: " + model.getScommessaUtentePartita());
        	}
        	else
        	{        		
        		statoLabel.setText("Mano " + mano.getStato().toString().toLowerCase());
        	}
        	add(statoLabel, BorderLayout.SOUTH);
        }
        
        //indicatore turno
     	TurnoIndicatorPanel turnoIndicatorPanel = new TurnoIndicatorPanel();
     	add(turnoIndicatorPanel, BorderLayout.EAST);
    }
	
	//classe per disegnare la pallina del turno
    private class TurnoIndicatorPanel extends JPanel
    {
        public TurnoIndicatorPanel()
        {
            setPreferredSize(new Dimension(10, 10)); 
            setOpaque(false); 
        }

        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            ModelManager model = ModelManager.getInstance();

            int turnoCorrente = model.getTurnoPartita();
            int posizioneGiocatore = model.getGiocatoriPartita().indexOf(giocatore);

            //disegna la pallina solo se è il turno del giocatore e la mano è quella corrente 
            //uso == e non equals nel confrontare le mani 
            //perchè controllo proprio che facciano riferimento allo stesso oggetto
            if (turnoCorrente == posizioneGiocatore && mano == giocatore.getManoCorrente())
            {
            	int size = Math.min(getWidth(), getHeight());
                g.setColor(Color.GREEN); 
                g.fillOval(0, 0, size, size);
            }
        }
    }
}
