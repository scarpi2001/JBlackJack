package model.carte;
import java.util.Random;

import model.carte.Carta.Seme;
import model.carte.Carta.Simbolo;

/**
 * classe che rappresenta un mazzo di carte francesi
 */
public class Mazzo 
{
	/**
	 * lista di carte che rappresenta il mazzo
	 */
    private Carta[] mazzo;
    
    /**
     * punto del mazzo nel quale mi trovo quando sto distribuendo le carte
     */
    private int cartaCorrente;

    public Mazzo() 
    {
    	
    	
        mazzo = new Carta[52];
        int index = 0;
        
        for (Carta.Seme seme : Carta.Seme.values()) 
        {
            for (Carta.Simbolo simbolo : Carta.Simbolo.values()) 
            {
                mazzo[index++] = new Carta(seme, simbolo);
            }
        }
    	
    	
    	/*
    	mazzo = new Carta[3];
    	mazzo[0] = new Carta(Seme.CUORI, Simbolo.A);
    	mazzo[1] = new Carta(Seme.PICCHE, Simbolo.A);
    	mazzo[2] = new Carta(Seme.PICCHE, Simbolo.K);
    	mazzo[2] = new Carta(Seme.CUORI, Simbolo.SETTE);
    	mazzo[3] = new Carta(Seme.PICCHE, Simbolo.OTTO);
    	mazzo[4] = new Carta(Seme.CUORI, Simbolo.CINQUE);
    	mazzo[5] = new Carta(Seme.PICCHE, Simbolo.K);
    	mazzo[6] = new Carta(Seme.PICCHE, Simbolo.Q);
    	mazzo[7] = new Carta(Seme.CUORI, Simbolo.A);
    	mazzo[8] = new Carta(Seme.PICCHE, Simbolo.A);
    	mazzo[9] = new Carta(Seme.CUORI, Simbolo.A);
    	mazzo[10] = new Carta(Seme.PICCHE, Simbolo.A);
    	 */
    	
    }

    /**
     * metodo per mescolare il mazzo
     */
    public void mix() 
    {
        Random random = new Random();
        for (int i = 0; i < mazzo.length; i++) 
        {
            int index = random.nextInt(mazzo.length);
            Carta temp = mazzo[i];
            mazzo[i] = mazzo[index];
            mazzo[index] = temp;
        }
    }

    /**
     * metodo per distribuire una carta
     * distribuisce carte fino a che il mazzo non finisce,
     * una volta finito, il mazzo viene rimescolato 
     * e la distribuzione di carte riparte dall'inizio del mazzo
     * @return un oggetto di tipo Carta (una carta dal mazzo)
     */
    public Carta carta() 
    {
        if (cartaCorrente < mazzo.length) 
        {
            return this.mazzo[this.cartaCorrente++];            
        } 
        else 
        {
        	System.out.println("mazzo finito");
            this.mix();
            this.cartaCorrente = 0;
            return this.mazzo[this.cartaCorrente++];
        }   
    }
}

