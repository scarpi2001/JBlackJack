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
	public enum StatoMano
	{
		VINTA, PERSA, PAREGGIATA, IN_CORSO;
	}
	
	//CAMPI
	/**
	 * lista di carte di cui è composta la mano
	 */
    private List<Carta> carte;
    
    /**
     * flag che mi indica se la mano è "sballata" o no
     */
    private boolean sballata;
    
    /**
     * flag che mi indica se la mano è un blackjack(21) o no
     */
    private boolean blackjack;
    
    /**
     * flag che mi indica se la mano è soft o hard
     */
    private boolean soft;
    
	/**
	 * flag che indica se la mano che sta giocando il giocatore è terminata o no
	 */
	private boolean terminata;
	
	/**
	 * indica lo stato della mano (in corso, vinta, persa, pareggiata)
	 */
	private StatoMano statoMano;
    
    /**
     * conteggio del valore totale della mano
     */
    private int conteggio;

    public Mano()
    {
        carte = new ArrayList<>();
        statoMano = StatoMano.IN_CORSO;
    }
    
    //GETTERS E SETTER
    public int getConteggio() 
    {
        return conteggio;
    }

    public boolean isBusted()
    {
        return sballata;
    }

    public boolean isBlackJack() 
    {
        return blackjack;
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
    
    public List<Carta> getCarte() 
    {
        return carte;
    }
    
    public StatoMano getStato()
    {
    	return statoMano;
    }
    public void setStato(StatoMano statoMano)
    {
    	this.statoMano = statoMano;
    }
    
    //METODI
    /**
	 * metodo per aggiungere una carta alla mano
	 * riconosce lo stato di "sballata" e di "blackjack"
	 * @param carta la carta da aggiugere
	 */
    public void addCarta(Carta carta) 
    {
        carte.add(carta);
        conteggio += carta.getValore();

        //se ho beccato il primo asso, lo faccio valere 11 invece che 1, entrando in una "soft hand"
        if (conteggio <= 11 && carta.isAsso()) 
        {
            conteggio += 10;
            soft = true;
        }
        //se sono in una "soft hand" e ho "sballato" il primo asso torna a valere 1 invece che 11 ed esco dalla "soft hand" (hard hand)
        if (soft && conteggio > 21) 
        {
            soft = false;
            conteggio -= 10;
        }
                 
        //check se la mano è sballata oppure se ha fatto blackjack
        if (conteggio == 21) 
        {
            blackjack = true;
        }
        else if (conteggio > 21)
        {
            sballata = true;
            statoMano = StatoMano.PERSA;
        }
    }

    /**
	 * metodo per riconoscere la condizione di split della mano
	 * @return true se la mano è splittabile, false altrimenti
	 */
    public boolean canSplit() 
    {
        return carte.size() == 2 && carte.get(0).getSimbolo() == carte.get(1).getSimbolo();
    }

    /**
	 * metodo per resettare lo stato della mano
	 */	
    public void reset()
    {
        carte.clear();
        sballata = false;
        blackjack = false;
        soft = false;
        terminata = false;
        statoMano = Mano.StatoMano.IN_CORSO;
        conteggio = 0;
    }
}