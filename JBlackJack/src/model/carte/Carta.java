package model.carte;

/**
 * classe che rappresenta una carta da gioco francese
 */
public class Carta 
{
	//INIZIO ENUMS
	//il seme mi serve esclusivamente a determinare l'immagine
	/**
	 * enumerazione dei semi delle carte
	 */
	public enum Seme 
	{
	    CUORI, QUADRI, FIORI, PICCHE;
	}
	
	/**
	 * enumerazione dei valori delle carte
	 */
	public enum Valore 
	{
	    DUE(2), 
	    TRE(3),
	    QUATTRO(4), 
	    CINQUE(5), 
	    SEI(6), 
	    SETTE(7), 
	    OTTO(8),
	    NOVE(9),
	    DIECI(10), 
	    
	    J(10), 
	    Q(10),
	    K(10), 
	    A(1, 11);  

	    private final int valore;
	    private final int valoreAlternativo;

	    Valore(int valore) 
	    {
	        this.valore = valore;
	        this.valoreAlternativo = valore;  
	    }

	    Valore(int valore, int valoreAlternativo)
	    {
	        this.valore = valore;
	        this.valoreAlternativo = valoreAlternativo;
	    }

	    public int getValoreIntero() 
	    {
	        return this.valore;
	    }

	    public int getValoreInteroAlternativo() 
	    {
	        return this.valoreAlternativo;
	    }
	}
	//FINE ENUMS
	
	/**
	 * immagine della carta
	 */
	private final String immagine;
	
	/**
	 * valore numerico della carta
	 */
    private final int valore;

    public Carta(Seme seme, Valore valore) 
    {
        this.valore = valore.getValoreIntero();   
        immagine = "src/resources/images/carte/" + valore + "_" + seme + ".jpg";
    }
    
    public String getImmagine() 
    {
        return immagine;
    }

    public int getValore() 
    {
        return valore;
    }
}