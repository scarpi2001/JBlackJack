package controller.actionListeners.menu.utente;

import java.awt.event.*;

import model.ModelManager;
import view.View;

/**
 * definisce l'evento di creazione dell'utente che deve accadere al click di un componente swing
 */
public class CreaUtenteActionListener implements ActionListener
{
    @Override
	public void actionPerformed(ActionEvent e) 
	{    
    	ModelManager model = ModelManager.getInstance();
		String username = View.showUsernameInput();

		if (username != null) 
		{
			//controlla la validita dell'input 
			//(se il campo non è vuoto, il nome non è troppo lungo e non è gia stato preso allora crea l'utente, altrimenti manda un errore)
			if (username.strip().isEmpty()) 
			{
				View.showError("l'username non può essere vuoto!");
				return;			
            } 
			
			if(username.length() > 20)
			{
				View.showError("l'username supera la lunghezza massima di 20 caratteri");
				return;
			}
			
			if (model.usernamePresente(username, "src/resources/data/utenti.txt"))
			{
				View.showError("Questo username è già stato preso.");
				return;
			}
			
			model.creaUtente(username, "src/resources/data/utenti.txt", "src/resources/data/dati_utenti/" + username + "_dati.txt");
			model.setUtente("src/resources/data/dati_utenti/" + username + "_dati.txt", "src/resources/data/ultimo_utente.txt");
        } 
	}
}