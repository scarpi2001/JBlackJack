package controller.actionListeners.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

import model.ModelManager;

/**
 * cambia il numero di giocatori che parecipano alla partita al click di una comboBox swing
 */
public class SetGiocatoriActionListener implements ActionListener 
{
	private JComboBox<Integer> comboBoxGiocatori;
	
    public SetGiocatoriActionListener(JComboBox<Integer> comboBox) 
    {
        comboBoxGiocatori = comboBox;
    }
	
    @Override
	public void actionPerformed(ActionEvent e) 
	{           
		ModelManager.getInstance().setNumeroGiocatoriPartita((int)comboBoxGiocatori.getSelectedItem());
	}

}
