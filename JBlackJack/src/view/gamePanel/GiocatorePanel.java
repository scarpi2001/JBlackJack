package view.gamePanel;

import java.awt.*;
import javax.swing.*;

import controller.actionListeners.partita.HitActionListener;
import controller.actionListeners.partita.SplitActionListener;
import controller.actionListeners.partita.StayActionListener;
import view.MyJLabel;

public class GiocatorePanel extends JPanel
{
	private JLabel usernameLabel;
	private JButton buttonHit;
	private JButton buttonStay;
	private JButton buttonSplit;
    
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
        
        //split
        buttonSplit = new JButton("Split");
        buttonSplit.setVisible(false);
        buttonSplit.addActionListener(new SplitActionListener());
        add(buttonSplit);
    }
    
    public void setSplitVisible(boolean visible)
    {
    	buttonSplit.setVisible(visible);
    }
    
    public void aggiornaGiocatore()
    {
    	
    }
    
}
