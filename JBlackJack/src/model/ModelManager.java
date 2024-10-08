package model;

import java.util.List;
import java.util.Observable;

import model.carte.Mazzo;
import model.giocatore.Giocatore;
import model.giocatore.GiocatoreUtente;

/**
 * classe principale del package model 
 * ad essa è affidata la gestione dello stato della partia e dell'utente
 */
@SuppressWarnings("deprecation")
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
	 * partita di blackjack
	 */
	private Partita partita;
	
	//COSTRUTTORE
	/**
	 * il costruttore del model, 
	 * istanzia l'utente e la partita
	 */
	private ModelManager()
	{
		utente = GiocatoreUtente.getInstance();
		partita = Partita.getInstance();
	}
	public static ModelManager getInstance()
	{
		if (instance == null) instance = new ModelManager();
		return instance;
	}
	
	//GETTERS E SETTERS
	public GiocatoreUtente getUtente()
	{
		return utente;
	}
	
	public Partita getPartita()
	{
		return partita;
	}
	
	//GETTERS E SETTERS DELLA PARTITA
	public Mazzo getMazzoPartita() 
	{		
		return partita.getMazzo();
	}
	public void setMazzoPartita(Mazzo mazzo) 
	{		
		partita.setMazzo(mazzo);
	}
	
	public List<Giocatore> getGiocatoriPartita() 
	{		
		return partita.getGiocatori();
	}
	public void setGiocatoriPartita(List<Giocatore> giocatori) 
	{		
		partita.setGiocatori(giocatori);
	}
	
	public int getNumeroGiocatoriPartita() 
	{		
		return partita.getNumeroGiocatori();
	}
	public void setNumeroGiocatoriPartita(int numeroGiocatori) 
	{		
		partita.setNumeroGiocatori(numeroGiocatori);
	}
	
	public int getTurnoPartita() 
	{		
		return partita.getTurno();
	}
	public void setTurnoPartita(int turno) 
	{		
		partita.setTurno(turno);
	}
	
	public int getScommessaUtentePartita()
	{
		return partita.getScommessaUtente();
	}
	
	public boolean isCartaDealerScoperta()
	{
		return partita.isCartaDealerScoperta();
	}
	public void setCartaDealerScoperta(boolean cartaDealerScoperta)
	{
		partita.setCartaDealerScoperta(cartaDealerScoperta);
		updateObservers("cartaScoperta");
	}
	
	public void setDistribuzionePartita(boolean distribuzione)
	{
		partita.setDistribuzione(distribuzione);
		updateObservers("distribuzione: " + distribuzione);
	}
	public boolean getDistribuzionePartita()
	{
		return partita.getDistribuzione();
	}
	
	//GETTERS E SETTERS DELL'UTENTE
	public String getUsernameUtente()
	{
		return utente.getUsername();
	}
	
	public String getFilePathUtente()
	{
		return utente.getFilePath();
	}
	
	public int getChipsUtente()
	{
		return utente.getChips();
	}
	public void setChipsUtente(int chips)
	{
		utente.setChips(chips);
	}
	
	public int getManiGiocateUtente()
	{
		return utente.getManiGiocate();
	}
	public void setManiGiocateUtente(int maniGiocate)
	{
		utente.setManiGiocate(maniGiocate);
	}
	
	public int getManiVinteUtente()
	{
		return utente.getManiVinte();
	}
	public void setManiVinteUtente(int maniVinte)
	{
		utente.setManiVinte(maniVinte);
	}
	
	public int getManiPareggiateUtente()
	{
		return utente.getManiPareggiate();
	}
	public void setManiPareggiateUtente(int maniPareggiate)
	{
		utente.setManiPareggiate(maniPareggiate);
	}
	
	public int getManiPerseUtente()
	{
		return utente.getManiPerse();
	}
	public void setManiPerseUtente(int maniPerse)
	{
		utente.setManiPerse(maniPerse);
	}
	
	
	public int getLivelloUtente()
	{
		return utente.getLivello();
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
		getUtente().setDati(fileDatiUtentePath, fileUltimoUtentePath);        
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
		getUtente().creaUtente(username, fileUtentiPath, fileDatiUtentePath);
	}
	
	/**
 	 * elimina l'utente
	 * @param username l'username dell'utente da eliminare
	 * @param fileUtentiPath path del file degli utenti
	 * @param fileDatiUtentePath path del file dell'utente da eliminare
	 */
	public void eliminaUtente(String username, String fileUtentiPath, String fileDatiUtentePath)
	{
		getUtente().eliminaUtente(username, fileUtentiPath, fileDatiUtentePath);
	}
	
	/**
	 * ottiene la lista di utenti
	 * @param path path del file degli utenti
	 * @return la lista di utenti
	 */
	public List<String> getUtenti(String path) 
	{
		return FileUtils.leggiFile(path);
	}
	
	/**
	 * ottiene l'ultimo utente settato
	 * @param path path del file che contiene l'username dell'ultimo utente selezionato
	 */
	public String getUltimoUtente(String path) 
	{
		List<String> utenti = getUtenti(path);
		if(utenti.size() == 0) return null;
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
	
	//METODI PARTITA
	/**
	 * inizializza la partita
	 */
	public void initPartita()
	{	
		getPartita().initMazzo();
		getPartita().initGiocatori();
	}
	
	/**
	 * controlla che la partita sia in fase di "post bet"
	 * @return true se lo, false altrimenti
	 */
	public boolean isPartitaPostBet()
	{
		return getPartita().isPostBet();
	}
	
	/**
	 * controlla che la partita sia finita
	 * (la partita finisce quando si supera il turno del dealer)
	 * @return true se lo, false altrimenti
	 */
	public boolean isPartitaFinita()
	{
		return getPartita().isFinita();
	}
	
	/**
	 * definisce le azioni da compiere alla fine della partita
	 */
	public void finePartita()
	{
		getPartita().aggiornaStatsGiocatori();
		getPartita().fine();
		updateObservers();
	}
	
	/**
	 * ottiene il giocatore che sta giocando
	 * @return il giocatore corrente
	 */
    public Giocatore getGiocatoreCorrente()
	{
		return getPartita().getGiocatoreCorrente();
	}
    
    /**
     * torna al menu 
     */
    public void back()
    {
    	System.out.println("back");
    	getPartita().back();
		updateObservers();
    }
    
    //UPDATE OBSERVERS
    /**
     * aggiorna gli osservatori senza passargli niente
     */
    public void updateObservers()
  	{
  		setChanged();
  		notifyObservers();
  	}
    
    /**
     * aggiorna gli osservatori passandogli una stringa
     */
  	public void updateObservers(String s)
  	{
  		setChanged();
  		notifyObservers(s);
  	}
}