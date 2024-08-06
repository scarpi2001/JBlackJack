package view.menuPanel;

import javax.swing.*;
import java.awt.*;

import controller.actionListeners.PlayActionListener;

/**
 * panel che rappresenta la bottomBar del menu
 */
public class BottomBar extends JPanel 
{
    private JButton buttonGioca;
    private UtenteDatiPanel datiUtentePanel;

    public BottomBar() 
    {	
    	setLayout(new GridBagLayout());
        setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        
        //panel per i dati dell'utente
        datiUtentePanel = new UtenteDatiPanel();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LAST_LINE_START;
        add(datiUtentePanel, gbc);

        //bottone play
        buttonGioca = new JButton("GIOCA");
        buttonGioca.setPreferredSize(new Dimension(180, 60)); 
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LAST_LINE_END;
        
        buttonGioca.addActionListener(new PlayActionListener());
        add(buttonGioca, gbc);

    }
    
    public void aggiornaDatiUtente() 
    {
        datiUtentePanel.aggiornaDatiUtente();
    }
}