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
    public boolean fine()
    {
    	return turno == getGiocatori().size();
    }   
    
    /**
     * confronta ogni mano con la mano del dealer e ne decreta lo stato (vinta, persa, pareggiata)
     * se il giocatore è l'utente fa un serie di modifiche ai suoi dati in base al risultato
     */
    public void checkMani()
	{	
    	ModelManager model = ModelManager.getInstance(); 
		List<Giocatore> giocatori = getGiocatori();
		Giocatore dealer = giocatori.get(giocatori.size() - 1);
		Mano manoDealer = dealer.getMani().get(0);
		int conteggioDealer = manoDealer.getConteggio();
		
		for(Giocatore giocatore : getGiocatori())
		{
			for(Mano mano : giocatore.getMani())
			{
				if(giocatore instanceof GiocatoreUtente) model.setManiGiocateUtente(model.getManiGiocateUtente() + 1);
				
				if(mano.getStato() == Mano.Stato.IN_CORSO)
				{
					//confronto ogni mano con quella del dealer
					if(manoDealer.isBusted() || mano.getConteggio() > conteggioDealer) 
					{
						mano.setStato(Mano.Stato.VINTA);
						if(giocatore instanceof GiocatoreUtente)
						{
							//restituisci all'utente il doppio della scommessa che aveva fatto all'inizio
							model.setChipsUtente(model.getChipsUtente() + scommessaUtente * 2);
							model.setManiVinteUtente(model.getManiVinteUtente() + 1);							
						}
					}
					else if(mano.getConteggio() < conteggioDealer) 
					{
						mano.setStato(Mano.Stato.PERSA);
						if(giocatore instanceof GiocatoreUtente)
						{
							model.setManiPerseUtente(model.getManiPerseUtente() + 1);							
						}
					}
					else 
					{
						mano.setStato(Mano.Stato.PAREGGIATA);
						if(giocatore instanceof GiocatoreUtente)
						{
							//restituisci all'utente la stessa scommessa che aveva fatto all'inizio
							model.setChipsUtente(model.getChipsUtente() + scommessaUtente);
							model.setManiPareggiateUtente(model.getManiPareggiateUtente() + 1);							
						}
					}
				}
				else if (mano.getStato() == Mano.Stato.PERSA && giocatore instanceof GiocatoreUtente) model.setManiPerseUtente(model.getManiPerseUtente() + 1);				
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
    	scommessaUtente = 0;
    	postBet = false;
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
