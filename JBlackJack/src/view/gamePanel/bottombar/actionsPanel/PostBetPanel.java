package view.gamePanel.bottombar.actionsPanel;

import java.awt.*;
import javax.swing.*;
import controller.actionListeners.partita.azioni.HitActionListener;
import controller.actionListeners.partita.azioni.SplitActionListener;
import controller.actionListeners.partita.azioni.StayActionListener;

public class PostBetPanel extends JPanel
{
	private JButton buttonHit;
	private JButton buttonStay;
	private JButton buttonSplit;
	
	public PostBetPanel() 
	{
        setLayout(new GridLayout(3, 1, 0, 10)); 
        setOpaque(false);

        //split
        buttonSplit = new JButton("Split");
        buttonSplit.setPreferredSize(new Dimension(80, 40)); 
        buttonSplit.setVisible(false); 
        buttonSplit.addActionListener(new SplitActionListener());
        add(buttonSplit);
        
        //hit
        buttonHit = new JButton("Hit");
        buttonHit.setPreferredSize(new Dimension(80, 40)); 
        buttonHit.addActionListener(new HitActionListener());
        add(buttonHit);

        //stay
        buttonStay = new JButton("Stay");
        buttonStay.setPreferredSize(new Dimension(80, 40)); 
        buttonStay.addActionListener(new StayActionListener());
        add(buttonStay);
    }

	
    public void setSplitVisible(boolean visible) 
    {
    	buttonSplit.setVisible(visible);
    }
}
