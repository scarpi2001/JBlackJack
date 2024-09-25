package controller;

import model.ModelManager;
import model.giocatore.Giocatore;
import model.giocatore.GiocatoreBot;
import model.giocatore.GiocatoreUtente;
import view.View;

@SuppressWarnings("deprecation")
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
	 * se è gia stato creato (quindi non sono al primo avvio) imposta l'ultimo utente selezionato
	 */
    public void initMenu() 
    {		
		String ultimoUtente = model.getUltimoUtente("src/resources/data/ultimo_utente.txt");
        if (ultimoUtente == null) 
        {
            boolean isValid = false;
            while (!isValid) 
            {
                String username = View.showUsernameInput();

                //se clicco su OK
                if (username != null) 
                {
                	if (username.strip().isEmpty()) 
        			{
        				View.showError("l'username non può essere vuoto!");
                    } 	
                	else if(username.length() > 20)
        			{
        				View.showError("l'username supera la lunghezza massima di 20 caratteri");
        			}   			
                    else 
                    {
                    	model.creaUtente(username, "src/resources/data/utenti.txt", "src/resources/data/dati_utenti/" + username + "_dati.txt");
                    	model.setUtente("src/resources/data/dati_utenti/" + username + "_dati.txt", "src/resources/data/ultimo_utente.txt");
                        isValid = true;
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
     * gestisce il flusso di una partita
     */
	public void gameloop() 
	{	 
		Giocatore giocatore = model.getGiocatoreCorrente(); 
			
		giocatore.gioca();
		
		//dopo aver giocato devo gestire il turno
		//se il giocatore è un bot, dopo che ha giocato, la mano è sicuramente terminata
		//(perchè o fa bj o sballa o fa stay)
		//quindi passo alla mano successiva o turno successivo
		if (giocatore instanceof GiocatoreBot)
		{
			giocatore.manoSuccessiva();			
			if(model.FinePartita()) finePartita();
			else gameloop();
		}	
		//se è un utente e la mano è terminata passo alla mano successiva
		else if(giocatore instanceof GiocatoreUtente && giocatore.isManoTerminata())
		{
			//rigioca, giocherà la mano successiva o il giocatore successivo
            giocatore.manoSuccessiva();   
            gameloop();
	    }
	}

	/**
	 * gestisce la fine di una partita
	 */
	private void finePartita() 
	{
		model.aggiornaStats();
		model.nuovaPartita();
	}
	
	/**
	 * distribuisce le carte ai giocatori
	 * simula la distribuzione di carte del blackjack (una alla volta)
	 */
	public void distribuisciCarte() 
	{    	
		//resetta lo stato dei giocatori
		for (Giocatore giocatore : model.getGiocatoriPartita()) 
		{ 
			giocatore.resetStato();
		}
		
		//primo giro
		for (Giocatore giocatore : model.getGiocatoriPartita()) 
		{ 
			giocatore.hit();
		}
		
		//secondo giro
		for (Giocatore giocatore : model.getGiocatoriPartita()) 
		{
			giocatore.hit();                
		}
	}
}