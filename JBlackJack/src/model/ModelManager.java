package model;

import model.carte.Mazzo;
import java.io.*;
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
	private UtenteGiocante utente;
	
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
	 * il costruttore del model, che istanzia l'utente
	 */
	private ModelManager()
	{
		utente = UtenteGiocante.getInstance();
		giocatori = new ArrayList<>();
	}
	
	/**
	 * metodo per ottenere/creare l'istanza del model
	 * @return l'istanza del model
	 */
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
		setChanged();
		notifyObservers();
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
	
	//METODI UTENTE
	/**
	 * metodo che aggiorna i dati dell’utente prendendoli dal file passato in input,
	 * e notifica gli osservatori
	 * @param nomeFile nome del file che contiene i dati dell'utente
	 */
	public void setUtente(String path)
	{
		utente.setDati(path);        
		setChanged();
		notifyObservers();
	}
	
	/**
	 * metodo per creare l'utente 
	 * crea l'utente e lo setta
	 * @param username l'username dell'utente
	 * @return true se la creazione va a buon fine, altrimenti false
	 */
	public boolean creaUtente(String username)
	{
		boolean res = utente.creaUtente(username);
		if(res) setUtente("src/resources/data/dati_utenti/" + username + "_dati.txt");
		return res;
	}
	
	/**
	 * metodo per eliminare l'utente 
	 * elimina l'utente e setta il primo della select degli utenti  
	 * @param username l'username dell'utente da eliminare
	 */
	public void eliminaUtente(String username)
	{
		utente.eliminaUtente(username);
		//setto il primo utente della lista di utenti 
		setUtente("src/resources/data/dati_utenti/" + FileUtils.leggiFile("src/resources/data/utenti.txt").get(0) + "_dati.txt");
	}
	
	/**
	 * metodo per leggere l'utente da un file contenente l'ultimo utente selezionato
	 * @param path path del file che contiene i dati dell'utente
	 */
	public String getUltimoUtente(String path) 
	{
		List<String> righe = FileUtils.leggiFile(path);
		return righe.get(righe.size() - 1);  
    }
	
	//METODI PARTITA
	/**
	 * metodo privato che inizializza il mazzo
	 */
	public void initMazzo()
	{
		mazzo = new Mazzo();
		mazzo.mix();
	}
	
	/**
	 * metodo privato che inizializza la lista di giocatori che partecipano alla partita
	 */
	public void initGiocatori()
	{	
		giocatori.add(utente);
		for(int i = 0; i < numeroGiocatori - 1; i++)
		{	
			giocatori.add(new Giocatore());
		}	
	}
	
	/**
	 * metodo che svuota la lista di giocatori
	 */
	public void clearGiocatori() 
	{		
		giocatori.clear();
	}
}