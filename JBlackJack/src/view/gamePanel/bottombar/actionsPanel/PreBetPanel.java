package view.gamePanel.bottombar.actionsPanel;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

import controller.actionListeners.partita.azioniUtente.ScommettiActionListener;

/**
 * pannello che compare in fase di "pre bet"
 * contiene il campo di testo dove inserire la somma da scommettere
 * e il bottone per scommettere
 */
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
