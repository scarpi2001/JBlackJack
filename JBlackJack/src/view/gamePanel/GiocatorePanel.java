package view.gamePanel;

import java.awt.*;
import javax.swing.*;

import controller.actionListeners.HitActionListener;
import controller.actionListeners.StayActionListener;
import view.MyJLabel;

public class GiocatorePanel extends JPanel
{
	private JLabel usernameLabel;
	private JButton buttonHit;
	private JButton buttonStay;
    
    public GiocatorePanel() 
    {
    	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);

        //labels
        usernameLabel = new MyJLabel.Builder().text("Giocatore").font(new Font("Arial", Font.BOLD, 28)).build();
        add(usernameLabel);
        
        //hit
        buttonHit = new JButton("Hit");
        buttonHit.addActionListener(new HitActionListener());
        add(buttonHit);
        
        //stay
        buttonStay = new JButton("Stay");
        buttonStay.addActionListener(new StayActionListener());
        add(buttonStay);
    }
    
    public void aggiornaGiocatore()
    {
    	
    }
}
