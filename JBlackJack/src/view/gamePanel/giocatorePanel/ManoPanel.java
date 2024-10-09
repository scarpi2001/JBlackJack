package view.gamePanel.giocatorePanel;

import java.awt.*;
import javax.swing.*;

import model.ModelManager;
import model.carte.Mano;
import model.giocatori.Giocatore;
import model.giocatori.GiocatoreDealer;
import model.giocatori.GiocatoreUtente;
import view.MyJLabel;

/**
 * pannello della mano di un giocatore della partita,
 * contiene la label con il conteggio della mano,
 * il pannello con le carte che compongono la mano
 * e la label con lo stato della mano e l'eventuale vincita
 */
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
             
        //conteggio
        if(mano.getConteggio() != 0) add(createConteggioLabel(), BorderLayout.NORTH);
        	
        //carte
        add(new CartePanel(giocatore, mano.getCarte()), BorderLayout.CENTER);
        
        //stato
        if (!(giocatore instanceof GiocatoreDealer) && mano.getStato() != Mano.Stato.IN_CORSO) add(createStatoLabel(), BorderLayout.SOUTH);
        
        //indicatore turno
     	TurnoIndicatorPanel turnoIndicatorPanel = new TurnoIndicatorPanel();
     	add(turnoIndicatorPanel, BorderLayout.EAST);
    }
	
	private JLabel createConteggioLabel() 
	{
		ModelManager model = ModelManager.getInstance();
        JLabel conteggioLabel = new MyJLabel.Builder().build();
        
        if(giocatore instanceof GiocatoreDealer && !model.isCartaDealerScoperta())
        {
            if(mano.getCarte().size() > 0)
            {
                conteggioLabel.setText(mano.getCarte().get(0).getValore() + "");
            }
        } 
        else
        {
            conteggioLabel.setText(mano.getConteggio() + "");   
        }
        
        return conteggioLabel;
    }

    private JLabel createStatoLabel()
    {
        JLabel statoLabel = new MyJLabel.Builder().font(new Font("Arial", Font.BOLD, 16)).build();
        statoLabel.setText(getTestoStatoLabel());
        return statoLabel;
    }
    
    private String getTestoStatoLabel()
    {
    	ModelManager model = ModelManager.getInstance();
        if (giocatore instanceof GiocatoreUtente) 
        {
            if (mano.getStato() == Mano.Stato.VINTA)
            {
                return "Mano vinta, vincita: " + model.getScommessaUtentePartita() * 2;
            } 
            else if (mano.getStato() == Mano.Stato.PAREGGIATA) 
            {
                return "Mano pareggiata, vincita: " + model.getScommessaUtentePartita();
            }
        }
        return "Mano " + mano.getStato().toString().toLowerCase();
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
            
            ModelManager model = ModelManager.getInstance();

            int turnoCorrente = model.getTurnoPartita();
            int posizioneGiocatore = model.getGiocatoriPartita().indexOf(giocatore);

            //disegna la pallina solo se è il turno del giocatore e la mano è quella corrente 
            //uso == e non equals nel confrontare le mani 
            //perchè controllo proprio che facciano riferimento allo stesso oggetto
            if (turnoCorrente == posizioneGiocatore && mano == giocatore.getManoCorrente())
            {
            	super.paintComponent(g);
            	int size = Math.min(getWidth(), getHeight());
                g.setColor(Color.GREEN); 
                g.fillOval(0, 0, size, size);
            }
        }
    }
}
