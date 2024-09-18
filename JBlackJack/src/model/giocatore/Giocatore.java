package model.giocatore;

import java.util.ArrayList;
import java.util.List;

import model.ModelManager;
import model.carte.Carta;
import model.carte.Mano;

/**
 * classe che rappresenta il giocatore
 * può essere un utente reale oppure un bot
 */
public abstract class Giocatore 
{
	//CAMPI
	/**
	 * nome del giocatore
	 */
	private String username;
	
	/**
	 * lista di mani del giocatore, ogni mano è una lista di carte
	 */
	private List<Mano> mani;
	
	/**
	 * l'indice della mano che il giocatore sta giocando
	 */
	private int manoCorrente;
	
	//COSTRUTTORE
	public Giocatore()
	{
		if(this instanceof GiocatoreDealer) username = "dealer";
		else if(!(this instanceof GiocatoreUtente)) username = "bot";  	
		
		mani = new ArrayList<>();
        mani.add(new Mano());  
	}
	
	//GETTERS E SETTERS
	public String getUsername() 
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	
    public List<Mano> getMani()
    {
        return mani;
    }
    
    public int getManoCorrenteIndex() 
	{
		return manoCorrente;
	}
    public void setManoCorrenteIndex(int mano) 
	{
		manoCorrente = mano;
	}
    
    public boolean isManoTerminata()
    {
    	return getManoCorrente().isTerminata();
    }
	
	//METODI
    /**
     * ogni tipo di giocatore (utente, bot...) ha un modo di giocare
     */
    public abstract void gioca();
    
    /**
	 * aggiunge una carta alla mano corrente del giocatore, 
	 * in base alle condizioni della mano la termina o meno
	 * e aggiorna gli osservatori del model
	 */
    public void hit() 
    {  	
    	ModelManager model = ModelManager.getInstance();
    	Carta carta = model.getMazzoPartita().carta();
    	
    	getManoCorrente().addCarta(carta);
    	getManoCorrente().setTerminata(manoTerminata());
    	model.updateObservers();
    }

    /**
	 * termina la mano corrente
	 */
    public void stay()
    {
    	getManoCorrente().setTerminata(true);
    }
    
    /**
	 * metodo che permette all'utente di "splittare" le 2 carte iniziali quando hanno lo stesso simbolo
	 * in modo da giocare più mani nello stesso turno
	 */
	public void split()
	{
		/*
		Giocatore giocatore = model.getGiocatori().get(model.getTurno());
		Mano manoCorrente = getManoCorrente();
		
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
     */
	}
    
    /**
	 * passa alla mano successiva
	 * aumenta il conteggio delle mani giocate se il giocatore è un utente
	 * se non c'è una mano successiva passa al turno successivo
	 */
    public void manoSuccessiva() 
    {
    	ModelManager model = ModelManager.getInstance();
    	//conteggio mani: se il turno è il primo (quello dell'utente), aumenta il conteggio
        if(this instanceof GiocatoreUtente) model.setManiGiocateUtente(model.getManiGiocateUtente() + 1);
        
        //se sono all'ultima mano del giocatore passa al turno successivo, altrimenti indica al giocatore di passare alla mano successiva
        if(manoCorrente + 1 == mani.size()) model.setTurnoPartita(model.getTurnoPartita() + 1);
        else setManoCorrenteIndex(manoCorrente + 1);
    }
    
	/**
	 * indica se la mano corrente è terminata per un blackjack o una sballata
	 * @return true se la mano è terminata, false altrimenti
	 */
	public boolean manoTerminata()
	{
		return getManoCorrente().isBlackJack() || getManoCorrente().isBusted();
	}
	
    /**
     * metodo che ritorna la mano corrente del giocatore
     * @return la mano corrente
     */
    public Mano getManoCorrente() 
	{
		return mani.get(manoCorrente);
	}
    
	/**
	 * metodo per riconoscere la condizione di split della mano corrente del giocatore
	 * @return true se la mano può essere splittata, false altrimenti
	 */
	public boolean canSplit()
	{
		return getManoCorrente().canSplit();
	}
	
	/**
	 * metodo per resettare lo stato del giocatore
	 */	
    public void resetStato() 
    {
        mani.clear();
        mani.add(new Mano());
        manoCorrente = 0;
    }
}
