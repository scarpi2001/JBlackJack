package view.menuPanel.bottombar;

import javax.swing.*;
import controller.actionListeners.menu.PlayActionListener;
import controller.actionListeners.partita.azioni.HitActionListener;
import controller.actionListeners.partita.azioni.SplitActionListener;

import java.awt.*;

public class GiocaEsciPanel extends JPanel 
{
    private JButton buttonGioca;
    private JButton buttonEsci;

    public GiocaEsciPanel() 
    {
    	setLayout(new GridLayout(2, 1, 0, 10)); 
        setOpaque(false);
        
        //gioca
        buttonGioca = new JButton("GIOCA");
        buttonGioca.setPreferredSize(new Dimension(180, 60));
        buttonGioca.addActionListener(new PlayActionListener()); 
        add(buttonGioca);
        
        //esci
        buttonEsci = new JButton("ESCI");
        buttonEsci.setPreferredSize(new Dimension(180, 60));
        buttonEsci.addActionListener(e -> System.exit(0)); 
        add(buttonEsci);         
    }
}
