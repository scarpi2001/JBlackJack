package view.gamePanel;

import java.awt.*;
import javax.swing.*;

import controller.actionListeners.partita.BackActionListener;
import view.DatiUtentePanel;

/**
 * pannello per la topbar della partita, 
 * contiene il bottone per tornare indietro al menu e ilpannello per mostrare i dati utente
 */
public class TopBarGamePanel extends JPanel
{
	private JButton buttonBack;
	private DatiUtentePanel datiUtentePanel;

    public TopBarGamePanel() 
    {	
    	setLayout(new GridBagLayout());
		setOpaque(false);
		
		GridBagConstraints gbc = new GridBagConstraints();
        
		//dati utente gamePanel 
		datiUtentePanel = new DatiUtentePanel();
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 0.0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(datiUtentePanel, gbc);
		
		//bottone back
        buttonBack = new JButton("Torna indietro");
        gbc.fill = GridBagConstraints.NONE;  
        gbc.weightx = 1.0;  
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;  
        add(buttonBack, gbc);
        
        buttonBack.addActionListener(new BackActionListener());
    }
    
    public void aggiornaDatiUtente() 
    {
    	datiUtentePanel.aggiornaDatiUtente();
    }
}

