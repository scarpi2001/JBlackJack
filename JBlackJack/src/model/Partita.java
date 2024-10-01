package model;

import java.util.ArrayList;
import java.util.List;

import model.carte.Mazzo;
import model.giocatore.Giocatore;
import model.giocatore.GiocatoreBot;
import model.giocatore.GiocatoreDealer;
import model.giocatore.GiocatoreUtente;

/**
 * classe che rappresenta la partita
 */
public class Partita 
{
	//CAMPI
	/**
	 * istanza della partita
	 */
	private static Partita instance;
	
	/**
	 * mazzo di carte utilizzato in partita
	 */
	private Mazzo mazzo;
	
	/**
	 * variabile utilizzata per stabilire il turno della partita
	 */
	private int turno;
	
	/**
	 * numero di giocatori che partecipano alla partita
	 */
	private int numeroGiocatori;
	
	/**
	 * lista dei giocatori che partecipano alla partita
	 */
	private List<Giocatore> giocatori;
	
	/**
	 * flag che indica se la partita è in fase di "post bet" (la scommessa è stata fatta dall'utente)
	 */
	private boolean postBet;
	
	/**
	 * scommessa dell'utente sulla partita in corso
	 */
	private int scommessaUtente;
	
	//COSTRUTTORE
	private Partita()
	{
		giocatori = new ArrayList<>();
		numeroGiocatori = 1;
	}
	public static Partita getInstance()
	{
		if (instance == null) instance = new Partita();
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
	
    public boolean isPostBet()
    {
    	return postBet;
    } 
	public void setPostBet(boolean postBet)
	{
		this.postBet = postBet;
	}
	
	public int getScommessaUtente()
	{
		return scommessaUtente;
	}
	public void setScommessaUtente(int scommessaUtente)
	{
		this.scommessaUtente = scommessaUtente;
	}
	
	//METODI
    /**
     * restituisce il giocatore che deve giocare il turno
     * @return il giocatore corrente
     */
    public Giocatore getGiocatoreCorrente()
	{
		return getGiocatori().get(turno);
	}
    
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
	 * inserisce prima l'utente, poi i bot, infine il dealer
	 */
	public void initGiocatori()
	{	
		giocatori.add(GiocatoreUtente.getInstance());
		for(int i = 0; i < numeroGiocatori - 1; i++)
		{	
			giocatori.add(new GiocatoreBot());
		}	
		giocatori.add(new GiocatoreDealer());
	}
	     
	/**
     * indica se la partita è finita
     * @return true se è finito, false altrimenti
     */
    public boolean isFinita()
    {
    	return turno == getGiocatori().size();
    }   
    
    /**
     * aggiorna lo stato delle mani dei giocatori
     * se il giocatore è un utente ne aggiorna anche i dati
     */
    public void aggiornaStatsGiocatori()
	{	  	
		for(Giocatore giocatore : giocatori)
		{
			giocatore.aggiornaStats();
		}
	}
    
    /**
     * imposta il turno a 0 e svuota la lista di giocatori
     */
    public void back() 
    {		
    	setTurno(0);
    	giocatori.clear();
    	scommessaUtente = 0;
    	postBet = false;
    }
}
