package controller;

import model.ModelManager;
import model.partita.Giocatore;
import model.partita.carte.Carta;
import model.partita.carte.Mano;
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

    /**
     * costruttore del controller
     * crea le istanze di model e view e stabilisce la relazione di osservatore tra di loro.
     */
    
    //COSTRUTTORE
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
	 * metodo metodo che inizializza il menu 
	 * e controlla la presenza di un utente precedentemente creato, se non c'è chiede di crearlo
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
	 * metodo che inizializza la partita
	 */
	public void initPartita()
	{
		model.initMazzo();
		model.initGiocatori();
		
		distribuisciCarte();
		gioca();
	}
	
    /**
	 * metodo che aggiunge una carta alla mano corrente del giocatore giusto in base al turno della partita
	 */
    public void hit()
    {
    	Giocatore giocatore = model.getGiocatori().get(model.getTurno());
        
        giocatore.addCarta(model.getMazzo().carta());
        gioca();
    }
    
    /**
	 * metodo che passa alla mano o al turno successivo
	 */
	public void stay()
	{	
		System.out.println("stay");	
		manoSuccessiva();
	}
	
	/**
	 * metodo che permette all'utente di "splittare" le 2 carte iniziali quando hanno lo stesso simbolo
	 * in modo da giocare più mani nello stesso turno
	 */
	public void split()
	{
		Giocatore giocatore = model.getGiocatori().get(model.getTurno());
		Mano manoCorrente = giocatore.getManoCorrente();
		
        Carta carta1 = manoCorrente.getCarte().get(0);
        Carta carta2 = manoCorrente.getCarte().get(1);

        manoCorrente.reset();
        manoCorrente.addCarta(carta1);
        manoCorrente.addCarta(model.getMazzo().carta());
        
        System.out.println("");
        
        Mano nuovaMano = new Mano();
        nuovaMano.addCarta(carta2);
        nuovaMano.addCarta(model.getMazzo().carta());
        giocatore.getMani().add(nuovaMano); 

        gioca();
	}
	
	/**
	 * metodo che fa giocare il turno corrente
	 */
	private void gioca()
	{
        Giocatore giocatore = model.getGiocatori().get(model.getTurno());
        Mano manoCorrente = giocatore.getManoCorrente();
        
        //se siamo al turno 0 (turno dell'utente) e c'è la condizione per "splittare" allora fai comparire il pulsante split
        if (model.getTurno() == 0 && giocatore.canSplit()) 
        {
            view.setSplitVisible(true);
        } 
        else 
        {
            view.setSplitVisible(false);
        }

        //se la mano corrente è blackjack o sballata passa a quella successiva, se non c'è quella successiva vai al turno successivo"
        if (manoCorrente.isBlackJack() || manoCorrente.isBusted()) 
        {
        	manoSuccessiva();
        }
        
        //se non accade nulla aspetto che il giocatore clicca hit o stay o split
        //se non sono il giocatore gioca come farebbe il dealer
	}
	
    /**
	 * metodo privato che distribuisce le carte ai giocatori
	 */
    private void distribuisciCarte() 
    {
        //primo giro: distribuisci una carta a ciascun giocatore (incluso il dealer)
        for (Giocatore giocatore : model.getGiocatori()) 
        {
        	//svuota prima le mani del giocatore
            giocatore.resetStato(); 
            Carta carta1 = model.getMazzo().carta();
            giocatore.addCarta(carta1);
        }
        
        //secondo giro: distribuisci un'altra carta a ciascun giocatore (se è il dealer la carta è coperta)
        for (Giocatore giocatore : model.getGiocatori()) 
        {
            Carta carta2 = model.getMazzo().carta();
            giocatore.addCarta(carta2);
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
        
        //se i giocatori hanno finito, fai giocare il dealer e stabilisci vittoria o sconfitta dei giocatori
        if (turno >= model.getGiocatori().size() - 1) 
        {
            //giocaBot();
            
            //il round è finito, ridistribuisci le carte e ricomincia dal turno 0
            distribuisciCarte();
            model.setTurno(0);
        } 
        gioca(); 
    }
    
    /**
	 * metodo privato che passa alla mano successiva
	 * se non c'è una mano successiva passa al turno successivo
	 */
    private void manoSuccessiva() 
    {
        Giocatore giocatore = model.getGiocatori().get(model.getTurno());
        int manoCorrenteIndex = giocatore.getManoCorrenteIndex();

    	//conteggio mani: se il turno è il primo (quello dell'utente), aumenta il conteggio
        if(model.getTurno() == 0) model.setUtenteManiGiocate(model.getUtenteManiGiocate() + 1);
        
        
        if(manoCorrenteIndex + 1 >= giocatore.getMani().size()) turnoSuccessivo();
        else giocatore.setManoCorrenteIndex(manoCorrenteIndex + 1);
    }
}