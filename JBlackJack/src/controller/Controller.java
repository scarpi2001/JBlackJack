package controller;

import java.util.List;

import model.Giocatore;
import model.ModelManager;
import model.carte.Carta;
import view.AudioManager;
import view.View;

public class Controller 
{
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
    
    /**
	 * metodo per ottenere/creare l'istanza del controller
	 * @return l'istanza del controller
	 */
	public static Controller getInstance()
	{
		if (instance == null) instance = new Controller();
		return instance;
	}
    
    //METODI MENU
    /**
     * metodo che inizializza il menu e avvia la la clip di sottofondo
     */
    public void start() 
    {
    	AudioManager.getInstance().play("src/resources/audio/background.wav", true);
        initMenu();
    }
    
    /**
	 * metodo privato che controlla la presenza di un utente precedentemente creato, se non c'è chiede di crearlo
	 * se è gia stato creato (quindi non sono al primo avvio) setta l'ultimo utente selezionato
	 */
    private void initMenu() 
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
    
    //METODI PARTITA
    /**
	 * metodo che inizializza la partita
	 */
	public void initPartita()
	{
		model.initMazzo();
		model.initGiocatori();
		initCarte();
	}
	
    /**
	 * metodo che aggiunge una carta al giocatore giusto in base al turno della partita
	 */
    public void hit()
    {
    	List<Giocatore> giocatori = model.getGiocatori();
        int turno = model.getTurno();

        if (turno == giocatori.size()) initCarte();
  
        Giocatore giocatore = giocatori.get(turno);
        giocatore.addCarta(model.getMazzo().carta());

        if (giocatore.isBusted() || giocatore.isBlackJack()) turnoSuccessivo();
    }
    
    /**
	 * metodo che passa al turno successivo
	 */
	public void stay()
	{	
		System.out.println("stay");
		turnoSuccessivo();
	}
	
    /**
	 * metodo privato che distribuisce le carte ai giocatori
	 */
    private void initCarte() 
    {
        for (Giocatore giocatore : model.getGiocatori())
        {
            giocatore.resetCarte();
            Carta carta1 = model.getMazzo().carta();
            Carta carta2 = model.getMazzo().carta();

            giocatore.addCarta(carta1);
            giocatore.addCarta(carta2);

            if (giocatore.isBlackJack()) turnoSuccessivo();

        }
        
        System.out.println("");
    }
    
    /**
	 * metodo privato che passa al turno successivo
	 */
    private void turnoSuccessivo() 
    {
        int turno = model.getTurno();
        model.setTurno(++turno);
        
        if (turno >= model.getGiocatori().size()) 
        {
            initCarte();
            model.setTurno(0);
        }
    }
}