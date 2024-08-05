package view.menuPanel;

import javax.swing.*;
import java.awt.*;

import model.ModelManager;
import view.MyJLabel;


/**
 * panel che rappresenta la bottomBar del menu
 */
public class BottomBar extends JPanel 
{
    private JButton buttonGioca;
    
    private JLabel usernameLabel;
    private JLabel chipsLabel;
    private JLabel maniGiocateLabel;
    private JLabel maniVinteLabel;
    private JLabel maniPerseLabel;
    private JLabel livelloLabel;

    public BottomBar() 
    {
        ModelManager model = ModelManager.getInstance();

        setLayout(new BorderLayout());
        setBackground(new Color(0, 0, 0, 0));

        //bottone play
        buttonGioca = new JButton("GIOCA");
        add(buttonGioca, BorderLayout.LINE_END);

        //panel per i dati utente
        JPanel datiUtentePanel = new JPanel();
        datiUtentePanel.setLayout(new BoxLayout(datiUtentePanel, BoxLayout.Y_AXIS));
        datiUtentePanel.setOpaque(false);

        //labels
        usernameLabel = new MyJLabel();
        chipsLabel = new MyJLabel();
        maniGiocateLabel = new MyJLabel();
        maniVinteLabel = new MyJLabel();
        maniPerseLabel = new MyJLabel();
        livelloLabel = new MyJLabel();

        datiUtentePanel.add(usernameLabel);
        datiUtentePanel.add(chipsLabel);
        datiUtentePanel.add(maniGiocateLabel);
        datiUtentePanel.add(maniVinteLabel);
        datiUtentePanel.add(maniPerseLabel);
        datiUtentePanel.add(livelloLabel);

        add(datiUtentePanel, BorderLayout.CENTER);

    }
    
    public void aggiornaDatiUtente() 
    {
        ModelManager model = ModelManager.getInstance();
        if (model.getUtenteUsername() != null) 
        {
            usernameLabel.setText("Username: " + model.getUtenteUsername());
            chipsLabel.setText("Chips: " + model.getUtenteChips());
            maniGiocateLabel.setText("Mani Giocate: " + model.getUtenteManiGiocate());
            maniVinteLabel.setText("Mani Vinte: " + model.getUtenteManiVinte());
            maniPerseLabel.setText("Mani Perse: " + model.getUtenteManiPerse());
            livelloLabel.setText("Livello: " + model.getUtenteLivello());
        } 
    }
}