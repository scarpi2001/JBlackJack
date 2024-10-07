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
	private int manoCorrenteIndex;
	
	//COSTRUTTORE
	public Giocatore()
	{	
		if(this instanceof GiocatoreDealer) username = "Dealer";
		else if(!(this instanceof GiocatoreUtente)) username = "Bot";  	
		
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

    /**
     * metodo che permette ad un giocatore di scommettere,
     * è stato implementato sul giocatoreUtente
     * si trova in questa classe perchè in una futura implementazione del software
     * è prevista anche la scommessa di altri giocatori
     */
    public void scommetti(int scommessa){}
    
    /**
	 * aggiunge una carta alla mano corrente del giocatore, 
	 * e aggiorna gli osservatori del model
	 */
    public void hit() 
    {  	
    	ModelManager model = ModelManager.getInstance();
    	Carta carta = model.getMazzoPartita().carta();
    	
    	getManoCorrente().addCarta(carta);
    	System.out.println("hit");
    	model.updateObservers("hit");
    }

    /**
	 * termina la mano corrente
	 */
    public void stay()
    {
    	getManoCorrente().setTerminata(true);
    }

    /**
     * permette ad un giocatore di "splittare" la mano iniziale in due mani
     * se le carte che compongono la mano iniziale hanno lo stesso simbolo
     * è stato implementato sul giocatoreUtente
     * si trova in questa classe perchè in una futura implementazione del software
     * è previsto anche lo split di altri giocatori
     */
	public void split(){}
	
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
        
        //se sono all'ultima mano del giocatore passa al turno successivo, altrimenti indica al giocatore di passare alla mano successiva
        if(manoCorrenteIndex + 1 == mani.size()) 
        {
        	model.setTurnoPartita(model.getTurnoPartita() + 1);
        	System.out.println("turno succ");
        }
        else 
        {
        	setManoCorrenteIndex(manoCorrenteIndex + 1);
        	System.out.println("mano succ");
        }
        
        model.updateObservers("mano succ");
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
     * confronta le mani del giocatore con quella del dealer,
     * e aggiorna lo stato delle mani (nel caso dell'utente anche i dati)
     * 
     */
	public abstract void aggiornaStats();
    
	/**
	 * confronta la singola mano del giocatore con quella del dealer,
	 * @param mano la mano del giocatore
	 * @param manoDealer la mano del dealer
	 */
	public abstract void confrontaManoConDealer(Mano mano, Mano manoDealer);

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
