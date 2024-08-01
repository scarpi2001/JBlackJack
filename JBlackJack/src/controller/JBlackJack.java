package controller;

import model.ModelManager;
import view.View;

public class JBlackJack 
{
	public static void main(String[] args) 
	{
		ModelManager model = ModelManager.getInstance();
		View view = View.getInstance();
		model.addObserver(view);
		
		//gameloop
		caricaMenu();
	}
	
	/**
	 * metodo per caricare il menu 
	 * controlla che ci sia gia un utente creato, se non c'è chiede di crearlo
	 * se è gia stato creato un utente (quindi non sono al primo avvio) setta l'ultimo utente selezionato
	 */
	public static void caricaMenu() 
	{
		ModelManager model = ModelManager.getInstance();
		View view = View.getInstance();
		
		view.showMenuPanel();
		
        int numeroUtenti = model.getUtenti("src/resources/data/utenti.txt").length;
        if (numeroUtenti == 0) 
        {
            boolean isValid = false;
            while (!isValid) 
            {
                String username = View.showUsernameInput(true);
                //se clicco su OK
                if (username != null) 
                {
                    if (!username.isEmpty()) 
                    {
                    	model.creaUtente(username);
                        isValid = true;
                    } 
                    else 
                    {
                        View.showError("l'username non può essere vuoto!");
                    }
                }
            }
        } 
        else 
        {
            String username = model.getUltimoUtenteUsername("src/resources/data/ultimo_utente.txt");
            if (username != null) 
            {
                model.setUtente("src/resources/data/dati_utenti/" + username + "_dati.txt");
            }
        }
    }
}
