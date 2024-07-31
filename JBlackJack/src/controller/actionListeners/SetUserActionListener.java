package controller.actionListeners;

import java.awt.event.*;
import model.ModelManager;

/**
 * classe che contiene la logica necessaria a selezionare un utente
 */
public class SetUserActionListener implements ActionListener
{
	private String username;
	
	public SetUserActionListener(String username) {
        this.username = username;
    }
	
    @Override
	public void actionPerformed(ActionEvent e) 
	{           
		ModelManager model = ModelManager.getInstance();
		model.setUtente("src/resources/data/" + username + "_dati.txt");
	}
}