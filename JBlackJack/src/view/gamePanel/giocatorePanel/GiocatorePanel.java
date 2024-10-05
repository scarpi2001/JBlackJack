package view.gamePanel.giocatorePanel;

import java.awt.*;
import javax.swing.*;

import model.ModelManager;
import model.giocatore.Giocatore;
import view.MyJLabel;

public class GiocatorePanel extends JPanel
{ 		
	private Giocatore giocatore;

	public GiocatorePanel(Giocatore giocatore) 
    {
		this.giocatore = giocatore;

		setLayout(new BorderLayout());
		setOpaque(false);

		JLabel usernameLabel = new MyJLabel.Builder().build();
		usernameLabel.setText(giocatore.getUsername());
		add(usernameLabel, BorderLayout.NORTH);

		ManiPanel maniPanel = new ManiPanel(giocatore);
		add(maniPanel, BorderLayout.CENTER);
		
		// Aggiungo il pannello della pallina del turno
		TurnoIndicatorPanel turnoIndicatorPanel = new TurnoIndicatorPanel();
		add(turnoIndicatorPanel, BorderLayout.SOUTH);
		
		// debug
		//setBorder(BorderFactory.createLineBorder(Color.white, 2));
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

            //disegna la pallina solo se è il turno del giocatore
            if (turnoCorrente == posizioneGiocatore)
            {
            	int size = Math.min(getWidth(), getHeight());
                g.setColor(Color.GREEN); 
                g.fillOval(0, 0, size, size);
            }
        }
    }
}
