package model.partita;

import java.util.ArrayList;
import java.util.List;

import model.UtenteGiocante;
import model.partita.carte.Carta;
import model.partita.carte.Mano;

/**
 * classe che rappresenta il giocatore
 * può essere un utente reale oppure un bot
 */
public class Giocatore 
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
		if(this instanceof Dealer) username = "dealer";
		else if(!(this instanceof UtenteGiocante)) username = "bot";  	
		
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
    /**
	 * metodo per aggiungere una carta alla mano corrente del giocatore
	 * @param carta la carta da aggiugere
	 */
    public void addCarta(Carta carta) 
    {
    	System.out.print(username + ", ");
    	getManoCorrente().addCarta(carta);
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
