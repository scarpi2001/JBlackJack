package model;

import java.util.ArrayList;
import java.util.List;

import model.carte.Mano;
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
	 * flag che indica se la partita è all'inizio (pre-bet)
	 */
	private boolean inizio;
	
	/**
	 * scommessa dell'utente sulla partita in corso
	 */
	private int scommessaUtente;
	
	//COSTRUTTORE
	private Partita()
	{
		inizio = true;
		giocatori = new ArrayList<>();
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
	
	public void setInizio(boolean inizio)
	{
		this.inizio = inizio;
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
     * indica se la partita è all'inizio
     * @return true se è all'inizio, false altrimenti
     */
    public boolean inizio()
    {
    	return inizio;
    }   
    
	/**
     * indica se la partita è finita
     * @return true se è finito, false altrimenti
     */
    public boolean fine()
    {
    	return turno == getGiocatori().size();
    }   
    
    /**
     * confronta ogni mano con la mano del dealer e ne decreta lo stato (vinta, persa, pareggiata)
     */
    public void checkRisultati()
	{	
		List<Giocatore> giocatori = getGiocatori();
		Giocatore dealer = giocatori.get(giocatori.size() - 1);
		Mano manoDealer = dealer.getMani().get(0);
		int conteggioDealer = manoDealer.getConteggio();
		
		for(Giocatore giocatore : getGiocatori())
		{
			for(Mano mano : giocatore.getMani())
			{
				if(mano.getStato() == Mano.StatoMano.IN_CORSO)
				{
					//confronto ogni mano con quella del dealer
					if(manoDealer.isBusted() || mano.getConteggio() > conteggioDealer) 
					{
						mano.setStato(Mano.StatoMano.VINTA);
						//se la mano è vinta e il giocatore è l'utente
						if(giocatore instanceof GiocatoreUtente)
						{
							//riaggiungi il doppio della scommessa che l'utente aveva fatto all'inizio
							GiocatoreUtente utente = GiocatoreUtente.getInstance();
							utente.setChips(utente.getChips() + scommessaUtente * 2);
						}
					}
					else if(mano.getConteggio() < conteggioDealer) mano.setStato(Mano.StatoMano.PERSA);
					else 
					{
						mano.setStato(Mano.StatoMano.PAREGGIATA);
						//se la mano è pareggiata e il giocatore è l'utente
						if(giocatore instanceof GiocatoreUtente)
						{
							//riaggiungi la stessa scommessa che l'utente aveva fatto all'inizio
							GiocatoreUtente utente = GiocatoreUtente.getInstance();
							utente.setChips(utente.getChips() + scommessaUtente);
						}
					}
				}
			}
		}
	}
    
    /**
     * imposta il turno a 0 e svuota la lista di giocatori
     */
    public void back() 
    {		
    	setTurno(0);
    	giocatori.clear();
    	inizio = true;
    }
    
    /**
     * restituisce il giocatore che deve giocare il turno
     * @return il giocatore corrente
     */
    public Giocatore getGiocatoreCorrente()
	{
		return getGiocatori().get(turno);
	}
}
