package model;

import java.util.ArrayList;
import java.util.List;

import model.carte.Mazzo;
import model.giocatori.Giocatore;
import model.giocatori.GiocatoreBot;
import model.giocatori.GiocatoreDealer;
import model.giocatori.GiocatoreUtente;

/**
 * rappresenta la partita,
 * è singleton perchè si gioca una partita alla volta
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
	 * indica se la partita è in fase di "post bet" (la scommessa è stata fatta dall'utente)
	 */
	private boolean postBet;
	
	/**
	 * scommessa dell'utente sulla partita in corso
	 */
	private int scommessaUtente;
	
	/**
	 * indica se la seconda carta del dealer è stata scoperta
	 */
	private boolean cartaDealerScoperta;
	
	/**
	 * indica se sto distribuendo le carte
	 */
	private boolean distribuzione;
	
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
	
	public boolean isCartaDealerScoperta()
    {
    	return cartaDealerScoperta;
    } 
	public void setCartaDealerScoperta(boolean cartaDealerScoperta)
	{
		this.cartaDealerScoperta = cartaDealerScoperta;
	}
	
	public void setDistribuzione(boolean distribuzione)
	{
		this.distribuzione = distribuzione;
	}
	public boolean getDistribuzione()
	{
		return distribuzione;
	}
	
	//METODI
    /**
     * restituisce il giocatore che deve giocare il turno
     * @return il giocatore corrente
     */
    public Giocatore getGiocatoreCorrente()
	{
		return getGiocatori().get(getTurno());
	}
    
	/**
	 * inizializza il mazzo
	 */
	public void initMazzo()
	{
		setMazzo(new Mazzo());
		getMazzo().mix();
	}
	
	/**
	 * inizializza la lista di giocatori che partecipano alla partita, 
	 * inserisce prima l'utente, poi i bot, infine il dealer
	 */
	public void initGiocatori()
	{	
		getGiocatori().add(GiocatoreUtente.getInstance());
		for(int i = 0; i < getNumeroGiocatori() - 1; i++)
		{	
			getGiocatori().add(new GiocatoreBot());
		}	
		getGiocatori().add(GiocatoreDealer.getInstance());
	}
	     
	/**
     * indica se la partita è finita
     * @return true se lo è, false altrimenti
     */
    public boolean isFinita()
    {
    	return getTurno() == getGiocatori().size();
    }   
    
    /**
     * prepara la partita a riniziare
     */
    public void fine()
    {
    	aggiornaStatsGiocatori();
    	setTurno(0);
		setPostBet(false);
		setDistribuzione(false);
    }
    
    /**
     * aggiorna lo stato delle mani dei giocatori, 
     * se il giocatore è un utente ne aggiorna anche i dati
     */
    private void aggiornaStatsGiocatori()
	{	  	
		for(Giocatore giocatore : getGiocatori()) giocatore.aggiornaStats();		
	}
    
    /**
     * imposta il turno a 0, svuota la lista di giocatori e resetta lo stato di utente e dealer (gli unici giocatori "fissi")
     */
    public void back() 
    {		
    	setTurno(0);  	
    	getGiocatori().clear();
    	
    	//anche se tolgo tutti i giocatori lo stato di questi due rimane lo stesso
    	GiocatoreUtente.getInstance().resetStato();
    	GiocatoreDealer.getInstance().resetStato();
    	
    	setScommessaUtente(0);
    	setPostBet(false);
    	setDistribuzione(false);
    	setCartaDealerScoperta(false);
    }
}
