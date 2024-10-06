package view.menuPanel.bottombar;

import javax.swing.*;
import view.DatiUtentePanel;
import java.awt.*;

/**
 * panel che rappresenta la bottomBar del menu
 */
public class BottomBarMenuPanel extends JPanel 
{
    private DatiUtentePanel datiUtenteMenuPanel;
    private GiocaEsciPanel giocaEsciPanel;

    public BottomBarMenuPanel() 
    {	
    	setLayout(new GridBagLayout());
        setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        
        //panel per i dati dell'utente
        datiUtenteMenuPanel = new DatiUtentePanel();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        add(datiUtenteMenuPanel, gbc);

        //pannello giocaEsci
        giocaEsciPanel = new GiocaEsciPanel();  // Creiamo il pannello con i bottoni
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;   // Non si espande orizzontalmente
        gbc.weighty = 0.0;   // Non si espande verticalmente
        gbc.gridx = 1;       // Colonna destra
        gbc.gridy = 1;       // Seconda riga (in basso)
        gbc.anchor = GridBagConstraints.LAST_LINE_END;  // Posizionato in basso a destra
        add(giocaEsciPanel, gbc);

    }
    
    public void aggiornaDatiUtente() 
    {
    	datiUtenteMenuPanel.aggiornaDatiUtente();
    }
}