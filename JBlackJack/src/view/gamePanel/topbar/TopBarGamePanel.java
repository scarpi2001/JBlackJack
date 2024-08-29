package view.gamePanel.topbar;

import java.awt.*;
import javax.swing.*;

import controller.actionListeners.partita.BackActionListener;

public class TopBarGamePanel extends JPanel
{
	private JButton buttonBack;

    public TopBarGamePanel() 
    {	
    	setLayout(new GridBagLayout());
        setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        
        //bottone back
        buttonBack = new JButton("Torna indietro");
        gbc.fill = GridBagConstraints.NONE;  
        gbc.weightx = 1.0;  
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;  
        add(buttonBack, gbc);
        
        buttonBack.addActionListener(new BackActionListener());
    }
}
