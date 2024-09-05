package controller;

import model.ModelManager;
import model.carte.Carta;
import model.carte.Mano;
import model.giocatore.Giocatore;
import view.View;

public class Controller 
{
	//CAMPI
	/**
	 * istanza del controller
	 */
	private static Controller instance;
	
	/**
	 * istanza del model
	 */
    private ModelManager model;
    
    /**
	 * istanza della view
	 */
    private View view;

    //COSTRUTTORE
    /**
     * costruttore del controller
     * crea le istanze di model e view e stabilisce la relazione di osservatore tra di loro.
     */    
    private Controller() 
    {
        model = ModelManager.getInstance();
        view = View.getInstance();
        model.addObserver(view);
    }
    
	public static Controller getInstance()
	{
		if (instance == null) instance = new Controller();
		return instance;
	}
    
    //METODI MENU  
    /**
	 * inizializza il menu 
	 * e controlla la presenza di un utente precedentemente creato,
	 * se non c'è chiede di crearlo
	 * se è gia stato creato (quindi non sono al primo avvio) setta l'ultimo utente selezionato
	 */
    public void initMenu() 
    {		
		String ultimoUtente = model.getUltimoUtente("src/resources/data/ultimo_utente.txt");
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
                    	model.creaUtente(username, "src/resources/data/utenti.txt", "src/resources/data/dati_utenti/" + username + "_dati.txt");
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
            model.setUtente("src/resources/data/dati_utenti/" + ultimoUtente + "_dati.txt", "src/resources/data/ultimo_utente.txt");
        }
    }
    
    //METODI PARTITA
    /**
	 * inizializza la partita
	 */
	public void initPartita()
	{
		model.initMazzo();
		model.initGiocatori();
		model.distribuisciCarte();
		//gioca();
	}
	
	private void gioca()
	{
		while(!model.roundFinito())
		{
			model.giocaTurno();
		}
       
        //il round è finito, ridistribuisci le carte e ricomincia dal turno 0
        model.distribuisciCarte();
        model.setTurno(0);
        gioca();
	}
}