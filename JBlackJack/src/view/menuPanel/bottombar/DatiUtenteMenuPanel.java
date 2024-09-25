package view.menuPanel.bottombar;

import javax.swing.*;

import model.ModelManager;
import view.MyJLabel;
import view.gamePanel.topbar.DatiUtentePanel;

public class DatiUtenteMenuPanel extends DatiUtentePanel
{
    private JLabel maniGiocateLabel;
    private JLabel maniVinteLabel;
    private JLabel maniPareggiateLabel;
    private JLabel maniPerseLabel;
    private JLabel livelloLabel;
    
    public DatiUtenteMenuPanel() 
    {
        //labels
        maniGiocateLabel = new MyJLabel.Builder().build();
        maniVinteLabel = new MyJLabel.Builder().build();
        maniPareggiateLabel = new MyJLabel.Builder().build();
        maniPerseLabel = new MyJLabel.Builder().build();
        livelloLabel = new MyJLabel.Builder().build();
        
        add(maniGiocateLabel);
        add(maniVinteLabel);
        add(maniPareggiateLabel);
        add(maniPerseLabel);
        add(livelloLabel);
    }
    
    @Override
    public void aggiornaDatiUtente() 
    {
        ModelManager model = ModelManager.getInstance();
        if (model.getUsernameUtente() != null) 
        {
            getUsernameLabel().setText("Username: " + model.getUsernameUtente());
            getChipsLabel().setText("Chips: " + model.getChipsUtente());
            maniGiocateLabel.setText("Mani Giocate: " + model.getManiGiocateUtente());
            maniVinteLabel.setText("Mani Vinte: " + model.getManiVinteUtente());
            maniPareggiateLabel.setText("Mani Pareggiate: " + model.getManiPareggiateUtente());
            maniPerseLabel.setText("Mani Perse: " + model.getManiPerseUtente());
            livelloLabel.setText("Livello: " + model.getLivelloUtente());
        } 
    }
}
