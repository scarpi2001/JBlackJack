package model.carte;

/**
 * classe che rappresenta una carta da gioco francese
 */
public class Carta 
{
	//il seme mi serve esclusivamente a determinare l'immagine
	/**
	 * enum dei semi delle carte
	 */
	public enum Seme 
	{
	    CUORI, QUADRI, FIORI, PICCHE;
	}
	
	/**
	 * enum dei simboli delle carte
	 */
	public enum Simbolo 
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
	    A(1); 

	    private int valore;
	    
	    Simbolo(int valore) 
	    {
	        this.valore = valore;
	    }

	    public int getValore() 
	    {
	        return this.valore;
	    }
	}
	
	/**
	 * immagine della carta
	 */
	private String immagine;
	
	/**
	 * valore numerico della carta
	 */
    private int valore;
     
    /**
     * seme della carta
     */
    private Seme seme;
    
    /**
     * simbolo della carta
     */
    private Simbolo simbolo;
    
    public Carta(Seme seme, Simbolo simbolo) 
    {
    	this.simbolo = simbolo;
    	this.seme = seme;
        valore = simbolo.getValore();    
        immagine = "src/resources/images/carte/" + seme + "_" + simbolo + ".jpg";
    }
    
    public String getImmagine() 
    {
        return immagine;
    }

    public int getValore() 
    {
        return valore;
    }
    
    public boolean isAsso()
    {
    	return simbolo == Simbolo.A;
    }
    
    //utilizzato per test da console
    public String toString()
    {
    	return seme + " " + simbolo;
    }
}