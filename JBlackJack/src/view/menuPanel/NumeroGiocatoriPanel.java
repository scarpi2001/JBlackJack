package view.menuPanel;

import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.*;

import controller.actionListeners.SetGiocatoriActionListener;
import view.MyJLabel;

public class NumeroGiocatoriPanel extends JPanel
{
	private JComboBox<Integer> comboBoxGiocatori;
	
	public NumeroGiocatoriPanel()
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);
        
		//select numero giocatori
        add(new MyJLabel.Builder().text("Numero giocatori").font(new Font("Arial", Font.BOLD, 22)).build());
        
        Integer[] numeroGiocatori = {1, 2, 3, 4};
        comboBoxGiocatori = new JComboBox<>(numeroGiocatori);
        comboBoxGiocatori.addActionListener(new SetGiocatoriActionListener(comboBoxGiocatori));
        add(comboBoxGiocatori);
	}
}
