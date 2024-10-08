package controller.actionListeners.menu.utente;

import java.awt.event.*;
import java.util.List;

import model.ModelManager;
import view.View;

/**
 * definisce l'evento di eliminazione dell'utente che deve accadere al click di un componente swing
 */
public class EliminaUtenteActionListener implements ActionListener
{

    @Override
	public void actionPerformed(ActionEvent e) 
	{           
		ModelManager model = ModelManager.getInstance();
		String username = model.getUsernameUtente();
		
		//se c'è più di un utente, elimino quello slezionato, altrimenti se ho un solo utente do errore
		List<String> utenti = model.getUtenti("src/resources/data/utenti.txt");
		if(utenti.size() > 1) 
		{		
			model.eliminaUtente(username, "src/resources/data/utenti.txt", "src/resources/data/dati_utenti/" + username + "_dati.txt");
			//setto il primo utente della lista di utenti 
			model.setUtente("src/resources/data/dati_utenti/" + model.getUtenti("src/resources/data/utenti.txt").get(0) + "_dati.txt", "src/resources/data/ultimo_utente.txt");
	    }
		else
		{
			View.showError("non è possibile eliminare l'utente se è l'unico presente");
		}
	}
}
