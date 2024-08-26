package controller.actionListeners.partita;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import model.ModelManager;

/**
 * classe che definisce l'evento di set del numero di giocatori che deve accadere al click di un componente swing
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
		ModelManager model = ModelManager.getInstance();
		model.setNumeroGiocatori((int)comboBoxGiocatori.getSelectedItem());
	}

}
