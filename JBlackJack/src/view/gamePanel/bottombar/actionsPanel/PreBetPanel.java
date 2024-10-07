package view.gamePanel.bottombar.actionsPanel;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;
import controller.actionListeners.partita.azioni.ScommettiActionListener;

public class PreBetPanel extends JPanel
{
	private JButton buttonScommetti;	
	private JTextField fieldScommessa; 
	
	public PreBetPanel() 
	{
		setLayout(new GridLayout(2, 1, 0, 10)); 
        setOpaque(false);
        
        Dimension dimensione = new Dimension(120, 40);
        
        //scommessa
        fieldScommessa = new JTextField();  
        fieldScommessa.setPreferredSize(dimensione);
        fieldScommessa.setMinimumSize(dimensione);
        fieldScommessa.setMaximumSize(dimensione);

        add(fieldScommessa);
        
        //bottone scommetti
        buttonScommetti = new JButton("Scommetti");
        buttonScommetti.addActionListener(new ScommettiActionListener(fieldScommessa));
        buttonScommetti.setPreferredSize(dimensione);
        buttonScommetti.setMinimumSize(dimensione);
        buttonScommetti.setMaximumSize(dimensione);

        add(buttonScommetti);
	}  
}
