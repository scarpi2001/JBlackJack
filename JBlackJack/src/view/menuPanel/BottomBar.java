package view.menuPanel;

import javax.swing.*;
import java.awt.*;

/**
 * panel che rappresenta la bottomBar del menu
 */
public class BottomBar extends JPanel 
{
    private JButton buttonGioca;
    private UtenteDatiPanel datiUtentePanel;

    public BottomBar() 
    {
        setLayout(new BorderLayout());
        setOpaque(false);

        //bottone play
        buttonGioca = new JButton("GIOCA");
        add(buttonGioca, BorderLayout.LINE_END);

        //panel per i dati dell'utente
        datiUtentePanel = new UtenteDatiPanel();
        add(datiUtentePanel, BorderLayout.CENTER);

    }
    
    public void aggiornaDatiUtente() 
    {
        datiUtentePanel.aggiornaDatiUtente();
    }
}