package controller.actionListeners;

import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import model.ModelManager;
import view.View;

/**
 * classe che definisce l'evento di creazione dell'utente che deve accadere al click di un componente swing
 */
public class CreateUserActionListener implements ActionListener
{
    @Override
	public void actionPerformed(ActionEvent e) 
	{      
    	ModelManager model = ModelManager.getInstance();
		String username = View.showUsernameInput(false);

		//se clicco su OK
		if (username != null) 
		{
			//controlla la validita dell'input (se il campo non è vuoto fai l'actionPerformed del CreateUserActionListener, altrimenti manda un errore)
			if (!username.isEmpty()) 
			{
				model.creaUtente(username);
            } 
			else 
			{
            	View.showError("l'username non può essere vuoto!");
            }
        } 
	}
}