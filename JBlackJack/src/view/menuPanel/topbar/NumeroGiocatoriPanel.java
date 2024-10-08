package view.menuPanel.topbar;

import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.*;

import controller.actionListeners.menu.SetGiocatoriActionListener;
import view.MyJLabel;

/**
 * pannello per la scelta del numero di giocatori che partecipano alla partita, 
 * è composto da una label e una combobx per la scelta del numero
 */
public class NumeroGiocatoriPanel extends JPanel
{
	private JLabel giocatoriLabel;
	private JComboBox<Integer> comboBoxGiocatori;
	
	public NumeroGiocatoriPanel()
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);
        
        giocatoriLabel = new MyJLabel.Builder().text("Numero giocatori").font(new Font("Arial", Font.BOLD, 22)).build();
        add(giocatoriLabel);
        
        //select numero giocatori
        Integer[] numeroGiocatori = {1, 2, 3};
        comboBoxGiocatori = new JComboBox<>(numeroGiocatori);
        comboBoxGiocatori.addActionListener(new SetGiocatoriActionListener(comboBoxGiocatori));
        add(comboBoxGiocatori);
	}
}
