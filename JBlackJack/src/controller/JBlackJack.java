package controller;

import model.ModelManager;
import model.carte.*;
import view.View;

public class JBlackJack 
{
	public static void main(String[] args) throws InterruptedException 
	{
		ModelManager model = ModelManager.getInstance();
		View view = View.getInstance();
		model.addObserver(view);
		
		//gameloop
		//AudioManager.getInstance().play("src/resources/audio/background.wav", true);
		avviaMenu(model);	
	}
	
	/**
	 * metodo che controlla la presenza di un utente precedentemente creato, se non c'è chiede di crearlo
	 * se è gia stato creato (quindi non sono al primo avvio) setta l'ultimo utente selezionato
	 */
	public static void avviaMenu(ModelManager model) 
	{		
		String ultimoUtente = model.getUltimoUtenteUsername("src/resources/data/ultimo_utente.txt");
        if (ultimoUtente == null) 
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
            model.setUtente("src/resources/data/dati_utenti/" + ultimoUtente + "_dati.txt");
        }
    }
}
