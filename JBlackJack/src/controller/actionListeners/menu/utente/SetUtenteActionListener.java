package controller.actionListeners.menu.utente;

import java.awt.event.*;
import javax.swing.JComboBox;
import model.ModelManager;

/**
 * classe che definisce l'evento di set dell'utente che deve accadere al click di un componente swing
 */
public class SetUtenteActionListener implements ActionListener
{
	private JComboBox<String> comboBox;
	
    public SetUtenteActionListener(JComboBox<String> comboBox) 
    {
        this.comboBox = comboBox;
    }
	
    @Override
	public void actionPerformed(ActionEvent e) 
	{           
		ModelManager model = ModelManager.getInstance();
		String username = (String) comboBox.getSelectedItem();
		
		model.setUtente("src/resources/data/dati_utenti/" + username + "_dati.txt", "src/resources/data/ultimo_utente.txt");
	}
}