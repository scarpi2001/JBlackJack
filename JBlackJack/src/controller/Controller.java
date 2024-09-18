package controller;

import model.ModelManager;
import model.carte.Carta;
import model.carte.Mano;
import model.giocatore.Giocatore;
import model.giocatore.GiocatoreBot;
import model.giocatore.GiocatoreUtente;
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
		model.initPartita();
		distribuisciCarte();
		gameloop();
	}

	public void gameloop() 
	{	 
		Giocatore giocatore = model.getGiocatoreCorrente();

		giocatore.gioca();
		
		//se il giocatore è un bot, dopo che ha giocato, la mano è sicuramente terminata
		if (giocatore instanceof GiocatoreBot)
		{
			//passo al turno successivo 
			giocatore.manoSuccessiva();
			//e controllo se il round è finito, se lo è chiamo la logica di fine round che alla fine rigiocherà, se non lo è rigioco subito,
			if(model.roundFinito()) fineRound();
			else gameloop();
		}
		
		//se il giocatore è un utente e la sua mano è terminata
		if(giocatore instanceof GiocatoreUtente && giocatore.isManoTerminata())
		{
            giocatore.manoSuccessiva();   
            //rigioca, giocherà la mano successiva o il giocatore successivo
            gameloop();
	    }
	}

	/**
	 * gestisce la fine di un round
	 */
	private void fineRound() 
	{
		//aggiorna le chips del giocatore, rivela punteggi. (dal model)
		model.checkRisultati();
		
		//inizializza un nuovo round	
		model.setTurnoPartita(0);
		distribuisciCarte();
		gameloop();
	}
	
	/**
	 * distribuisce le carte ai giocatori
	 * simula la distribuzione di carte del blackjack (una alla volta)
	 */
	private void distribuisciCarte() 
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