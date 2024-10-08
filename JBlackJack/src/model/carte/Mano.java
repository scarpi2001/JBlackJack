package model.carte;

import java.util.ArrayList;
import java.util.List;

/**
 * classe che rappresenta una mano di blackjack, una mano non è altro che una lista di carte
 */
public class Mano
{
	/**
	 * enum degli stati di una mano di blackjack
	 */
	public enum Stato
	{
		VINTA, PERSA, PAREGGIATA, IN_CORSO;
	}
	
	//CAMPI
	/**
	 * lista di carte che compongono la mano
	 */
    private List<Carta> carte;
    
    /**
     * indica se la mano è "sballata" o no
     */
    private boolean sballata;
    
    /**
     * indica se la mano è un blackjack(21) o no
     */
    private boolean blackjack;
    
    /**
     * indica se la mano è soft o no
     */
    private boolean soft;
    
	/**
	 * indica se la mano è terminata o no
	 */
	private boolean terminata;
	
	/**
	 * indica lo stato della mano (in corso, vinta, persa, pareggiata)
	 */
	private Stato stato;
    
    /**
     * conteggio del valore totale della mano
     */
    private int conteggio;

    public Mano()
    {
        carte = new ArrayList<>();
        stato = Stato.IN_CORSO;
    }
    
    //GETTERS E SETTER
    public int getConteggio() 
    {
        return conteggio;
    }
    private void setConteggio(int conteggio) 
    {
        this.conteggio = conteggio;
    }

    public boolean isBusted()
    {
        return sballata;
    }
    private void setBusted(boolean sballata) 
    {
        this.sballata = sballata;
    }

    public boolean isBlackJack() 
    {
        return blackjack;
    }
    private void setBlackJack(boolean blackjack) 
    {
        this.blackjack = blackjack;
    }

    public boolean isTerminata()
    {
        return terminata;
    }
    public void setTerminata(boolean terminata)
    {
        this.terminata = terminata;
    }

    public boolean isSoft()
    {
        return soft;
    }
    private void setSoft(boolean soft)
    {
        this.soft = soft;
    }

    public List<Carta> getCarte() 
    {
        return carte;
    }

    public Stato getStato()
    {
        return stato;
    }
    public void setStato(Stato stato)
    {
        this.stato = stato;
    }
    
    //METODI
    /**
	 * aggiunge una carta alla mano, modificando il conteggio, 
	 * se necessario attribuisce alla mano lo stato di "sballata" o di "blackjack"
	 * @param carta la carta da aggiugere
	 */
    public void addCarta(Carta carta) 
    {
        getCarte().add(carta);
        setConteggio(getConteggio() + carta.getValore());

        if (getConteggio() <= 11 && carta.isAsso()) 
        {
            setConteggio(getConteggio() + 10);
            setSoft(true);
        }

        if (isSoft() && getConteggio() > 21) 
        {
            setSoft(false);
            setConteggio(getConteggio() - 10);
        }
        
        if (getConteggio() == 21) 
        {
        	setTerminata(true);
            setBlackJack(true);
        }
        else if (getConteggio() > 21)
        {
        	setTerminata(true);
            setBusted(true);
            setStato(Stato.PERSA);
        }
    }

    /**
	 * metodo per riconoscere la condizione di split della mano, 
	 * @return true se la mano è splittabile, false altrimenti
	 */
    public boolean canSplit() 
    {
        return getCarte().size() == 2 && getCarte().get(0).getSimbolo() == getCarte().get(1).getSimbolo();
    }

    /**
	 * metodo per resettare lo stato della mano
	 */	
    public void reset()
    {
        getCarte().clear();
        setBusted(false);
        setBlackJack(false);
        setSoft(false);
        setTerminata(false);
        setStato(Stato.IN_CORSO);
        setConteggio(0);
    }
}