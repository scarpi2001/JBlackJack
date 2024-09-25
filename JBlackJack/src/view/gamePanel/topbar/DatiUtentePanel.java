package view.gamePanel.topbar;

import java.awt.*;
import javax.swing.*;

import model.ModelManager;
import view.MyJLabel;

public class DatiUtentePanel extends JPanel
{
	private JLabel usernameLabel;
	private JLabel chipsLabel;
    
    public DatiUtentePanel() 
    {
    	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);

        //labels
        usernameLabel = new MyJLabel.Builder().build();
        chipsLabel = new MyJLabel.Builder().build();
        
        add(new MyJLabel.Builder().text("Utente").font(new Font("Arial", Font.BOLD, 26)).build());
        add(Box.createRigidArea(new Dimension(0, 10)));
        
        add(usernameLabel);
        add(chipsLabel);
    }
    
    public JLabel getUsernameLabel()
    {
    	return usernameLabel;
    }
    public JLabel getChipsLabel()
    {
    	return chipsLabel;
    }
    
    public void aggiornaDatiUtente() 
    {
        ModelManager model = ModelManager.getInstance();
        if (model.getUsernameUtente() != null) 
        {
            usernameLabel.setText("Username: " + model.getUsernameUtente());
            chipsLabel.setText("Chips: " + model.getChipsUtente());
        } 
    }
}
