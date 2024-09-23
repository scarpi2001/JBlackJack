package view.gamePanel.bottombar.giocatorePanel;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;


import controller.actionListeners.partita.azioni.HitActionListener;
import controller.actionListeners.partita.azioni.SplitActionListener;
import controller.actionListeners.partita.azioni.StayActionListener;

public class ActionsPanel extends JPanel
{
	private JButton buttonHit;
	private JButton buttonStay;
	private JButton buttonSplit;
	
	public ActionsPanel() 
	{
		setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
		setOpaque(false);
	        
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
}
