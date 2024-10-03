package view.gamePanel.bottombar.actionsPanel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;


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
        setLayout(new GridLayout(3, 1, 10, 10)); 
        setOpaque(false);

        // Hit
        buttonHit = new JButton("Hit");
        buttonHit.setPreferredSize(new Dimension(80, 40)); 
        buttonHit.addActionListener(new HitActionListener());
        add(buttonHit);

        // Stay
        buttonStay = new JButton("Stay");
        buttonStay.setPreferredSize(new Dimension(80, 40)); 
        buttonStay.addActionListener(new StayActionListener());
        add(buttonStay);

        // Split
        buttonSplit = new JButton("Split");
        buttonSplit.setPreferredSize(new Dimension(80, 40)); 
        buttonSplit.setVisible(false); 
        buttonSplit.addActionListener(new SplitActionListener());
        add(buttonSplit);
    }

	
    public void setSplitVisible(boolean visible) 
    {
    	buttonSplit.setVisible(visible);
    }
}
