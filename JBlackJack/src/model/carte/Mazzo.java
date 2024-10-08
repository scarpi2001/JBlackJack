package model.carte;
import java.util.Random;

/**
 * classe che rappresenta un mazzo di carte francesi
 */
public class Mazzo 
{
	/**
	 * lista di carte che compone il mazzo
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
     * metodo per distribuire una carta,
     * distribuisce carte fino a che il mazzo non finisce,
     * una volta finito, il mazzo viene rimescolato 
     * e la distribuzione di carte riparte dall'inizio del mazzo
     * @return una carta dal mazzo
     */
    public Carta carta() 
    {
        if (cartaCorrente < mazzo.length) 
        {
            return this.mazzo[this.cartaCorrente++];            
        } 
        else 
        {
            this.mix();
            this.cartaCorrente = 0;
            return this.mazzo[this.cartaCorrente++];
        }   
    }
}

