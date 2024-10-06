package model;

import model.carte.Mazzo;
import model.giocatore.Giocatore;
import model.giocatore.GiocatoreDealer;
import model.giocatore.GiocatoreUtente;

import java.util.List;
import java.util.Observable;

/**
 * classe che rappresenta il model
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
	
	//GETTERS E SETTERS DELL'UTENTE
	public String getUsernameUtente()
	{
		return utente.getUsername();
	}
	
	public String getFilePathUtent()
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
	
	//METODI MODEL
	public void updateObservers(String s)
	{
		setChanged();
		notifyObservers(s);
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
		System.out.println("setUtente");
		updateObservers("setUtente");
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
		partita.initMazzo();
		partita.initGiocatori();
	}
	
	public boolean isPartitaPostBet()
	{
		return partita.isPostBet();
	}
	
	public boolean isPartitaFinita()
	{
		return partita.isFinita();
	}
	
	public void finePartita()
	{
		partita.aggiornaStatsGiocatori();
		partita.fine();
		System.out.println("finePartita");
		updateObservers("finePartita");
	}
		
    public Giocatore getGiocatoreCorrente()
	{
		return partita.getGiocatoreCorrente();
	}
    
    public void back()
    {
    	partita.back();
    	System.out.println("back");
		updateObservers("back");
    }
}