package model;

import model.carte.Carta;
import model.carte.Mazzo;
import model.giocatore.Giocatore;
import model.giocatore.GiocatoreBot;
import model.giocatore.GiocatoreDealer;
import model.giocatore.GiocatoreUtente;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * classe che rappresenta il model
 */
public class ModelManager extends Observable 
{
	//CAMPI
	/**
	 * istanza del model
	 */
	private static ModelManager instance;
	
	/**
	 * utente che sta utilizzando l'applicazione
	 */
	private GiocatoreUtente utente;
	
	/**
	 * numero di giocatori che partecipano alla partita
	 */
	private int numeroGiocatori;
	
	/**
	 * lista dei giocatori che partecipano alla partita
	 */
	private List<Giocatore> giocatori;
	
	/**
	 * mazzo di carte utilizzato in partita
	 */
	private Mazzo mazzo;
	
	/**
	 * variabile utilizzata per stabilire quale giocatore deve giocare
	 */
	private int turno;
	
	//COSTRUTTORE
	/**
	 * il costruttore del model, 
	 * istanzia l'utente e la lista di giocatori
	 */
	private ModelManager()
	{
		utente = GiocatoreUtente.getInstance();
		giocatori = new ArrayList<>();
	}
	
	public static ModelManager getInstance()
	{
		if (instance == null) instance = new ModelManager();
		return instance;
	}
	
	//GETTERS E SETTERS
	public Mazzo getMazzo() 
	{		
		return mazzo;
	}
	public void setMazzo(Mazzo mazzo) 
	{		
		this.mazzo = mazzo;
	}
	
	public List<Giocatore> getGiocatori() 
	{		
		return giocatori;
	}
	public void setGiocatori(List<Giocatore> giocatori) 
	{		
		this.giocatori = giocatori;
	}
	public int getNumeroGiocatori() 
	{		
		return numeroGiocatori;
	}
	public void setNumeroGiocatori(int numeroGiocatori) 
	{		
		this.numeroGiocatori = numeroGiocatori;
	}
	
	public int getTurno() 
	{		
		return turno;
	}
	public void setTurno(int turno) 
	{		
		this.turno = turno;
	}
	
	//GETTERS E SETTERS DELL'UTENTE
	public String getUtenteUsername()
	{
		return utente.getUsername();
	}
	
	public String getUtenteFilePath()
	{
		return utente.getFilePath();
	}
	
	public int getUtenteChips()
	{
		return utente.getChips();
	}
	
	public int getUtenteManiGiocate()
	{
		return utente.getManiGiocate();
	}
	public void setUtenteManiGiocate(int maniGiocate)
	{
		utente.setManiGiocate(maniGiocate);
		updateObservers();
	}
	
	public int getUtenteManiVinte()
	{
		return utente.getManiVinte();
	}
	
	public int getUtenteManiPerse()
	{
		return utente.getManiPerse();
	}
	
	public int getUtenteLivello()
	{
		return utente.getLivello();
	}
	
	//METODI MODEL
	public void updateObservers()
	{
		setChanged();
		notifyObservers();
	}
	
	//METODI UTENTE
	/**
	 * aggiorna i dati dell’utente prendendoli dal file fileDatiUtentePath,
	 * scrive l'username dell'utente settato nel file fileUltimoUtentePath
	 * e notifica gli osservatori
	 * @param fileDatiUtentePath path del file che contiene i dati dell'utente
	 * @param fileUltimoUtentePath path del file che conterrà l'username dell'utente selezionato
	 */
	public void setUtente(String fileDatiUtentePath, String fileUltimoUtentePath)
	{
		utente.setDati(fileDatiUtentePath, fileUltimoUtentePath);        
		updateObservers();
	}
	
	/**
	 * crea l'utente, scrivendo l'username nel file fileUtentiPath e creando il file fileDatiUtentePath
	 * @param username l'username dell'utente
	 * @param fileUtentiPath path del file degli utenti
	 * @param fileDatiUtentePath path del file dell'utente da creare
	 */
	public void creaUtente(String username, String fileUtentiPath, String fileDatiUtentePath)
	{
		utente.creaUtente(username, fileUtentiPath, fileDatiUtentePath);
	}
	
	/**
 	 * elimina l'utente
	 * @param username l'username dell'utente da eliminare
	 * @param fileUtentiPath path del file degli utenti
	 * @param fileDatiUtentePath path del file dell'utente da eliminare
	 */
	public void eliminaUtente(String username, String fileUtentiPath, String fileDatiUtentePath)
	{
		utente.eliminaUtente(username, fileUtentiPath, fileDatiUtentePath);
	}
	
	
	/**
	 * ottiene la lista di utenti
	 * @param username username da controllare
	 * @param path path del file degli utenti
	 * @return la lista di utenti
	 */
	public List<String> getUtenti(String path) 
	{
		return FileUtils.leggiFile(path);
	}
	
	/**
	 * ottiene l'ultimo utente selezionato
	 * @param path path del file che contiene l'username dell'ultimo utente selezionato
	 */
	public String getUltimoUtente(String path) 
	{
		List<String> utenti = getUtenti(path);
		return utenti.get(utenti.size() - 1);  
	}
	
	/**
	 * controlla che l'username sia presente nel file 
	 * @param username username da controllare
	 * @param fileUtentiPath path del file da controllare
	 * @return true se è presente, false altrimenti
	 */
	public boolean usernamePresente(String username, String fileUtentiPath) 
	{
	    return FileUtils.leggiFile(fileUtentiPath).contains(username);
	}
	
	//METODI INIZIALIZZAZIONE PARTITA
	/**
	 * inizializza il mazzo
	 */
	public void initMazzo()
	{
		mazzo = new Mazzo();
		mazzo.mix();
	}
	
	/**
	 * inizializza la lista di giocatori che partecipano alla partita
	 * inserisce prima l'utente, poi i bot, l'ultimo giocatore è il dealer
	 */
	public void initGiocatori()
	{	
		giocatori.add(utente);
		for(int i = 0; i < numeroGiocatori - 1; i++)
		{	
			giocatori.add(new GiocatoreBot());
		}	
		giocatori.add(new GiocatoreDealer());
	}
	
	//METODI PARTITA
	public void giocaTurno()
	{
		getGiocatoreCorrente().gioca();
	}
	
	/**
	 * distribuisce le carte ai giocatori
	 * simula la distribuzione di carte reale del blackjack
	 */
    public void distribuisciCarte() 
    {    	
    	//resetta lo stato dei giocatori
    	for (Giocatore giocatore : getGiocatori()) 
        { 
            giocatore.resetStato();
        }
    	
        //primo giro
        for (Giocatore giocatore : getGiocatori()) 
        { 
            giocatore.hit();
        }
        
        //secondo giro
        for (Giocatore giocatore : getGiocatori()) 
        {
            giocatore.hit();                
        }
    }
		
    /**
	 * metodo che passa alla mano successiva
	 * se non c'è una mano successiva passa al turno successivo
	 */
    public void manoSuccessiva() 
    {
    	Giocatore giocatore = getGiocatoreCorrente();
        int manoCorrenteIndex = giocatore.getManoCorrenteIndex();

    	//conteggio mani: se il turno è il primo (quello dell'utente), aumenta il conteggio
        //if(turno == 0) setUtenteManiGiocate(getUtenteManiGiocate() + 1);
        
        //se sono all'ultima mano del giocatore passa al turno successivo, altrimenti indica che sei passato alla mano successiva
        if(manoCorrenteIndex + 1 == giocatore.getMani().size()) turnoSuccessivo();
        else giocatore.setManoCorrenteIndex(manoCorrenteIndex + 1);
        
        giocaTurno();
    }
    
    /**
	 * metodo privato che passa al turno successivo
	 */
    private void turnoSuccessivo() 
    {
        turno++;   
        if(turno == getGiocatori().size())
        {
        	setTurno(0);
        	distribuisciCarte();
        }
    }  
        
    public boolean roundFinito()
    {
    	return turno >= getGiocatori().size() - 1;
    }   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	
    /**
     * metodo per ottenere il giocatore che deve giocare il turno
     * @return il giocatore corrente
     */
    public Giocatore getGiocatoreCorrente()
	{
		return getGiocatori().get(turno);
	}
    
    /**
     * rimette il turno a 0 e svuota la lista di giocatori
     */
    public void back() 
    {		
    	setTurno(0);
    	giocatori.clear();
    }
}