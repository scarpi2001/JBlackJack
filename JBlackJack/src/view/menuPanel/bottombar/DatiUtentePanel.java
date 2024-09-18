package view.menuPanel.bottombar;

import java.awt.*;
import javax.swing.*;

import model.ModelManager;
import view.MyJLabel;

public class DatiUtentePanel extends JPanel
{
	private JLabel usernameLabel;
    private JLabel chipsLabel;
    private JLabel maniGiocateLabel;
    private JLabel maniVinteLabel;
    private JLabel maniPerseLabel;
    private JLabel livelloLabel;
    
    public DatiUtentePanel() 
    {
    	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);

        //labels
        usernameLabel = new MyJLabel.Builder().build();
        chipsLabel = new MyJLabel.Builder().build();
        maniGiocateLabel = new MyJLabel.Builder().build();
        maniVinteLabel = new MyJLabel.Builder().build();
        maniPerseLabel = new MyJLabel.Builder().build();
        livelloLabel = new MyJLabel.Builder().build();
        
        add(new MyJLabel.Builder().text("Utente").font(new Font("Arial", Font.BOLD, 28)).build());
        add(Box.createRigidArea(new Dimension(0, 10)));
        
        add(usernameLabel);
        add(chipsLabel);
        add(maniGiocateLabel);
        add(maniVinteLabel);
        add(maniPerseLabel);
        add(livelloLabel);
    }
    
    public void aggiornaDatiUtente() 
    {
        ModelManager model = ModelManager.getInstance();
        if (model.getUsernameUtente() != null) 
        {
            usernameLabel.setText("Username: " + model.getUsernameUtente());
            chipsLabel.setText("Chips: " + model.getChipsUtente());
            maniGiocateLabel.setText("Mani Giocate: " + model.getManiGiocateUtente());
            maniVinteLabel.setText("Mani Vinte: " + model.getManiVinteUtente());
            maniPerseLabel.setText("Mani Perse: " + model.getManiPerseUtente());
            livelloLabel.setText("Livello: " + model.getLivelloUtente());
        } 
    }
}
