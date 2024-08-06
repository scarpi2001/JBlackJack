package controller.actionListeners;

import java.awt.event.*;
import javax.swing.JComboBox;
import model.ModelManager;

/**
 * classe che definisce l'evento di set dell'utente che deve accadere al click di un componente swing
 */
public class SetUserActionListener implements ActionListener
{
	private JComboBox<String> comboBoxUtenti;
	
    public SetUserActionListener(JComboBox<String> comboBox) 
    {
        this.comboBoxUtenti = comboBox;
    }
	
    @Override
	public void actionPerformed(ActionEvent e) 
	{           
		ModelManager model = ModelManager.getInstance();
		String username = (String) comboBoxUtenti.getSelectedItem();
		model.setUtente("src/resources/data/dati_utenti/" + username + "_dati.txt");
	}
}