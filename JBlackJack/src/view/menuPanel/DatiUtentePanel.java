package view.menuPanel;

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
