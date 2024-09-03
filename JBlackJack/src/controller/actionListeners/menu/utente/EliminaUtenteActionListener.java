package controller.actionListeners.menu.utente;

import java.awt.event.*;
import java.util.List;

import model.FileUtils;
import model.ModelManager;
import view.View;

/**
 * classe che definisce l'evento di eliminazione dell'utente che deve accadere al click di un componente swing
 */
public class EliminaUtenteActionListener implements ActionListener
{

    @Override
	public void actionPerformed(ActionEvent e) 
	{           
		ModelManager model = ModelManager.getInstance();
		
		//se c'è più di un utente, elimino quello slezionato, altrimenti se ho un solo utente do errore
		List<String> utenti = FileUtils.leggiFile("src/resources/data/utenti.txt");
		if(utenti.size() > 1) 
		{		
			model.eliminaUtente(model.getUtenteUsername());
	    }
		else
		{
			View.showError("non è possibile eliminare l'utente se è l'unico presente");}
		}
}
