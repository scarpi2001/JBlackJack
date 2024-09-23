package view.gamePanel.bottombar.giocatorePanel;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.actionListeners.partita.azioni.BetActionListener;


public class BetPanel extends JPanel
{
	private JButton buttonBet;	
	private JButton plus;
	private JButton minus;
	
	public BetPanel() 
	{
		setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
		setOpaque(false);
	        
		//bet
	    buttonBet = new JButton("Bet");
	    buttonBet.addActionListener(new BetActionListener());
	    add(buttonBet);
	}  
}
