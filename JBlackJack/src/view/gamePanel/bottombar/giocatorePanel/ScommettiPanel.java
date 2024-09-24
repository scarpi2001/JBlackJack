package view.gamePanel.bottombar.giocatorePanel;

import javax.swing.*;
import controller.actionListeners.partita.azioni.ScommettiActionListener;

public class ScommettiPanel extends JPanel
{
	private JButton buttonScommetti;	
	private JTextField fieldScommessa; 
	
	public ScommettiPanel() 
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);
	        
	    //campo di testo scommessa
        fieldScommessa = new JTextField();  
        add(fieldScommessa);
        
        //bottone scommetti
        buttonScommetti = new JButton("Scommetti");
        buttonScommetti.addActionListener(new ScommettiActionListener(fieldScommessa));
        add(buttonScommetti);
	}  
}
