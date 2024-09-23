package model.giocatore;

import java.util.ArrayList;
import java.util.List;

import model.ModelManager;
import model.Partita;
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
	private int manoCorrenteIndex;
	
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
		return manoCorrenteIndex;
	}
    public void setManoCorrenteIndex(int index) 
	{
    	manoCorrenteIndex = index;
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
    
    public void bet()
    {
    	Partita.getInstance().setInizio(false);
    }

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
    	System.out.println("hit");
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
	 * metodo che permette al giocatore di "splittare" le 2 carte iniziali quando hanno lo stesso simbolo
	 * in modo da giocare più mani nello stesso turno
	 */
	public void split()
	{
		ModelManager model = ModelManager.getInstance();
		Mano manoCorrente = getManoCorrente();
		
        Carta carta1 = manoCorrente.getCarte().get(0);
        Carta carta2 = manoCorrente.getCarte().get(1);

        manoCorrente.reset();
        manoCorrente.addCarta(carta1);
        manoCorrente.addCarta(model.getMazzoPartita().carta());
        manoCorrente.setTerminata(manoTerminata());
        
        Mano nuovaMano = new Mano();
        nuovaMano.addCarta(carta2);
        nuovaMano.addCarta(model.getMazzoPartita().carta());
        nuovaMano.setTerminata(manoTerminata());
        
        getMani().add(nuovaMano); 

        System.out.println("split");
        model.updateObservers();
	}
	
	/**
	 * metodo per riconoscere la condizione di split della mano corrente del giocatore
	 * @return true se la mano corrente può essere splittata, false altrimenti
	 */
	public boolean canSplit()
	{
		return getManoCorrente().canSplit();
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
        if(manoCorrenteIndex + 1 == mani.size()) model.setTurnoPartita(model.getTurnoPartita() + 1);
        else setManoCorrenteIndex(manoCorrenteIndex + 1);
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
		return mani.get(manoCorrenteIndex);
	}
	
	/**
	 * metodo per resettare lo stato del giocatore
	 */	
    public void resetStato() 
    {
        mani.clear();
        mani.add(new Mano());
        manoCorrenteIndex = 0;
    }
}
