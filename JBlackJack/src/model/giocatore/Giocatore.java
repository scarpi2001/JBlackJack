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
		//all'inizio il giocatore ha una sola mano
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
	
	//METODI
    //se è l'utente gioca in un modo, se è un bot gioca in un altro
    public abstract boolean gioca();
    
    /**
	 * metodo per aggiungere una carta alla mano corrente del giocatore
	 * @param carta la carta da aggiugere
	 */
    public void addCarta(Carta carta) 
    {  	
    	getManoCorrente().addCarta(carta);
    }
    
    /**
	 * aggiunge una carta alla mano corrente del giocatore giusto in base al turno della partita
	 */
    public void hit()
    {
    	/*
    	addCarta(model.getMazzo().carta());
    	gioca();
     */
    }
    /**
	 * passa alla mano o al turno successivo
	 */
    public void stay()
    {
    	/*
    	model.manoSuccessiva();
     */
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
    
	public boolean isFinito()
	{
		Mano manoCorrente = getManoCorrente();
		return manoCorrente.isBlackJack() || manoCorrente.isBusted();
	}
    /**
     * metodo che ritorna la mano corrente del giocatore
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
