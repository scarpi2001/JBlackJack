package controller;

import javax.swing.Timer;

import controller.actionListeners.partita.timers.TimerDistribuzioneActionListener;
import controller.actionListeners.partita.timers.TimerGameLoopActionListener;

import model.ModelManager;
import model.giocatore.*;
import view.View;

@SuppressWarnings("deprecation")
/**
 * classe principale del package controller
 * gestisce l'inizializzazione del menu e il flusso della partita
 */
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
     * crea le istanze di model e view e stabilisce la relazione di osservazione tra di loro.
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
     * gestisce il flusso della partita
     */
    public void gameloop() 
    {
        Giocatore giocatore = model.getGiocatoreCorrente();
        giocatore.gioca();
            
        //se il giocatore è il dealer 
        if(giocatore instanceof GiocatoreDealer) model.setCartaDealerScoperta(true);
        
        //se il giocatore è un bot o il dealer (il dealer è un bot)
        if (giocatore instanceof GiocatoreBot) 
        {        	
            Timer timer = new Timer(700, new TimerGameLoopActionListener(giocatore));      
            timer.setRepeats(false);
            timer.start();
        } 
        
        //se il giocatore è l'utente e la sua mano è terminata
        if (giocatore instanceof GiocatoreUtente && giocatore.isManoTerminata()) 
        {
            giocatore.manoSuccessiva();
            gameloop();
        }
    }
	
	/**
	 * distribuisce le carte ai giocatori 
	 * simula la distribuzione di carte del blackjack (una alla volta)
	 */
	public void distribuisciCarte()
	{	
	    //resetta lo stato dei giocatori
	    for (Giocatore giocatore : model.getGiocatoriPartita()) giocatore.resetStato();
	      
	    //inizio a distribuire
	    model.setCartaDealerScoperta(false);
	    model.setDistribuzionePartita(true);
	    
	    //do subito la prima carta
	    Giocatore primoGiocatore = model.getGiocatoriPartita().get(0);
        primoGiocatore.hit();

	    //imposto timer per rallentare la distribuzione delle carte successive
	    Timer timer = new Timer(700, new TimerDistribuzioneActionListener()); 
	    timer.start();	  
   }
}